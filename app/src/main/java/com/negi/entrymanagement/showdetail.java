package com.negi.entrymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class showdetail extends AppCompatActivity {
TextView t1,t2,t3,t4;
FirebaseDatabase firebaseDatabase;
DatabaseReference mref;
List<AddHost> li;


//just for test purpose

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdetail);
    t1=findViewById(R.id.t1);
    t2=findViewById(R.id.t2);
    t3=findViewById(R.id.t3);
    t4=findViewById(R.id.t4);
li=new ArrayList<>();

        firebaseDatabase=FirebaseDatabase.getInstance();
        mref=firebaseDatabase.getReference("HOST");



       // t2.setText(allhost.get(0).getRegno());
       // t1.setText(ad.name);
    }
    @Override
    protected void onStart() {
        super.onStart();
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren() ) {
                    AddHost temp=new AddHost(ds.child("regno").getValue().toString(),
                            ds.child("name").getValue().toString(),
                            ds.child("phone").getValue().toString(),
                            ds.child("email").getValue().toString(),
                            ds.child("designation").getValue().toString()


                    );
                    li.add(temp);
                    // t1.setText(ds.child("regno").getValue().toString());

                }
                t2.setText(li.get(0).getDesignation());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(showdetail.this,""+databaseError,Toast.LENGTH_LONG);
            }
        });

    }
}
