package com.example.infs3605_t11a_g1_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SignInActivity extends AppCompatActivity {

    private TextInputEditText etEmail, etPassword;
    private ProgressBar progressBar;
    private static final String TAG = SignInActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();

        MaterialButton btnSignIn = findViewById(R.id.signIn_btnSignIn);
        TextView tvSignUp = findViewById(R.id.signIn_tvSignUp);
        TextView tvForgotPassword = findViewById(R.id.tvForgotPassword);
        progressBar = findViewById(R.id.signIn_progressBar);
        etEmail = findViewById(R.id.signIn_etEmail);
        etPassword = findViewById(R.id.signIn_etPassword);

        tvSignUp.setOnClickListener(view -> Toast.makeText(SignInActivity.this, "unavailable", Toast.LENGTH_SHORT).show());

        tvForgotPassword.setOnClickListener(view -> Toast.makeText(SignInActivity.this, "unlucky lol", Toast.LENGTH_SHORT).show());

        btnSignIn.setOnClickListener(view -> handleSignInOnClick());
    }

    private void handleSignInOnClick() {
        progressBar.setVisibility(View.VISIBLE);

        String email = etEmail.getEditableText().toString().trim();
        String password = etPassword.getEditableText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            etEmail.setError("Email is required!");
            etEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please provide valid email!");
            etEmail.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(password)) {
            etPassword.setError("Password is required!");
            etPassword.requestFocus();
        }

        else {
            if (etEmail.getText().toString().equals("admin@impactio.com") && etPassword.getText().toString().equals("admin")) {
                Toast.makeText(SignInActivity.this, "Successfully signed in!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignInActivity.this, MainActivity.class));
            } else {
                Toast.makeText(SignInActivity.this, "Failed to sign in!", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            };
        }
    }

}