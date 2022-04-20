package in.calibrage.easyfarm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import in.calibrage.easyfarm.Common.AnimationUtil;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.Views.Services_details;
import in.calibrage.easyfarm.Views.Vendor_Services;
import in.calibrage.easyfarm.model.GetCropCycleDailyUploadresponse;
import in.calibrage.easyfarm.model.GetVendorServices;

public class VendorserviceAdapter extends RecyclerView.Adapter<VendorserviceAdapter.ViewHolder> {

    List<GetVendorServices.ListResult> recomm_Set;
    public Context mContext;
    DecimalFormat df = new DecimalFormat("####0.00");
    List<String> imageslist =new ArrayList<>();
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private Vendorservicelistener listener;
    public VendorserviceAdapter(List<GetVendorServices.ListResult> recomm_Set, Vendorservicelistener listener,Context context) {
        this.recomm_Set = recomm_Set;
        this.listener =listener;
        this.mContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.vendorservices, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
//        try {
//       //     Date oneWayTripDate = input.parse(recomm_Set.get(position).getComplaintDate());
//
//          //  datetimevaluereq = output.format(oneWayTripDate);
//
//
//         //       } catch (ParseException e) {
////            e.printStackTrace();
////        }  Log.e("===============", "======currentData======" + output.format(oneWayTripDate));
//

      //  holder.ServiceName.setText(recomm_Set.get(position).getServiceName());
        holder.discription.setText(recomm_Set.get(position).getDescription());
        holder.Category.setText(recomm_Set.get(position).getVendorCategoryName());
        Glide.with(mContext).load(recomm_Set.get(position).getVendorServiceFiles().get(0).getImage()).error(R.drawable.ic_user).into(holder.image);
//        holder.Sub_Category.setText(recomm_Set.get(position).getVendorSubCategoryName());
//        holder.Brand_Name.setText(recomm_Set.get(position).getBrandName());
//        if(recomm_Set.get(position).getSize()==null){
//            holder.lyt_size.setVisibility(View.GONE);
//            holder.lin3.setVisibility(View.GONE);
//        }else{
//            holder.size.setText(recomm_Set.get(position).getSize()+"");
//        }
//
//        holder.uom.setText(recomm_Set.get(position).getUOMType());

       // holder.price.setText(mContext.getString(R.string.Rs) +df.format(recomm_Set.get(position).getPrice()+""));
        holder.price.setText(mContext.getString(R.string.Rs) +recomm_Set.get(position).getPrice()+"");
//
        if(recomm_Set.get(position).getDiscount()== null){
            holder.discount.setVisibility(View.GONE);
        }
        else {
            holder.discount.setText(recomm_Set.get(position).getDiscount()+" % \n off");
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                listener.onitemSelected(recomm_Set.get(position));
                //  getTermsheet(plotcode);

//

            }

        });
//
//        if(recomm_Set.get(position).getContactPerson()== null){
//            holder.lyt_contactname.setVisibility(View.GONE);
//            holder.lin2.setVisibility(View.GONE);
//        }
//        else {
//
//
//            holder.Contact_person.setText(recomm_Set.get(position).getContactPerson() + "");
//        }
//        if(recomm_Set.get(position).getContactNumber()== null){
//            holder.lyt_contactnum.setVisibility(View.GONE);
//            holder.lin.setVisibility(View.GONE);
//        }
//        else {
//
//
//            holder.Contact_num.setText(recomm_Set.get(position).getContactNumber() + "");
//        }
//
//
//        if(recomm_Set.get(position).getOpeningTime()== null){
//            holder.lyt_open_time.setVisibility(View.GONE);
//            holder.lin5.setVisibility(View.GONE);
//        }
//        else {
//
//            holder.open_time.setText(recomm_Set.get(position).getOpeningTime() + "");
//        }
//
//        if(recomm_Set.get(position).getClosingTime()== null){
//            holder.lyt_closetime.setVisibility(View.GONE);
//            holder.lin6.setVisibility(View.GONE);
//        }
//        else {
//
//            holder.closing_time.setText(recomm_Set.get(position).getClosingTime() + "");
//        }
//        holder.address.setText( recomm_Set.get(position).getVillage() + ","
//                + recomm_Set.get(position).getMandal() + "," + recomm_Set.get(position).getDistrict() + "," + recomm_Set.get(position).getState() + "," + recomm_Set.get(position).getContactPerson());
//        if(recomm_Set.get(position).getComments()== null){
//            holder.lyt_comments.setVisibility(View.GONE);
//            holder.lin7.setVisibility(View.GONE);
//        }
//        else {
//
//            holder.comments.setText(recomm_Set.get(position).getComments()+"");
//        }
//
//        holder.status.setText(recomm_Set.get(position).getStatus());
//        LinearLayoutManager layoutManager = new LinearLayoutManager(
//                holder.images_item.getContext(),
//                LinearLayoutManager.VERTICAL,
//                false
//        );
//        layoutManager= new GridLayoutManager(mContext, 3);
//
//        ImageAdapter subItemAdapter = new ImageAdapter(mContext ,recomm_Set.get(position).getVendorServiceFiles());
//
//
//        holder.images_item.setLayoutManager(layoutManager);
//        holder.images_item.setAdapter(subItemAdapter);
//        holder.images_item.setRecycledViewPool(viewPool);

