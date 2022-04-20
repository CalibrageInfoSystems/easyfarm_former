package in.calibrage.easyfarm.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.Adapter.CropReports_Adapter;
import in.calibrage.easyfarm.Adapter.VendorserviceAdapter;
import in.calibrage.easyfarm.Common.CommonActivity;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.model.GetComplaintsByPlotCode;
import in.calibrage.easyfarm.model.GetCropCycleDailyUploadobject;
import in.calibrage.easyfarm.model.GetCropCycleDailyUploadresponse;
import in.calibrage.easyfarm.model.GetVendorServices;
import in.calibrage.easyfarm.model.vendordata;
import in.calibrage.easyfarm.service.APIConstantURL;
import in.calibrage.easyfarm.service.ApiService;
import in.calibrage.easyfarm.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Vendor_Services extends CommonActivity implements  VendorserviceAdapter.Vendorservicelistener{

    String Farmer_code,code;
    TextView noRecords;
    private RecyclerView recyclerView;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    Toolbar toolbar;
    List<String> datelist = new ArrayList<String>();
    List<String> media_data = new ArrayList<String>();
    String Description, Category,image_url,quantity,date,price,serviceid;
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    TextView txt_count;
    public static final int IntentId =1; //Intent Request Code
    String Count;
    final int MY_REQUEST_CODE = 42;
    private ImageView cartButtonIV,optedservices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor__services);
        Intent i = getIntent();
        code = i.getStringExtra("code");



        settoolbar();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        noRecords = (TextView) findViewById(R.id.no_data);
        txt_count =(TextView) findViewById(R.id.txt_count);
        cartButtonIV = findViewById(R.id.cartButtonIV);
        optedservices =findViewById(R.id.optedservices);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
       // recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(4), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // catagoriesList = SharedPrefsData.getCatagories(getContext());
        // recyclerView.setAdapter(adapter);

        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();

        optedservices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Vendor_Services.this, Optedservices.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);


            }

        });
        if (isOnline())
            GetVendorServicesByPlotCode();
        else {
            showDialog(this, getResources().getString(R.string.Internet));

        }



        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
  //      Log.e("================102", preferences.getString("price",null));
        price = preferences.getString("price",null);

        if(price != null )
        {
             Count  =preferences.getString("count","");

            image_url =preferences.getString("imageurl","");
            quantity = preferences.getString("quantity","");
            Log.e("=========quantity",quantity);
            date = preferences.getString("date","");
            Description= preferences.getString("description", "");
            Category = preferences.getString("category","");
            serviceid = preferences.getString("ServiceId","");
            txt_count.setText(Count);
        }
        else{
            txt_count.setText("0");
        }



            cartButtonIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("==========Count==128 ",txt_count.getText()+"");

                    if (txt_count.getText() != "0" && !txt_count.getText().toString().equalsIgnoreCase("0")) {

                        Intent i = new Intent(Vendor_Services.this, CartActivity.class);
                        i.putExtra("description", Description);
                        i.putExtra("category", Category);
                        i.putExtra("imageurl", image_url);
                        i.putExtra("quantity", quantity);
                        Log.e("=========quantity153",quantity);
                        i.putExtra("date", date);
                        i.putExtra("price", price);
                        i.putExtra("ServiceId", serviceid);
                        i.putExtra("code",code);
                        startActivityForResult(i, IntentId);


                    } else {
                        showDialog(Vendor_Services.this, getResources().getString(R.string.select_product_toast));
                    }
                }

            });
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

    private void GetVendorServicesByPlotCode() {
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getVendorServicesByPlotCode(APIConstantURL.VendorServicesByPlotCode+code)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetVendorServices>() {
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
                        showDialog(Vendor_Services.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetVendorServices getVendorServices) {
                        mdilogue.dismiss();
                        if (getVendorServices.getListResult()!= null ) {

                            noRecords.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            Log.d("Analysis============> ",getVendorServices.getListResult().get(0).getServiceName());
                            VendorserviceAdapter adapter = new VendorserviceAdapter(getVendorServices.getListResult(),Vendor_Services.this, Vendor_Services.this);
                            recyclerView.setAdapter(adapter);



                        } else {
                            noRecords.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);

                        }
                    }






                });

    }

    private JsonObject getuploadsReuestobject() {


        GetCropCycleDailyUploadobject requestModel = new GetCropCycleDailyUploadobject();
        requestModel.setCropCycle(code);
        requestModel.setIsActive(true);



        return new Gson().toJsonTree(requestModel).getAsJsonObject();

    }
