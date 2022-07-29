package com.example.infs3605_t11a_g1_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class FormActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mBaselineOne, mBaselineTwo, mCurrentOne, mCurrentTwo;
    Button mSubmit;
    ImageView upload;
    Uri imageuri = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_form);

        mCurrentOne = (EditText)findViewById(R.id.et_currentOne);
        mCurrentTwo = (EditText)findViewById(R.id.et_currentTwo);
        mSubmit = (Button)findViewById(R.id.btn_formSubmit);
        mSubmit.setOnClickListener(this);
        upload = findViewById(R.id.uploadpdf);

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
