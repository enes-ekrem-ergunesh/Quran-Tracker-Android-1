package com.example.quran_tracker_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /* Layout variables */
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Layout variables */
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);

        toolbar.getMenu().getItem(1).setOnMenuItemClickListener(menuItem -> {
            Toast.makeText(getApplicationContext(), "Added :)", Toast.LENGTH_LONG).show();
            return false;
        });

    }
}