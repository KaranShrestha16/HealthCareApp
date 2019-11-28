package www.myandroidcode.mydoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class AdminDashboard extends AppCompatActivity {

    private FrameLayout fl_clinic,fl_doctor,fl_hospital, fl_appointmnet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        fl_clinic =findViewById(R.id.fl_admin_clinic);
        fl_doctor =findViewById(R.id.fl_admin_doctor);
        fl_hospital =findViewById(R.id.fl_admin_hospital);
        fl_appointmnet =findViewById(R.id.fl_admin_appointment);

        fl_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, AdminHospital.class);
                startActivity(intent);
                finish();
            }
        });



    }
}
