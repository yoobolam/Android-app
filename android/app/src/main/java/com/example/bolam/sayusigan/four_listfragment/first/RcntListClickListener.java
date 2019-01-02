package com.example.bolam.sayusigan.four_listfragment.first;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import com.example.bolam.sayusigan.four_listfragment.three_click_listActivity_alarm_text.AlarmActivity;
import com.example.bolam.sayusigan.four_listfragment.two_click_listfragment_listview.ListActivity;
import com.example.bolam.sayusigan.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by bolam on 2018-04-11.
 */

class RcntListClickListener implements View.OnClickListener {
    private int position;
    private Context context;
    public RcntListClickListener(int position) {
        this.position = position;
    }
      private ArrayList<Waiting> waitList;

    public RcntListClickListener(int position, Context context,ArrayList<Waiting> waitList) {
        this.position = position;
        this.context = context;
        this.waitList = waitList;

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rcnt_list_items:
          // context.startActivity(new Intent(context,ListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
                Intent intent =  new Intent(context,ListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.putExtra("store",waitList.get(position).getwStore());
                intent.putExtra("work",waitList.get(position).getwWork());
                intent.putExtra("time",String.valueOf(waitList.get(position).getwTime()));
                intent.putExtra("order",String.valueOf(waitList.get(position).getwOrder()));

                context.startActivity(intent);

            break;
        }
    }

}
