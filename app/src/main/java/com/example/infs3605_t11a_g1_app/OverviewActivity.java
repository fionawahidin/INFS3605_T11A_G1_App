package com.example.infs3605_t11a_g1_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OverviewActivity extends AppCompatActivity {
    private Button mUpdate;
    private TextView mTargetSdgOne, mTargetSdgTwo, mTargetKpiOne, mTargetKpiTwo,
            mBaselineKpiOne, mBaselineKpiTwo, mCurrentKpiOne, mCurrentKpiTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        mTargetSdgOne = findViewById(R.id.tv_sdgTargetOne);
        mTargetSdgTwo = findViewById(R.id.tv_sdgTargetTwo);
        mTargetKpiOne = findViewById(R.id.tv_targetKpiOne);
        mTargetKpiTwo = findViewById(R.id.tv_targetKpiTwo);
        mBaselineKpiOne = findViewById(R.id.tv_baseKpiOne);
        mBaselineKpiTwo = findViewById(R.id.tv_baseKpiTwo);
        mCurrentKpiOne = findViewById(R.id.tv_currentKpiOne);
        mCurrentKpiTwo = findViewById(R.id.tv_currentKpiTwo);


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
