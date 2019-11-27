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

import Model.BloodBankModel;
import Model.PharmacyModel;
import www.myandroidcode.mydoctor.R;

public class BloodBankAdapter extends RecyclerView.Adapter<BloodBankAdapter.BloodBankHolder> implements Filterable {

    Context  context;
    List<BloodBankModel> bloodBankData;
    List<BloodBankModel> bloodBankDataFilter;

    public BloodBankAdapter(Context context, List<BloodBankModel> bloodBankData) {
        this.context = context;
        this.bloodBankData = bloodBankData;
        this.bloodBankDataFilter = bloodBankData;
    }

    @NonNull
    @Override
    public BloodBankHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.recycler_view_blood_bank, parent, false );
        return new BloodBankAdapter.BloodBankHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull BloodBankHolder holder, int position) {
        holder.bloodBank_layout.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        holder.tvName.setText(bloodBankDataFilter.get(position).getName());
        holder.tvAddress.setText(bloodBankDataFilter.get(position).getAddress());
        holder.tvContact.setText(bloodBankDataFilter.get(position).getContact());
        holder.tvWebsite.setText(bloodBankDataFilter.get(position).getWebpage());
    }

    @Override
    public int getItemCount() {
        return bloodBankDataFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String Key= charSequence.toString();
                if(Key.isEmpty()){
                    bloodBankDataFilter=bloodBankData;
                }else{
                    List<BloodBankModel> listFiltered = new ArrayList<>();
                    for(BloodBankModel row :bloodBankDataFilter){
                        if(row.getName().toLowerCase().contains(Key.toLowerCase())){
                            listFiltered.add(row);
                        }
                    } bloodBankDataFilter=listFiltered;
                }

                FilterResults filterResults=new FilterResults();
                filterResults.values=bloodBankDataFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                bloodBankDataFilter=(List<BloodBankModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class BloodBankHolder extends RecyclerView.ViewHolder{
        private TextView tvName,tvAddress,tvContact,tvWebsite;
        private LinearLayout bloodBank_layout;
        private ImageView bloodBank_image;
        public BloodBankHolder(View itemView) {
            super(itemView);

            bloodBank_layout= itemView.findViewById(R.id.bloodBank_layout);
            tvName=itemView.findViewById(R.id.tv_bloodBank_name);
            tvAddress= itemView.findViewById(R.id.tv_bloodBank_address);
            tvContact= itemView.findViewById(R.id.tv_bloodBank_contact);
            tvWebsite= itemView.findViewById(R.id.tv_bloodBank_website);
            bloodBank_image=itemView.findViewById(R.id.tv_bloodBank_image);

        }
    }

}

