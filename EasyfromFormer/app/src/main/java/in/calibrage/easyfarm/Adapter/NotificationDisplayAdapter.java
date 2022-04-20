package in.calibrage.easyfarm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.Views.Vendor_Services;
import in.calibrage.easyfarm.localData.SharedPrefsData;
import in.calibrage.easyfarm.model.GetVendorServices;
import in.calibrage.easyfarm.model.LoginResponse;
import in.calibrage.easyfarm.model.ReadNotificationById;
import in.calibrage.easyfarm.model.getNotificationResponse;
import in.calibrage.easyfarm.service.APIConstantURL;
import in.calibrage.easyfarm.service.ApiService;
import in.calibrage.easyfarm.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NotificationDisplayAdapter extends RecyclerView.Adapter<NotificationDisplayAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    public static String codee;
    Context context;
    LoginResponse loginressponse;
    int User_id;
    int notificationId;


    String datetimevaluereq;
    private SpotsDialog mdilogue;
    private Subscription mSubscription;

    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
    private static final String LOG_TAG = NotificationDisplayAdapter.class.getName();
    String timeStamp;
    public static final String DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss";
    List<getNotificationResponse.ListResult> notifications;
    int row_index = -1;
//     int row_index_old = -1;

    public NotificationDisplayAdapter(Context ctx, List<getNotificationResponse.ListResult> data) {
        this.layoutInflater = LayoutInflater.from(ctx);
        this.context = ctx;
        this.notifications = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.noti_display_item, parent, false);

//        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
//                .setContext(context)
//                .setTheme(R.style.Custom)
//                .build();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        timeStamp = new SimpleDateFormat(DATE_FORMAT1).format(Calendar.getInstance().getTimeInMillis());
        Log.e("timeStamp====", timeStamp);
        try {
            Date oneWayTripDate = input.parse(notifications.get(position).getCreatedDate());
            Boolean isRead = notifications.get(position).getIsRead();
            Log.d("notification===", isRead + "");
            if (notifications.get(position).getIsRead() == true) {
                holder.createdDateTextView.setTextColor(Color.parseColor("#a6a6a6"));//gray
            } else {
                holder.createdDateTextView.setTextColor(Color.parseColor("#1CD6EC"));

            }
            datetimevaluereq = output.format(oneWayTripDate);

//
            Log.e("===============", "data=" + notifications.get(position).getNotificationTypeId() + notifications.get(position).getCreatedByUserId());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.headerTextView.setText(notifications.get(position).getHeader() + "");
        holder.descriptionTextView.setText(notifications.get(position).getDesc());

        holder.createdDateTextView.setText(datetimevaluereq);
        if (row_index == position) {
            holder.descriptionTextView.setMaxLines(5);
            holder.headerTextView.setMaxLines(5);
            // holder.createdDateTextView.setVisibility(View.VISIBLE);

        } else {
            holder.descriptionTextView.setMaxLines(1);
            holder.headerTextView.setMaxLines(1);
            //  holder.createdDateTextView.setVisibility(View.GONE);
        }


        final int h = holder.card_view.getHeight();
        holder.card_view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                int oldindex = row_index;
                row_index = position;
                notifyItemChanged(oldindex);
                notifyItemChanged(position);
                Log.d("oldindex", oldindex + "");
                Log.d("position", position + "");
                notificationId = notifications.get(position).getId();

                loginressponse = SharedPrefsData.getCatagories(context);
                User_id =  loginressponse.getResult().getUserInfos().getId();

                ApiService service = ServiceFactory.createRetrofitService(context, ApiService.class);
                mSubscription = service.readNnotification(APIConstantURL.readnotificationById+User_id+"/"+notificationId)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ReadNotificationById>() {
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
                            public void onNext(ReadNotificationById readNotificationById) {
                                if (readNotificationById.getIsSuccess() == true ) {

                                    notifications.get(position).setIsRead(true);
                                    notifyItemChanged(position);
                                }
                            }

                        });






            }

        });
    }

    @Override
    public int getItemCount() {

        return notifications.size();
    }

    private void GetVendorServicesByPlotCode() {



    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView headerTextView;
        TextView descriptionTextView;
        TextView createdDateTextView;
        ImageView thumbnail;
        CardView card_view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            headerTextView = (TextView) itemView.findViewById(R.id.notiHeaderTxt);
            descriptionTextView = (TextView) itemView.findViewById(R.id.notiDesc);
            createdDateTextView = (TextView) itemView.findViewById(R.id.notiDate);
            thumbnail = (ImageView) itemView.findViewById(R.id.imageView);
            card_view = (CardView) itemView.findViewById(R.id.card_view);
        }

    }


}
