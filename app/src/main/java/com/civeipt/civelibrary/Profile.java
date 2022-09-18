package com.civeipt.civelibrary;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Profile extends AppCompatActivity{
    View txtreg_no, edtphone;
    ImageButton btnback;

    private String regNo=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        txtreg_no = findViewById(R.id.edtregistration);
        edtphone = findViewById(R.id.edtphone);
        btnback = findViewById(R.id.btnimage);

        Intent i = new Intent();
        regNo = i.getStringExtra("_regNo");
        /*Toast.makeText(Profile.this, regNo, Toast.LENGTH_SHORT).show();*/

        btnback.setOnClickListener(view -> {
            Intent i1 = new Intent(Profile.this, HomeActivity.class);
            i1.putExtra(HomeActivity.reg_no, String.valueOf(regNo));
            startActivity(i1);
        });

    }
}