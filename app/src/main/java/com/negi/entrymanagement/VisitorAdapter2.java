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

public class VisitorAdapter2 extends RecyclerView.Adapter<VisitorAdapter2.VisitorViewHolder> {
    private Context context;




    private List<AddVisitor> addVisitorList;
    public  VisitorAdapter2(Context context,List<AddVisitor> addVisitorList)
    {
        this.context=context;
        this.addVisitorList=addVisitorList;
    }

    @NonNull
    @Override
    public VisitorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.alllog,parent,false);
        VisitorViewHolder holder=new VisitorViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VisitorViewHolder holder, final int position) {
        final AddVisitor addVisitor = addVisitorList.get(position);
        holder.name.setText(addVisitor.getName());
        holder.phone.setText(addVisitor.getPhone());
        holder.hostregno.setText(addVisitor.getHostid());
        holder.arrival.setText(addVisitor.getCheckin());
        holder.hostname.setText(addVisitor.getAddressvisited());
        holder.er.setText(addVisitor.getCheckout());



    }



    @Override
    public int getItemCount() {
        return addVisitorList.size();
    }

    public class VisitorViewHolder extends RecyclerView.ViewHolder {
        TextView name,hostname,phone,hostregno,arrival,er;
        LinearLayout l1;

        public VisitorViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            hostname=itemView.findViewById(R.id.hostname);
            phone=itemView.findViewById(R.id.phone);
            hostregno=itemView.findViewById(R.id.hostregno);
            arrival=itemView.findViewById(R.id.arrival);

            er=itemView.findViewById(R.id.er);
        }
    }
}
