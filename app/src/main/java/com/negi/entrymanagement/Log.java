package com.negi.entrymanagement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


public class Log extends Fragment {
   RecyclerView recyclerView;
   VisitorAdapter2 adapter;
   List<AddVisitor> li;
   FirebaseDatabase firebaseDatabase;
   DatabaseReference mref;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_log, container, false);
      recyclerView=v.findViewById(R.id.reco);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
      li=new ArrayList<>();
      firebaseDatabase=FirebaseDatabase.getInstance();
      mref=firebaseDatabase.getReference("VISITOR");
      mref.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              for(DataSnapshot ds: dataSnapshot.getChildren())
              {
                  if(ds.child("status").getValue().toString().equals("1")) {
                      AddVisitor temp = new AddVisitor(ds.child("name").getValue().toString(),
                              ds.child("phone").getValue().toString(),
                              ds.child("checkin").getValue().toString(),
                              ds.child("checkout").getValue().toString(),
                              ds.child("hostname").getValue().toString(),
                              ds.child("hostid").getValue().toString(),
                              ds.child("addressvisited").getValue().toString(),
                              ds.child("status").getValue().toString()
                      );

                      li.add(temp);
                  }
              }
              adapter=new VisitorAdapter2(getContext(),li);
              recyclerView.setAdapter(adapter);


          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {

          }
      });


        return v;
    }


}
