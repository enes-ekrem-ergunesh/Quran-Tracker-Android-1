package com.example.quran_tracker_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    /* Layout variables */
    MaterialToolbar toolbar;

    TextView textView;

    /* Firebase variables */
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Layout variables */
        onCreate_toolbar();
        textView = findViewById(R.id.main_textview);

        /* Firebase variables */
        onCreate_firebase();

    }

    // TOOLBAR
    private void onCreate_toolbar(){
        toolbar = (MaterialToolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.title_activity_main);

        // Toolbar add button click listener
        toolbar.getMenu().getItem(0).setOnMenuItemClickListener(menuItem -> {
            Toast.makeText(getApplicationContext(), "Added :)", Toast.LENGTH_SHORT).show();
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
                textView.setText(response);
            }
        });

    }

    public void firebase_write_user(String userId, String email, String password) {
//        User user = new User(email, password);

//        mDatabase.child("users").child(userId).setValue(user);
    }





}