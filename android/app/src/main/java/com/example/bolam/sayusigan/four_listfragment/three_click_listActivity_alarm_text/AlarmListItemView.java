package com.example.bolam.sayusigan.four_listfragment.three_click_listActivity_alarm_text;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bolam.sayusigan.R;

/**
 * Created by bolam on 2018-04-07.
 */
public class AlarmListItemView extends LinearLayout {
    TextView rcntaStore, rcntaTime;

    public AlarmListItemView(Context context) {
        super(context);

        init(context);
    }

    public AlarmListItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.alarm_list_items, this, true); //history_item.xml 참조가능

        rcntaStore = (TextView) findViewById(R.id.rcntaStore);
        rcntaTime = (TextView) findViewById(R.id.rcntaTime);


    }

    public void setaStore(String aStore){
        rcntaStore.setText(aStore);
    }
    public void setaTime(String aTime){
        rcntaTime.setText(aTime);
    }

}