//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
//        leaning_recycle.setLayoutManager(mLayoutManager);
//        leaning_recycle.setItemAnimator(new DefaultItemAnimator());
//        // adding inbuilt divider line
//        leaning_recycle.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
//        leaning_recycle.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 30));
//



//        holder.req_date.setText(datetimevaluereq);
//        holder.status.setText(list_complaints.get(position).getPlotCode());


        AnimationUtil.animate(holder, true);
    }

    @Override
    public int getItemCount() {
        return recomm_Set.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView ServiceName,status,Category,Sub_Category,gps_area,Brand_Name,country,state,district,mandal,village,address,
                address2,passbook,Contact_person,Contact_num,discription,size,uom,price,discount,open_time,closing_time,comments;
        LinearLayout lyt_plot_code,lyt_status,lyt_totalarea,lyt_Adapted,lyt_gps,lyt_plotownership,new_complaint,exit_complaint,owner_bottom_layout,
                soildetails,lyt_contactname,lyt_contactnum,lin,lin2,lyt_size,lin3,lin4,lin5,lin6,lin7,lyt_discount,lyt_open_time,lyt_closetime,lyt_comments;

        ImageView image;
RecyclerView images_item;
        CardView card_view;
        public ViewHolder(View itemView) {
            super(itemView);
           // this.ServiceName = (TextView) itemView.findViewById(R.id.ServiceName);
            this.discription = (TextView) itemView.findViewById(R.id.discription);
            this.image =(ImageView)itemView.findViewById(R.id.imageView);
            this.Category = (TextView) itemView.findViewById(R.id.Category);
            this.price = (TextView) itemView.findViewById(R.id.price);
           this.discount = (TextView) itemView.findViewById(R.id.discount);
//            this.Sub_Category = (TextView) itemView.findViewById(R.id.Sub_Category);
//            this.Brand_Name = (TextView) itemView.findViewById(R.id.Brand_Name);
//            this.size = (TextView) itemView.findViewById(R.id.size);
//            this.status = (TextView) itemView.findViewById(R.id.status);
//            this.uom = (TextView) itemView.findViewById(R.id.uom);
//            this.price = (TextView) itemView.findViewById(R.id.price);
//            this.discount = (TextView) itemView.findViewById(R.id.discount);
//            this.Contact_person = (TextView) itemView.findViewById(R.id.Contact_person);
//            this.Contact_num = (TextView) itemView.findViewById(R.id.Contact_num);
//            this.open_time = (TextView) itemView.findViewById(R.id.open_time);
//            this.closing_time = (TextView) itemView.findViewById(R.id.closing_time);
////
//            this.address = (TextView) itemView.findViewById(R.id.address);
//            this.comments = (TextView) itemView.findViewById(R.id.comments);
//            this.card_view = (CardView) itemView.findViewById(R.id.card_view);
//
//
//            this.lyt_plot_code =(LinearLayout)itemView.findViewById(R.id.lyt_plot_code) ;
//            this.lyt_status =(LinearLayout)itemView.findViewById(R.id.lyt_status) ;
//            this.lyt_totalarea =(LinearLayout)itemView.findViewById(R.id.lyt_totalarea) ;
//            this.lyt_Adapted =(LinearLayout)itemView.findViewById(R.id.lyt_Adapted) ;
//            this.lin2 =(LinearLayout)itemView.findViewById(R.id.lin2) ;
//            this.lyt_plotownership =(LinearLayout)itemView.findViewById(R.id.lyt_plotownership) ;
//            this.lin =(LinearLayout)itemView.findViewById(R.id.lin) ;
//            this.lin3 =(LinearLayout)itemView.findViewById(R.id.lin3) ;
//            this.lyt_size =(LinearLayout)itemView.findViewById(R.id.lyt_size) ;
//            this.lyt_contactnum =(LinearLayout)itemView.findViewById(R.id.lyt_contactnum) ;
//            this.lyt_contactname =(LinearLayout)itemView.findViewById(R.id.lyt_contactname) ;
//           this.images_item =(RecyclerView) itemView.findViewById(R.id.images_item) ;
//            this.lin4 =(LinearLayout)itemView.findViewById(R.id.lin4) ;
//            this.lin5 =(LinearLayout)itemView.findViewById(R.id.lin5) ;
//            this.lin6 =(LinearLayout)itemView.findViewById(R.id.lin6) ;
//            this.lin7 =(LinearLayout)itemView.findViewById(R.id.lin7) ;
//            this.lyt_discount =(LinearLayout)itemView.findViewById(R.id.lyt_discount);
//            this.lyt_open_time  = (LinearLayout)itemView.findViewById(R.id.lyt_open_time);
//            this.lyt_closetime  = (LinearLayout)itemView.findViewById(R.id.lyt_closetime);
//            this.lyt_comments =(LinearLayout)itemView.findViewById(R.id.lyt_comments);
//
//            this.soildetails =(LinearLayout)itemView.findViewById(R.id.soildetails) ;


        }


    }

    public interface Vendorservicelistener {
        void onitemSelected(GetVendorServices.ListResult service_detsils);
    }
}


