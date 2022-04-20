package in.calibrage.easyfarm.Views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.Adapter.SoilDetailsAdapter;
import in.calibrage.easyfarm.Common.CommonActivity;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.model.GetCropCycleDetailsByCode;
import in.calibrage.easyfarm.model.getSoiltestreports;
import in.calibrage.easyfarm.service.APIConstantURL;
import in.calibrage.easyfarm.service.ApiService;
import in.calibrage.easyfarm.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class insurance_details extends CommonActivity {
    String Farmer_code,code;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    Toolbar toolbar;
    ImageView bond_img;
    TextView policy_name,startdate,enddate,farmer_sum,vfarmer_sum,bond_number,issues_sum,providername;
    String start_date,end_date;
    TextView noRecords;
    LinearLayout insurancelayout;

    Double issued_amount,farmer_amount,virtualfarmer_amount,farmer_per,virtualfarm_per;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_details);
        Intent i = getIntent();
        code = i.getStringExtra("code");
        intviews();
        setview();
        settoolbar();

    }

    private void setview() {
        if (isOnline())
            GetinsuranceDetails();
        else {
            showDialog(this, getResources().getString(R.string.Internet));

        }

    }
    private void settoolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle("Select Godown");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_left);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void intviews() {
        noRecords = (TextView) findViewById(R.id.no_data);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();

        bond_img = findViewById(R.id.bond_img);
        policy_name= findViewById(R.id.policy_name);
        startdate= findViewById(R.id.startdate);
        enddate = findViewById(R.id.enddate);
        farmer_sum =findViewById(R.id.farmer_sumper);
        vfarmer_sum =findViewById(R.id.vfarmer_perc);
        bond_number =findViewById(R.id.bond_number);
        issues_sum =findViewById(R.id.insured_sum);
        providername =findViewById(R.id.providername);
        insurancelayout =findViewById(R.id.insurancelayout);

    }

    private void GetinsuranceDetails() {
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);

        mSubscription = service.getcropdetails(APIConstantURL.GetCropCycleDetailsByCode+code)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetCropCycleDetailsByCode>() {
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
                        //showDialog(SoilTestReports.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(final GetCropCycleDetailsByCode getCropCycleDetailsByCode) {
                        if (getCropCycleDetailsByCode.getResult().getCropInsuranceDetails() != null &&
                                getCropCycleDetailsByCode.getResult().getCropInsuranceDetails().size()!=0) {
                            //  startdate,enddate,farmer_sum,vfarmer_sum
                            policy_name.setText(getCropCycleDetailsByCode.getResult().getCropInsuranceDetails().get(0).getInsurancePlan() + "");
                            bond_number.setText(getCropCycleDetailsByCode.getResult().getCropInsuranceDetails().get(0).getBondNumber());
                            Glide.with(insurance_details.this).load(getCropCycleDetailsByCode.getResult().getCropInsuranceDetails().get(0).getFilePath() + "").error(R.drawable.cashbook).into(bond_img);
                            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
                            SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                Date oneWayTripDate = input.parse(getCropCycleDetailsByCode.getResult().getCropInsuranceDetails().get(0).getStartDate());
                                Date oneWayTripDate2 = input.parse(getCropCycleDetailsByCode.getResult().getCropInsuranceDetails().get(0).getEndDate());

                                start_date = output.format(oneWayTripDate);
                                end_date = output.format(oneWayTripDate2);


                                Log.e("===============", "======currentData======" + output.format(oneWayTripDate));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            startdate.setText(start_date);
                            enddate.setText(end_date);
                            issues_sum.setText(getCropCycleDetailsByCode.getResult().getCropInsuranceDetails().get(0).getSumIssued()+"");

                            issued_amount =getCropCycleDetailsByCode.getResult().getCropInsuranceDetails().get(0).getSumIssued();
                            farmer_per=getCropCycleDetailsByCode.getResult().getCropInsuranceDetails().get(0).getFarmerPencentage();
                            virtualfarm_per =getCropCycleDetailsByCode.getResult().getCropInsuranceDetails().get(0).getVFarmerPencentage();
                             farmer_amount = (issued_amount / 100.0f) * farmer_per;
                            virtualfarmer_amount =(issued_amount / 100.0f) * virtualfarm_per;
                            farmer_sum.setText(farmer_amount+"");
                            vfarmer_sum.setText(virtualfarmer_amount+"");
                            providername.setText(getCropCycleDetailsByCode.getResult().getCropInsuranceDetails().get(0).getProviderName());


                            bond_img.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Context context = getApplicationContext();
                                    LayoutInflater  mInflater = LayoutInflater.from(context);
                                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(insurance_details.this);
                                    View mView = mInflater.inflate(R.layout.dialog_custom_layout, null);
                                    TextView cancel = mView.findViewById(R.id.cancel);
                                    //  Picasso.with(mContext).load(getCollectionInfoById.getResult().getReceiptImg()).error(R.drawable.ic_user).into(photoView);
                                    PhotoView photoView = mView.findViewById(R.id.imageView);

                                    Glide.with(insurance_details.this).load(getCropCycleDetailsByCode.getResult().getCropInsuranceDetails().get(0).getFilePath() + "").error(R.drawable.ic_user).into(photoView);
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
                        } else {
                            //Toast.makeText(insurance_details.this, " No Insurance Details Available ", Toast.LENGTH_SHORT).show();
                            noRecords.setVisibility(View.VISIBLE);
                            insurancelayout.setVisibility(View.GONE);
                        }

                    }



                });



    }
}
