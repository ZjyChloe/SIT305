package com.example.task2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void length_conversion(View view) {
        Intent intent = new Intent(this, LengthActivity.class);
        startActivity(intent);
    }

    public void weight_conversion(View view) {
        Intent intent = new Intent(this, WeightActivity.class);
        startActivity(intent);
    }

    public void temperature_conversion(View view) {
        Intent intent = new Intent(this, TemperatureActivity.class);
        startActivity(intent);
    }
}