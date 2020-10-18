package com.myfirstapp.mrpc.phc_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Doctorresponse extends AppCompatActivity {
    EditText respond;
    Button btn,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorresponse);

        respond = findViewById(R.id.response);
        btn = findViewById(R.id.response_btn);
        back = findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Doctorresponse.this,RealAllComplains.class));
                finish();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference().child("Response");
                String message = respond.getText().toString().trim();
                reference.setValue(message);
                Toast.makeText(Doctorresponse.this, "Response Submitted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
