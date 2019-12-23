package www.myandroidcode.mydoctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class AddBloodBank extends AppCompatActivity {
    private ImageView back;
    private Toolbar toolbar_addDoctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bloodbank);

        setToolbar();
    }

    private void setToolbar() {
        toolbar_addDoctor = findViewById(R.id.toolbar_addDoctor);
        setSupportActionBar(toolbar_addDoctor);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back = findViewById(R.id.add_DoctorBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddBloodBank.this, AdminDoctor.class);
                startActivity(intent);
                finish();
            }
        });


    }

}
