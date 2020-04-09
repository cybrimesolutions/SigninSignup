package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText loginmail, loginpass;
    Button loginbutton;
    TextView reg_login;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginmail = findViewById(R.id.loginmail);
        loginpass = findViewById(R.id.loginpass);
        loginbutton = findViewById(R.id.loginbutton);
        reg_login = findViewById(R.id.reg_login);
        progressBar = findViewById(R.id.progesivebar);
        fAuth = FirebaseAuth.getInstance();


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email= loginmail.getText().toString().trim();
                String password =  loginpass.getText().toString().trim();

                if(TextUtils.isEmpty(email)){

                    loginmail.setError("EMail is Required");

                    return;
                }
                if(TextUtils.isEmpty(password)){

                    loginpass.setError("Enter Password");
                    return;
                }

                if(password.length() < 8 ){

                    loginpass.setError("Password Must be 8 or more Characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            Toast.makeText(Login.this, "User Signing Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(Login.this, "Error!" + task.getException() .getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        reg_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
    }
}
