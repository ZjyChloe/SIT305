package com.example.task9_1;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.task9_1.data.DatabaseClient;
import com.example.task9_1.data.Item;
import com.example.task9_1.recyclerview.ItemsAdapter;

import java.util.List;

public class showItemActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item);

        recyclerView = findViewById(R.id.itemsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getItems();
    }

    private void getItems() {

        class GetItems extends  AsyncTask<Void, Void, List<Item>> {

            @Override
            protected List<Item> doInBackground(Void... voids) {
                List<Item> itemList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getItemDatabase()
                        .itemDAO()
                        .getAll();
                return itemList;
            }

            @Override
            protected void onPostExecute(List<Item> items){
                super.onPostExecute(items);
                ItemsAdapter adapter = new ItemsAdapter(showItemActivity.this, items);
                recyclerView.setAdapter(adapter);
            }

        }

        GetItems gt = new GetItems();
        gt.execute();

    }


}