package com.example.infs3605_t11a_g1_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private TextView mDetailTitle, mDetailChallenge, mDetailRating, mDetailDesc;
    private ImageView mImage;
    private Button mBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Solution solution = Solution.getSolution(intent.getStringExtra("Code"));

        mDetailTitle = findViewById(R.id.tv_detail_title);
        mDetailChallenge = findViewById(R.id.tv_detail_challenge);
        mDetailRating = findViewById(R.id.tv_detail_rating);
        mImage = findViewById(R.id.iv_detail_image);
        mDetailDesc = findViewById(R.id.tv_detail_desc);

        loadSolutionData(solution);

        mBackButton = findViewById(R.id.btn_back);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void loadSolutionData(Solution solution) {
        mDetailTitle.setText(solution.getTitle());
        mDetailChallenge.setText(solution.getChallenge());
        mDetailRating.setText(solution.getRating());
        mImage.setImageResource(solution.getImage());
        mDetailDesc.setText(solution.getDesc());
    }
}
