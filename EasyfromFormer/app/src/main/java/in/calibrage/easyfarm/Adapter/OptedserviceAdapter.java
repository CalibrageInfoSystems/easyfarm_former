package in.calibrage.easyfarm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.Common.AnimationUtil;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.Views.CartActivity;
import in.calibrage.easyfarm.Views.HomeActivity;
import in.calibrage.easyfarm.Views.Optedservices;
import in.calibrage.easyfarm.Views.Vendor_Services;
import in.calibrage.easyfarm.localData.SharedPrefsData;
import in.calibrage.easyfarm.model.AddUpdateService;
import in.calibrage.easyfarm.model.AddUpdateServiceresponse;
import in.calibrage.easyfarm.model.GetServiceOrdersByFarmerId;
import in.calibrage.easyfarm.model.GetUserInfoByUserCode;
import in.calibrage.easyfarm.model.LoginResponse;
import in.calibrage.easyfarm.service.ApiService;
import in.calibrage.easyfarm.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OptedserviceAdapter extends RecyclerView.Adapter<OptedserviceAdapter.ViewHolder> {

    List<GetServiceOrdersByFarmerId.ListResult> settings;
    public Context mContext;
    private Subscription mSubscription;
    LayoutInflater mInflater;

    int id;
    LoginResponse loginressponse;
String datetimevaluereq,serviceid,currentDate,serviced_date,quantity;
    public static final String DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss";
    public OptedserviceAdapter(List<GetServiceOrdersByFarmerId.ListResult> recomm_Set, Context context) {
        this.settings = recomm_Set;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.opted_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        loginressponse = SharedPrefsData.getCatagories(mContext);
        holder.text_category.setText(settings.get(position).getCategory());
        holder.txt_discription.setText(settings.get(position).getDescription());

        holder.txt_price.setText(mContext.getString(R.string.Rs) +settings.get(position).getPrice()+"");
        holder.quantity.setText(settings.get(position).getQuantity()+"");
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
        try {
          Date oneWayTripDate = input.parse(settings.get(position).getOrderDate());

       datetimevaluereq = output.format(oneWayTripDate);
            holder.date.setText(datetimevaluereq);

                } catch (ParseException e) {
            e.printStackTrace();
        }
        serviceid =settings.get(position).getServiceId()+"";
        serviced_date =settings.get(position).getOrderDate()+"";
        quantity =settings.get(position).getQuantity()+"";
        id = settings.get(position).getId();
        if (settings.get(position).getStatusTypeId() == 186) {
            holder.txt_status.setText(settings.get(position).getStatusType());
            holder.txt_status.setTextColor(mContext.getColor(R.color.green));
            holder.serviced.setVisibility(View.VISIBLE);
            holder.txt_imag.setVisibility(View.INVISIBLE);
        }
        else if(settings.get(position).getStatusTypeId() == 188) {
            holder.serviced.setVisibility(View.GONE);
            holder.txt_status.setText(settings.get(position).getStatusType());
            holder.txt_status.setTextColor(mContext.getColor(R.color.green));
            holder.txt_imag.setVisibility(View.INVISIBLE);

        }
        else{
            holder.serviced.setVisibility(View.GONE);
            holder.txt_status.setText(settings.get(position).getStatusType());
            holder.txt_status.setTextColor(mContext.getColor(R.color.flot_clr));
            holder.txt_imag.setVisibility(View.VISIBLE);
        }
        holder.serviced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AddUpdateServiceOrder(position);


                holder.serviced.setVisibility(View.GONE);




            }
        });


        //Picasso.with(mContext).load(settings.get(position).getIdentityProof()).error(R.drawable.ic_user).transform(new CircleTransform()).into(holder.id_image);
//
        Glide.with(mContext).load(settings.get(position).getImage()+"").into(holder.image_view);
        if (position % 2 == 0) {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.whitee));
        } else {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white2));

        }



        AnimationUtil.animate(holder, true);
    }

    private void AddUpdateServiceOrder(final int position) {
        final SpotsDialog mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(mContext)
                .setTheme(R.style.Custom)
                .build();
        mdilogue.show();
        JsonObject object = AddUpdateServiceobject();
        ApiService service = ServiceFactory.createRetrofitService(mContext, ApiService.class);
        mSubscription = service.postService(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AddUpdateServiceresponse>() {
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

                    public void onNext(AddUpdateServiceresponse addUpdateServiceresponse) {

                        mdilogue.dismiss();
                        if (addUpdateServiceresponse.getIsSuccess()) {
                            Toast.makeText(mContext, addUpdateServiceresponse.getEndUserMessage(), Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(mContext, Optedservices.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(i);

                        }

                        else {
                            Toast.makeText(mContext,addUpdateServiceresponse.getEndUserMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }


                });
    }

    private JsonObject AddUpdateServiceobject() {
        int userid =  loginressponse.getResult().getUserInfos().getId();
        Log.e("state===",userid+"");
        currentDate = new SimpleDateFormat(DATE_FORMAT1).format(Calendar.getInstance().getTimeInMillis());


        AddUpdateService requestModel = new AddUpdateService();
        requestModel.setId(id);
        requestModel.setServiceId(serviceid);
        requestModel.setStatusTypeId(188);
        requestModel.setQuantity(quantity);
        requestModel.setOrderDate(serviced_date);
        requestModel.setComments(null);
        requestModel.setIsActive(true);
        requestModel.setCreatedByUserId(userid);
        requestModel.setCreatedDate(currentDate);
        requestModel.setUpdatedByUserId(userid);
        requestModel.setUpdatedDate(currentDate);
        requestModel.setServerUpdatedStatus(true);

        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }

    @Override
    public int getItemCount() {
        return settings.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_category, txt_discription,txt_price,date,quantity,txt_status,serviced,txt_imag;
        ImageView image_view;
        CardView card_view;

        public ViewHolder(View itemView) {
            super(itemView);

            this.card_view = (CardView) itemView.findViewById(R.id.card_view);
            this.text_category =(TextView)itemView.findViewById(R.id.text_category);
            this.txt_discription =(TextView)itemView.findViewById(R.id.txt_discription);
            this.txt_price =(TextView) itemView.findViewById(R.id.txt_price);
            this.image_view =(ImageView)itemView.findViewById(R.id.image_view);
            this.date =(TextView) itemView.findViewById(R.id.date);
            this.txt_status =(TextView) itemView.findViewById(R.id.txt_status);
this.serviced =(TextView) itemView.findViewById(R.id.serviced);
            this.quantity =(TextView) itemView.findViewById(R.id.quantity);

            this.txt_imag =(TextView) itemView.findViewById(R.id.txt_imag);


        }


    }
}


