package com.example.task9_1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.task9_1.data.DatabaseClient;
import com.example.task9_1.data.Item;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;

public class createActivity extends AppCompatActivity {
    private EditText nameEditText,phoneEditText,descriptionEditText,dateEditText,locationEditText;
    String postType;
    LatLng latLng;
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
        Button getLocationBtn = findViewById(R.id.GetLocationBtn);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });

        if(!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), BuildConfig.API_KEY);
        }

        getLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);

                // Start the autocomplete intent.
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
                        .build(getApplicationContext());
                startAutocomplete.launch(intent);
            }
        });

    }

    private final ActivityResultLauncher<Intent> startAutocomplete = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    if (intent != null) {
                        Place place = Autocomplete.getPlaceFromIntent(intent);
                        Log.i("TAG", "Place: ${place.getName()}, ${place.getId()}");

                        locationEditText.setText(place.getName());
                        latLng = place.getLatLng();

                    }
                } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                    // The user canceled the operation.
                    Log.i("TAG", "User canceled autocomplete");
                }
            });

    public void saveTask(){
        final String sName = nameEditText.getText().toString().trim();
        final String sPhone = phoneEditText.getText().toString().trim();
        final String sDescription = descriptionEditText.getText().toString().trim();
        final String sDate = dateEditText.getText().toString().trim();
        final String sLocation = locationEditText.getText().toString().trim();
        final double sLatitude = latLng.latitude;
        final double sLongitude = latLng.longitude;

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
                item.setLatitude(sLatitude);
                item.setLongitude(sLongitude);

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