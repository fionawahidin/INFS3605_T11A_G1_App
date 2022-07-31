package com.example.infs3605_t11a_g1_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class FormActivity extends AppCompatActivity {
    TextView mTargetSdgOne, mTargetSdgTwo, mTargetKpiOne, mTargetKpiTwo, mBaselineKpiOne, mBaselineKpiTwo;
    EditText mCurrentKpiOne, mCurrentKpiTwo;
    Button mSubmit;
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private final DatabaseReference databaseReferenceOne = FirebaseDatabase.getInstance().getReference("ProjectLeader");
    private final DatabaseReference databaseReferenceTwo = FirebaseDatabase.getInstance().getReference("ProjectLeader");

    ImageView upload;
    Uri imageuri;

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
        upload = findViewById(R.id.uploadpdf);

        mSubmit = (Button) findViewById(R.id.btn_formSubmit);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentOne = mCurrentKpiOne.getText().toString();
                String currentTwo = mCurrentKpiTwo.getText().toString();

                if (TextUtils.isEmpty(currentOne) || TextUtils.isEmpty(currentTwo)) {
                    Toast.makeText(FormActivity.this, "Please enter all fields and upload evidence.", Toast.LENGTH_SHORT).show();
                } else {
                    submitUpdate(currentOne, currentTwo);
                }
            }

            private void submitUpdate(String currentOne, String currentTwo) {
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

                        float impactScoreFloat = (float) ((((Float.valueOf(currentOne) / kpiOne) * 0.5) + ((Float.valueOf(currentTwo) / kpiTwo) * 0.5)) * 100);
                        double impactScoreDouble = Double.valueOf(String.valueOf(impactScoreFloat));
                        String impactScore = Double.toString(impactScoreDouble);

                        String baseOne = mBaselineKpiOne.getText().toString();
                        String baseTwo = mBaselineKpiTwo.getText().toString();

                        float baseComparisonOne = (Float.valueOf(currentOne) / Float.valueOf(baseOne));
                        float baseComparisonTwo = (Float.valueOf(currentOne) / Float.valueOf(baseTwo));

                        String baselineAchieve = null;
                        if (baseComparisonOne == 1 && baseComparisonTwo == 1) {
                            baselineAchieve = "Baseline Achieved";
                        } else {
                            baselineAchieve = "Baseline Not Reached";
                        }

                        Map<String, Object> updates = new HashMap<>();
                        updates.put("currentOne", currentOne);
                        updates.put("currentTwo", currentTwo);
                        updates.put("impactScore", impactScore);
                        updates.put("baselineAchieve", baselineAchieve);
                        databaseReferenceOne.child(mAuth.getCurrentUser().getUid()).updateChildren(updates);
                        startActivity(new Intent(FormActivity.this, OverviewActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(FormActivity.this, "Failed to submit" + error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        databaseReferenceTwo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String targetOne = ds.child("targetOne").getValue(String.class);
                    String targetTwo = ds.child("targetTwo").getValue(String.class);
                    String kpiOne = ds.child("kpiOne").getValue(String.class);
                    String kpiTwo = ds.child("kpiTwo").getValue(String.class);
                    String kpiOneSpin = ds.child("kpiOneSpin").getValue(String.class);
                    String kpiTwoSpin = ds.child("kpiTwoSpin").getValue(String.class);

                    mTargetSdgOne.setText(targetOne);
                    mTargetSdgOne.setText(targetTwo);
                    mBaselineKpiOne.setText(kpiOne);
                    mBaselineKpiTwo.setText(kpiTwo);
                    mTargetKpiOne.setText(kpiOneSpin);
                    mTargetKpiTwo.setText(kpiTwoSpin);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FormActivity.this, "Failed to Load" + error, Toast.LENGTH_SHORT).show();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

                galleryIntent.setType("application/pdf");
                startActivityForResult(galleryIntent, 1);
            }
        });
    }

    ProgressDialog dialog;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            dialog = new ProgressDialog(this);
            dialog.setMessage("Uploading");
            dialog.show();
            imageuri = data.getData();
            final String timestamp = "" + System.currentTimeMillis();
            StorageReference storageReference = FirebaseStorage.getInstance().getReference();
            final String messagePushID = timestamp;
            Toast.makeText(FormActivity.this, imageuri.toString(), Toast.LENGTH_SHORT).show();

            final StorageReference filepath = storageReference.child(messagePushID + "." + "pdf");
            Toast.makeText(FormActivity.this, filepath.getName(), Toast.LENGTH_SHORT).show();
            filepath.putFile(imageuri).continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return filepath.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        dialog.dismiss();
                        Uri uri = task.getResult();
                        String myurl;
                        myurl = uri.toString();
                        Toast.makeText(FormActivity.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        dialog.dismiss();
                        Toast.makeText(FormActivity.this, "Upload Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}