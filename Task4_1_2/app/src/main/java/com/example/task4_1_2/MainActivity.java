package com.example.task4_1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button okBtn;
    private EditText workoutTime, restTime;

    public static final String EXTRA_WORKOUT = "com.example.android.task31.extra.WORKOUT";
    public static final String EXTRA_REST = "com.example.android.task31.extra.REST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        okBtn = findViewById(R.id.okBtn);
        workoutTime = findViewById(R.id.workoutEdit);
        restTime = findViewById(R.id.restEdit);


    }

    public void runTimer(View view) {

            if(workoutTime.getText().toString().trim().length() == 0 || restTime.getText().toString().trim().length() == 0)
            {
                Toast.makeText(MainActivity.this, "Enter Time Duration", Toast.LENGTH_SHORT).show();
            }else
            {
                Intent intent = new Intent(this, runTimer.class);
                String workoutMSG = workoutTime.getText().toString();
                String restMSG = restTime.getText().toString();

                intent.putExtra(EXTRA_WORKOUT, workoutMSG);
                intent.putExtra(EXTRA_REST, restMSG);
                startActivity(intent);
            }

    }
}