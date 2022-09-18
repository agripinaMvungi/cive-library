package com.civeipt.civelibrary;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private String registrationNo=null;
    TextView reg_no, edtProgram, edtEmail, edtname, edtphone;
    ImageButton btnback;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        reg_no = v.findViewById(R.id.edtregistration);
        edtProgram = v.findViewById(R.id.edtProgram);
        edtEmail = v.findViewById(R.id.edtEmail);
        edtname = v.findViewById(R.id.edtname);
        edtphone = v.findViewById(R.id.edtphone);
        btnback = v.findViewById(R.id.btnimage);

        assert this.getArguments() != null;
        registrationNo = this.getArguments().getString("reg_no");
        reg_no.setText(registrationNo);

        btnback.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), HomeActivity.class);
            i.putExtra(HomeActivity.reg_no, String.valueOf(registrationNo));
            startActivity(i);
        });

        return v;
    }
}