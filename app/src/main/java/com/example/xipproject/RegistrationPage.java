package com.example.xipproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText editTextEmail, editTextPassword, name,  ph;
    Spinner dept;

    Button signup;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        name = (EditText) findViewById(R.id.editTextName);
        dept = (Spinner) findViewById(R.id.editTextDept);
        ph = (EditText) findViewById(R.id.editTextPhone);
        mAuth = FirebaseAuth.getInstance();

    }

    public void registration_click(View view) {

        if (name.length() == 0) {
            name.setError("Enter your name");
        } else if (ph.length()!=10) {
            ph.setError("Enter correct phone no. excluding +91");
        } else if (editTextEmail.getText().toString().length() == 0) {
            editTextPassword.setError("Enter your valid EmailId");
        } else if (editTextPassword.getText().toString().length() < 6) {
            editTextPassword.setError("Enter a min.6 digit password");
        } else {

            progressBar.setVisibility(View.VISIBLE);
            (mAuth.createUserWithEmailAndPassword(editTextEmail.getText().toString() , editTextPassword.getText().toString())).addOnCompleteListener(new OnCompleteListener <AuthResult>() {
                @Override
                public void onComplete(@NonNull Task <AuthResult> task) {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful())
                    {

                        Toast.makeText(getApplicationContext() , "You have registered successfully" , Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(RegistrationPage.this , LoginPage.class);
                        startActivity(i);
                    }
                    else
                        {
                        Log.e("ERROR" , task.getException().toString());
                        Toast.makeText(getApplicationContext() , task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
    }

        public void go_login (View view){
            Intent j = new Intent(RegistrationPage.this , LoginPage.class);
            startActivity(j);


        }

}
