package in.calibrage.easyfarm.FCM;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.Views.Exit_Complaints_Activity;
import in.calibrage.easyfarm.Views.HomeActivity;
import in.calibrage.easyfarm.Views.LoginActivity;
import in.calibrage.easyfarm.Views.SplashActivity;
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

public class FirebaseMessageReceiver extends FirebaseMessagingService {

//    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
//    public static String PREVIOUS_NOTIFICATION_DATE = "previous_notification_date";
//    int User_id;
//    LoginResponse loginressponse;
//
//
//
//    private SpotsDialog mdilogue;
//
//    public String notificationHeadder, notificationDesc, lastnotificationTime;
//
//    private Subscription mSubscription;
//    Context context;
//    public static final String DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss";
//
//    private final static String default_notification_channel_id = "default" ;
//    @Override
//    public void onNewToken (String s) {
//        super .onNewToken(s) ;
//    }
//    @Override
//    public void onMessageReceived (RemoteMessage remoteMessage) {
//        super .onMessageReceived(remoteMessage) ;
//
//        context = getApplicationContext();
////        Intent notifyIntent = new Intent(this, ResultActivity.class);
////// Set the Activity to start in a new, empty task
////        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
////                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
////// Create the PendingIntent
////        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
////                this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
////        );
//
//        JsonObject object = notificationsObject();
//
//        Log.d("NotificationObject", object.toString() + "");
//
//        ApiService service = ServiceFactory.createRetrofitService(context, ApiService.class);
//        mSubscription = service.getNoticationRepo(object)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<getNotificationResponse>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        if (e instanceof HttpException) {
//                            ((HttpException) e).code();
//                            ((HttpException) e).message();
//                            ((HttpException) e).response().errorBody();
//                            // CommonUtils.appendLog(LOG_TAG, "externalRegistrationAPI", e.getMessage());
//
//                            try {
//                                ((HttpException) e).response().errorBody().string();
//                            } catch (IOException e1) {
//                                e1.printStackTrace();
//                                //CommonUtils.appendLog(LOG_TAG, "externalRegistrationAPI", e1.getMessage());
//
//                            }
//                            e.printStackTrace();
//                            //CommonUtils.appendLog(LOG_TAG, "externalRegistrationAPI", e.getMessage());
//
//                        }
//                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
//                    }
//
//
//
//                    @Override
//                    public void onNext(final getNotificationResponse notificationsresponse) {
//                        android.util.Log.e("NotificationResult",notificationsresponse.getListResult().size()+"");
//
//                        if (notificationsresponse.getIsSuccess()) {
//                            new Handler().postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//
//                                    Log.d("NotificationAPI", "Successfull");
//
//                                    int lastindex = notificationsresponse.getListResult().size();
//
//
//                                    notificationHeadder = notificationsresponse.getListResult().get(lastindex -1).getHeader();
//                                    notificationDesc = notificationsresponse.getListResult().get(lastindex -1).getDesc();
//                                    lastnotificationTime = notificationsresponse.getListResult().get(lastindex -1).getCreatedDate();
//
//                                    Log.d("SNotificationHeader", notificationHeadder);
//                                    Log.d("SNotificationDesc", notificationDesc);
//                                    Log.d("lastnotificationTime", lastnotificationTime);
//
//
//                                    Intent notificationIntent = new Intent(getApplicationContext() , HomeActivity.class ) ;
//                                    notificationIntent.putExtra( "NotificationMessage" , "I am from Notification" ) ;
//                                    notificationIntent.addCategory(Intent. CATEGORY_LAUNCHER ) ;
//                                    notificationIntent.setAction(Intent. ACTION_MAIN ) ;
//                                    notificationIntent.setFlags(Intent. FLAG_ACTIVITY_CLEAR_TOP | Intent. FLAG_ACTIVITY_SINGLE_TOP ) ;
//                                    PendingIntent resultIntent = PendingIntent. getActivity (getApplicationContext() , 0 , notificationIntent , 0 ) ;
//                                    getNotificationsAPI();
//                                    Log.d("SRNotificationHeader", notificationHeadder + "");
//                                    Log.d("SRNotificationDesc", notificationDesc + "");
//                                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext() ,
//                                            default_notification_channel_id )
//                                            .setSmallIcon(R.drawable. ic_launcher_foreground )
//                                            .setContentTitle(notificationHeadder +"")
//                                            .setContentText(notificationDesc + "")
//                                            .setContentIntent(resultIntent) ;
////                                    Log.d("FirebaseMessaging", notifications.get(notifications.size() -1).getHeader()+"");
////                                    Log.d("FirebaseMessaging", notifications.get(notifications.size() -1).getDesc()+"");
//
//
//                                    NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context. NOTIFICATION_SERVICE ) ;
//                                    if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
//                                        int importance = NotificationManager. IMPORTANCE_HIGH ;
//                                        NotificationChannel notificationChannel = new
//                                                NotificationChannel( NOTIFICATION_CHANNEL_ID , "NOTIFICATION_CHANNEL_NAME" , importance) ;
//                                        mBuilder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
//                                        assert mNotificationManager != null;
//                                        mNotificationManager.createNotificationChannel(notificationChannel) ;
//                                    }
//                                    assert mNotificationManager != null;
//                                    mNotificationManager.notify(( int ) System. currentTimeMillis () ,
//                                            mBuilder.build()) ;
//
//
////                                    Log.d("NotificationHeader", notificationsresponse.getListResult().get(-1).getHeader() + "");
////                                    Log.d("NotificationDesc", notificationsresponse.getListResult().get(-1).getDesc() + "");
//
//
//                                }
//                            }, 0);
//
//                        } else {
//
//
//                        }
//
//                        //  Toast.makeText(getContext(),registrationResponse.getEndUserMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
////        dataAccessHandler = new DataAccessHandler(context);
////        notifications = (List<Notifications>) dataAccessHandler.getNotification(Queries.getInstance().Notification(), 1);
//
//    }
//
//    private void getNotificationsAPI() {
//
//
//
//    }
//
//    private JsonObject notificationsObject() {
//
//        Calendar calendar = Calendar.getInstance();
//
//        calendar.add(Calendar.SECOND, -5);
//
//        loginressponse = SharedPrefsData.getCatagories(context);
//        User_id =  loginressponse.getResult().getUserInfos().getId();
//
//        String timeStamp1 = new SimpleDateFormat(DATE_FORMAT1).format(calendar.getTimeInMillis());
//        String timeStamp2 = new SimpleDateFormat(DATE_FORMAT1).format(Calendar.getInstance().getTimeInMillis());
//
//
//
//        notficationRequest requestModel = new notficationRequest();
//        requestModel.setUserId(User_id);
//        requestModel.setSearchValue("");
//        requestModel.setFromDate(timeStamp1);
//        requestModel.setToDate(timeStamp2);
//
//        return new Gson().toJsonTree(requestModel).getAsJsonObject();
//    }
//
//    public static void updateNotificationDate(Context context, String date) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences("appprefs", MODE_PRIVATE);
//        sharedPreferences.edit().putString(PREVIOUS_NOTIFICATION_DATE, date).apply();
//    }




































    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //handle when receive notification via data event
        if(remoteMessage.getData().size()>0){
            showNotification(remoteMessage.getData().get("title"),remoteMessage.getData().get("message"));
        }

