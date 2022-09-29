package com.example.aplikasipbo.api.base;

import com.example.aplikasipbo.api.model.ProductsModel;
import com.example.aplikasipbo.api.response.ProductsResponse;

import java.math.BigInteger;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BaseApiService {
    @GET("catatans")
    Call<ProductsResponse> getProducts();

    @FormUrlEncoded
    @POST("catatans")
    Call<ProductsModel> postCatatan(
          @Field("judul") String judul,
          @Field("isi") String isi
    );
    @PUT("catatans/{id}")
    Call<ProductsModel> editCatatan(
            @Path("id") int id, @Body ProductsModel productsModel);

    @DELETE("catatans/{id}")
    Call<Void> deleteCatatan(@Path("id") int id);
}
