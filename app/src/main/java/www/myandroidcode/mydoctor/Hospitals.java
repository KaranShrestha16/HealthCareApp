package www.myandroidcode.mydoctor;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class Hospitals extends AppCompatActivity  {
    private Toolbar toolbarHospital;
    private ImageView back,refesh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);
       setToolbar();







    }

    private void setToolbar() {
        toolbarHospital= findViewById(R.id.toolbarHospital);
        setSupportActionBar(toolbarHospital);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back=findViewById(R.id.hospitalBack);
        refesh=findViewById(R.id.hospitalRefresh);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Hospitals.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        refesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Hospitals.this, " Refresh Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
