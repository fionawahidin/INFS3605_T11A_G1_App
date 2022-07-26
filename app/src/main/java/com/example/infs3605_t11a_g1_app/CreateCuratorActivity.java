package com.example.infs3605_t11a_g1_app;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class CreateCuratorActivity  extends AppCompatActivity {
    private Spinner mChallengeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createcurator);

        Spinner challengeDropdown = findViewById(R.id.sp_challenges);
        String[] challenges = new String[]{"The Great Fashion Decarbonisation", "Innovate to Regenerate", "Cities of Tomorrow", "Bushfire Regeneration Challenge"};
        ArrayAdapter<String> challengesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, challenges);
        challengeDropdown.setAdapter(challengesAdapter);

        Spinner kpiDropdown = findViewById(R.id.sp_kpis);
        String[] kpis = new String[]{""};
        ArrayAdapter<String> kpisAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, kpis);
        kpiDropdown.setAdapter(kpisAdapter);
    }

}
