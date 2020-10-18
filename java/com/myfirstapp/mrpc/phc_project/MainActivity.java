package com.myfirstapp.mrpc.phc_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.myfirstapp.mrpc.phc_project.PersonModel.DoctorModel;
import com.myfirstapp.mrpc.phc_project.PersonModel.PatientModel;

public class MainActivity extends AppCompatActivity {

    LinearLayout Lp,Ld;
    ToggleButton toggle;

    //Doctor Signup
    EditText d_nam, d_mal, d_pass, d_no, d_addrs, d_specil,d_revws,d_fee,d_dscription;
    Button d_sign_up, d_img_btn, d_log_in;
    ProgressDialog d_progressDialog;
    RatingBar d_ratingBar;
    Uri d_fileData = null;
    FirebaseAuth d_auth;

    //Patient Signup
    EditText pa_nam, pa_mal, pa_pass, pa_no, pa_addrs;
    Button pa_sign_up, pa_img_btn, pa_log_in;
    ProgressDialog pa_progressDialog;
    Uri pa_fileData = null;
    FirebaseAuth pa_auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggle = findViewById(R.id.toggle);
        Ld = findViewById(R.id.doc_layout);
        Lp = findViewById(R.id.pat_layout);
        //Doctor Signup
        d_nam = findViewById(R.id.d_name);
        d_mal = findViewById(R.id.d_email);
        d_pass = findViewById(R.id.d_pass);
        d_no = findViewById(R.id.d_phone);
        d_addrs = findViewById(R.id.d_address);
        d_specil = findViewById(R.id.d_special);
        d_revws = findViewById(R.id.d_review);
        d_dscription = findViewById(R.id.d_descrip);
        d_fee = findViewById(R.id.d_fee);
        d_ratingBar = (RatingBar)findViewById(R.id.d_rating);
        d_img_btn = findViewById(R.id.d_image_butn);
        d_sign_up = findViewById(R.id.d_signup_butn);
        d_log_in = findViewById(R.id.d_login_butn);
        d_progressDialog = new ProgressDialog(this);
        d_auth = FirebaseAuth.getInstance();


        //Patient Signup
        pa_nam = findViewById(R.id.p_name);
        pa_mal = findViewById(R.id.p_email);
        pa_pass = findViewById(R.id.p_pass);
        pa_no = findViewById(R.id.p_phone);
        pa_addrs = findViewById(R.id.p_address);
        pa_img_btn = findViewById(R.id.p_image_butn);
        pa_sign_up = findViewById(R.id.p_signup_butn);
        pa_log_in = findViewById(R.id.p_login_butn);
        pa_progressDialog = new ProgressDialog(this);
        pa_auth = FirebaseAuth.getInstance();


