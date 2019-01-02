package com.example.bolam.sayusigan.five_recentfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.bolam.sayusigan.ImeiData;
import com.example.bolam.sayusigan.R;
import com.example.bolam.sayusigan.four_listfragment.first.ApiService1;
import com.example.bolam.sayusigan.three_main.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG_T1 = "T1";
    private static final String TAG_T2 = "T2";
    private static final String TAG_T3 = "T3";
    private static final String TAG_STORE = "STORE";
    private static final String TAG_W1 = "W1";
    private static final String TAG_W2 = "W2";
    private static final String TAG_W3 = "W3";
    SwipeRefreshLayout mSwipeRefreshLayout;
    Retrofit retrofit;
    ApiService1 apiService1;
    ArrayList<HashMap<String, String>> mArrayList;
    ListView mlistView;

    public RecentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recent, container, false);
        ((MainActivity) getActivity()).setActionBarTitle("자유시간");
        retrofit = new Retrofit.Builder().baseUrl(ApiService1.API_URL).build();
        apiService1 = retrofit.create(ApiService1.class);
        mlistView = (ListView) v.findViewById(R.id.listview);
        mArrayList = new ArrayList<>();
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        setServerData();
        Log.d("test",ImeiData.getInstace().getImei());
        return v;

    }
private void setServerData(){
    Call<ResponseBody> comment = apiService1.getPostComment(ImeiData.getInstace().getImei());
                            comment.enqueue(new Callback<ResponseBody>()

    {
        @Override
        public void onResponse (Call < ResponseBody > call, Response < ResponseBody > response){
        try {
            String result = response.body().string();
            Log.v("Test", result);
            try {
                JSONArray jsonArray = new JSONArray(result); //json형식의 string을 객체에 저장

                int T11;
                int T22;
                int T33;
                String T1;
                String T2;
                String T3;
                String STORE;
                String W1;
                String W2;
                String W3;
                int h;
                int m;

                //정수형: getInt(), 실수형: getDouble(), 문자: getString()
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);//객체 크기

                    T11 = jsonObject.getInt("waitingtime1");
                    m=T11/60;
                    h=m/60;
                    m=m%60;
                    T1=h+":"+m;
                    T22 = jsonObject.getInt("waitingtime2");
                    m=T22/60;
                    h=m/60;
                    m=m%60;
                    T2=h+":"+m;

                    T33 = jsonObject.getInt("waitingtime3");
                    m=T33/60;
                    h=m/60;
                    m=m%60;
                    T3=h+":"+m;
                    STORE = jsonObject.getString("wentstore"); //실제 php받아오는 값//시간
                    W1 = jsonObject.getString("wentwork1"); //실제 php받아오는 값//시간
                    W2 = jsonObject.getString("wentwork2"); //실제 php받아오는 값//시간
                    W3 = jsonObject.getString("wentwork3"); //실제 php받아오는 값//시간

                    Log.v("Test", jsonObject.toString());

                    HashMap<String, String> hashMap = new HashMap<>();

                    hashMap.put(TAG_T1, T1);
                    hashMap.put(TAG_T2, T2);
                    hashMap.put(TAG_T3, T3);
                    hashMap.put(TAG_STORE, STORE);
                    hashMap.put(TAG_W1, W1);
                    hashMap.put(TAG_W2, W2);
                    hashMap.put(TAG_W3, W3);

                    Log.v("Test", hashMap.toString());

                    mArrayList.add(hashMap);
                }
                ListAdapter adapter = new SimpleAdapter(
                        getActivity(), mArrayList, R.layout.rcnt_list_items2,
                        new String[]{TAG_T1, TAG_T2, TAG_T3, TAG_STORE, TAG_W1, TAG_W2, TAG_W3},
                        new int[]{R.id.rcntTime1, R.id.rcntTime2, R.id.rcntTime3,
                                R.id.rcntStore, R.id.rcntWork1, R.id.rcntWork2, R.id.rcntWork3}
                );
                mlistView.setAdapter(adapter);
                mlistView.setTextFilterEnabled(true);
                mlistView.setClickable(true);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
        @Override
        public void onFailure (Call < ResponseBody > call, Throwable t){
            mSwipeRefreshLayout.setRefreshing(false);
    }
    });
}
    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        mArrayList.clear();
        setServerData();
    }
}
