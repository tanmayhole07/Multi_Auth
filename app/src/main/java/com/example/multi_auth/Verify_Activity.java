package com.example.multi_auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class Verify_Activity extends AppCompatActivity {

    EditText ic1, ic2, ic3, ic4, ic5, ic6;

    TextView textmobile;
    ProgressBar progressBar;
    Button btnVerify;
    String verfID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        ic1 = findViewById(R.id.inputcode1);
        ic2 = findViewById(R.id.inputcode2);
        ic3 = findViewById(R.id.inputcode3);
        ic4 = findViewById(R.id.inputcode4);
        ic5 = findViewById(R.id.inputcode5);
        ic6 = findViewById(R.id.inputcode6);
        textmobile = findViewById(R.id.textMobile);
        progressBar = findViewById(R.id.progressBar);
        btnVerify = findViewById(R.id.buttonVerification);

        verfID  = getIntent().getStringExtra("verificationID");
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ic1.getText().toString().trim().isEmpty()
                        || ic2.getText().toString().trim().isEmpty()
                        || ic3.getText().toString().trim().isEmpty()
                        || ic4.getText().toString().trim().isEmpty()
                        || ic5.getText().toString().trim().isEmpty()
                        || ic6.getText().toString().trim().isEmpty()){
                    Toast.makeText(Verify_Activity.this, "Please Enter valid code", Toast.LENGTH_SHORT).show();
                }

                String code = ic1.getText().toString() + ic2.getText().toString()
                        + ic3.getText().toString() + ic4.getText().toString()
                        + ic5.getText().toString() + ic6.getText().toString();


                if (verfID != null){
                    progressBar.setVisibility(View.VISIBLE);
                    btnVerify.setVisibility(View.VISIBLE);
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            verfID, code
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            btnVerify.setVisibility(View.VISIBLE);
                            if (task.isSuccessful()){
                                Intent i = new Intent(getApplicationContext(), DashboardActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                            }else{
                                Toast.makeText(Verify_Activity.this, "Please enter valid code", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        textmobile.setText(String.format("+91-%s",getIntent().getStringExtra("mobile")));

        setOTPInputs();
    }

    private void setOTPInputs(){
        ic1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()){
                    ic2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ic2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()){
                    ic3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ic3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()){
                    ic4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ic4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()){
                    ic5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ic5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()){
                    ic6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}