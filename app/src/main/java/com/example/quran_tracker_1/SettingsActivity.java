package com.example.quran_tracker_1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;

public class SettingsActivity extends AppCompatActivity {

    /* Layout variables */
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        /* Layout variables */
        onCreate_toolbar();


    }

    @Override
    public boolean onSupportNavigateUp() {
        /* When user clicks on action bar's back button, it will finish the activity
        * so the animation will look same as system back button animation*/
        finish();
        return true;
    }

    // TOOLBAR
    private void onCreate_toolbar(){
        toolbar = (MaterialToolbar) findViewById(R.id.settings_toolbar);
        toolbar.setTitle(R.string.title_activity_settings);
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        if(ab!=null)
            ab.setDisplayHomeAsUpEnabled(true);

    }

}