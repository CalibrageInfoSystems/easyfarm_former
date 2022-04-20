package in.calibrage.easyfarm.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.Adapter.ViewPagerAdapter;
import in.calibrage.easyfarm.Common.CommonActivity;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.localData.SharedPrefsData;

import static in.calibrage.easyfarm.Common.CommonUtil.updateResources;

public class EncyclopediaActivity  extends CommonActivity {
    //region variables
    private static final String TAG = EncyclopediaActivity.class.getSimpleName();
    private int postTypeId;
    private String titleName,telugu_title;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SpotsDialog mdilogue;
    private String[] tabnames;
  //  final int langID = SharedPrefsData.getInstance(this).getIntFromSharedPrefs("lang");
    private ViewPagerAdapter adapter;
    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_encyclopedia);
        init();
        setViews();
    }

    private void init() {


        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_left);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    private void setViews() {
        if (getIntent() != null) {
            postTypeId = getIntent().getIntExtra("postTypeId", 0);
            titleName = getIntent().getStringExtra("name");
            telugu_title = getIntent().getStringExtra("teluguname");
            tabnames = getIntent().getStringArrayExtra("tabslist");
            SharedPrefsData.getInstance(this).updateIntValue(this,"count",tabnames.length);
            SharedPrefsData.getInstance(this).updateIntValue(this,"postTypeId",postTypeId);
            adapter = new ViewPagerAdapter(getSupportFragmentManager(),tabnames);
        }



        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

            toolbar.setTitle(titleName);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
    }





}


