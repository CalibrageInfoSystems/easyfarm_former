package in.calibrage.easyfarm.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.badoualy.stepperindicator.StepperIndicator;
import com.bumptech.glide.Glide;
import com.facebook.Profile;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import in.calibrage.easyfarm.Common.NonSwipeableViewPager;
import in.calibrage.easyfarm.Fragments.addnew_plotdetails;
import in.calibrage.easyfarm.Fragments.addnewfarmer_basicdetails;
import in.calibrage.easyfarm.Fragments.addnewfarmer_plotdetails;
import in.calibrage.easyfarm.R;

public class SignnewUpActicity extends AppCompatActivity implements addnewfarmer_basicdetails.OnStepOneListener, addnewfarmer_plotdetails.OnStepTwoListener,addnew_plotdetails.OnStepthreeListener {

    public static String LOG_TAG = SignnewUpActicity.class.getSimpleName();
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public NonSwipeableViewPager mViewPager;
    private StepperIndicator stepperIndicator;
    Toolbar toolbar;
    GoogleSignInClient mGoogleSignInClient;
    public  String personGivenName, personFamilyName, personEmail, F_name, lastname, user_name, Email, fatherguardianname, adress1, adress2, dateof_birth, pasword, roleid;
    public static int stateid, distid, mandalid, villageid, genderid;
    public static   int Socialmediadata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signnew_up_acticity);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        stepperIndicator = findViewById(R.id.stepperIndicator);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        stepperIndicator.showLabels(false);
        stepperIndicator.setViewPager(mViewPager);
        // or keep last page as "end page"
        stepperIndicator.setViewPager(mViewPager, mViewPager.getAdapter().getCount() - 1); //
        Socialmediadata = getIntent().getIntExtra("data",1);
        Log.e("dataFrom Login",Socialmediadata+"");
        if(getIntent()!=null) {
            if (Socialmediadata == 1) {
                personGivenName = getIntent().getStringExtra("first_name");
                personFamilyName = getIntent().getStringExtra("last_name");
                user_name = getIntent().getStringExtra("user_name");
                personEmail = getIntent().getStringExtra("Email");;
                Log.e("dataFrom FB", F_name + "===" + lastname + "==========" + user_name);
            } else if (Socialmediadata == 2) {


                    personGivenName = getIntent().getStringExtra("first_name");
                    personFamilyName = getIntent().getStringExtra("last_name");
                    personEmail = getIntent().getStringExtra("Email");
                user_name = getIntent().getStringExtra("user_name");

                    Log.e("dataFrom Google", personGivenName + "===" + personFamilyName + "==========" + personEmail);

                }
            }

        settoolbar();





        /*// or manual change
        indicator.setStepCount(3);
        indicator.setCurrentStep(2);
*/

    }
    public Bundle getMyData() {
        Bundle hm = new Bundle();
        hm.putString("val1",personGivenName);
        hm.putString("val2",personFamilyName);
        hm.putString("val3",personEmail);
        hm.putString("val4",user_name);


        return hm;
    }
//    public Bundle getFBData() {
//        Bundle hm = new Bundle();
//
//        hm.putString("valF1",F_name);
//        hm.putString("valF2",lastname);
//        hm.putString("valF3",user_name);
//
//        Log.e("dataFrom FB2",F_name+"==="+lastname+"=========="+user_name);
//        return hm;
//    }
    private void settoolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle("Select Godown");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_left);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Socialmediadata==2)  {
                    mGoogleSignInClient.signOut().addOnCompleteListener(SignnewUpActicity.this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            startActivity(new Intent(SignnewUpActicity.this, LoginActivity.class));
                            finish();
                        }
                    });
                }else {


                   finish();
                }
            }
        });
    }


    @Override
    public int getLayoutResource() {
        return R.layout.activity_signnew_up_acticity;
    }


    @Override
    public void onNextPressed(Fragment fragment) {

        if (fragment instanceof addnewfarmer_basicdetails) {
            mViewPager.setCurrentItem(1, true);

        } else if (fragment instanceof addnewfarmer_plotdetails) {
            mViewPager.setCurrentItem(2, true);
        } else if (fragment instanceof addnew_plotdetails) {

            mViewPager.setCurrentItem(3, true);
          //  Toast.makeText(this, "Thanks For Registering", Toast.LENGTH_SHORT).show();
            // finish();
        }

    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return addnewfarmer_basicdetails.newInstance("", "");

                case 1:
                    return addnewfarmer_plotdetails.newInstance("", "");
                case 2:
                    return addnew_plotdetails.newInstance("", "");
            }

            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "First Level";
                case 1:
                    return "Second Level";
                case 2:
                    return "Third Level";
            }
            return null;
        }
    }


    @SuppressLint("ResourceType")

    @Override
    public void onBackPressed(Fragment fragment) {
        if (fragment instanceof addnewfarmer_plotdetails) {
            mViewPager.setCurrentItem(0, true);
        } else if (fragment instanceof addnew_plotdetails) {
            mViewPager.setCurrentItem(1, true);
        }
    }
}