        //Doctor OnClick Listeners
        d_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoctorCheckAllValidation();
            }
        });

        d_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoctorChoose_Image();
            }
        });

        d_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
                finish();
            }
        });

        //Patient OnClick Listeners
        pa_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PatientCheckAllValidation();
            }
        });
        pa_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PatientChoose_Image();
            }
        });
        pa_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
                finish();
            }
        });


    }


    //Universal Toggle
    public void TogleChecked(View v )
    {
        if (toggle.isChecked())
        {
            Lp.setVisibility(View.GONE);
            Ld.setVisibility(View.VISIBLE);
        }
        else
        {
            Lp.setVisibility(View.VISIBLE);
            Ld.setVisibility(View.GONE);
        }
    }

    //universal Data transfer From One Activity to Other
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==01 &&  resultCode==RESULT_OK && toggle.isChecked())
        {
            d_fileData=data.getData();

        }
        else if(requestCode==01 && resultCode==RESULT_OK)
        {
            pa_fileData =data.getData();
        }
    }

    //Doctor Signup
    private void DoctorChoose_Image() {

        Intent ImagePick=new Intent();
        ImagePick.setType("image/*");
        ImagePick.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(ImagePick,01);
    }
    private void DoctorCheckAllValidation() {
        String d_userName = d_nam.getText().toString().trim();
        String d_userEmail = d_mal.getText().toString().trim();
        String d_userPass = d_pass.getText().toString().trim();
        String d_userPhone = d_no.getText().toString().trim();
        String d_userAddress = d_addrs.getText().toString().trim();
        String d_userSpecialization = d_specil.getText().toString().trim();
        String d_userDescription = d_dscription.getText().toString().trim();
        String d_userReview =d_revws.getText().toString().trim();
        String d_userFee = d_fee.getText().toString().trim();
        String d_userRating = String.valueOf(d_ratingBar.getRating());


        if (TextUtils.isEmpty(d_userName)) {
            d_nam.setError("Please enter the username");
        }
        else if (TextUtils.isEmpty(d_userEmail)) {
            d_mal.setError("Please enter the email");
        }
        else if (!(Patterns.EMAIL_ADDRESS.matcher(d_userEmail).matches())) {
            d_mal.setError("Please enter the valid email");
        }
        else if (TextUtils.isEmpty(d_userPass)) {
            d_pass.setError("Please enter the password");
        }
        else if (TextUtils.isEmpty(d_userPhone)) {
            d_no.setError("Please enter the password");
        }
        else if (TextUtils.isEmpty(d_userAddress)) {
            d_addrs.setError("Please enter the password");
        }
        else if (TextUtils.isEmpty(d_userSpecialization)) {
            d_specil.setError("Please enter the password");
        }
        else if (TextUtils.isEmpty(d_userDescription)) {
            d_dscription.setError("Please enter the password");
        }
        else if (TextUtils.isEmpty(d_userReview)) {
            d_revws.setError("Please enter the password");
        }
        else if (TextUtils.isEmpty(d_userFee)) {
            d_fee.setError("Please enter the password");
        }
        else if (d_fileData == null) {
            Toast.makeText(this, "Choose the Image", Toast.LENGTH_SHORT).show();
        }
        else {

            Toast.makeText(this, "Working", Toast.LENGTH_SHORT).show();


           DoctorInsertInDatabase(d_userName, d_userEmail, d_userPass, d_userPhone, d_userAddress,d_userSpecialization,d_userDescription,d_userReview,d_userFee,d_userRating, d_fileData);
        }

    }
    private void DoctorInsertInDatabase(final String d_userName, final String d_userEmail, final String d_userPass, final String d_userPhone, final String d_userAddress, final String d_userSpecialization, final String d_userDescription, final String d_userReview, final String d_userFee, final String d_userRating, final Uri d_fileData) {
        d_progressDialog.setMessage("Please Wait");
        d_progressDialog.setCancelable(false);
        d_progressDialog.show();

        d_auth.createUserWithEmailAndPassword(d_userEmail,d_userPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        Toast.makeText(MainActivity.this, "Checking if", Toast.LENGTH_SHORT).show();
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "Before Function", Toast.LENGTH_SHORT).show();
                            DoctorSendImageInStorage(d_userName,d_userEmail,d_userPass,d_userPhone,d_userAddress,d_userSpecialization,d_userDescription,d_userReview,d_userFee,d_userRating,d_fileData);
                        }
                        else
                        {
                            d_progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Authentication not complete", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    private void DoctorSendImageInStorage(final String d_userName, final String d_userEmail, final String d_userPass, final String d_userPhone, final String d_userAddress, final String d_userSpecialization, final String d_userDescription, final String d_userReview, final String d_userFee, final String d_userRating, final Uri d_fileData) {


        final StorageReference ref = FirebaseStorage.getInstance().getReference("d_UserImages/" + pa_auth.getCurrentUser().getUid());
        ref.putFile(d_fileData).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                return ref.getDownloadUrl();
            }


        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful())
                {
                    Uri DownloadURL=task.getResult();
                    DoctorInsertInRealTimeDatabase(d_userName,d_userEmail,d_userPass,d_userPhone,d_userAddress,d_userSpecialization,d_userDescription,d_userReview,d_userFee,d_userRating,DownloadURL);
                    Toast.makeText(MainActivity.this, "Inserted in Realtime Database", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    d_progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Url not Generated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void DoctorInsertInRealTimeDatabase( String d_userName, String d_userEmail, String d_userPass, String d_userPhone, String d_userAddress, String d_userSpecialization, String d_userDescription, String d_userReview, String d_userFee, String d_userRating, Uri downloadURL) {

        DoctorModel values = new DoctorModel(d_userName,d_userEmail,d_userPass,d_userPhone,d_userAddress,d_userSpecialization,d_userDescription,d_userReview,d_userFee,d_userRating,downloadURL.toString());
        FirebaseDatabase.getInstance().getReference("d_userTable").child(d_auth.getCurrentUser().getUid()).setValue(values)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            d_progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,Login.class));
                            finish();
                        }
                        else
                        {
                            d_progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "User Not Created", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //Patient Signup
    private void PatientChoose_Image() {

        Intent ImagePick=new Intent();
        ImagePick.setType("image/*");
        ImagePick.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(ImagePick,01);
    }
    private void PatientCheckAllValidation() {
        String pa_userName = pa_nam.getText().toString().trim();
        String pa_userEmail = pa_mal.getText().toString().trim();
        String pa_userPass = pa_pass.getText().toString().trim();
        String pa_userPhone = pa_no.getText().toString().trim();
        String pa_userAddress = pa_addrs.getText().toString().trim();


        if (TextUtils.isEmpty(pa_userName)) {
            pa_nam.setError("Please enter the username");
        }
        else if (TextUtils.isEmpty(pa_userEmail)) {
            pa_mal.setError("Please enter the email");
        }
        else if (!(Patterns.EMAIL_ADDRESS.matcher(pa_userEmail).matches())) {
            pa_mal.setError("Please enter the valid email");
        }
        else if (TextUtils.isEmpty(pa_userPass)) {
            pa_pass.setError("Please enter the password");
        }
        else if (TextUtils.isEmpty(pa_userAddress)) {
            pa_addrs.setError("Please enter the password");
        }
        else if (pa_fileData == null) {
            Toast.makeText(this, "Choose the Image", Toast.LENGTH_SHORT).show();
        }
        else {


            InsertInDatabase(pa_userName, pa_userEmail, pa_userPass, pa_userPhone, pa_userAddress, pa_fileData);
        }

    }
    private void InsertInDatabase(final String pa_userName,final String pa_userEmail,final String pa_userPass,final String pa_userPhone,final String pa_userAddress,final Uri fileData) {
        pa_progressDialog.setMessage("Please Wait");
        pa_progressDialog.setCancelable(false);
        pa_progressDialog.show();

        pa_auth.createUserWithEmailAndPassword(pa_userEmail,pa_userPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        Toast.makeText(MainActivity.this, "Checking if", Toast.LENGTH_SHORT).show();
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "Before Function", Toast.LENGTH_SHORT).show();
                            SendImageInStorage(pa_userName,pa_userEmail,pa_userPass,pa_userPhone,pa_userAddress,fileData);
                        }
                        else
                        {
                            pa_progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Authentication not complete", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    private void SendImageInStorage(final String pa_userName,final String pa_userEmail,final String pa_userPass,final String pa_userPhone,final String pa_userAddress,final Uri fileData) {


        final StorageReference ref = FirebaseStorage.getInstance().getReference("p_UserImages/" + pa_auth.getCurrentUser().getUid());
        ref.putFile(fileData).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                return ref.getDownloadUrl();
            }


        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful())
                {
                    Uri DownloadURL=task.getResult();
                    InsertInRealTimeDatabase(pa_userName,pa_userEmail,pa_userPass,pa_userPhone,pa_userAddress,DownloadURL);
                    Toast.makeText(MainActivity.this, "Inserted in Realtime Database", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    pa_progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Url not Generated", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void InsertInRealTimeDatabase(String pa_userName, String pa_userEmail, String pa_userPass, String pa_userPhone, String pa_userAddress, Uri downloadURL) {

        PatientModel values = new PatientModel(pa_userName,pa_userEmail,pa_userPass,pa_userPhone,pa_userAddress,downloadURL.toString());
        FirebaseDatabase.getInstance().getReference("p_userTable").child(pa_auth.getCurrentUser().getUid()).setValue(values)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            pa_progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,MainActivity.class));
                            finish();
                        }
                        else
                        {
                            pa_progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "User Not Created", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


}
