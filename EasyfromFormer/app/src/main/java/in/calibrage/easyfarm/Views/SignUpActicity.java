package in.calibrage.easyfarm.Views;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
import java.util.Locale;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.Common.CommonActivity;
import in.calibrage.easyfarm.Common.EmailValidator;
import in.calibrage.easyfarm.R;

import in.calibrage.easyfarm.model.GetDistricts;
import in.calibrage.easyfarm.model.GetGender;
import in.calibrage.easyfarm.model.GetMandals;
import in.calibrage.easyfarm.model.GetOwnershipStatus;
import in.calibrage.easyfarm.model.GetPlotStatus;
import in.calibrage.easyfarm.model.GetRoles;
import in.calibrage.easyfarm.model.GetStates;

import in.calibrage.easyfarm.model.GetVillages;

import in.calibrage.easyfarm.model.GetVillagesbypincode;
import in.calibrage.easyfarm.model.RegistrationRequest;
import in.calibrage.easyfarm.model.RegistrationResponse;

import in.calibrage.easyfarm.service.APIConstantURL;
import in.calibrage.easyfarm.service.ApiService;
import in.calibrage.easyfarm.service.ServiceFactory;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SignUpActicity extends CommonActivity implements AdapterView.OnItemSelectedListener {
    private static final String LOG_TAG = SignUpActicity.class.getName();
    Button signup;
    EditText Fathername_edittxt,fullname, contact_number,address, address2;
    private EditText userNameEdt,First_name,middle_name,last_name,date_birth,email,password,confirmpass_edittxt;
    TextInputLayout name_layout,firstname_label,lastname_label,email_label,pass_label,father_name_layout, middleName_layout,confirm_pass;
    TextView login;
    TextInputLayout  mobile_label,address_label, address2_label;
    String[] gender = { "Select Gender ","Male", "Female" };
    Spinner spin_gender,spin_state,spin_dist,spin_mandal,spin_village,spin_roll;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    String dob,birthdate;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    List<String>get_state = new ArrayList<String>();
    List<Integer> get_state_Id = new ArrayList<Integer>();

    List<String>get_district = new ArrayList<String>();
    List<Integer> get_district_Id = new ArrayList<Integer>();

    List<String>get_mandal = new ArrayList<String>();
    List<Integer> get_mandal_Id = new ArrayList<Integer>();

    List<String>get_villages = new ArrayList<String>();
    List<Integer> get_villages_Id = new ArrayList<Integer>();

    List<String>get_gender = new ArrayList<String>();
    List<Integer> get_gender_Id = new ArrayList<Integer>();

    List<String>get_role = new ArrayList<String>();
    List<Integer> get_role_Id = new ArrayList<Integer>();



    List<String>get_status = new ArrayList<String>();
    List<Integer> get_status_Id = new ArrayList<Integer>();

    List<String>get_ownership = new ArrayList<String>();
    List<Integer> get_ownership_Id = new ArrayList<Integer>();
    int state_id,dist_id,mandal_id,village_id,gender_id,status_id,ownership_id,plot_state_id,plot_dist_id,plot_mandal_id,plot_village_id;
    Integer role_id;
    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;


    EditText total_area,adopted_area,palm_area,gps_area,plot_address,plot_address2,pincode;
    TextInputLayout total_area_layout,adopted_area_label,adopted_are_label,gps_area_label,plot_address_label,plot_address2_label,date_label;

    Spinner spin_plot_state,spin_plot_dist,spin_plot_mandal,spin_plot_village,spin_plotstatus,spin_Ownership;

    String currentDate;
String Pincode;
    LinearLayout plot_details;

    EditText survey_number,passbook_number,owner_name,owner_contactnumber;
    TextInputLayout survey_number_label,passbook_number_label,owner_name_label,owner_contactnumber_label;
    String Pin_villaname;
    int stateid_from_server,Pin_villcode,pin_distid,pin_madalid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_acticity);
        init();
        if (isOnline()) {
            setViews();
        }
        else {
            showDialog(SignUpActicity.this, getResources().getString(R.string.Internet));
        }
    }

    private void init() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        name_layout =findViewById(R.id.name_layout);
        middleName_layout = findViewById(R.id.middleName_layout);
        address_label =findViewById(R.id.address_label);
        mobile_label = findViewById(R.id.mobile_label);
        email_label = findViewById(R.id.email_label);
        father_name_layout = findViewById(R.id.father_name_layout);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.sign_in);
        fullname = findViewById(R.id.username_edittxt);
        spin_gender = findViewById(R.id.spin_gender);
        address = findViewById(R.id.address_edittxt);
        address2 = findViewById(R.id.address2_edittxt);
        address2_label = findViewById(R.id.address2_label);
        contact_number = findViewById(R.id.phone_edittxt);
        email = findViewById(R.id.Email_edittxt);

        firstname_label =findViewById(R.id.firstname_label);
        name_layout =findViewById(R.id.name_layout);
        lastname_label = findViewById(R.id.lastname_label);
        email_label = findViewById(R.id.email_label);
        pass_label  = findViewById(R.id.pass_label);
        confirm_pass =findViewById(R.id.confirm_pass_label);
        Fathername_edittxt = findViewById(R.id.Fathername_edittxt);
        userNameEdt = findViewById(R.id.name_edittxt);
        First_name = findViewById(R.id.fname_edittxt);
        middle_name =  findViewById(R.id.m_edittxt);
        last_name = findViewById(R.id.L_edittxt);
        date_birth =  findViewById(R.id.date_edittxt);
        email = findViewById(R.id.Email_edittxt);
        password = findViewById(R.id.pass_edittxt);
        confirmpass_edittxt= findViewById(R.id.confirmpass_edittxt);
        spin_state = findViewById(R.id.spin_state);
        spin_dist = findViewById(R.id.spin_dist);
        spin_mandal =findViewById(R.id.spin_mandal);
        spin_village = findViewById(R.id.spin_village);
        spin_roll =findViewById(R.id.spin_roll);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
        total_area_layout=findViewById(R.id.Total_Area_label);
        adopted_area_label=findViewById(R.id.Adopted_Area_label);
        gps_area_label=findViewById(R.id.firstname_label);
        plot_address_label=findViewById(R.id.addresss_label);
        plot_address2_label=findViewById(R.id.address_label2);
        date_label=findViewById(R.id.date_label);

        total_area = findViewById(R.id.Total_Area_edittxt);
        adopted_area=findViewById(R.id.Adopted_area_edittxt);
       // palm_area = findViewById(R.id.Palm_Area_edittxt);
        gps_area=findViewById(R.id.gps_are_edittxt);
        plot_address=findViewById(R.id.addresss_plot_edittxt);
        plot_address2=findViewById(R.id.addres_plot_edittxt);
        pincode= findViewById(R.id.pin_edittxt);

        spin_plot_state = findViewById(R.id.spin_plot_state);
        spin_plot_dist = findViewById(R.id.spin_plot_dist);
        spin_plot_mandal =findViewById(R.id.spin_plot_mandal);
        spin_plot_village = findViewById(R.id.spin_plot_village);
      //  spin_plotstatus = findViewById(R.id.spin_plotstatus);
        spin_Ownership = findViewById(R.id.spin_Ownership);

        plot_details = findViewById(R.id.plot_details);