//(List<vendordata.VendorServiceFile> vendorServiceFiles, String uOMType, String status, String vendorCategoryName, String vendorSubCategoryName, String village, String mandal, String district, String state, String country, String createdBy, String updatedBy, Integer id, Integer vendorCategoryId, Integer vendorSubCategoryId, String serviceName, String brandName, Integer uOMTypeId, Double price, String description, Integer statusTypeId, Boolean isActive, Integer createdByUserId, String createdDate, Integer updatedByUserId, String updatedDate, Object comments, Object size, Object discount, Object contactNumber, Object contactPerson, Integer villageId,
// Integer mandalId, Integer districtId, Integer stateId, Integer countryId, String openingTime, String closingTime) {
//
    @Override
    public void onitemSelected(GetVendorServices.ListResult service_details) {

        List<String> imageslist =new ArrayList<>();
       vendordata listdata = new vendordata(service_details.getUOMType(),service_details.getStatus(),service_details.getVendorCategoryName(),service_details.getVendorSubCategoryName(),service_details.getVillage(),
               service_details.getMandal(),service_details.getDistrict(),service_details.getState(),service_details.getCountry(),service_details.getCreatedBy(),service_details.getUpdatedBy(),service_details.getId(),service_details.getVendorCategoryId(),service_details.getVendorSubCategoryId(),
               service_details.getServiceName(),service_details.getBrandName(),service_details.getUOMTypeId(),service_details.getPrice(),service_details.getDescription(),service_details.getStatusTypeId(),service_details.getIsActive(),service_details.getCreatedByUserId(),service_details.getCreatedDate(),service_details.getUpdatedByUserId(),
               service_details.getUpdatedDate(),service_details.getComments(),service_details.getSize(),service_details.getDiscount(),service_details.getContactNumber(),service_details.getContactPerson(),service_details.getVillageId(),service_details.getMandalId(),service_details.getDistrictId(),
               service_details.getStateId(),service_details.getCountryId(),service_details.getOpeningTime(),service_details.getClosingTime());

        for (int j = 0; j < service_details.getVendorServiceFiles().size(); j++) {
            imageslist.add(service_details.getVendorServiceFiles().get(j).getImage());
        }
      Intent intent = new Intent(Vendor_Services.this, Services_details.class);
//        intent.putExtra("listdata",  listdata);
        intent.putExtra("Character", listdata);
        intent.putExtra("images", (Serializable) imageslist);
        startActivityForResult(intent, 2);
//        final Bundle bundle = new Bundle();
//        bundle.putBinder("object_value", new ObjectWrapperForBinder(listdata));
//       // Activity is started with requestCode 2
//        startActivity(new Intent(this, Services_details.class).putExtras(bundle));
//        Log.d("line=====189", "original object=" + listdata);
        //    startActivityForResult(intent, 2);

      //  startActivityForResult(intent, 10, bundle);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == IntentId) {
            if (resultCode == RESULT_OK) {

                //If result code is OK then get String extra and set message
                Count = data.getStringExtra("message");
                //When result is cancelled display toast
                Toast.makeText(Vendor_Services.this, Count, Toast.LENGTH_SHORT).show();

            }

            if (resultCode == RESULT_CANCELED)

                //When result is cancelled display toast
                Toast.makeText(Vendor_Services.this, "Activity cancelled.", Toast.LENGTH_SHORT).show();


        }
            if (resultCode == 2) { // Activity.RESULT_OK


                Description = data.getStringExtra("description");
                    Category = data.getStringExtra("category");
                    image_url =data.getStringExtra("imageurl");
                    quantity = data.getStringExtra("quantity");
                    date = data.getStringExtra("date");
                    price = data.getStringExtra("price");
                serviceid = data.getStringExtra("ServiceId");
                Count =data.getStringExtra("Count");
                    Log.e("quantity=========2",quantity+"====="+Description);


            }




        txt_count.setText(Count);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("description",Description);
        editor.putString("imageurl",image_url);
        editor.putString("count",Count);
        editor.putString("category",Category);
        editor.putString("quantity",quantity);
        editor.putString("date",date);
        editor.putString("price",price);
        editor.putString("ServiceId",serviceid);
        editor.clear();
        editor.apply();
        // Getstate();
//        cartButtonIV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent i = new Intent(Vendor_Services.this, CartActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.putExtra("description",Description);
//                i.putExtra("category",Category);
//                i.putExtra("imageurl",image_url);
//                i.putExtra("quantity",quantity);
//                i.putExtra("date",date);
//                i.putExtra("price",price);
//                i.putExtra("ServiceId",serviceid);
//                i.putExtra("code",code);
//                startActivityForResult(i, MY_REQUEST_CODE);
//                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
//
//
//            }
//
//        });
        }
    }



