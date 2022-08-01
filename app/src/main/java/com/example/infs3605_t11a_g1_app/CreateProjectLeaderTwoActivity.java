package com.example.infs3605_t11a_g1_app;
import android.content.Intent;
import android.net.Uri;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateProjectLeaderTwoActivity extends AppCompatActivity {
    private EditText mBaselineOne, mBaselineTwo;
    private Spinner mKpiSpinnerOne, mKpiSpinnerTwo, mTargetSpinnerOne, mTargetSpinnerTwo;
    private Button mSubmit;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ProjectLeader");
    ProjectLeader projectLeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprojectleadertwo);
        projectLeader = new ProjectLeader();
        mBaselineOne = findViewById(R.id.et_baselinekpiOne);
        mBaselineTwo = findViewById(R.id.et_baselinekpiTwo);
        mKpiSpinnerOne = findViewById(R.id.sp_kpisOne);
        mKpiSpinnerTwo = findViewById(R.id.sp_kpisTwo);
        mTargetSpinnerOne = findViewById(R.id.sp_targetsOne);
        mTargetSpinnerTwo = findViewById(R.id.sp_targetsTwo);

        Spinner kpiDropdownOne = findViewById(R.id.sp_kpisOne);
        String[] kpisOne = new String[]{"10+ flagship species on recovery path", "10+ culturally important species on recovery path", "100,000 ha under restoration",
                "75+ Indigenous organisations/communities supported in their efforts to revitalise cultural fire management",
                "300 land manager adopt nature-based solutions", "150 regional jobs created"};
        ArrayAdapter<String> kpisAdapterOne = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, kpisOne);
        kpiDropdownOne.setAdapter(kpisAdapterOne);

        Spinner kpiDropdownTwo = findViewById(R.id.sp_kpisTwo);
        String[] kpisTwo = new String[]{"10+ flagship species on recovery path", "10+ culturally important species on recovery path", "100,000 ha under restoration",
                "75+ Indigenous organisations/communities supported in their efforts to revitalise cultural fire management",
                "300 land manager adopt nature-based solutions", "150 regional jobs created"};
        ArrayAdapter<String> kpisAdapterTwo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, kpisTwo);
        kpiDropdownTwo.setAdapter(kpisAdapterTwo);
        Spinner targetDropDownOne = findViewById(R.id.sp_targetsOne);
        String[] targetsOne = new String[]{"15.1: Ensure the conservation of ecosystems",
                "15.2: Promote sustainable management",
                "15.3: Combat restore degraded land",
                "15.4: Ensure the conservation biodiversity",
                "15.5: Take action to reduce the degradation of habitats",
                "15.7: End poaching of protected species of flora and fauna",
                "15.8: Prevent impact of invasive alien species",
                "15.9: Integrate biodiversity values into national and local planning"};
        ArrayAdapter<String> targetsAdapterOne = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, targetsOne);
        targetDropDownOne.setAdapter(targetsAdapterOne);
        Spinner targetDropDownTwo = findViewById(R.id.sp_targetsTwo);
        String[] targetsTwo = new String[]{"15.1: Ensure the conservation of ecosystems",
                "15.2: Promote sustainable management",
                "15.3: Combat restore degraded land",
                "15.4: Ensure the conservation biodiversity",
                "15.5: Take action to reduce the degradation of habitats",
                "15.7: End poaching of protected species of flora and fauna",
                "15.8: Prevent impact of invasive alien species",
                "15.9: Integrate biodiversity values into national and local planning"};;
        ArrayAdapter<String> targetsAdapterTwo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, targetsTwo);
        targetDropDownTwo.setAdapter(targetsAdapterTwo);

        mSubmit = findViewById(R.id.btn_submit);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String targetOne = mTargetSpinnerOne.getSelectedItem().toString();
                String kpiOne = mKpiSpinnerOne.getSelectedItem().toString();
                String baselineOne = mBaselineOne.getText().toString();
                String targetTwo = mTargetSpinnerTwo.getSelectedItem().toString();
                String kpiTwo = mKpiSpinnerTwo.getSelectedItem().toString();
                String baselineTwo = mBaselineTwo.getText().toString();
                if (TextUtils.isEmpty(targetOne) || TextUtils.isEmpty(kpiOne) || TextUtils.isEmpty(baselineOne) ||
                        TextUtils.isEmpty(targetTwo) || TextUtils.isEmpty(kpiTwo) || TextUtils.isEmpty(baselineTwo)) {
                    Toast.makeText(CreateProjectLeaderTwoActivity.this, "Please enter all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    addToData(targetOne, kpiOne, baselineOne, targetTwo, kpiTwo, baselineTwo);
                }
            }
            private void addToData(String targetOne, String kpiOne, String baselineOne,
                                   String targetTwo, String kpiTwo, String baselineTwo) {
                Intent nextIntent = getIntent();
                String name = nextIntent.getStringExtra("Name");
                projectLeader.setName(name);
                String solutionName = nextIntent.getStringExtra("SolutionName");
                projectLeader.setSolutionName(solutionName);
                String challenge = nextIntent.getStringExtra("Challenge");
                projectLeader.setChallengeSpin(challenge);
                String desc = nextIntent.getStringExtra("Desc");
                projectLeader.setDesc(desc);
                projectLeader.setTargetOne(targetOne);
                projectLeader.setTargetTwo(targetTwo);
                projectLeader.setKpiOneSpin(kpiOne);
                projectLeader.setKpiOne(baselineOne);
                projectLeader.setKpiTwoSpin(kpiTwo);
                projectLeader.setKpiTwo(baselineTwo);
                projectLeader.setCurrentOne("0");
                projectLeader.setCurrentTwo("0");
                projectLeader.setImpactScore("0");
                projectLeader.setBaselineAchieve("N/A");
                projectLeader.setCheckbox(false);
                projectLeader.setLink(null);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(mAuth.getCurrentUser().getUid()).setValue(projectLeader);
                        startActivity(new Intent(CreateProjectLeaderTwoActivity.this, OverviewActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(CreateProjectLeaderTwoActivity.this, "Failed to submit" + error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}