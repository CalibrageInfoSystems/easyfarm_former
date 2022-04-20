package in.calibrage.easyfarm.Adapter;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.Adapter.SoilDetailsAdapter;
import in.calibrage.easyfarm.BuildConfig;
import in.calibrage.easyfarm.Common.AnimationUtil;
import in.calibrage.easyfarm.Common.FileDownloader;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.Views.Cropdetails;
import in.calibrage.easyfarm.Views.PolyActivity;
import in.calibrage.easyfarm.Views.SoilTestReports;
import in.calibrage.easyfarm.Views.Vendor_Services;
import in.calibrage.easyfarm.model.GetGeoBoundaries;
import in.calibrage.easyfarm.model.GetPlotsByFarmerCode;
import in.calibrage.easyfarm.model.Termsheet;
import in.calibrage.easyfarm.service.APIConstantURL;
import in.calibrage.easyfarm.service.ApiService;
import in.calibrage.easyfarm.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PlotDetailsAdapter extends RecyclerView.Adapter<PlotDetailsAdapter.ViewHolder> {

    List<GetPlotsByFarmerCode.ListResult> recomm_Set;
    public Context mContext;
    DecimalFormat df = new DecimalFormat("####0.00");
    private String name, fileurl;
    String latlan;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    public PlotDetailsAdapter(List<GetPlotsByFarmerCode.ListResult> recomm_Set, Context context) {
        this.recomm_Set = recomm_Set;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.plot_list, parent, false);
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

        holder.country.setText( recomm_Set.get(position).getCountryName());
        holder.village.setText(recomm_Set.get(position).getVillageName());
        holder.district.setText(recomm_Set.get(position).getDistrictName());
        holder.mandal.setText(recomm_Set.get(position).getMandalName());

        holder.state.setText( recomm_Set.get(position).getStateName());
        if(null != recomm_Set.get(position).getAddress2()) {
            holder.address.setText(recomm_Set.get(position).getAddress2() + "," + recomm_Set.get(position).getAddress1() + "," + recomm_Set.get(position).getVillageName() + ","
                    + recomm_Set.get(position).getMandalName() + "," + recomm_Set.get(position).getDistrictName() + "," + recomm_Set.get(position).getStateName() + "," + recomm_Set.get(position).getCountryName());
            holder.address2.setText(recomm_Set.get(position).getAddress2());
        }
        else{
            holder.address.setText(recomm_Set.get(position).getAddress1() + "," + recomm_Set.get(position).getVillageName() + ","
                    + recomm_Set.get(position).getMandalName() + "," + recomm_Set.get(position).getDistrictName() + "," + recomm_Set.get(position).getStateName() + "," + recomm_Set.get(position).getCountryName());
            holder.address2.setText(recomm_Set.get(position).getAddress2());
        }

        holder.passbook.setText(recomm_Set.get(position).getPassbookNumber());
        holder.servey_number.setText( recomm_Set.get(position).getSurveyNumber());
        if(recomm_Set.get(position).getOwnerName()!=null && !TextUtils.isEmpty(recomm_Set.get(position).getOwnerName()))
            holder.ownername.setText( recomm_Set.get(position).getOwnerName());


        else {
            holder.lyt_ownername.setVisibility(View.GONE);
            holder.owner_bottom_layout.setVisibility(View.GONE);
        }

        if(recomm_Set.get(position).getOwnerContactNumber()!=null && !TextUtils.isEmpty(recomm_Set.get(position).getOwnerContactNumber()))
            holder.owner_contact_number.setText( recomm_Set.get(position).getOwnerContactNumber());
        else{
            holder.lyt_ownercontactnumer.setVisibility(View.GONE);
            holder.owner_bottom_layout.setVisibility(View.GONE);}
        if (position % 2 == 0) {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white));
        } else {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white2));

        }
        holder.soildetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SoilTestReports.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("code",recomm_Set.get(position).getCode());
                intent.putExtra("Farmercode",recomm_Set.get(position).getFarmerCode());
                mContext.startActivity(intent);

            }

        });

        holder.termsheet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String plotcode =recomm_Set.get(position).getCode();
                Intent intent = new Intent(mContext, Cropdetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("code",recomm_Set.get(position).getCode());
                intent.putExtra("Farmercode",recomm_Set.get(position).getFarmerCode());
                mContext.startActivity(intent);
                //  getTermsheet(plotcode);

//

            }

        });
        holder.vendorservices.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String plotcode =recomm_Set.get(position).getCode();
                Intent intent = new Intent(mContext, Vendor_Services.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("code",recomm_Set.get(position).getCode());
                intent.putExtra("Farmercode",recomm_Set.get(position).getFarmerCode());
                mContext.startActivity(intent);
                //  getTermsheet(plotcode);

//

            }

        });
        holder.mapview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String plotcode =recomm_Set.get(position).getCode();
                GetGeoBoundaries(plotcode);

            }});
        AnimationUtil.animate(holder, true);
    }

    private void GetGeoBoundaries( String plotcode) {
        final SpotsDialog mdiloguee = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(mContext)
                .setTheme(R.style.Custom)
                .build();
        mdiloguee.show();
        ApiService service = ServiceFactory.createRetrofitService(mContext, ApiService.class);
        mSubscription = service.Getgeoboundaries(APIConstantURL.GetGeoBoundaries+plotcode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetGeoBoundaries>() {
                    @Override
                    public void onCompleted() {
                        mdiloguee.dismiss();
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
                        mdiloguee.dismiss();
                        //showDialog(Exit_Complaints_Activity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetGeoBoundaries getGeoBoundaries) {
                        mdiloguee.dismiss();
                        if (getGeoBoundaries.getListResult() != null) {
                            for (int i = 0; i < getGeoBoundaries.getListResult().get(0).getCoordinates().size(); i++) {

                                latlan = getGeoBoundaries.getListResult().get(0).getCoordinates().get(i) + "";
                                Log.d("latLong====1", latlan + "");

                            }
                            Log.d("latLong====2", latlan + "");
                            Intent intent = new Intent(mContext, PolyActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("code",latlan);

                            mContext.startActivity(intent);
//
////
                        }else{
                            Toast.makeText(mContext, " Boundaries not Found to this Plot ", Toast.LENGTH_SHORT).show();
                        }
                       // Toast.makeText(mContext, "TermSheet not available to this Plot ", Toast.LENGTH_SHORT).show();

                    }
                });}



    private void getTermsheet(String plotcode) {
        ApiService service = ServiceFactory.createRetrofitService(mContext, ApiService.class);
        mSubscription = service.Gettermsheet(APIConstantURL.GetTermsheet+plotcode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Termsheet>() {
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

                        //showDialog(Exit_Complaints_Activity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(Termsheet termsheet) {


                        if ( termsheet.getListResult()!= null) {
                            fileurl = termsheet.getListResult().get(0).getTermSheet() ;
                            name = termsheet.getListResult().get(0).getPlotCode();

                            new DownloadFilee().execute(fileurl, "Termsheet"+name + ".xlsx");
                            Log.e("url==========", fileurl);
                        } else {

                            Toast.makeText(mContext, "TermSheet not available to this Plot ", Toast.LENGTH_SHORT).show();

                        }
                    }
                });}
    @Override
    public int getItemCount() {
        return recomm_Set.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView plotcode,status,totalarea,adopted_area,gps_area,plot_ownership,country,state,district,mandal,village,address,
                address2,passbook,servey_number,owner_contact_number,ownername,soildetails,termsheet,vendorservices;
        LinearLayout lyt_plot_code,lyt_status,lyt_totalarea,lyt_Adapted,lyt_gps,lyt_plotownership,lyt_ownercontactnumer,lyt_ownername,owner_bottom_layout;
        LinearLayout mapview;
        CardView card_view;
        public ViewHolder(View itemView) {
            super(itemView);
            this.plotcode = (TextView) itemView.findViewById(R.id.plotcode);
            this.status = (TextView) itemView.findViewById(R.id.status);
            this.totalarea = (TextView) itemView.findViewById(R.id.totalarea);
            this.adopted_area = (TextView) itemView.findViewById(R.id.adopted_area);
            this.gps_area = (TextView) itemView.findViewById(R.id.gps_area);
            this.plot_ownership = (TextView) itemView.findViewById(R.id.plot_ownership);
            this.country = (TextView) itemView.findViewById(R.id.country);
            this.state = (TextView) itemView.findViewById(R.id.state);
            this.district = (TextView) itemView.findViewById(R.id.district);
            this.mandal = (TextView) itemView.findViewById(R.id.mandal);
            this.village = (TextView) itemView.findViewById(R.id.village);
            this.address = (TextView) itemView.findViewById(R.id.address);
            this.address2 = (TextView) itemView.findViewById(R.id.address2);
            this.passbook = (TextView) itemView.findViewById(R.id.passbook);
            this.servey_number = (TextView) itemView.findViewById(R.id.servey_number);
            this.owner_contact_number = (TextView) itemView.findViewById(R.id.owner_contact_number);
            this.ownername = (TextView) itemView.findViewById(R.id.ownername);
            this.termsheet = (TextView) itemView.findViewById(R.id.termsheet);

            this.card_view = (CardView) itemView.findViewById(R.id.card_view);


            this.lyt_plot_code =(LinearLayout)itemView.findViewById(R.id.lyt_plot_code) ;
            this.lyt_status =(LinearLayout)itemView.findViewById(R.id.lyt_status) ;
            this.lyt_totalarea =(LinearLayout)itemView.findViewById(R.id.lyt_totalarea) ;
            this.lyt_Adapted =(LinearLayout)itemView.findViewById(R.id.lyt_Adapted) ;
            this.lyt_gps =(LinearLayout)itemView.findViewById(R.id.lyt_gps) ;
            this.lyt_plotownership =(LinearLayout)itemView.findViewById(R.id.lyt_plotownership) ;
            this.lyt_plot_code =(LinearLayout)itemView.findViewById(R.id.lyt_plot_code) ;
            this.lyt_plot_code =(LinearLayout)itemView.findViewById(R.id.lyt_plot_code) ;
            this.lyt_ownercontactnumer =(LinearLayout)itemView.findViewById(R.id.lyt_owner_contact_number) ;
            this.lyt_ownername =(LinearLayout)itemView.findViewById(R.id.lyt_owner_name) ;
            this.owner_bottom_layout =(LinearLayout)itemView.findViewById(R.id.owner_bottom_layout) ;

            this.soildetails =(TextView) itemView.findViewById(R.id.soildetails) ;

            this.mapview = itemView.findViewById(R.id.map);
            this.vendorservices =(TextView) itemView.findViewById(R.id.vendorservices) ;
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

            File pdfFile = new File(folder,name+".xlsx");

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
                Toast.makeText(mContext, "No Application available to view Excel ", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(android.content.Intent.ACTION_VIEW);


                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.microsoft.office.excel"));

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

