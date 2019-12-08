package Adapter;

import android.content.Context;
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
import Model.AppointmentHistoryModel;
import Model.BloodBankModel;
import www.myandroidcode.mydoctor.R;

public class AppointmentHistoryAdapter extends RecyclerView.Adapter<AppointmentHistoryAdapter.AppointmentHistoryAdapterHolder>  {

    Context  context;
    List<AppointmentHistoryModel> appointmentHistoryDate;

    public AppointmentHistoryAdapter(Context context, List<AppointmentHistoryModel> appointmentHistoryDate) {
        this.context = context;
        this.appointmentHistoryDate = appointmentHistoryDate;
    }

    @NonNull
    @Override
    public AppointmentHistoryAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.recycler_view_appointmnet_history, parent, false );
        return new AppointmentHistoryAdapter.AppointmentHistoryAdapterHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentHistoryAdapterHolder holder, int position) {
        holder.appointmnetHistory_layout.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        String imgpath = Url.BASE_URL + appointmentHistoryDate.get(position).getIMAGE();
        StrictMode();
        try{
            URL url = new URL(imgpath);
            holder.hospital_image.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        }catch (IOException e) {
            e.printStackTrace();
        }

        holder.tv_hospitalName.setText("Hospital Name:  "+appointmentHistoryDate.get(position).getHOSPITAL_NAME());
        holder.tv_department.setText("Qualification :  "+appointmentHistoryDate.get(position).getQUALIFICATION());
        holder.tv_docvtorName.setText("Doctor Name:  "+appointmentHistoryDate.get(position).getDOCTOR_NAME());
        holder.tv_birthdate.setText("Appointment Date:  "+appointmentHistoryDate.get(position).getAPPOINTMENT_DATE());

    }

    @Override
    public int getItemCount() {
        return appointmentHistoryDate.size();
    }


    public class AppointmentHistoryAdapterHolder extends RecyclerView.ViewHolder{
        private TextView tv_hospitalName,tv_docvtorName,tv_birthdate,tv_department;
        private LinearLayout appointmnetHistory_layout;
        private ImageView hospital_image;
        public AppointmentHistoryAdapterHolder(View itemView) {
            super(itemView);
            appointmnetHistory_layout= itemView.findViewById(R.id.appointment_history_layout);
            tv_hospitalName=itemView.findViewById(R.id.tv_hospital_name_appointmnet);
            tv_docvtorName= itemView.findViewById(R.id.tv_doctorName_appointment);
            tv_birthdate= itemView.findViewById(R.id.tv_appointment_date);
            tv_department=itemView.findViewById(R.id.tv_department_appointmentHistory);
            hospital_image=itemView.findViewById(R.id.image_hospital);

        }
    }
    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }



}

