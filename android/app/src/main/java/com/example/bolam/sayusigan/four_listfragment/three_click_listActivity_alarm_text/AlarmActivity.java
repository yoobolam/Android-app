package com.example.bolam.sayusigan.four_listfragment.three_click_listActivity_alarm_text;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bolam.sayusigan.four_listfragment.first.ListFragment;
import com.example.bolam.sayusigan.R;

import java.util.ArrayList;


public class AlarmActivity extends AppCompatActivity {
        public String aaa;
        public String bbb;
    EditText ccc;
    String alarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm1);
        ccc = (EditText) findViewById(R.id.editText);


        Intent intent1 = getIntent();
        aaa = intent1.getStringExtra("store1");
        bbb = intent1.getStringExtra("work1");
        Button button = (Button) findViewById(R.id.setalarm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarm = ccc.getText().toString();
                Toast.makeText(getApplicationContext(), aaa +"  "+ bbb +"  "+"대기시간 "  +alarm + "분전 알람 예정입니다!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

