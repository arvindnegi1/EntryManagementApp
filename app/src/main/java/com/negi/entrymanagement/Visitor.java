package com.negi.entrymanagement;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Visitor extends Fragment  {

LinearLayout l1,l2;
TextView t1;
Button b1,b2;
HostAdapter adapter;
RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mref;
    FirebaseDatabase db2;
    DatabaseReference mref2;
    List<AddHost> li;
   EditText n1,p1,c1,v1;
    String name,phone,checkin,visited;
    Button ds;
    SharedPreferences pref;
    String hname,hphone;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_visitor, container, false);
   l1=v.findViewById(R.id.top);
   l2=v.findViewById(R.id.recy);
   b1=v.findViewById(R.id.visitoradd);
   n1=v.findViewById(R.id.name);
   p1=v.findViewById(R.id.Phone);

   v1=v.findViewById(R.id.visited);
   t1=v.findViewById(R.id.teste);
   ds=v.findViewById(R.id.addtodatabase);
   recyclerView=v.findViewById(R.id.recycle);
        li=new ArrayList<>();
        hname="";
        firebaseDatabase= FirebaseDatabase.getInstance();
        mref=firebaseDatabase.getReference("HOST");
        db2=FirebaseDatabase.getInstance();
        mref2=db2.getReference("VISITOR");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren() ) {
//      Toast.makeText(getContext(),""+ds.child("name").getValue().toString(),Toast.LENGTH_SHORT).show();
                    AddHost temp=new AddHost(ds.child("regno").getValue().toString(),
                            ds.child("name").getValue().toString(),
                            ds.child("phone").getValue().toString(),
                            ds.child("email").getValue().toString(),
                            ds.child("designation").getValue().toString()


                    );

                    li.add(temp);

                    // t1.setText(ds.child("regno").getValue().toString());

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),""+databaseError,Toast.LENGTH_LONG);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           name=n1.getText().toString();
           phone=p1.getText().toString();
//           checkin=c1.getText().toString();
           visited=v1.getText().toString();

           l1.setVisibility(View.GONE);
           l2.setVisibility(View.VISIBLE);
       }
   });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //t1.setText(""+li.size());
        adapter=new HostAdapter(getContext(),li);
        recyclerView.setAdapter(adapter);
        ds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle("CONFIRM SAVE")
                        .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                pref=getContext().getSharedPreferences("temp",Context.MODE_PRIVATE);
                                final String hostid=pref.getString("hostid","");

                                mref.addValueEventListener(new ValueEventListener() {
                                 @Override
                                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                     for(DataSnapshot ds:dataSnapshot.getChildren() ) {
                                         if ((ds.child("regno").getValue().toString()) == hostid) {
                                           // Toast.makeText(getContext(), "Succesfull" + ds.child("name").getValue().toString(), Toast.LENGTH_SHORT).show();
                                         hname=ds.child("name").getValue().toString();
                                         hphone=ds.child("phone").getValue().toString();
                                         final String curdate=java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                                             final AddVisitor visitor=new AddVisitor(name,phone,curdate,"",hname,hostid,visited,"0");
                                             mref2.child(phone).setValue(visitor).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                 @Override
                                                 public void onComplete(@NonNull Task<Void> task) {
                                                     Toast.makeText(getContext(), "Succesfull"+visitor.getName(), Toast.LENGTH_SHORT).show();
                                                     SmsManager smsManager=SmsManager.getDefault();
                                                     smsManager.sendTextMessage("+91"+hphone,null,"name:"+name+"\r"+"Phone:"+phone+"\r"+"arrival:" +
                                                             curdate+"",null,null);
                                                 }
                                             });
                                         }
                                     }
                                 }

                                 @Override
                                 public void onCancelled(@NonNull DatabaseError databaseError) {

                                 }
                             });
                                //name,phone,checkin,checkout,hostname,hostid,addressvisited,status;



                                // Toast.makeText(getContext(),""+hostid,Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create();
                builder.show();
            }
        });


        return  v;
    }

}
