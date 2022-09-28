package com.example.aplikasipbo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aplikasipbo.api.base.BaseApiService;
import com.example.aplikasipbo.api.koneksi.conn;
import com.example.aplikasipbo.api.model.ProductsModel;
import com.example.aplikasipbo.ui.etc.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityUpdate extends AppCompatActivity {

    EditText judul, isi;
    Button save;
    int id;
    ImageButton back;
    private BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        judul = findViewById(R.id.judul1);
        isi = findViewById(R.id.isi1);
        save = findViewById(R.id.save_btn1);
        back = findViewById(R.id.back);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        judul.setText(bundle.getString("judul"));
        isi.setText(bundle.getString("isi"));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                                //KIRIM DATA KE API
                                int idnya = id;
                                String judulnya = judul.getText().toString();
                                String isinya = isi.getText().toString();
                                mApiService = conn.getClient().create(BaseApiService.class);
                                ProductsModel productsModel = new ProductsModel(idnya,judulnya,isinya);
                                Call<ProductsModel> edit = mApiService.editCatatan(id, productsModel );
                                edit.enqueue(new Callback<ProductsModel>() {
                                    @Override
                                    public void onResponse(Call<ProductsModel> call, Response<ProductsModel> response) {
                                        Toast.makeText(ActivityUpdate.this, "Tersimpan", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(ActivityUpdate.this, MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                    @Override
                                    public void onFailure(Call<ProductsModel> call, Throwable t) {
                                        Toast.makeText(ActivityUpdate.this, "Tidak Tersimpan", Toast.LENGTH_SHORT).show();
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