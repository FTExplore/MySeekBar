package com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.haha.R;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "LightSeekBar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LightSeekBar seekBar = (LightSeekBar) findViewById(R.id.seek);

        seekBar.setLEVEL_MAX(20);

        seekBar.setOnProgerssBarListener(new LightSeekBar.OnProgerssBarListener() {
            @Override
            public void onProgressChange(int i) {
                Log.d(TAG, i + "");
            }
        });
    }
}
