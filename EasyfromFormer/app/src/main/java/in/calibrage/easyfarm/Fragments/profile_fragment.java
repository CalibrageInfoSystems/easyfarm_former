package in.calibrage.easyfarm.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.calibrage.easyfarm.Common.BaseFragment;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.localData.SharedPrefsData;
import in.calibrage.easyfarm.model.LoginResponse;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.WINDOW_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link profile_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link profile_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profile_fragment extends BaseFragment {
    public static final String TAG = profile_fragment.class.getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String Farmer_code;
    ImageView img_profile;

    private LoginResponse catagoriesList;
    private OnFragmentInteractionListener mListener;

    private TextView farmer_name, father_name, gender, education, village, mandal, dist, state, category, mobile, alt_mobile, email,farmer_code,dateofbirth,country,address,address2;
    private LinearLayout lyt_fathername, lyt_education, lin_alter, lyt_village, lyt_mandal, lyt_dist, lyt_state, lyt_category, lyt_gender,lyt_address2,
            lyt_alt_mobile, lyt_dob,lyt_country,lin_edu;
    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
    String datetimevaluereq;
    public profile_fragment() {
        // Required empty public constructor
    }


    public static profile_fragment newInstance(String param1, String param2) {
        profile_fragment fragment = new profile_fragment();
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


        View view = inflater.inflate(R.layout.fragment_profile_fragment,
                container, false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        SharedPreferences pref = getActivity().getSharedPreferences("FARMER", MODE_PRIVATE);
        Farmer_code = pref.getString("farmerid", "");
        init(view);

        setviews();

        return view;
    }

    private void setviews() {
        catagoriesList = SharedPrefsData.getCatagories(getContext());
       // Log.e("Clusterid===",catagoriesList.getResult().getFarmerDetails().get(0).getClusterId()+"===="+ catagoriesList.getResult().getFarmerDetails().get(0).getClusterName());
       // farmer_code.setText("" +Farmer_code);
        String name = catagoriesList.getResult().getUserInfos().getFirstName() + " " + catagoriesList.getResult().getUserInfos().getMiddleName() + " " + catagoriesList.getResult().getUserInfos().getLastName();
        farmer_name.setText("" + name.replace("null", ""));

        if (null != catagoriesList.getResult().getUserInfos().getVillageName())
            village.setText("" + catagoriesList.getResult().getUserInfos().getVillageName());
        else
            lyt_village.setVisibility(View.GONE); // village.setText(": N/A");


        if (null != catagoriesList.getResult().getUserInfos().getGender())
            gender.setText("" + catagoriesList.getResult().getUserInfos().getGender());
        else
            lyt_gender.setVisibility(View.GONE);// res_address.setText(": N/A");

        if (null != catagoriesList.getResult().getUserInfos().getMandalName())
            mandal.setText("" + catagoriesList.getResult().getUserInfos().getMandalName());
        else
            lyt_mandal.setVisibility(View.GONE);//mandal.setText(": N/A");

        if (null != catagoriesList.getResult().getUserInfos().getEducationType())
            education.setText("" + catagoriesList.getResult().getUserInfos().getEducationType());
        else {
            lyt_education.setVisibility(View.GONE);
            lin_edu.setVisibility(View.GONE);
        }// land_mark.setText(": N/A");
        if (null != catagoriesList.getResult().getUserInfos().getCountryName())
            country.setText("" + catagoriesList.getResult().getUserInfos().getCountryName());
        else
            lyt_country.setVisibility(View.GONE); // land_mark.setText(": N/A");


        if (null != catagoriesList.getResult().getUserInfos().getDistrictName())
            dist.setText("" + catagoriesList.getResult().getUserInfos().getDistrictName());
        else
            lyt_dist.setVisibility(View.GONE);// dist.setText(": N/A");

        if (null != catagoriesList.getResult().getUserInfos().getStateName())
            state.setText("" + catagoriesList.getResult().getUserInfos().getStateName());
        else
            lyt_state.setVisibility(View.GONE);// state.setText(": N/A");

        if (null != catagoriesList.getResult().getUserInfos().getFatherNameGuardianName())
            father_name.setText("" + catagoriesList.getResult().getUserInfos().getFatherNameGuardianName());
        else
            lyt_fathername.setVisibility(View.GONE); // father_name.setText(": N/A");
        if (null != catagoriesList.getResult().getUserInfos().getSecondaryPhoneNumber())
//        if (null != catagoriesList.getResult().getFarmerDetails().get(0).getContactNumber())
            alt_mobile.setText("" + catagoriesList.getResult().getUserInfos().getSecondaryPhoneNumber());
        else {
            lyt_alt_mobile.setVisibility(View.GONE);
            lin_alter.setVisibility(View.GONE);
        }// alt_mobile.setText(": N/A");

        if (null != catagoriesList.getResult().getUserInfos().getDOB() && !   TextUtils.isEmpty(catagoriesList.getResult().getUserInfos().getDOB()+"") && !catagoriesList.getResult().getUserInfos().getDOB().toString().isEmpty()) {
            try {
                Date oneWayTripDate = input.parse(catagoriesList.getResult().getUserInfos().getDOB());

                datetimevaluereq = output.format(oneWayTripDate);


                Log.e("===============", "======currentData======" + output.format(oneWayTripDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dateofbirth.setText("" + datetimevaluereq);
        }
        else
            lyt_dob.setVisibility(View.GONE);//;  email.setText(": N/A");

        if (null != catagoriesList.getResult().getUserInfos().getCategorytype())
            category.setText("" + catagoriesList.getResult().getUserInfos().getCategorytype());
        else
            lyt_category.setVisibility(View.GONE); // land_mark.setText(": N/A");


        address.setText(catagoriesList.getResult().getUserInfos().getAddress1());
        if (null != catagoriesList.getResult().getUserInfos().getAddress2()){
            address.setText(catagoriesList.getResult().getUserInfos().getAddress2()+"," +catagoriesList.getResult().getUserInfos().getAddress1()+"," + catagoriesList.getResult().getUserInfos().getVillageName()+"," +
                    catagoriesList.getResult().getUserInfos().getMandalName() +"," +catagoriesList.getResult().getUserInfos().getDistrictName()+","+ catagoriesList.getResult().getUserInfos().getStateName()
                    +"," +catagoriesList.getResult().getUserInfos().getCountryName());

        }

        else{
            address.setText(catagoriesList.getResult().getUserInfos().getAddress1()+"," + catagoriesList.getResult().getUserInfos().getVillageName()+"," +
                    catagoriesList.getResult().getUserInfos().getMandalName() +"," +catagoriesList.getResult().getUserInfos().getDistrictName()+","+ catagoriesList.getResult().getUserInfos().getStateName()
                    +"," +catagoriesList.getResult().getUserInfos().getCountryName());
        }
            lyt_address2.setVisibility(View.GONE); // land_mark.setText(": N/A");


    }

    private void init(View view) {
        img_profile = (ImageView) view.findViewById(R.id.img_profile);
        farmer_name = (TextView) view.findViewById(R.id.farmerName);
        //farmer_code =(TextView) view.findViewById(R.id.farmer_Code);
        father_name = (TextView) view.findViewById(R.id.fatherName);
        education = (TextView) view.findViewById(R.id.education);
        gender = (TextView) view.findViewById(R.id.gender);
        village = (TextView) view.findViewById(R.id.village);
        mandal = (TextView) view.findViewById(R.id.mandal);
        dist = (TextView) view.findViewById(R.id.district);
        state = (TextView) view.findViewById(R.id.state);
        category = (TextView) view.findViewById(R.id.category);
        dateofbirth = (TextView) view.findViewById(R.id.dateofbirth);
        alt_mobile = (TextView) view.findViewById(R.id.alternatemobilenumber);
        email = (TextView) view.findViewById(R.id.emailid);
        country = (TextView) view.findViewById(R.id.country);
        address =(TextView) view.findViewById(R.id.address);
        address2 =(TextView) view.findViewById(R.id.address2);
        lyt_fathername = view.findViewById(R.id.lyt_fathername);
       lyt_education = view.findViewById(R.id.lyt_education);
        lin_alter = view.findViewById(R.id.lin_alter);
        lyt_village = view.findViewById(R.id.lyt_village);
        lyt_mandal = view.findViewById(R.id.lyt_mandal);
        lyt_dist = view.findViewById(R.id.lyt_dist);
        lyt_state = view.findViewById(R.id.lyt_state);
        lyt_category = view.findViewById(R.id.lyt_category);
        lyt_gender = view.findViewById(R.id.lyt_gender);
        lyt_alt_mobile = view.findViewById(R.id.lyt_alt_mobile);
        lyt_dob = view.findViewById(R.id.lyt_date_birth);
        lyt_country= view.findViewById(R.id.lyt_country);
        lin_edu =view.findViewById(R.id.lin_edu);
        lyt_address2=view.findViewById(R.id.lytaddress2);

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
