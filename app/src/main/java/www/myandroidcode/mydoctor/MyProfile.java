package www.myandroidcode.mydoctor;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import API.PatientAPI;
import API.Url;
import Model.PatientModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfile extends AppCompatActivity {
    private Toolbar toolbarClinic;
    private ImageView back,refesh;
    private TextView tv_bloodGroup,tv_contact,tv_birthdate,tv_name,tv_editProfile,tv_medicalReport,tv_appointmentHistory;
    private ImageView image_patientProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        setToolbar();

        tv_name=findViewById(R.id.tv_patientName);
        tv_bloodGroup=findViewById(R.id.tv_bloodGroup);
        tv_contact=findViewById(R.id.tv_contact);
        image_patientProfile=findViewById(R.id.image_patientProfile);
        tv_appointmentHistory=findViewById(R.id.tv_appointmentHistory);
        tv_birthdate=findViewById(R.id.tv_birthdate);
        tv_medicalReport=findViewById(R.id.tv_medicalReport);
        tv_editProfile=findViewById(R.id.tv_editProfile);

        PatientAPI patientAPI= Url.getInstance().create(PatientAPI.class);
        Call<PatientModel> patientModelCall= patientAPI.getPatientById(Url.accessToken,Url.id);
        patientModelCall.enqueue(new Callback<PatientModel>() {
            @Override
            public void onResponse(Call<PatientModel> call, Response<PatientModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MyProfile.this, "Patient_id Could not find", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    String imgpath = Url.BASE_URL + response.body().getIMAGE();
                    StrictMode();
                    try{
                        URL url = new URL(imgpath);
                        image_patientProfile.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                    tv_name.setText(response.body().getNAME());
                    tv_bloodGroup.setText(response.body().getBLOOD_GROUP());
                    tv_contact.setText(response.body().getCONTACT());
                    tv_birthdate.setText(response.body().getBIRTHDATE());

                    Log.d("bloodGroup", response.body().getBLOOD_GROUP()+"");
                    Log.d("Birthdate", response.body().getBIRTHDATE()+"");
                }
            }

            @Override
            public void onFailure(Call<PatientModel> call, Throwable t) {
                Toast.makeText(MyProfile.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        tv_appointmentHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent( MyProfile.this, AppointmentHistoty.class);
                startActivity(intent);
                finish();

            }
        });

        tv_medicalReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent( MyProfile.this, Report.class);
                startActivity(intent);
                finish();
            }
        });




        }

    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }
    private void setToolbar() {
        toolbarClinic= findViewById(R.id.toolbarProfile);
        setSupportActionBar(toolbarClinic);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back=findViewById(R.id.myProfileBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MyProfile.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }



}
