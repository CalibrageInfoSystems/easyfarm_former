package in.calibrage.easyfarm.Views;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;


import in.calibrage.easyfarm.Common.Constants;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.localData.SharedPrefsData;

public class SplashActivity extends AppCompatActivity {
    public  static  final  String LOG_TAG = SplashActivity.class.getSimpleName();

    ProgressBar splashProgress;
    int SPLASH_TIME = 3000; //This is 3 seconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashProgress = findViewById(R.id.splashProgress);
        playProgress();
        final boolean is_login = SharedPrefsData.getBool(SplashActivity.this, Constants.IS_LOGIN);
        final boolean welcome = SharedPrefsData.getBool(SplashActivity.this, Constants.WELCOME);


        //Code to start timer and take action after the timer ends
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do any action here. Now we are moving to next page
                if(!is_login){
                    Intent mySuperIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(mySuperIntent);

                    //This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
                    finish();
                }else {
                    Intent mySuperIntent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(mySuperIntent);

                    //This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
                    finish();
                }


            }
        }, SPLASH_TIME);
    }

    private void playProgress() {
        ObjectAnimator.ofInt(splashProgress, "progress", 100)
                .setDuration(5000)
                .start();

    }

}
