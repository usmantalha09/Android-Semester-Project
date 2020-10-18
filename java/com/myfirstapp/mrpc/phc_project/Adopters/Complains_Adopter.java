package com.myfirstapp.mrpc.phc_project.Adopters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.myfirstapp.mrpc.phc_project.PersonModel.ComplainsModel;
import com.myfirstapp.mrpc.phc_project.R;

import java.util.ArrayList;


public class Complains_Adopter extends RecyclerView.Adapter<Complains_Adopter.ComplainViewHolder>{

    private Context ctx;
    private ArrayList<ComplainsModel> list;
    private Complains_Adopter.OnItemClickListner complainlistener;



    public void setOnItemClickListener(Complains_Adopter.OnItemClickListner listener){
        complainlistener = listener;
    }

    public Complains_Adopter(Context ctx, ArrayList<ComplainsModel> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @Override
    public ComplainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.patient_complain_design,parent,false);
        return new Complains_Adopter.ComplainViewHolder(v,complainlistener);
    }

    @Override
    public void onBindViewHolder(ComplainViewHolder holder, int position) {

        ComplainsModel model = list.get(position);

        holder.name.setText(model.getP_name());
        holder.address.setText(model.getP_address());
        holder.phone.setText(model.getP_phoneno());
        holder.blood.setText(model.getP_group());
        holder.complain.setText(model.getP_complain());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ComplainViewHolder extends RecyclerView.ViewHolder{

        TextView name,address,phone,blood,complain;
        Button btn;

        public ComplainViewHolder(View itemView,final OnItemClickListner listener) {
            super(itemView);
            name = itemView.findViewById(R.id.Complainpatientname);
            address = itemView.findViewById(R.id.Complainpatientaddress);
            phone = itemView.findViewById(R.id.Complainpatientphone);
            blood = itemView.findViewById(R.id.Complainpatientblood);
            complain = itemView.findViewById(R.id.Complainpatientcomplain);
            btn = itemView.findViewById(R.id.ComplainResponse);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                    {
                        int position = getAdapterPosition();
                        listener.Response(position);
                    }

                }
            });
        }
    }

    public interface OnItemClickListner{

        void Response(int position);
    }
}
