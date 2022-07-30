package com.example.infs3605_t11a_g1_app;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

public class OverviewActivity extends AppCompatActivity {
    private Button mUpdate;
    private TextView mTargetSdgOne, mTargetSdgTwo, mTargetKpiOne, mTargetKpiTwo,
            mBaselineKpiOne, mBaselineKpiTwo, mCurrentKpiOne, mCurrentKpiTwo,
            mImpactScore, mBaseAchieve;
    private ProjectLeader projectLeader = new ProjectLeader();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        mAuth = FirebaseAuth.getInstance();

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

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        databaseRef.addValueEventListener(new ValueEventListener() {
            //        databaseRef.child("ProjectLeader").addValueEventListener(new ValueEventListener() {
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
        //                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    ProjectLeader projectLeader = postSnapshot.child("ProjectLeader").getValue(ProjectLeader.class);
//
//                    String targetOne = projectLeader.getTargetOne();
//                    String targetTwo = projectLeader.getTargetTwo();
//                    String kpiOneSpin = projectLeader.getKpiOneSpin();
//                    String kpiOne = projectLeader.getKpiOne();
//                    String kpiTwoSpin = projectLeader.getKpiTwoSpin();
//                    String kpiTwo = projectLeader.getKpiTwo();
//                    String currentOne = projectLeader.getCurrentOne();
//                    String currentTwo = projectLeader.getCurrentTwo();
//
//                    mTargetSdgOne.setText((CharSequence) targetOne);
//                    mTargetSdgTwo.setText((CharSequence) targetTwo);
//                    mTargetKpiOne.setText((CharSequence) kpiOneSpin);
//                    mTargetKpiTwo.setText((CharSequence) kpiTwoSpin);
//                    mBaselineKpiOne.setText((CharSequence) kpiOne);
//                    mBaselineKpiTwo.setText((CharSequence) kpiTwo);
//                    mCurrentKpiOne.setText((CharSequence) currentOne);
//                    mCurrentKpiTwo.setText((CharSequence) currentTwo);