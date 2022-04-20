package in.calibrage.easyfarm.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.Adapter.PlotAdapter;
import in.calibrage.easyfarm.Adapter.PlotDetailsAdapter;
import in.calibrage.easyfarm.Common.BaseFragment;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.Views.Complaints_Activity;
import in.calibrage.easyfarm.Views.Exit_Complaints_Activity;
import in.calibrage.easyfarm.localData.SharedPrefsData;
import in.calibrage.easyfarm.model.GetPlotsByFarmerCode;
import in.calibrage.easyfarm.model.LoginResponse;
import in.calibrage.easyfarm.model.getSoiltestreports;
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
 * {@link ActiveplotsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ActiveplotsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActiveplotsFragment extends BaseFragment implements  AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    public static String TAG = ActiveplotsFragment.class.getSimpleName();
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
    public TextView plotcode,status,totalarea,adopted_area,gps_area,plot_ownership,country,state,district,mandal,village,address,
            address2,passbook,servey_number,owner_contact_number,ownername;
    LinearLayout plot_data,lyt_status,lyt_totalarea,lyt_Adapted,lyt_gps,lyt_plotownership,new_complaint,exit_complaint,owner_bottom_layout,soildetails;
    DecimalFormat df = new DecimalFormat("####0.00");

    String  select_plot;
    List<String> get_plot = new ArrayList<String>();
    Spinner spin_plot;
    private OnFragmentInteractionListener mListener;

    public ActiveplotsFragment() {
        // Required empty public constructor
    }


    public static ActiveplotsFragment newInstance(String param1, String param2) {
        ActiveplotsFragment fragment = new ActiveplotsFragment();
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
        intviews();
        setviews();


        return view;
    }

    private void setviews() {
        catagoriesList = SharedPrefsData.getCatagories(getContext());


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        catagoriesList = SharedPrefsData.getCatagories(getContext());
        // recyclerView.setAdapter(adapter);
        if (isOnline(getContext()))
            GetPlotDetailsByFarmerCode();
        else {
            showDialog(getActivity(), getResources().getString(R.string.Internet));

        }
//        spin_plot.setOnItemSelectedListener(this);
//        // recyclerView.setAdapter(adapter);
//        if (isOnline(getContext()))
//            GetPlotDetailsByFarmerCode();
//        else {
//            showDialog(getActivity(), getResources().getString(R.string.Internet));
//
//        }
//
//        new_complaint.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), Complaints_Activity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("code",select_plot);
//                intent.putExtra("totalarea",totalarea.getText());
//                intent.putExtra("status",status.getText().toString());
//                intent.putExtra("ownership",plot_ownership.getText());
//
//                startActivity(intent);
//
//            }
//
//        });
//        exit_complaint.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), Exit_Complaints_Activity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("code",select_plot);
//                intent.putExtra("totalarea",totalarea.getText());
//                intent.putExtra("status",status.getText().toString());
//                intent.putExtra("ownership",plot_ownership.getText().toString());
//
//                startActivity(intent);
//
//            }
//
//        });

    }

    private void intviews() {
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(getContext())
                .setTheme(R.style.Custom)
                .build();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        noRecords = (LinearLayout) view.findViewById(R.id.text);
//        spin_plot = (Spinner) view.findViewById(R.id.spin_plot);
//        plot_data =(LinearLayout) view.findViewById(R.id.plot_data);
//        status = (TextView) view.findViewById(R.id.status);
//        totalarea = (TextView) view.findViewById(R.id.totalarea);
//        adopted_area = (TextView) view.findViewById(R.id.adopted_area);
//        gps_area = (TextView) view.findViewById(R.id.gps_area);
//        plot_ownership = (TextView) view.findViewById(R.id.plot_ownership);
//        new_complaint = view.findViewById(R.id.new_complaint);
//        exit_complaint = view.findViewById(R.id.exit_complaint);
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
                            PlotAdapter adapter = new PlotAdapter(getPlotsByFarmerCode.getListResult(), getContext());
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



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        select_plot = spin_plot.getItemAtPosition(spin_plot.getSelectedItemPosition()).toString();
        Log.e("select_plot===2",select_plot+"");
        if(i!=0) {
            plot_data.setVisibility(View.VISIBLE);
            GetPlotsByPlotCode();

        }else{
            plot_data.setVisibility(View.GONE);
        }

    }


    private void GetPlotsByPlotCode() {
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(getActivity(), ApiService.class);

        mSubscription = service.getSoildetailsbyplotcode(APIConstantURL.GetsoildetailsplotCode+select_plot )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<getSoiltestreports>() {
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
                    public void onNext(getSoiltestreports getSoiltestreports) {
                        if (getSoiltestreports.getResult().getPlotDetails()!= null ) {

                            status.setText(getSoiltestreports.getResult().getPlotDetails().getPlotStatus());
                            totalarea.setText(df.format(getSoiltestreports.getResult().getPlotDetails().getTotalPlotArea()));
                            adopted_area.setText(df.format(getSoiltestreports.getResult().getPlotDetails().getAdaptedArea()));
                            gps_area.setText(df.format(getSoiltestreports.getResult().getPlotDetails().getAdaptedArea()));
                            plot_ownership.setText(getSoiltestreports.getResult().getPlotDetails().getPlotOwnerShip()+"");
                        } else {


                        }
                    }




                });


    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
