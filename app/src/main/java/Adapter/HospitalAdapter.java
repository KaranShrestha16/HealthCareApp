package Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import Model.HospitalModel;
import www.myandroidcode.mydoctor.Hospital_Details;
import www.myandroidcode.mydoctor.R;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder> implements Filterable {

    Context context;
    List<HospitalModel> hospitalData;
    List<HospitalModel> hospitalDataFilter;


    public HospitalAdapter(Context context, List<HospitalModel> hospitalData) {
        this.context = context;
        this.hospitalData = hospitalData;
        this.hospitalDataFilter = hospitalData;
    }

    @NonNull
    @Override
    public HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View layout = LayoutInflater.from(context).inflate(R.layout.recycle_view_hospital, parent, false );
         return new HospitalViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull final HospitalViewHolder holder, final int position) {
        holder.linearLayout.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
//        final HospitalModel hospitalModelss= hospitalDataFilter.get(position);
        String imgpath = Url.BASE_URL + hospitalDataFilter.get(position).getIMAGE();
        StrictMode();
        try{
            URL url = new URL(imgpath);
            holder.imgView.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        }catch (IOException e) {
            e.printStackTrace();
        }

        holder.tvName.setText(hospitalDataFilter.get(position).getNAME());
        holder.tvContact.setText(hospitalDataFilter.get(position).getCONTACT());
        holder.tvAddress.setText(hospitalDataFilter.get(position).getADDRESS());
//
        holder.tv_readmore_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Hospital_Details.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("hospital_id",hospitalDataFilter.get(position).getHOSPITAL_ID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hospitalDataFilter.size();
    }

    @Override
    public Filter getFilter() {

        return  new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String Key= charSequence.toString();
                if(Key.isEmpty()){
                    hospitalDataFilter=hospitalData;
                }else{
                    List<HospitalModel> listFiltered = new ArrayList<>();
                    for(HospitalModel row :hospitalData){
                        if(row.getNAME().toLowerCase().contains(Key.toLowerCase())){
                            listFiltered.add(row);
                        }
                    } hospitalDataFilter=listFiltered;
                }

                FilterResults filterResults=new FilterResults();
                filterResults.values=hospitalDataFilter;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            hospitalDataFilter=(List<HospitalModel>) filterResults.values;
            notifyDataSetChanged();
            }
        };
    }
    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }


    public class HospitalViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName,tvContact,tvAddress,tv_readmore_hospital;
        private ImageView imgView;
        private LinearLayout linearLayout;


        public HospitalViewHolder(View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.hospital_layout);
            tvName=itemView.findViewById(R.id.tv_Hospital_name );
            imgView=itemView.findViewById(R.id.txtHospital_image );
            tvAddress=itemView.findViewById(R.id.tv_Hospital_location );
            tvContact=itemView.findViewById(R.id.tv_Hospital_contatc );
            tv_readmore_hospital=itemView.findViewById(R.id.tv_readmore_hospital );

        }
    }
}
