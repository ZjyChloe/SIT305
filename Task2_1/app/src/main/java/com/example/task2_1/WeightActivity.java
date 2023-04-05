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

public class WeightActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView textview;
    private EditText editText;
    private String source;
    private String destination;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        textview = findViewById(R.id.weight_view);
        editText = findViewById(R.id.weight_edit);

        //Create the source spinner
        Spinner source_spinner = findViewById(R.id.source_weight_spinner);
        if (source_spinner != null) {
            source_spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.weight_array, android.R.layout.simple_spinner_item);
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
        Spinner destination_spinner = findViewById(R.id.destination_weight_spinner);
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

    public void weight_convert(View view) {
        String s = editText.getText().toString();
        double so = Double.parseDouble(s);
        switch (source){
            case "pound":
                switch (destination){
                    case "pound":
                        textview.setText("The result is " + s);
                        break;
                    case "ounce":
                        textview.setText("The result is " + so*(0.0283495/0.453592));
                        break;
                    case "ton":
                        textview.setText("The result is " + so*(907.185/0.453592));
                        break;
                    default:
                        break;
                }break;
            case "ounce":
                switch (destination){
                    case "pound":
                        textview.setText("The result is " + so*(0.453592/0.0283495));
                        break;
                    case "ounce":
                        textview.setText("The result is " + s);
                        break;
                    case "ton":
                        textview.setText("The result is " + so*(907.185/0.0283495));
                        break;
                    default:
                        break;
                }break;
            case "ton":
                switch (destination){
                    case "pound":
                        textview.setText("The result is " + so*(0.453592/907.185));
                        break;
                    case "ounce":
                        textview.setText("The result is " + so*(0.0283495/907.185));
                        break;
                    case "ton":
                        textview.setText("The result is " + s);
                        break;
                    default:
                        break;
                }break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}