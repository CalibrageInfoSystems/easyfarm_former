package in.calibrage.easyfarm.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import in.calibrage.easyfarm.Common.CommonUtil;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.Views.Complaints_Activity;
import in.calibrage.easyfarm.localData.SharedPrefsData;
import in.calibrage.easyfarm.model.Complaintobject;
import in.calibrage.easyfarm.model.GetComplaintRepositoryResponse;
import in.calibrage.easyfarm.model.GetComplaintRepositoryobject;
import in.calibrage.easyfarm.model.GetComplaintsByPlotCode;
import in.calibrage.easyfarm.model.GetComplaintsobject;
import in.calibrage.easyfarm.model.GetComplaintsresponse;
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

public class GetcomplaintsAdapter extends RecyclerView.Adapter<GetcomplaintsAdapter.ViewHolder> {

    List<GetComplaintsByPlotCode.ListResult> list_complaints;
    public Context mContext;
    String datetimevaluereq, currentDate;
    String selectedItemID;
    int selectedPO;
    Button ok_btn;
    String AudioURL, imageur1;
    LinearLayout linearLayout_Play;
    Button submitBtn;
    //	private Chronometer chronometer;
    private List<String> image_list = new ArrayList<String>();
    ImageView iv1, iv2, iv3, imageViewPlay;
    LinearLayout voice_layout;
    SeekBar seekBar;
    private MediaPlayer mPlayer;
    private int lastProgress = 0;
    private Handler mHandler = new Handler();
    private int RECORD_AUDIO_REQUEST_CODE = 123;
    private boolean isPlaying = false;
    public static final String DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss";
    DecimalFormat df = new DecimalFormat("####0.00");
    private Subscription mSubscription;
    LayoutInflater mInflater;
    LoginResponse loginressponse;
    Boolean isreopen = false;
    int User_id;
    Integer Assigned_userid,Complaint_id;
    String Comments,plotcode;
    String reopencomments;
    TextInputLayout commentslbl;
    EditText comment_et;
    boolean setIsVideoRecording = false;
    // RecyclerView recyclerView;
    public GetcomplaintsAdapter(List<GetComplaintsByPlotCode.ListResult> list_loan, Context ctx) {
        this.list_complaints = list_loan;
        this.mContext = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.complaint_req_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        loginressponse = SharedPrefsData.getCatagories(mContext);

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date oneWayTripDate = input.parse(list_complaints.get(position).getComplaintDate());

            datetimevaluereq = output.format(oneWayTripDate);


            Log.e("===============", "======currentData======" + output.format(oneWayTripDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.requestCode.setText(list_complaints.get(position).getComplaintCode());
        holder.req_date.setText(datetimevaluereq);
        holder.PlotId.setText(list_complaints.get(position).getPlotCode());
//        if(list_complaints.get(position).getPlotSize()!=null) {
//            holder.plot_size.setText(df.format(list_complaints.get(position).getPlotSize()) +  " Acre");
//
//        }
      //  holder.location.setText(list_complaints.get(position).getVillageName());
        holder.statusType.setText(list_complaints.get(position).getStatus());
        currentDate = new SimpleDateFormat(DATE_FORMAT1).format(Calendar.getInstance().getTimeInMillis());
        if ("Resolution Provided".equals(holder.statusType.getText())) {
            // if ("Open".equals(holder.statusType.getText())) {

            holder.close.setVisibility(View.VISIBLE);
            holder.reopen.setVisibility(View.VISIBLE);

        } else {
            holder.close.setVisibility(View.GONE);
            holder.reopen.setVisibility(View.GONE);

        }


        holder.close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                selectedItemID = list_complaints.get(position).getComplaintCode();
                selectedPO = position;
                Assigned_userid =list_complaints.get(position).getAssignToUserId();
                Complaint_id = list_complaints.get(position).getComplaintId();
                Log.e("Assigned_userid===",Assigned_userid+"");
               Comments =list_complaints.get(position).getComments();
                plotcode=list_complaints.get(position).getPlotCode();
                try {
                    close_request();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });

        holder.details.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                selectedItemID = list_complaints.get(position).getComplaintCode();

                selectedPO = position;
                showCondetailsDialog(selectedPO);


            }

        });

        holder.reopen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

             //   if (isreopen == false) {

                    selectedItemID = list_complaints.get(position).getComplaintCode();
                    selectedPO = position;
                    showreopenDialog(selectedPO);
