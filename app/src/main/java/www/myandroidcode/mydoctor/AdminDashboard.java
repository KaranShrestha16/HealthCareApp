package www.myandroidcode.mydoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class AdminDashboard extends AppCompatActivity {

    private FrameLayout fl_doctor,fl_hospital, fl_appointmnet,fl_pharmacy,fl_bloodbank,fl_ambulance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        fl_doctor =findViewById(R.id.fl_admin_doctor);
        fl_hospital =findViewById(R.id.fl_admin_hospital);
        fl_appointmnet =findViewById(R.id.fl_admin_appointment);
        fl_pharmacy =findViewById(R.id.fl_admin_pharmacy);
        fl_bloodbank =findViewById(R.id.fl_admin_bloodbank);
        fl_ambulance =findViewById(R.id.fl_admin_ambulance);

        fl_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, AdminHospital.class);
                startActivity(intent);
                finish();
            }
        });


        fl_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, AdminDoctor.class);
                startActivity(intent);
                finish();
            }
        });
        fl_pharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, AdminPharmacy.class);
                startActivity(intent);
                finish();
            }
        });


        fl_bloodbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, AdminBloodBank.class);
                startActivity(intent);
                finish();
            }
        });

        fl_ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, AdminAmbulance.class);
                startActivity(intent);
                finish();
            }
        });









    }
}
