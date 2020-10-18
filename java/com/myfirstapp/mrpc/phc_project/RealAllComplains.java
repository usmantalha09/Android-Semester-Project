package com.myfirstapp.mrpc.phc_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myfirstapp.mrpc.phc_project.Adopters.Complains_Adopter;
import com.myfirstapp.mrpc.phc_project.PersonModel.ComplainsModel;

import java.util.ArrayList;

public class RealAllComplains extends AppCompatActivity {

    Complains_Adopter adaptor;
    ArrayList<ComplainsModel> list;
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_all_complains);

        recyclerView = findViewById(R.id.ComplainsmyRecycler);
        manager = new LinearLayoutManager(this);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(manager);
        fetchData();
        btn = findViewById(R.id.Complainback);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RealAllComplains.this,Doc_Login.class));
                finish();
            }
        });
    }

    private void fetchData() {

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Patient_Complains").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String db_Name = snapshot.child("p_name").getValue().toString();
                    String db_Email = snapshot.child("p_email").getValue().toString();
                    String db_Group = snapshot.child("p_group").getValue().toString();
                    String db_Phone = snapshot.child("p_phoneno").getValue().toString();
                    String db_Address = snapshot.child("p_address").getValue().toString();
                    String db_CNIC = snapshot.child("p_cnic").getValue().toString();
                    String db_Complain = snapshot.child("p_complain").getValue().toString();
                    //Toast.makeText(RealAllComplains.this, db_Name + "\n" + db_Email + "\n" + db_Group + "\n" + db_Phone + "\n" + db_Address + "\n" + db_CNIC + "\n" + db_Complain + "\n", Toast.LENGTH_SHORT).show();

                    list.add(new ComplainsModel(db_Name, db_Group, db_CNIC, db_Phone, db_Address, db_Email, db_Complain));
                }
                //Toast.makeText(RealAllComplains.this, Integer.toString(list.size()), Toast.LENGTH_SHORT).show();
                adaptor = new Complains_Adopter(RealAllComplains.this, list);
                recyclerView.setAdapter(adaptor);
                adaptor.setOnItemClickListener(new Complains_Adopter.OnItemClickListner() {
                    @Override
                    public void Response(int position) {
                        Intent i = new Intent(RealAllComplains.this, Doctorresponse.class);
                        //Toast.makeText(RealAllComplains.this, "Clicked/n" + Integer.toString(position), Toast.LENGTH_SHORT).show();
                        startActivity(i);
                        finish();
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(RealAllComplains.this, databaseError.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}