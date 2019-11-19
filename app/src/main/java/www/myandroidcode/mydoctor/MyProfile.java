package www.myandroidcode.mydoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MyProfile extends AppCompatActivity {
    private Toolbar toolbarClinic;
    private ImageView back,refesh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        setToolbar();
    }


    private void setToolbar() {
        toolbarClinic= findViewById(R.id.toolbarProfile);
        setSupportActionBar(toolbarClinic);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back=findViewById(R.id.myProfileBack);
        refesh=findViewById(R.id.myProfileRefresh);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MyProfile.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        refesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyProfile.this, " Refresh Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }



}
