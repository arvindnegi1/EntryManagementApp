package com.negi.entrymanagement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Host extends Fragment implements View.OnClickListener {

    EditText n,p,e,r,d;
    Button b;
    AddHost ah;
    String name,designation,regno,phone,email;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mref;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_host, container, false);
        n=v.findViewById(R.id.name);
        p=v.findViewById(R.id.phone);
        e=v.findViewById(R.id.email);
        r=v.findViewById(R.id.regno);
        d=v.findViewById(R.id.deignation);
        b=v.findViewById(R.id.submit);
        b.setOnClickListener(this);

        return  v;
    }




    @Override
    public void onClick(View v) {
        if(checkValidity()) {
            firebaseDatabase = FirebaseDatabase.getInstance();
            mref = firebaseDatabase.getReference("HOST");
            ah = new AddHost(regno, name, phone, email, designation);
            mref.child(regno).setValue(ah).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getContext(), "Succesfull"+ah.getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private boolean checkValidity()
    {
        name=n.getText().toString().trim();
        email=e.getText().toString().trim();
        phone=p.getText().toString().trim();
        regno=r.getText().toString().trim();
        designation=d.getText().toString().trim();

        if(name.length()==0) {
            n.setError("name required");
            n.requestFocus();
            return false;
        } else if(email.length()==0) {
            e.setError("emailrequired");
            e.requestFocus();
            return false;
        }else if (phone.length() == 0) {
            p.setError("Mobile Number Required");
            p.requestFocus();
            return false;
        } else if (phone.length() < 10) {
            p.setError("Mobile Number Invalid");
            p.requestFocus();
            return false;
        }else if(regno.length()==0){
            r.setError("REgistartion no. Required");
            r.requestFocus();
            return false;
        }else if(designation.length()==0){
            d.setError("Designation Required");
            d.requestFocus();
            return false;
        }
        return true;
    }
}
