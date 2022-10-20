package com.example.quran_tracker_1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.chip.Chip;

public class SettingsActivity extends AppCompatActivity {

    /* Backend variables */
    private Toast mToast = null;

    /* Layout variables */
    MaterialToolbar toolbar;
    Chip googleSignIn, sync;
    TextView userDisplayName;

    /* Google */
    GoogleAccount googleAccount;
    GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 12500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        /* Google */
        googleAccount = new GoogleAccount(getApplicationContext());

        /* Layout variables */
        userDisplayName = findViewById(R.id.settings_TW_userDisplayName);
        onCreate_toolbar();
        onCreate_googleSignIn();
        onCreate_sync();
    }

    @Override
    public boolean onSupportNavigateUp() {
        /* When user clicks on action bar's back button, it will finish the activity
         * so the animation will look same as system back button animation*/
        finish();
        return true;
    }

    // TOOLBAR
    private void onCreate_toolbar() {
        toolbar = findViewById(R.id.settings_toolbar);
        toolbar.setTitle(R.string.title_activity_settings);
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        if (ab != null)
            ab.setDisplayHomeAsUpEnabled(true);

    }

    // GOOGLE SIGN-IN
    private void onCreate_googleSignIn() {
        googleSignIn = findViewById(R.id.settings_chip_googleSignIn);
        if(googleAccount.getGoogleSignInAccount() != null)
            updateUI();

        googleSignIn.setOnClickListener(view -> {
            log_toast("googleSignIn", "clicked!");

            /* Check for existing Google Sign In account, if the user is already signed in
            the GoogleSignInAccount will be non-null. */
            if (googleAccount.getGoogleSignInAccount() != null) {
                log_toast("googleSignIn", "Already logged in!");
            }
            else {
                log_toast("googleSignIn", "Signing in!");
                signIn();
            }
        });
    }

    private void signIn() {
        Log.d("Google Account", "signIn!");

        /* Configure Google sign in */
        /* Configure sign-in to request the user's ID, email address, and basic
        profile. ID and basic profile are included in DEFAULT_SIGN_IN. */
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getApplicationContext(), gso);


        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        someActivityResultLauncher.launch(signInIntent);
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
                if (result.getResultCode() == RC_SIGN_IN) {
                    // The Task returned from this call is always completed, no need to attach
                    // a listener.
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                    handleSignInResult(task);
                } else Log.d("Google Account", "onActivityResult else!");
            });

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            String string_account_data = account.getEmail();
            log_toast("Signed in with: ", string_account_data);
            googleAccount = new GoogleAccount(getApplicationContext());
            updateUI();
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google account", "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
        }
    }

    private void updateUI() {
        googleSignIn.setText(getText(R.string.chipText_GoogleSignOut));
        googleSignIn.setContentDescription(getString(R.string.contentDescription_GoogleSignOut));
        userDisplayName.setText(googleAccount.getDisplayName());
        userDisplayName.setVisibility(View.VISIBLE);
    }

    // SYNC
    private void onCreate_sync() {
        sync = findViewById(R.id.settings_chip_sync);

        sync.setOnClickListener(view -> {
            log_toast("sync", "clicked!!");

            return;
        });
    }

    private void log_toast(String method, String message) {
        Log.d("Settings->" + method, message);
        if (mToast != null) mToast.cancel();
        mToast = Toast.makeText(getApplicationContext(), method + ": " + message, Toast.LENGTH_SHORT);
        mToast.show();
    }
}