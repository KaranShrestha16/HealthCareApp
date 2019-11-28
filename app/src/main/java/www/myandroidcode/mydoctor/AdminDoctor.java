package www.myandroidcode.mydoctor;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AdminDoctor extends AppCompatActivity {
    private ImageView back, addDoctor;
    private Toolbar toolbar_adminDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_doctor);
        setToolbar();

    }
    private void setToolbar() {
        toolbar_adminDoctor = findViewById(R.id.toolbar_adminDoctor);
        setSupportActionBar(toolbar_adminDoctor);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back = findViewById(R.id.admin_DoctorBack);
        addDoctor = findViewById(R.id.admin_addDoctor);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDoctor.this, AdminDashboard.class);
                startActivity(intent);
                finish();
            }
        });

        addDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDoctor.this, AddDoctor.class);
                startActivity(intent);
                finish();
            }
        });

    }




}
