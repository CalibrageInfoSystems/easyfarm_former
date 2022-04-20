package in.calibrage.easyfarm.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.Adapter.GetcomplaintsAdapter;
import in.calibrage.easyfarm.Common.CommonActivity;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.localData.SharedPrefsData;
import in.calibrage.easyfarm.model.GetComplaintsByPlotCode;
import in.calibrage.easyfarm.model.GetComplaintsobject;
import in.calibrage.easyfarm.model.GetComplaintsresponse;
import in.calibrage.easyfarm.model.Getcomplaints;
import in.calibrage.easyfarm.model.LoginResponse;
import in.calibrage.easyfarm.service.APIConstantURL;
import in.calibrage.easyfarm.service.ApiService;
import in.calibrage.easyfarm.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Exit_Complaints_Activity extends CommonActivity {
    public static final String TAG = Exit_Complaints_Activity.class.getSimpleName();

    private Context ctx;
    private LinearLayoutManager lytManager;
    private RecyclerView rcv_complaints;
    private SpotsDialog mdilogue;
    private Subscription mSubscription;
    LoginResponse loginressponse;
    int User_id;
    // MyLabour_ReqAdapter adapter;
    String Farmer_code;
    TextView no_data;
Toolbar toolbar;
    String plot_id,totalarea,status,ownership;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit__complaints);
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

        rcv_complaints =findViewById(R.id.rcv_complaints);
        ctx = this;
        lytManager = new LinearLayoutManager(this);

        no_data=findViewById(R.id.no_data);
        rcv_complaints.setLayoutManager(lytManager);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
    }

    private void setviews() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            plot_id = extras.getString("code");




        }
        loginressponse = SharedPrefsData.getCatagories(Exit_Complaints_Activity.this);
        getComplaints();
    }

    private void getComplaints() {


        mdilogue.show();

        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.GetExitingcomplaints(APIConstantURL.GetComplaintsByPlotCode+plot_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetComplaintsByPlotCode>() {
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
                        //showDialog(Exit_Complaints_Activity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetComplaintsByPlotCode getComplaintsresponse) {


                        if ( getComplaintsresponse.getListResult()!= null) {
                            no_data.setVisibility(View.GONE);
                            rcv_complaints.setVisibility(View.VISIBLE);

                            GetcomplaintsAdapter adapter = new GetcomplaintsAdapter(getComplaintsresponse.getListResult(), ctx);
                            rcv_complaints.setAdapter(adapter);
                        } else {
                            no_data.setVisibility(View.VISIBLE);
                            rcv_complaints.setVisibility(View.GONE);


                        }
                    }


//                            GetLoanAdapter adapter = new GetLoanAdapter(resLoan.getListResult(), ctx);
//                            rcv_requests.setAdapter(adapter);


                });
    }

    private JsonObject getComplaintobject() {
        User_id =  loginressponse.getResult().getUserInfos().getId();
        GetComplaintsobject requestModel = new GetComplaintsobject();
        requestModel.setUserId(User_id);
        requestModel.setStatusTypeId(159);
        requestModel.setComplaintTypeId(null);
        requestModel.setToDate(null);
        requestModel.setFromDate(null);
        requestModel.setCanAssign(true);
        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }
}