//        survey_number =findViewById(R.id.surveynumber_edittxt);
//        passbook_number =findViewById(R.id.passbook_number_edittxt);
//        owner_name =findViewById(R.id.ownername_edittxt);
//        owner_contactnumber =findViewById(R.id.owner_phone_edittxt);
//        survey_number_label =findViewById(R.id.surveynumber_label);
//        passbook_number_label =findViewById(R.id.passbook_number_label);
//        owner_name_label =findViewById(R.id.ownername_label);
//        owner_contactnumber_label =findViewById(R.id.owner_mobile_label);
        //passbook_number_label,owner_name_label,owner_contactnumber_label;

    }
    private void setViews() {
        getLastLocation();
        GetAddressByPinCode();
        currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Log.i("LOG_RESPONSE date ", currentDate);
        date_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(SignUpActicity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                              date_birth.setText(day + "/" + (month + 1) + "/" + year);
                               // date_birth.setText(year + "-" + (month + 1) + "-" + day);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });



       // middle_name.addTextChangedListener(new ValidationTextWatcher(middle_name));
        address2.addTextChangedListener(new ValidationTextWatcher(address2));
        Fathername_edittxt.addTextChangedListener(new ValidationTextWatcher(Fathername_edittxt));
        contact_number.addTextChangedListener(new ValidationTextWatcher(contact_number));
        address.addTextChangedListener(new ValidationTextWatcher(address));
        userNameEdt.addTextChangedListener(new ValidationTextWatcher(userNameEdt));
        First_name.addTextChangedListener(new ValidationTextWatcher(First_name));
        last_name.addTextChangedListener(new ValidationTextWatcher(last_name));
        password.addTextChangedListener(new ValidationTextWatcher(password));
        confirmpass_edittxt.addTextChangedListener(new ValidationTextWatcher(confirmpass_edittxt));

        total_area.addTextChangedListener(new ValidationTextWatcher(total_area));
//       adopted_area.addTextChangedListener(new ValidationTextWatcher(adopted_area));
//        gps_area.addTextChangedListener(new ValidationTextWatcher(gps_area));
        plot_address.addTextChangedListener(new ValidationTextWatcher(plot_address));

//        survey_number.addTextChangedListener(new ValidationTextWatcher(survey_number));
//        passbook_number.addTextChangedListener(new ValidationTextWatcher(passbook_number));
//        owner_name.addTextChangedListener(new ValidationTextWatcher(owner_name));
//        owner_contactnumber.addTextChangedListener(new ValidationTextWatcher(owner_contactnumber));




  //   GetplotStates();
        spin_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selected_state = spin_state.getItemAtPosition(spin_state.getSelectedItemPosition()).toString();


                if (position != 0) {
                    state_id = get_state_Id.get(spin_state.getSelectedItemPosition() - 1);
                    Log.d(LOG_TAG, "state_id======" + state_id);
                    Getdistricts(state_id);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        spin_dist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selected_dist = spin_dist.getItemAtPosition(spin_dist.getSelectedItemPosition()).toString();
                Log.d(LOG_TAG, "dist_name======" + selected_dist);
                if (position != 0) {
                    dist_id = get_district_Id.get(spin_dist.getSelectedItemPosition() - 1);
                    Log.d(LOG_TAG, "dist_id======" + dist_id);
                    Getmandal(dist_id);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
        spin_mandal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selected_mandal = spin_mandal.getItemAtPosition(spin_mandal.getSelectedItemPosition()).toString();
                if (position != 0) {
                    mandal_id = get_mandal_Id.get(spin_mandal.getSelectedItemPosition() - 1);
                    Log.d(LOG_TAG, "mandal_id======" + mandal_id);
                    GetVillages(mandal_id);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
        GetAllRole();
        spin_village.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selected_mandal = spin_village.getItemAtPosition(spin_village.getSelectedItemPosition()).toString();
                if (position != 0) {

                    village_id = get_villages_Id.get(spin_village.getSelectedItemPosition() - 1);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        spin_roll.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selected_mandal = spin_roll.getItemAtPosition(spin_roll.getSelectedItemPosition()).toString();
                if (position != 0) {

                    role_id =get_role_Id.get(spin_roll.getSelectedItemPosition() - 1);
                    if(role_id==2){
                        plot_details.setVisibility(View.VISIBLE);
                    }
                    else {
                        plot_details.setVisibility(View.GONE);
                    }
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

//        spin_plotstatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                String selected_state = spin_plotstatus.getItemAtPosition(spin_plotstatus.getSelectedItemPosition()).toString();
//
//
//                if (position != 0) {
//                    status_id = get_status_Id.get(spin_plotstatus.getSelectedItemPosition() - 1);
//
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                // DO Nothing here
//            }
//        });
//        spin_Ownership.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                String selected_ownership= spin_Ownership.getItemAtPosition(spin_Ownership.getSelectedItemPosition()).toString();
//
//
//                if (position != 0) {
//
//                    ownership_id = get_ownership_Id.get(spin_Ownership.getSelectedItemPosition() - 1);
//
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                // DO Nothing here
//            }
//        });
        spin_plot_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selected_state= spin_plot_state.getItemAtPosition(spin_plot_state.getSelectedItemPosition()).toString();


                if (position != 0) {
                    plot_state_id = get_state_Id.get(spin_plot_state.getSelectedItemPosition()-1);
                    Getplotdistricts(plot_state_id);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        spin_plot_dist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selected_state = spin_plot_dist.getItemAtPosition(spin_plot_dist.getSelectedItemPosition()).toString();
                if (position != 0) {
                    //spin_plot_dist.setSelection(2);
                    plot_dist_id = get_district_Id.get(spin_plot_dist.getSelectedItemPosition()-1);
                    Log.d(LOG_TAG, "dist_id======" + dist_id);
                    Getplotmandal(plot_dist_id);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
        spin_plot_mandal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selected_mandal = spin_plot_mandal.getItemAtPosition(spin_plot_mandal.getSelectedItemPosition()).toString();
                if (position != 0) {
                    plot_mandal_id = get_mandal_Id.get(spin_plot_mandal.getSelectedItemPosition() - 1);
                    Log.d(LOG_TAG, "mandal_id======" + mandal_id);
                    GetplotVillages(plot_mandal_id);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
        spin_plot_village.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selected_mandal = spin_plot_village.getItemAtPosition(spin_plot_village.getSelectedItemPosition()).toString();
                if (position != 0) {

                    plot_village_id = get_villages_Id.get(spin_plot_village.getSelectedItemPosition() - 1);
                }
                spin_plot_village.setPrompt(selected_mandal);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (validations()) {
                    externalRegistrationAPI();
//

//
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActicity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                //       finish();
            }
        });

    }

    private void GetAddressByPinCode() {

            mdilogue.show();
            get_villages.clear();
            get_villages_Id.clear();
            ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
            mSubscription = service.getvillagesbypincode(APIConstantURL.GetAddressByPinCode + Pincode )
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetVillagesbypincode>() {
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
                            mdilogue.cancel();
                            // showDialog(SignUpActicity.this, getString(R.string.server_error));
                        }

                        @Override
                        public void onNext(GetVillagesbypincode getVillagesbypincode) {
                            if (getVillagesbypincode.getListResult() != null) {
                                for (GetVillagesbypincode.ListResult data : getVillagesbypincode.getListResult()
                                ) {

                                    Pin_villcode =data.getVillageId();
                                    pin_distid  = data.getDistrictId();
                                    stateid_from_server =data.getStateId();
                                    pin_madalid =data.getMandalId();
                                    spin_plot_village.setPrompt(data.getVillageName());
                                   spin_plot_state.setPrompt(data.getStateName());
                                   spin_plot_dist.setPrompt(data.getDistrictName());
                                   spin_plot_mandal.setPrompt(data.getMandalName());
                                }



                            }
                            if (isOnline()) {
                                getgender();
                                getplotstatus();
                                getplotownership();
                                GetStates();
                                spin_gender.setOnItemSelectedListener(SignUpActicity.this);
                            }

                            else {
                                showDialog(SignUpActicity.this, getResources().getString(R.string.Internet));
                                //Toast.makeText(LoginActivity.this, "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
                            }
                        }


                    });
    }

    private void GetplotVillages(int plot_mandal_id) {
        mdilogue.show();
        get_villages.clear();
        get_villages_Id.clear();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getvillages(APIConstantURL.Getvillages + plot_mandal_id +"/"+null)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetVillages>() {
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
                        mdilogue.cancel();
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetVillages getVillages) {

                        if (getVillages.getListResult() != null) {
                            get_villages.add("Select Village");
                            for (GetVillages.ListResult data : getVillages.getListResult()
                            ) {
                                get_villages.add(data.getName());
                                get_villages_Id.add(data.getId());
                            }
                            Log.d(LOG_TAG, "RESPONSE======" + get_state);



                            ArrayAdapter village = new ArrayAdapter(SignUpActicity.this,android.R.layout.simple_spinner_item,get_villages);
                            village.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spin_plot_village.setAdapter(village);

                        } else {
                            get_villages.add("No Villages Available");
                            Log.e("nodada====", "nodata===custom2");

                        }
                        for(int i=0; i< getVillages.getListResult().size();i++)
                        {
                            if( getVillages.getListResult().get(i).getId() == Pin_villcode)
                            {
                                spin_plot_village.setSelection(i+1);


                            }
                        }
                    }

                });
    }

    private void Getplotmandal(int plot_dist_id) {
        mdilogue.show();
        get_mandal.clear();
        get_mandal_Id.clear();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getmandals(APIConstantURL.Getmandals + plot_dist_id +"/"+null)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetMandals>() {
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
                        mdilogue.cancel();
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetMandals getMandals) {


                        if (getMandals.getListResult() != null) {
                            get_mandal.add("Select Mandal");
                            for (GetMandals.ListResult data : getMandals.getListResult()
                            ) {
                                get_mandal.add(data.getName());
                                get_mandal_Id.add(data.getId());
                            }
                            Log.d(LOG_TAG, "RESPONSE======" + get_state);

                            ArrayAdapter mandal = new ArrayAdapter(SignUpActicity.this,android.R.layout.simple_spinner_item,get_mandal);
                            mandal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spin_plot_mandal.setAdapter(mandal);


                        } else {
                            Log.e("nodada====", "nodata===custom2");
                            get_mandal.add("No Mandal Available");
                        }
                        for(int i=0; i< getMandals.getListResult().size();i++)
                        {
                            if( getMandals.getListResult().get(i).getId() == pin_madalid)
                            {
                                spin_plot_mandal.setSelection(i+1);
                            }
                        }
                    }

                });
    }

    private void Getplotdistricts(int plot_state_id) {
        mdilogue.show();
        get_district.clear();
        get_district_Id.clear();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getdistricts(APIConstantURL.Getdistricts + plot_state_id+"/"+ null)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetDistricts>() {
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
                        mdilogue.cancel();
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetDistricts getDistricts) {

                        if (getDistricts.getListResult() != null) {
                            get_district.add("Select District");
                            for (GetDistricts.ListResult data : getDistricts.getListResult()
                            ) {
                                get_district.add(data.getName());
                                get_district_Id.add(data.getId());
                            }
                            Log.d(LOG_TAG, "RESPONSE======" + get_state);

//

                            ArrayAdapter dist= new ArrayAdapter(SignUpActicity.this,android.R.layout.simple_spinner_item,get_district);
                            dist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spin_plot_dist.setAdapter(dist);

                        } else {
                            Log.e("nodada====", "nodata===custom2");
                            get_district.add("No District Available");
                        }
                        for(int i=0; i< getDistricts.getListResult().size();i++)
                        {
                            if( getDistricts.getListResult().get(i).getId() == pin_distid)
                            {
                                spin_plot_dist.setSelection(i+1);
                            }
                        }
                    }

                });
    }

    private void GetplotStates() {
        mdilogue.show();
        get_state.clear();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getstates(APIConstantURL.Getstate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetStates>() {
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
                        mdilogue.cancel();
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetStates getStates) {

                        Log.d(LOG_TAG, "RESPONSE======" + getStates);
                        if (getStates.getListResult() != null) {
                            get_state.add("Select State");
                            for (GetStates.ListResult data : getStates.getListResult()
                            ) {
                                get_state.add(data.getName());
                                get_state_Id.add(data.getId());
                            }
                            Log.d(LOG_TAG, "RESPONSE======" + get_state);

//
                            ArrayAdapter aa = new ArrayAdapter(SignUpActicity.this,android.R.layout.simple_spinner_item,get_state);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spin_plot_state.setAdapter(aa);


                        } else {
                            Log.e("nodada====", "nodata===custom2");
                            get_state.add("No State Available");

                        }

                    }

                });

    }

    private void getplotstatus() {
        get_status.clear();
        get_status_Id.clear();
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getstatus(APIConstantURL.GetPlotstatus )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetPlotStatus>() {
                    @Override
                    public void onCompleted() {
                        mdilogue.dismiss();

                    }
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetPlotStatus getPlotStatus) {


                        if (getPlotStatus.getListResult() != null) {
                            get_status.add("Select PlotStatus");
                            for (GetPlotStatus.ListResult data : getPlotStatus.getListResult()
                            ) {
                                get_status.add(data.getDesc());
                                get_status_Id.add(data.getTypeCdId());
                            }
                            Log.d(LOG_TAG, "RESPONSE======" + get_state);


                            ArrayAdapter aa = new ArrayAdapter(SignUpActicity.this,android.R.layout.simple_spinner_item,get_status);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spin_plotstatus.setAdapter(aa);


                        } else {
                            Log.e("nodada====", "nodata===custom2");

                        }

                    }

                });
    }
    private void getplotownership() {
        get_ownership.clear();
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getownership(APIConstantURL.GetPlotownership )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetOwnershipStatus>() {
                    @Override
                    public void onCompleted() {
                        mdilogue.dismiss();

                    }
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetOwnershipStatus getOwnershipStatus) {


                        if (getOwnershipStatus.getListResult() != null) {
                            get_ownership.add("Select PlotStatus");
                            for (GetOwnershipStatus.ListResult data : getOwnershipStatus.getListResult()
                            ) {
                                get_ownership.add(data.getDesc());
                                get_ownership_Id.add(data.getTypeCdId());
                            }
                            Log.d(LOG_TAG, "RESPONSE======" + get_ownership);


                            ArrayAdapter aa = new ArrayAdapter(SignUpActicity.this,android.R.layout.simple_spinner_item,get_ownership);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spin_Ownership.setAdapter(aa);


                        } else {
                            Log.e("nodada====", "nodata===custom2");

                        }

                    }

                });


    }
    @SuppressLint("MissingPermission")
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                                    try {
                                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                        plot_address.setText(addresses.get(0).getSubThoroughfare() + ","+ addresses.get(0).getThoroughfare());
                                        plot_address2.setText(addresses.get(0).getSubLocality()+","+addresses.get(0).getLocality());
                                        Pincode = addresses.get(0).getPostalCode();
                                        Log.e("Pincode=========",Pincode);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    Log.e("lat====================",location.getLatitude()+"");
                                    Log.e("long==================",location.getLongitude()+"");


                                }
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }


    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
           // plot_address.setText();
            Log.e("lat====================",mLastLocation.getLatitude()+"");
            Log.e("long==================",mLastLocation.getLongitude()+"");

        }
    };

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    private void GetAllRole() {
        get_role.clear();
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getrole(APIConstantURL.Getroles )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetRoles>() {
                    @Override
                    public void onCompleted() {
                        mdilogue.dismiss();

                    }
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetRoles getRoles) {

                        if (getRoles.getListResult() != null) {
                            get_role.add("Select Role");
                            for (GetRoles.ListResult data : getRoles.getListResult()
                            ) {
                                get_role.add(data.getDesc());
                                get_role_Id.add(data.getId());
                            }
                            Log.d(LOG_TAG, "RESPONSE======" + get_role);


                            ArrayAdapter aa = new ArrayAdapter(SignUpActicity.this,android.R.layout.simple_spinner_item,get_role);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spin_roll.setAdapter(aa);


                        } else {
                            Log.e("nodada====", "nodata===custom2");

                        }

                    }

                });
    }

    private void getgender() {
        get_gender.clear();
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getender(APIConstantURL.Getgender )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetGender>() {
                    @Override
                    public void onCompleted() {
                        mdilogue.dismiss();

                    }
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetGender getGender) {
                        if (getGender.getListResult() != null) {
                            get_gender.add("Select Gender");
                            for (GetGender.ListResult data : getGender.getListResult()
                            ) {
                                get_gender.add(data.getDesc());
                                get_gender_Id.add(data.getTypeCdId());
                            }
                            Log.d(LOG_TAG, "RESPONSE======" + get_state);


                            ArrayAdapter aa = new ArrayAdapter(SignUpActicity.this,android.R.layout.simple_spinner_item,get_gender);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spin_gender.setAdapter(aa);


                        } else {
                            Log.e("nodada====", "nodata===custom2");

                        }

                    }

                });
                    }


 private void externalRegistrationAPI() {

        JsonObject object = externalRegistrationObject();
        ApiService service = ServiceFactory.createRetrofitService(SignUpActicity.this, ApiService.class);
        mSubscription = service.externalRegistrationPage(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RegistrationResponse>() {
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

                        mdilogue.cancel();
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }



                    @Override
                    public void onNext(RegistrationResponse registrationResponse) {

                        if(registrationResponse.getIsSuccess() == true){
                            Toast.makeText(getApplicationContext(),"External Registration Successfull", Toast.LENGTH_SHORT).show();

//                            new Handler().postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    /* Create an Intent that will start the Menu-Activity. */
////                                    SharedPrefsData.putBool(LoginActivity.this, Constants.IS_LOGIN, true);
////                                    SharedPrefsData.saveCreatedUser(LoginActivity.this, loginResponse);
////                                    SharedPrefsData.getInstance(LoginActivity.this).updateStringValue(LoginActivity.this, Constants.createduser_ID, loginResponse.getResult().getUserInfos().getId()+"");
////                                    Log.e("created_useid==", loginResponse.getResult().getUserInfos().getId()+"");
//                                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//                                    finish();
//                                }
//                            }, 300);

                        }
else{
                        Toast.makeText(getApplicationContext(),registrationResponse.getEndUserMessage(), Toast.LENGTH_SHORT).show();}
                    }
                });

    }

    private JsonObject externalRegistrationObject() {

        dob = date_birth.getText().toString().trim();

        SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {

            birthdate = myFormat.format(fromUser.parse(dob));
            Log.d("collection", "RESPONSE reformattedStr======" + birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        RegistrationRequest requestModel = new RegistrationRequest();
        requestModel.setFirstName(First_name.getText().toString());
        requestModel.setMiddleName(middle_name.getText().toString());
        requestModel.setLastName(last_name.getText().toString());
        requestModel.setPrimaryPhoneNumber(contact_number.getText().toString());
        requestModel.setFatherOrGuardianName(Fathername_edittxt.getText().toString());
        requestModel.setAddress1(address.getText().toString());
        requestModel.setAddress2(address2.getText().toString());
        requestModel.setStateId(state_id);
        requestModel.setDistrictId(dist_id);
        requestModel.setMandalId(mandal_id);
        requestModel.setVillageId(village_id);
        requestModel.setGenderTypeId(gender_id);
        requestModel.setDOB(birthdate);
        requestModel.setUserName(userNameEdt.getText().toString());
        requestModel.setPassword(password.getText().toString());
        requestModel.setRoleIds(role_id+"");
        requestModel.setEmail(email.getText().toString());
        requestModel.setServerUpdateStatus(true);
//        requestModel.setTotalPlotArea(Double.valueOf(total_area.getText().toString()));


//        requestModel.setAdaptedArea(Double.valueOf(adopted_area.getText().toString()));
//        requestModel.setGPSPlotArea(Double.valueOf(gps_area.getText().toString()));
//        requestModel.setSurveyNumber(survey_number.getText().toString());
//        requestModel.setPlotOwnerShipTypeId(ownership_id);
//        requestModel.setOwnerName(owner_name.getText().toString());
//        requestModel.setOwnerContactNumber(owner_contactnumber.getText().toString());
//        requestModel.setPlotStautusId(status_id);
        if(role_id==2) {
            requestModel.setTotalPlotArea(Double.valueOf(total_area.getText().toString()));
            requestModel.setPAddress1(plot_address.getText().toString());
            requestModel.setPAddress2(plot_address2.getText().toString());
            requestModel.setPStateId(plot_state_id);
            requestModel.setPDistrictId(plot_dist_id);
            requestModel.setPMandalId(plot_mandal_id);
            requestModel.setPVillageId(plot_village_id);
        }

//        requestModel.setIsActive(true);
//        requestModel.setCreatedByUserId(1);
//        requestModel.setCreatedDate(currentDate);
//        requestModel.setUpdatedByUserId(1);
//        requestModel.setUpdatedDate(currentDate);
//        requestModel.setServerUpdatedStatus(true);
        return new Gson().toJsonTree(requestModel).getAsJsonObject();



//                "CreatedByUserId": 35,
//                "CreatedDate": "2020-06-05T09:30:50.2506828+05:30",
//                "UpdatedByUserId": 37,
//                "UpdatedDate": "2020-06-05T09:30:50.2506828+05:30",
//                "ServerUpdatedStatus": true

    }




    private void GetVillages(int mandalid) {
        mdilogue.show();
        get_villages.clear();
        get_villages_Id.clear();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getvillages(APIConstantURL.Getvillages + mandalid +"/"+null)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetVillages>() {
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
                        mdilogue.cancel();
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetVillages getVillages) {

                        if (getVillages.getListResult() != null) {
                            get_villages.add("Select Village");
                            for (GetVillages.ListResult data : getVillages.getListResult()
                            ) {
                                get_villages.add(data.getName());
                                get_villages_Id.add(data.getId());
                            }
                            Log.d(LOG_TAG, "RESPONSE======" + get_state);


                            ArrayAdapter aa = new ArrayAdapter(SignUpActicity.this,android.R.layout.simple_spinner_item,get_villages);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spin_village.setAdapter(aa);

                        } else {
                            get_villages.add("No Villages Available");
                            Log.e("nodada====", "nodata===custom2");

                        }

                    }

                });

    }

    private void Getmandal(int distid) {
        mdilogue.show();
        get_mandal.clear();
        get_mandal_Id.clear();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getmandals(APIConstantURL.Getmandals + distid +"/"+null)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetMandals>() {
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
                        mdilogue.cancel();
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetMandals getMandals) {


                        if (getMandals.getListResult() != null) {
                            get_mandal.add("Select Mandal");
                            for (GetMandals.ListResult data : getMandals.getListResult()
                            ) {
                                get_mandal.add(data.getName());
                              get_mandal_Id.add(data.getId());
                            }
                            Log.d(LOG_TAG, "RESPONSE======" + get_mandal);


                            ArrayAdapter aa = new ArrayAdapter(SignUpActicity.this,android.R.layout.simple_spinner_item,get_mandal);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spin_mandal.setAdapter(aa);



                        } else {
                            Log.e("nodada====", "nodata===custom2");
                            get_mandal.add("No Mandal Available");
                        }

                    }

                });
    }

    private void Getdistricts(int stateid) {
        mdilogue.show();
        get_district.clear();
        get_district_Id.clear();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getdistricts(APIConstantURL.Getdistricts + stateid+"/"+ null)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetDistricts>() {
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
                        mdilogue.cancel();
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetDistricts getDistricts) {

                        if (getDistricts.getListResult() != null) {
                            get_district.add("Select District");
                            for (GetDistricts.ListResult data : getDistricts.getListResult()
                            ) {
                                get_district.add(data.getName());
                                get_district_Id.add(data.getId());
                            }
                            Log.d(LOG_TAG, "RESPONSE======" + get_state);

//
                            ArrayAdapter aa = new ArrayAdapter(SignUpActicity.this,android.R.layout.simple_spinner_item,get_district);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spin_dist.setAdapter(aa);


                        } else {
                            Log.e("nodada====", "nodata===custom2");
                            get_district.add("No District Available");
                        }

                    }

                });



    }


    private void GetStates() {
        mdilogue.show();
        get_state.clear();
        get_state_Id.clear();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getstates(APIConstantURL.Getstate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetStates>() {
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
                        mdilogue.cancel();
                        // showDialog(SignUpActicity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(GetStates getStates) {

                        Log.d(LOG_TAG, "RESPONSE======" + getStates);
                        if (getStates.getListResult() != null) {
                            get_state.add("Select State");
                            for (GetStates.ListResult data : getStates.getListResult()
                            ) {

                                get_state.add(data.getName());
                                get_state_Id.add(data.getId());

                            }
                            Log.d(LOG_TAG, "RESPONSE======" + get_state);

//
                            ArrayAdapter aa = new ArrayAdapter(SignUpActicity.this,android.R.layout.simple_spinner_item,get_state);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spin_state.setAdapter(aa);

                            ArrayAdapter state = new ArrayAdapter(SignUpActicity.this,android.R.layout.simple_spinner_item,get_state);
                            state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spin_plot_state.setAdapter(state);
                            for(int i=0; i< getStates.getListResult().size();i++)
                            {
                                if( getStates.getListResult().get(i).getId() == stateid_from_server)
                                {
                                    Log.e(LOG_TAG,"i===========1"+stateid_from_server);
                                    Log.e(LOG_TAG,"i===========2"+i);
                                    spin_plot_state.setSelection(i+1);
                                }
                            }




                        } else {
                            Log.e("nodada====", "nodata===custom2");
                            get_state.add("No State Available");

                        }

                    }

                });


    }

    private boolean validations() {
        String pass = password.getText().toString();
        String Confirm_pass =confirmpass_edittxt.getText().toString();

        if (TextUtils.isEmpty(userNameEdt.getText().toString())) {

            name_layout.setError(getResources().getString(R.string.err_please_enter_username));

            return false;
        }
        else if (TextUtils.isEmpty(contact_number.getText().toString().trim())) {
            mobile_label.setError(getResources().getString(R.string.err_please_phone));

            return false;
        }
        else if (TextUtils.isEmpty(password.getText().toString().trim())) {

            pass_label.setError(getResources().getString(R.string.err_please_password));

            return false;
        } else if (TextUtils.isEmpty(confirmpass_edittxt.getText().toString().trim())) {

            confirm_pass.setError(getResources().getString(R.string.err_please_confirm_password));

            return false;
        }
        else if(!pass.equals(Confirm_pass)){
            confirm_pass.setError(getResources().getString(R.string.error_confirm));
            return false;
        }
//        else  if (TextUtils.isEmpty(middle_name.getText().toString().trim())) {
//            middleName_layout.setError("Please Enter Middle Name");
//            return false;
//        }
        else if (TextUtils.isEmpty(First_name.getText().toString().trim())) {
            firstname_label.setError(getResources().getString(R.string.err_please_F_name));
            return false;
        }
        else if (TextUtils.isEmpty(last_name.getText().toString().trim())) {
            //  passwordEdt.setError(getString(R.string.err_please_enter_password));
            lastname_label.setError(getResources().getString(R.string.err_please_L_name));

            return false;
        }  else if (TextUtils.isEmpty(Fathername_edittxt.getText().toString().trim())) {
            //  passwordEdt.setError(getString(R.string.err_please_enter_password));
            father_name_layout.setError("Please Enter Father/Guardian Name");

            return false;
        }
        else if (spin_gender.getSelectedItemPosition() == 0) {

            showDialog(this, getResources().getString(R.string.select_gender));
            return false;
        }
        else if (TextUtils.isEmpty(date_birth.getText().toString().trim())) {
            date_label.setError(getString(R.string.err_please_date));
            //   showDialog(SignUpActicity.this, getResources().getString(R.string.err_please_date));
            return false;
        }
       else if (TextUtils.isEmpty(address.getText().toString().trim())) {
            address_label.setError(getResources().getString(R.string.err_please_address));
            return false;
        } else if (TextUtils.isEmpty(address2.getText().toString().trim())) {
            //  passwordEdt.setError(getString(R.string.err_please_enter_password));
            address2_label.setError("Please Enter Address");


            return false;
        }
        else if (spin_state.getSelectedItemPosition() == 0) {

            showDialog(this, getResources().getString(R.string.select_state));
            return false;
        } else if (spin_dist.getSelectedItemPosition() == 0) {

            showDialog(this, getResources().getString(R.string.select_district));
            return false;
        } else if (spin_mandal.getSelectedItemPosition() == 0) {

            showDialog(this, getResources().getString(R.string.select_mandal));
            return false;
        } else if (spin_village.getSelectedItemPosition() == 0) {

            showDialog(this, getResources().getString(R.string.select_village));
            return false;
        }

//        else if (TextUtils.isEmpty(email.getText().toString().trim())) {
//            //  passwordEdt.setError(getString(R.string.err_please_enter_password));
//            email_label.setError(getResources().getString(R.string.err_please_email));
//            return false;
//        }
//        else if(!EmailValidator.getInstance().validate(email.getText().toString().trim())){
//            email_label.setError(getResources().getString(R.string.err_please_valid_email));
//            return false;
//        }




       else if (spin_roll.getSelectedItemPosition() == 0) {

            showDialog(this, getResources().getString(R.string.select_role));
            return false;
        }

        // total_area,adopted_area,palm_area,gps_area,plot_address,plot_address2,pincode;
        // total_area_layout,adopted_area_label,adopted_are_label,gps_area_label,plot_address_label,plot_address2_label,pincode_label;
        else if (role_id == 2) {
         if (TextUtils.isEmpty(total_area.getText().toString().trim())) {
                total_area_layout.setError(getResources().getString(R.string.err_please_Totalarea));
                return false;
            }


            else if (spin_plot_state.getSelectedItemPosition() == 0) {

                showDialog(this, getResources().getString(R.string.select_state));
                return false;
            } else if (spin_plot_dist.getSelectedItemPosition() == 0) {

                showDialog(this, getResources().getString(R.string.select_district));
                return false;
            } else if (spin_plot_mandal.getSelectedItemPosition() == 0) {

                showDialog(this, getResources().getString(R.string.select_mandal));
                return false;
            } else if (spin_plot_village.getSelectedItemPosition() == 0) {

                 showDialog(this, getResources().getString(R.string.select_village));
                return false;
            }


        }
        return true;
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String  select_gender = spin_gender.getItemAtPosition(spin_gender.getSelectedItemPosition()).toString();
        if (position != 0) {

            gender_id = get_gender_Id.get(spin_gender.getSelectedItemPosition()-1);
            Log.d(LOG_TAG, "gender_id======" + gender_id);

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private class ValidationTextWatcher implements TextWatcher {

        private View view;

        private ValidationTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }



        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {

                case R.id.name_edittxt:
                    validateusename();
                    break;
                case R.id.phone_edittxt:
                    validate_mobile();
                    break;
                case R.id.address_edittxt:
                    validate_address();
                    break;
                case R.id.username_edittxt:
                    validate_name();
                    break;
//                case R.id.Email_edittxt:
//                    validate_email();
//                    break;
//                case R.id.m_edittxt:
//                    validate_middlename();
//                    break;
                case R.id.Fathername_edittxt:
                    validate_faatherguardianname();
                    break;
                case R.id.address2_edittxt:
                    validate_address2();
                    break;

                case R.id.fname_edittxt:
                    validate_firstname();
                    break;
                case R.id.L_edittxt:
                    validate_lastname();
                    break;

                case R.id.pass_edittxt:
                    validate_password();
                    break;




                case R.id.Total_Area_edittxt:
                    validate_total_area();
                    break;
                case R.id.confirmpass_edittxt:
                    validaeconfirmpassword();
                    break;
//                case R.id.gps_are_edittxt:
//                    validate_gps_area();
//                    break;
//
//                case R.id.surveynumber_edittxt:
//                    validate_surveynumber();
//                    break;
//                case R.id.passbook_number_edittxt:
//                    validate_passbook();
//                    break;
//
//                case R.id.ownername_edittxt:
//                    validate_ownername();
//                    break;
//                case R.id.owner_phone_edittxt:
//                    validate_ownermobile();
//                    break;



            }
        }
    }

    private boolean validaeconfirmpassword() {
        if (TextUtils.isEmpty(confirmpass_edittxt.getText().toString().trim())) {
            confirm_pass.setError(getResources().getString(R.string.err_please_confirm_password));
            requestFocus(confirmpass_edittxt);
        }
        else {
            confirm_pass.setErrorEnabled(false);
        }
        return true;
    }


    private boolean validate_ownermobile() {
        if (TextUtils.isEmpty(owner_contactnumber.getText().toString().trim())) {
            owner_contactnumber_label.setError(getResources().getString(R.string.err_please_owner_contactnumber));
            requestFocus(owner_contactnumber);
        } else {
            owner_name_label.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validate_ownername() {
        if (TextUtils.isEmpty(owner_name.getText().toString().trim())) {
            owner_name_label.setError(getResources().getString(R.string.err_please_owner_name));
            requestFocus(owner_name);
        } else {
            owner_name_label.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validate_surveynumber() {
        if (TextUtils.isEmpty(survey_number.getText().toString().trim())) {
            survey_number_label.setError(getResources().getString(R.string.err_please_survey_number));
            requestFocus(survey_number);
        } else {
            survey_number_label.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validate_passbook() {
        if (TextUtils.isEmpty(passbook_number.getText().toString().trim())) {
            passbook_number_label.setError(getResources().getString(R.string.err_please_passbook_number));
            requestFocus(passbook_number);
        } else {
            passbook_number_label.setErrorEnabled(false);
        }
        return true;

    }
    private boolean validate_gps_area() {
        if (TextUtils.isEmpty(gps_area.getText().toString().trim())) {
            gps_area_label.setError(getResources().getString(R.string.err_please_gpsarea));
            requestFocus(gps_area);
        } else {
            gps_area_label.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validate_adopted_area() {
        if (TextUtils.isEmpty(adopted_area.getText().toString().trim())) {
            adopted_area_label.setError(getResources().getString(R.string.err_please_adoprtedarea));
            requestFocus(password);
        } else {
            adopted_area_label.setErrorEnabled(false);
        }
        return true;
    }
        private boolean validate_total_area() {
            if (TextUtils.isEmpty(total_area.getText().toString().trim())) {
                total_area_layout.setError(getResources().getString(R.string.err_please_Totalarea));
                requestFocus(total_area);
            } else {
                total_area_layout.setErrorEnabled(false);
            }
            return true;
    }

    private boolean validate_password() {
        if (TextUtils.isEmpty(password.getText().toString().trim())) {
            pass_label.setError(getResources().getString(R.string.err_please_password));
            requestFocus(password);
        }
        else {
            pass_label.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validate_lastname() {
        if (TextUtils.isEmpty(last_name.getText().toString())) {
            lastname_label.setError(getResources().getString(R.string.err_please_L_name));
            requestFocus(last_name);
        } else {
            lastname_label.setErrorEnabled(false);

        }
        return true;
    }


//    private boolean validate_middlename() {
//        if (TextUtils.isEmpty(middle_name.getText().toString())) {
//            middleName_layout.setError(getResources().getString(R.string.err_please_L_name));
//            requestFocus(middle_name);
//        } else {
//            middleName_layout.setErrorEnabled(false);
//
//        }
//        return true;
//    }

    private boolean validate_firstname() {
        if (TextUtils.isEmpty(First_name.getText().toString())) {
            firstname_label.setError(getResources().getString(R.string.err_please_F_name));
            requestFocus(First_name);
        } else {
            firstname_label.setErrorEnabled(false);

        }
        return true;
    }

    private boolean validate_faatherguardianname() {
        if (TextUtils.isEmpty(Fathername_edittxt.getText().toString())) {
            father_name_layout.setError(getResources().getString(R.string.err_please_F_name));
            requestFocus(Fathername_edittxt);
        } else {
            father_name_layout.setErrorEnabled(false);

        }
        return true;
    }



    private boolean validateusename() {
        if (TextUtils.isEmpty(userNameEdt.getText().toString())) {
            name_layout.setError(getResources().getString(R.string.err_please_enter_username));
            requestFocus(userNameEdt);
            return false;

        }
        else {
            name_layout.setErrorEnabled(false);
        }
        return true;
    }
//
//    private boolean validate_email() {
//        if (TextUtils.isEmpty(email.getText().toString().trim())) {
//            email_label.setError(getResources().getString(R.string.err_please_email));
//            requestFocus(email);
//        }
//        else  if(!EmailValidator.getInstance().validate(email.getText().toString().trim())){
//            email_label.setError(getResources().getString(R.string.err_please_valid_email));
//            requestFocus(email);
//
//        }
//        else {
//            email_label.setErrorEnabled(false);
//        }
//        return true;
//    }

    private boolean validate_name() {
        if (TextUtils.isEmpty(fullname.getText().toString())) {
            name_layout.setError(getResources().getString(R.string.err_please_Full_name));
            requestFocus(fullname);
        } else {
            name_layout.setErrorEnabled(false);

        }
        return true;
    }



    private boolean validate_address() {
        if(TextUtils.isEmpty(address.getText().toString().trim())){
            address_label.setError(getResources().getString(R.string.err_please_address));
            requestFocus(address);

        }
        else {
            address_label.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validate_address2() {
        if(TextUtils.isEmpty(address2.getText().toString().trim())){
            address2_label.setError(getResources().getString(R.string.err_please_address));
            requestFocus(address2);

        }
        else {
            address2_label.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validate_mobile() {
        if(TextUtils.isEmpty(contact_number.getText().toString().trim())){
            mobile_label.setError(getResources().getString(R.string.err_please_phone));
            requestFocus(contact_number);

        }
        else {
            mobile_label.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
           getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
