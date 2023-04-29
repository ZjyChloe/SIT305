package com.example.task4_1_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class runTimer extends AppCompatActivity {

    private TextView timeLeft,timeModeTv;
    private Button startPause;
    private ProgressBar progressBar;

    private Boolean isStart = true;
    private Integer workoutTimeSelected = 0, restTimeSelected = 0, timeSelected = 0;
    private CountDownTimer timeCountDown = null;
    private Long pauseOffSet = Long.valueOf(0);
    private Integer timeProgress = 0;
    private String workoutMSG, restMSG;
    private boolean mTimerRunning;
    private boolean timeMode; //workout time: true, rest time: false

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_timer);

        Intent intent = getIntent();
        workoutMSG = intent.getStringExtra(MainActivity.EXTRA_WORKOUT);
        restMSG = intent.getStringExtra(MainActivity.EXTRA_REST);

        timeLeft = findViewById(R.id.tvTimeLeft);
        timeModeTv = findViewById(R.id.WorkRestTv);
        startPause = findViewById(R.id.StartPauseBtn);
        progressBar = findViewById(R.id.pbTimer);
        mTimerRunning = false;
        timeMode = true;
        timeLeft.setText(workoutMSG);
        workoutTimeSelected = Integer.valueOf(workoutMSG);
        restTimeSelected = Integer.valueOf(restMSG);

        startPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimerRunning){
                    pauseTimer();
                }else {
                    startTimer(pauseOffSet);
                }
            }
        });
    }


    private void startTimer(Long pauseOffSetL){
        startPause.setText("Pause");
        mTimerRunning = true;


        if(timeMode)
        {
            timeSelected = workoutTimeSelected;
        }else
        {
            timeSelected = restTimeSelected;
        }
        progressBar.setMax(timeSelected);

        progressBar.setProgress(timeProgress);
        timeCountDown = new CountDownTimer(
                Long.valueOf(timeSelected*1000) - pauseOffSetL*1000, 1000
        ) {
            @Override
            public void onTick(long p0) {
                timeProgress++;
                pauseOffSet = Long.valueOf(timeSelected) - p0/1000;
                progressBar.setProgress(timeSelected-timeProgress);
                timeLeft.setText(String.valueOf (timeSelected - timeProgress));
            }

            @Override
            public void onFinish() {
                if(timeMode)
                {
                    alert();
                    timeMode = false;
                    timeModeTv.setText("Rest Time");
                    resetTime();
                }else
                {
                    alert();
                    timeMode = true;
                    timeModeTv.setText("Workout Time");
                    resetTime();
                }
            }
        }.start();
    }

    private void resetTime()
    {
        if(timeCountDown != null)
        {
            if (timeMode)
            {
                progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.blue_progressbar_background));
                timeCountDown.cancel();
                timeProgress=0;
                timeSelected=workoutTimeSelected;
                pauseOffSet=Long.valueOf(0);
                timeCountDown=null;
                progressBar.setProgress(0);
                timeLeft.setText(workoutMSG);
                startTimer(pauseOffSet);
            }else
            {
                progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.gray_progressbar_background));
                timeCountDown.cancel();
                timeProgress=0;
                timeSelected=restTimeSelected;
                pauseOffSet=Long.valueOf(0);
                timeCountDown=null;
                progressBar.setProgress(0);
                timeLeft.setText(restMSG);
                startTimer(pauseOffSet);
            }
        }
    }

    private void pauseTimer()
    {
        timeCountDown.cancel();
        mTimerRunning = false;
        startPause.setText("Start");
    }

    private void alert()
    {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), alarmSound);
        r.play();
    }
}