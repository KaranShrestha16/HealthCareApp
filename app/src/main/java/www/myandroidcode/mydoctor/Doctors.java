package www.myandroidcode.mydoctor;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import Adapter.DoctorAdapter;

import Model.DoctorModel;


public class Doctors extends AppCompatActivity {
    private Toolbar toolbardoctor;
    private ImageView back, refesh;
    private RecyclerView recyclerView;
    private DoctorAdapter doctorAdapter;
    private List<DoctorModel> doctorData;
    private EditText searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
        setToolbar();

        recyclerView = findViewById(R.id.recyclerView_doctor);
        doctorData = new ArrayList<>();

        doctorData.add(new DoctorModel("Dr. Karan Shrestha ", "MBBS MS (Pediatric Surgery & Neonatal Surgen)"));
        doctorData.add(new DoctorModel("Dr. Giri Raj Raut ", " MS- General Surgery: Kathmandu University Hospital"));
        doctorData.add(new DoctorModel("Dr. Rojin Miya ", " MS- General Surgery: Kathmandu University Hospita)"));
        doctorData.add(new DoctorModel("Dr. Rojin Miya ", " MS- General Surgery: Kathmandu University Hospita)"));
        doctorData.add(new DoctorModel("Dr. Bibek Lama", " MS- General Surgery: Kathmandu University Hospital"));
        doctorData.add(new DoctorModel("Dr. Yubraj Shrestha ", " MS- General Surgery: Kathmandu University Hospital"));
        doctorData.add(new DoctorModel("Dr. Kritika Shrestha ", " MS- General Surgery: Kathmandu University Hospital"));
        doctorData.add(new DoctorModel("Dr. Prakriti Lama ", " MBBS MS (Pediatric Surgery & Neonatal Surgen)"));
        doctorData.add(new DoctorModel("Dr. Dimphu Lama ", " MS- General Surgery: Kathmandu University Hospital"));
        doctorData.add(new DoctorModel("Dr. Kishowr Raut ", " MBBS MS (Pediatric Surgery & Neonatal Surgen)"));
        doctorData.add(new DoctorModel("Dr. Mahesh Shretha ", " MS- General Surgery: Kathmandu University Hospital"));
        doctorData.add(new DoctorModel("Dr. Raman Fadera ", " MS- General Surgery: Kathmandu University Hospital"));
        doctorData.add(new DoctorModel("Dr. Arjun Shrestha ", " MBBS MS (Pediatric Surgery & Neonatal Surgen)"));
        doctorData.add(new DoctorModel("Dr. Rishave Shresthas ", " MS- General Surgery: Kathmandu University Hospital"));
        doctorData.add(new DoctorModel("Dr. Namarata Rai ", " MS- General Surgery: Kathmandu University Hospital"));
        doctorData.add(new DoctorModel("Dr. Sagar Shrestha ", " MBBS MS (Pediatric Surgery & Neonatal Surgen)"));
        doctorData.add(new DoctorModel("Dr. Anu Lama ", " MS- General Surgery: Kathmandu University Hospital"));
        doctorData.add(new DoctorModel("Dr. Raju Lama ", " MBBS MS (Pediatric Surgery & Neonatal Surgen"));

        doctorAdapter= new DoctorAdapter(this,doctorData);
        recyclerView.setAdapter(doctorAdapter);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        searchButton = findViewById(R.id.txtSearchDoctor);
        searchButton.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                doctorAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    private void setToolbar() {
        toolbardoctor = findViewById(R.id.toolbardoctor);
        setSupportActionBar(toolbardoctor);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back = findViewById(R.id.doctorBack);
        refesh = findViewById(R.id.doctorRefresh);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Doctors.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        refesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Doctors.this, " Refresh Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
