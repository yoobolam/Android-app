package com.example.bolam.sayusigan.seven_vip;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.bolam.sayusigan.R;

/**
 * Created by bolam on 2018-05-23.
 */

public class RcntListClickListener1 implements View.OnClickListener {
private int position;
private Context context;
public RcntListClickListener1(int position) {
        this.position = position;
        }

public RcntListClickListener1(int position, Context context) {
        this.position = position;
        this.context = context;
        }
@Override
public void onClick(View v) {
        switch (v.getId()){
        case R.id.viplist_item:
        //context.startActivity(new Intent(context,ListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
        break;
        }
        }

        }

