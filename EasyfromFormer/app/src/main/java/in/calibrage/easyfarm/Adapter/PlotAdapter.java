package in.calibrage.easyfarm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import in.calibrage.easyfarm.Common.AnimationUtil;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.Views.Complaints_Activity;
import in.calibrage.easyfarm.Views.Exit_Complaints_Activity;
import in.calibrage.easyfarm.model.GetPlotsByFarmerCode;

public class PlotAdapter extends RecyclerView.Adapter<PlotAdapter.ViewHolder> {

    List<GetPlotsByFarmerCode.ListResult> recomm_Set;
    public Context mContext;
    DecimalFormat df = new DecimalFormat("####0.00");


    public PlotAdapter(List<GetPlotsByFarmerCode.ListResult> recomm_Set, Context context) {
        this.recomm_Set = recomm_Set;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.plot_list_basic, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        plotcode,status,totalarea,adopted_area,gps_area,plot_ownership,country,state,district,mandal,village,address,
//                address2,passbook,servey_number,owner_contact_number,ownername;

        holder.plotcode.setText(recomm_Set.get(position).getCode());
        holder.status.setText(recomm_Set.get(position).getPlotStatus());
        holder.totalarea.setText(df.format(recomm_Set.get(position).getTotalPlotArea()) + " Acre");
        if (recomm_Set.get(position).getAdaptedArea() != null) {
            holder.adopted_area.setText(df.format(recomm_Set.get(position).getAdaptedArea()) + " Acre");
        }
        if (recomm_Set.get(position).getGPSPlotArea() != null){
            holder.gps_area.setText(df.format(recomm_Set.get(position).getGPSPlotArea()) + "  Acre");
        }
        holder.plot_ownership.setText( recomm_Set.get(position).getPlotOwnerShip());
        if(null != recomm_Set.get(position).getAddress2()) {
            holder.address.setText(recomm_Set.get(position).getAddress2() + "," + recomm_Set.get(position).getAddress1() + "," + recomm_Set.get(position).getVillageName() + ","
                    + recomm_Set.get(position).getMandalName() + "," + recomm_Set.get(position).getDistrictName() + "," + recomm_Set.get(position).getStateName() + "," + recomm_Set.get(position).getCountryName());

        }
        else{
            holder.address.setText(recomm_Set.get(position).getAddress1() + "," + recomm_Set.get(position).getVillageName() + ","
                    + recomm_Set.get(position).getMandalName() + "," + recomm_Set.get(position).getDistrictName() + "," + recomm_Set.get(position).getStateName() + "," + recomm_Set.get(position).getCountryName());

        }

//        holder.country.setText( recomm_Set.get(position).getCountryName());
//        holder.village.setText(recomm_Set.get(position).getVillageName());
//        holder.district.setText(recomm_Set.get(position).getDistrictName());
//        holder.mandal.setText(recomm_Set.get(position).getMandalName());
//        holder.state.setText( recomm_Set.get(position).getStateName());
//        holder.address.setText( recomm_Set.get(position).getAddress1());
//        holder.address2.setText( recomm_Set.get(position).getAddress2());
//
//        holder.passbook.setText(recomm_Set.get(position).getPassbookNumber());
//        holder.servey_number.setText( recomm_Set.get(position).getSurveyNumber());
//        if(recomm_Set.get(position).getOwnerName()!=null && !TextUtils.isEmpty(recomm_Set.get(position).getOwnerName()))
//            holder.ownername.setText( recomm_Set.get(position).getOwnerName());
//
//
//        else {
//            holder.lyt_ownername.setVisibility(View.GONE);
//            holder.owner_bottom_layout.setVisibility(View.GONE);
//        }
//
//        if(recomm_Set.get(position).getOwnerContactNumber()!=null && !TextUtils.isEmpty(recomm_Set.get(position).getOwnerContactNumber()))
//            holder.owner_contact_number.setText( recomm_Set.get(position).getOwnerContactNumber());
//        else{
//            holder.lyt_ownercontactnumer.setVisibility(View.GONE);
//            holder.owner_bottom_layout.setVisibility(View.GONE);}
        if (position % 2 == 0) {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white));
        } else {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white2));

        }
        holder.new_complaint.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Complaints_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("code",recomm_Set.get(position).getCode());
               intent.putExtra("totalarea",df.format(recomm_Set.get(position).getTotalPlotArea()) + " Acre");
                intent.putExtra("status",recomm_Set.get(position).getPlotStatus());
                intent.putExtra("ownership",recomm_Set.get(position).getPlotOwnerShip());

                mContext.startActivity(intent);

            }

        });
        holder.exit_complaint.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Exit_Complaints_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("code",recomm_Set.get(position).getCode());
                intent.putExtra("totalarea",df.format(recomm_Set.get(position).getTotalPlotArea()) + " Acre");
                intent.putExtra("status",recomm_Set.get(position).getPlotStatus());
                intent.putExtra("ownership",recomm_Set.get(position).getPlotOwnerShip());

                mContext.startActivity(intent);

            }

        });

        AnimationUtil.animate(holder, true);
    }

    @Override
    public int getItemCount() {
        return recomm_Set.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView plotcode,status,totalarea,adopted_area,gps_area,plot_ownership,country,state,district,mandal,village,address,
                address2,passbook,servey_number,owner_contact_number,ownername;
        LinearLayout lyt_plot_code,lyt_status,lyt_totalarea,lyt_Adapted,lyt_gps,lyt_plotownership,new_complaint,exit_complaint,owner_bottom_layout,soildetails;

        CardView card_view;
        public ViewHolder(View itemView) {
            super(itemView);
            this.plotcode = (TextView) itemView.findViewById(R.id.plotcode);
            this.status = (TextView) itemView.findViewById(R.id.status);
            this.totalarea = (TextView) itemView.findViewById(R.id.totalarea);
            this.adopted_area = (TextView) itemView.findViewById(R.id.adopted_area);
            this.gps_area = (TextView) itemView.findViewById(R.id.gps_area);
            this.plot_ownership = (TextView) itemView.findViewById(R.id.plot_ownership);
//            this.country = (TextView) itemView.findViewById(R.id.country);
//            this.state = (TextView) itemView.findViewById(R.id.state);
//            this.district = (TextView) itemView.findViewById(R.id.district);
//            this.mandal = (TextView) itemView.findViewById(R.id.mandal);
//            this.village = (TextView) itemView.findViewById(R.id.village);
           this.address = (TextView) itemView.findViewById(R.id.address);
//            this.address2 = (TextView) itemView.findViewById(R.id.address2);
//            this.passbook = (TextView) itemView.findViewById(R.id.passbook);
//            this.servey_number = (TextView) itemView.findViewById(R.id.servey_number);
//            this.owner_contact_number = (TextView) itemView.findViewById(R.id.owner_contact_number);
//            this.ownername = (TextView) itemView.findViewById(R.id.ownername);
            // this.address = (TextView) itemView.findViewById(R.id.address);

            this.card_view = (CardView) itemView.findViewById(R.id.card_view);


            this.lyt_plot_code =(LinearLayout)itemView.findViewById(R.id.lyt_plot_code) ;
            this.lyt_status =(LinearLayout)itemView.findViewById(R.id.lyt_status) ;
            this.lyt_totalarea =(LinearLayout)itemView.findViewById(R.id.lyt_totalarea) ;
            this.lyt_Adapted =(LinearLayout)itemView.findViewById(R.id.lyt_Adapted) ;
            this.lyt_gps =(LinearLayout)itemView.findViewById(R.id.lyt_gps) ;
            this.lyt_plotownership =(LinearLayout)itemView.findViewById(R.id.lyt_plotownership) ;
            this.lyt_plot_code =(LinearLayout)itemView.findViewById(R.id.lyt_plot_code) ;
            this.lyt_plot_code =(LinearLayout)itemView.findViewById(R.id.lyt_plot_code) ;
         this.new_complaint =(LinearLayout)itemView.findViewById(R.id.new_complaint) ;
           this.exit_complaint =(LinearLayout)itemView.findViewById(R.id.exit_complaint) ;
//            this.owner_bottom_layout =(LinearLayout)itemView.findViewById(R.id.owner_bottom_layout) ;
//
//            this.soildetails =(LinearLayout)itemView.findViewById(R.id.soildetails) ;


        }


    }
}


