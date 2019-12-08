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

import API.PatientAPI;
import API.Url;
import Adapter.AppointmentHistoryAdapter;
import Model.AppointmentHistoryModel;
import Model.AppointmentModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentHistoty extends AppCompatActivity {

    private ImageView back;
    private Toolbar toolbar_adminHospital;
    private RecyclerView recyclerView;
    private TextView tv_errormessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_histoty);
        setToolbar();

        recyclerView= findViewById(R.id.appointmentRecyclearview);
        tv_errormessage= findViewById(R.id.tv_errormessage);

        PatientAPI patientAPI= Url.getInstance().create(PatientAPI.class);
        Call<List<AppointmentHistoryModel>> listCall= patientAPI.getAppointmentHistory(Url.accessToken,Url.id);
        listCall.enqueue(new Callback<List<AppointmentHistoryModel>>() {
            @Override
            public void onResponse(Call<List<AppointmentHistoryModel>> call, Response<List<AppointmentHistoryModel>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(AppointmentHistoty.this, response.code(), Toast.LENGTH_SHORT).show();
                }else
                {
                    if(response.body().isEmpty()){
                        tv_errormessage.setText("No Appointment has taken");
                    }else{

                        List<AppointmentHistoryModel> appointmentHistoryModelels=response.body();
                        AppointmentHistoryAdapter appointmentHistoryAdapter= new AppointmentHistoryAdapter(AppointmentHistoty.this,appointmentHistoryModelels);
                        recyclerView.setAdapter(appointmentHistoryAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(AppointmentHistoty.this));

                    }
                }
            }

            @Override
            public void onFailure(Call<List<AppointmentHistoryModel>> call, Throwable t) {
                Toast.makeText(AppointmentHistoty.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void setToolbar() {
        toolbar_adminHospital = findViewById(R.id.toolbar_appointment_history);
        setSupportActionBar(toolbar_adminHospital);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back = findViewById(R.id.toolbar_image_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppointmentHistoty.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


}
