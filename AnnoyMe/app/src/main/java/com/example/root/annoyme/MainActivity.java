package com.example.root.annoyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startColetaDemo1(View view) {

        Intent Activity;
        Activity = new Intent(this, ColetaDemo1.class);

        startActivity(Activity);
    }
}
