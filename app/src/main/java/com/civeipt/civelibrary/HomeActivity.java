package com.civeipt.civelibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    public static String regNo, reg_no, firstname, secondname, lastname, programme, phonenumber, email;


    MyAdapter myAdapter;
    public static ArrayList<profileDetails> profileDetailsArrayList = new ArrayList<>();
    profileDetails profileDetails;

    ImageButton btnhome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnhome = findViewById(R.id.btnhome);
        myAdapter = new MyAdapter(HomeActivity.this, profileDetailsArrayList);

        Intent i = getIntent();
        regNo = i.getStringExtra(reg_no);
        //Toast.makeText(HomeActivity.this, regNo, Toast.LENGTH_SHORT).show();

        getData(regNo);


        Bundle bundle = new Bundle();
        bundle.putString("reg_no", String.valueOf(regNo));
        Fragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.navHostFragment, fragment, null)
                .commit();


        btnhome.setOnClickListener(view -> {
            Bundle bundle1 = new Bundle();
            bundle1.putString("reg_no", String.valueOf(regNo));
            Fragment fragment1 = new HomeFragment();
            fragment1.setArguments(bundle1);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.navHostFragment, fragment1, null)
                    .commit();
        });

    }

    private void getData(String regNo) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.42.6/library/profileDisplay.php", response -> {

            profileDetailsArrayList.clear();
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(response);
                String success = jsonObject.getString("Success");
                JSONArray jsonArray = jsonObject.getJSONArray("data");

                if (success.equals("1")){
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        reg_no = object.getString("reg_no");
                        firstname = object.getString("firstname");
                        secondname = object.getString("secondname");
                        lastname = object.getString("lastname");
                        programme = object.getString("programme");
                        email = object.getString("email");
                        phonenumber = object.getString("phonenumber");

                        profileDetails = new profileDetails(firstname, secondname, lastname, programme, email, phonenumber, reg_no);
                        profileDetailsArrayList.add(profileDetails);
                        myAdapter.notifyDataSetChanged();

                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> Toast.makeText(HomeActivity.this, "No Network Connection", Toast.LENGTH_LONG).show()){

            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params= new HashMap<>();
                params.put("reg_no", String.valueOf(regNo));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(HomeActivity.this);
        requestQueue.add(stringRequest);
    }
}