package www.myandroidcode.mydoctor;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
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
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import API.PatientAPI;
import API.Url;
import Adapter.LoginFragmentAdapter;
import Model.PatientModel;
import Model.ResponseFromAPI;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TextView nav_patientName,nav_patientEmail;
    private CircleImageView nav_patientImage;
    private FrameLayout fl_clinic,fl_doctor,fl_hospital,fl_pharmacy, fl_medicineTracker,fl_bim,fl_bloodbank
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
                Intent intent = new Intent(MainActivity.this, Pharmacy.class);
                startActivity(intent);
                finish();
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
                Intent intent = new Intent(MainActivity.this, BIM.class);
                startActivity(intent);
                finish();
            }
        });

        fl_bloodbank = (FrameLayout)findViewById(R.id.fl_bloodbank);
        fl_bloodbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, BloodBank.class);
                startActivity(intent);
                finish();
            }
        });

        fl_ambulance = (FrameLayout)findViewById(R.id.fl_ambulance);
        fl_ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Ambulance.class);
                startActivity(intent);
                finish();
            }
        });

        fl_funeralVehicle = (FrameLayout)findViewById(R.id.fl_healthPackages);
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
                Intent intent = new Intent(MainActivity.this, MyProfile.class);
                startActivity(intent);
                finish();

            }
        });



        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View hView =  navigationView.getHeaderView(0);

        nav_patientEmail= hView.findViewById(R.id.nav_patientEmail);
        nav_patientImage= hView.findViewById(R.id.nav_patientImage);
        nav_patientName= hView.findViewById(R.id.nav_patientName);

        PatientAPI patientAPI= Url.getInstance().create(PatientAPI.class);
        Call<PatientModel> patientModelCall= patientAPI.getPatientById(Url.accessToken,Url.id);
        patientModelCall.enqueue(new Callback<PatientModel>() {
            @Override
            public void onResponse(Call<PatientModel> call, Response<PatientModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Patient_id Could not find", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    String imgpath = Url.BASE_URL + response.body().getIMAGE();
                    StrictMode();
                    try{
                        URL url = new URL(imgpath);
                        nav_patientImage.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                    nav_patientEmail.setText(response.body().getEMAIL());
                    nav_patientName.setText(response.body().getNAME());

                    Log.d("Email", response.body().getEMAIL()+"");
                    Log.d("Name", response.body().getNAME()+"");
                }
            }

            @Override
            public void onFailure(Call<PatientModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });






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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_contact_us) {
            Toast.makeText(this, "Click Contact Us", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_setting) {
            Toast.makeText(this, "Click Settings", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_about_us) {
            Intent intent = new Intent(this, AboutUs.class);
            startActivity(intent);
            finish();
        }else if (id == R.id.nav_logout) {
            Url.accessToken="";
            Url.id=0;
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }


}
