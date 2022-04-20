package in.calibrage.easyfarm.Adapter;

import android.Manifest;
import android.app.Activity;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.BuildConfig;
import in.calibrage.easyfarm.Common.AnimationUtil;
import in.calibrage.easyfarm.Common.FileDownloader;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.Views.daily_reports;
import in.calibrage.easyfarm.Views.insurance_details;
import in.calibrage.easyfarm.model.getSoiltestreports;

public class CropDetailsAdapter extends RecyclerView.Adapter<CropDetailsAdapter.ViewHolder> {

    List<getSoiltestreports.CropCycleInfo> recomm_Set;
    public Context mContext;
    DecimalFormat df = new DecimalFormat("####0.00");
    private String name, fileurl;;

    public CropDetailsAdapter(List<getSoiltestreports.CropCycleInfo> recomm_Set, Context context) {
        this.recomm_Set = recomm_Set;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.croplist, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        requestMultiplePermissions();
        holder.cropcode.setText(recomm_Set.get(position).getCycleCode());
        holder.crop.setText(recomm_Set.get(position).getCropName());
        holder.seed.setText(recomm_Set.get(position).getSeedName());
        holder.status.setText(recomm_Set.get(position).getStatus());
        //   Glide.with(mContext).load(recomm_Set.get(position).getImage()+"").into(holder.soiltestimage);
        if (position % 2 == 0) {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white));
        } else {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white2));

        }


        holder.termsheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                fileurl = recomm_Set.get(position).getTermSheet();
                name = recomm_Set.get(position).getFileName();

                if(fileurl!=null ){

                    new DownloadFilee().execute(fileurl, name + ".xlsx");
                    Log.e("url==========", fileurl);
                } else {

                    Toast.makeText(mContext, "TermSheet not available to this Plot ", Toast.LENGTH_SHORT).show();


                }

            }
        });
        holder.insurancedetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, insurance_details.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("code",recomm_Set.get(position).getCycleCode());
             //   intent.putExtra("Farmercode",recomm_Set.get(position).getFarmerCode());
                mContext.startActivity(intent);

            }

        });

        holder.dailyreports.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, daily_reports.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("code",recomm_Set.get(position).getCycleCode());
                //   intent.putExtra("Farmercode",recomm_Set.get(position).getFarmerCode());
                mContext.startActivity(intent);

            }

        });


        AnimationUtil.animate(holder, true);
    }

    private void requestMultiplePermissions() {
        Dexter.withActivity((Activity) mContext)
                .withPermissions(
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
                       // Toast.makeText(mContext, "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();

    //    currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    }


    @Override
    public int getItemCount() {
        return recomm_Set.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView cropcode,crop,seed,status,date,insurancedetails,termsheet,dailyreports;
        ImageView soiltestimage;
        CardView card_view;
        public ViewHolder(View itemView) {
            super(itemView);
            this.cropcode = (TextView) itemView.findViewById(R.id.cropcode);
            this.crop= itemView.findViewById(R.id.crop);
            this.seed= itemView.findViewById(R.id.seed);
            this.status= itemView.findViewById(R.id.status);
           this.insurancedetails= itemView.findViewById(R.id.insurancedetails);
            this.card_view = (CardView) itemView.findViewById(R.id.card_view);
            this.termsheet= itemView.findViewById(R.id.termsheet);
            this.dailyreports= itemView.findViewById(R.id.dailyreports);


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
            String fileName = strings[1];  // -> maven.pdf
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "EasyFarm");
            folder.mkdir();

            File pdfFile = new File(folder, fileName);
            Log.e("pdfFile==========171", pdfFile+"");
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


            File pdfFile = new File(Environment.getExternalStorageDirectory() + "/EasyFarm/" + name+".xlsx");

            Log.e("pdfFile===========",pdfFile+"");// -> filename = maven.pdf
            //Uri path = Uri.fromFile(pdfFile);
            Uri path = FileProvider.getUriForFile(mContext, BuildConfig.APPLICATION_ID + ".provider", pdfFile);
            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
            //
            pdfIntent.setDataAndType(path, "application/vnd.ms-excel");
            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            try {
                mContext.startActivity(pdfIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(mContext, "No Application available to view  Excel", Toast.LENGTH_SHORT).show();

//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
//
//
//                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.microsoft.office.excel"));
//
////                Intent intent = new Intent(Intent.ACTION_VIEW);
////                intent.setData(Uri.parse(
////                        "https://play.google.com/store/apps/details?id=com.microsoft.office.excel"));
////                intent.setPackage("com.android.vending");
////                startActivity(intent);
//                mContext.startActivity(intent);
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

//    private class DownloadFilee extends AsyncTask<String, String, String> {
//
//        private String resp;
//        //   ProgressDialog progressDialog;
//
//        SpotsDialog mdiloguee = (SpotsDialog) new SpotsDialog.Builder()
//                .setContext(mContext)
//                .setTheme(R.style.Custom)
//                .build();
//        @Override
//        protected String doInBackground(String... strings) {
//            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
//            String fileName = strings[1];  // -> maven.pdf
//            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
//            File folder = new File(extStorageDirectory, "EasyFarm");
//            folder.mkdir();
//
//            File pdfFile = new File(folder, fileName);
//
//            try {
//                pdfFile.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            FileDownloader.downloadFile(fileUrl, pdfFile);
//            return null;
//        }
//
//
//
//
//        @Override
//        protected void onPostExecute(String result) {
//            // execution of result of Long time consuming operation
//            mdiloguee.dismiss();
//
//            File pdfFile = new File(Environment.getExternalStorageDirectory() + "/EasyFarm/" + name+".xlsx");
//
//            Log.e("pdfFile===========",pdfFile+"");// -> filename = maven.pdf
//            //Uri path = Uri.fromFile(pdfFile);
//            Uri path = FileProvider.getUriForFile(mContext, BuildConfig.APPLICATION_ID + ".provider", pdfFile);
//            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
//            //
//            pdfIntent.setDataAndType(path, "application/vnd.ms-excel");
//            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//
//            try {
//                mContext.startActivity(pdfIntent);
////            } catch (ActivityNotFoundException e) {
////                Toast.makeText(getActivity(), "No Application available to view PDF", Toast.LENGTH_SHORT).show();
////            }
////            File pdfFile = new File(Environment.getExternalStorageDirectory() + "/EasyFarm/" + name+".xlsx");
////
////            Log.e("pdfFile===========",pdfFile+"");// -> filename = maven.pdf
////            //Uri path = Uri.fromFile(pdfFile);
////            Uri path = FileProvider.getUriForFile(mContext, BuildConfig.APPLICATION_ID + ".provider", pdfFile);
////            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
////            //
////            pdfIntent.setDataAndType(path, "application/vnd.ms-excel");
////            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////            pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
////
////            try {
////                mContext.startActivity(pdfIntent);
//            } catch (ActivityNotFoundException e) {
//                Toast.makeText(mContext, "No Application available to view Excel ", Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
//
//
//                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.microsoft.office.excel"));
//
////                Intent intent = new Intent(Intent.ACTION_VIEW);
////                intent.setData(Uri.parse(
////                        "https://play.google.com/store/apps/details?id=com.microsoft.office.excel"));
////                intent.setPackage("com.android.vending");
////                startActivity(intent);
//                mContext.startActivity(intent);
//            }
//            //finalResult.setText(result);
//
//
//
//
//        }
//
//        @Override
//        protected void onPreExecute() {
//            mdiloguee.show();
////            mdiloguee = ProgressDialog.show(getActivity(),
////                    "ProgressDialog",
////                    "Wait for "+ " seconds");
//        }
//
//
//        @Override
//        protected void onProgressUpdate(String... text) {
//            //finalResult.setText(text[0]);
//
//        }
//    }
}


