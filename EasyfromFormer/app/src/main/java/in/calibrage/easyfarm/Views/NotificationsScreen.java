package in.calibrage.easyfarm.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.Adapter.NotificationDisplayAdapter;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.localData.SharedPrefsData;
import in.calibrage.easyfarm.model.LoginResponse;
import in.calibrage.easyfarm.model.getNotificationResponse;
import in.calibrage.easyfarm.model.notficationRequest;
import in.calibrage.easyfarm.service.ApiService;
import in.calibrage.easyfarm.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NotificationsScreen extends AppCompatActivity {
    public static final String LOG_TAG = NotificationsScreen.class.getName();
    private ImageView refreshBtn;
    private RecyclerView notiRecyclerView;
    private NotificationDisplayAdapter notificationDisplayAdapter;
    private LinearLayoutManager layoutManager;
    Toolbar toolbar;
    Context context;
    int User_id;
    private SpotsDialog mdilogue;
    private List<getNotificationResponse.ListResult> notificationlist = new ArrayList<>();
TextView noData;
    LoginResponse loginressponse;
   private Subscription mSubscription;
    public static final String DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss";

   // private List<Notifications> notificationlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_screen);
        settoolbar();
        intview();
        setViews();
    }

    private void intview() {
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
        notiRecyclerView = (RecyclerView) findViewById(R.id.notificationRecyclerView);
        noData =(TextView)findViewById(R.id.noData);
    }

    private void setViews() {
        mdilogue.show();
        JsonObject object = notificationsObject();

        Log.d("NotificationObject", object.toString() + "");

        ApiService service = ServiceFactory.createRetrofitService(getApplicationContext(), ApiService.class);
        mSubscription = service.getNoticationRepo(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<getNotificationResponse>() {
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
                    public void onNext(final getNotificationResponse notificationsresponse) {
                        android.util.Log.e("NotificationResult",notificationsresponse.getListResult().size()+"");
                        mdilogue.dismiss();
                        if (notificationsresponse.getListResult().size()!=0 && notificationsresponse.getListResult()!=null) {
                            noData.setVisibility(View.GONE);
                            notiRecyclerView.setVisibility(View.VISIBLE);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    Log.d("NotificationAPI", "Success");

                                    notiRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                                    notificationlist = notificationsresponse.getListResult();


                                    for (int i = 0; i < notificationlist.size(); i++) {

                                        Log.d("notification===", notificationlist.get(i).getIsRead() + "");

                                    }

                                    notificationDisplayAdapter = new NotificationDisplayAdapter(getApplicationContext(), notificationlist);
                                    notiRecyclerView.setAdapter(notificationDisplayAdapter);

                                }
                            }, 0);

                        } else {

                            noData.setVisibility(View.VISIBLE);
                            notiRecyclerView.setVisibility(View.GONE);
                        }

                    }
                });


    }

    private void getNotificationsAPI()
    {



    }
    private JsonObject notificationsObject() {

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.SECOND, -5);

        loginressponse = SharedPrefsData.getCatagories(getApplicationContext());
        User_id =  loginressponse.getResult().getUserInfos().getId();

        String timeStamp1 = new SimpleDateFormat(DATE_FORMAT1).format(calendar.getTimeInMillis());
        String timeStamp2 = new SimpleDateFormat(DATE_FORMAT1).format(Calendar.getInstance().getTimeInMillis());



        notficationRequest requestModel = new notficationRequest();
        requestModel.setUserId(User_id);
        requestModel.setSearchValue("");
        requestModel.setFromDate("2020-03-20T12:41:06.9358805+05:30");
        requestModel.setToDate(timeStamp2);

        return new Gson().toJsonTree(requestModel).getAsJsonObject();
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
}
