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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import API.Url;
import Model.AppointmentHistoryModel;
import Model.ReportModel;
import www.myandroidcode.mydoctor.R;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder>  {

    Context  context;
    List<ReportModel> reportModelsDate;

    public ReportAdapter(Context context, List<ReportModel> reportModelsDate) {
        this.context = context;
        this.reportModelsDate = reportModelsDate;
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.recycler_view_report, parent, false );
        return new ReportAdapter.ReportViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        holder.report_layout.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        String imgpath = Url.BASE_URL + reportModelsDate.get(position).getREPORT_IMAGE();
        StrictMode();
        try{
            URL url = new URL(imgpath);
            holder.report_image.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        }catch (IOException e) {
            e.printStackTrace();
        }

        holder.tv_reportDescription.setText(reportModelsDate.get(position).getDESCRIPTION());
        holder.tv_reportName.setText("Report Name:  "+reportModelsDate.get(position).getREPORT_NAME());
        holder.tv_reportDate.setText("Report Date:  "+reportModelsDate.get(position).getREPORT_DATE());

    }

    @Override
    public int getItemCount() {
        return reportModelsDate.size();
    }




    public class ReportViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_reportDate,tv_reportDescription,tv_reportName;
        private LinearLayout report_layout;
        private ImageView report_image;
        public ReportViewHolder(View itemView) {
            super(itemView);
            report_layout= itemView.findViewById(R.id.report_layout);
            tv_reportDate=itemView.findViewById(R.id.tv_reportDate);
            tv_reportName=itemView.findViewById(R.id.tv_reportName);
            tv_reportDescription= itemView.findViewById(R.id.tv_reportDescription);
            report_image=itemView.findViewById(R.id.image_report);

        }
    }
    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }



}

