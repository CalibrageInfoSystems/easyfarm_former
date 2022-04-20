package in.calibrage.easyfarm.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.appevents.suggestedevents.ViewOnClickListener;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import in.calibrage.easyfarm.Adapter.SlideAdapter;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.model.GetVendorServices;
import in.calibrage.easyfarm.model.vendordata;

public class Services_details extends AppCompatActivity {
  //  ArrayList<String> imageslist = new ArrayList<>();
    List<String> imageslist = new ArrayList<>();
    private static ViewPager mPager;
    private Spinner QuantitySpinner;
    public static String QuantityID="";
    SliderView sliderView;
    DatePickerDialog picker;
    String date,currentDate;

    public TextView ServiceName,status,Category,Sub_Category,Brand_Name,country,state,district,mandal,village,address,
            address2,passbook,Contact_person,Contact_num,discription,size,uom,price,discount,open_time,closing_time,comments , datee;
    LinearLayout lyt_plot_code,lyt_status,lyt_totalarea,lyt_Adapted,lyt_gps,lyt_plotownership,new_complaint,exit_complaint,owner_bottom_layout,
            soildetails,lyt_contactname,lyt_contactnum,lin,lin2,lyt_size,lin3,lin4,lin5,lin6,lin7,lyt_discount,lyt_open_time,lyt_closetime,lyt_comments;

    Button add_cart;
    Toolbar toolbar;
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_details);
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

    private void setviews() {
        final vendordata objReceived = (vendordata)getIntent().getSerializableExtra("Character");
        imageslist = (ArrayList<String>) getIntent().getSerializableExtra("images");
        currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
//
//        final vendordata objReceived = ((ObjectWrapperForBinder) getIntent().getExtras().getBinder("object_value")).getData();
//        Log.d("====25", "received object=" + objReceived.getBrandName());
//        for (int i = 0; i < objReceived.getVendorServiceFiles().size(); i++) {
//            imageslist.add(objReceived.getVendorServiceFiles().get(i).getImage());
//        }

        final SlideAdapter adapter = new SlideAdapter(this, imageslist);
        adapter.setCount(imageslist.size());
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
     sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
     sliderView.startAutoCycle();
       sliderView.setScrollTimeInSec(10);

        Category.setText(objReceived.getVendorCategoryName());
        Sub_Category.setText(objReceived.getVendorSubCategoryName());
        discription.setText(objReceived.getDescription());
        Brand_Name.setText(objReceived.getBrandName());
        Contact_person.setText(objReceived.getContactPerson());
        Contact_num.setText(objReceived.getContactNumber()+"");
        size.setText(objReceived.getSize()+"");
        uom.setText(objReceived.getUOMType());
        price.setText(getString(R.string.Rs) +objReceived.getPrice()+"");
        discount.setText(objReceived.getDiscount()+" % \n off");
        open_time.setText(objReceived.getOpeningTime()+" to " + objReceived.getClosingTime());
        comments.setText(objReceived.getComments()+"");
        ServiceName.setText(objReceived.getServiceName());
        datee.setText(currentDate);
        address.setText(objReceived.getVillage()+"(Village), " +objReceived.getMandal()+"(Mandal),\n" +objReceived.getDistrict()+"(District), " +objReceived.getState()+", \n" +objReceived.getCountry() );
        Quantity();
        add_cart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(Services_details.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date = (dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                                Intent intent=new Intent();
//
                               intent.putExtra("description",objReceived.getDescription());
                                intent.putExtra("category",objReceived.getVendorCategoryName());
                                intent.putExtra("ServiceId",objReceived.getId()+"");
                                intent.putExtra("imageurl",imageslist.get(0));
                                intent.putExtra("quantity",QuantityID);
                                intent.putExtra("date",date);
                                intent.putExtra("Count","1");
                                intent.putExtra("price",objReceived.getPrice()+"");
                                Log.e("======quantity",QuantityID+"");
                                setResult(2,intent);
                                finish();
                              //  setResult(RESULT_OK, intent);
                               // startActivity(intent);
                            }
                        }, year, month, day);
                picker.show();
                picker.getDatePicker().setMinDate(System.currentTimeMillis());


            }


        });
    }

    private void intviews() {
        sliderView =findViewById(R.id.imageSliderr);
        Category = findViewById(R.id.Category);
        Sub_Category =findViewById(R.id.Sub_Category);
        discription = findViewById(R.id.discription);
        Brand_Name = findViewById(R.id.Brand_Name);
        Contact_person = findViewById(R.id.Contact_person);
        Contact_num = findViewById(R.id.Contact_num);
        size =findViewById(R.id.size);
        uom = findViewById(R.id.uom);
        price = findViewById(R.id.price);
        discount = findViewById(R.id.discount);
        open_time = findViewById(R.id.open_time);
        comments = findViewById(R.id.comments);
        datee = findViewById(R.id.date);
        address = findViewById(R.id.address);
        ServiceName = findViewById(R.id.ServiceName);
        add_cart = findViewById(R.id.add_cart);
        QuantitySpinner=(Spinner)findViewById(R.id.spinnerQuantity);

    }

    public void Quantity(){
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("1");categories.add("2");categories.add("3");categories.add("4");categories.add("5");categories.add("6");
        categories.add("7");categories.add("8");categories.add("9");categories.add("10");categories.add("11");categories.add("12");
        categories.add("13");categories.add("14");categories.add("15");categories.add("16");categories.add("17");categories.add("18");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        QuantitySpinner.setAdapter(dataAdapter);
        QuantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
               // ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                QuantityID = parent.getItemAtPosition(position).toString();
                Log.e("selected===========",QuantitySpinner.getSelectedItemPosition()+"");
                // Showing selected spinner item
         //   Toast.makeText(parent.getContext(), QuantityID, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }


}
