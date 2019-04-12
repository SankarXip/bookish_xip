package com.example.xipproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {


    private FirebaseAuth mAuth;
    EditText editTextEmail, editTextPassword;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        mAuth = FirebaseAuth.getInstance();

    }

    public void log_in(View view) {
        progressBar.setVisibility(View.VISIBLE);

        (mAuth.signInWithEmailAndPassword(editTextEmail.getText().toString(),editTextPassword.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful())
                {



                    Toast.makeText(getApplicationContext(), "LogIn Successfull", Toast.LENGTH_SHORT).show();
                    if(editTextEmail.getText().toString().equals("gourisankar006@gmail.com"))
                    {
                        Intent i = new Intent(LoginPage.this , adminDashBoard.class);
                        Toast.makeText(getApplicationContext(), "HELLO ADMIN", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    }
                    else
                        {
                            Intent i = new Intent(LoginPage.this , empDashBoard.class);
                            startActivity(i);
                        }

                }
                else
                {
                    Log.e("ERROR",task.getException().toString());
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void go_to_reg(View view) {

        Intent j= new Intent(LoginPage.this,RegistrationPage.class);
        startActivity(j);
    }




}