//                }else if  (isreopen == true) {
//                    reopen_request();
//                    holder.close.setVisibility(View.GONE);
//                    holder.reopen.setVisibility(View.GONE);
//                 //   holder.statusType.setText("Open");
//
//                }

            }

        });
    }

    private void reopen_request() {
        JsonObject object = reopenRequestobject();
        ApiService service = ServiceFactory.createRetrofitService(mContext, ApiService.class);
        mSubscription = service.postComplaint(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseComplaint>() {
                    @Override
                    public void onCompleted() {

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

                    }

                    @Override
                    public void onNext(ResponseComplaint visitresponseModel) {


                        if (visitresponseModel.getIsSuccess()) {
                            GetComplaintsByPlotCode.ListResult item = list_complaints.get(selectedPO);
                          item.setStatus("Reopen");
                            list_complaints.set(selectedPO, item);
                         Toast.makeText(mContext, mContext.getString(R.string.reopen), Toast.LENGTH_LONG).show();
                            notifyItemChanged(selectedPO);

                        }

                    }
                });


    }

    private JsonObject reopenRequestobject() {
        User_id =  loginressponse.getResult().getUserInfos().getId();


        List<Complaintobject.RepoFile> visitRepo = new ArrayList<>();


        Complaintobject requestModel = new Complaintobject(visitRepo);
        requestModel.setId(Complaint_id);
        requestModel.setAssignToUserId(Assigned_userid);
        requestModel.setCode(selectedItemID);
        requestModel.setPlotCode(plotcode);
        requestModel.setStatusTypeId(164);
        requestModel.setServerUpdatedStatus(false);
        requestModel.setCreatedByUserId(User_id);
        requestModel.setCreatedDate(currentDate);
        requestModel.setUpdatedByUserId(User_id);
        requestModel.setUpdatedDate(currentDate);
        requestModel.setComments(reopencomments);
        requestModel.setComplaintTypeIds(null);

        requestModel.setIsActive(true);



        return new Gson().toJsonTree(requestModel).getAsJsonObject();

    }

    private void showreopenDialog(final int selectedPO) {

        final Dialog dialog = new Dialog(mContext, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_comments);
        commentslbl = (TextInputLayout) dialog.findViewById(R.id.comments_lbl);
        comment_et = (EditText) dialog.findViewById(R.id.comment_et);
        submitBtn = (Button) dialog.findViewById(R.id.submitBtn);

/**
 * @param OnClickListner
 */
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reopencomments = comment_et.getText().toString();
                Assigned_userid =list_complaints.get(selectedPO).getAssignToUserId();
                Complaint_id = list_complaints.get(selectedPO).getComplaintId();
                plotcode=list_complaints.get(selectedPO).getPlotCode();
                Log.d("Complaint_id ====", Complaint_id+"" );
                isreopen = true;
             //   Toast.makeText(mContext, "Please click on Re-open to Submit", Toast.LENGTH_SHORT).show();
                if(Validation()) {


                    reopen_request();
                }
                dialog.dismiss();

            }
        });
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    private boolean Validation() {
        if (TextUtils.isEmpty(comment_et.getText().toString())) {
            // userNameEdt.setError(getString(R.string.err_please_enter_username));
            commentslbl.setError(mContext.getResources().getString(R.string.err_please_entercomments));
            //  showDialog(SignupActivity.this, getResources().getString(R.string.err_please_enter_username));
            return false;
        }
        else{

            commentslbl.setErrorEnabled(false);
        }
        return true;
    }


    private void close_request() throws JSONException {

        JsonObject object = Requestobject();
        ApiService service = ServiceFactory.createRetrofitService(mContext, ApiService.class);
        mSubscription = service.postComplaint(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseComplaint>() {
                    @Override
                    public void onCompleted() {

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

                    }

                    @Override
                    public void onNext(ResponseComplaint visitresponseModel) {


                        if (visitresponseModel.getIsSuccess()) {
                            GetComplaintsByPlotCode.ListResult item = list_complaints.get(selectedPO);
                            item.setStatus("Closed");
                            list_complaints.set(selectedPO, item);
                            Toast.makeText(mContext, mContext.getString(R.string.cancel_success), Toast.LENGTH_LONG).show();
                            notifyItemChanged(selectedPO);

                        }

                    }
                });
    }

    private JsonObject Requestobject() {
        User_id =  loginressponse.getResult().getUserInfos().getId();


        List<Complaintobject.RepoFile> visitRepo = new ArrayList<>();


        Complaintobject requestModel = new Complaintobject(visitRepo);
        requestModel.setId(Complaint_id);
        requestModel.setAssignToUserId(Assigned_userid);
        requestModel.setCode(selectedItemID);
        requestModel.setPlotCode(plotcode);
        requestModel.setStatusTypeId(163);
        requestModel.setServerUpdatedStatus(false);
        requestModel.setCreatedByUserId(User_id);
        requestModel.setCreatedDate(currentDate);
        requestModel.setUpdatedByUserId(User_id);
        requestModel.setUpdatedDate(currentDate);
        requestModel.setComments(Comments);
        requestModel.setComplaintTypeIds(null);

        requestModel.setIsActive(true);

     ;

        return new Gson().toJsonTree(requestModel).getAsJsonObject();



    }

    private void showCondetailsDialog(int selectedPO) {
        GetrequestRequestRepository();
        final Dialog dialog = new Dialog(mContext, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_images);
        imageViewPlay = (ImageView) dialog.findViewById(R.id.imageViewPlay);
        seekBar = (SeekBar) dialog.findViewById(R.id.seekBar);
        ok_btn = (Button) dialog.findViewById(R.id.buttonOk);
        iv1 = (ImageView) dialog.findViewById(R.id.iv);
        iv2 = (ImageView) dialog.findViewById(R.id.iv2);
        iv3 = (ImageView) dialog.findViewById(R.id.iv3);
        //linearLayout_Play=(LinearLayout)dialog.findViewById(R.id.linearLayout_Play)
        voice_layout = dialog.findViewById(R.id.linearLayout_Play);
        iv1.setVisibility(View.GONE);
        iv2.setVisibility(View.GONE);
        iv3.setVisibility(View.GONE);

//        String imageUrl = "https://via.placeholder.com/500";
//        Picasso.with(mContext).load(imageUrl).error(R.drawable.ic_user).into(iv1);
        //Loading image using Picasso
        // Picasso.get().load(imageUrl).into(iv1);
//
//

//        }
//

        imageViewPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (!isPlaying && AudioURL != null) {
                    isPlaying = true;
                    if (mPlayer != null) {
                        lastProgress = 0;
                        //chronometer.setBase(SystemClock.elapsedRealtime());
                        startPlaying();
                    } else {
                        startPlaying();
                    }

                } else {
                    isPlaying = false;
                    stopPlaying();
                }

            }
        });
        //  ok_btn = dialog.findViewById(R.id.btn_dialog);


