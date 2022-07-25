package com.example.infs3605_t11a_g1_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FormActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mBaselineOne, mBaselineTwo, mCurrentOne, mCurrentTwo;
    Button mSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_form);

        mBaselineOne = (EditText)findViewById(R.id.et_baselineOne);
        mBaselineTwo = (EditText)findViewById(R.id.et_baselineTwo);
        mCurrentOne = (EditText)findViewById(R.id.et_currentOne);
        mCurrentTwo = (EditText)findViewById(R.id.et_currentTwo);
        mSubmit = (Button)findViewById(R.id.btn_submit);
        mSubmit.setOnClickListener(this);
    }
    private void addItemToSheet() {
        final ProgressDialog loading = ProgressDialog.show(this,"Updating Project","Please wait");
        final String baselineOne = mBaselineOne.getText().toString().trim();
        final String baselineTwo = mBaselineTwo.getText().toString().trim();
        final String currentOne = mCurrentOne.getText().toString().trim();
        final String currentTwo = mCurrentTwo.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbwk03NgPjghNJY1WzEFg1GQPmJIA3tSW3FxSmz9uduKmSEe1zkpjRX-1U7ZRUR-Fjm4/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        Toast.makeText(FormActivity.this,response,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                //here we pass params
                params.put("action", "updateProject");
                params.put("baselineOne", baselineOne);
                params.put("baselineTwo", baselineTwo);
                params.put("currentOne", currentOne);
                params.put("currentTwo", currentTwo);

                return params;
            }
        };

        int socketTimeOut = 50000;

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);
    }

    protected void launchOverviewActivity() {
        Intent formIntent = new Intent(FormActivity.this, OverviewActivity.class);
        formIntent.putExtra("Baseline One ", mBaselineOne.getText().toString());
        formIntent.putExtra("Baseline Two ", mBaselineTwo.getText().toString());
        formIntent.putExtra("Current One", mCurrentOne.getText().toString());
        formIntent.putExtra("Current Two", mCurrentTwo.getText().toString());
        startActivity(formIntent);
    }

    @Override
    public void onClick(View v) {
        if(v==mSubmit){
            addItemToSheet();
            launchOverviewActivity();
        }
    }




}
