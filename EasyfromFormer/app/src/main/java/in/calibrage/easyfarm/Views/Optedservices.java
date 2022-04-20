package in.calibrage.easyfarm.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.Adapter.OptedserviceAdapter;
import in.calibrage.easyfarm.Adapter.VendorserviceAdapter;
import in.calibrage.easyfarm.Common.CommonActivity;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.localData.SharedPrefsData;
import in.calibrage.easyfarm.model.GetServiceOrdersByFarmerId;
import in.calibrage.easyfarm.model.GetVendorServices;
import in.calibrage.easyfarm.model.LoginResponse;
import in.calibrage.easyfarm.service.APIConstantURL;
import in.calibrage.easyfarm.service.ApiService;
import in.calibrage.easyfarm.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Optedservices extends CommonActivity {
    String Farmer_code;
    TextView no_data;
    Toolbar toolbar;
    public static final String TAG = Exit_Complaints_Activity.class.getSimpleName();
    int User_id;
    private Context ctx;
    private LinearLayoutManager lytManager;
    private RecyclerView rcv_optservice;
    private SpotsDialog mdilogue;
    private Subscription mSubscription;
    LoginResponse loginressponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optedservices);
        settoolbar();
        intviews();
        setviews();
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
    private void intviews() {
        rcv_optservice =findViewById(R.id.rcv_services);
        ctx = this;
        lytManager = new LinearLayoutManager(this);

        no_data=findViewById(R.id.no_data);
        rcv_optservice.setLayoutManager(lytManager);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();

    }
    private void setviews() {
        loginressponse = SharedPrefsData.getCatagories(Optedservices.this);
        Farmer_code =loginressponse.getResult().getUserInfos().getCode();
        if (isOnline())
            getServiceOrdersByFarmerId();
        else {
            showDialog(this, getResources().getString(R.string.Internet));

        }

    }

        private void getServiceOrdersByFarmerId() {
            mdilogue.show();
            ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
            mSubscription = service.GetServiceOrdersByFarmerId(APIConstantURL.getServiceOrdersByFarmerId+Farmer_code)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetServiceOrdersByFarmerId>() {
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
                            showDialog(Optedservices.this, getString(R.string.server_error));
                        }

                        @Override
                        public void onNext(GetServiceOrdersByFarmerId getServiceOrdersByFarmerId) {


                            if (getServiceOrdersByFarmerId.getListResult()!= null ) {

                                no_data.setVisibility(View.GONE);
                                rcv_optservice.setVisibility(View.VISIBLE);
                                Log.d("Analysis============> ",getServiceOrdersByFarmerId.getListResult().get(0).getServiceName());
                                OptedserviceAdapter adapter = new OptedserviceAdapter(getServiceOrdersByFarmerId.getListResult(),Optedservices.this);
                                rcv_optservice.setAdapter(adapter);



                            } else {
                                no_data.setVisibility(View.VISIBLE);
                                rcv_optservice.setVisibility(View.GONE);

                            }
                        }






                    });

        }

    }

