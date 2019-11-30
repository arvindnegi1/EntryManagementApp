package com.negi.entrymanagement;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class VisitorAdapter extends RecyclerView.Adapter<VisitorAdapter.VisitorViewHolder> {
    private Context context;




    private List<AddVisitor> addVisitorList;
    public  VisitorAdapter(Context context,List<AddVisitor> addVisitorList)
    {
        this.context=context;
        this.addVisitorList=addVisitorList;
    }

    @NonNull
    @Override
    public VisitorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.card_layout_pending,parent,false);
        VisitorViewHolder holder=new VisitorViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VisitorViewHolder holder, final int position) {
        final AddVisitor addVisitor=addVisitorList.get(position);
        holder.name.setText(addVisitor.getName());
        holder.phone.setText(addVisitor.getPhone());
   holder.hostregno.setText(addVisitor.getHostid());
 holder.arrival.setText(addVisitor.getCheckin());
   holder.hostname.setText(addVisitor.getHostname());

            FirebaseDatabase firebaseDatabase;
            firebaseDatabase=FirebaseDatabase.getInstance();
            final DatabaseReference mref=firebaseDatabase.getReference("VISITOR");
            holder.l1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setTitle("Departure")
                            .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                    final String curdate=java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                                    mref.child(addVisitor.getPhone()).child("checkout").setValue(curdate);
                                    mref.child(addVisitor.getPhone()).child("status").setValue("1").addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(context,"succesfull",Toast.LENGTH_SHORT).show();
                                            SmsManager smsManager=SmsManager.getDefault();
                                            smsManager.sendTextMessage("+91"+addVisitor.getPhone(),null,"name:"+addVisitor.getName()+"\r"+"Phone:"+addVisitor.getPhone()+"\r"+"arrival:" +
                                                    addVisitor.getCheckin()+"\r"+"Checkout:"+curdate+"\r"+"Host name:"+addVisitor.getHostname()+"\r"+"address visited:"+addVisitor.getAddressvisited(),null,null);
                                        addVisitorList.remove(position);
                                        notifyDataSetChanged();
                                        }
                                    });




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

    }



    @Override
    public int getItemCount() {
        return addVisitorList.size();
    }

    public class VisitorViewHolder extends RecyclerView.ViewHolder {
        TextView name,hostname,phone,hostregno,arrival;
        LinearLayout l1;

        public VisitorViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            hostname=itemView.findViewById(R.id.hostname);
            phone=itemView.findViewById(R.id.phone);
            hostregno=itemView.findViewById(R.id.hostregno);
            arrival=itemView.findViewById(R.id.arrival);
            l1=itemView.findViewById(R.id.full);
        }
    }
}
