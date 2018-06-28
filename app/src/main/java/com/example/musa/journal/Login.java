package com.example.musa.journal;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class Login extends AppCompatActivity {
    Button Login;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FloatingActionButton fab;

    FirebaseAuth.AuthStateListener authStateListener;
    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Login = findViewById(R.id.button2);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                assert connectivityManager != null;
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
                if (!isConnected) {
                    Snackbar.make(v, "Please Connect to Internet", Snackbar.LENGTH_LONG).show();
                }
                else {
                         firebaseAuth = FirebaseAuth.getInstance();

                         authStateListener = new FirebaseAuth.AuthStateListener() {
                         @Override
                           public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth1) {
                            user = firebaseAuth1.getCurrentUser();
                            if (user != null) {
                                Toast.makeText(Login.this,"Welcome back "+user.getDisplayName(),Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else {

                                List<AuthUI.IdpConfig> providers = Arrays.asList(
                                        new AuthUI.IdpConfig.EmailBuilder().build(),
                                        new AuthUI.IdpConfig.GoogleBuilder().build());

// Create and launch sign-in intent
                                startActivityForResult(
                                        AuthUI.getInstance()
                                                .createSignInIntentBuilder()
                                                .setAvailableProviders(providers)
                                                .setTheme(R.style.AppTheme)
                                                .build(),
                                        RC_SIGN_IN);
                            }
                        }
                    };
                    firebaseAuth.addAuthStateListener(authStateListener);

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Toast.makeText(this,"Signed in Sucessfully",Toast.LENGTH_SHORT).show();
                // ...
            } else if (resultCode == RESULT_CANCELED) {


            }
        }
    }
    @Override
    protected void onPause() {
        if (firebaseAuth!=null)
            firebaseAuth.removeAuthStateListener(authStateListener);
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (firebaseAuth!=null)
            firebaseAuth.addAuthStateListener(authStateListener);
        super.onResume();
    }

}

