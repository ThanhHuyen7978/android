package com.example.app.eSales.database.remote;

public class ApiUtils {

    public static final String BASE_URL = "http://localhost:8080/api/public/api/";

   public static Api getData() {
       return RetrofitClient.getClient(BASE_URL).create(Api.class);
    }
}
