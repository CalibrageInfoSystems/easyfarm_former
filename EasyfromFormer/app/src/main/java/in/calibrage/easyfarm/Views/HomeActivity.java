package in.calibrage.easyfarm.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.lang.reflect.Field;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.Common.CommonActivity;
import in.calibrage.easyfarm.Common.Constants;
import in.calibrage.easyfarm.Fragments.ActiveplotsFragment;
import in.calibrage.easyfarm.Fragments.AddNewplot;
import in.calibrage.easyfarm.Fragments.HomeFragment;
import in.calibrage.easyfarm.Fragments.ProfileFragment;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.localData.SharedPrefsData;
import in.calibrage.easyfarm.model.LoginResponse;
import in.calibrage.easyfarm.model.ReadNotificationById;
import in.calibrage.easyfarm.model.TokenObject;
import in.calibrage.easyfarm.model.UnreadCount;
import in.calibrage.easyfarm.service.APIConstantURL;
import in.calibrage.easyfarm.service.ApiService;
import in.calibrage.easyfarm.service.DevisetokenResopnse;
import in.calibrage.easyfarm.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class HomeActivity extends CommonActivity implements NavigationView.OnNavigationItemSelectedListener{
     String TOken;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    private BottomNavigationView bottom_navigation;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private ImageView img_profile;
    FloatingActionButton myFab;
    Integer mSelectedItem;
    private TextView  dialogMessage;
    LoginResponse loginressponse;
    int User_id;
    Toolbar toolbar;
    private ImageView notification;
    TextView txt_count;
    private Button ok_btn, cancel_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initviews();
        setViews();

        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
        //fVHH4Dz_vKo:APA91bFFaxnUbX1U5IaDjJR4Hy3ewEX8QSjsCXHDTVLXPGToK0HU0dnItAUqHsPDkLTn6jGoiq5LK4mqiCyWtvrM_xPn2GwgTHbuc5rInJ0f2Us2iUmqIIwSBH_Fg1czVDPBbhJsgrnc
        //token for sending to specific device
        //for sending to all device using own server code subscribe your app to one topic
        Log.d("TOken ",""+ FirebaseInstanceId.getInstance().getToken());
        TOken =FirebaseInstanceId.getInstance().getToken();
        FirebaseMessaging.getInstance().subscribeToTopic("allDevices");

        UpdateDeviseTokenByUserId();
    }

    private void initviews() {
        initToolBar();
//         toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        nv = (NavigationView) findViewById(R.id.nv);
        dl = (DrawerLayout) findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl, R.string.app_name, R.string.app_name);
        myFab = (FloatingActionButton) findViewById(R.id.call_fb);
        dl.addDrawerListener(t);
        t.syncState();
        toolbar.setNavigationIcon(R.drawable.toggle_f);

        bottom_navigation = findViewById(R.id.bottom_navigation);
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, homeFragment, "homeTag")
                .commit();

        notification = (ImageView)findViewById(R.id.notification) ;
        txt_count= (TextView)findViewById(R.id.ntxt_count);



    }
    private void setViews() {

        nv.setNavigationItemSelectedListener(this);
        loginressponse = SharedPrefsData.getCatagories(HomeActivity.this);

        BottomNavigationViewHelper bottomNavigationViewHelper = new BottomNavigationViewHelper();
        bottomNavigationViewHelper.disableShiftMode(bottom_navigation);


        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {

                    case R.id.action_home: {
                        mSelectedItem = item.getItemId();
                        viewFragment(new HomeFragment(), HomeFragment.TAG);
                        break;
                    }
                    case R.id.action_profile: {
                        mSelectedItem = item.getItemId();
                        viewFragment(new ProfileFragment(), ProfileFragment.TAG);
                        break;
                    }
                    case R.id.action_addplot: {
                        mSelectedItem = item.getItemId();
                        logOutDialog();
//                        viewFragment(new AddNewplot(), AddNewplot.TAG);
                        break;
                    }
                    case R.id.action_complaits: {
                        mSelectedItem = item.getItemId();
                        viewFragment(new ActiveplotsFragment(), ActiveplotsFragment.TAG);
                        break;

                    }


                }
                return true;
            }
        });


        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, NotificationsScreen.class));
            }
        });

        getNotificationsCountAPI();

    }



        public void initToolBar() {
            toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            toolbar.setNavigationIcon(R.drawable.toggle_f);
            toolbar.setTitle("");
            toolbar.setNavigationOnClickListener(
                    new View.OnClickListener() {
                        @SuppressLint("WrongConstant")
                        @Override
                        public void onClick(View v) {
                            if (!dl.isDrawerOpen(Gravity.START)) dl.openDrawer(Gravity.START);
                            else dl.closeDrawer(Gravity.END);
                        }
                    }
            );
        }



    private void getNotificationsCountAPI() {


        User_id = loginressponse.getResult().getUserInfos().getId();

        ApiService service = ServiceFactory.createRetrofitService(getApplicationContext(), ApiService.class);
        mSubscription = service.unreadCount(APIConstantURL.notificationunreadcount + User_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<UnreadCount>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ((HttpException) e).code();
                            ((HttpException) e).message();
                            ((HttpException) e).response().errorBody();
                            txt_count.setText("0");
                            try {
                                ((HttpException) e).response().errorBody().string();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            e.printStackTrace();
                        }

                        mdilogue.dismiss();
                        txt_count.setText("0");
                     //   showDialog(HomeActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(UnreadCount unreadCount) {

                        Log.d("UnreadCountLog", unreadCount.getListResult().get(0).getHeader() + "");
                        Log.d("UnreadCountStatus", unreadCount.getIsSuccess() + "");
                        if (unreadCount.getListResult() != null) {
                            if (unreadCount.getIsSuccess()) {
                                txt_count.setText(unreadCount.getAffectedRecords() + "");


                                Log.d("Count1", txt_count.getText().toString());
                            }
                        }
                        else {

                            txt_count.setText("0");
                            Log.d("Count2", txt_count.getText().toString());

                        }
                    }


                });

    }



    private void logOutDialog() {


        final Dialog dialog = new Dialog(HomeActivity.this, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_logout);
        dialogMessage = dialog.findViewById(R.id.dialogMessage);
        dialogMessage.setText(getString(R.string.alert_logout));
        cancel_btn = dialog.findViewById(R.id.cancel_btn);
        ok_btn = dialog.findViewById(R.id.ok_btn);
/**
 * @param OnClickListner
 */
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                updateResources(getApplicationContext(), "en-US");
//                SharedPrefsData.getInstance(getApplicationContext()).ClearData(getApplicationContext());
                SharedPrefsData.putBool(HomeActivity.this, Constants.IS_LOGIN, false);
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });

