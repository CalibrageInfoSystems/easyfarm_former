package in.calibrage.easyfarm.Views;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

import in.calibrage.easyfarm.Adapter.CropReports_Adapter;
import in.calibrage.easyfarm.Common.CommonActivity;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.model.GetCropCycleDailyUploadobject;
import in.calibrage.easyfarm.model.GetCropCycleDailyUploadresponse;
import in.calibrage.easyfarm.service.ApiService;
import in.calibrage.easyfarm.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PreviousReports extends CommonActivity {
    String Farmer_code,code;
    TextView noRecords;
    private RecyclerView recyclerView;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    Toolbar toolbar;
    List<String> datelist = new ArrayList<String>();
    List<String> media_data = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_reports);
        Intent i = getIntent();
        code = i.getStringExtra("cropcode");

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
            GetCropCycleDailyUpload();
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

    private void GetCropCycleDailyUpload() {
        mdilogue.show();
        JsonObject object = getuploadsReuestobject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getdailyreports(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetCropCycleDailyUploadresponse>() {
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
                    }

                    @Override
                    public void onNext(GetCropCycleDailyUploadresponse getCropCycleDailyUploadresponse) {

                        if (getCropCycleDailyUploadresponse.getListResult().size()!=0 ) {

//                            noRecords.setVisibility(View.GONE);
//                            recyclerView.setVisibility(View.VISIBLE);
//                            Log.d("Analysis============> ",getCropCycleDailyUploadresponse.getListResult().get(0).getPostedOn());
//                            CropReportsAdapter adapter = new CropReportsAdapter(getCropCycleDailyUploadresponse.getListResult(), PreviousReports.this);
//                            recyclerView.setAdapter(adapter);
                            datelist =new ArrayList<>();

                            for(int i = 0; i <getCropCycleDailyUploadresponse.getListResult().size();i++ ){
                                if (datelist.contains(getCropCycleDailyUploadresponse.getListResult().get(i).getPostedOn())) {
                                    System.out.println("Account found");
                                } else {
                                    System.out.println("Account not found");
                                    datelist.add(getCropCycleDailyUploadresponse.getListResult().get(i).getPostedOn());
                                }
                            }

for(int k =0; k <datelist.size();k++)
{

    Log.d("Analysis============>",datelist.get(k)+"");
                                 noRecords.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);

                           CropReports_Adapter adapter = new CropReports_Adapter(datelist, getCropCycleDailyUploadresponse.getListResult(),PreviousReports.this);
                            recyclerView.setAdapter(adapter);

                            if(datelist.get(k) == getCropCycleDailyUploadresponse.getListResult().get(k).getPostedOn());{
   // media_data.set("",getCropCycleDailyUploadresponse.getListResult().get(k).getFileUrl());
    Log.d("Analysis============>",getCropCycleDailyUploadresponse.getListResult().size()+"");

                            }


}

                        } else {
                            noRecords.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);

                        }
                    }




                });

    }

    private JsonObject getuploadsReuestobject() {


        GetCropCycleDailyUploadobject requestModel = new GetCropCycleDailyUploadobject();
        requestModel.setCropCycle(code);
        requestModel.setIsActive(true);



        return new Gson().toJsonTree(requestModel).getAsJsonObject();

    }

}
