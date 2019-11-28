package www.myandroidcode.mydoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class AddHospital extends AppCompatActivity {
    private ImageView back;
    private Toolbar toolbar_addHospital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hospital);
        setToolbar();
    }



    private void setToolbar() {
        toolbar_addHospital = findViewById(R.id.toolbar_addHospital);
        setSupportActionBar(toolbar_addHospital);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back = findViewById(R.id.add_hospitalBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddHospital.this, AdminHospital.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
