package com.example.infs3605_t11a_g1_app;

import static android.view.View.INVISIBLE;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;


public class SignInActivity extends AppCompatActivity {

    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private TextInputEditText etEmail, etPassword;
    private ProgressBar progressBar;
    private static final String TAG = SignInActivity.class.getName();
    private ImageView icon;
    private TextView title;

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
        title = findViewById(R.id.signIn_title);
        icon = findViewById(R.id.app_icon);
        title.setVisibility(INVISIBLE);
        icon.setVisibility(INVISIBLE);
        btnSignIn.setVisibility(INVISIBLE);

        tvForgotPassword.setOnClickListener(view -> resetPassword());

        btnSignIn.setOnClickListener(view -> handleSignInOnClick());

        tvSignUp.setOnClickListener(view -> startActivity(new Intent(SignInActivity.this, SignUpActivity.class)));

        startMainAnimations(icon, title);

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> startSecondaryAnimations(btnSignIn), 1500);
    }

    private void handleSignInOnClick() {
        progressBar.setVisibility(View.VISIBLE);

        String email = etEmail.getEditableText().toString().trim();
        String password = etPassword.getEditableText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            etEmail.setError("Email is required!");
            etEmail.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please provide valid email!");
            etEmail.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if(TextUtils.isEmpty(password)) {
            etPassword.setError("Password is required!");
            etPassword.requestFocus();
            progressBar.setVisibility(View.GONE);
        }

        else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful() && mAuth.getCurrentUser().getEmail().endsWith("impactiocurator.com")) {
                    Toast.makeText(SignInActivity.this, "Successfully signed in!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                }

                else if (task.isSuccessful() && mAuth.getCurrentUser().getEmail().endsWith("impactiopl.com")) {
                    Toast.makeText(SignInActivity.this, "Successfully signed in!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignInActivity.this, OverviewActivity.class));
                }

                else {
                    Toast.makeText(SignInActivity.this, "Failed to sign in!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    task.getException();
                }
            });
        }
    }

    private void resetPassword() {
        Dialog dialog = new Dialog(SignInActivity.this);
        dialog.setContentView(R.layout.dialog_forgot_password);
        ProgressBar dialogProgressBar = dialog.findViewById(R.id.forgotPassword_progressBar);
        TextInputEditText resetPasswordEmail = dialog.findViewById(R.id.forgotPassword_etEmail);
        MaterialButton btnSubmit = dialog.findViewById(R.id.forgotPassword_btnSubmit);
        TextView tvOutput = dialog.findViewById(R.id.forgotPassword_tvOutput);

        btnSubmit.setOnClickListener(view -> {
            dialogProgressBar.setVisibility(View.VISIBLE);
            String email = resetPasswordEmail.getEditableText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                resetPasswordEmail.setError("Email is required!");
                resetPasswordEmail.requestFocus();
                dialogProgressBar.setVisibility(View.GONE);
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                resetPasswordEmail.setError("Please provide valid email!");
                resetPasswordEmail.requestFocus();
                dialogProgressBar.setVisibility(View.GONE);
            }

            else {
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        CharSequence output = "Please check your email to reset password!";
                        tvOutput.setText(output);
                        final Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(dialog::dismiss, 2000);
                    } else {
                        CharSequence output = "We cannot find your email. Please try again!";
                        tvOutput.setText(output);
                        dialogProgressBar.setVisibility(View.GONE);
                    }
                });
            }
        });

        dialog.show();
    }

    public void startMainAnimations(ImageView imageView, TextView textView) {
        Log.d(TAG, "Sequence 1");
        icon.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);

        YoYo.with(Techniques.FadeIn)
                .duration(2000)
                .repeat(0)
                .playOn(imageView);

        YoYo.with(Techniques.Bounce)
                .duration(2000)
                .repeat(0)
                .playOn(textView);

        YoYo.with(Techniques.FadeIn)
                .duration(2000)
                .repeat(0)
                .playOn(textView);
    }

    public void startSecondaryAnimations(MaterialButton btn1) {
        Log.d(TAG, "Sequence 2");
        btn1.setVisibility(View.VISIBLE);

        YoYo.with(Techniques.FadeIn)
                .duration(1800)
                .repeat(0)
                .playOn(btn1);
    }
}