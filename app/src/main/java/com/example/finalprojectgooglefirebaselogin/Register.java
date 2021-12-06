package com.example.finalprojectgooglefirebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText registerFullName,registerEmail,registerPassword,registerConfirmPassword;
    Button registerButton,registerLogin;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //identifiers
        registerFullName = findViewById(R.id.registerFullName);
        registerEmail = findViewById(R.id.registerEmail);
        registerPassword = findViewById(R.id.registerPassword);
        registerConfirmPassword = findViewById(R.id.registerConfirmPassword);
        registerButton = findViewById(R.id.registerButton);
        registerLogin = findViewById(R.id.registerLogin);

        //used for firebase login
        fAuth = FirebaseAuth.getInstance();

        //button to go back to login screen
        registerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //strings for data
                String fullName = registerFullName.getText().toString();
                String email = registerEmail.getText().toString();
                String password = registerPassword.getText().toString();
                String confirmPassword = registerConfirmPassword.getText().toString();

                //statement check to make sure data is correct and will display error
                if(fullName.isEmpty())
                {
                    registerFullName.setError("Full Name is Required to Register!");
                    return;
                }

                if (email.isEmpty())
                {
                    registerEmail.setError("Full Name is Required to Register!");
                    return;
                }

                if(password.isEmpty())
                {
                    registerPassword.setError("Password is Required to Register!");
                    return;
                }

                if(confirmPassword.isEmpty())
                {
                    registerConfirmPassword.setError("Please re-enter in the same password to Register!");
                    return;
                }

                if(!confirmPassword.equals(password))
                {
                    registerConfirmPassword.setError("Passwords DO NOT match!");
                    return;
                }

                //toast message
                Toast.makeText(Register.this,  "Register Successfully!",Toast.LENGTH_SHORT).show();

                //used for success or fail for firebase for login
                fAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this,  e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });





    }
}