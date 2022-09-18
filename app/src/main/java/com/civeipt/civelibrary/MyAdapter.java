package com.civeipt.civelibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends ArrayAdapter<profileDetails> {
    TextView reg_no, edtProgram, edtEmail, edtname, edtphone;


    Context context;
    List<profileDetails> arrayListprofileDetails;

    public MyAdapter(@NonNull Context context, List<profileDetails> arrayListprofileDetails) {
        super(context, R.layout.fragment_profile, arrayListprofileDetails);

        this.context=context;
        this.arrayListprofileDetails=arrayListprofileDetails;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        @SuppressLint({"ViewHolder", "ResourceType"}) View v = LayoutInflater.from(parent.getContext()).inflate(R.id.fragmentHome, null, true);

        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_profile, null, true);
        reg_no = v.findViewById(R.id.edtregistration);
        edtProgram = v.findViewById(R.id.edtProgram);
        edtEmail = v.findViewById(R.id.edtEmail);
        edtname = v.findViewById(R.id.edtname);
        edtphone = v.findViewById(R.id.edtphone);

        String fname = arrayListprofileDetails.get(position).getFirstname();
        String mname = arrayListprofileDetails.get(position).getSecondname();
        String lname = arrayListprofileDetails.get(position).getLastname();

        reg_no.setText(arrayListprofileDetails.get(position).getReg_no());
        edtProgram.setText(arrayListprofileDetails.get(position).getProgramme());
        //edtname.setText(lname+", "+fname+" "+mname);
        edtname.setText("Mcharo");
        edtEmail.setText(arrayListprofileDetails.get(position).getEmail());
        edtphone.setText(arrayListprofileDetails.get(position).getPhonenumber());

        return v;
    }
}
