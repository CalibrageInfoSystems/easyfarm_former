package in.calibrage.easyfarm.Views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.commons.io.FileUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.Common.CommonActivity;
import in.calibrage.easyfarm.Common.CommonUtil;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.localData.SharedPrefsData;
import in.calibrage.easyfarm.model.Complaintobject;
import in.calibrage.easyfarm.model.Getcomplaints;
import in.calibrage.easyfarm.model.LoginResponse;
import in.calibrage.easyfarm.model.MSGmodel;
import in.calibrage.easyfarm.model.ResponseComplaint;
import in.calibrage.easyfarm.service.APIConstantURL;
import in.calibrage.easyfarm.service.ApiService;
import in.calibrage.easyfarm.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class Complaints_Activity extends CommonActivity implements View.OnClickListener {
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    ImageView backImg, home_btn;
    Button btn_addIMG;
    private Button btn;
    private ImageView imageview, imageview2, imageview3;
    private ImageView img_delete1, img_delete2, img_delete3;
    private RelativeLayout lyt_img, lyt_img2, lyt_img3;
    private static final String IMAGE_DIRECTORY = "/demonuts";
    private int GALLERY = 1, CAMERA = 2;
    public static final int RequestPermissionCode = 1;
    MediaPlayer mediaPlayer;
    String currentDate,selected_issue;
    EditText comments;
    boolean flag = false;
    int pos;

    private Chronometer chronometer;
    private ImageView imageViewRecord, imageViewPlay, imageViewStop;
    private SeekBar seekBar;
    private LinearLayout linearLayoutRecorder, linearLayoutPlay;
    private MediaRecorder mRecorder;
    private MediaPlayer mPlayer;
    private String fileName = null;
    private int lastProgress = 0;
    private Handler mHandler = new Handler();
    private int RECORD_AUDIO_REQUEST_CODE = 123;
    private boolean isPlaying = false;
    Random random;
    Button submit;
    Spinner getcomplaints;
    List<String> Issue_type = new ArrayList<String>();
    private static final String TAG = Complaints_Activity.class.getSimpleName();
    List<Integer> Issue_Id = new ArrayList<Integer>();
    private List<Bitmap> images = new ArrayList<>();
    LoginResponse loginressponse;
    int User_id;
    String plot_id,totalarea,status,ownership;
    TextView plotcode, plotstatus,plotarea,plotowner;
    private String mCurrentPhotoPath;
    private String extension;
    public static final String DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_);
        requestMultiplePermissions();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getPermissionToRecordAudio();
           
        }
        Bundle extras = getIntent().getExtras();


        if (extras != null) {
            plot_id = extras.getString("code");
            totalarea = extras.getString("totalarea");
            status = extras.getString("status");
            ownership = extras.getString("ownership");



        }
        intview();
        setViews();


    }


    private void requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            //Toast.makeText(getApplicationContext(), getResources().getString(R.string.userPermission), Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();

        currentDate = new SimpleDateFormat(DATE_FORMAT1).format(Calendar.getInstance().getTimeInMillis());
    }


    private void intview() {
        //   plotcode, plotstatus,plotarea,plotowner;;

        plotcode = findViewById(R.id.plotcode);
        plotstatus = findViewById(R.id.plotstatus);
        plotarea = findViewById(R.id.plotarea);
        plotowner = findViewById(R.id.plotowner);
        backImg = (ImageView) findViewById(R.id.back);
        home_btn = (ImageView) findViewById(R.id.home_btn);
        btn_addIMG = findViewById(R.id.btn_addIMG);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();


        getcomplaints = findViewById(R.id.issue_type);
        comments = findViewById(R.id.comments);
        imageview = (ImageView) findViewById(R.id.iv);
        imageview2 = (ImageView) findViewById(R.id.iv2);
        imageview3 = (ImageView) findViewById(R.id.iv3);

        imageview.setVisibility(View.GONE);
        imageview2.setVisibility(View.GONE);
        imageview2.setVisibility(View.GONE);
        btn_addIMG.setVisibility(View.VISIBLE);

        submit = (Button) findViewById(R.id.req_loan);

        lyt_img = findViewById(R.id.lyt_img1);
        lyt_img2 = findViewById(R.id.lyt_img2);
        lyt_img3 = findViewById(R.id.lyt_img3);

        img_delete1 = findViewById(R.id.img_delete1);
        img_delete2 = findViewById(R.id.img_delete2);
        img_delete3 = findViewById(R.id.img_delete3);

        /*Initially Disbale images */
        lyt_img.setVisibility(View.GONE);
        lyt_img2.setVisibility(View.GONE);
        lyt_img3.setVisibility(View.GONE);

        linearLayoutRecorder = (LinearLayout) findViewById(R.id.linearLayoutRecorder);
        chronometer = (Chronometer) findViewById(R.id.chronometerTimer);
        chronometer.setBase(SystemClock.elapsedRealtime());
        imageViewRecord = (ImageView) findViewById(R.id.imageViewRecord);
        imageViewStop = (ImageView) findViewById(R.id.imageViewStop);
        imageViewPlay = (ImageView) findViewById(R.id.imageViewPlay);
        linearLayoutPlay = (LinearLayout) findViewById(R.id.linearLayoutPlay);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        imageViewRecord.setOnClickListener(this);
        imageViewStop.setOnClickListener(this);
        imageViewPlay.setOnClickListener(this);
        random = new Random();

    }

    private void setViews() {

        plotcode.setText(plot_id);
        plotstatus.setText(status);
        plotarea.setText(totalarea);
        plotowner.setText(ownership);
        loginressponse = SharedPrefsData.getCatagories(Complaints_Activity.this);

        img_delete1.setOnClickListener(this);
        img_delete2.setOnClickListener(this);
        img_delete3.setOnClickListener(this);

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent =new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);*/
                Intent intent = new Intent(Complaints_Activity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        Getcomplaint_type();
        getcomplaints.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_issue = getcomplaints.getItemAtPosition(getcomplaints.getSelectedItemPosition()).toString();

                //  Log.e("seleced_period===", seleced_Duration);
//                durationId = period_id.get(labourSpinner.getSelectedItemPosition());
                //Log.e("duration======", String.valueOf(durationId));
//
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
        btn_addIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (images.size() > 2) {
                    Toast.makeText(Complaints_Activity.this, "max Images 3", Toast.LENGTH_SHORT).show();
                } else {
                    showPictureDialog();
                }


            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validations()) {
                    stoppRecording();
                    try {
                        mPlayer.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mPlayer = null;
                    submit.setEnabled(false);

                    submit.setBackgroundColor(Color.BLACK);
                    if (isOnline())
                        AddComplaintRequest();
                    else {
                        showDialog(Complaints_Activity.this, getResources().getString(R.string.Internet));
                        //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    private void stoppRecording() {
        try {
            mRecorder.stop();
            mRecorder.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mRecorder = null;
        //starting the chronometer
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime());
    }


    private boolean validations() {
        if (getcomplaints.getSelectedItemPosition() == 0) {

            showDialog(Complaints_Activity.this, getResources().getString(R.string.valid_issue_type));
            return false;
        }

        if (images.size() == 0 &&  fileName == null) {

            Log.d(TAG, "---- analysis ---->> base64 :" + images.size() + fileName);
            showDialog(Complaints_Activity.this, getResources().getString(R.string.select_image));
            return false;
        }


        return true;
    }


    private void AddComplaintRequest() {
        mdilogue.show();
        JsonObject object = visitReuestobject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.postComplaint(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseComplaint>() {
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
                    public void onNext(ResponseComplaint visitresponseModel) {


                        if (visitresponseModel.getIsSuccess()) {
                            mdilogue.dismiss();
                            submit.setEnabled(false);
                            if (visitresponseModel.getAffectedRecords() == 0) {
                                showDialog(Complaints_Activity.this, visitresponseModel.getEndUserMessage());
                            } else {
                                new Handler().postDelayed(new Runnable() {
                                    @RequiresApi(api = Build.VERSION_CODES.M)
                                    @Override
                                    public void run() {
//
                                        List<MSGmodel> displayList = new ArrayList<>();

                                        // displayList.add(new MSGmodel(getString(R.string.select_labour_type), selected_name));
                                        displayList.add(new MSGmodel(getResources().getString(R.string.issue_type), selected_issue));
                                        String Comments = comments.getText().toString();
                                        if (Comments != null && !Comments.isEmpty() && !Comments.equals("null")) {
                                            displayList.add(new MSGmodel(getResources().getString(R.string.comments), comments.getText().toString()));
                                        }


                                        showvisitSuccessDialog(displayList, getResources().getString(R.string.visit_success));
                                    }
                                }, 300);
                            }
                        } else {
                            showDialog(Complaints_Activity.this, visitresponseModel.getEndUserMessage());
                        }
                    }


                });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void showvisitSuccessDialog(List<MSGmodel> displayList, String summary) {

        final Button play;
        final ImageView iv1, iv2, iv3, imageView_Play;
        LinearLayout voice_layout;
        final SeekBar seek_Bar;
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.visit_dialog, viewGroup, false);
        TextView summary_text = dialogView.findViewById(R.id.summary_text);
        summary_text.setText(summary);

        iv1 = dialogView.findViewById(R.id.iv);
        iv2 = dialogView.findViewById(R.id.iv2);
        iv3 = dialogView.findViewById(R.id.iv3);
        imageView_Play = dialogView.findViewById(R.id.imageView_Play);
        seek_Bar = dialogView.findViewById(R.id.seek_Bar);
        voice_layout = dialogView.findViewById(R.id.linearLayout_Play);
        iv1.setVisibility(View.GONE);
        iv2.setVisibility(View.GONE);
        iv3.setVisibility(View.GONE);

        if (null != fileName || !TextUtils.isEmpty(fileName) ){
//            File file = new File(fileName);
//        if (file.exists()) {
            voice_layout.setVisibility(View.VISIBLE);
        }
        else {
            voice_layout.setVisibility(View.GONE);
        }

        for (int i = 0; i < images.size(); i++) {
            if (i == 0) {
                iv1.setVisibility(View.VISIBLE);
                iv1.setImageBitmap(images.get(i));
            }
            if (i == 1) {
                iv2.setVisibility(View.VISIBLE);
                iv2.setImageBitmap(images.get(i));
            }

            if (i == 2) {
                iv3.setVisibility(View.VISIBLE);
                iv3.setImageBitmap(images.get(i));
            }
        }
//
        imageView_Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) throws IllegalArgumentException, SecurityException, IllegalStateException {
                if (!isPlaying && fileName != null) {
                    isPlaying = true;
                    if (mPlayer != null) {
                        lastProgress = 0;
                       // chronometer.setBase(SystemClock.elapsedRealtime());
                        starttPlaying();
                    } else {
                        starttPlaying();
                    }
//                    isPlaying = true;
//
//                      lastProgress = 0;
//                        chronometer.setBase(SystemClock.elapsedRealtime());
//                    mPlayer = new MediaPlayer();
//                    try {
//                        mPlayer.setDataSource(fileName);
//                        mPlayer.prepare();
//                        mPlayer.start();
//                    } catch (IOException e) {
//                        Log.e("LOG_TAG", "prepare() failed");
//                    }
//                    //making the imageview pause button
//                    imageView_Play.setImageResource(R.drawable.ic_pause);
//
//                    seek_Bar.setProgress(lastProgress);
//                    mPlayer.seekTo(lastProgress);
//                    seek_Bar.setMax(mPlayer.getDuration());
//                    seek_Updation();
//                    chronometer.start();
//
//
//                    mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                        @Override
//                        public void onCompletion(MediaPlayer mp) {
//                            imageView_Play.setImageResource(R.drawable.ic_play);
//                            isPlaying = false;
//                            chronometer.stop();
//                        }
//                    });
//
//
//                    seek_Bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                        @Override
//                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                            if (mPlayer != null && fromUser) {
//                                mPlayer.seekTo(progress);
//                                chronometer.setBase(SystemClock.elapsedRealtime() - mPlayer.getCurrentPosition());
//                                lastProgress = progress;
//                            }
//                        }
//
//                        @Override
//                        public void onStartTrackingTouch(SeekBar seekBar) {
//
//                        }
//
//                        @Override
//                        public void onStopTrackingTouch(SeekBar seekBar) {
//
//                        }
//                    });
                 }else {
                    isPlaying = false;
                    try {
                        mPlayer.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mPlayer = null;
                    //showing the play button
                    imageView_Play.setImageResource(R.drawable.ic_play);
                    //chronometer.stop();


                }
            }

            private void starttPlaying() {
                mPlayer = new MediaPlayer();
                try {
                    mPlayer.setDataSource(fileName);
                    mPlayer.prepare();
                    mPlayer.start();
                } catch (IOException e) {
                    Log.e("LOG_TAG", "prepare() failed");
                }
                //making the imageview pause button
                imageView_Play.setImageResource(R.drawable.ic_pause);
                seek_Bar.setVisibility(View.VISIBLE);
                seek_Bar.setProgress(lastProgress);
                mPlayer.seekTo(lastProgress);
                seek_Bar.setMax(mPlayer.getDuration());
                seek_Updation();
               // chronometer.start();

                mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        imageView_Play.setImageResource(R.drawable.ic_play);
                        seek_Bar.setVisibility(View.GONE);
                        isPlaying = false;
                        chronometer.stop();
                    }
                });


                seek_Bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (mPlayer != null && fromUser) {
                            mPlayer.seekTo(progress);
                          //  chronometer.setBase(SystemClock.elapsedRealtime() - mPlayer.getCurrentPosition());
                            lastProgress = progress;
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });


            }

            Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        seek_Updation();
                    }
                };


            //
            private void seek_Updation() {
                if (mPlayer != null) {
                    int mCurrentPosition = mPlayer.getCurrentPosition();
                    seek_Bar.setProgress(mCurrentPosition);
                    lastProgress = mCurrentPosition;
                }
                mHandler.postDelayed(runnable, 100);
            }
        });


        LinearLayout layout = dialogView.findViewById(R.id.linear_text);
        final ImageView img = dialogView.findViewById(R.id.img);


        for (int i = 0; i < displayList.size()   ; i++) {

            LinearLayout lty = new LinearLayout(this);
            lty.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
            lty.setWeightSum(1);
            lty.setOrientation(LinearLayout.HORIZONTAL);

            TextView txtTitle = new TextView(this);
            txtTitle.setText(displayList.get(i).getKey());
            txtTitle.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            txtTitle.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f));
            txtTitle.setTextColor(getColor(R.color.red));
            lty.addView(txtTitle);

            TextView txtitem = new TextView(this);
            txtitem.setText(displayList.get(i).getValue());
            txtitem.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            txtitem.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f));


            lty.addView(txtitem);
            //  lty.setGravity(View.FOCUS_LEFT);

            layout.addView(lty);
            displayImages();
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setCancelable(false);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Button dialogButton = (Button) alertDialog.findViewById(R.id.buttonOk);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                stoppRecording();
                try {
                    mPlayer.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(Complaints_Activity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

            }
        });


    }



    private JsonObject visitReuestobject() {
        User_id =  loginressponse.getResult().getUserInfos().getId();


        List<Complaintobject.RepoFile> visitRepo = new ArrayList<>();

        for (int i = 0; i < images.size(); i++) {

            Complaintobject.RepoFile visitRepo1 = new Complaintobject.RepoFile();
            visitRepo1.setId(0);
            visitRepo1.setComplaintCode(null);
            visitRepo1.setFileLocation(null);
            visitRepo1.setFileName(null);
            visitRepo1.setFileBytes(CommonUtil.bitMaptoBase64(images.get(i)));
            visitRepo1.setFileExtension(extension);
            visitRepo1.setIsActive(true);
            visitRepo1.setCreatedByUserId(User_id);
            visitRepo1.setCreatedDate(currentDate);
            visitRepo1.setIsResult(true);
            visitRepo1.setIsAudioRecording(false);
            visitRepo1.setServerUpdatedStatus(false);
            visitRepo1.setUpdatedByUserId(User_id);
            visitRepo1.setUpdatedDate(currentDate);


            visitRepo.add(visitRepo1);
        }
        if (null != fileName || !TextUtils.isEmpty(fileName)) {
    Complaintobject.RepoFile visitRepo11 = new Complaintobject.RepoFile();
            visitRepo11.setId(0);
            visitRepo11.setComplaintCode(null);
            visitRepo11.setFileLocation(null);
            visitRepo11.setFileName(null);
            visitRepo11.setFileBytes(doFileUpload(new File(fileName)));
            visitRepo11.setFileExtension(".mp3");
            visitRepo11.setIsActive(true);
            visitRepo11.setCreatedByUserId(User_id);
            visitRepo11.setCreatedDate(currentDate);
            visitRepo11.setIsResult(true);
            visitRepo11.setIsAudioRecording(true);
            visitRepo11.setServerUpdatedStatus(false);
            visitRepo11.setUpdatedByUserId(User_id);
            visitRepo11.setUpdatedDate(currentDate);


    visitRepo.add(visitRepo11);

}



        Complaintobject requestModel = new Complaintobject(visitRepo);
        requestModel.setId(0);
        requestModel.setCode(null);
        requestModel.setPlotCode(plot_id);
        requestModel.setStatusTypeId(159);
        requestModel.setServerUpdatedStatus(false);
        requestModel.setCreatedByUserId(User_id);
        requestModel.setCreatedDate(currentDate);
        requestModel.setUpdatedByUserId(User_id);
        requestModel.setUpdatedDate(currentDate);
        requestModel.setComments(comments.getText().toString());
        requestModel.setComplaintTypeIds(Issue_Id.get(getcomplaints.getSelectedItemPosition() - 1)+"");
        requestModel.setAssignToUserId(null);
        requestModel.setIsActive(true);

        Log.d(TAG, "---- analysis ---->> base64 514:" + images.size() + fileName);

        return new Gson().toJsonTree(requestModel).getAsJsonObject();


    }

    private String doFileUpload(File f){


        byte[] bytes = new byte[0];
        try {
            bytes = FileUtils.readFileToByteArray(f);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //String encoded = Base64.encodeToString(bytes, 0);
        String finalString =android.util.Base64.encodeToString(bytes,0);



        return finalString;

// Receiving side

    }
    private void Getcomplaint_type() {
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getIssuestypes(APIConstantURL.GetIssue+29)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Getcomplaints>() {
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
                        mdilogue.cancel();
                        showDialog(Complaints_Activity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(Getcomplaints getIssueModel) {


                        if (getIssueModel.getListResult() != null) {
                            Issue_type.add("Select");
                            for (Getcomplaints.ListResult data : getIssueModel.getListResult()
                            ) {
                                Issue_type.add(data.getDesc());
                                Issue_Id.add(data.getTypeCdId());
                            }
                            Log.d(TAG, "RESPONSE======" + Issue_type);

//

                            ArrayAdapter aa = new ArrayAdapter(Complaints_Activity.this, R.layout.spinner_item, Issue_type);
                            aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                            getcomplaints.setAdapter(aa);
                        } else {
                            Log.e("nodada====", "nodata===custom2");

                        }

                    }

                });

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getPermissionToRecordAudio() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    RECORD_AUDIO_REQUEST_CODE);

        }
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.img_delete1:
                images.remove(0);
                displayImages();
                break;
            case R.id.img_delete2:
                images.remove(1);
                displayImages();
                break;
            case R.id.img_delete3:
                images.remove(2);
                displayImages();
                break;

            case R.id.imageViewRecord:
                prepareforRecording();
                startRecording();

                break;
            case R.id.imageViewStop:
                prepareforStop();
                stopRecording();

                break;
            case R.id.imageViewPlay:
                if (!isPlaying && fileName != null) {
                    isPlaying = true;
                    if (mPlayer != null) {
                        lastProgress = 0;
                        chronometer.setBase(SystemClock.elapsedRealtime());
                        startPlaying();
                    } else {
                        startPlaying();
                    }

                } else {
                    isPlaying = false;
                    stopPlaying();
                }
        }

    }


    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Log.e("path===", path);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] imageInByte = stream.toByteArray();
                    long sizeOfImage = imageInByte.length / 1024; //Image size
//                    Log.e("sizeOfImage===", sizeOfImage+"");
//                    if(sizeOfImage > 200) {
//                        Log.e("imagesize===", "Image Size more than 200KB");
//                        showDialog(Complaints_Activity.this, "Image Size more than 200KB");
//                        Toast.makeText(this, "Image Size more than 200KB", Toast.LENGTH_LONG).show();
//                    }else {
                        images.add(bitmap);
                        displayImages();
               //     }
                } catch (IOException e) {
                    e.printStackTrace();
                    //  Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] imageInByte = stream.toByteArray();
            long sizeOfImage = imageInByte.length / 1024; //Image size
//            Log.e("sizeOfImage===", sizeOfImage+"");
//            if(sizeOfImage > 200) {
//                Log.e("imagesize===", "Image Size more than 200KB");
//                showDialog(Complaints_Activity.this, "Image Size more than 200KB");
//                Toast.makeText(this, "Image Size more than 200KB", Toast.LENGTH_LONG).show();
//            }else {
                images.add(thumbnail);
                //  saveImage(thumbnail);
                displayImages();
         //   }
            //Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayImages() {

        lyt_img.setVisibility(View.GONE);
        lyt_img2.setVisibility(View.GONE);
        lyt_img3.setVisibility(View.GONE);
        btn_addIMG.setVisibility(View.VISIBLE);
        if (images.size() > 0) {
            if (images.size() > 0 && images.get(0) != null) {
                imageview.setImageBitmap(images.get(0));
                imageview.setVisibility(View.VISIBLE);
                lyt_img.setVisibility(View.VISIBLE);

            }
            if (images.size() > 1 && images.get(1) != null) {
                imageview2.setImageBitmap(images.get(1));
                imageview2.setVisibility(View.VISIBLE);
                lyt_img2.setVisibility(View.VISIBLE);
            }
            if (images.size() > 2 && images.get(2) != null) {
                imageview3.setImageBitmap(images.get(2));
                imageview3.setVisibility(View.VISIBLE);
                lyt_img3.setVisibility(View.VISIBLE);
                btn_addIMG.setVisibility(View.GONE);
            }
        }

    }



    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance().getTimeInMillis() + ".jpg");

            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            long length = f.length() / 1024; // Size in KB Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());
            Log.d("TAG", " sizFilee::--->" + length);
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());
            mCurrentPhotoPath =f.getAbsolutePath();
            Log.e("PhotoPath===========",mCurrentPhotoPath);

            extension = mCurrentPhotoPath.substring(mCurrentPhotoPath.lastIndexOf("."));
            Log.e("Extension===========",extension);

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void prepareforStop() {
        TransitionManager.beginDelayedTransition(linearLayoutRecorder);
        imageViewRecord.setVisibility(View.VISIBLE);
        imageViewStop.setVisibility(View.GONE);
        linearLayoutPlay.setVisibility(View.VISIBLE);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void prepareforRecording() {
        TransitionManager.beginDelayedTransition(linearLayoutRecorder);
        imageViewRecord.setVisibility(View.GONE);
        imageViewStop.setVisibility(View.VISIBLE);
        linearLayoutPlay.setVisibility(View.GONE);
    }

    private void stopPlaying() {
        try {
            mPlayer.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mPlayer = null;
        //showing the play button
        imageViewPlay.setImageResource(R.drawable.ic_play);
        chronometer.stop();

    }


    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.AudioEncoder.AMR_NB);

        File root = android.os.Environment.getExternalStorageDirectory();
        File file = new File(root.getAbsolutePath() + "/3FAkshaya/Audios");

        if (!file.exists()) {
            file.mkdirs();
        }

        fileName = root.getAbsolutePath() + "/3FAkshaya/Audios/" + String.valueOf(System.currentTimeMillis() + ".mp3");
        Log.d("filename===1049", fileName);
        mRecorder.setOutputFile(fileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);

        try {
            mRecorder.prepare();
            mRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lastProgress = 0;
        seekBar.setProgress(0);
        stopPlaying();
        // making the imageview a stop button
        //starting the chronometer
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }


    private void stopRecording() {

        try {
            mRecorder.stop();
            mRecorder.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mRecorder = null;
        //starting the chronometer
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime());
        //showing the play button
        //  Toast.makeText(this, "Recording saved successfully.", Toast.LENGTH_SHORT).show();
    }


    private void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(fileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e("LOG_TAG", "prepare() failed");
        }
        //making the imageview pause button
        imageViewPlay.setImageResource(R.drawable.ic_pause);
        seekBar.setVisibility(View.VISIBLE);
        seekBar.setProgress(lastProgress);
        mPlayer.seekTo(lastProgress);
        seekBar.setMax(mPlayer.getDuration());
        seekUpdation();
        chronometer.start();

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                imageViewPlay.setImageResource(R.drawable.ic_play);
                seekBar.setVisibility(View.GONE);
                isPlaying = false;
                chronometer.stop();
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mPlayer != null && fromUser) {
                    mPlayer.seekTo(progress);
                    chronometer.setBase(SystemClock.elapsedRealtime() - mPlayer.getCurrentPosition());
                    lastProgress = progress;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
     
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            seekUpdation();
        }
    };

    private void seekUpdation() {
        if (mPlayer != null) {
            int mCurrentPosition = mPlayer.getCurrentPosition();
            seekBar.setProgress(mCurrentPosition);
            lastProgress = mCurrentPosition;
        }
        mHandler.postDelayed(runnable, 100);
    }

}
