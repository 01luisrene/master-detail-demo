package com.codepath.example.masterdetailmanual.adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codepath.example.masterdetailmanual.ItemsListFragment;
import com.codepath.example.masterdetailmanual.modelos.Item;
import com.codepath.example.masterdetailmanual.R;

import java.util.List;


public class Lista extends RecyclerView.Adapter<Lista.MyViewHolder>{

    private List<Item> mItems;
    private Context mContext;

    //Variable para la comunicación al fragment que contiene la lista
    ItemsListFragment.OnItemSelectedListener itemClickListener;

    public Lista(List<Item> items, Context context, ItemsListFragment.OnItemSelectedListener itemClickListener){

        this.mItems = items;
        this.mContext  = context;
        this.itemClickListener = itemClickListener;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View  v = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Item item = mItems.get(position);

        holder.bind(mContext, item, itemClickListener);


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView id, titulo;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemView.setClickable(true);

            id = (TextView) itemView.findViewById(R.id.tv_id);
            titulo = (TextView) itemView.findViewById(R.id.tv_titulo);
        }

        public void bind(final Context context, final Item item, final ItemsListFragment.OnItemSelectedListener itemClickListener){

            id.setText(item.getId());
            titulo.setText(item.getTitle());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    itemClickListener.onItemSelected(item);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
