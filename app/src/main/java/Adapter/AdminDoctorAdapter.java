package Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import API.Url;
import Model.DoctorModel;
import Model.Doctor_HospitalModel;
import www.myandroidcode.mydoctor.GetAppointment;
import www.myandroidcode.mydoctor.R;

public class AdminDoctorAdapter extends RecyclerView.Adapter<AdminDoctorAdapter.DoctorViewHolde> implements Filterable {

    Context context;
    List<Doctor_HospitalModel> doctorData;
    List<Doctor_HospitalModel> doctorDataFilter;

    public AdminDoctorAdapter(Context context, List<Doctor_HospitalModel> doctorData) {
        this.context = context;
        this.doctorData = doctorData;
        this.doctorDataFilter = doctorData;


    }


    @NonNull
    @Override
    public DoctorViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.recycler_view_admin_doctor, parent, false );
        return new DoctorViewHolde(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolde holder, final int position) {
        holder.doctor_layout.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));

        String imgpath = Url.BASE_URL + doctorDataFilter.get(position).getDOCTOR_IMAGE();
        StrictMode();
        try{
            URL url = new URL(imgpath);
            holder.doctor_image.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        }catch (IOException e) {
            e.printStackTrace();
        }

        holder.tvName.setText(doctorDataFilter.get(position).getDOCTOR_NAME());
        holder.tv_doctor_qualification.setText("Qualification: "+doctorDataFilter.get(position).getQUALIFICATION());
        holder.tv_doctor_admin_speciality.setText("Speciality: "+doctorDataFilter.get(position).getDEPARTMENT());





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
                    List<Doctor_HospitalModel> listFiltered = new ArrayList<>();
                    for(Doctor_HospitalModel row :doctorData){
                        if(row.getDOCTOR_NAME().toLowerCase().contains(Key.toLowerCase())){
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
                doctorDataFilter=(List<Doctor_HospitalModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class DoctorViewHolde extends RecyclerView.ViewHolder {
        private TextView tvName,tv_doctor_qualification,tv_doctor_admin_speciality;
        private LinearLayout doctor_layout;
        private ImageView doctor_image;

            public DoctorViewHolde(View itemView) {
                super(itemView);
                doctor_layout= itemView.findViewById(R.id.doctor_layout_admin);
                tvName=itemView.findViewById(R.id.tv_admin_doctorNameNext);
                tv_doctor_qualification= itemView.findViewById(R.id.tv_doctor_admin_qualification);
                tv_doctor_admin_speciality= itemView.findViewById(R.id.tv_doctor_admin_speciality);
                doctor_image=itemView.findViewById(R.id.doctor_admin_image);


            }
        }

    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

}
