package com.example.aplikasipbo.ui.etc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.aplikasipbo.R;
import com.example.aplikasipbo.api.base.BaseApiService;
import com.example.aplikasipbo.api.koneksi.conn;
import com.example.aplikasipbo.api.model.ProductsModel;
import com.example.aplikasipbo.api.response.ProductsResponse;
import com.example.aplikasipbo.ui.adapter.AdapterProduct;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    List<ProductsModel> productsList;
    BaseApiService mApiService1;
    private RecyclerView recyclerView;
    AdapterProduct mAdapter;
    ImageView wallpaper;
    LinearLayout bg;
    com.google.android.material.floatingactionbutton.FloatingActionButton add;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerViewLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        getData();

        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityCreate.class);
                startActivity(intent);
            }
        });

        recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(MainActivity.this, "Ikeehhh", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void getData() {
        mApiService1 = conn.getClient().create(BaseApiService.class);
        Call<ProductsResponse> dataCall = mApiService1.getProducts();
        dataCall.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productsList = response.body().getSemua_data();
                    mAdapter = new AdapterProduct(getApplicationContext(),productsList);
                    recyclerView.setAdapter(mAdapter);



//                    mAdapter.setOnItemClickListener(new AdapterProduct.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(int position, int id) {
//                            Call<Void> call = mApiService1.deleteCatatan(id);
//                            mAdapter.notifyItemRemoved(position);
//                        }
//                    });
            }
                else {
                    Toast.makeText(getApplicationContext(), "Responsenya Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                Log.d("Error Jaringan", "disini");
                t.printStackTrace();
                Log.d("here", "here", t);
                Toast.makeText(MainActivity.this, "Silahkan Refresh", Toast.LENGTH_SHORT).show();
            }
        });

        wallpaper = findViewById(R.id.wallpaper);
        bg = findViewById(R.id.bg);
        wallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(MainActivity
                        .this, view);
                popupMenu.setOnMenuItemClickListener(MainActivity.this);
                popupMenu.inflate(R.menu.wallpaper_menu);
                popupMenu.show();


            }
        });



    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        bg = findViewById(R.id.bg);
        switch (menuItem.getItemId()){
            case R.id.menu1:
                bg.setBackgroundResource(R.drawable.bg1);
                break;
            case R.id.menu2:
                bg.setBackgroundResource(R.drawable.bg2);
                break;
            case R.id.menu3:
                bg.setBackgroundResource(R.drawable.bg3);
                break;
            case R.id.menu4:
                bg.setBackgroundResource(R.drawable.bg4);
                break;
            case R.id.menu5:
                bg.setBackgroundResource(R.drawable.ihya);
                break;
        }
        return false;
    }
}