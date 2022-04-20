package in.calibrage.easyfarm.Views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.Common.CommonActivity;
import in.calibrage.easyfarm.Common.Constants;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.localData.SharedPrefsData;
import in.calibrage.easyfarm.model.Forgotpasswordobject;
import in.calibrage.easyfarm.model.Forgotpasswordresponse;
import in.calibrage.easyfarm.model.LoginResponse;
import in.calibrage.easyfarm.model.Loginobject;
import in.calibrage.easyfarm.model.RegistrationRequest;
import in.calibrage.easyfarm.model.RegistrationResponse;
import in.calibrage.easyfarm.service.APIConstantURL;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import in.calibrage.easyfarm.service.ApiService;
import in.calibrage.easyfarm.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends CommonActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final String LOG_TAG = LoginActivity.class.getName();
    Button login;
    private LoginButton facebookBtn;
    EditText username, password;
    TextView signup;
    TextInputLayout name_layout,pass_label;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    public CallbackManager callbackManager;
int socialmedia =0;
    Button FbBtn;
    private static final String EMAIL = "email";
    public  String personGivenName, personFamilyName, personEmail;
    int RC_SIGN_IN = 101,RC_FACEBOOK =102;
Button signInButton;
    GoogleSignInClient mGoogleSignInClient;
    TextView forgot_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
   //     AppEventsLogger.activateApp(this);
        init();
        setViews();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == RC_SIGN_IN)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }else
        {
            callbackManager.onActivityResult(requestCode,resultCode,data);
        }


        super.onActivityResult(requestCode, resultCode, data);

    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            if(account != null) {
                socialmedia =2;


                String personName = account.getDisplayName();
                personGivenName = account.getGivenName();
                personFamilyName = account.getFamilyName();
                personEmail = account.getEmail();
                String personId = account.getId();

                Uri personPhoto = account.getPhotoUrl();
                Log.e("dataFrom Google", personGivenName + "===" + personFamilyName + "==========" + personEmail);


                Intent intent = new Intent(LoginActivity.this, SignnewUpActicity.class);
                intent.putExtra("data",socialmedia);
                intent.putExtra("first_name",personGivenName);
                intent.putExtra("last_name",personFamilyName);
                intent.putExtra("Email",personEmail);
                intent.putExtra("user_name","");
                startActivity(intent);
            }
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google Sign In Error", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null) {
            socialmedia =2;


                String personName = account.getDisplayName();
                personGivenName = account.getGivenName();
                personFamilyName = account.getFamilyName();
                personEmail = account.getEmail();
                String personId = account.getId();

                Uri personPhoto = account.getPhotoUrl();
                Log.e("dataFrom Google", personGivenName + "===" + personFamilyName + "==========" + personEmail);


            Intent intent = new Intent(LoginActivity.this, SignnewUpActicity.class);
            intent.putExtra("data",socialmedia);
            intent.putExtra("first_name",personGivenName);
            intent.putExtra("last_name",personFamilyName);
            intent.putExtra("Email",personEmail);
            intent.putExtra("user_name","");
            startActivity(intent);
        }
        super.onStart();
    }
    GraphRequest request1;

//



    private void init() {
        name_layout =findViewById(R.id.name_layout);
        pass_label =findViewById(R.id.pass_label);
        login = findViewById(R.id.loginBtn);
        facebookBtn = findViewById(R.id.facebookBtn);
        signup = findViewById(R.id.signup);
        FbBtn= findViewById(R.id.FBBtn);
        username = findViewById(R.id.username_edittxt);
        password = findViewById(R.id.pass_edittxt);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
        signInButton = findViewById(R.id.sign_in_button);
        forgot_password =findViewById(R.id.forgot_password);
    }
    private void setViews() {
        password.setTypeface(Typeface.createFromAsset(getAssets(), "font/rubik_regular.ttf"));
        pass_label.setTypeface(Typeface.createFromAsset(getAssets(), "font/rubik_regular.ttf"));
        username.addTextChangedListener(new ValidationTextWatcher(username));
        password.addTextChangedListener(new ValidationTextWatcher(password));


        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (isOnline()) {
                if (validations()) {
                    user_login();
//
                }
            }
                else {
                    showDialog(LoginActivity.this, getResources().getString(R.string.Internet));
                }}
        });
        signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                socialmedia =0;
                Intent intent = new Intent(LoginActivity.this, SignnewUpActicity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("data",socialmedia);

                startActivity(intent);
                //       finish();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
GoogleSignInAccount google = GoogleSignIn.getLastSignedInAccount(LoginActivity.this);

Log.e("testgoogle===",google+"");
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signIn();

            }

        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        startActivity(new Intent(getApplicationContext(), Forgotpassword.class));
                        finish();
                    }
                }, 300);

            }

        });


