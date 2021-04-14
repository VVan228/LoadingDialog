package com.example.loadingdialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txt = findViewById(R.id.text);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dial = new DialogFragment();
                Loading load = new Loading(Color.rgb(255,250,250), Color.TRANSPARENT, Color.RED, 20.0f);
                load.setOnCancelListener(new onCancelListener() {
                    @Override
                    public void onCancel() {
                        Log.d("animation", "cancelled");
                    }
                });
                load.show(getSupportFragmentManager(), "dial");
            }
        });

    }
}