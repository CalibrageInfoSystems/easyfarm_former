package in.calibrage.easyfarm.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.model.Forgotpasswordobject;
import in.calibrage.easyfarm.model.Forgotpasswordresponse;
import in.calibrage.easyfarm.service.ApiService;
import in.calibrage.easyfarm.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Forgotpassword extends Complaints_Activity {
EditText forgotEmail_edittxt;
TextView submit;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    TextInputLayout name_layout;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        forgotEmail_edittxt = findViewById(R.id.forgotEmail_edittxt);
        submit =findViewById(R.id.forgot_button);
        name_layout = findViewById(R.id.email_label);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
        settoolbar();
        forgotEmail_edittxt.addTextChangedListener(new ValidationTextWatcher(forgotEmail_edittxt));
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgot_passwordapi();

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
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });
    }
    private void forgot_passwordapi() {
        mdilogue.show();
        JsonObject object = forgotObject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getforgotpassowd(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Forgotpasswordresponse>() {
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
                        showDialog(Forgotpassword.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(Forgotpasswordresponse forgotpasswordresponse) {


                        mdilogue.dismiss();
                        if (forgotpasswordresponse.getIsSuccess()) {
                            Toast.makeText(Forgotpassword.this, "Email Sent Successfully", Toast.LENGTH_SHORT).show();

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                    finish();
                                }
                            }, 300);
                        } else {

                            showDialog(Forgotpassword.this,forgotpasswordresponse.getEndUserMessage() );
                        }
                    }


                });}

    private JsonObject forgotObject() {
        Forgotpasswordobject requestModel = new Forgotpasswordobject();
        requestModel.setUsernameOrEmail(forgotEmail_edittxt.getText().toString());



        return new Gson().toJsonTree(requestModel).getAsJsonObject();
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
                case R.id.forgotEmail_edittxt:
                    if(validate()) {
                        validateusename();
                    }
                    break;






            }
        }
    }

    private boolean validate() {
        if (TextUtils.isEmpty(forgotEmail_edittxt.getText().toString())) {
  name_layout.setError(getResources().getString(R.string.err_please_valid_email));

            return false;
        }
        else{

            name_layout.setErrorEnabled(false);
        }
return true;
    }

    private boolean validateusename() {
        if (TextUtils.isEmpty(forgotEmail_edittxt.getText().toString() ) ) {
            name_layout.setError(getResources().getString(R.string.err_please_valid_email));
            requestFocus(forgotEmail_edittxt);
            return false;

        }
        else {
            name_layout.setErrorEnabled(false);
        }
        return true;
    }



    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
