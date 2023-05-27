package com.example.task7_1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task7_1_2.data.DatabaseClient;
import com.example.task7_1_2.data.Item;

public class deleteItemActivity extends AppCompatActivity {

    private Button removeButton;
    private TextView reTextViewPostType, reTextViewName, reTextViewDate, reTextViewLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);

        removeButton = findViewById(R.id.RemoveBtn);
        reTextViewPostType = findViewById(R.id.textViewPostType);
        reTextViewName = findViewById(R.id.textViewName);
        reTextViewDate = findViewById(R.id.textViewDate);
        reTextViewLocation = findViewById(R.id.textViewLocation);

        final Item item = (Item) getIntent().getSerializableExtra("item");

        loadItem(item);

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem(item);
            }
        });
    }

    private void loadItem(Item item) {
        reTextViewPostType.setText(item.getPost_type());
        reTextViewName.setText(item.getName());
        reTextViewDate.setText(item.getDate());
        reTextViewLocation.setText(item.getLocation());
    }

    private void deleteItem(final Item item){
        class DeleteItem extends AsyncTask <Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getItemDatabase()
                        .itemDAO()
                        .delete(item);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(deleteItemActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(deleteItemActivity.this, showItemActivity.class));
            }

        }

        DeleteItem dt = new DeleteItem();
        dt.execute();
    }
}