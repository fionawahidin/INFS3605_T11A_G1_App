package com.example.infs3605_t11a_g1_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class OverviewActivity extends AppCompatActivity {
    private TextView mTargetSdgOne, mTargetSdgTwo, mTargetKpiOne, mTargetKpiTwo,
            mBaselineKpiOne, mBaselineKpiTwo, mCurrentKpiOne, mCurrentKpiTwo,
            mImpactScore, mBaseAchieve, mLink;

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
        mImpactScore = findViewById(R.id.tv_impact);
        mBaseAchieve = findViewById(R.id.tv_baseAchieve);
        mLink = findViewById(R.id.tv_linkForm);

        Button mStats = findViewById(R.id.btn_profile);
        mStats.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ProjectLeaderProfileActivity.class);
            startActivity(intent);
        });

        Button mUpdate = findViewById(R.id.btn_update);
        mUpdate.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), FormActivity.class);
            startActivity(intent);
        });

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ProjectLeader").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String targetOne = dataSnapshot.child("targetOne").getValue(String.class);
                    String targetTwo = dataSnapshot.child("targetTwo").getValue(String.class);
                    String kpiOne = dataSnapshot.child("kpiOne").getValue(String.class);
                    String kpiTwo = dataSnapshot.child("kpiTwo").getValue(String.class);
                    String kpiOneSpin = dataSnapshot.child("kpiOneSpin").getValue(String.class);
                    String kpiTwoSpin = dataSnapshot.child("kpiTwoSpin").getValue(String.class);
                    String currentOne = dataSnapshot.child("currentOne").getValue(String.class);
                    String currentTwo = dataSnapshot.child("currentTwo").getValue(String.class);
                    String impactScore = dataSnapshot.child("impactScore").getValue(String.class);
                    String baselineAchieve = dataSnapshot.child("baselineAchieve").getValue(String.class);
                    String link = dataSnapshot.child("link").getValue(String.class);

                    mTargetSdgOne.setText(targetOne);
                    mTargetSdgTwo.setText(targetTwo);
                    mBaselineKpiOne.setText(kpiOne);
                    mBaselineKpiTwo.setText(kpiTwo);
                    mTargetKpiOne.setText(kpiOneSpin);
                    mTargetKpiTwo.setText(kpiTwoSpin);
                    mCurrentKpiOne.setText(currentOne);
                    mCurrentKpiTwo.setText(currentTwo);
                    mImpactScore.setText(impactScore);
                    mBaseAchieve.setText(baselineAchieve);
                    mLink.setText(link);
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(OverviewActivity.this, "Failed to Load" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}