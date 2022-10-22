package com.example.quran_tracker_1.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quran_tracker_1.R;
import com.example.quran_tracker_1.room.AppDatabase;
import com.example.quran_tracker_1.room.Chapter;
import com.example.quran_tracker_1.room.Recitation;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class AddActivity extends AppCompatActivity {

    /* Backend variables */
    Toast toast;

    /* Layout variables */
    MaterialToolbar toolbar;
    TabLayout tabLayout;
    TabItem tab_recitation, tab_chapter;

    EditText name, chapterNo;
    Button add;

    /* Room variables */
//    String stringName, stringChapterName;

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
        onClick_add();

        new Thread(this::requestFocusOnNameThread).start();

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

    // ROOM
    private void onClick_add(){
        add.setOnClickListener(view -> {
            Thread threadAdd;
            if(tabLayout.getSelectedTabPosition()==0)
                threadAdd = new Thread(this::addRecitation);
            else
                threadAdd = new Thread(this::addChapter);
            threadAdd.start();
        });
    }

    private void addRecitation(){

        String stringName = name.getText().toString();
        Recitation recitation = AppDatabase.getInstance(getApplicationContext()).recitationDao().getByName(stringName);

        // If there is duplicate, toast message: "Recitation with same name already exists!"
        if(recitation != null) {
            runOnUiThread(() -> toast(getString(R.string.toast_add_duplicateRecitation)));
            return;
        }

        // Add the recitation
        recitation = new Recitation(stringName, false);
        AppDatabase.getInstance(getApplicationContext()).recitationDao().insert(recitation);

        // Insert chapters for new recitation
        recitation = AppDatabase.getInstance(getApplicationContext()).recitationDao().getByName(stringName);
        AppDatabase.getInstance(getApplicationContext()).recitationDao().
                insertChaptersOfRecitation(recitation.id, false);

        // Insert pages for chapters of new recitation
        List<Chapter> chapters = AppDatabase.getInstance(getApplicationContext()).chapterDao().getByRecitationID(recitation.id);
        for(Chapter c : chapters){
            if(c.number == 30)
                AppDatabase.getInstance(getApplicationContext()).chapterDao().insertPagesOfChapter24(c.id, false);
            else
                AppDatabase.getInstance(getApplicationContext()).chapterDao().insertPagesOfChapter20(c.id, false);
        }

    }

    private void addChapter(){

        String stringName = name.getText().toString();

        // If name is empty or longer than 20 characters, toast message: "Please enter a name." / "Name is too long!"
        if(stringName.length()==0){
            runOnUiThread(() -> toast(getString(R.string.toast_add_emptyName)));
            return;
        }
        else if(stringName.length()>20){
            runOnUiThread(() -> toast(getString(R.string.toast_add_longName)));
            return;
        }

        // If chapterNo is empty, toast message: "Please enter a Chapter No."
        if(chapterNo.getText().toString().length()==0){
            runOnUiThread(() -> toast(getString(R.string.toast_add_emptyChapterNo)));
            return;
        }

        int numChapterNo;

        // Try to parse chapterNo to integer, if can't toast message: "Invalid Chapter No!"
        try {
            numChapterNo = Integer.parseInt(chapterNo.getText().toString());
        } catch (Exception e){
            runOnUiThread(() -> toast(getString(R.string.toast_add_invalidChapterNo)+" ("+e.getMessage()+")"));
            return;
        }


        // If chapterNo is smaller than 1 or bigger than 30, toast message: "Chapter No should be between 1-30!"
        if(numChapterNo<1 || numChapterNo>30){
            runOnUiThread(() -> toast(getString(R.string.toast_add_outOfLimitChapterNo)));
            return;
        }

        Chapter chapter = AppDatabase.getInstance(getApplicationContext()).chapterDao().getByNameAndNumber(stringName, numChapterNo);


        // If there is duplicate, toast message: "Chapter with same name and number already exists!"
        if(chapter != null) {
            runOnUiThread(() -> toast(getString(R.string.toast_add_duplicateChapter)));
            return;
        }

        // Add the chapter
        chapter = new Chapter(stringName, numChapterNo, false);
        AppDatabase.getInstance(getApplicationContext()).chapterDao().insert(chapter);

        // Add pages for new chapter
        chapter = AppDatabase.getInstance(getApplicationContext()).
                chapterDao().getByNameAndNumber(stringName, numChapterNo);
        if(numChapterNo == 30)
            AppDatabase.getInstance(getApplicationContext()).chapterDao().insertPagesOfChapter24(chapter.id, false);
        else
            AppDatabase.getInstance(getApplicationContext()).chapterDao().insertPagesOfChapter20(chapter.id, false);

        // Finish the activity with intent data
        Intent returnIntent = new Intent();
        returnIntent.putExtra("chapter_id", chapter.id);
        returnIntent.putExtra("chapter_name", chapter.name);
        returnIntent.putExtra("chapter_number", chapter.number);
        returnIntent.putExtra("chapter_isCompleted", chapter.isCompleted);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();

    }

    private void toast(String message){
        if(toast != null)
            toast.cancel();
        toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.show();
    }


    // This method requests focus on name edittext and shows keyboard automatically when activity starts
    public void requestFocusOnNameThread(){

        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                name.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT);
            }
        });

    }

}