package com.example.aplikasipbo.api.response;

import com.example.aplikasipbo.api.model.ProductsModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsResponse {
    private String hasil;
    @SerializedName("data")
    List<ProductsModel> semua_data;

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }

    public List<ProductsModel> getSemua_data() {
        return semua_data;
    }

    public void setSemua_data(List<ProductsModel> semua_data) {
        this.semua_data = semua_data;
    }
}
