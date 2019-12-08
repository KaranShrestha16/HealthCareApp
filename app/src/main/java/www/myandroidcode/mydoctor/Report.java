package www.myandroidcode.mydoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import API.PharmacyAPI;
import API.Url;
import Adapter.ReportAdapter;
import Model.ReportModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Report extends AppCompatActivity {
    private ImageView back, addReport;
    private Toolbar toolbar_report;
    private RecyclerView recyclerView;
    private TextView tv_errormessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        setToolbar();

        recyclerView= findViewById(R.id.reportRecyclearView);
        tv_errormessage=findViewById(R.id.tv_errormessageReport);

        PharmacyAPI pharmacyAPI= Url.getInstance().create(PharmacyAPI.class);
        Call<List<ReportModel>> rListCall= pharmacyAPI.getReportById(Url.accessToken,Url.id);
        rListCall.enqueue(new Callback<List<ReportModel>>() {
            @Override
            public void onResponse(Call<List<ReportModel>> call, Response<List<ReportModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Report.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    if(response.body().isEmpty()){
                        tv_errormessage.setText("Report Not Added Yet");
                    }else{
                        List<ReportModel> reportModelsData= response.body();
                        ReportAdapter reportAdapter= new ReportAdapter(Report.this,reportModelsData);
                        recyclerView.setAdapter(reportAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Report.this));

                    }
                }
            }

            @Override
            public void onFailure(Call<List<ReportModel>> call, Throwable t) {
                Toast.makeText(Report.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setToolbar() {
        toolbar_report = findViewById(R.id.toolbar_report);
        setSupportActionBar(toolbar_report);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back = findViewById(R.id.reportBack);
        addReport = findViewById(R.id.add_report);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Report.this, MyProfile.class);
                startActivity(intent);
                finish();
            }
        });

        addReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Report.this, AddReport.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
