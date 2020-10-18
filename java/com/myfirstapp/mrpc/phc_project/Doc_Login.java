package com.myfirstapp.mrpc.phc_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Doc_Login extends AppCompatActivity {
    Button comp_btn,logout_btn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc__login);
        auth = FirebaseAuth.getInstance();

        comp_btn = findViewById(R.id.complains);
        logout_btn = findViewById(R.id.logout);

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(auth != null){
                    auth.signOut();
                    Intent i = new Intent(Doc_Login.this,Login.class);
                    startActivity(i);
                    finishAffinity();
                    finish();
                }

            }
        });

        comp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Doc_Login.this,RealAllComplains.class);
                startActivity(i);
                finish();
            }
        });

    }
}