/**
 * @param OnClickListner
 */
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    private void viewFragment(Fragment fragment, String name) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        // 1. Know how many fragments there are in the stack
        final int count = fragmentManager.getBackStackEntryCount();
        // 2. If the fragment is **not** "home type", save it to the stack
        if (name.equals(HomeFragment.TAG)) {
            fragmentTransaction.addToBackStack(name);
        }
        // Commit !
        fragmentTransaction.commit();
        // 3. After the commit, if the fragment is not an "home type" the back stack is changed, triggering the
        // OnBackStackChanged callback
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                // If the stack decreases it means I clicked the back button
                if (fragmentManager.getBackStackEntryCount() <= count) {
                    // pop all the fragment and remove the listener
                    fragmentManager.popBackStack(HomeFragment.TAG, POP_BACK_STACK_INCLUSIVE);
                    fragmentManager.removeOnBackStackChangedListener(this);
                    // set the home button selected
                    bottom_navigation.getMenu().getItem(0).setChecked(true);
                }
            }
        });
    }

    private void UpdateDeviseTokenByUserId() {
        JsonObject object = tokenObject();
    ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.gettokenresponse(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DevisetokenResopnse>() {
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
                       showDialog(HomeActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(DevisetokenResopnse devisetokenResopnse) {


                        mdilogue.dismiss();
                        if (devisetokenResopnse.getIsSuccess()) {

                            Log.d("DeviceTokenUpdateStatus",devisetokenResopnse.getEndUserMessage());
                          //  Toast.makeText(getApplicationContext(),devisetokenResopnse.getEndUserMessage(), Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(getApplicationContext(),devisetokenResopnse.getEndUserMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }});}





    private JsonObject tokenObject() {

        int userid  =  loginressponse.getResult().getUserInfos().getId();
        TokenObject requestModel = new TokenObject();
        requestModel.setDeviseToken(TOken);
        requestModel.setUserId(userid);


        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        Log.e("id===", String.valueOf(id));
if (id == R.id.action_home) {

            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_frame, homeFragment, null)
                    .commit();
            // Select home item
            bottom_navigation.setSelectedItemId(id);
            // finish();
            bottom_navigation.setSelectedItemId(R.id.action_home);
            if (this.dl.isDrawerOpen(GravityCompat.START))
                this.dl.closeDrawer(GravityCompat.START);

        } else if (id == R.id.action_profile) {


            mSelectedItem = item.getItemId();
            viewFragment(new ProfileFragment(), ProfileFragment.TAG);
            bottom_navigation.setSelectedItemId(R.id.action_profile);
            if (this.dl.isDrawerOpen(GravityCompat.START))
                this.dl.closeDrawer(GravityCompat.START);


        } else if (id == R.id.action_complaits) {
            mSelectedItem = item.getItemId();
            viewFragment(new ActiveplotsFragment(), ActiveplotsFragment.TAG);
            bottom_navigation.setSelectedItemId(R.id.action_complaits);
            if (this.dl.isDrawerOpen(GravityCompat.START))
                this.dl.closeDrawer(GravityCompat.START);
        }
else if (id == R.id.nav_logout) {
    //  bottom_navigation.setSelectedItemId(R.id.action_requests);
    //popupdialog to show message to logout the application
    logOutDialog();
}
        return true;
    }

    public class BottomNavigationViewHelper {

        @SuppressLint("RestrictedApi")
        public void disableShiftMode(BottomNavigationView view) {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
            try {
                Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
                shiftingMode.setAccessible(true);
                shiftingMode.setBoolean(menuView, false);
                shiftingMode.setAccessible(false);
                for (int i = 0; i < menuView.getChildCount(); i++) {
                    BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                    //noinspection RestrictedApi
                //    item.setShiftingMode(false);
                    // set once again checked value, so view will be updated
                    //noinspection RestrictedApi
                    item.setChecked(item.getItemData().isChecked());
                }
            } catch (NoSuchFieldException e) {
                Log.e("BNVHelper", "Unable to get shift mode field", e);
            } catch (IllegalAccessException e) {
                Log.e("BNVHelper", "Unable to change value of shift mode", e);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

     getNotificationsCountAPI();
    }
}

