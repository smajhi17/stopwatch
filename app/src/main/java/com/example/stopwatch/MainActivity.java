package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvTimer;
    private Button btnStart, btnStop, btnReset;
    private long startTime = 0L;
    private Handler handler = new Handler();
    private Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTimer = findViewById(R.id.textView);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnReset = findViewById(R.id.btnReset);
    }

    public void startTimer(View view) {
        startTime = System.currentTimeMillis();
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                long millis = System.currentTimeMillis() - startTime;
                int seconds = (int) (millis / 1000);
                int minutes = seconds / 60;
                seconds %= 60;
                int hours = minutes / 60;
                minutes %= 60;
                tvTimer.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
                handler.postDelayed(this, 500);
            }
        }, 0);
        btnStart.setEnabled(false);
        btnStop.setEnabled(true);
    }

    public void stopTimer(View view) {
        handler.removeCallbacks(runnable);
        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
    }

    public void resetTimer(View view) {
        handler.removeCallbacks(runnable);
        tvTimer.setText("00:00:00");
        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
    }
}