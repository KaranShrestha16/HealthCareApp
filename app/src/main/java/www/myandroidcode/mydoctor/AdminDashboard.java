package www.myandroidcode.mydoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class AdminDashboard extends AppCompatActivity {

    private FrameLayout fl_healthPackges,fl_hospital, fl_appointmnet,fl_pharmacy,fl_bloodbank,fl_ambulance;
    private ImageView logout;
    private Toolbar toolbarAdminDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        fl_healthPackges =findViewById(R.id.fl_healthPackges);
        fl_hospital =findViewById(R.id.fl_admin_hospital);
        fl_appointmnet =findViewById(R.id.fl_admin_appointment);
        fl_pharmacy =findViewById(R.id.fl_admin_pharmacy);
        fl_bloodbank =findViewById(R.id.fl_admin_bloodbank);
        fl_ambulance =findViewById(R.id.fl_admin_ambulance);
        setToolbar();


        fl_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, AdminHospital.class);
                startActivity(intent);
                finish();
            }
        });


        fl_healthPackges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, HealthPackages.class);
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
                Intent intent = new Intent(AdminDashboard.this, BloodBank_Admin.class);
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

    private void setToolbar() {
        toolbarAdminDashboard = findViewById(R.id.toolbarAdminDashboard);
        setSupportActionBar(toolbarAdminDashboard);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        logout = findViewById(R.id.adminDashboard_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboard.this, Login.class);
                Toast.makeText(AdminDashboard.this, "Logout Successful", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
    }

}
