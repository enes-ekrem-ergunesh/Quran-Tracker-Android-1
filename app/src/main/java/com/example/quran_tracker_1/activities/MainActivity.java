package com.example.quran_tracker_1.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.quran_tracker_1.ItemViewModel;
import com.example.quran_tracker_1.R;
import com.example.quran_tracker_1.adapters.MainViewPagerAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    /* Layout variables */
    MaterialToolbar toolbar;
    TabLayout tabLayout;
    TabItem tab_recitation, tab_chapter;

    /* ViewPager variables */
    ViewPager2 viewPager;
    FragmentStateAdapter pagerAdapter;
    private ItemViewModel viewModel;



    /* Firebase variables */
    private DatabaseReference mDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Layout variables */
        onCreate_tab();
        onCreate_toolBar();
        onCreate_viewPager();


        /* Firebase variables */
        onCreate_firebase();

    }

    // TOOLBAR
    private void onCreate_toolBar(){
        toolbar = findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.title_activity_main);

        // Toolbar add button click listener
        toolbar.getMenu().getItem(0).setOnMenuItemClickListener(menuItem -> {
//            Toast.makeText(getApplicationContext(), "Added :)", Toast.LENGTH_SHORT).show();

            // start add activity with selected tab position
            Intent intentAdd = new Intent(getApplicationContext(), AddActivity.class);
            intentAdd.putExtra("main_tab_position", tabLayout.getSelectedTabPosition());
            addActivityResultLauncher.launch(intentAdd);

            return false;
        });

        // Toolbar settings button click listener
        toolbar.getMenu().getItem(1).setOnMenuItemClickListener(menuItem -> {
//            Toast.makeText(getApplicationContext(), "settings :)", Toast.LENGTH_SHORT).show();

            Intent intentSettings = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intentSettings);

            return false;
        });
    }

    // TAB
    private void onCreate_tab(){
        tabLayout = findViewById(R.id.main_tabLayout);
        tab_recitation = findViewById(R.id.main_tabItem_recitation);
        tab_chapter = findViewById(R.id.main_tabItem_chapter);

        // On tab change, change the current page on viewPager
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    // VIEWPAGER
    private void onCreate_viewPager(){
        viewPager = findViewById(R.id.main_viewPager);
        pagerAdapter = new MainViewPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        viewModel = new ViewModelProvider(this).get(ItemViewModel.class);


        // On page change, change the selected tab on tabLayout
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Objects.requireNonNull(tabLayout.getTabAt(position)).select();
            }
        });


    }


    ActivityResultLauncher<Intent> addActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // There are no request codes
                    Intent data = result.getData();
                    assert data != null;
                    Bundle bundle = data.getExtras();
                    viewModel.selectItem(bundle);
                    Log.d("onActivityResult", bundle.getString("chapter_name"));
                }
            }
    );



















    // FIREBASE
    private void onCreate_firebase(){
        mDatabase = FirebaseDatabase.getInstance().getReference();

        firebase_read_once("users", "1");

    }

    private void firebase_read_once(String table, String id){
        mDatabase.child(table).child(id).get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebaseMe", "Error getting data", task.getException());
            }
            else {
                String response = String.valueOf(task.getResult().getValue());
                Log.d("firebaseMe", response);

                /* Update the Element (TextView) that is going to hold the data */
            }
        });

    }

    public void firebase_write_user(String userId, String email, String password) {
//        User user = new User(email, password);

//        mDatabase.child("users").child(userId).setValue(user);
    }





}