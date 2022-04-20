package in.calibrage.easyfarm.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.IOException;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.Common.BaseFragment;
import in.calibrage.easyfarm.Adapter.IDDetailsAdapter;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.localData.SharedPrefsData;
import in.calibrage.easyfarm.model.GetUserInfoByUserCode;
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
 * {@link Bank_details.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Bank_details#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Bank_details extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    LinearLayout noRecords,no_text;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    private RecyclerView recyclerView;

    TextView Accountholder_name,Account_number,bankname,branch,ifsc_code;
    View view;
    private OnFragmentInteractionListener mListener;
    ImageView passbook_image;
    private LoginResponse catagoriesList;
    LinearLayout linear_bankdetails;
    public Context mContext;

    LayoutInflater mInflater;
    public Bank_details() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Bank_details.
     */
    // TODO: Rename and change types and number of parameters
    public static Bank_details newInstance(String param1, String param2) {
        Bank_details fragment = new Bank_details();
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
        view = inflater.inflate(R.layout.fragment_bank_details,
                container, false);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(getContext())
                .setTheme(R.style.Custom)
                .build();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        Accountholder_name =(TextView)  view.findViewById(R.id.account_name);
        Account_number =(TextView)  view.findViewById(R.id.account_number);
        bankname =(TextView)  view.findViewById(R.id.bankname);
        branch =(TextView)  view.findViewById(R.id.branch);
        ifsc_code =(TextView)  view.findViewById(R.id.ifsc_Code);
        passbook_image =(ImageView) view.findViewById(R.id.passbook_image);
        linear_bankdetails=(LinearLayout)  view.findViewById(R.id.linear_bankdetails);
        noRecords = (LinearLayout) view.findViewById(R.id.text);
        no_text =(LinearLayout) view.findViewById(R.id.no_text);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        catagoriesList = SharedPrefsData.getCatagories(getContext());
        // recyclerView.setAdapter(adapter);
        if (isOnline(getContext()))
            GetUserInfoByUserCode();
        else {
            showDialog(getActivity(), getResources().getString(R.string.Internet));

        }


        return view;
    }

    private void GetUserInfoByUserCode() {
        String Farmer_code =catagoriesList.getResult().getUserInfos().getCode();
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(getContext(), ApiService.class);
        SharedPreferences pref = getActivity().getSharedPreferences("FARMER", MODE_PRIVATE);
       // String Farmer_code = pref.getString("farmerid", "");
        mSubscription = service.UserInfoByUserCode(APIConstantURL.GetUserInfoByUserCode+Farmer_code)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetUserInfoByUserCode>() {
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
                     //   showDialog(getActivity(), getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(final GetUserInfoByUserCode getUserInfoByUserCode) {
                      //  Account_number,bankname,branch,ifsc_code;

                        if (getUserInfoByUserCode.getResult().getBankDetails() != null) {
                            linear_bankdetails.setVisibility(View.VISIBLE);
                            no_text.setVisibility(View.GONE);
                            Accountholder_name.setText(getUserInfoByUserCode.getResult().getBankDetails().getAccountHolderName());
                            Account_number.setText(getUserInfoByUserCode.getResult().getBankDetails().getAccountNumber());
                            bankname.setText(getUserInfoByUserCode.getResult().getBankDetails().getBankName());
                            branch.setText(getUserInfoByUserCode.getResult().getBankDetails().getBranchName());
                            ifsc_code.setText(getUserInfoByUserCode.getResult().getBankDetails().getIFSCCode());
                            Log.e("passboook========",getUserInfoByUserCode.getResult().getBankDetails().getBankPassbookImage());
                            //Picasso.with(getContext()).load(getUserInfoByUserCode.getResult().getBankDetails().getBankPassbookImage()+"").into(passbook_image);
                            Glide.with(getActivity()).load(getUserInfoByUserCode.getResult().getBankDetails().getBankAccountImage()+"").error(R.drawable.cashbook).into(passbook_image);
                        }
                        else{
                            linear_bankdetails.setVisibility(View.GONE);
                            no_text.setVisibility(View.VISIBLE);
                        }
                        passbook_image.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                Log.e("===========","================clicked");
                              //  Context context = mContext.getApplicationContext();
                                mInflater = LayoutInflater.from(getActivity());
                                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                                View mView = mInflater.inflate(R.layout.dialog_custom_layout, null);
                                TextView cancel =mView.findViewById(R.id.cancel);
                                TextView text = mView.findViewById(R.id.text);
                                text.setText("Passbook Image");
                                //  Picasso.with(mContext).load(getCollectionInfoById.getResult().getReceiptImg()).error(R.drawable.ic_user).into(photoView);
                                PhotoView photoView = mView.findViewById(R.id.imageView);
                                Glide.with(getActivity()).load(getUserInfoByUserCode.getResult().getBankDetails().getBankAccountImage()+""+"").into(photoView);
                                // Picasso.with(mContext).load(img.get(finalJ)).error(R.drawable.ic_user).into(photoView);
                                //photoView.setImageResource(Integer.parseInt(getCollectionInfoById.getResult().getReceiptImg()));
                                mBuilder.setView(mView);

                                final AlertDialog mDialog = mBuilder.create();
                                cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mDialog.dismiss();
                                    }
                                });
                                mDialog.show();
                            }
                        });

                        if (getUserInfoByUserCode.getResult().getIdentityProofDetails() != null && getUserInfoByUserCode.getResult().getIdentityProofDetails().size()!=0) {
                            noRecords.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            IDDetailsAdapter adapter = new IDDetailsAdapter(getUserInfoByUserCode.getResult().getIdentityProofDetails(), getContext());
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
