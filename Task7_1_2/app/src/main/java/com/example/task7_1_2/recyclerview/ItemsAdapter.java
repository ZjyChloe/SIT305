package com.example.task7_1_2.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task7_1_2.R;
import com.example.task7_1_2.data.Item;
import com.example.task7_1_2.deleteItemActivity;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {

    private Context mCtx;
    private List<Item> itemList;

    public ItemsAdapter(Context mCtx, List<Item> itemList){
        this.mCtx = mCtx;
        this.itemList = itemList;
    }

    @Override
    public ItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemsViewHolder holder, int position){
        Item i = itemList.get(position);
        holder.textViewPostType.setText(i.getPost_type());
        holder.textViewName.setText(i.getName());

    }

    @Override
    public int getItemCount(){
        return itemList.size();
    }

    class ItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewPostType, textViewName;

        public ItemsViewHolder(View itemView){
            super(itemView);

            textViewPostType = itemView.findViewById(R.id.itemTypeView);
            textViewName = itemView.findViewById(R.id.itemNameView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            Item item = itemList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, deleteItemActivity.class);
            intent.putExtra("item", item);
            mCtx.startActivity(intent);
        }
    }
}
