package com.example.infs3605_t11a_g1_app;

import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
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

public class CuratorVerificationActivity extends AppCompatActivity {
    private TextView mTargetSdgOne, mTargetSdgTwo, mTargetKpiOne, mTargetKpiTwo,
            mBaselineKpiOne, mBaselineKpiTwo, mCurrentKpiOne, mCurrentKpiTwo,
            mImpactScore, mBaseAchieve;
    private CheckBox mCheckbox;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    DatabaseReference databaseReferenceOne = FirebaseDatabase.getInstance().getReference("ProjectLeader");
    DatabaseReference databaseReferenceTwo = FirebaseDatabase.getInstance().getReference("ProjectLeader");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curatorverification);

        mTargetSdgOne = findViewById(R.id.tv_sdgTargetOneVerify);
        mTargetSdgTwo = findViewById(R.id.tv_sdgTargetTwoVerify);
        mTargetKpiOne = findViewById(R.id.tv_targetKpiOneVerify);
        mTargetKpiTwo = findViewById(R.id.tv_targetKpiTwoVerify);
        mBaselineKpiOne = findViewById(R.id.tv_baseKpiOneVerify);
        mBaselineKpiTwo = findViewById(R.id.tv_baseKpiTwoVerify);
        mCurrentKpiOne = findViewById(R.id.tv_currentKpiOneVerify);
        mCurrentKpiTwo = findViewById(R.id.tv_currentKpiTwoVerify);
        mImpactScore = findViewById(R.id.tv_impactVerify);
        mBaseAchieve = findViewById(R.id.tv_baseAchieveVerify);

        mCheckbox = findViewById(R.id.cb_verification);
        mCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReferenceOne.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (mCheckbox.isChecked()) {
                            databaseReferenceOne.child("checkbox").setValue(mCheckbox.isChecked());
                        } else {

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(CuratorVerificationActivity.this, "Failed to Verify" + error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

//        databaseReferenceTwo.orderByChild("name").equalTo("Fiz Miz").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String targetOne = snapshot.child("targetOne").getValue(String.class);
//                String targetTwo = snapshot.child("targetTwo").getValue(String.class);
//                String kpiOne = snapshot.child("kpiOne").getValue(String.class);
//                String kpiTwo = snapshot.child("kpiTwo").getValue(String.class);
//                String kpiOneSpin = snapshot.child("kpiOneSpin").getValue(String.class);
//                String kpiTwoSpin = snapshot.child("kpiTwoSpin").getValue(String.class);
//                String currentOne = snapshot.child("currentOne").getValue(String.class);
//                String currentTwo = snapshot.child("currentTwo").getValue(String.class);
//                String impactScore = snapshot.child("impactScore").getValue(String.class);
//                String baselineAchieve = snapshot.child("baselineAchieve").getValue(String.class);
//
//                mTargetSdgOne.setText(targetOne);
//                mTargetSdgTwo.setText(targetTwo);
//                mBaselineKpiOne.setText(kpiOne);
//                mBaselineKpiTwo.setText(kpiTwo);
//                mTargetKpiOne.setText(kpiOneSpin);
//                mTargetKpiTwo.setText(kpiTwoSpin);
//                mCurrentKpiOne.setText(currentOne);
//                mCurrentKpiTwo.setText(currentTwo);
//                mImpactScore.setText(impactScore);
//                mBaseAchieve.setText(baselineAchieve);
            }

//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(CuratorVerificationActivity.this, "Failed to Load Data" + error, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
