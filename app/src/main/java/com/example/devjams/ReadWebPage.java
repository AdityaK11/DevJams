package com.example.devjams;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadWebPage extends Activity {
    private TextView textView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_web_page);
        textView = (TextView) findViewById(R.id.TextView01);
        new RequestTask().execute("http://www.google.com/");
    }

    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            for (String url : urls) {

                DefaultHttpClient client = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
                try {
                    HttpResponse execute = client.execute(httpGet);
                    InputStream content = execute.getEntity().getContent();

                    BufferedReader buffer = new BufferedReader(
                            new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            textView.setText(Html.fromHtml(result));
        }
    }

    public void readWebpage(View view) {
        /*DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute(new String[] { "http://www.google.com" });*/

        new RequestTask().execute("http://www.google.com/");

    }
    class RequestTask extends AsyncTask<String, String, String>{

        @Override
// username, password, message, mobile
        protected String doInBackground(String... url) {
            // constants
            int timeoutSocket = 5000;
            int timeoutConnection = 5000;

            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
            HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
            HttpClient client = new DefaultHttpClient(httpParameters);

            HttpGet httpget = new HttpGet(url[0]);

            try {
                HttpResponse getResponse = client.execute(httpget);
                final int statusCode = getResponse.getStatusLine().getStatusCode();

                if(statusCode != HttpStatus.SC_OK) {
                    Log.w("MyApp", "Download Error: " + statusCode + "| for URL: " + url);
                    return null;
                }

                String line = "";
                StringBuilder total = new StringBuilder();

                HttpEntity getResponseEntity = getResponse.getEntity();

                BufferedReader reader = new BufferedReader(new InputStreamReader(getResponseEntity.getContent()));

                while((line = reader.readLine()) != null) {
                    total.append(line);
                }

                line = total.toString();
                return line;
            } catch (Exception e) {
                Log.w("MyApp", "Download Exception : " + e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // do something with result
            textView.setText(Html.fromHtml(result));
        }
    }
}
