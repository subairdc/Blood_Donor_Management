package com.subairdc.bdma.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.subairdc.bdma.R;
import com.subairdc.bdma.databinding.ActivityRegisterUserBinding;

import java.util.Calendar;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private TextView bannar, registerUser;
    private EditText editTextName, editTextPhoneNo, editTextEmail, editTextPassword, editTextConfirmPassword;
    private ProgressBar progressBar;
    DatePickerDialog.OnDateSetListener setListener;

    ActivityRegisterUserBinding binding;
    RadioButton radioButton;
    String gender;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_register_user);
        binding = ActivityRegisterUserBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();

        //bannar = (TextView) findViewById(R.id.bannar);
        binding.bannar.setOnClickListener(this);

        //registerUser = (Button) findViewById(R.id.registerUser);
        binding.registerUser.setOnClickListener(this);

        //editTextName = (EditText) findViewById(R.id.nameTitle);
        editTextPhoneNo = (EditText) findViewById(R.id.phoneNo);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);
        editTextConfirmPassword = (EditText) findViewById(R.id.confirmPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        binding.ivdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        RegisterUser.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        binding.dob.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
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
        String name = binding.name.getText().toString().trim();
        int id = binding.rgGender.getCheckedRadioButtonId();
        String dob = binding.dob.getText().toString().trim();
        String phoneNo = binding.phoneNo.getText().toString().trim();
        String email = binding.email.getText().toString().trim();
        String password = binding.password.getText().toString().trim();
        String confirmPassword = binding.confirmPassword.getText().toString().trim();

        if(name.isEmpty()){
            binding.name.setError("Full Name is required");
            binding.name.requestFocus();
            return;
        }else if (name.length()>=20){
            binding.name.setError("Name length is only 15 ");
            binding.name.requestFocus();
            return;
        }
        switch (id){
            case R.id.rbMale:
                gender = "Male";
                break;

            case R.id.rbFemale:
                gender = "Female";
                break;

            case R.id.rbOther:
                gender = "Other";
                break;
        }

        if(dob.isEmpty()){
            binding.dob.setError("Date of Birth is required");
            binding.dob.requestFocus();
            return;
        }

        if(phoneNo.isEmpty()){
            binding.phoneNo.setError("Phone Number is required");
            binding.phoneNo.requestFocus();
            return;
        }

        if(phoneNo.length() < 10){
            binding.phoneNo.setError("Please provide valid Phone Number");
            binding.phoneNo.requestFocus();
            return;
        }

        if(email.isEmpty()){
            binding.email.setError("email is required");
            binding.email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.email.setError("Please provide valid Email");
            binding.email.requestFocus();
            return;
        }

        if(password.isEmpty()){
            binding.password.setError("Password is required");
            binding.password.requestFocus();
            return;
        }

        if(password.length() < 6){
            binding.password.setError("Min Password length should 6 characters!");
            binding.password.requestFocus();
            return;
        }

        if(confirmPassword != null && !confirmPassword.equals(password)) {
            binding.confirmPassword.setError("Password Doesn't Match");
            binding.confirmPassword.requestFocus();
            return;
            }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            User user = new User(name, gender, dob, phoneNo, email, password);
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