package com.example.bolam.sayusigan.four_listfragment.first;

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

public class RcntListItemView_list extends LinearLayout {
    private static final String TAG = RcntListItemView_list.class.getSimpleName();
    TextView rcntOrder;
    TextView rcntTime;
    TextView rcntStore;
    TextView rcntWork;
    TextView endOrder;


    public RcntListItemView_list(Context context) {
        super(context);
        init(context);
    }

    public RcntListItemView_list(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.rcnt_list_items, this, true);

        rcntOrder = (TextView) view.findViewById(R.id.rcntOrder);
        rcntTime = (TextView) view.findViewById(R.id.rcntTime);
        rcntStore = (TextView) view.findViewById(R.id.rcntStore);
        rcntWork = (TextView) view.findViewById(R.id.rcntWork);
        endOrder = (TextView) view.findViewById(R.id.endOrder);
        Log.i(TAG, rcntOrder.getText().toString() + "///////////" + rcntTime.getText().toString());
    }

    public void setwOrder(String wOrder) {
        rcntOrder.setText(wOrder);
    }

    public void setwStore(String wStore) {
        rcntStore.setText(wStore);
    }

    public void seteOrder(String eOrder) {
        endOrder.setText(eOrder);
    }

    public void setwWork(String wWork) {
        rcntWork.setText(wWork);
    }

    public void setwTime(String wTime) {
        Log.i(TAG, "순서2");
        rcntTime.setText(wTime);
    }

}
