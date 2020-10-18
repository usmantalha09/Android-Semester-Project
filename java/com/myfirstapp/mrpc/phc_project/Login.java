package com.myfirstapp.mrpc.phc_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    LinearLayout Lp_login,Ld_login;
    ToggleButton toggle_login;

    //Doctor login
    Button d_login,d_signup;
    EditText d_mail,d_pass;
    ProgressDialog d_progressDialog;
    FirebaseAuth d_auth;
    FirebaseDatabase d_firebaseDatabase;
    //Patient Login
    Button pa_login,pa_signup;
    EditText pa_mail,pa_pass;
    ProgressDialog pa_progressDialog;
    FirebaseAuth pa_auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toggle_login = findViewById(R.id.toggle_login);
        Ld_login = findViewById(R.id.doc_login_layout);
        Lp_login = findViewById(R.id.pat_login_layout);


        //Doctor login
        d_login = findViewById(R.id.d_login_page);
        d_signup = findViewById(R.id.d_signup_page);
        d_mail = findViewById(R.id.doc_mail);
        d_pass = findViewById(R.id.doc_pass);
        d_progressDialog = new ProgressDialog(this);
        d_auth = FirebaseAuth.getInstance();
        d_firebaseDatabase = FirebaseDatabase.getInstance();

        //Patient Login
        pa_login = findViewById(R.id.p_login_page);
        pa_signup = findViewById(R.id.p_signup_page);
        pa_mail = findViewById(R.id.pat_mail);
        pa_pass = findViewById(R.id.pat_pass);
        pa_progressDialog = new ProgressDialog(this);
        pa_auth = FirebaseAuth.getInstance();


        //Doctor Login Onclick Listener
        d_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DoctorLoginCheckValidations();

            }
        });

        d_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,MainActivity.class);
                startActivity(i);
                finish();

            }
        });
        //Patient Login OnClick Listener
        pa_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PatientcheckValidations();

            }
        });

        pa_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,MainActivity.class);
                startActivity(i);
                finish();

            }
        });



    }
    //Universal Toggle Button
    public void TogleChecked(View v )
    {

        if (toggle_login.isChecked())
        {
            Lp_login.setVisibility(View.GONE);
            Ld_login.setVisibility(View.VISIBLE);


        }
        else
        {
            Lp_login.setVisibility(View.VISIBLE);
            Ld_login.setVisibility(View.GONE);

        }


    }

    //Doctor Login
    private void DoctorLoginCheckValidations() {
        String Username = d_mail.getText().toString().trim();
        String Password = d_pass.getText().toString().trim();

        if(TextUtils.isEmpty(Username))
        {
            d_mail.setError("Please Enter valid Value");
        }
        else if(!(Patterns.EMAIL_ADDRESS.matcher(Username).matches()))
        {
            d_mail.setError("Please Enter valid Email");
        }
        else if(TextUtils.isEmpty(Password))
        {
            d_mail.setError("Please Enter valid Password");
        }
        else
        {
            DoctorPerformAuthentication(Username,Password);
        }

    }
    private void DoctorPerformAuthentication(final String username, final String password) {

        d_progressDialog.setMessage("Please Wait...!");
        d_progressDialog.setCancelable(false);
        d_progressDialog.show();

        d_auth.signInWithEmailAndPassword(username,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            d_progressDialog.dismiss();
                            startActivity(new Intent(Login.this,Doc_Login.class));
                            finish();
                        }
                        else
                        {
                            d_progressDialog.dismiss();
                            Toast.makeText(Login.this, "Not Authenticated", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    //Patient Login
    private void PatientcheckValidations() {
        String Username = pa_mail.getText().toString().trim();
        String Password = pa_pass.getText().toString().trim();

        if(TextUtils.isEmpty(Username))
        {
            pa_mail.setError("Please Enter valid Value");
        }
        else if(!(Patterns.EMAIL_ADDRESS.matcher(Username).matches()))
        {
            pa_mail.setError("Please Enter valid Email");
        }
        else if(TextUtils.isEmpty(Password))
        {
            pa_mail.setError("Please Enter valid Password");
        }
        else
        {
            PatientPerformAuthentication(Username,Password);
        }
    }
    private void PatientPerformAuthentication(String username, String password) {

        pa_progressDialog.setMessage("Please Wait...!");
        pa_progressDialog.setCancelable(false);
        pa_progressDialog.show();

        pa_auth.signInWithEmailAndPassword(username,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            pa_progressDialog.dismiss();
                            startActivity(new Intent(Login.this,PatientDashboardTabbed.class));
                            finish();
                        }
                        else
                        {
                            pa_progressDialog.dismiss();
                            Toast.makeText(Login.this, "Not Authenticated", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


}
