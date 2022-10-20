package com.example.quran_tracker_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class AddActivity extends AppCompatActivity {

    /* Layout variables */
    TabLayout tabLayout;
    TabItem tab_recitation, tab_chapter;

    EditText name, chapterNo;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        /* Layout variables */
        onCreate_tab();
        name = findViewById(R.id.add_editText_name);
        chapterNo = findViewById(R.id.add_editText_chapterNo);
        add = findViewById(R.id.add_button_add);


    }

    // TAB
    private void onCreate_tab(){
        tabLayout = findViewById(R.id.add_tabLayout);
        tab_recitation = findViewById(R.id.add_tabItem_recitation);
        tab_chapter = findViewById(R.id.add_tabItem_chapter);


        Log.d("add_tab", "recitation id: " + R.id.add_tabItem_recitation);

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