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

import Model.AmbulanceModel;
import Model.BloodBankModel;
import www.myandroidcode.mydoctor.R;

public class AmbulanceAdapter extends RecyclerView.Adapter<AmbulanceAdapter.AmbulanceHolder> implements Filterable {
    Context context;
    List<AmbulanceModel> ambulanceData;
    List<AmbulanceModel> ambulanceDataFilter;

    public AmbulanceAdapter(Context context, List<AmbulanceModel> ambulanceData) {
        this.context = context;
        this.ambulanceData = ambulanceData;
        this.ambulanceDataFilter = ambulanceData;
    }

    @NonNull
    @Override
    public AmbulanceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.recycler_view_ambulance, parent, false );
        return new AmbulanceAdapter.AmbulanceHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull AmbulanceHolder holder, int position) {
        holder.ambulance_layout.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        holder.tvName.setText(ambulanceDataFilter.get(position).getNAME());
        holder.tvAddress.setText(ambulanceDataFilter.get(position).getADDRESS());
        holder.tvContact.setText(ambulanceDataFilter.get(position).getCONTACT());
    }

    @Override
    public int getItemCount() {
        return ambulanceDataFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String Key= charSequence.toString();
                if(Key.isEmpty()){
                    ambulanceDataFilter=ambulanceData;
                }else{
                    List<AmbulanceModel> listFiltered = new ArrayList<>();
                    for(AmbulanceModel row :ambulanceDataFilter){
                        if(row.getNAME().toLowerCase().contains(Key.toLowerCase())){
                            listFiltered.add(row);
                        }
                    } ambulanceDataFilter=listFiltered;
                }

                FilterResults filterResults=new FilterResults();
                filterResults.values=ambulanceDataFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                ambulanceDataFilter=(List<AmbulanceModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class AmbulanceHolder extends RecyclerView.ViewHolder{
        private TextView tvName,tvAddress,tvContact;
        private LinearLayout ambulance_layout;
        public AmbulanceHolder(View itemView) {
            super(itemView);
           ambulance_layout= itemView.findViewById(R.id.ambulance_layout);
            tvName=itemView.findViewById(R.id.tv_ambulance_name);
            tvAddress= itemView.findViewById(R.id.tv_ambulance_address);
            tvContact= itemView.findViewById(R.id.tv_ambulance_contact);
        }
    }

}
