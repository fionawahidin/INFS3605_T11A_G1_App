package com.example.infs3605_t11a_g1_app;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProjectLeaderProfileActivity extends AppCompatActivity {
    TextView mName, mDesc, mSolutionName, mCurrentLevel, mNextLevel, mCompleteLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projectleaderprofile);

        mName = findViewById(R.id.tv_namePL);
        mDesc = findViewById(R.id.tv_descProfile);
        mSolutionName = findViewById(R.id.tv_solutionNameProfile);
        mCurrentLevel = findViewById(R.id.tv_levelPL);
        mCompleteLevel = findViewById(R.id.tv_completeLevel);
        mNextLevel = findViewById(R.id.tv_nextLevel);

        DatabaseReference userDatabase = FirebaseDatabase.getInstance().getReference("ProjectLeader").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        userDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String impact = String.valueOf((dataSnapshot.child("impactScore").getValue()));
                String name = dataSnapshot.child("name").getValue(String.class);
                String desc = dataSnapshot.child("desc").getValue(String.class);
                String solutionName = dataSnapshot.child("solutionName").getValue(String.class);
                Double impactCal = Double.parseDouble(impact);

                int currentLevel = 0;
                String nextLevel = null;

                if (impactCal < 10.00) {
                    currentLevel = 0;
                    nextLevel = String.valueOf(10.00 - impactCal);
                } else if (impactCal >= 10.00 && impactCal < 20.00) {
                    currentLevel = 1;
                    nextLevel = String.valueOf(20.00 - impactCal);
                } else if (impactCal >= 20.00 && impactCal < 30.00) {
                    currentLevel = 2;
                    nextLevel = String.valueOf(30.00 - impactCal);
                } else if (impactCal >= 30.00 && impactCal < 40.00) {
                    currentLevel = 3;
                    nextLevel = String.valueOf(40.00 - impactCal);
                } else if (impactCal >= 40.00 && impactCal < 50.00) {
                    currentLevel = 4;
                    nextLevel = String.valueOf(50.00 - impactCal);
                } else if (impactCal >= 50.00 && impactCal < 60.00) {
                    currentLevel = 5;
                    nextLevel = String.valueOf(60.00 - impactCal);
                } else if (impactCal >= 60.00 && impactCal < 70.00) {
                    currentLevel = 6;
                    nextLevel = String.valueOf(70.00 - impactCal);
                } else if (impactCal >= 70.00 && impactCal < 80.00) {
                    currentLevel = 7;
                    nextLevel = String.valueOf(80.00 - impactCal);
                } else if (impactCal >= 80.00 && impactCal < 90.00) {
                    currentLevel = 8;
                    nextLevel = String.valueOf(90.00 - impactCal);
                } else if (impactCal >= 90.00 && impactCal < 100.00) {
                    currentLevel = 9;
                    nextLevel = String.valueOf(100.00 - impactCal);
                } else if (impactCal == 100.00) {
                    currentLevel = 10;
                }

                int upLevel = currentLevel + 1;

                mName.setText(name);
                mSolutionName.setText(solutionName);
                mDesc.setText(desc);
                mCurrentLevel.setText("Level " + Integer.toString(currentLevel));
                mNextLevel.setText(nextLevel + "% before Level " + Integer.toString(upLevel));
                mCompleteLevel.setText(Double.toString(impactCal) + "% Completed");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }
}