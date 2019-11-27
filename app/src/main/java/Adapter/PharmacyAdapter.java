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
import Model.PharmacyModel;
import www.myandroidcode.mydoctor.R;

public class PharmacyAdapter extends RecyclerView.Adapter<PharmacyAdapter.PharmacyViewHolder> implements Filterable {
    Context context;
    List<PharmacyModel> pharmacyData;
    List<PharmacyModel> pharmacyDataFilter;

    public PharmacyAdapter(Context context, List<PharmacyModel> pharmacyData) {
        this.context = context;
        this.pharmacyData = pharmacyData;
        this.pharmacyDataFilter = pharmacyData;
    }

    @NonNull
    @Override
    public PharmacyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.recycler_view_pharmacy, parent, false );
        return new PharmacyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacyViewHolder holder, int position) {
        holder.pharmacy_layout.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        holder.tvName.setText(pharmacyDataFilter.get(position).getName());
        holder.tvAddress.setText(pharmacyDataFilter.get(position).getAddress());
        holder.tvContact.setText(pharmacyDataFilter.get(position).getContact());
        holder.tvWebsite.setText(pharmacyDataFilter.get(position).getWebpage());

    }

    @Override
    public int getItemCount() {
        return pharmacyDataFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String Key= charSequence.toString();
                if(Key.isEmpty()){
                    pharmacyDataFilter=pharmacyData;
                }else{
                    List<PharmacyModel> listFiltered = new ArrayList<>();
                    for(PharmacyModel row :pharmacyData){
                        if(row.getName().toLowerCase().contains(Key.toLowerCase())){
                            listFiltered.add(row);
                        }
                    } pharmacyDataFilter=listFiltered;
                }

                FilterResults filterResults=new FilterResults();
                filterResults.values=pharmacyDataFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                pharmacyDataFilter=(List<PharmacyModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class PharmacyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName,tvAddress,tvContact,tvWebsite;
        private LinearLayout pharmacy_layout;
        private ImageView pharmacy_image;



        public PharmacyViewHolder(View itemView) {
            super(itemView);
            pharmacy_layout= itemView.findViewById(R.id.pharmacy_layout);
            tvName=itemView.findViewById(R.id.tv_pharmacy_name);
            tvAddress= itemView.findViewById(R.id.tv_pharmacy_location);
            tvContact= itemView.findViewById(R.id.tv_pharmacy_contact);
            tvWebsite= itemView.findViewById(R.id.tv_pharmacy_website);
            pharmacy_image=itemView.findViewById(R.id.tv_pharmacy_image);

        }
    }
}
