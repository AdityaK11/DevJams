package com.example.devjams;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.devjams.classes.model;
import com.example.devjams.url.ArticleActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class OutputActivity extends AppCompatActivity {

    ListView listNews;
    ProgressBar loader;

    ArrayList<HashMap<String, String>> dataList = new ArrayList<>();
    static final String KEY_AUTHOR = "author";
    static final String KEY_TITLE = "title";
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_URL = "url";
    static final String KEY_URLTOIMAGE = "urlToImage";
    static final String KEY_PUBLISHEDAT = "publishedAt";

    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        getSupportActionBar().hide();

        if (getIntent() != null){
            value=getIntent().getStringExtra("value");
        }
        Log.d("Value",value);

        listNews = findViewById(R.id.listNews);
        loader = findViewById(R.id.loader);
        listNews.setEmptyView(loader);


        if (Function.isNetworkAvailable(getApplicationContext())) {
            DownloadNews newsTask = new DownloadNews();
            newsTask.execute();
        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }

    }

    class DownloadNews extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() { super.onPreExecute(); }

        protected String doInBackground(String... args) {
            String xml = Function.excuteGet("https://newsapi.org/v2/everything?q="+value+"&language=en&apiKey=b14eab59f5d54bc7a9ee19ad1aaa8eca");
            Log.d("XML",xml);


            return xml;
        }

        @Override
        protected void onPostExecute(String xml) {



            if (xml.length() > 10) { // Just checking if not empty

                try {
                    JSONObject jsonResponse = new JSONObject(xml);
                    JSONArray jsonArray = jsonResponse.optJSONArray("articles");

                    for (int i = 0; i < 5; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        HashMap<String, String> map = new HashMap<>();
                        map.put(KEY_AUTHOR, jsonObject.optString(KEY_AUTHOR));
                        map.put(KEY_TITLE, jsonObject.optString(KEY_TITLE));
                        map.put(KEY_DESCRIPTION, jsonObject.optString(KEY_DESCRIPTION));
                        map.put(KEY_URL, jsonObject.optString(KEY_URL));
                        map.put(KEY_URLTOIMAGE, jsonObject.optString(KEY_URLTOIMAGE));
                        map.put(KEY_PUBLISHEDAT, jsonObject.optString(KEY_PUBLISHEDAT));
                        model.setUrl(jsonObject.optString(KEY_URL),i);
                        Intent in = new Intent(OutputActivity.this, ArticleActivity.class);
                        in.putExtra("url",jsonObject.optString(KEY_URL) );
                        in.putExtra("index",i);
                        //startActivity(in);
                        Log.d("URL",jsonObject.optString(KEY_URL));
                        dataList.add(map);
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Unexpected error", Toast.LENGTH_SHORT).show();
                }



            } else {
                Toast.makeText(getApplicationContext(), "No news found", Toast.LENGTH_SHORT).show();
            }

            /*for(int i=0;i<5;i++){
                Log.d("Summary",model.getArr(i));
            }*/

            ListNewsAdapter adapter = new ListNewsAdapter(OutputActivity.this, dataList);
            listNews.setAdapter(adapter);


        }
    }

}
