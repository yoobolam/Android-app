package com.example.bolam.sayusigan.seven_vip;

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

public class RcntListItemView_VIP extends LinearLayout {
    private static final String TAG = RcntListItemView_VIP.class.getSimpleName();
    TextView vipstore;
    TextView vipwork1;
    TextView vipwork2;
    TextView vipwork3;
    TextView viptime1;
    TextView viptime2;
    TextView viptime3;

    public RcntListItemView_VIP(Context context) {
        super(context);
        init(context);
    }

    public RcntListItemView_VIP(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.viplist_item, this, true);

        vipstore = (TextView) view.findViewById(R.id.vipstore);
        vipwork1 = (TextView) view.findViewById(R.id.vipwork1);
        vipwork2 = (TextView) view.findViewById(R.id.vipwork2);
        vipwork3 = (TextView) view.findViewById(R.id.vipwork3);
        viptime1 = (TextView) view.findViewById(R.id.viptime1);
        viptime2 = (TextView) view.findViewById(R.id.viptime2);
        viptime3 = (TextView) view.findViewById(R.id.viptime3);

        Log.i(TAG, vipstore.getText().toString() + "///////////" + viptime1.getText().toString());
    }
    public void setvstore(String vstore){vipstore.setText(vstore);}
    public void setvwork1(String vwork1){vipwork1.setText(vwork1);}
    public void setvwork2(String vwork2){vipwork2.setText(vwork2);}
    public void setvwork3(String vwork3){vipwork3.setText(vwork3);}
    public void setvtime1(String vtime1){viptime1.setText(vtime1);}
    public void setvtime2(String vtime2){viptime2.setText(vtime2);}
    public void setvtime3(String vtime3){viptime3.setText(vtime3);}



    }

    

    
    

