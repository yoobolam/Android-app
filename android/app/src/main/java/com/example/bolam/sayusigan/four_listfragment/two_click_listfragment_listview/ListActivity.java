package com.example.bolam.sayusigan.four_listfragment.two_click_listfragment_listview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.bolam.sayusigan.four_listfragment.three_click_listActivity_alarm_text.AlarmActivity;
import com.example.bolam.sayusigan.R;

public class ListActivity extends AppCompatActivity {
    TextView getstore;
    TextView getwork;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final TextView getstore = (TextView) findViewById(R.id.getstore);
        final TextView getwork = (TextView) findViewById(R.id.getwork);
        final TextView gettime = (TextView) findViewById(R.id.gettime);
        TextView getorder = (TextView) findViewById(R.id.getorder);
        Intent intent = getIntent();
        getstore.setText(intent.getStringExtra("store"));
        getwork.setText(intent.getStringExtra("work"));
        gettime.setText(intent.getStringExtra("time"));
        getorder.setText(intent.getStringExtra("order"));

        final String store1=intent.getStringExtra("store");
        final String work1=intent.getStringExtra("work");

        TextView Alarmbutton = (TextView) findViewById(R.id.Alarmbutton);
        Alarmbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 =  new Intent(getApplicationContext(),AlarmActivity.class);
                intent1.putExtra("store1",store1);
                intent1.putExtra("work1", work1);
                startActivity(intent1);
    }
});
    }
}
