package com.example.infs3605_t11a_g1_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class StartScreen extends AppCompatActivity {
    private static final String TAG = "StartScreen";
    private TextView mainTitle;
    private TextView secondaryTitle;

    private ImageView mainIcon;
    private ImageView secondaryIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        getSupportActionBar().hide();

        mainTitle = findViewById(R.id.main_title);
        secondaryTitle = findViewById(R.id.secondary_title);
        mainIcon = findViewById(R.id.start_icon1);
        secondaryIcon = findViewById(R.id.start_icon2);

        mainTitle.setVisibility(INVISIBLE);
        secondaryTitle.setVisibility(INVISIBLE);
        mainIcon.setVisibility(INVISIBLE);
        secondaryIcon.setVisibility(INVISIBLE);

        startMainAnimations(mainIcon, mainTitle);

        final Handler delay = new Handler(Looper.getMainLooper());
        delay.postDelayed(new Runnable() {
            @Override
            public void run() {
                startSecondAnimations(secondaryIcon, secondaryTitle);
            }
        }, 1700);

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                launchMainActivity();
            }
        }, 3500);
    }

    public void launchMainActivity() {
        Log.d(TAG, "Launching MainActivity");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void startMainAnimations(ImageView imageView, TextView textView) {
        Log.d(TAG, "Sequence 1");
        mainIcon.setVisibility(VISIBLE);
        mainTitle.setVisibility(VISIBLE);

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

    public void startSecondAnimations(ImageView imageView, TextView textView) {
        Log.d(TAG, "Sequence 2");
        secondaryIcon.setVisibility(VISIBLE);
        secondaryTitle.setVisibility(VISIBLE);

        YoYo.with(Techniques.FadeIn)
                .duration(2000)
                .repeat(0)
                .playOn(imageView);

        YoYo.with(Techniques.FadeIn)
                .duration(2000)
                .repeat(0)
                .playOn(textView);
    }
}