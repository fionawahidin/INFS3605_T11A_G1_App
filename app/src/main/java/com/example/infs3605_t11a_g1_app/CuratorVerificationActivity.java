package com.example.infs3605_t11a_g1_app;

import android.os.Bundle;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class CuratorVerificationActivity extends AppCompatActivity {
    private TextView mTargetSdgOne, mTargetSdgTwo, mTargetKpiOne, mTargetKpiTwo,
            mBaselineKpiOne, mBaselineKpiTwo, mCurrentKpiOne, mCurrentKpiTwo,
            mImpactScore, mBaseAchieve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createcurator);

        mTargetSdgOne = findViewById(R.id.tv_sdgTargetTwoVerify);
        mTargetKpiTwo = findViewById(R.id.tv_sdgTargetTwoVerify);
        mTargetKpiOne = findViewById(R.id.tv_targetKpiOneVerify);
        mTargetKpiTwo = findViewById(R.id.tv_targetKpiTwoVerify);
        mBaselineKpiOne = findViewById(R.id.tv_baseKpiOneVerify);
        mBaselineKpiTwo = findViewById(R.id.tv_baseKpiTwoVerify);
        mCurrentKpiOne = findViewById(R.id.tv_currentKpiOneVerify);
        mCurrentKpiTwo = findViewById(R.id.tv_currentKpiTwoVerify);
        mImpactScore = findViewById(R.id.tv_impactVerify);
        mBaseAchieve = findViewById(R.id.tv_baseAchieveVerify);
    }
}
