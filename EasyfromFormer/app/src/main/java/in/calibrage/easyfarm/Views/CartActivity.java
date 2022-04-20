package in.calibrage.easyfarm.Views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.Common.CommonActivity;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.localData.SharedPrefsData;
import in.calibrage.easyfarm.model.AddUpdateService;
import in.calibrage.easyfarm.model.AddUpdateServiceresponse;
import in.calibrage.easyfarm.model.LoginResponse;
import in.calibrage.easyfarm.model.MSGmodel;
import in.calibrage.easyfarm.model.ResponseComplaint;
import in.calibrage.easyfarm.service.ApiService;
import in.calibrage.easyfarm.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CartActivity extends CommonActivity {
    String Description, Category, image_url, quantity, date, price, serviceid;
    TextView text_category, description, text_date, txt_price;
    ImageView image;
    Toolbar toolbar;
    private Spinner QuantitySpinner;
    public static String QuantityID = "";
    Button place_order;
    TextInputLayout commentslbl;
    EditText comment_et;
    Button submitBtn;
    String Comments,code;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    String currentDate,serviced_date;
    public static final String DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss";
    LoginResponse loginressponse;
    List<String> categories = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            Description = extras.getString("description");
            Category = extras.getString("category");
            image_url = extras.getString("imageurl");
            quantity = extras.getString("quantity");
            date = extras.getString("date");
            price = extras.getString("price");
            serviceid = extras.getString("ServiceId");
            code = extras.getString("code");
            Log.e("quantity=========", quantity);


        }

        intviews();
        setviews();
        settoolbar();

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
        text_category = findViewById(R.id.text_category);
        description = findViewById(R.id.txt_discription);
        text_date = findViewById(R.id.date);
        image = findViewById(R.id.image_view);
        txt_price = findViewById(R.id.txt_price);
        QuantitySpinner = (Spinner) findViewById(R.id.spinnerQuantity);
        place_order = (Button) findViewById(R.id.place_order);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
        Quantity();
    }

    public void Quantity() {
        categories.clear();
        // Spinner Drop down elements

        categories.add("1");
        categories.add("2");
        categories.add("3");
        categories.add("4");
        categories.add("5");
        categories.add("6");
        categories.add("7");
        categories.add("8");
        categories.add("9");
        categories.add("10");
        categories.add("11");
        categories.add("12");
        categories.add("13");
        categories.add("14");
        categories.add("15");
        categories.add("16");
        categories.add("17");
        categories.add("18");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        QuantitySpinner.setAdapter(dataAdapter);
        QuantitySpinner.setSelection(Integer.parseInt(quantity) - 1);

//        for (int i = 0; i < categories.size(); i++) {
//            if (categories.get(i) == QuantityID) {
//                QuantitySpinner.setSelection(i);
//            }
//        }
        QuantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                // ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                QuantityID = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
//            Toast.makeText(parent.getContext(), QuantityID, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void setviews() {
        loginressponse = SharedPrefsData.getCatagories(CartActivity.this);
        currentDate = new SimpleDateFormat(DATE_FORMAT1).format(Calendar.getInstance().getTimeInMillis());

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date oneWayTripDate = output.parse(date);

            serviced_date = input.format(oneWayTripDate);


         //   Log.e("===============", "======currentData======" + output.format(oneWayTripDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
       // serviced_date =new SimpleDateFormat(DATE_FORMAT1).format(date);
        text_category.setText(Category);
        description.setText(Description);
        text_date.setText(date);
        Glide.with(this).load(image_url).error(R.drawable.ic_user).into(image);
        txt_price.setText(getString(R.string.Rs) + price + "");
        place_order.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {



                                               if (isOnline())
                                                   AddUpdateServiceOrder();
                                               else {
                                                   showDialog(CartActivity.this, getResources().getString(R.string.Internet));
                                                   //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
                                               }
                                              // Comentspopup();

                                           }
                                       }
        );
    }
    private void Comentspopup() {

        final Dialog dialog = new Dialog(this, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_comments);
        commentslbl = (TextInputLayout) dialog.findViewById(R.id.comments_lbl);
        comment_et = (EditText) dialog.findViewById(R.id.comment_et);
        submitBtn = (Button) dialog.findViewById(R.id.submitBtn);

/**
 * @param OnClickListner
 */
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Comments = comment_et.getText().toString();
//                Assigned_userid =list_complaints.get(selectedPO).getAssignToUserId();
//                Complaint_id = list_complaints.get(selectedPO).getComplaintId();
//                plotcode=list_complaints.get(selectedPO).getPlotCode();
//                Log.d("Complaint_id ====", Complaint_id+"" );
//                isreopen = true;
                //   Toast.makeText(mContext, "Please click on Re-open to Submit", Toast.LENGTH_SHORT).show();
                if(Validation()) {

                    if (isOnline())
                        AddUpdateServiceOrder();
                    else {
                        showDialog(CartActivity.this, getResources().getString(R.string.Internet));
                        //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
                    }
                }
                dialog.dismiss();

            }
        });
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    private void AddUpdateServiceOrder() {

        mdilogue.show();
        JsonObject object = AddUpdateServiceobject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
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
                            Toast.makeText(CartActivity.this, addUpdateServiceresponse.getEndUserMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.putExtra("message","0");//Put Message to pass over intent
                            setResult(RESULT_OK,intent);//Set result OK
                            finish();//finish activity
//                            Intent intent=new Intent();
////
//                            intent.putExtra("Count","0");
//                            intent.putExtra("roja","roja");
//                            Log.e("======ServiceId150","===========");
//                            setResult(42,intent);
//                            finish();


                        }

                     else {
                            showDialog(CartActivity.this, addUpdateServiceresponse.getEndUserMessage());
                        }
                    }


                });
    }

    private JsonObject AddUpdateServiceobject() {
        int userid =  loginressponse.getResult().getUserInfos().getId();
        Log.e("state===",userid+"");


        AddUpdateService requestModel = new AddUpdateService();
        requestModel.setId(0);
        requestModel.setServiceId(serviceid);
        requestModel.setStatusTypeId(185);
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


    private boolean Validation() {
        if (TextUtils.isEmpty(comment_et.getText().toString())) {
            // userNameEdt.setError(getString(R.string.err_please_enter_username));
            commentslbl.setError(getResources().getString(R.string.err_please_entercomments));
            //  showDialog(SignupActivity.this, getResources().getString(R.string.err_please_enter_username));
            return false;
        }
        else{

            commentslbl.setErrorEnabled(false);
        }
        return true;
    }



}