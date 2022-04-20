package in.calibrage.easyfarm.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.Adapter.EncycloAdapter;
import in.calibrage.easyfarm.Common.BaseFragment;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.Views.Complaints_Activity;
import in.calibrage.easyfarm.Views.HomeActivity;
import in.calibrage.easyfarm.Views.PolyActivity;
import in.calibrage.easyfarm.model.EncyclopediaObject;
import in.calibrage.easyfarm.model.GetActiveEncyclopediaCategoryDetails;
import in.calibrage.easyfarm.service.APIConstantURL;
import in.calibrage.easyfarm.service.ApiService;
import in.calibrage.easyfarm.service.DevisetokenResopnse;
import in.calibrage.easyfarm.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment {
    public static String TAG = "HomeFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SpotsDialog mdilogue;
    String Farmer_code;
    View v;
    private Context mContext;
    private Subscription mSubscription;
    private RecyclerView Encyclo_recycle;
    Button googlemap;
    private EncycloAdapter encycloBaseAdapter;
    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        v = inflater.inflate(R.layout.fragment_home, container, false);



        initviews();
        setviews();
        return v;
    }

    private void setviews() {

      RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
      Encyclo_recycle.setLayoutManager(mLayoutManager);
        Encyclo_recycle.setItemAnimator(new DefaultItemAnimator());
        // adding inbuilt divider line
        Encyclo_recycle.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
     //   Encyclo_recycle.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 30));
        googlemap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent =new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);*/
                Intent intent = new Intent(getActivity(), PolyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                //finish();
            }
        });

    }

    private void initviews() {
        Encyclo_recycle = (RecyclerView) v.findViewById(R.id.learning_list);

        googlemap =(Button)v.findViewById(R.id.googlemap) ;
        mContext = getContext();
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(mContext)
                .setTheme(R.style.Custom)
                .build();
        if (isOnline(getContext()))
            GetEncyclopediaDetails();
        else {
            showDialog(getActivity(), getResources().getString(R.string.Internet));

        }
    }

    private void GetEncyclopediaDetails() {
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(mContext, ApiService.class);
        mSubscription = service.getCategoryDetails(APIConstantURL.GetActiveEncyclopediaCategoryDetails)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetActiveEncyclopediaCategoryDetails>() {
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
                               public void onNext(GetActiveEncyclopediaCategoryDetails getActiveEncyclopediaCategoryDetails) {
                                   mdilogue.cancel();
                                   if (getActiveEncyclopediaCategoryDetails.getListResult() != null && getActiveEncyclopediaCategoryDetails.getListResult().size()!= 0 ) {

                                       Log.d(TAG, "---- analysis ---->GetActiveGodows-->> Responce size-->> :" + getActiveEncyclopediaCategoryDetails.getListResult().size());
                                       encycloBaseAdapter = new EncycloAdapter(mContext, getActiveEncyclopediaCategoryDetails.getListResult());
                                       Encyclo_recycle.setAdapter(encycloBaseAdapter);
                                   }

                               }
    });}

    private JsonObject EncyclopediaObject() {
        EncyclopediaObject requestModel = new EncyclopediaObject();
        requestModel.setCategoryId(null);
        requestModel.setStateIds(null);
        requestModel.setIsActive(null);

        return new Gson().toJsonTree(requestModel).getAsJsonObject();
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
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
