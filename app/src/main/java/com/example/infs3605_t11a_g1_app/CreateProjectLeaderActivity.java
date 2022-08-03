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

import androidx.appcompat.app.AppCompatActivity;

public class CreateProjectLeaderActivity extends AppCompatActivity {
    private EditText mName, mSolutionName, mDesc;
    private Spinner mChallengeSpinner;
    private Button mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprojectleader);

        mName = findViewById(R.id.et_name);
        mSolutionName = findViewById(R.id.et_solution);
        mDesc = findViewById(R.id.et_desc);

        mChallengeSpinner = findViewById(R.id.sp_challenges);
        Spinner challengeDropdown = mChallengeSpinner;
        String[] challenges = new String[]{"The Great Fashion Decarbonisation", "Innovate to Regenerate", "Cities of Tomorrow", "Bushfire Regeneration Challenge"};
        ArrayAdapter<String> challengesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, challenges);
        challengeDropdown.setAdapter(challengesAdapter);

        mNext = findViewById(R.id.bts_next);
        mNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = mName.getText().toString();
                String solutionName = mSolutionName.getText().toString();
                String challenge = mChallengeSpinner.getSelectedItem().toString();
                String desc = mDesc.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(solutionName) || TextUtils.isEmpty(challenge) ||
                        TextUtils.isEmpty(desc)) {
                    Toast.makeText(CreateProjectLeaderActivity.this, "Please enter all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    launchNextPage();
                }
            }

            private void launchNextPage() {
                String name = mName.getText().toString();
                String solutionName = mSolutionName.getText().toString();
                String challenge = mChallengeSpinner.getSelectedItem().toString();
                String desc = mDesc.getText().toString();

                Intent nextIntent = new Intent(CreateProjectLeaderActivity.this, CreateProjectLeaderTwoActivity.class);
                nextIntent.putExtra("Name", name);
                nextIntent.putExtra("SolutionName", solutionName);
                nextIntent.putExtra("Challenge", challenge);
                nextIntent.putExtra("Desc", desc);
                startActivity(nextIntent);
            }
        });
    }
}