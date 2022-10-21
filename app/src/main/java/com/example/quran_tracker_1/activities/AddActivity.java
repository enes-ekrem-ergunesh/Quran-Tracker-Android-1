package com.example.quran_tracker_1.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quran_tracker_1.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class AddActivity extends AppCompatActivity {

    /* Layout variables */
    MaterialToolbar toolbar;
    TabLayout tabLayout;
    TabItem tab_recitation, tab_chapter;

    EditText name, chapterNo;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        /* Layout variables */
        name = findViewById(R.id.add_editText_name);
        chapterNo = findViewById(R.id.add_editText_chapterNo);
        add = findViewById(R.id.add_button_add);
        onCreate_toolbar();
        onCreate_tab();


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
        toolbar = findViewById(R.id.add_toolbar);
        toolbar.setTitle(R.string.title_activity_add);
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        if (ab != null)
            ab.setDisplayHomeAsUpEnabled(true);

    }

    // TAB
    private void onCreate_tab(){
        tabLayout = findViewById(R.id.add_tabLayout);
        tab_recitation = findViewById(R.id.add_tabItem_recitation);
        tab_chapter = findViewById(R.id.add_tabItem_chapter);

        // on create, select tab with selected tab position on parent activity
        Bundle extras = getIntent().getExtras();
        int tab_position = extras.getInt("main_tab_position");
        TabLayout.Tab tab = tabLayout.getTabAt(tab_position);
        assert tab != null;
        tab.select();

        // If tab is recitation, disable chapterNo
        if(tabLayout.getSelectedTabPosition() == 0){
            chapterNo.setVisibility(View.GONE);
            chapterNo.setEnabled(false);
        }
        else{
            chapterNo.setVisibility(View.VISIBLE);
            chapterNo.setEnabled(true);
        }

        // Enable/Disable chapterNo when tab changes
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                    chapterNo.setVisibility(View.GONE);
                    chapterNo.setEnabled(false);
                }
                else{
                    chapterNo.setVisibility(View.VISIBLE);
                    chapterNo.setEnabled(true);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}