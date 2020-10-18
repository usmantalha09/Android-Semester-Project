package com.myfirstapp.mrpc.phc_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.myfirstapp.mrpc.phc_project.PersonModel.ComplainsModel;

public class Patient_Complain extends AppCompatActivity {

    EditText name,group,cnic,phoneno,address,email,complain;
    Button button,back;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__complain);


        name = findViewById(R.id.pat_name);
        group = findViewById(R.id.pat_group);
        cnic = findViewById(R.id.pat_cnic);
        phoneno = findViewById(R.id.pat_phone);
        address = findViewById(R.id.pat_address);
        email = findViewById(R.id.pat_email);
        complain = findViewById(R.id.pat_complain);
        button = findViewById(R.id.pat_complainButton);
        back = findViewById(R.id.pat_complainBackButton);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendData();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Patient_Complain.this,PatientDashboardTabbed.class));
                finish();
            }
        });

    }

    private void SendData() {
        String Name = name.getText().toString().trim();
        String Group = group.getText().toString().trim();
        String CNIC = cnic.getText().toString().trim();
        String Phone = phoneno.getText().toString().trim();
        String Address = address.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String Complain = complain.getText().toString().trim();
        if (TextUtils.isEmpty(Name)) {
            name.setError("Please enter the Patient Name");
        }
        else if(TextUtils.isEmpty(Group)) {
            group.setError("Please enter the Blood Group");
        }
        else if(TextUtils.isEmpty(CNIC)) {
            cnic.setError("Please enter the CNIC");
        }
        else if(TextUtils.isEmpty(Phone)) {
            phoneno.setError("Please enter the Phone");
        }
        else if(TextUtils.isEmpty(Address)) {
            address.setError("Please enter the Address");
        }
        else if(TextUtils.isEmpty(Email)) {
            email.setError("Please enter the Email");
        }
        else if (!(Patterns.EMAIL_ADDRESS.matcher(Email).matches())) {
            email.setError("Please enter the valid email");
        }
        else if(TextUtils.isEmpty(Complain)) {
            complain.setError("Please enter the Complain");
        }
        else {
            ComplainsModel values = new ComplainsModel(Name,Group,CNIC,Phone,Address,Email,Complain);
            FirebaseDatabase.getInstance().getReference().child("Patient_Complains").push().setValue(values);
            Toast.makeText(this, "Complain Submitted", Toast.LENGTH_SHORT).show();
        }

    }


}
