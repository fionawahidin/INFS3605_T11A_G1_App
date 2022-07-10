package com.example.infs3605_t11a_g1_app;

import static android.view.View.INVISIBLE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.button.MaterialButton;

public class WelcomeActivity extends AppCompatActivity {

    private static final String TAG = "WelcomeActivity";
    private ImageView icon;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();

        title = findViewById(R.id.app_title);
        icon = findViewById(R.id.app_icon);
        title.setVisibility(View.INVISIBLE);
        icon.setVisibility(View.INVISIBLE);

        MaterialButton btnSignUp = findViewById(R.id.welcome_btnSignUp);
        MaterialButton btnSignIn = findViewById(R.id.welcome_btnSignIn);
        btnSignUp.setVisibility(INVISIBLE);
        btnSignIn.setVisibility(INVISIBLE);

        btnSignUp.setOnClickListener(view ->
                Toast.makeText(WelcomeActivity.this, "unavailable", Toast.LENGTH_SHORT).show());

        btnSignIn.setOnClickListener(view ->
                startActivity(new Intent(WelcomeActivity.this, SignInActivity.class)));

        startMainAnimations(icon, title);

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> startSecondaryAnimations(btnSignIn, btnSignUp), 1800);
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

    public void startSecondaryAnimations(MaterialButton btn1, MaterialButton btn2) {
        Log.d(TAG, "Sequence 2");
        btn1.setVisibility(View.VISIBLE);
        btn2.setVisibility(View.VISIBLE);

        YoYo.with(Techniques.FadeIn)
                .duration(1800)
                .repeat(0)
                .playOn(btn1);

        YoYo.with(Techniques.FadeIn)
                .duration(1800)
                .repeat(0)
                .playOn(btn2);
    }
}