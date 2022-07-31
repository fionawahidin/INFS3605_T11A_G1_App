package com.example.infs3605_t11a_g1_app;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class OverviewActivity extends AppCompatActivity {
    private Button mUpdate;
    private TextView mTargetSdgOne, mTargetSdgTwo, mTargetKpiOne, mTargetKpiTwo,
            mBaselineKpiOne, mBaselineKpiTwo, mCurrentKpiOne, mCurrentKpiTwo,
            mImpactScore, mBaseAchieve;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ProjectLeader");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_overview);
        getSupportActionBar().hide();

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

        mUpdate = findViewById(R.id.btn_update);
        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FormActivity.class);
                startActivity(intent);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String targetOne = ds.child("targetOne").getValue(String.class);
                    String targetTwo = ds.child("targetTwo").getValue(String.class);
                    String kpiOne = ds.child("kpiOne").getValue(String.class);
                    String kpiTwo = ds.child("kpiTwo").getValue(String.class);
                    String kpiOneSpin = ds.child("kpiOneSpin").getValue(String.class);
                    String kpiTwoSpin = ds.child("kpiTwoSpin").getValue(String.class);
                    String currentOne = ds.child("currentOne").getValue(String.class);
                    String currentTwo = ds.child("currentTwo").getValue(String.class);
                    String impactScore = ds.child("impactScore").getValue(String.class);
                    String baselineAchieve = ds.child("baselineAchieve").getValue(String.class);

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
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(OverviewActivity.this, "Failed to Load" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}