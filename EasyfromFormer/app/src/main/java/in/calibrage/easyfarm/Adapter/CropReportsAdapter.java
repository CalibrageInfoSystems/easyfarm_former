package in.calibrage.easyfarm.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import in.calibrage.easyfarm.Common.CommonUtil;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.Views.PlayerActivity;
import in.calibrage.easyfarm.localData.SharedPrefsData;
import in.calibrage.easyfarm.model.Complaintobject;
import in.calibrage.easyfarm.model.GetComplaintRepositoryResponse;
import in.calibrage.easyfarm.model.GetComplaintRepositoryobject;
import in.calibrage.easyfarm.model.GetComplaintsByPlotCode;
import in.calibrage.easyfarm.model.GetCropCycleDailyUploadresponse;
import in.calibrage.easyfarm.model.LoginResponse;
import in.calibrage.easyfarm.model.ResponseComplaint;
import in.calibrage.easyfarm.model.date_object;
import in.calibrage.easyfarm.service.ApiService;
import in.calibrage.easyfarm.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CropReportsAdapter extends RecyclerView.Adapter<CropReportsAdapter.ViewHolder> {

   // List<GetCropCycleDailyUploadresponse.ListResult> list_complaints;
    public Context mContext;
    String datetimevaluereq, currentDate;
    String cropcode;
    int selectedPO;
    Button ok_btn;
    String AudioURL, imageur1;
    LinearLayout linearLayout_Play;
    Button submitBtn;
    //	private Chronometer chronometer;
    private List<String> image_list = new ArrayList<String>();

    LinearLayout voice_layout;
    int row_index = -1;
    private boolean isPlaying = false;
    public static final String DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss";
    DecimalFormat df = new DecimalFormat("####0.00");
    private Subscription mSubscription;
    LayoutInflater mInflater;
    LoginResponse loginressponse;
    List<String> list_reports =new ArrayList<>();
    // private List<GetCropCycleDailyUploadresponse.ListResult> contactListFiltered;
    public CropReportsAdapter(List<String> images,String videourl,String cropcode, Context ctx) {
        this.list_reports = images;
        this.AudioURL =videourl;
        this.cropcode =cropcode;
        this.mContext = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.uploading_data, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

holder.cropcode.setText(cropcode);
     //   try {
//        final String videoId = CommonUtil.extractYoutubeId(AudioURL);
//            //  final String videoname = listResultVideo.get(position).getName();
//            Log.e("VideoId is->", "" + videoId);
//            if(videoId == null){
//                holder.webvideo_layout2.setVisibility(View.GONE);
//            }
//            String img_url = "http://img.youtube.com/vi/" + videoId + "/0.jpg"; // this is link which will give u thumnail image of that video
//            Picasso.with(mContext)
//                    .load(img_url)
//                    .placeholder(R.drawable.easy_farm_logo)
//                    .into(holder.iv_youtube_thumnail);
//                            holder.txt_name.setText(listResultVideo.get(position).getName());
//                            holder.txt_desc.setText(listResultVideo.get(position).getDescription());
//                            if (listResultVideo.size() == 0) {
//                                Log.e("no====", "videos==");
//                            } else {
//                                Log.e("==========", "videos==");
//                            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, PlayerActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    // intent.putExtra("videoid", videoId);
                    intent.putExtra("name", "Crop Video");
                    mContext.startActivity(intent);
                }
            });

//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

            final List<String> img = new ArrayList<>();
            if (list_reports!= null) {

                for (int i = 0; i < list_reports.size(); i++) {



                     ///  voice_layout.setVisibility(View.GONE);
                        img.add(list_reports.get(i));

//
                    }
//

                    for (int j = 0; j < img.size(); j++) {
                        final   int finalJ =j;
                        if (j == 0) {
                            holder.iv1.setVisibility(View.VISIBLE);
                            // Picasso.with(mContext).load(img.get(j)).error(R.drawable.ic_user).into(iv1);
                            Glide.with(mContext).load(img.get(j)).error(R.drawable.ic_user).into(holder.iv1);

                            holder.iv1.setOnClickListener(new View.OnClickListener() {
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
                            holder.iv2.setVisibility(View.VISIBLE);
                            //  Picasso.with(mContext).load(img.get(j)).error(R.drawable.ic_user).into(iv2);
                            Glide.with(mContext).load(img.get(j)).error(R.drawable.ic_user).into(holder.iv2);

                            holder.iv2.setOnClickListener(new View.OnClickListener() {
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
                            holder.iv3.setVisibility(View.VISIBLE);
                            // Picasso.with(mContext).load(img.get(j)).error(R.drawable.ic_user).into(iv3);
                            Glide.with(mContext).load(img.get(j)).error(R.drawable.ic_user).into(holder.iv3);
                            holder.iv3.setOnClickListener(new View.OnClickListener() {
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
                                //    Picasso.with(mContext).load(img.get(finalJ)).error(R.drawable.ic_user).into(photoView);
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















    @Override
    public int getItemCount() {

        if (list_reports != null)
            return 1;
        else
            return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView cropcode;
        public TextView filetype, date;
        public TextView statusType, cancel;
        public TextView PlotId, plot_size, location,reopen;
        LinearLayout details,voice_layout;
        ImageView iv_youtube_thumnail;
        TextView txt_name, txt_desc;
        RelativeLayout webvideo_layout2;
        public   ImageView iv1, iv2, iv3, imageViewPlay;
        public ViewHolder(View itemView) {
            super(itemView);


            cropcode = itemView.findViewById(R.id.cropcode);
         //   filetype = itemView.findViewById(R.id.filetype);
            date = itemView.findViewById(R.id.date);
            iv1 = (ImageView) itemView.findViewById(R.id.iv);
            iv2 = (ImageView) itemView.findViewById(R.id.iv2);
            iv3 = (ImageView) itemView.findViewById(R.id.iv3);
            webvideo_layout2 =(RelativeLayout) itemView.findViewById(R.id.webvideo_layout2);
            iv_youtube_thumnail = itemView.findViewById(R.id.img_thumnail);

            iv1.setVisibility(View.GONE);
            iv2.setVisibility(View.GONE);
            iv3.setVisibility(View.GONE);
//            location = itemView.findViewById(R.id.village_name);
//            req_date = itemView.findViewById(R.id.reqCreatedDate);
//            statusType = itemView.findViewById(R.id.statusType);
//            reopen = itemView.findViewById(R.id.reopen);
//
//            close = itemView.findViewById(R.id.close);
      //     voice_layout = itemView.findViewById(R.id.webvideo_layout2);


        }


    }
}