//            try {
//                final PackageInfo info = this.getPackageManager().getPackageInfo(this.getPackageName(), PackageManager.GET_SIGNATURES);
//                for (android.content.pm.Signature signature : info.signatures) {
//                    final MessageDigest md = MessageDigest.getInstance("SHA");
//                    md.update(signature.toByteArray());
//                    final String hashKey = new String(Base64.encode(md.digest(), 0));
//                    Log.i("AppLog", "key:" + hashKey + "=");
//                    //1Noe2t6rUIvhjtwq9Y1hTG2WAGA=
//                   // 291945805566970
//                }
//            } catch (Exception e) {
//                Log.e("AppLog", "error:", e);
//            }

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        facebookBtn.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday"));
        FbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean loggedOut = AccessToken.getCurrentAccessToken() == null;
                if(loggedOut) {
                    facebookBtn.callOnClick();
                    Log.d("===========","islogin false");
                }
                else{
                    // LoginManager.getInstance().logOut();
                    getUserProfile(AccessToken.getCurrentAccessToken());
                    Log.d("===========","islogin true");
                }

            }
        });
        facebookBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                String Accesstoken = loginResult.getAccessToken().getToken();
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.d("TAG=============", object.toString());
                                try {
                                   // Log.d("TAG", "Username is: " + Profile.getCurrentProfile().getName());
                                    String first_name = object.getString("first_name");
                                    String last_name = object.getString("last_name");
                                    //  String email = object.getString("email");
                                    String id = object.getString("id");
                                    //  txtUsername.setText("First Name: " + first_name + "\nLast Name: " + last_name);
                                    String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                                    Log.d("TAG=====", first_name);
                                    Log.d("TAG==========", last_name);

                                    socialmedia =1;

                                    Intent intent = new Intent(LoginActivity.this, SignnewUpActicity.class);
                                    intent.putExtra("first_name",first_name);
                                    intent.putExtra("last_name",last_name);
                                    intent.putExtra("user_name",first_name);
                                    intent.putExtra("data",socialmedia);
                                    intent.putExtra("Email","");
                                    startActivity(intent);


                                    // txtEmail.setText(email);
//                            Picasso.with(MainActivity.this).load(image_url).into(imageView);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "first_name,last_name,email,id");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }


    private void getUserProfile(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("TAG3", object.toString());
                        try {
                            Log.d("TAG376", "Username is: " + Profile.getCurrentProfile().getName());
                            String first_name = object.getString("first_name");
                            String last_name = object.getString("last_name");
                            //  String email = object.getString("email");
                            String id = object.getString("id");
//                                    txtUsername.setText("First Name: " + first_name + "\nLast Name: " + last_name);
                            String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                            Log.d("TAG=====3", first_name);
                            Log.d("TAG==========3", last_name);
                            socialmedia =1;
                            Intent intent = new Intent(LoginActivity.this, SignnewUpActicity.class);
                            intent.putExtra("first_name",first_name);
                            intent.putExtra("last_name",last_name);
                            intent.putExtra("user_name",Profile.getCurrentProfile().getName());
                            intent.putExtra("data",socialmedia);
                            intent.putExtra("Email","");
                            startActivity(intent);

//                            txtUsername.setText("First Name: " + first_name + "\nLast Name: " + last_name);
//                            txtEmail.setText(email);
//                            Picasso.with(MainActivity.this).load(image_url).into(imageView);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void user_login() {
        mdilogue.show();
        JsonObject object = loginObject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getLoginPage(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResponse>() {
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
                        showDialog(LoginActivity.this, getString(R.string.server_error));
                    }

                    @Override
                    public void onNext(final LoginResponse loginResponse) {
                        mdilogue.dismiss();
                        if (loginResponse.getIsSuccess()) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    /* Create an Intent that will start the Menu-Activity. */
                                    SharedPrefsData.putBool(LoginActivity.this, Constants.IS_LOGIN, true);
                                    SharedPrefsData.saveCreatedUser(LoginActivity.this, loginResponse);
                                    SharedPrefsData.getInstance(LoginActivity.this).updateStringValue(LoginActivity.this, Constants.USER_ID, loginResponse.getResult().getUserInfos().getId()+"");
                                    Log.e("created_useid==", loginResponse.getResult().getUserInfos().getId()+"");

                                    SharedPrefsData.getInstance(LoginActivity.this).updateStringValue(LoginActivity.this, "statecode",loginResponse.getResult().getUserInfos().getStateId()+"");
                                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                    finish();
                                }
                            }, 300);

                        } else {

                            showDialog(LoginActivity.this,loginResponse.getEndUserMessage() );
                        }
                    }


                });}


    private JsonObject loginObject() {
        Loginobject requestModel = new Loginobject();
        requestModel.setUserName(username.getText().toString());
        requestModel.setPassword(password.getText().toString());


        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }

    private boolean validations() {

        if (TextUtils.isEmpty(username.getText().toString())) {
            // userNameEdt.setError(getString(R.string.err_please_enter_username));
            name_layout.setError(getResources().getString(R.string.err_please_enter_username));
            //  showDialog(SignupActivity.this, getResources().getString(R.string.err_please_enter_username));
            return false;
        }
        else{

            name_layout.setErrorEnabled(false);
        }


        if (TextUtils.isEmpty(password.getText().toString().trim())) {
            pass_label.setError(getResources().getString(R.string.err_please_password));
            return false;
        }
        else {
            pass_label.setErrorEnabled(false);
        }
        return true;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

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
                case R.id.username_edittxt:
                    validateusename();
                    break;

                case R.id.pass_edittxt:
                    validate_password();
                    break;




            }
        }
    }

    private boolean validateusename() {
        if (TextUtils.isEmpty(username.getText().toString() ) ) {
            name_layout.setError(getResources().getString(R.string.err_please_enter_username));
            requestFocus(username);
            return false;

        } else if( username.length() < 6)
        {
            name_layout.setError("User Name must have 6 Characters minimum");
            return false;
        }

        else {
            name_layout.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validate_password() {
        if (TextUtils.isEmpty(password.getText().toString().trim())) {
            pass_label.setError(getResources().getString(R.string.err_please_password));
            requestFocus(password);
        }
        else if( password.length() < 6)
        {
            pass_label.setError("Password must have 6 Characters minimum");
            return false;
        }
        else {
            pass_label.setErrorEnabled(false);
        }
        return true;
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

//    public static synchronized void loginWithCloud(final JSONObject values, final String url, final ApplicationThread.OnComplete<String> onComplete) {
//        ApplicationThread.bgndPost(LoginActivity.class.getName(), "placeDataInCloud..", new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    HttpClient.postDataToServerjson(url, values, new ApplicationThread.OnComplete<String>() {
//                        @Override
//                        public void execute(boolean success, String result, String msg) {
//                            if (success) {
//                                try {
//                                    onComplete.execute(success, result.toString(), msg);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                    onComplete.execute(success, result, msg);
//                                }
//                            } else
//                                onComplete.execute(success, result, msg);
//                        }
//                    });
//                } catch (Exception e) {
//                    Log.v(LOG_TAG, "@Error while getting " + e.getMessage());
//                }
//            }
//        });
//
//    }
}
