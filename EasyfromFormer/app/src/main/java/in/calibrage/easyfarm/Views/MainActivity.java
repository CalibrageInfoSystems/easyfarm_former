package in.calibrage.easyfarm.Views;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.media.MediaCas;
import android.os.Bundle;
import android.se.omapi.Session;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import android.text.Html;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
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
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

import dmax.dialog.SpotsDialog;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.Views.SignnewUpActicity;
import rx.Subscription;

public class MainActivity extends AppCompatActivity {
    LoginButton loginButton;
    CallbackManager callbackManager;
    ImageView imageView;
    Button FbBtn;
    //1857726564351501
    TextView txtUsername, txtEmail;
    int RC_SIGN_IN = 0;
Button signInButton;
    GoogleSignInClient mGoogleSignInClient;
    private static final String LOG_TAG = LoginActivity.class.getName();
    Button login;
    private LoginButton facebookBtn;
    EditText username, password;
    TextView signup;
    TextInputLayout name_layout,pass_label;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setViews();
        // b  getHashkey();
        loginButton = findViewById(R.id.facebookBtn);
        FbBtn= findViewById(R.id.FBBtn);


        FacebookSdk.sdkInitialize(getApplicationContext());

        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));
        callbackManager = CallbackManager.Factory.create();


        //  checkloginstatus();

        FbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean loggedOut = AccessToken.getCurrentAccessToken() == null;
                if(loggedOut) {
                    loginButton.callOnClick();
                    Log.d("===========","islogin false");
                }
                else{
                   // LoginManager.getInstance().logOut();
                    getUserProfile(AccessToken.getCurrentAccessToken());
                    Log.d("===========","islogin true");
                }

            }
        });
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
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
                                    String first_name = object.getString("first_name");
                                    String last_name = object.getString("last_name");
                                    //  String email = object.getString("email");
                                    String id = object.getString("id");
//                                    txtUsername.setText("First Name: " + first_name + "\nLast Name: " + last_name);
                                    String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                                    Log.d("TAG=====", first_name);
                                    Log.d("TAG==========", last_name);
                                    Intent intent = new Intent(MainActivity.this, SignnewUpActicity.class);
                                    intent.putExtra("first_name",first_name);
                                    intent.putExtra("last_name",last_name);
                                    intent.putExtra("first_name",first_name);
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

//
    }
    private void init() {
        name_layout =findViewById(R.id.name_layout);
        pass_label =findViewById(R.id.pass_label);
        login = findViewById(R.id.loginBtn);
        facebookBtn = findViewById(R.id.facebookBtn);
        signup = findViewById(R.id.signup);
        username = findViewById(R.id.username_edittxt);
        password = findViewById(R.id.pass_edittxt);
        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .build();
        signInButton = findViewById(R.id.sign_in_button);
    }
    private void setViews() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    callbackManager.onActivityResult(requestCode,resultCode,data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            startActivity(new Intent(MainActivity.this, SignnewUpActicity.class));
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google Sign In Error", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null) {
            startActivity(new Intent(MainActivity.this, SignnewUpActicity.class));
        }
        super.onStart();
    }
    private void getUserProfile(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("TAG", object.toString());
                        try {
                            String first_name = object.getString("first_name");
                            String last_name = object.getString("last_name");
                            //  String email = object.getString("email");
                            String id = object.getString("id");
//                                    txtUsername.setText("First Name: " + first_name + "\nLast Name: " + last_name);
                            String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                            Log.d("TAG=====", first_name);
                            Log.d("TAG==========", last_name);
                            Intent intent = new Intent(MainActivity.this, SignnewUpActicity.class);
                            intent.putExtra("first_name",first_name);
                            intent.putExtra("last_name",last_name);
                            intent.putExtra("first_name",first_name);
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




//    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }



}