/**
 * @param OnClickListner
 */
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

                try {
                    mPlayer.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mPlayer = null;
            }
        });
        dialog.show();

    }


    private void GetrequestRequestRepository() {
        JsonObject object = getComplaintrepobject();
        ApiService service = ServiceFactory.createRetrofitService(mContext, ApiService.class);
        mSubscription = service.Getcomplaintsfilerepo(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetComplaintRepositoryResponse>() {
                    @Override
                    public void onCompleted() {

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
                        // mdilogue.dismiss();
                        //showDialog(LabourActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetComplaintRepositoryResponse getVisitRequestRepository) {


                        final List<String> img = new ArrayList<>();
                        if (getVisitRequestRepository.getListResult() != null) {

                            for (int i = 0; i < getVisitRequestRepository.getListResult().size(); i++) {


                                if (getVisitRequestRepository.getListResult().get(i).getIsAudioRecording()== true) {
                                    voice_layout.setVisibility(View.VISIBLE);
                                    AudioURL = getVisitRequestRepository.getListResult().get(i).getFilePath();
                                } else {
                                    voice_layout.setVisibility(View.GONE);
                                    img.add(getVisitRequestRepository.getListResult().get(i).getFilePath());
                                    Log.d("GETVisit", getVisitRequestRepository.getListResult().get(i).getFilePath());
//
                                }
//

                                for (int j = 0; j < img.size(); j++) {
                                    final   int finalJ =j;
                                    if (j == 0) {
                                        iv1.setVisibility(View.VISIBLE);
                                       // Picasso.with(mContext).load(img.get(j)).error(R.drawable.ic_user).into(iv1);
                                        Glide.with(mContext).load(img.get(j)).error(R.drawable.ic_user).into(iv1);

                                        iv1.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Context context = mContext.getApplicationContext();
                                                mInflater = LayoutInflater.from(context);
                                              AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
                                                View mView = mInflater.inflate(R.layout.dialog_custom_layout, null);
                                                TextView cancel =mView.findViewById(R.id.cancel);
                                                //  Picasso.with(mContext).load(getCollectionInfoById.getResult().getReceiptImg()).error(R.drawable.ic_user).into(photoView);
                                                PhotoView photoView = mView.findViewById(R.id.imageView);
                                                Glide.with(mContext).load(img.get(finalJ)).error(R.drawable.ic_user).into(photoView);
                                              //  Picasso.with(mContext).load(img.get(finalJ)).error(R.drawable.ic_user).into(photoView);
                                                //photoView.setImageResource(Integer.parseInt(getCollectionInfoById.getResult().getReceiptImg()));
                                                mBuilder.setView(mView);

                                                final AlertDialog mDialog = mBuilder.create();
                                                cancel.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        mDialog.dismiss();
                                                    }
                                                });
                                                mDialog.show();
                                            }
                                        });
                                    }
                                    if (j == 1) {
                                        iv2.setVisibility(View.VISIBLE);
                                      //  Picasso.with(mContext).load(img.get(j)).error(R.drawable.ic_user).into(iv2);
                                        Glide.with(mContext).load(img.get(j)).error(R.drawable.ic_user).into(iv2);

                                        iv2.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Context context = mContext.getApplicationContext();
                                                mInflater = LayoutInflater.from(context);
                                              AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
                                                View mView = mInflater.inflate(R.layout.dialog_custom_layout, null);
                                                TextView cancel =mView.findViewById(R.id.cancel);
                                                //  Picasso.with(mContext).load(getCollectionInfoById.getResult().getReceiptImg()).error(R.drawable.ic_user).into(photoView);
                                                PhotoView photoView = mView.findViewById(R.id.imageView);

                                                Glide.with(mContext).load(img.get(finalJ)).error(R.drawable.ic_user).into(photoView);
                                              //  Picasso.with(mContext).load(img.get(finalJ)).error(R.drawable.ic_user).into(photoView);
                                                //photoView.setImageResource(Integer.parseInt(getCollectionInfoById.getResult().getReceiptImg()));
                                                mBuilder.setView(mView);

                                                final AlertDialog mDialog = mBuilder.create();
                                                cancel.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        mDialog.dismiss();
                                                    }
                                                });
                                                mDialog.show();
                                            }
                                        });
                                    }
                                    if (j == 2) {
                                        iv3.setVisibility(View.VISIBLE);
                                       // Picasso.with(mContext).load(img.get(j)).error(R.drawable.ic_user).into(iv3);
                                        Glide.with(mContext).load(img.get(j)).error(R.drawable.ic_user).into(iv3);
                                        iv3.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Context context = mContext.getApplicationContext();
                                                mInflater = LayoutInflater.from(context);
                                               AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
                                                View mView = mInflater.inflate(R.layout.dialog_custom_layout, null);
                                                TextView cancel =mView.findViewById(R.id.cancel);
                                                //  Picasso.with(mContext).load(getCollectionInfoById.getResult().getReceiptImg()).error(R.drawable.ic_user).into(photoView);
                                                PhotoView photoView = mView.findViewById(R.id.imageView);
                                                Glide.with(mContext).load(img.get(finalJ)).error(R.drawable.ic_user).into(photoView);
                                             //   Picasso.with(mContext).load(img.get(finalJ)).error(R.drawable.ic_user).into(photoView);
                                                //photoView.setImageResource(Integer.parseInt(getCollectionInfoById.getResult().getReceiptImg()));
                                                mBuilder.setView(mView);

                                                final AlertDialog mDialog = mBuilder.create();
                                                cancel.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        mDialog.dismiss();
                                                    }
                                                });
                                                mDialog.show();
                                            }
                                        });
                                    }
                                }


                            }

                        }

                    }


                });
    }

    private JsonObject getComplaintrepobject() {
        GetComplaintRepositoryobject requestModel = new GetComplaintRepositoryobject();
        requestModel.setCode(selectedItemID);
        requestModel.setIsVideoRecording(null);

        return new Gson().toJsonTree(requestModel).getAsJsonObject();
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
        //chronometer.stop();

    }

    private void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(AudioURL);
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
        //chronometer.start();

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                imageViewPlay.setImageResource(R.drawable.ic_play);
                seekBar.setVisibility(View.GONE);
                isPlaying = false;
                //	chronometer.stop();
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mPlayer != null && fromUser) {
                    mPlayer.seekTo(progress);
                    //	chronometer.setBase(SystemClock.elapsedRealtime() - mPlayer.getCurrentPosition());
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





    @Override
    public int getItemCount() {

        if (list_complaints != null)
            return list_complaints.size();
        else
            return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView requestCode;
        public TextView req_date, close;
        public TextView statusType, cancel;
        public TextView PlotId, plot_size, location,reopen;
        LinearLayout details;

        public ViewHolder(View itemView) {
            super(itemView);


            requestCode = itemView.findViewById(R.id.requestCode);
            PlotId = itemView.findViewById(R.id.plotId);
            plot_size = itemView.findViewById(R.id.plot_size);
            location = itemView.findViewById(R.id.village_name);
            req_date = itemView.findViewById(R.id.reqCreatedDate);
            statusType = itemView.findViewById(R.id.statusType);
            reopen = itemView.findViewById(R.id.reopen);

            close = itemView.findViewById(R.id.close);
            details = itemView.findViewById(R.id.details);


        }


    }
}


