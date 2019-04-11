package com.example.app.eSales.database.remote;

import com.example.app.eSales.database.model.ARSALESPERSON;
import com.example.app.eSales.database.model.Customer;
//import com.example.app.eSales.database.model.LoginResponse;

import java.util.List;
import java.util.StringTokenizer;

//import okhttp3.Call;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("login")
    //Call<List<ARSALESPERSON>> Logindata
    Call<List<ARSALESPERSON>> Logindata(
           @Field("full_name") String full_name, @Field("password") String password
            );
    @FormUrlEncoded
    @GET("customer")
    Call<List<Customer>> loadKH();
}

