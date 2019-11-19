package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Model.ClinicModel;
import Model.HospitalModel;
import www.myandroidcode.mydoctor.Hospital_Details;
import www.myandroidcode.mydoctor.R;

public class ClinicAdapter extends RecyclerView.Adapter<ClinicAdapter.ClinicViewHolde> implements  Filterable{
    Context context;
    List<ClinicModel>clinicData;
    List<ClinicModel> clinicDataFilter;

    public ClinicAdapter(Context context, List<ClinicModel> clinicData) {
        this.context = context;
        this.clinicData = clinicData;
        this.clinicDataFilter = clinicData;

    }

    @NonNull
    @Override
    public ClinicViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.clinic_recyclerview, parent, false );
        return new ClinicViewHolde(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ClinicViewHolde holder, int position) {
        holder.linearLayout.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        holder.tvName.setText(clinicDataFilter.get(position).getName());
        holder.tvContact.setText(clinicDataFilter.get(position).getContact());
        holder.tvAddress.setText(clinicDataFilter.get(position).getAddress());
//        holder.imgView.setText(hospitalData.get(position).getAddress());


    }

    @Override
    public int getItemCount() {
        return clinicDataFilter.size();
    }

    @Override
    public Filter getFilter() {
        return  new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key= constraint.toString();
                if(Key.isEmpty()){
                    clinicDataFilter=clinicData;
                }else{
                    List<ClinicModel> listFiltered = new ArrayList<>();
                    for(ClinicModel row :clinicData){
                        if(row.getName().toLowerCase().contains(Key.toLowerCase())){
                            listFiltered.add(row);
                        }
                    } clinicDataFilter=listFiltered;
                }

                FilterResults filterResults=new FilterResults();
                filterResults.values=clinicDataFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                clinicDataFilter=(List<ClinicModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    public class ClinicViewHolde extends RecyclerView.ViewHolder {
        private TextView tvName,tvContact,tvAddress,tv_readmore;
        private ImageView imgView;
        private LinearLayout linearLayout;


        public ClinicViewHolde(View itemView) {
            super(itemView);
            linearLayout= itemView.findViewById(R.id.clinic_layout);
            tvName=itemView.findViewById(R.id.tv_Clinic_name);
            imgView=itemView.findViewById(R.id.tv_clinic_image);
            tvAddress=itemView.findViewById(R.id.tv_Clinic_location);
            tvContact=itemView.findViewById(R.id.tv_clinic_contact );
            tv_readmore=itemView.findViewById(R.id.tv_readmore_clinic );



        }


    }

}
