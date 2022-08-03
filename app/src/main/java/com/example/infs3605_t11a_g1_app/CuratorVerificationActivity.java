package com.example.infs3605_t11a_g1_app;

import android.os.Bundle;

import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import java.util.Objects;

public class CuratorVerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curatorverification);

        CheckBox mCheckbox = findViewById(R.id.cb_verification);
        mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean check) {
                submitVerify(check);
            }

            final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Curator").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
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