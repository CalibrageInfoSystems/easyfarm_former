package in.calibrage.easyfarm.Views;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.Adapter.SoilDetailsAdapter;
import in.calibrage.easyfarm.Common.CommonActivity;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.model.getSoiltestreports;
import in.calibrage.easyfarm.service.APIConstantURL;
import in.calibrage.easyfarm.service.ApiService;
import in.calibrage.easyfarm.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SoilTestReports extends CommonActivity {



    String Farmer_code,code;
    TextView noRecords;
    private RecyclerView recyclerView;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soil_test_reports);

        Intent i = getIntent();
        code = i.getStringExtra("code");
        Farmer_code = i.getStringExtra("Farmercode");
        Log.d("Code", code + "");
        settoolbar();


        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        noRecords = (TextView) findViewById(R.id.no_data);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // catagoriesList = SharedPrefsData.getCatagories(getContext());
        // recyclerView.setAdapter(adapter);

        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();


        if (isOnline())
            GetsoiltestDetails();
        else {
            showDialog(this, getResources().getString(R.string.Internet));

        }
    }

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
                finish();
            }
        });
    }


    private void GetsoiltestDetails() {
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        SharedPreferences pref = this.getSharedPreferences("FARMER", MODE_PRIVATE);
       // String Farmer_code =catagoriesList.getResult().getUserInfos().getCode();
        mSubscription = service.getSoildetailsbyplotcode(APIConstantURL.GetsoildetailsplotCode+code )
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
                        showDialog(SoilTestReports.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(getSoiltestreports getSoiltestreports) {
                        if (getSoiltestreports.getResult().getSoilTestDetails().size()!= 0 ) {
                            noRecords.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            SoilDetailsAdapter adapter = new SoilDetailsAdapter(getSoiltestreports.getResult().getSoilTestDetails(), SoilTestReports.this);
                            recyclerView.setAdapter(adapter);


                        } else {
                            noRecords.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);

                        }
                    }




                });


    }
}
