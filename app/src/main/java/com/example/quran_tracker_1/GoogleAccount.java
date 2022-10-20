package com.example.quran_tracker_1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.Calendar;

public class GoogleAccount {

    private final GoogleSignInAccount googleSignInAccount;
    private String id;
    private String displayName;
    private String email;


    public GoogleAccount(Context context){
        this.googleSignInAccount = GoogleSignIn.getLastSignedInAccount(context);
        if(googleSignInAccount == null){
            return;
        }
        this.id = googleSignInAccount.getId();
        this.displayName = googleSignInAccount.getDisplayName();
        this.email = googleSignInAccount.getEmail();
    }

    public GoogleSignInAccount getGoogleSignInAccount() {
        return googleSignInAccount;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }
}
