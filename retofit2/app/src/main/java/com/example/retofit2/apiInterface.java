package com.example.retofit2;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface apiInterface {

    String Base_url = "http://dummy.restapiexample.com/api/v1/";

    @GET("employees")
    Call<ArrayList<Employee>> getEmployees(@Query("id") String param1,
                                           @Query("employee_name") String param2);
}
