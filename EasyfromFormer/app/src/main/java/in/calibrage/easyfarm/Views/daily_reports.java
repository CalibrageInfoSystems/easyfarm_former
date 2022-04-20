package in.calibrage.easyfarm.Views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dmax.dialog.SpotsDialog;

import in.calibrage.easyfarm.Common.CommonActivity;
import in.calibrage.easyfarm.Common.CommonUtil;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.localData.SharedPrefsData;
import in.calibrage.easyfarm.model.Complaintobject;
import in.calibrage.easyfarm.model.Config;
import in.calibrage.easyfarm.model.DailyreportsResponse;
import in.calibrage.easyfarm.model.Dailyreportsobject;
import in.calibrage.easyfarm.model.GetCropCycleDailyUploadobject;
import in.calibrage.easyfarm.model.GetCropCycleDailyUploadresponse;
import in.calibrage.easyfarm.model.Getcomplaints;
import in.calibrage.easyfarm.model.LoginResponse;
import in.calibrage.easyfarm.model.ResponseComplaint;
import in.calibrage.easyfarm.service.APIConstantURL;
import in.calibrage.easyfarm.service.ApiService;
import in.calibrage.easyfarm.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class daily_reports extends CommonActivity implements View.OnClickListener {
    String code;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    Toolbar toolbar;
    Spinner getfiletype;
   Button btn_addIMG;
    private Button submit;
    private ImageView imageview, imageview2, imageview3;
    private ImageView img_delete1, img_delete2, img_delete3;
    private RelativeLayout lyt_img, lyt_img2, lyt_img3;
    private static final String IMAGE_DIRECTORY = "/demonuts";
    private int GALLERY = 1, CAMERA = 2;
    String selected_file;
    List<String> file_type = new ArrayList<String>();
    private static final String TAG = Complaints_Activity.class.getSimpleName();
    List<Integer> file_Id = new ArrayList<Integer>();
    private List<Bitmap> images = new ArrayList<>();
    LoginResponse loginressponse;
    int User_id;
    private String mCurrentPhotoPath;
    private String extension;
    LinearLayout imageslinear,voicelinear;
    String currentDate, currentDatee;
    Date date1;

    Date date2;
    public static final String DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss";
    private VideoView vidPreview;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    private Uri fileUri; // file url to store image/video

    private int position = 0;
    private MediaController mediaController;
    FrameLayout myCameraPreview;
    private ImageView  btnRecordVideo;

    private static final int SELECT_VIDEO = 3;
    private String selectedPath,datetimevaluereq;
    Button previous_reports;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_reports);
        requestMultiplePermissions();
        Intent i = getIntent();
        code = i.getStringExtra("code");
        intviews();
        setview();
        settoolbar();
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


    }

    private void intviews() {
      //  getfiletype =(Spinner)findViewById(R.id.getfiletype);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
        btn_addIMG = findViewById(R.id.btn_addIMG);
        imageview = (ImageView) findViewById(R.id.iv);
        imageview2 = (ImageView) findViewById(R.id.iv2);
        imageview3 = (ImageView) findViewById(R.id.iv3);

       // imageslinear =(LinearLayout)findViewById(R.id.imageslinear);
        voicelinear =(LinearLayout) findViewById(R.id.voicelinear);
        imageview.setVisibility(View.GONE);
        imageview2.setVisibility(View.GONE);
        imageview2.setVisibility(View.GONE);
        btn_addIMG.setVisibility(View.VISIBLE);

        submit = (Button) findViewById(R.id.sunmit_report);

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
        btnRecordVideo = (ImageView) findViewById(R.id.btnRecordVideo);
        this.vidPreview = (VideoView) findViewById(R.id.videoView);
        myCameraPreview =(FrameLayout)findViewById(R.id.frameLayout);
        previous_reports = (Button) findViewById(R.id.previous_reports);

    }
    private void setview() {
        loginressponse = SharedPrefsData.getCatagories(daily_reports.this);
        currentDate = new SimpleDateFormat(DATE_FORMAT1).format(Calendar.getInstance().getTimeInMillis());
       // currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        img_delete1.setOnClickListener(this);
        img_delete2.setOnClickListener(this);
        img_delete3.setOnClickListener(this);
        GetCropCycleDailyUpload();

        btn_addIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (images.size() > 2) {
                    Toast.makeText(daily_reports.this, "max Images 3", Toast.LENGTH_SHORT).show();
                } else {
                    showPictureDialog();
                }


            }
        });

        btnRecordVideo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //
                showPictureDialogvideo();

            }
        });
        previous_reports.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //
                Intent intent = new Intent(daily_reports.this, PreviousReports.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("cropcode",code);

              startActivity(intent);

            }
        });
        // Set the media controller buttons
        if (this.mediaController == null) {
            this.mediaController = new MediaController(daily_reports.this);

            // Set the videoView that acts as the anchor for the MediaController.
            this.mediaController.setAnchorView(vidPreview);

            // Set MediaController for VideoView
            this.vidPreview.setMediaController(mediaController);
        }


        // When the video file ready for playback.
        this.vidPreview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {

                vidPreview.seekTo(position);
                if (position == 0) {
                    vidPreview.start();
                }

                // When video Screen change size.
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

                        // Re-Set the videoView that acts as the anchor for the MediaController
                        mediaController.setAnchorView(vidPreview);
                    }
                });
            }
        });

        // Checking camera availability
        if (!isDeviceSupportCamera()) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Your device doesn't support camera",
                    Toast.LENGTH_LONG).show();
            // will close the app if the device does't have camera
            finish();
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if (validations()) {
                    if (isOnline()) {
                        submit.setEnabled(false);
                        submit.setBackgroundColor(R.drawable.button_disablebg);
                        AddUpdateCropCycleDailyUploads();
                    }
                    else {
                        showDialog(daily_reports.this, getResources().getString(R.string.Internet));
                        //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
                    }

                }
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

                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onNext(GetCropCycleDailyUploadresponse getCropCycleDailyUploadresponse) {

                        if (getCropCycleDailyUploadresponse.getListResult().size()!=0 ) {

                            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
                            SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                Date oneWayTripDate = input.parse(getCropCycleDailyUploadresponse.getListResult().get(0).getPostedOn());

                                datetimevaluereq = input.format(oneWayTripDate);

                                //Setting dates
                                date1 = input.parse(currentDate);
                                date2 = input.parse(datetimevaluereq);

                                //Comparing dates
                                long difference = Math.abs(date1.getTime() - date2.getTime());
                                long differenceDates = difference / (24 * 60 * 60 * 1000);

                                //Convert long to String
                                String dayDifference = Long.toString(differenceDates);

                                int hours = (int) (difference / (1000 * 60 * 60));
                                Log.e("===============", "======diiffrent======" + hours);
if(hours >= 24){
    submit.setEnabled(true);

}
else{
    submit.setEnabled(false);
    submit.setBackgroundColor(R.drawable.button_disablebg);
    Toast.makeText(daily_reports.this, " Report Sent Today", Toast.LENGTH_LONG).show();
}

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }




                        } else {

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
    private void showPictureDialogvideo() {

            AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
            pictureDialog.setTitle("Select Action");
            String[] pictureDialogItems = {
                    "Select Video from Files",
                    "Capture Video from Camera"};
            pictureDialog.setItems(pictureDialogItems,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0:
                                    chooseVideo();
                                    break;
                                case 1:
                                    recordVideo();
                                    break;
                            }
                        }
                    });
            pictureDialog.show();
        }

    private void chooseVideo() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select a Video "), SELECT_VIDEO);
    }


    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }


    private void recordVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        // set video quality
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file
        // name
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        // start the video capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_VIDEO_REQUEST_CODE);
    }

    /**
     * Creating file uri to store image/video
     */
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * returning image / video
     */
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                Config.IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(TAG, "Oops! Failed create "
                        + Config.IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    private void AddUpdateCropCycleDailyUploads() {

        mdilogue.show();
        JsonObject object = dailyuploadsReuestobject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.postdailyreports(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DailyreportsResponse>() {
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
                    public void onNext(DailyreportsResponse dailyreportsResponse) {
                        mdilogue.dismiss();
                        if (dailyreportsResponse.getIsSuccess()) {

                            submit.setEnabled(false);
                            Toast.makeText(daily_reports.this, "Reports Send Successfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(daily_reports.this, HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
//                            if (dailyreportsResponse.getAffectedRecords() == 0) {
//                                showDialog(Complaints_Activity.this, visitresponseModel.getEndUserMessage());
//                            } else {
//                                new Handler().postDelayed(new Runnable() {
//                                    @RequiresApi(api = Build.VERSION_CODES.M)
//                                    @Override
//                                    public void run() {
////
//                                        List<MSGmodel> displayList = new ArrayList<>();
//
//                                        // displayList.add(new MSGmodel(getString(R.string.select_labour_type), selected_name));
//                                        displayList.add(new MSGmodel(getResources().getString(R.string.issue_type), selected_issue));
//                                        String Comments = comments.getText().toString();
//                                        if (Comments != null && !Comments.isEmpty() && !Comments.equals("null")) {
//                                            displayList.add(new MSGmodel(getResources().getString(R.string.comments), comments.getText().toString()));
//                                        }
//
//
//                                        showvisitSuccessDialog(displayList, getResources().getString(R.string.visit_success));
//                                    }
//                                }, 300);
//                            }
                        } else {
                            showDialog(daily_reports.this, dailyreportsResponse.getEndUserMessage());
                        }

                    }


                    });}

    private JsonObject dailyuploadsReuestobject() {
        User_id =  loginressponse.getResult().getUserInfos().getId();


        List<Dailyreportsobject.RepoFile> dailyRepo = new ArrayList<>();

        for (int i = 0; i < images.size(); i++) {

            Dailyreportsobject.RepoFile DailyRepo1 = new Dailyreportsobject.RepoFile();
            DailyRepo1.setId(0);
            DailyRepo1.setUploadId(0);
            DailyRepo1.setFileLocation(null);
            DailyRepo1.setFileName(null);
            DailyRepo1.setFileBytes(CommonUtil.bitMaptoBase64(images.get(i)));
            DailyRepo1.setFileExtension(extension);
            DailyRepo1.setCreatedByUserId(User_id);
            DailyRepo1.setCreatedDate(currentDate);
            DailyRepo1.setFileTypeId(112);
            DailyRepo1.setVideoEmbedUrl(null);
//            DailyRepo1.setIsResult(true);
//            DailyRepo1.setIsAudioRecording(false);
//            DailyRepo1.setServerUpdatedStatus(false);
//            DailyRepo1.setUpdatedByUserId(User_id);
//            DailyRepo1.setUpdatedDate(currentDate);


            dailyRepo.add(DailyRepo1);
        }
        if (null != selectedPath || !TextUtils.isEmpty(selectedPath)) {
            Log.e("Cycl===========",doFileUpload(new File(selectedPath)));
            Dailyreportsobject.RepoFile visitRepo11 = new Dailyreportsobject.RepoFile();
            visitRepo11.setId(0);
            visitRepo11.setUploadId(0);
            visitRepo11.setFileLocation(null);
            visitRepo11.setFileName(null);
            visitRepo11.setFileTypeId(113);
            visitRepo11.setFileExtension(".mp4");
            visitRepo11.setVideoEmbedUrl(null);
            visitRepo11.setFileBytes(doFileUpload(new File(selectedPath)));
            visitRepo11.setCreatedByUserId(User_id);
            visitRepo11.setCreatedDate(currentDate);


            dailyRepo.add(visitRepo11);

        }



        Dailyreportsobject requestModel = new Dailyreportsobject(dailyRepo);
        requestModel.setId(0);
        requestModel.setCycleCode(code);
        requestModel.setCreatedByUserId(User_id);
        requestModel.setCreatedDate(currentDate);
        requestModel.setUpdatedByUserId(User_id);
        requestModel.setUpdatedDate(currentDate);

        requestModel.setIsActive(true);

      //  Log.d(TAG, "---- analysis ---->> base64 514:" + images.size() + fileName);

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

    private boolean validations() {
//        if (getfiletype.getSelectedItemPosition() == 0) {
//
//            showDialog(daily_reports.this, getResources().getString(R.string.valid_filetype_type));
//            return false;
//        }
//if(selected_file.equalsIgnoreCase("Image")) {
    if (images.size() == 0) {

        Log.d(TAG, "---- analysis ---->> base64 :" + images.size());
        showDialog(daily_reports.this, getResources().getString(R.string.select_image));
        return false;
    }

        return true;
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
        else if (requestCode == CAMERA_CAPTURE_VIDEO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                myCameraPreview.setVisibility(View.VISIBLE);
                selectedPath =fileUri.getPath();
                vidPreview.setVideoPath(selectedPath);
                //vidPreview.start();
                // video successfully recorded
                // launching upload activity
                //launchUploadActivity(false);

            } else if (resultCode == RESULT_CANCELED) {

                // user cancelled recording
                Toast.makeText(getApplicationContext(),
                        "User cancelled video recording", Toast.LENGTH_SHORT)
                        .show();

            } else {
                // failed to record video
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to record video", Toast.LENGTH_SHORT)
                        .show();
            }
        }
       else if (requestCode == SELECT_VIDEO) {
            System.out.println("SELECT_VIDEO");
            myCameraPreview.setVisibility(View.VISIBLE);

            Uri selectedImageUri = data.getData();
            selectedPath = getPath(selectedImageUri);
            vidPreview.setVideoPath(selectedPath);
           // vidPreview.start();
            //   textView.setText(selectedPath);
        }
    }

    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
        cursor.close();

        return path;
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


        }

    }



    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // Store current position.
        savedInstanceState.putInt("CurrentPosition", vidPreview.getCurrentPosition());
        vidPreview.pause();
    }


    // After rotating the phone. This method is called.
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Get saved position.
        position = savedInstanceState.getInt("CurrentPosition");
        vidPreview.seekTo(position);
    }


}

