package com.example.infs3605_t11a_g1_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private SolutionAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        SolutionAdapter.RecyclerViewClickListener listener = new SolutionAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, String solutionCode) {
                launchDetailActivity(solutionCode);
            }
        };
        mAdapter = new SolutionAdapter(Solution.getSolutions(), listener);
        mRecyclerView.setAdapter(mAdapter);
    }

    protected void launchDetailActivity(String code) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("Code", code);
        startActivity(intent);
    }
}