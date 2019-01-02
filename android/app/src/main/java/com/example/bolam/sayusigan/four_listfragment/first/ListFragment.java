package com.example.bolam.sayusigan.four_listfragment.first;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.widget.SwipeRefreshLayout;
import com.example.bolam.sayusigan.ImeiData;
import com.example.bolam.sayusigan.R;
import com.example.bolam.sayusigan.four_listfragment.two_click_listfragment_listview.ListActivity;
import com.example.bolam.sayusigan.three_main.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Time;
import java.util.ArrayList;
import okhttp3.ResponseBody;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit2.Call;
import android.content.Context;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener{

    private ArrayList<Waiting> mainWaitList = new ArrayList<Waiting>();
    private boolean removeOk = false;
    ListViewAdapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private static final String TAG_SN = "store";
    private static final String TAG_ST = "select";
    private static final String TAG_WN = "number";
    private static final String TAG_Time = "time";
    Retrofit retrofit;
    ApiService1 apiService1;
    public static final String ROOT_URL = "http://52.78.230.83/connect/";
    public String store;
    public String work;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        retrofit=new Retrofit.Builder().baseUrl(ApiService1.API_URL).build();
        apiService1=retrofit.create(ApiService1.class);
        ((MainActivity)getActivity()).setActionBarTitle("자유시간");
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );

        final ListView listView = (ListView) v.findViewById(R.id.mainListView);


        Log.d("test", ImeiData.getInstace().getImei());

        adapter = new ListViewAdapter(mainWaitList);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);
        listView.setClickable(true);

        setServerData();



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), position + "번 쨰 눌림", Toast.LENGTH_LONG).show();
                Toast.makeText(getActivity(), "눌림", Toast.LENGTH_LONG).show();
                Log.d("포지션",position+"");




            }
        });
        listView.setFocusable(false);
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), position + "번 쨰 눌림", Toast.LENGTH_LONG).show();
                Toast.makeText(getActivity(), "눌림", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return v;
    }
    @Override
    public void onClick(View v) {

    }
    private void setServerData(){
        Call<ResponseBody> comment = apiService1.getCommentStr(ImeiData.getInstace().getImei());
        comment.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    Log.v("Test", result);
                    try {
                        JSONArray jsonArray = new JSONArray(result); //json형식의 string을 객체에 저장

                        String store;
                        String select;
                        int number;
                        int time1;
                        String eOrder;
                        int h;
                        int m;

                        String time2;
                        //정수형: getInt(), 실수형: getDouble(), 문자: getString()
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);//객체 크기

                            store = jsonObject.getString("store"); //실제 php받아오는 값//스토이 이름
                            select = jsonObject.getString("select"); //실제 php받아오는 값//스토이 업무
                            number = jsonObject.getInt("number"); //실제 php받아오는 값//시간
                            time1 = jsonObject.getInt("time");
                            m=time1/60;
                            h=m/60;
                            m=m%60;
                            time2=h+":"+m;

                            eOrder = jsonObject.getString("eOrder");

                            Log.v("Test", jsonObject.toString());

                            mainWaitList.add(new Waiting(number,time2,store,select,eOrder));
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
        private ArrayList<Waiting> waitList = new ArrayList<Waiting>();

        public ListViewAdapter(ArrayList<Waiting> waitList) {this.waitList = waitList;}

        @Override
        public int getCount() {return waitList.size();}
        public void addItem(Waiting waiting) {waitList.add(waiting);}
        @Override
        public Object getItem(int position) {return waitList.get(position);}
        @Override
        public long getItemId(int position) {return position;}
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final RcntListItemView_list view;
            view = new RcntListItemView_list(getActivity());
            /*
            전체 클릭
             */

                if (String.valueOf(waitList.get(position).getwTime()).equals("0:0")) {
                    mainWaitList.remove(position);
                    adapter.notifyDataSetChanged();
                }

            if(waitList.size()>0) {
                view.findViewById(R.id.rcnt_list_items).setOnClickListener(new RcntListClickListener(position, getActivity(), waitList));
                view.setwTime(String.valueOf(waitList.get(position).getwTime()));
                view.setwOrder(String.valueOf(waitList.get(position).getwOrder()));
                view.setwStore(waitList.get(position).getwStore());
                view.setwWork(waitList.get(position).getwWork());
                view.seteOrder(waitList.get(position).geteOrder());
            }
            //TimeSet(position);


            ImageButton deletebutton = (ImageButton) view.findViewById(R.id.deletebutton);
            deletebutton.setOnClickListener(new ImageButton.OnClickListener() {
                @Override
                public void onClick(View v) {showMessage2(view, position);}});
            deletebutton.setFocusable(false);

            ImageButton delaybutton = (ImageButton) view.findViewById(R.id.delaybutton);
            delaybutton.setOnClickListener(new ImageButton.OnClickListener() {
                @Override
                public void onClick(View v) {showMessage1(view, position);}});
            delaybutton.setFocusable(false);
            return view;
        }
    }

    public void showMessage1(RcntListItemView_list view, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("순번 미루기");
        builder.setCancelable(false);
        builder.setMessage("순번을 미루시겠습니까?\n\n");
        builder.setMessage("현재 대기팀" + mainWaitList.get(position).geteOrder() + "명");
          store=mainWaitList.get(position).getwStore() ;
          work=mainWaitList.get(position).getwWork();

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                insertPushBack();
                Toast.makeText(getActivity(),"미루기 완료!"+"\n"+"대기표 새로고침을 해주세요",Toast.LENGTH_LONG).show();
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
    public void showMessage2(RcntListItemView_list view, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("대기표 취소");
        builder.setMessage("대기표를 취소하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_delete);
        store=mainWaitList.get(position).getwStore();
        work=mainWaitList.get(position).getwWork();

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                insertDelete();
                Toast.makeText(getActivity(),"취소 완료!",Toast.LENGTH_LONG).show();
                mainWaitList.remove(position);
                adapter.notifyDataSetChanged();
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
    private void insertPushBack() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();
        RegisterAPIPushBack api = adapter.create(RegisterAPIPushBack.class);
        api.insertPushBack(
                ImeiData.getInstace().getImei(),
                store, //field1
                work,
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

    private void insertDelete() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();
        RegisterAPIDelete api = adapter.create(RegisterAPIDelete.class);
        api.insertDelete(
                ImeiData.getInstace().getImei(),
                store, //field1
                work,
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