        //handle when receive notification
        if(remoteMessage.getNotification()!=null){
            showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        }

    }

    private RemoteViews getCustomDesign(String title, String message){
        RemoteViews remoteViews=new RemoteViews(getApplicationContext().getPackageName(), R.layout.notification);
        remoteViews.setTextViewText(R.id.title,title);
        remoteViews.setTextViewText(R.id.message,message);
        remoteViews.setImageViewResource(R.id.icon,R.drawable.easyfarmlogo);
        return remoteViews;
    }

    public void showNotification(String title,String message){
        Intent intent=new Intent(this, SplashActivity.class);
        String channel_id="Easy Farm";
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext(),channel_id)
                .setSmallIcon(R.drawable.easyfarmlogo)
                .setSound(uri)
                .setContentTitle("Push Notification")
                .setContentText("New Notification Received")
                .setAutoCancel(true)
                .setVibrate(new long[]{1000,1000,1000,1000,1000})
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
            builder=builder.setContent(getCustomDesign(title,message));
        }
        else{
            builder=builder.setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.drawable.easyfarmlogo);
        }

        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel=new NotificationChannel(channel_id,"web_app",NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setSound(uri,null);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        notificationManager.notify(0,builder.build());
    }

    //app part ready now let see how to send differnet users
    //like send to specific device
    //like specifi topic
}