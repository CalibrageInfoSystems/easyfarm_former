package in.calibrage.easyfarm.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.io.IOException;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.Common.BaseFragment;
import in.calibrage.easyfarm.Adapter.PlotDetailsAdapter;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.localData.SharedPrefsData;
import in.calibrage.easyfarm.model.GetPlotsByFarmerCode;
import in.calibrage.easyfarm.model.LoginResponse;
import in.calibrage.easyfarm.service.APIConstantURL;
import in.calibrage.easyfarm.service.ApiService;
import in.calibrage.easyfarm.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link plot_details.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link plot_details#newInstance} factory method to
 * create an instance of this fragment.
 */
public class plot_details extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    public static String TAG = plot_details.class.getSimpleName();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;
    String Farmer_code;
    LinearLayout noRecords;
    private LoginResponse catagoriesList;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;

    private OnFragmentInteractionListener mListener;

    public plot_details() {
        // Required empty public constructor
    }


    public static plot_details newInstance(String param1, String param2) {
        plot_details fragment = new plot_details();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_plot_detailss,
                container, false);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(getContext())
                .setTheme(R.style.Custom)
                .build();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        noRecords = (LinearLayout) view.findViewById(R.id.text);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        catagoriesList = SharedPrefsData.getCatagories(getContext());
        // recyclerView.setAdapter(adapter);
        if (isOnline(getContext()))
            GetPlotDetailsByFarmerCode();
        else {
            showDialog(getActivity(), getResources().getString(R.string.Internet));

        }


        return view;
    }


    private void GetPlotDetailsByFarmerCode() {

        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(getContext(), ApiService.class);
        SharedPreferences pref = getActivity().getSharedPreferences("FARMER", MODE_PRIVATE);
        String Farmer_code =catagoriesList.getResult().getUserInfos().getCode();
        mSubscription = service.getPlotsByFarmerCode(APIConstantURL.GetPlotsByFarmerCode + Farmer_code)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetPlotsByFarmerCode>() {
                    @Override
                    public void onCompleted() {
                        mdilogue.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ((HttpException) e).code();
                            ((HttpException) e).message();
                            ((HttpException) e).response().errorBody();
                            try {
                                ((HttpException) e).response().errorBody().string();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            e.printStackTrace();
                        }
                        mdilogue.dismiss();
                        showDialog(getActivity(), getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetPlotsByFarmerCode getPlotsByFarmerCode) {






                        if (getPlotsByFarmerCode.getListResult() != null) {
                            noRecords.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            PlotDetailsAdapter adapter = new PlotDetailsAdapter(getPlotsByFarmerCode.getListResult(), getContext());
                            recyclerView.setAdapter(adapter);


                        } else {
                            noRecords.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);

                        }
                    }


                });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
