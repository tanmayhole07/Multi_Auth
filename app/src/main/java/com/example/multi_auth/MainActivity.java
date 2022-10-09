package com.example.multi_auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText inputMobile;
    Button btnGetOtp;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputMobile = findViewById(R.id.inputMobile);
        btnGetOtp = findViewById(R.id.buttonGetOTP);
        progressBar = findViewById(R.id.progressBar);



        btnGetOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputMobile.getText().toString().trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter Mobile", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                btnGetOtp.setVisibility(View.INVISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91"+inputMobile.getText().toString(),
                        60,
                        TimeUnit.SECONDS,
                        MainActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                progressBar.setVisibility(View.GONE);
                                btnGetOtp.setVisibility(View.VISIBLE);

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                progressBar.setVisibility(View.GONE);
                                btnGetOtp.setVisibility(View.VISIBLE);
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verificationID, forceResendingToken);
                                progressBar.setVisibility(View.GONE);
                                btnGetOtp.setVisibility(View.VISIBLE);

                                Intent i = new Intent(getApplicationContext(), Verify_Activity.class);
                                i.putExtra("mobile", inputMobile.getText().toString());
                                i.putExtra("verificationID",verificationID);
                                startActivity(i);
                            }
                        }
                );



            }
        });

    }
}