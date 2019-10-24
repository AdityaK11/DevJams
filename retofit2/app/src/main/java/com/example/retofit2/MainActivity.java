package com.example.retofit2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiInterface.Base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface api = retrofit.create(apiInterface.class);

        Call<ArrayList<Employee>> call = api.getEmployees("10237","GUJ hjxbfhusy");

        call.enqueue(new Callback<ArrayList<Employee>>() {
            @Override
            public void onResponse(Call<ArrayList<Employee>> call, Response<ArrayList<Employee>> response) {
                ArrayList<Employee> Employees = response.body();

                RecyclerAdapter adapter = new RecyclerAdapter(MainActivity.this,Employees);
                Log.d("URL", String.valueOf(response));
                rv.setAdapter(adapter);
                for(Employee e: Employees){
                    Log.i("id",e.getId());
                    Log.i("name",e.getName());
                    Log.i("age",e.getAge());
                    Log.i("salary",e.getSalary());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Employee>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}

