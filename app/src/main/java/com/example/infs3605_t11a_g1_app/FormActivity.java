package com.example.infs3605_t11a_g1_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FormActivity extends AppCompatActivity {
    TextView mTargetSdgOne, mTargetSdgTwo, mTargetKpiOne, mTargetKpiTwo, mBaselineKpiOne, mBaselineKpiTwo;
    EditText mCurrentKpiOne, mCurrentKpiTwo, mLink;
    Button mSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_form);

        mTargetSdgOne = findViewById(R.id.tv_sdgTargetOneForm);
        mTargetSdgTwo = findViewById(R.id.tv_sdgTargetTwoForm);
        mTargetKpiOne = findViewById(R.id.tv_targetKpiOneForm);
        mTargetKpiTwo = findViewById(R.id.tv_targetKpiTwoForm);
        mBaselineKpiOne = findViewById(R.id.tv_baseKpiOneForm);
        mBaselineKpiTwo = findViewById(R.id.tv_baseKpiTwoForm);
        mCurrentKpiOne = findViewById(R.id.et_currentKpiOneForm);
        mCurrentKpiTwo = findViewById(R.id.et_currentKpiTwoForm);
        mLink = findViewById(R.id.et_link);

        mSubmit = findViewById(R.id.btn_formSubmit);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentOne = mCurrentKpiOne.getText().toString();
                String currentTwo = mCurrentKpiTwo.getText().toString();
                String link = mLink.getText().toString();

                if (TextUtils.isEmpty(currentOne) || TextUtils.isEmpty(currentTwo) || TextUtils.isEmpty(link)) {
                    Toast.makeText(FormActivity.this, "Please enter all fields and upload evidence.", Toast.LENGTH_SHORT).show();
                } else {
                    submitUpdate(currentOne, currentTwo, link);
                }
            }

            final DatabaseReference databaseReferenceOne = FirebaseDatabase.getInstance().getReference("ProjectLeader").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
            private void submitUpdate(String currentOne, String currentTwo, String link) {
                databaseReferenceOne.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        float kpiOne = 0;
                        float kpiTwo = 0;
                        if (mTargetKpiOne.getText().toString().equals("10+ flagship species on recovery path")) {
                            kpiOne = 10;
                        } else if (mTargetKpiOne.getText().toString().equals("10+ culturally important species on recovery path")) {
                            kpiOne = 10;
                        } else if (mTargetKpiOne.getText().toString().equals("100,000 ha under restoration")) {
                            kpiOne = 100000;
                        } else if (mTargetKpiOne.getText().toString().equals("100,000 ha under restoration")) {
                            kpiOne = 100000;
                        } else if (mTargetKpiOne.getText().toString().equals("75+ Indigenous organisations/communities supported in their efforts to revitalise cultural fire management")) {
                            kpiOne = 75;
                        } else if (mTargetKpiOne.getText().toString().equals("300 land manager adopt nature-based solutions")) {
                            kpiOne = 300;
                        } else if (mTargetKpiOne.getText().toString().equals("150 regional jobs created")) {
                            kpiOne = 150;
                        } else {
                            Toast.makeText(FormActivity.this, "Invalid submission", Toast.LENGTH_SHORT).show();
                        }

                        if (mTargetKpiTwo.getText().toString().equals("10+ flagship species on recovery path")) {
                            kpiTwo = 10;
                        } else if (mTargetKpiTwo.getText().toString().equals("10+ culturally important species on recovery path")) {
                            kpiTwo = 10;
                        } else if (mTargetKpiTwo.getText().toString().equals("100,000 ha under restoration")) {
                            kpiTwo = 100000;
                        } else if (mTargetKpiTwo.getText().toString().equals("100,000 ha under restoration")) {
                            kpiTwo = 100000;
                        } else if (mTargetKpiTwo.getText().toString().equals("75+ Indigenous organisations/communities supported in their efforts to revitalise cultural fire management")) {
                            kpiTwo = 75;
                        } else if (mTargetKpiTwo.getText().toString().equals("300 land manager adopt nature-based solutions")) {
                            kpiTwo = 300;
                        } else if (mTargetKpiTwo.getText().toString().equals("150 regional jobs created")) {
                            kpiTwo = 150;
                        } else {
                            Toast.makeText(FormActivity.this, "Invalid submission", Toast.LENGTH_SHORT).show();
                        }

                        float impactScoreFloat = (float) (((Float.parseFloat(currentOne) / kpiOne * 0.5) + (Float.parseFloat(currentTwo) / kpiTwo) * 0.5) * 100);
                        double impactScoreDouble = Double.parseDouble(String.valueOf(impactScoreFloat));
                        String impactScore = Double.toString(impactScoreDouble);

                        String baseOne = mBaselineKpiOne.getText().toString();
                        String baseTwo = mBaselineKpiTwo.getText().toString();

                        float baseComparisonOne = (Float.parseFloat(currentOne) / Float.parseFloat(baseOne));
                        float baseComparisonTwo = (Float.parseFloat(currentTwo) / Float.parseFloat(baseTwo));

                        String baselineAchieve;
                        if (baseComparisonOne >= 1 && baseComparisonTwo >= 1) {
                            baselineAchieve = "Baseline Achieved";
                        } else {
                            baselineAchieve = "Baseline Not Reached";
                        }

                        Map<String, Object> updates = new HashMap<>();
                        updates.put("currentOne", currentOne);
                        updates.put("currentTwo", currentTwo);
                        updates.put("link", link);
                        updates.put("impactScore", impactScore);
                        updates.put("baselineAchieve", baselineAchieve);
                        databaseReferenceOne.updateChildren(updates);
                        startActivity(new Intent(FormActivity.this, OverviewActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(FormActivity.this, "Failed to submit" + error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        DatabaseReference databaseReferenceTwo = FirebaseDatabase.getInstance().getReference("ProjectLeader").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
        databaseReferenceTwo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String targetOne = snapshot.child("targetOne").getValue(String.class);
                    String targetTwo = snapshot.child("targetTwo").getValue(String.class);
                    String kpiOne = snapshot.child("kpiOne").getValue(String.class);
                    String kpiTwo = snapshot.child("kpiTwo").getValue(String.class);
                    String kpiOneSpin = snapshot.child("kpiOneSpin").getValue(String.class);
                    String kpiTwoSpin = snapshot.child("kpiTwoSpin").getValue(String.class);

                    mTargetSdgOne.setText(targetOne);
                    mTargetSdgTwo.setText(targetTwo);
                    mBaselineKpiOne.setText(kpiOne);
                    mBaselineKpiTwo.setText(kpiTwo);
                    mTargetKpiOne.setText(kpiOneSpin);
                    mTargetKpiTwo.setText(kpiTwoSpin);
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FormActivity.this, "Failed to Load" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}