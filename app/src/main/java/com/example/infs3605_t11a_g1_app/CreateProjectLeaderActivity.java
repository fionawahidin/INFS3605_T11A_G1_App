package com.example.infs3605_t11a_g1_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateProjectLeaderActivity extends AppCompatActivity {
    private EditText mName, mSolutionName, mDesc, mBaselineOne, mBaselineTwo;
    private Spinner mChallengeSpinner, mKpiSpinnerOne, mKpiSpinnerTwo;
    private Button mSubmit;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ProjectLeader projectLeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createcurator);

//        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("ProjectLeader");
        projectLeader = new ProjectLeader();

        mName = findViewById(R.id.et_name);
        mSolutionName = findViewById(R.id.et_solution);
        mDesc = findViewById(R.id.et_desc);
        mBaselineOne = findViewById(R.id.et_baselinekpiOne);
        mBaselineTwo = findViewById(R.id.et_baselinekpiTwo);
        mChallengeSpinner = findViewById(R.id.sp_challenges);
        mKpiSpinnerOne = findViewById(R.id.sp_kpisOne);
        mKpiSpinnerTwo = findViewById(R.id.sp_kpisTwo);

        Spinner challengeDropdown = findViewById(R.id.sp_challenges);
        String[] challenges = new String[]{"The Great Fashion Decarbonisation", "Innovate to Regenerate", "Cities of Tomorrow", "Bushfire Regeneration Challenge"};
        ArrayAdapter<String> challengesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, challenges);
        challengeDropdown.setAdapter(challengesAdapter);

        Spinner kpiDropdownOne = findViewById(R.id.sp_kpisOne);
        String[] kpisOne = new String[]{"10+ flagship species on recovery path", "10+ culturally important species on recovery path", "100,000 ha under restoration",
                "1.4 million ha under enhanced legal protection", "Increase by 50% rescued animals released into wild", "75+ Indigenous organisations/communities supported in their efforts to revitalise cultural fire management",
                "300 land manager adopt nature-based solutions", "150 regional jobs created"};
        ArrayAdapter<String> kpisAdapterOne = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, kpisOne);
        kpiDropdownOne.setAdapter(kpisAdapterOne);

        Spinner kpiDropdownTwo = findViewById(R.id.sp_kpisTwo);
        String[] kpisTwo = new String[]{"10+ flagship species on recovery path", "10+ culturally important species on recovery path", "100,000 ha under restoration",
                "1.4 million ha under enhanced legal protection", "Increase by 50% rescued animals released into wild", "75+ Indigenous organisations/communities supported in their efforts to revitalise cultural fire management",
                "300 land manager adopt nature-based solutions", "150 regional jobs created"};
        ArrayAdapter<String> kpisAdapterTwo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, kpisTwo);
        kpiDropdownTwo.setAdapter(kpisAdapterTwo);

//        DatabaseReference databaseReference = mDatabase.getReference("CURATORS");
        mSubmit = findViewById(R.id.btn_createSubmit);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                databaseReference.child(mName.getText().toString()).setValue(databaseReference);
//                databaseReference.child(mSolutionName.getText().toString()).setValue(databaseReference);
//                databaseReference.child(mChallengeSpinner.getSelectedItem().toString()).setValue(databaseReference);
//                databaseReference.child(mDesc.getText().toString()).setValue(databaseReference);
//                databaseReference.child(mKpiSpinnerOne.getSelectedItem().toString()).setValue(databaseReference);
//                databaseReference.child(mBaselineOne.getText().toString()).setValue(databaseReference);
//                databaseReference.child(mKpiSpinnerTwo.getSelectedItem().toString()).setValue(databaseReference);
//                databaseReference.child(mBaselineTwo.getText().toString()).setValue(databaseReference);

                String name = mName.getText().toString();
                String solutionName = mSolutionName.getText().toString();
                String challenge = mChallengeSpinner.getSelectedItem().toString();
                String desc = mDesc.getText().toString();
                String kpiOne = mKpiSpinnerOne.getSelectedItem().toString();
                String baselineOne = mBaselineOne.getText().toString();
                String kpiTwo = mKpiSpinnerTwo.getSelectedItem().toString();
                String baselineTwo = mBaselineTwo.getText().toString();
//                launchOverviewActivity();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(solutionName) || TextUtils.isEmpty(challenge) ||
                        TextUtils.isEmpty(desc) || TextUtils.isEmpty(kpiOne) || TextUtils.isEmpty(baselineOne) ||
                        TextUtils.isEmpty(kpiTwo) || TextUtils.isEmpty(baselineTwo)) {
                    Toast.makeText(CreateProjectLeaderActivity.this, "Please enter all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    addData(name, solutionName, challenge, desc, kpiOne, baselineOne, kpiTwo, baselineTwo);
                }

//            protected void launchOverviewActivity() {
//                Intent intent = new Intent(CreateProjectLeaderActivity.this, OverviewActivity.class);
//                startActivity(intent);
//            }
//        });
            }

            private void addData(String name, String solutionName, String challenge, String desc, String kpiOne, String baselineOne, String kpiTwo, String baselineTwo) {
                projectLeader.setName(name);
                projectLeader.setSolutionName(solutionName);
                projectLeader.setChallengeSpin(challenge);
                projectLeader.setDesc(desc);
                projectLeader.setKpiOneSpin(kpiOne);
                projectLeader.setKpiOne(baselineOne);
                projectLeader.setKpiTwoSpin(kpiTwo);
                projectLeader.setKpiTwo(baselineTwo);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.setValue(projectLeader);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(CreateProjectLeaderActivity.this, "Failed to submit" + error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}