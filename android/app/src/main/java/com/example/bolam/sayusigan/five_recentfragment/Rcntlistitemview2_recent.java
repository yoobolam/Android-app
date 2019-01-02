package com.example.bolam.sayusigan.five_recentfragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bolam.sayusigan.R;

/**
 * Created by B108 on 2018-04-03.
 */

public class Rcntlistitemview2_recent extends LinearLayout {
    private static final String TAG = Rcntlistitemview2_recent.class.getSimpleName();

    TextView rcntTime1;
    TextView rcntTime2;
    TextView rcntTime3;
    TextView rcntStore;
    TextView rcntWork1;
    TextView rcntWork2;
    TextView rcntWork3;



    public Rcntlistitemview2_recent(Context context) {
        super(context);
        init(context);
    }

    public Rcntlistitemview2_recent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.rcnt_list_items2, this, true);


        rcntTime1 = (TextView) view.findViewById(R.id.rcntTime1);
        rcntTime2 = (TextView) view.findViewById(R.id.rcntTime2);
        rcntTime3 = (TextView) view.findViewById(R.id.rcntTime3);
        rcntStore = (TextView) view.findViewById(R.id.rcntStore);
        rcntWork1 = (TextView) view.findViewById(R.id.rcntWork1);
        rcntWork2 = (TextView) view.findViewById(R.id.rcntWork2);
        rcntWork3 = (TextView) view.findViewById(R.id.rcntWork3);

       Log.i(TAG, rcntStore.getText().toString() + "///////////" + rcntTime1.getText().toString());
    }



    public void setwStore(String wStore) {
        rcntStore.setText(wStore);
    }
    public void setwTime1(String wTime1) {
        Log.i(TAG,"순서2");
        rcntTime1.setText(wTime1);
    }
    public void setwTime2(String wTime2) {
        Log.i(TAG,"순서2");
        rcntTime2.setText(wTime2);
    }
    public void setwTime3(String wTime3) {
        Log.i(TAG,"순서2");
        rcntTime3.setText(wTime3);
    }
    public void setwWork1(String wWork1) { rcntWork1.setText(wWork1);}
    public void setwWork2(String wWork2) { rcntWork2.setText(wWork2);}
    public void setwWork3(String wWork3) { rcntWork3.setText(wWork3);}
}
