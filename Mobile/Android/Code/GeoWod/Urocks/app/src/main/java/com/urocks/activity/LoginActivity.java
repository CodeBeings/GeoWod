package com.urocks.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sony on 10-10-2015.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private Button button;
    private EditText editText;
    private TextView fbText;
    private CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_new);
        button= (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
        fbText=(TextView)findViewById(R.id.fbText);
        editText.getBackground().clearColorFilter();
        button.setOnClickListener(this);
        fbText.setOnClickListener(this);

    }

    public void login()
    {
        this.finish();
        startActivity(new Intent(LoginActivity.this,DrawerActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button:
                login();

                break;

            case R.id.fbText:

                FacebookSdk.sdkInitialize(getApplicationContext());
                callbackManager = CallbackManager.Factory.create();
                checkFacebookLogin();
              //  LoginManager.getInstance().logOut();
                break;

             default:
                 Log.d("Something went wrong", "onClick ");
        }
    }

    //facebook login++++++++++++++++++++++++++++++

    private void checkFacebookLogin() {

        ArrayList<String> list = new ArrayList<String>();
        list.add("email");
        LoginManager.getInstance().logInWithReadPermissions(this, list);
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(final LoginResult result) {
                 Toast.makeText(LoginActivity.this, "Login Succesfull", Toast.LENGTH_LONG).show();


                GraphRequest request = GraphRequest.newMeRequest(result.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject json, GraphResponse response) {
                        if (response.getError() != null) {
                            System.out.println("ERROR");
                            Log.d("errorTag", "onCompleted ");
                        } else {
                            System.out.println("Success");
                            try {

                                String jsonresult = String.valueOf(json);
                                System.out.println("JSON Result" + jsonresult);

                                JSONObject job = new JSONObject(jsonresult);
                                String email = job.getString("email");
                                String name = job.getString("name");
                                String id = job.getString("id");
                                String photoUrl = "http://graph.facebook.com/" + id + "/picture?type=large";

                                Log.i("", "name111" + name);
                                Log.i("", "socialId1111" + id);
                                Log.i("", "email111" + email);

                                /*IoUtils.storeFbDataToPreferance(ActivityLogin.this, id, email, photoUrl, name);

                                IoUtils.hideSoftKeyboard(loginButton, ActivityLogin.this);

                                String timestamp = IoUtils.ts;
                                String key = "InteriorApp";
                                String temp = email+timestamp+key;
                                String md5 = IoUtils.getMd5For(temp);

                                checkUserAccount(email, id, md5, timestamp);*/


                                //  socialAuthApiCall(email, name, id, photoUrl, "facebook", "A", regid, IoUtils.key, IoUtils.ts);


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                Log.d("oncancel", "onCancel ");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("onerror", "onError ");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // twitterButton.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);

    }



}
