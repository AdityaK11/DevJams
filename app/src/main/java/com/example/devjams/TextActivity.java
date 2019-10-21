package com.example.devjams;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;

public class TextActivity extends AppCompatActivity {

    EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_text);

         ed = (EditText)findViewById(R.id.value_text);
        CardView button = (CardView)findViewById(R.id.text_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TextActivity.this,OutputActivity.class);
                i.putExtra("value",ed.getText().toString());
                startActivity(i);
            }
        });


    }
}
