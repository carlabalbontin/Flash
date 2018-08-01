package com.cbalt.flash;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;


public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        if (new CurrentUser().getCurrentUser() != null){
            // already signed in
            logged();
            Toast.makeText(this, "User is logged in", Toast.LENGTH_SHORT).show();
        } else {
            // not signed in
            Toast.makeText(this, "User is not logged in", Toast.LENGTH_SHORT).show();
            signUp();
        }

    }

    private void signUp(){
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.EmailBuilder().build()
                                )
                        )
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (RC_SIGN_IN == requestCode){

            if (RESULT_OK == resultCode){
                logged();
            }
        }
    }

    private void logged(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
