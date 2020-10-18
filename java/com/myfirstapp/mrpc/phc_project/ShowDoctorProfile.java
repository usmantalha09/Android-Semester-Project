package com.myfirstapp.mrpc.phc_project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ShowDoctorProfile extends AppCompatActivity {
    TextView name,spec,f,rev;
    Button btn;
    ImageView img,Simg1,Simg2;
    Context ctx;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_doctor_profile);
        name = findViewById(R.id.DocName);
        spec = findViewById(R.id.DocSpecial);
        f = findViewById(R.id.DocFee);
        rev = findViewById(R.id.DocReview);
        img = findViewById(R.id.DocImage);
        Simg1 = findViewById(R.id.img1);
        Simg2 = findViewById(R.id.img2);
        ratingBar = findViewById(R.id.rat);
        btn = findViewById(R.id.compButton);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            String DoctorName = bundle.getString("NAME");
            String DoctorSpecial = bundle.getString("SPECIAL");
            String DoctorFee = bundle.getString("PRICE");
            String DoctorReviews = bundle.getString("REVIEW");
            String DoctorImage = bundle.getString("URL");
            String imageUrl1 = "https://clipartstation.com/wp-content/uploads/2017/11/headache-clipart-2.jpg";
            String imageUrl2 = "https://i.dlpng.com/static/png/4161615-huntsville-achievement-school-tips-for-cold-and-flu-season-cold-flu-png-2480_2480_preview.webp";
            String Rating = bundle.getString("RATING");
            Picasso.with(ctx).load(DoctorImage).fit().into(img);
            Picasso.with(ctx).load(imageUrl1).fit().into(Simg1);
            Picasso.with(ctx).load(imageUrl2).fit().into(Simg2);
            name.setText(DoctorName);
            spec.setText(DoctorSpecial);
            f.setText(DoctorFee);
            rev.setText(DoctorReviews);
            ratingBar.setRating(Float.parseFloat(Rating));
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowDoctorProfile.this,Patient_Complain.class);
                startActivity(i);
                finish();

            }
        });
    }
}
