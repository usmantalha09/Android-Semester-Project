package com.myfirstapp.mrpc.phc_project;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myfirstapp.mrpc.phc_project.Adopters.Profile_Adopter;
import com.myfirstapp.mrpc.phc_project.PersonModel.DoctorModel;

import java.util.ArrayList;


public class Top_Rated extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    ArrayList<DoctorModel> list;
    FirebaseAuth auth;
    Profile_Adopter adaptor;
    TextView valu;
    Button btn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top__rated, container, false);
        auth = FirebaseAuth.getInstance();
        recyclerView = v.findViewById(R.id.TopdoctorRecycler);
        manager = new LinearLayoutManager(getActivity());
        list = new ArrayList<>();
        recyclerView.setLayoutManager(manager);
        valu = v.findViewById(R.id.NoofDoc);
        fetchData();
        btn = v.findViewById(R.id.Topsignout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(auth != null)
                {
                    auth.signOut();
                    startActivity(new Intent(getActivity(),Login.class));
                }

            }
        });
        return v;
    }
    private void fetchData() {
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("d_userTable")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            String Checked_Rating = snapshot.child("d_Rating").getValue().toString();
                            Float value = Float.parseFloat(Checked_Rating);
                            if (value == 5.0){
                                    String db_Name = snapshot.child("d_Name").getValue().toString();
                                    String db_Email = snapshot.child("d_Email").getValue().toString();
                                    String db_Password = snapshot.child("d_Pass").getValue().toString();
                                    String db_Phone = snapshot.child("d_Phone").getValue().toString();
                                    String db_Address = snapshot.child("d_Address").getValue().toString();
                                    String db_Special = snapshot.child("d_Specialization").getValue().toString();
                                    String db_Description = snapshot.child("d_Description").getValue().toString();
                                    String db_Reviews = snapshot.child("d_Review").getValue().toString();
                                    String Rating = snapshot.child("d_Rating").getValue().toString();
                                    String db_Price = snapshot.child("d_Fee").getValue().toString();
                                    String ImageURL = snapshot.child("imageURL").getValue().toString();
                                    list.add(new DoctorModel(db_Name, db_Email,db_Password,db_Phone,db_Address,db_Special,db_Description,db_Reviews,db_Price,Rating,ImageURL));
                                }

                        }

                        String Number = Integer.toString(list.size());
                        valu.setText(Number);

                        adaptor = new Profile_Adopter(getActivity(),list);
                        recyclerView.setAdapter(adaptor);
                        adaptor.setOnItemClickListener(new Profile_Adopter.OnItemClickListner() {
                            @Override
                            public void onMove(int position) {
                                DoctorModel model = list.get(position);
                                String Name_ = model.getD_Name();
                                String Email_ = model.getD_Email();
                                String Password_ = model.getD_Pass();
                                String Phone_ = model.getD_Phone();
                                String Address_ = model.getD_Address();
                                String Specialization_ = model.getD_Specialization();
                                String Description_ = model.getD_Description();
                                String Review_ = model.getD_Review();
                                String Price_ = model.getD_Fee();
                                String Rating_ = model.getD_Rating();
                                String URL_ = model.getImageURL();
                                Intent i = new Intent(getActivity(),ShowDoctorProfile.class);
                                i.putExtra("NAME",Name_);
                                i.putExtra("EMAIL",Email_);
                                i.putExtra("PASSWORD",Password_);
                                i.putExtra("PHONE",Phone_);
                                i.putExtra("ADDRESS",Address_);
                                i.putExtra("SPECIAL",Specialization_);
                                i.putExtra("DESCRIPTION",Description_);
                                i.putExtra("REVIEW",Review_);
                                i.putExtra("PRICE",Price_);
                                i.putExtra("RATING",Rating_);
                                i.putExtra("URL",URL_);
                                startActivity(i);
                            }
                        });
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getActivity(), databaseError.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
}
