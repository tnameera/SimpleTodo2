package com.example.simpletodo;

import static android.R.layout.simple_list_item_1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


// adapter is Responsible for displaying data from the model into a row  in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnClickListener{
        void onItemClicked(int i );
    }

    public interface OnLongClickListener{
        void onItemLongClicked(int i);
    }

    List<String> items;// member variable
    OnLongClickListener longClickListener;
    OnClickListener clickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener clickListener) {
        this.items = items; // defining member variable  =  the variable passed in the construtor
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //use layout inflator to inflate a view
       View todoView = LayoutInflater.from(viewGroup.getContext()).inflate(simple_list_item_1,viewGroup,false);
        //wrap it inside a view holder and return it
        return new ViewHolder(todoView);
    }

    @Override
    //BindViewHolder taking data to a particular viewHolder
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //grab the item in the position
        String item = items.get(i);

        //bind the item in the specified view holder
        viewHolder.bind(item);

    }

    //Tells the rv how many items in the list
    @Override
    //ItemCount simply the number of items available in the data
    public int getItemCount() {
        return items.size();
    }

    // container to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvitem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvitem = itemView.findViewById(android.R.id.text1);
        }

        //update the view inside of the view holder with this data
        public void bind(String item) {
            tvitem.setText(item);
            tvitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClicked(getAdapterPosition());

                }
            });
            tvitem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    //Noitfy the listener which position(i) was long passed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    // updated from false to true
                    return true;
                }
            });
        }
    }
}
