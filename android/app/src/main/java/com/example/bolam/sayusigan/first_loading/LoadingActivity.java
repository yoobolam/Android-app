package com.example.bolam.sayusigan.first_loading;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.example.bolam.sayusigan.R;
import com.example.bolam.sayusigan.three_main.MainActivity;
import com.example.bolam.sayusigan.two_select.SelectActivity;

public class LoadingActivity extends AppCompatActivity {

    private static final String TAG = "NFC";
    private NfcAdapter nfcAdapter;
    private PendingIntent nfcPendingIntent;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, this.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        /*startLoading();*/
        img = (ImageView) findViewById(R.id.img);

        img.post(new Runnable() {
            @Override
            public void run() {
                ((AnimationDrawable) img.getBackground()).start();
            }
        });
    }

    private void startSelectActivity() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    private void startMainActivity() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        img = (ImageView) findViewById(R.id.img);

        img.post(new Runnable() {
            @Override
            public void run() {
                ((AnimationDrawable) img.getBackground()).start();
            }
        });
        if (getIntent() != null) {
            Intent intent = getIntent();
            String action = intent.getAction();
            Log.d(TAG, "onResume:  " + action);
            if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
                //startMainActivity();
                Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

                Ndef ndefTag = Ndef.get(tag);
// 태그 크기
                int size = ndefTag.getMaxSize();
// 쓰기 가능 여부
                boolean writable = ndefTag.isWritable();
// 태그 타입
                String type = ndefTag.getType();
// 태그 ID
                String id = byteArrayToHexString(tag.getId());
                Log.d(TAG, "tag ID = " + tag.getId().toString());
                Log.d(TAG, "tag ID = " + tag.getId().toString());
                Log.d(TAG, "tag ID = " + tag.getId().toString());
                Log.d(TAG, "tag ID = " + tag.getId().toString());
                Log.d(TAG, "tag ID = " + tag.getId().toString());
                startSelectActivity();
                startMainActivity();
            } else {
                startSelectActivity();
            }
        }

    }

    public String byteArrayToHexString(byte[] b) {
        int len = b.length;
        String data = new String();

        for (int i = 0; i < len; i++) {
            data += Integer.toHexString((b[i] >> 4) & 0xf);
            data += Integer.toHexString(b[i] & 0xf);
        }
        return data;
    }
}
   /* private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),SelectActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    };*/


