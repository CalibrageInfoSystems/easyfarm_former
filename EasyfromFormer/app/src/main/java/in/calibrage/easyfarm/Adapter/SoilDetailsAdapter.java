package in.calibrage.easyfarm.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.BuildConfig;
import in.calibrage.easyfarm.Common.AnimationUtil;
import in.calibrage.easyfarm.Common.FileDownloader;
import in.calibrage.easyfarm.Fragments.TabFragment;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.model.getSoiltestreports;

public class SoilDetailsAdapter extends RecyclerView.Adapter<SoilDetailsAdapter.ViewHolder> {

    List<getSoiltestreports.SoilTestDetail> recomm_Set;
    public Context mContext;
    DecimalFormat df = new DecimalFormat("####0.00");

    private String name, url;
    public SoilDetailsAdapter(List<getSoiltestreports.SoilTestDetail> recomm_Set, Context context) {
        this.recomm_Set = recomm_Set;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.soillist, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.plotcode.setText(recomm_Set.get(position).getPlotCode());
      //  holder.Nitrogen.setText(df.format(recomm_Set.get(position).getNitrogen()+" %"));
        holder.Nitrogen.setText(df.format(recomm_Set.get(position).getNitrogen()) + " %");
       holder.Prosperous.setText(df.format(recomm_Set.get(position).getProsperous()) + " %");
        holder.Potassium.setText(df.format(recomm_Set.get(position).getPotassium()) + " %");
        holder.Carbon.setText(df.format(recomm_Set.get(position).getCarbon()) + " %");
        holder.Hydrogen.setText(df.format(recomm_Set.get(position).getHydrogen()) + " %");
        holder.Oxygen.setText(df.format(recomm_Set.get(position).getOxygen()) + " %");
        holder.Sulphur.setText(df.format(recomm_Set.get(position).getSulphur()) + " %");
        holder.Calcium.setText(df.format(recomm_Set.get(position).getCalcium()) + " %");
        holder.Magnesium.setText(df.format(recomm_Set.get(position).getMagnesium()) + " %");
        if(recomm_Set.get(position).getCalcium()==145) {
            holder.soilculutetype.setText("Articulture");
        }
        else{
            holder.soilculutetype.setText("Agriculture");
        }
     //   Glide.with(mContext).load(recomm_Set.get(position).getImage()+"").into(holder.soiltestimage);
        if (position % 2 == 0) {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white));
        } else {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white2));

        }

        holder.soiltestimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                url =recomm_Set.get(position).getImage();
                name=recomm_Set.get(position).getFileName();

                    new DownloadFilee().execute(url,name + ".pdf");
                    Log.e("url==========", url);


            }
        });
//        holder.soil_reports.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mContext, Soiltesr_reports.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("code",recomm_Set.get(position).getCode());
//                intent.putExtra("Farmercode",recomm_Set.get(position).getFarmerCode());
//                mContext.startActivity(intent);
//
//            }
//
//        });

        AnimationUtil.animate(holder, true);
    }

    @Override
    public int getItemCount() {
        return recomm_Set.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView plotcode,Nitrogen,Prosperous,Potassium,Carbon,Hydrogen,Oxygen,Sulphur,Calcium,Magnesium,village,soilculutetype,
                address2,passbook,servey_number,owner_contact_number,ownername;
    //    LinearLayout lyt_plot_code,lyt_status,lyt_totalarea,lyt_Adapted,lyt_gps,lyt_plotownership,lyt_ownercontactnumer,lyt_ownername,owner_bottom_layout;
ImageView soiltestimage;
        CardView card_view;
        public ViewHolder(View itemView) {
            super(itemView);
            this.plotcode = (TextView) itemView.findViewById(R.id.plotcode);
            this.Nitrogen= itemView.findViewById(R.id.nitrogen);
            this.Prosperous= itemView.findViewById(R.id.Prosperous);
            this.Potassium= itemView.findViewById(R.id.Potassium);
            this.Carbon= itemView.findViewById(R.id.Carbon);
            this.Hydrogen= itemView.findViewById(R.id.Hydrogen);
            this.Oxygen= itemView.findViewById(R.id.Oxygen);
            this.Sulphur= itemView.findViewById(R.id.Sulphur);
            this.Calcium=itemView.findViewById(R.id.Calcium);
            this. Magnesium= itemView.findViewById(R.id.Magnesium);
          this.soilculutetype = (TextView) itemView.findViewById(R.id.soilculutetype);
this.soiltestimage=(ImageView) itemView.findViewById(R.id.soilcertificate);
            this.card_view = (CardView) itemView.findViewById(R.id.card_view);


//            this.lyt_plot_code =(LinearLayout)itemView.findViewById(R.id.lyt_plot_code) ;
//            this.lyt_status =(LinearLayout)itemView.findViewById(R.id.lyt_status) ;
//            this.lyt_totalarea =(LinearLayout)itemView.findViewById(R.id.lyt_totalarea) ;
//            this.lyt_Adapted =(LinearLayout)itemView.findViewById(R.id.lyt_Adapted) ;
//            this.lyt_gps =(LinearLayout)itemView.findViewById(R.id.lyt_gps) ;
//            this.lyt_plotownership =(LinearLayout)itemView.findViewById(R.id.lyt_plotownership) ;
//            this.lyt_plot_code =(LinearLayout)itemView.findViewById(R.id.lyt_plot_code) ;
//            this.lyt_plot_code =(LinearLayout)itemView.findViewById(R.id.lyt_plot_code) ;
//            this.lyt_ownercontactnumer =(LinearLayout)itemView.findViewById(R.id.lyt_owner_contact_number) ;
//            this.lyt_ownername =(LinearLayout)itemView.findViewById(R.id.lyt_owner_name) ;
//            this.owner_bottom_layout =(LinearLayout)itemView.findViewById(R.id.owner_bottom_layout) ;
//



        }


    }

    private class DownloadFilee extends AsyncTask<String, String, String> {

        private String resp;
        //   ProgressDialog progressDialog;

        SpotsDialog mdiloguee = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(mContext)
                .setTheme(R.style.Custom)
                .build();
        @Override
        protected String doInBackground(String... strings) {
            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
         //   String fileName = strings[1];  // -> maven.pdf
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "EasyFarm");
            folder.mkdir();

            File pdfFile = new File(folder,name+".pdf");

            try {
                pdfFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileDownloader.downloadFile(fileUrl, pdfFile);
            return null;
        }



        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            mdiloguee.dismiss();


            File pdfFile = new File(Environment.getExternalStorageDirectory() + "/EasyFarm/" + name+".pdf");

            Log.e("pdfFile===========",pdfFile+"");// -> filename = maven.pdf
            //Uri path = Uri.fromFile(pdfFile);
            Uri path = FileProvider.getUriForFile(mContext, BuildConfig.APPLICATION_ID + ".provider", pdfFile);
            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
            //
            pdfIntent.setDataAndType(path, "application/pdf");
            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            try {
                mContext.startActivity(pdfIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(mContext, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW);


                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.adobe.reader"));

//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(
//                        "https://play.google.com/store/apps/details?id=com.microsoft.office.excel"));
//                intent.setPackage("com.android.vending");
//                startActivity(intent);
                mContext.startActivity(intent);
            }
            //finalResult.setText(result);
        }


        @Override
        protected void onPreExecute() {
            mdiloguee.show();
//            mdiloguee = ProgressDialog.show(getActivity(),
//                    "ProgressDialog",
//                    "Wait for "+ " seconds");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            //finalResult.setText(text[0]);

        }
    }
}


