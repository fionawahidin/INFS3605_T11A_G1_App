package com.example.infs3605_t11a_g1_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private final DatabaseReference mRoot = FirebaseDatabase.getInstance().getReference("users");
    private ProgressBar progressBar;
    private TextInputEditText etFullName, etEmail, etPassword, etConfirmPassword;
    private RadioButton curatorSelect;
    private RadioButton projectLeaderSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        MaterialButton btnSignUp = findViewById(R.id.signUp_btnSignUp);
        TextView tvSignIn = findViewById(R.id.signUp_tvSignIn);
        progressBar = findViewById(R.id.signUp_progressBar);
        etEmail = findViewById(R.id.signUp_etEmail);
        etPassword = findViewById(R.id.signUp_etPassword);
        etConfirmPassword = findViewById(R.id.signUp_etConfirmPassword);
        etFullName = findViewById(R.id.signUp_etFullName);
        curatorSelect = findViewById(R.id.curator);
        projectLeaderSelect = findViewById(R.id.project_leader);

        tvSignIn.setOnClickListener(view -> startActivity(new Intent(SignUpActivity.this, SignInActivity.class)));
        btnSignUp.setOnClickListener(view -> handleSignUpOnClick());

        Dialog dialog = new Dialog(SignUpActivity.this);
        dialog.setContentView(R.layout.dialog_sign_up);
        dialog.show();

        final Handler delay = new Handler(Looper.getMainLooper());
        delay.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 5000);
    }

    private void handleSignUpOnClick() {
        String fullName = etFullName.getEditableText().toString().trim();
        String email = etEmail.getEditableText().toString().trim();
        String password = etPassword.getEditableText().toString().trim();
        String confirmPassword = etConfirmPassword.getEditableText().toString().trim();

        if(TextUtils.isEmpty(fullName) || TextUtils.isEmpty(email)
                || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(SignUpActivity.this, "Please fill in the blanks!", Toast.LENGTH_SHORT).show();
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please provide valid email!");
            etEmail.requestFocus();
        }

        if(password.length() < 6) {
            etPassword.setError("Your password must be at least 6 characters long!");
            etPassword.requestFocus();
        }

        if(!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Password does not match!");
            etConfirmPassword.requestFocus();
        }

        else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                    task -> {
                        if(task.isSuccessful()) {
                            User user = new User(fullName, email);
                            mRoot.child(mAuth.getCurrentUser().getUid()).setValue(user)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()) {
                                                Toast.makeText(SignUpActivity.this,
                                                        "Successfully signed up!", Toast.LENGTH_SHORT)
                                                        .show();
                                                if (curatorSelect.isChecked()) {
                                                    startActivity(new Intent(SignUpActivity.this, SignInActivity.class));

                                                } else if (projectLeaderSelect.isChecked()){
                                                    startActivity(new Intent(SignUpActivity.this, CreateProjectLeaderActivity.class));
                                                } else {
                                                    task.getException();
                                                }
                                            } else {
                                                Toast.makeText(SignUpActivity.this,
                                                        "Failed to sign up!", Toast.LENGTH_SHORT)
                                                        .show();
                                                task.getException();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                        }
                    }
            );
        }

    }
}