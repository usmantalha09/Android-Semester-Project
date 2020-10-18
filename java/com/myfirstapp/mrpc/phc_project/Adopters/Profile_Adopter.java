package com.myfirstapp.mrpc.phc_project.Adopters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.myfirstapp.mrpc.phc_project.PersonModel.DoctorModel;
import com.myfirstapp.mrpc.phc_project.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;




public class Profile_Adopter extends RecyclerView.Adapter<Profile_Adopter.ProfileViewHolder>{

    private Context ctx;
    private ArrayList<DoctorModel> list;
    private OnItemClickListner profilelistener;


    public void setOnItemClickListener(OnItemClickListner listener){
        profilelistener = listener;
    }

    public Profile_Adopter(Context ctx, ArrayList<DoctorModel> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.design_view,parent,false);
        return new ProfileViewHolder(v,profilelistener);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {
        DoctorModel model = list.get(position);

        holder.name.setText(model.getD_Name());
        holder.special.setText(model.getD_Specialization());
        holder.desrcip.setText(model.getD_Description());
        holder.review.setText(model.getD_Review());
        holder.price.setText(model.getD_Fee());
        holder.ratingBar.setRating(Float.parseFloat(model.getD_Rating()));
        String url = model.getImageURL();
        Picasso.with(ctx).load(url).fit().into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder{
        TextView name,special,desrcip,price,review;
        ImageView img;
        RatingBar ratingBar;
        Button btn;

        public ProfileViewHolder(View itemView, final OnItemClickListner listener) {
            super(itemView);
            name = itemView.findViewById(R.id.doc_name);
            special = itemView.findViewById(R.id.doc_sppecial);
            desrcip = itemView.findViewById(R.id.doc_descrip);
            price = itemView.findViewById(R.id.doc_price);
            review = itemView.findViewById(R.id.doc_review);
            img =itemView.findViewById(R.id.doc_img);
            ratingBar = itemView.findViewById(R.id.doc_rating);
            btn = itemView.findViewById(R.id.showProfile);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                    {
                        int position = getAdapterPosition();
                        listener.onMove(position);
                    }

                }
            });
        }
    }
    public interface OnItemClickListner{

        void onMove(int position);
    }
}
