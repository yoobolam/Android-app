package com.example.bolam.sayusigan.two_select;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.bolam.sayusigan.ImeiData;
import com.example.bolam.sayusigan.R;
import com.example.bolam.sayusigan.four_listfragment.first.ListFragment;
import com.example.bolam.sayusigan.three_main.MainActivity;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SelectActivity extends Activity {
    private String imei;

    public static final String ROOT_URL = "http://ec2-13-124-185-67.ap-northeast-2.compute.amazonaws.com/connect/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        //권한체크 부분 read_sms는 Oreo 버젼부터 api가 deprecated 됐네요
        //사용은 할수있어도 경고메세지는 뜰거에요
        permissionCheck();

        ImageButton sajubutton = findViewById(R.id.sajubutton);
        sajubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                insertUser2();
                startActivity(intent);
                finish();
            }
        });
        ImageButton taroButton = findViewById(R.id.tarobutton);
        taroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                insertUser();
                Log.d("test", imei);
                startActivity(intent);
                finish();
            }
        });
    }
    private void insertUser() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();
        RegisterAPItaro api = adapter.create(RegisterAPItaro.class);
        api.insertUser(

                //Passing the values by getting it from editTexts
                imei, //field1
                // editTextUsername.getText().toString(),//field2


                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader = null;

                        //An string to store output from the server
                        String output = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //Displaying the output as a toast
                        Toast.makeText(SelectActivity.this, output, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(SelectActivity.this, error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    private void permissionCheck() {
        TedPermission.with(SelectActivity.this)
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.READ_PHONE_STATE)
                .setDeniedMessage("거부시 앱을 이용할 수 없습니다")
                .check();
    }
    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            TelephonyManager mgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            try {
                imei = mgr.getDeviceId();
                Log.d("test", imei);
            } catch (SecurityException e) {
                Log.e("onPermissionGranted: ", "디바이스 권한 에러");
            }
        }
        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            finish();
        }
    };
    private void insertUser2() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();
        RegisterAPIsaju api = adapter.create(RegisterAPIsaju.class);
        api.insertUser2(

                //Passing the values by getting it from editTexts
                imei, //field1

                // editTextUsername.getText().toString(),//field2


                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader = null;

                        //An string to store output from the server
                        String output = "성공했습니다.";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //Displaying the output as a toast
                        Toast.makeText(SelectActivity.this, output, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(SelectActivity.this, error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}

