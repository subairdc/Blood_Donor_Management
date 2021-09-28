package com.subairdc.bdma.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.subairdc.bdma.R;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private TextView bannar, registerUser;
    private EditText editTextName, editTextPhoneNo, editTextEmail, editTextPassword, editTextConfirmPassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        bannar = (TextView) findViewById(R.id.bannar);
        bannar.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        editTextName = (EditText) findViewById(R.id.nameTitle);
        editTextPhoneNo = (EditText) findViewById(R.id.phoneNo);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);
        editTextConfirmPassword = (EditText) findViewById(R.id.confirmPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bannar:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.registerUser:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String name = editTextName.getText().toString().trim();
        String phoneNo = editTextPhoneNo.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        if(name.isEmpty()){
            editTextName.setError("Full Name is required");
            editTextName.requestFocus();
            return;
        }

        if(phoneNo.isEmpty()){
            editTextPhoneNo.setError("Phone Number is required");
            editTextPhoneNo.requestFocus();
            return;
        }

        if(phoneNo.length() < 10){
            editTextPhoneNo.setError("Phone Number is required");
            editTextPhoneNo.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmail.setError("email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid Email");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextPassword.setError("Min Password length should 6 characters!");
            editTextPassword.requestFocus();
            return;
        }

        if(confirmPassword != null && !confirmPassword.equals(password)) {
            editTextConfirmPassword.setError("Password Doesn't Match");
            editTextConfirmPassword.requestFocus();
            return;
            }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            User user = new User(name, phoneNo, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        Toast.makeText(RegisterUser.this, "User has been registered successfull", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(RegisterUser.this, LoginActivity.class));
                                        progressBar.setVisibility(View.INVISIBLE);
                                        //Redirect to login layout
                                    }else {
                                        Toast.makeText(RegisterUser.this, "Failed to register! Pls Try Again", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.INVISIBLE);
                                    }

                                }
                            });

                        }/*else {
                            Toast.makeText(RegisterUser.this, "Failed to register Pls Try Again", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }*/
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterUser.this, e.getMessage(),Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        }

    }