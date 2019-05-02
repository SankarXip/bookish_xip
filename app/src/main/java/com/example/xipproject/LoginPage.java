package com.example.xipproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class  LoginPage extends AppCompatActivity {


    private FirebaseAuth mAuth;
    EditText editTextEmail, editTextPassword;
    ProgressBar progressBar;
    GoogleSignInClient mGoogleSignInClient;
    SignInButton signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        signInButton=(SignInButton)findViewById(R.id.myGoogleButton);
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("My Client Id")
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 101);

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 101) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
               // Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }






    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           // Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext() , "LogIn Successful" , Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginPage.this , empDashBoard.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext() , "LogIn UnSuccessful" , Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }





    public void log_in(View view) {

        if (editTextEmail.getText().toString().length() == 0) {
            editTextEmail.setError("Enter your valid EmailId");
        } else if (editTextPassword.getText().toString().length() < 6) {
            editTextPassword.setError("Enter a min.6 digit password");
        } else {
            progressBar.setVisibility(View.VISIBLE);

            (mAuth.signInWithEmailAndPassword(editTextEmail.getText().toString() , editTextPassword.getText().toString())).addOnCompleteListener(new OnCompleteListener <AuthResult>() {
                @Override
                public void onComplete(@NonNull Task <AuthResult> task) {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {



                        if (editTextEmail.getText().toString().equals("gourisankar006@gmail.com")) {
                            Intent i = new Intent(LoginPage.this , adminDashBoard.class);
                            Toast.makeText(getApplicationContext() , "HELLO ADMIN..You have successfully logged-In" , Toast.LENGTH_SHORT).show();
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext() , "LogIn Successful" , Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginPage.this , empDashBoard.class);
                            String loginEmail=editTextEmail.getText().toString();
                            i.putExtra("key_email", loginEmail);
                            startActivity(i);
                        }

                    } else {
                        Log.e("ERROR" , task.getException().toString());
                        Toast.makeText(getApplicationContext() , task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
    public void go_to_reg(View view) {

        Intent j= new Intent(LoginPage.this,RegistrationPage.class);
        startActivity(j);
    }




}
