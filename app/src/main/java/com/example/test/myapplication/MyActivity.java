package com.example.test.myapplication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MyActivity extends Activity {

    Button mClickMeButton;
    TextView mHelloWorldTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("==== onCreate is called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mHelloWorldTextView = (TextView) findViewById(R.id.helloWorldTextView);
        mClickMeButton = (Button) findViewById(R.id.clickMeBtn);
        mClickMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("==== onClick is called");
                mHelloWorldTextView.setText("HEY WORLD");
                sendIntent();
            }
        });
        LocalBroadcastManager.getInstance(this).registerReceiver(aLBReceiver,
                new IntentFilter("anEvent"));
    }
    @Override
    protected void onDestroy() {
        System.out.println("==== onDestroy is called");
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(aLBReceiver);
        super.onDestroy();
    }

    void sendIntent (){
        System.out.println("==== sendIntent is called");
        Intent intent = new Intent("anEvent");
        intent.putExtra("key", "This is an event");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private BroadcastReceiver aLBReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("==== Intent is received");
            mHelloWorldTextView.setText("Received an Intent");
        }
    };
}
