package www.myandroidcode.mydoctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
FrameLayout fl_clinic,fl_doctor,fl_hospital,fl_pharmacy, fl_medicineTracker,fl_bim,fl_bloodbank
        ,fl_ambulance,fl_funeralVehicle,fl_feedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fl_clinic = (FrameLayout)findViewById(R.id.fl_clinic);
        fl_clinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Clinics.class);
                startActivity(intent);
                finish();

            }
        });

        fl_doctor = (FrameLayout)findViewById(R.id.fl_doctor);
        fl_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Doctors.class);
                startActivity(intent);
                finish();
            }
        });


        fl_hospital = (FrameLayout)findViewById(R.id.fl_hospital);
        fl_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Hospitals.class);
                startActivity(intent);
                finish();
            }
        });

        fl_pharmacy = (FrameLayout)findViewById(R.id.fl_pharmacy);
        fl_pharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"Pharmacy Clicked",Toast.LENGTH_SHORT).show();
    }
});


        fl_medicineTracker = (FrameLayout)findViewById(R.id.fl_medicineTracker);
        fl_medicineTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Medicine Tracker Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        fl_bim = (FrameLayout)findViewById(R.id.fl_bim);
        fl_bim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"BIM Calculator Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        fl_bloodbank = (FrameLayout)findViewById(R.id.fl_bloodbank);
        fl_bloodbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"Blood Bank Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        fl_ambulance = (FrameLayout)findViewById(R.id.fl_ambulance);
        fl_ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"Ambulance Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        fl_funeralVehicle = (FrameLayout)findViewById(R.id.fl_funeralVehicle);
        fl_funeralVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"Funeral Vehicle  Clicked",Toast.LENGTH_SHORT).show();
            }
        });


        fl_feedback = (FrameLayout)findViewById(R.id.fl_feedback);
        fl_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"Feedback Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        ImageView cart = (ImageView)findViewById(R.id.imageview_profile);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"UserProfile Clicked",Toast.LENGTH_SHORT).show();
            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
