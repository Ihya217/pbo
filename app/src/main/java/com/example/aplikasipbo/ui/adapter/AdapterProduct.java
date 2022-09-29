package com.example.aplikasipbo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasipbo.ui.etc.ActivityUpdate;
import com.example.aplikasipbo.R;
import com.example.aplikasipbo.api.base.BaseApiService;
import com.example.aplikasipbo.api.model.ProductsModel;

import java.util.List;


public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {
    private List<ProductsModel> productsList;
    Context mcontext;
    BaseApiService mApiservice;


   public AdapterProduct(Context mcontext, List<ProductsModel> productsList) {
       this.mcontext = mcontext;
       this.productsList = productsList;
   }

//view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        LinearLayout parent_layout;
        ImageButton delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleText);
            desc = itemView.findViewById(R.id.descText);
            parent_layout = itemView.findViewById(R.id.parentLayout1);
            delete = itemView.findViewById(R.id.menu);

        }
    }


    @NonNull
    @Override
    public AdapterProduct.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        int id;
        LinearLayout parent_layout = holder.parent_layout;
        holder.title.setText(productsList.get(position).getJudul());
        holder.desc.setText(productsList.get(position).getIsi());


        parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ActivityUpdate.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", productsList.get(holder.getAdapterPosition()).getId());
                bundle.putString("judul", productsList.get(holder.getAdapterPosition()).getJudul());
                bundle.putString("isi", productsList.get(holder.getAdapterPosition()).getIsi());
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
            return productsList.size();
    }
}