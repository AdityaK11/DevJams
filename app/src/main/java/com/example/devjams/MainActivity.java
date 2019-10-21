package com.example.devjams;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.devjams.url.UrlActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        CardView textButton = (CardView)findViewById(R.id.textButton);
        CardView linkButton = (CardView)findViewById(R.id.linkButton);
        CardView imageButton = (CardView)findViewById(R.id.imageButton);

        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,TextActivity.class);
                startActivity(i);
            }
        });

        linkButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, UrlActivity.class);
                startActivity(i);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ImageActivity.class);
                startActivity(i);
            }
        });

    }
}
