package in.calibrage.easyfarm.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import in.calibrage.easyfarm.Common.BaseFragment;
import in.calibrage.easyfarm.Common.CircleTransform;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.localData.SharedPrefsData;
import in.calibrage.easyfarm.model.LoginResponse;


public class ProfileFragment extends BaseFragment {
    public static String TAG = ProfileFragment.class.getSimpleName();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LoginResponse catagoriesList;
  TextView farmer_code,mobile,Email,farmername;
  CircleImageView profile_image;
  String role_ids;
    public ProfileFragment() {

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


        View view = inflater.inflate(R.layout.fragment_profile,
                container, false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);

        tabs.setupWithViewPager(viewPager);

        farmer_code =(TextView) view.findViewById(R.id.txt_farmercode);
        farmername =(TextView) view.findViewById(R.id.txt_name);
        mobile =(TextView) view.findViewById(R.id.txt_phone);
        Email =(TextView) view.findViewById(R.id.txt_email);
        profile_image = view.findViewById(R.id.img_profile);
        catagoriesList = SharedPrefsData.getCatagories(getContext());



        String name = catagoriesList.getResult().getUserInfos().getFirstName() + " " + catagoriesList.getResult().getUserInfos().getMiddleName() + " " + catagoriesList.getResult().getUserInfos().getLastName();
        farmername.setText("" + name.replace("null", ""));

        if (null != catagoriesList.getResult().getUserInfos().getPrimaryPhoneNumber()) {
//        if (null != catagoriesList.getResult().getFarmerDetails().get(0).getMobileNumber())
            mobile.setText("" + catagoriesList.getResult().getUserInfos().getPrimaryPhoneNumber().toString());
//
        }
        else{
            mobile.setVisibility(View.GONE);
        }
        if (null != catagoriesList.getResult().getUserInfos().getEmail() && !   TextUtils.isEmpty(catagoriesList.getResult().getUserInfos().getEmail()+"") && !catagoriesList.getResult().getUserInfos().getEmail().toString().isEmpty())
            Email.setText("" + catagoriesList.getResult().getUserInfos().getEmail());
        else {
            Email.setVisibility(View.GONE);
        }

        farmer_code.setText(catagoriesList.getResult().getUserInfos().getCode());
        if (!TextUtils.isEmpty(catagoriesList.getResult().getUserInfos().getProfilePic()+"")){
        //Picasso.with(getContext()).load(catagoriesList.getResult().getUserInfos().getProfilePic()+"").error(R.drawable.ic_user).transform(new CircleTransform()).into(profile_image);
   Glide.with(this).load(catagoriesList.getResult().getUserInfos().getProfilePic()+"").placeholder(R.drawable.ic_myprofile).into(profile_image);}
        else{
            Glide.with(this).load(R.drawable.ic_myprofile+"").into(profile_image);}


        //Glide.with(this).load(catagoriesList.getResult().getUserInfos().getProfilePic()+"").centerCrop() .into(profile_image);
//        Picasso.with(getContext())
//                .load("http://103.241.144.240:9096/3FAkshayaRepo/FileRepository/2019\\10\\10\\Banners/20191010061238662.jpg")
//                // optional
//                .resize(400,400)                        // optional
//                .into(profile_image);

//        if (!TextUtils.isEmpty(catagoriesList.getResult().getUserInfos().getProfilePic()+""))
//            Picasso.with(getContext()).load(catagoriesList.getResult().getUserInfos().getProfilePic()+"").into(profile_image);
//            Log.e("Image========url",catagoriesList.getResult().getUserInfos().getProfilePic()+"");

          //  Picasso.with(getContext()).load("http://183.82.111.111/EasyFarmFileRepository/FileRepository/2020\\06\\11\\ProfilePics\\\\20200611042011834.jpg").error(R.drawable.ic_user).transform(new CircleTransform()).into(profile_image);
        return view;

    }


    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        catagoriesList = SharedPrefsData.getCatagories(getContext());
        if(catagoriesList.getResult().getUserInfos().getRoleIds()!=null) {
            role_ids = catagoriesList.getResult().getUserInfos().getRoleIds();
            Log.e("role_ids=======",role_ids);
        }
        Adapter   adapter = new Adapter(getChildFragmentManager());


        if(role_ids == "2" || role_ids.contains("2") || role_ids.equalsIgnoreCase("2") || role_ids.equals("2")) {

            Log.e("role_ids===1","1111111111111");
            adapter.addFragment(new profile_fragment(), getResources().getString(R.string.farmer_profile));

            adapter.addFragment(new Bank_details(), getResources().getString(R.string.bank_details));
            adapter.addFragment(new plot_details(), getResources().getString(R.string.plot_details));
        }
else {
 Log.e("role_ids===2","22222222222");

            adapter.addFragment(new profile_fragment(), getResources().getString(R.string.farmer_profile));
            adapter.addFragment(new Bank_details(), getResources().getString(R.string.bank_details));

        }


        viewPager.setAdapter(adapter);


    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
