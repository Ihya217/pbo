package com.example.aplikasipbo.ui.etc;

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

import com.example.aplikasipbo.R;
import com.example.aplikasipbo.api.base.BaseApiService;
import com.example.aplikasipbo.api.koneksi.conn;
import com.example.aplikasipbo.api.model.ProductsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityUpdate extends AppCompatActivity {

    EditText judul, isi;
    Button save;
    int id;
    ImageButton back;
    ImageView delete;
    private BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        judul = findViewById(R.id.judul1);
        isi = findViewById(R.id.isi1);
        save = findViewById(R.id.save_btn1);
        back = findViewById(R.id.back);
        delete = findViewById(R.id.delete);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        judul.setText(bundle.getString("judul"));
        isi.setText(bundle.getString("isi"));

        mApiService = conn.getClient().create(BaseApiService.class);

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

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertbox = new AlertDialog.Builder(ActivityUpdate.this)
                        .setMessage("Yakin Puh ??")
                        .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                            // do something when the button is clicked
                            public void onClick(DialogInterface arg0, int arg1) {
                                int idnya = id;
                                Call<Void> delete = mApiService.deleteCatatan(idnya);
                                delete.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        Toast.makeText(ActivityUpdate.this, "Catatan Dihapus", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {

                                    }
                                });

                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {

                            // do something when the button is clicked
                            public void onClick(DialogInterface arg0, int arg1) {
                            }
                        })
                        .show();
            }
        });

    }

    private void deleteCatatan(){

    }

}