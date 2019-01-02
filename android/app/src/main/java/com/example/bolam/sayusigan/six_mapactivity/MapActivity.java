package com.example.bolam.sayusigan.six_mapactivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bolam.sayusigan.ImeiData;
import com.example.bolam.sayusigan.R;
import com.example.bolam.sayusigan.four_listfragment.first.ApiService1;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private ArrayList<Map> mainWaitList = new ArrayList<Map>();
    ArrayList<HashMap<String, String>> mArrayList;
    final private int REQUEST_PERMISSIONS_FOR_LAST_KNOWN_LOCATION = 0;
    final private String TAG = "LocationService";
    private FusedLocationProviderClient mFusedLocationClient;
    private Location mCurrentLocation;
    private GoogleMap mGoogleMap = null;
    private Address address;
    ApiService1 apiService1;
    private MarkerOptions myLocationMarker;
    Retrofit retrofit;

    private Location mLocation;
    MarkerOptions friendMarker1;
    // ListViewAdapter adapter;
    private Drawable pictureDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        retrofit = new Retrofit.Builder().baseUrl(ApiService1.API_URL).build();
        apiService1 = retrofit.create(ApiService1.class);
        setServerData();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.maps);

        // mapFragment.getMapAsync(this);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d(TAG, "GoogleMap is ready.");
                mGoogleMap = googleMap;

            }
        });
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (!checkLocationPermissions()) {
            requestLocationPermissions(REQUEST_PERMISSIONS_FOR_LAST_KNOWN_LOCATION);
        } else {
            getLastLocation();
        }

        setServerData();
        Button findBtn = (Button) findViewById(R.id.button);
        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkLocationPermissions()) {
                    requestLocationPermissions(REQUEST_PERMISSIONS_FOR_LAST_KNOWN_LOCATION);
                } else {
                    mGoogleMap.clear();

                    getAddress();//받아온 텍스트 로 위치 찾기
                }
            }

        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestMyLocation();
            }
        });
    }

    //외부클래스
    private void setServerData() {
        Call<ResponseBody> comment = apiService1.getPostMap(ImeiData.getInstace().getImei());
        comment.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    Log.v("Test", result);
                    try {

                        mainWaitList.clear();

                        JSONArray jsonArray = new JSONArray(result); //json형식의 string을 객체에 저장
                        String mstore;
                        String mwork1;
                        String mwork2;
                        String mwork3;
                        int mtime11;
                        int mtime22;
                        int mtime33;
                        int h;
                        int m;
                        String mtime1;
                        String mtime2;
                        String mtime3;

                        //정수형: getInt(), 실수형: getDouble(), 문자: getString()
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);//객체 크기

                            mstore = jsonObject.getString("mstore"); //실제 php받아오는 값//스토이 이름
                            mwork1 = jsonObject.getString("mwork1");
                            mwork2 = jsonObject.getString("mwork2");
                            mwork3 = jsonObject.getString("mwork3");
                            mtime11 = jsonObject.getInt("mtime1");
                            m = mtime11 / 60;
                            h = m / 60;
                            m = m % 60;
                            mtime1 = h + ":" + m;

                            mtime22 = jsonObject.getInt("mtime2");
                            m = mtime22 / 60;
                            h = m / 60;
                            m = m % 60;
                            mtime2 = h + ":" + m;

                            mtime33 = jsonObject.getInt("mtime3");
                            m = mtime33 / 60;
                            h = m / 60;
                            m = m % 60;
                            mtime3 = h + ":" + m;
                            Map map = new Map(mstore, mwork1, mwork2, mwork3, mtime1, mtime2, mtime3);

                            Log.v("Test", jsonObject.toString());

                            mainWaitList.add(map);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //error
            }
        });
    }

    private boolean checkLocationPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermissions(int requestCode) {
        ActivityCompat.requestPermissions(
                MapActivity.this,            // MainActivity 액티비티의 객체 인스턴스를 나타냄
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},        // 요청할 권한 목록을 설정한 String 배열
                requestCode    // 사용자 정의 int 상수. 권한 요청 결과를 받을 때
        );
    }

    @SuppressWarnings("MissingPermission")
    private void getLastLocation() {
        Task task = mFusedLocationClient.getLastLocation();       // Task<Location> 객체 반환
        task.addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    mCurrentLocation = location;
                    LatLng curlocation = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curlocation, 15));
                    //updateUI();
                } else
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.no_location_detected),
                            Toast.LENGTH_SHORT)
                            .show();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        if (mCurrentLocation != null) {
            LatLng location = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
            // move the camera
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
        }
    }

    private void getAddress() {
        EditText addressEdit = (EditText) findViewById(R.id.edit_text);
        addressEdit.setHint("위치를 지정해주세요");
        addressEdit.setHintTextColor(Color.parseColor("#ff0000"));
        String addressstr = addressEdit.getText().toString();
        TextView result = (TextView) findViewById(R.id.result);// 값을 안드로이드 화면에 띄워줌
        boolean isEmpty = true;
        LatLng naksanLoca = null;

        for (int i = 0; i < mainWaitList.size(); i++) {
            if (addressstr.equals(mainWaitList.get(i).getmstore())) {

                isEmpty = false;
                try {
                    Geocoder geocoder = new Geocoder(this, Locale.KOREA);
                    List<Address> addresses = geocoder.getFromLocationName(addressstr, 1);

                    if (addresses.size() > 0) {
                        address = addresses.get(0);
                        result.setText(String.format("[%s]\t[%s]",
                                address.getLatitude(),
                                address.getLongitude()
                        ));
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Failed in using Geocoder", e);
                }
                if (mGoogleMap != null) {
                    naksanLoca = new LatLng(address.getLatitude(), address.getLongitude());//현재 위도 경도 저장
                    mGoogleMap.addMarker(
                            new MarkerOptions().
                                    position(naksanLoca).
                                    title("                                    "+addressstr+"                                   ").
                                    snippet(mainWaitList.get(i).getmwork1() + ": 시간 " + mainWaitList.get(i).getmtime1() + "분 " + mainWaitList.get(i).getmwork2()
                                            + ": 시간 " + mainWaitList.get(i).getmtime2() + "분 " + mainWaitList.get(i).getmwork3()
                                            + ":시간" + mainWaitList.get(i).getmtime3() + "분").
                                    alpha(1f)
                    );
                }
            }
        }
        /**
         * // 위에서 결과가 없을경우
         * */
        if (isEmpty) {
            if (mGoogleMap != null) {
                isEmpty = false;
                try {
                    Geocoder geocoder = new Geocoder(this, Locale.KOREA);
                    List<Address> addresses = geocoder.getFromLocationName(addressstr, 1);

                    if (addresses.size() > 0) {
                        address = addresses.get(0);
                        result.setText(String.format("[%s]\t[%s]",
                                address.getLatitude(),
                                address.getLongitude()
                        ));
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Failed in using Geocoder", e);
                }
                if (mGoogleMap != null) {
                    naksanLoca = new LatLng(address.getLatitude(), address.getLongitude());//현재 위도 경도 저장
                    mGoogleMap.addMarker(
                            new MarkerOptions().
                                    position(naksanLoca).
                                    title(addressstr).
                                    snippet(addressstr).
                                    alpha(1f)
                    );
                }
            }
        }
        if (naksanLoca != null) {
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(naksanLoca, 15));//그곳으로 카메라 옮기기
        }
    }


    private void requestMyLocation() {
        LocationManager manager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            long minTime = 60000;
            float minDistance = 0;
            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime,
                    minDistance,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            mLocation = location;
                            showCurrentLocation(location);
                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {
                        }

                        @Override
                        public void onProviderEnabled(String provider) {
                        }

                        @Override
                        public void onProviderDisabled(String provider) {
                        }
                    }
            );
            Location lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            mLocation = lastLocation;
            if (lastLocation != null) {
                showCurrentLocation(lastLocation);
            }
            manager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    minTime,
                    minDistance,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            mLocation = location;
                            showCurrentLocation(location);
                            addPictures(location);
                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {
                        }

                        @Override
                        public void onProviderEnabled(String provider) {
                        }

                        @Override
                        public void onProviderDisabled(String provider) {
                        }
                    }
            );
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void showCurrentLocation(Location location) {
        LatLng curPoint = new LatLng(location.getLatitude(), location.getLongitude());
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));
        showMyLocationMarker(location);
    }

    private void showMyLocationMarker(Location location) {
        if (myLocationMarker == null) {
            myLocationMarker = new MarkerOptions();
            myLocationMarker.position(new LatLng(location.getLatitude(), location.getLongitude()));
            myLocationMarker.title("● 현재 위치");

            myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation_yellow));
            mGoogleMap.addMarker(myLocationMarker);
        } else {
            myLocationMarker.position(new LatLng(location.getLatitude(), location.getLongitude()));
        }
    }

    private void addPictures(Location location) {
        LatLng naksanLoca1 = null;
        for (int i = 1; i < mainWaitList.size(); i++) {
            String addressstr = mainWaitList.get(i).getmstore();
            Log.d("test", addressstr);

            try {
                Geocoder geocoder = new Geocoder(this, Locale.KOREA);
                List<Address> addresses = geocoder.getFromLocationName(addressstr, 1);

                if (addresses.size() > 0) {
                    address = addresses.get(0);
                      /*  result.setText(String.format("[%s]\t[%s]",
                                address.getLatitude(),
                                address.getLongitude()
                        ));*/
                }
            } catch (IOException e) {
                Log.e(TAG, "Failed in using Geocoder", e);
            }
            if (mGoogleMap != null) {
                naksanLoca1 = new LatLng(address.getLatitude(), address.getLongitude());//현재 위도 경도 저장
                mGoogleMap.addMarker(
                        new MarkerOptions().
                                position(naksanLoca1).
                                title("                                    "+addressstr+"                                   ").
                                snippet(mainWaitList.get(i).getmwork1() + ": 시간 " + mainWaitList.get(i).getmtime1() + "분 " + mainWaitList.get(i).getmwork2()
                                + ": 시간 " + mainWaitList.get(i).getmtime2() + "분 " + mainWaitList.get(i).getmwork3()
                                + ":시간" + mainWaitList.get(i).getmtime3() + "분")

                );


            }
        }
    }


    @Override
    protected void onPause() {
        super.onPause();

        if (mGoogleMap != null) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
