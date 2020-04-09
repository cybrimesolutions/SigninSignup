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

public class Register extends AppCompatActivity {

    EditText efuln,emailid,phnumber,rgpassword;
    Button registerbutton;
    TextView registedlogin;
    ProgressBar progesivebar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        efuln = findViewById(R.id.efuln);
        emailid = findViewById(R.id.emailid);
        phnumber = findViewById(R.id.phnumber);
        rgpassword = findViewById(R.id.rgpassword);
        registerbutton = findViewById(R.id.registerbutton);
        registedlogin = findViewById(R.id.registedlogin);
        progesivebar = findViewById(R.id.progesivebar);

        fAuth = FirebaseAuth.getInstance();
        progesivebar = findViewById(R.id.progesivebar);


        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= emailid.getText().toString().trim();
                String password =  rgpassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){

                    emailid.setError("EMail is Required");

                    return;
                }
                if(TextUtils.isEmpty(password)){

                    rgpassword.setError("Enter Password");
                    return;
                }

                if(password.length() < 8 ){

                    rgpassword.setError("Password Must be 8 or more Characters");
                    return;
                }

                progesivebar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));


                        }else {

                            Toast.makeText(Register.this, "Error!" + task.getException() .getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
            registedlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(getApplicationContext(),Login.class));

                }
            });

    }
}
