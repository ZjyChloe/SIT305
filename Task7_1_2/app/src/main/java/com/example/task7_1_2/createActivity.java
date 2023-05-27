package com.example.task7_1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.task7_1_2.data.DatabaseClient;
import com.example.task7_1_2.data.Item;

public class createActivity extends AppCompatActivity {
    private EditText nameEditText,phoneEditText,descriptionEditText,dateEditText,locationEditText;
    String postType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        nameEditText = findViewById(R.id.nameEdit);
        phoneEditText = findViewById(R.id.phoneEdit);
        descriptionEditText = findViewById(R.id.descriptionEdit);
        dateEditText = findViewById(R.id.dateEdit);
        locationEditText = findViewById(R.id.locationEdit);
        Button saveButton = findViewById(R.id.saveBtn);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });
    }

    public void saveTask(){
        final String sName = nameEditText.getText().toString().trim();
        final String sPhone = phoneEditText.getText().toString().trim();
        final String sDescription = descriptionEditText.getText().toString().trim();
        final String sDate = dateEditText.getText().toString().trim();
        final String sLocation = locationEditText.getText().toString().trim();

        if(postType.isEmpty()){
            Toast.makeText(this, "post type required", Toast.LENGTH_SHORT).show();
            return;
        }
        if(sName.isEmpty()){
            nameEditText.setError("Name required");
            nameEditText.requestFocus();
            return;
        }
        if(sPhone.isEmpty()){
            phoneEditText.setError("Name required");
            phoneEditText.requestFocus();
            return;
        }
        if(sDescription.isEmpty()){
            descriptionEditText.setError("Name required");
            descriptionEditText.requestFocus();
            return;
        }
        if(sDate.isEmpty()){
            dateEditText.setError("Name required");
            dateEditText.requestFocus();
            return;
        }
        if(sLocation.isEmpty()){
            locationEditText.setError("Name required");
            locationEditText.requestFocus();
            return;
        }

        class SaveTask extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids){

                //creating a task
                Item item = new Item();
                item.setPost_type(postType);
                item.setName(sName);
                item.setPhone(sPhone);
                item.setDescription(sDescription);
                item.setDate(sDate);
                item.setLocation(sLocation);

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getItemDatabase()
                        .itemDAO()
                        .insert(item);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                finish();
                Toast.makeText(createActivity.this, "Saved", Toast.LENGTH_SHORT).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.radio_Lost:
                if(checked)
                    postType = "Lost";
                break;
            case R.id.radio_Found:
                if(checked)
                    postType = "Found";
                break;
        }
    }
}