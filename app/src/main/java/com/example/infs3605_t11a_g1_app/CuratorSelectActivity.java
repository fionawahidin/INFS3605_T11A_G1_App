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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CuratorSelectActivity extends AppCompatActivity {
    EditText mCuratorName, mReason;
    Spinner mCuratorChallenges;
    Button mCuratorSubmit;
    Curator curator;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Curator");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createcurator);

        curator = new Curator();

        mCuratorName = findViewById(R.id.et_curatorName);
        mReason = findViewById(R.id.et_curatorReason);

        mCuratorChallenges = findViewById(R.id.sp_curatorChallenges);
        Spinner curatorDropdown = findViewById(R.id.sp_curatorChallenges);
        String[] curatorChallenges = new String[]{"The Great Fashion Decarbonisation", "Innovate to Regenerate", "Cities of Tomorrow", "Bushfire Regeneration Challenge"};
        ArrayAdapter<String> curatorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, curatorChallenges);
        curatorDropdown.setAdapter(curatorAdapter);

        mCuratorSubmit = findViewById(R.id.btn_curatorSubmit);
        mCuratorSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String curatorName = mCuratorName.getText().toString();
                String curatorChallenge = mCuratorChallenges.getSelectedItem().toString();
                String curatorReason = mReason.getText().toString();

                if (TextUtils.isEmpty(curatorName) || TextUtils.isEmpty(curatorChallenge) || TextUtils.isEmpty(curatorReason)) {
                    Toast.makeText(CuratorSelectActivity.this, "Please enter all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    addCurator(curatorName, curatorChallenge, curatorReason);
                }
            }

//        } else {
//            addToData(targetOne, kpiOne, baselineOne, targetTwo, kpiTwo, baselineTwo);
//        }
//    }

            private void addCurator(String curatorName, String curatorChallenge, String curatorReason) {
                curator.setCuratorName(curatorName);
                curator.setCuratorChallenge(curatorChallenge);
                curator.setReason(curatorReason);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(mAuth.getCurrentUser().getUid()).setValue(curator);
                        startActivity(new Intent(CuratorSelectActivity.this, CuratorVerificationActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(CuratorSelectActivity.this, "Failed to submit" + error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
