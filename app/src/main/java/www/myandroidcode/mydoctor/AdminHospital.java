package www.myandroidcode.mydoctor;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class AdminHospital extends AppCompatActivity {
    private ImageView back, addHospital;
    private Toolbar toolbar_adminHospital;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_hospital);

        setToolbar();

    }

    private void setToolbar() {
        toolbar_adminHospital = findViewById(R.id.toolbar_adminHospital);
        setSupportActionBar(toolbar_adminHospital);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back = findViewById(R.id.admin_hospitalBack);
        addHospital = findViewById(R.id.admin_addHospital);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHospital.this, AdminDashboard.class);
                startActivity(intent);
                finish();
            }
        });

        addHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHospital.this, AddHospital.class);
                startActivity(intent);
                finish();
            }
        });

    }



}
