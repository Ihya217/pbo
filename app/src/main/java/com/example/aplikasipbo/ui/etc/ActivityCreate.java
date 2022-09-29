package com.example.aplikasipbo.ui.etc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.aplikasipbo.R;
import com.example.aplikasipbo.api.base.BaseApiService;
import com.example.aplikasipbo.api.koneksi.conn;
import com.example.aplikasipbo.api.model.ProductsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityCreate extends AppCompatActivity {
    EditText judul, isi;
    Button save;
    private BaseApiService mApiService;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        judul = findViewById(R.id.judul);
        isi = findViewById(R.id.isi);
        save = findViewById(R.id.save_btn);
        back = findViewById(R.id.back);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                                //KIRIM DATA KE API
                                String judulnya = judul.getText().toString();
                                String isinya = isi.getText().toString();
                                mApiService = conn.getClient().create(BaseApiService.class);
                                Call<ProductsModel> save = mApiService.postCatatan(judulnya,isinya);
                                save.enqueue(new Callback<ProductsModel>() {
                                    @Override
                                    public void onResponse(Call<ProductsModel> call, Response<ProductsModel> response) {
                                        Toast.makeText(ActivityCreate.this, "Tersimpan", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(ActivityCreate.this, MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                    @Override
                                    public void onFailure(Call<ProductsModel> call, Throwable t) {
                                        Toast.makeText(ActivityCreate.this, "Tidak Tersimpan", Toast.LENGTH_SHORT).show();
                                        Log.d("Error Jaringan", "disini");
                                        t.printStackTrace();
                                        Log.d("here", "here", t);
                                    }
                                });
                            }

        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    }