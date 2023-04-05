package com.example.task2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LengthActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView textview;
    private EditText editText;
    private String source;
    private String destination;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        textview = findViewById(R.id.length_view);
        editText = findViewById(R.id.length_edit);

        //Create the source spinner
        Spinner source_spinner = findViewById(R.id.source_length_spinner);
        if(source_spinner != null){
            source_spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.length_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(source_spinner != null){
            source_spinner.setAdapter(adapter);
        }

        source_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                source = source_spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //Create the destination spinner
        Spinner destination_spinner = findViewById(R.id.destination_length_spinner);
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


    public void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


    public void length_convert(View view) {
            String s = editText.getText().toString();
            double so = Double.parseDouble(s);
            //Toast.makeText(this, "the value is "+de, Toast.LENGTH_SHORT).show();
        switch (source){
            case "inch":
                switch (destination){
                    case "inch":
                        textview.setText("The result is " + s);
                        break;
                    case "foot":
                        textview.setText("The result is "+so*(30.48/2.45));
                        break;
                    case "yard":
                        textview.setText("The result is "+so*(91.44/2.45));
                        break;
                    case "mile":
                        textview.setText("The result is almost equal to 0");
                        break;
                    default:
                        break;
                }break;
            case "foot":
                switch (destination){
                    case "inch":
                        textview.setText("The result is " + so*(2.54/30.48));
                        break;
                    case "foot":
                        textview.setText("The result is "+s);
                        break;
                    case "yard":
                        textview.setText("The result is "+so*(91.44/30.48));
                        break;
                    case "mile":
                        textview.setText("The result is almost equal to 0");
                        break;
                    default:
                        break;
                }break;
            case "yard":
                switch (destination){
                    case "inch":
                        textview.setText("The result is " + so*(2.54/91.44));
                        break;
                    case "foot":
                        textview.setText("The result is " + so*(30.48/91.44));
                        break;
                    case "yard":
                        textview.setText("The result is "+s);
                        break;
                    case "mile":
                        textview.setText("The result is almost equal to 0");
                        break;
                    default:
                        break;
                }break;
            case "mile":
                textview.setText("The result is infinitely close to infinity.");
                break;
            default:
                break;
        }

        //Toast.makeText(this, "the value is "+ source, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}