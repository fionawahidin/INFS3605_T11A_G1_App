package com.example.infs3605_t11a_g1_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OverviewActivity extends AppCompatActivity {
    private Button mUpdate;
    private TextView mProgress, mProblems, mPlans, mPercentage, mFunding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        Intent formIntent = getIntent();
        mProgress = findViewById(R.id.tv_progress);
        mProgress.setText(formIntent.getStringExtra("Progress"));
        mProblems = findViewById(R.id.tv_problems);
        mProblems.setText(formIntent.getStringExtra("Problems"));
        mPlans = findViewById(R.id.tv_plans);
        mPlans.setText(formIntent.getStringExtra("Plans"));
        mPercentage = findViewById(R.id.tv_percentage);
        mPercentage.setText(formIntent.getStringExtra("Percentage"));
        mFunding = findViewById(R.id.tv_funding);
        mFunding.setText(formIntent.getStringExtra("Funding"));

        mUpdate = findViewById(R.id.btn_update);
        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FormActivity.class);
                startActivity(intent);
            }
        });
    }
}
