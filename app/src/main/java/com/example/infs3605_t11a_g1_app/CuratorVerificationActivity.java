package com.example.infs3605_t11a_g1_app;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

import java.util.HashMap;
import java.util.Map;

public class CuratorVerificationActivity extends AppCompatActivity {
    private TextView mTargetSdgOne, mTargetSdgTwo, mTargetKpiOne, mTargetKpiTwo,
            mBaselineKpiOne, mBaselineKpiTwo, mCurrentKpiOne, mCurrentKpiTwo,
            mImpactScore, mBaseAchieve, mLink;
    private CheckBox mCheckbox;

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
        mLink = findViewById(R.id.tv_linkVerify);

        mCheckbox = findViewById(R.id.cb_verification);
        mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean check) {
                submitVerify(check);
            }

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Curator").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
            private void submitVerify(boolean check) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Map<String, Object> updates = new HashMap<>();
                        updates.put("verify", check);
                        databaseReference.updateChildren(updates);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(CuratorVerificationActivity.this, "Failed to Load" + error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}