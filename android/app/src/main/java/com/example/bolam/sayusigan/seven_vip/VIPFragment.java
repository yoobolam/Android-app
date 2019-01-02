package com.example.bolam.sayusigan.seven_vip;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bolam.sayusigan.ImeiData;
import com.example.bolam.sayusigan.R;
import com.example.bolam.sayusigan.four_listfragment.first.ApiService1;


import com.example.bolam.sayusigan.four_listfragment.first.RcntListItemView_list;
import com.example.bolam.sayusigan.seven_vip.RcntListItemView_VIP;

import com.example.bolam.sayusigan.seven_vip.Vip;
import com.example.bolam.sayusigan.three_main.MainActivity;
import com.example.bolam.sayusigan.two_select.RegisterAPIsaju;
import com.example.bolam.sayusigan.two_select.SelectActivity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
/**
 * A simple {@link Fragment} subclass.
 */
public class VIPFragment extends Fragment implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener {
    private ArrayList<Vip> mainWaitList = new ArrayList<Vip>();
    private boolean removeOk = false;
    ListViewAdapter adapter;
    Retrofit retrofit;
    ApiService1 apiService1;
    public static final String ROOT_URL = "http://52.78.230.83/connect/";
    public String store;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_vip, container, false);
        retrofit = new Retrofit.Builder().baseUrl(ApiService1.API_URL).build();
        apiService1 = retrofit.create(ApiService1.class);
        ((MainActivity) getActivity()).setActionBarTitle("자유시간");
        final ListView listView = (ListView) v.findViewById(R.id.viplistview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        Log.d("test", ImeiData.getInstace().getImei());
        adapter = new ListViewAdapter(mainWaitList);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);
        listView.setClickable(true);
        setServerData();
        return v;
    }

    @Override
    public void onClick(View v) {

    }
    private void setServerData() {
        Call<ResponseBody> comment = apiService1.getPostCommentStr(ImeiData.getInstace().getImei());
        comment.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    Log.v("Test", result);
                    try {
                        JSONArray jsonArray = new JSONArray(result); //json형식의 string을 객체에 저장

                        String vstore;
                        String vwork1;
                        String vwork2;
                        String vwork3;
                        String vtime1;
                        String vtime2;
                        String vtime3;
                        int h;
                        int m;
                        int vtime11;
                        int vtime22;
                        int vtime33;
                        //정수형: getInt(), 실수형: getDouble(), 문자: getString()
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);//객체 크기

                            vstore = jsonObject.getString("vstore"); //실제 php받아오는 값//스토이 이름
                            vwork1 = jsonObject.getString("vwork1");
                            vwork2 = jsonObject.getString("vwork2");
                            vwork3 = jsonObject.getString("vwork3");
                            vtime11 = jsonObject.getInt("vtime1");
                            m=vtime11/60;
                            h=m/60;
                            m=m%60;
                            vtime1=h+":"+m;

                            vtime22 = jsonObject.getInt("vtime2");
                            m=vtime22/60;
                            h=m/60;
                            m=m%60;
                            vtime2=h+":"+m;

                            vtime33 = jsonObject.getInt("vtime3");
                            m=vtime33/60;
                            h=m/60;
                            m=m%60;
                            vtime3=h+":"+m;

                            Log.v("Test", jsonObject.toString());

                            mainWaitList.add(new Vip(vstore, vwork1, vwork2, vwork3, vtime1,  vtime2, vtime3));
                        }
                        if(adapter != null){
                            adapter.notifyDataSetChanged();
                        }
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
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //error
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

    }



    class ListViewAdapter extends BaseAdapter {
        private final String TAG = ListViewAdapter.class.getSimpleName();
        private ArrayList<Vip> waitList = new ArrayList<Vip>();

        public ListViewAdapter(ArrayList<Vip> waitList) {
            this.waitList = waitList;
        }

        @Override
        public int getCount() {
            return waitList.size();
        }

        public void addItem(Vip vip) {
            waitList.add(vip);
        }

        @Override
        public Object getItem(int position) {
            return waitList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final RcntListItemView_VIP view;
            view = new RcntListItemView_VIP(getActivity());
            view.findViewById(R.id.viplist_item);
            view.setvstore(waitList.get(position).getvstore());
            view.setvwork1(waitList.get(position).getvwork1());
            view.setvwork2(waitList.get(position).getvwork2());
            view.setvwork3(waitList.get(position).getvwork3());
            view.setvtime1(waitList.get(position).getvtime1());
            view.setvtime2(waitList.get(position).getvtime2());
            view.setvtime3(waitList.get(position).getvtime3());


            Button work1 = (Button) view.findViewById(R.id.work1);
            work1.setOnClickListener(new ImageButton.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showMessage1(view, position);
                }
            });
            work1.setFocusable(false);

            Button work2 = (Button) view.findViewById(R.id.work2);
            work2.setOnClickListener(new ImageButton.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showMessage2(view, position);
                }
            });
            work2.setFocusable(false);

            Button work3 = (Button) view.findViewById(R.id.work3);
            work3.setOnClickListener(new ImageButton.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showMessage3(view, position);
                }
            });
            work3.setFocusable(false);

            return view;
        }
    }

    public void showMessage1(RcntListItemView_VIP view, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("대기표 받기");
        builder.setCancelable(false);
        builder.setMessage("대기표를 받으시겠습니까??\n\n");
        store = mainWaitList.get(position).getvstore();
        //getIntent().getExtras().getString("","");
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                insertVip1();
                Toast.makeText(getActivity(), "대기표 발급 완료!" + "\n" + "대기표를 새로고침 해주세요!", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showMessage2(RcntListItemView_VIP view, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("대기표 받기");
        builder.setCancelable(false);
        builder.setMessage("대기표를 받으시겠습니까??\n\n");
        store = mainWaitList.get(position).getvstore();
        Log.d("test",store);
        Log.d("test",store);
        Log.d("test",store);
        Log.d("test",store);
        Log.d("test",store);
        Log.d("test",store);
        Log.d("test",store);
        Log.d("test",store);
        //getIntent().getExtras().getString("","");
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                insertVip2();
                Toast.makeText(getActivity(), "대기표 생성 완료!" + "\n" + "대기표를 새로고침 해주세요!", Toast.LENGTH_LONG).show();

            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showMessage3(RcntListItemView_VIP view, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("대기표 받기");
        builder.setCancelable(false);
        builder.setMessage("대기표를 받으시겠습니까??\n\n");
        store = mainWaitList.get(position).getvstore();
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                insertVip3();
                Toast.makeText(getActivity(), "대기표 생성 완료!" + "\n" + "대기표를 새로고침 해주세요!", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void insertVip1() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();
        RegisterAPIWork1 api = adapter.create(RegisterAPIWork1.class);
        api.insertVip1(

                //Passing the values by getting it from editTexts
                ImeiData.getInstace().getImei(),
                store, //field1

                // editTextUsername.getText().toString(),//field2


                //Creating an anonymous callback
                new retrofit.Callback<retrofit.client.Response>() {
                    @Override
                    public void success(retrofit.client.Response result, retrofit.client.Response response) {
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
                        Toast.makeText(getActivity(), output, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }


    private void insertVip2() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();
        RegisterAPIWork2 api = adapter.create(RegisterAPIWork2.class);
        api.insertVip2(

                //Passing the values by getting it from editTexts
                ImeiData.getInstace().getImei(),
                store, //field1

                // editTextUsername.getText().toString(),//field2


                //Creating an anonymous callback
                new retrofit.Callback<retrofit.client.Response>() {
                    @Override
                    public void success(retrofit.client.Response result, retrofit.client.Response response) {
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
                        Toast.makeText(getActivity(), output, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    private void insertVip3() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();
        RegisterAPIWork3 api = adapter.create(RegisterAPIWork3.class);
        api.insertVip3(

                //Passing the values by getting it from editTexts
                ImeiData.getInstace().getImei(),
                store, //field1

                // editTextUsername.getText().toString(),//field2


                //Creating an anonymous callback
                new retrofit.Callback<retrofit.client.Response>() {
                    @Override
                    public void success(retrofit.client.Response result, retrofit.client.Response response) {
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
                        Toast.makeText(getActivity(), output, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        mainWaitList.clear();
        setServerData();
    }
}
