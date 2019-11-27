package Adapter;

import android.content.Context;
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

import Model.DoctorModel;
import www.myandroidcode.mydoctor.R;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolde> implements Filterable {

    Context context;
    List<DoctorModel> doctorData;
    List<DoctorModel> doctorDataFilter;

    public DoctorAdapter(Context context, List<DoctorModel> doctorData) {
        this.context = context;
        this.doctorData = doctorData;
        this.doctorDataFilter = doctorData;


    }


    @NonNull
    @Override
    public DoctorViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.recycler_view_doctor, parent, false );
        return new DoctorViewHolde(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolde holder, int position) {
        holder.doctor_layout.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        holder.tvName.setText(doctorDataFilter.get(position).getName());
        holder.tvSpeciality.setText(doctorDataFilter.get(position).getSpciality());


    }

    @Override
    public int getItemCount() {
        return doctorDataFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String Key= charSequence.toString();
                if(Key.isEmpty()){
                    doctorDataFilter=doctorData;
                }else{
                    List<DoctorModel> listFiltered = new ArrayList<>();
                    for(DoctorModel row :doctorData){
                        if(row.getName().toLowerCase().contains(Key.toLowerCase())){
                            listFiltered.add(row);
                        }
                    } doctorDataFilter=listFiltered;
                }

                FilterResults filterResults=new FilterResults();
                filterResults.values=doctorDataFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                doctorDataFilter=(List<DoctorModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class DoctorViewHolde extends RecyclerView.ViewHolder {
        private TextView tvName,tvSpeciality;
        private LinearLayout doctor_layout;
        private ImageView doctor_image;

            public DoctorViewHolde(View itemView) {
                super(itemView);
                doctor_layout= itemView.findViewById(R.id.doctor_layout);
                tvName=itemView.findViewById(R.id.tv_doctorName);
                tvSpeciality= itemView.findViewById(R.id.tv_doctor_speciality);
                doctor_image=itemView.findViewById(R.id.doctor_image);


            }
        }

}
