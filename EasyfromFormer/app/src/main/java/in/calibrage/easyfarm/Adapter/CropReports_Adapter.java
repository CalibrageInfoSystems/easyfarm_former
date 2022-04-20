package in.calibrage.easyfarm.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.Views.PreviousReports;
import in.calibrage.easyfarm.model.GetCropCycleDailyUploadresponse;
import in.calibrage.easyfarm.model.date_object;

public class CropReports_Adapter extends RecyclerView.Adapter<CropReports_Adapter.ViewHolder> {

        List<GetCropCycleDailyUploadresponse.ListResult> list_complaints;
public Context mContext;
        String datetimevaluereq, currentDate;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        String selectedItemID;
        int selectedPO;
        Button ok_btn;
        String AudioURL, imageur1;
        LinearLayout linearLayout_Play;
        Button submitBtn;
//	private Chronometer chronometer;
private List<String> image_list = new ArrayList<String>();

    private List<String> datelist = new ArrayList<String>();

    public CropReports_Adapter(List<String> list_loan,List<GetCropCycleDailyUploadresponse.ListResult> datalist, Context ctx) {
        this.datelist = list_loan;
        this.list_complaints =datalist;
        this.mContext = ctx;
    }
@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.uploading_dataa, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
        }



    @Override
public void onBindViewHolder(final ViewHolder holder, final int position) {

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date oneWayTripDate = input.parse(datelist.get(position));

            datetimevaluereq = output.format(oneWayTripDate);
            holder.tvItemTitle.setText(datetimevaluereq);

            Log.e("===============", "======currentData======" + output.format(oneWayTripDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }


  //  holder.tvItemTitle.setText(list_complaints.get(position).getCycleCode());
 //  holder.tvItemTitle.setText(datetimevaluereq);


        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rvSubItem.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );


//   layoutManager.setInitialPrefetchItemCount(list_complaints.size());

        // Create sub item view adapter
        List<GetCropCycleDailyUploadresponse.ListResult> sublist =new ArrayList<>();
        List<String> imageslist =new ArrayList<>();
        String videourl ="";
        String cropcode = "";
        for (GetCropCycleDailyUploadresponse.ListResult item:list_complaints
             ) {
            Log.d("Anlysis============>", "curreent date :"+item.getPostedOn()+" && recv exist date Compare date :"+datelist.get(position));
            if(item.getPostedOn().equalsIgnoreCase(datelist.get(position)) )
            {
                cropcode=item.getCycleCode();

                if(item.getFileTypeId() == 113)
                {
                    videourl = item.getVideoEmbedUrl();

                }else{
                    imageslist.add(item.getFileUrl());
                }


                Log.d("Anlysis============>","Date Compatered added to list");
                //print log
            }else
            {
                Log.d("Anlysis============>","Date not compared andd not added to list");
              //  print  log
            }
        }

        CropReportsAdapter subItemAdapter = new CropReportsAdapter(imageslist,videourl,cropcode,mContext );

        holder.rvSubItem.setLayoutManager(layoutManager);
        holder.rvSubItem.setAdapter(subItemAdapter);
        holder.rvSubItem.setRecycledViewPool(viewPool);

    //holder.filetype.setText(list_complaints.get(position).getDesc());

    // currentDate = new SimpleDateFormat(DATE_FORMAT1).format(Calendar.getInstance().getTimeInMillis());

}




//        if ("Resolution Provided".equals(holder.statusType.getText())) {
//            // if ("Open".equals(holder.statusType.getText())) {
//
//            holder.close.setVisibility(View.VISIBLE);
//            holder.reopen.setVisibility(View.VISIBLE);
//
//        } else {
//            holder.close.setVisibility(View.GONE);
//            holder.reopen.setVisibility(View.GONE);
//
//        }










@Override
public int getItemCount() {

        if (datelist != null)
        return datelist.size();
        else
        return 0;
        }


public static class ViewHolder extends RecyclerView.ViewHolder {


    private TextView tvItemTitle;
    private RecyclerView rvSubItem;

    public ViewHolder(View itemView) {
        super(itemView);
        tvItemTitle = itemView.findViewById(R.id.tv_item_title);
        rvSubItem = itemView.findViewById(R.id.rv_sub_item);
    }

//            location = itemView.findViewById(R.id.village_name);
//            req_date = itemView.findViewById(R.id.reqCreatedDate);
//            statusType = itemView.findViewById(R.id.statusType);
//            reopen = itemView.findViewById(R.id.reopen);
//
//            close = itemView.findViewById(R.id.close);
//            details = itemView.findViewById(R.id.details);


    }


}





