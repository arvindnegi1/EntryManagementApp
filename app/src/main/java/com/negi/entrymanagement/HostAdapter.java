package com.negi.entrymanagement;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class HostAdapter extends RecyclerView.Adapter<HostAdapter.HostViewHolder> {
   private Context context;
    int selectedPosition=-1;
   private List<AddHost> addHostList;
   public  HostAdapter(Context context,List<AddHost> addHostList)
   {
       this.context=context;
       this.addHostList=addHostList;
   }

    @NonNull
    @Override
    public HostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.card_layout,parent,false);
        HostViewHolder holder=new HostViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HostViewHolder holder, final int position) {
            final AddHost addHost=addHostList.get(position);
        holder.name.setText(addHost.getName());
        holder.designation.setText(addHost.getDesignation());
        holder.regno.setText(addHost.getRegno());
        holder.phone.setText(addHost.getPhone());
        holder.email.setText(addHost.getEmail());
        if(selectedPosition==position) {
            holder.l1.setBackgroundColor(Color.parseColor("#f9A602"));

        }
        else
            holder.l1.setBackgroundColor(Color.parseColor("#ffffff"));
        holder.l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition=position;

                SharedPreferences pref;
                 SharedPreferences.Editor editor;
                 pref=context.getSharedPreferences("temp",Context.MODE_PRIVATE);
                 editor=pref.edit();
                editor.clear();
                 editor.putString("hostid",addHost.getRegno());
                    editor.commit();


                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return addHostList.size();
    }

    public class HostViewHolder extends RecyclerView.ViewHolder {
       TextView name,email,designation,regno,phone;
       LinearLayout l1;

       public HostViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            email=itemView.findViewById(R.id.email);
            designation=itemView.findViewById(R.id.designation);
            regno=itemView.findViewById(R.id.regno);
            phone=itemView.findViewById(R.id.phone);
            l1=itemView.findViewById(R.id.full);

        }
    }
}
