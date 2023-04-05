package com.example.task2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class TemperatureActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView textview;
    private EditText editText;
    private String source;
    private String destination;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        textview = findViewById(R.id.temperature_view);
        editText = findViewById(R.id.temperature_edit);

        //Create the source spinner
        Spinner source_spinner = findViewById(R.id.source_temperature_spinner);
        if (source_spinner != null) {
            source_spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.temperature_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (source_spinner != null) {
            source_spinner.setAdapter(adapter);
        }

        source_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                source = source_spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //Create the destination spinner
        Spinner destination_spinner = findViewById(R.id.destination_temperature_spinner);
        if(destination_spinner != null){
            destination_spinner.setOnItemSelectedListener(this);
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(destination_spinner != null){
            destination_spinner.setAdapter(adapter);
        }

        destination_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                destination = destination_spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void temperature_convert(View view) {
        String s = editText.getText().toString();
        double so = Double.parseDouble(s);
        switch (source){
            case "Celsius":
                switch (destination){
                    case "Celsius":
                        textview.setText("The result is " + s);
                        break;
                    case "Fahrenheit":
                        textview.setText("The result is " + (so*1.8)+32);
                        break;
                    case "Kelvin":
                        textview.setText("The result is " + (so+273.15));
                        break;
                    default:
                        break;
                }break;
            case "Fahrenheit":
                switch (destination){
                    case "Celsius":
                        textview.setText("The result is " + (so-32)/1.8);
                        break;
                    case "Fahrenheit":
                        textview.setText("The result is " + s);
                        break;
                    case "Kelvin":
                        textview.setText("The result is " + ((so-32)/1.8)+273.15);
                        break;
                    default:
                        break;
                }break;
            case "Kelvin":
                switch (destination){
                    case "Celsius":
                        textview.setText("The result is " + (so-273.15));
                        break;
                    case "Fahrenheit":
                        textview.setText("The result is " + (((so-273.15)*1.8)+32));
                        break;
                    case "Kelvin":
                        textview.setText("The result is " + s);
                        break;
                    default:
                        break;
                }break;
            default:
                break;
        }
    }
}