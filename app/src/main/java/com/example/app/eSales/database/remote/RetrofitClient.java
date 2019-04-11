package com.example.app.eSales.database.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import static com.example.app.eSales.database.remote.ApiUtils.BASE_URL;

public class RetrofitClient {
   /* private static final String BASE_URL = "http://localhost:8080/api/public/api/";
private static RetrofitClient mInstance;
    private  Retrofit retrofit ;

   private RetrofitClient() {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }
    public static synchronized RetrofitClient getInstance()
        {
            if (mInstance == null) {
                mInstance = new RetrofitClient();
            }
            return mInstance;
        }
        public Api getApi()
        {
            return retrofit.create(Api.class);

        }*/


        private static Retrofit retrofit = null;

        public static Retrofit getClient(String baseUrl) {
            if (retrofit==null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)

                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }


