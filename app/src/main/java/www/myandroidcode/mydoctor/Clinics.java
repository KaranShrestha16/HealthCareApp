package www.myandroidcode.mydoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.ClinicAdapter;
import Adapter.HospitalAdapter;
import Model.ClinicModel;
import Model.HospitalModel;

public class Clinics extends AppCompatActivity {
    private Toolbar toolbarClinic;
    private ImageView back,refesh;
    private RecyclerView recyclerView;
    private ClinicAdapter clinicAdapter;
    private List<ClinicModel> clinicData;
    private EditText searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinics);
        setToolbar();

        recyclerView= findViewById(R.id.recyclerView_clinic);
        clinicData= new ArrayList<>();

        clinicData.add(new ClinicModel(1,"Simple Plus Dental Clinic ","9812345678","New Road, Kathmandu, Nepal"));
        clinicData.add(new ClinicModel(2,"Healthy Smiles","9812345678","New Road, Kathmandu, Nepal"));
        clinicData.add(new ClinicModel(4," Boudha Polyclinic ","9812345678","New Road, Kathmandu, Nepal"));
        clinicData.add(new ClinicModel(5," Namaste Nepal Medical Center ","9812345678","New Road, Kathmandu, Nepal"));
        clinicData.add(new ClinicModel(6,"Spark Polyclinic ","9812345678","New Road, Kathmandu, Nepal"));
        clinicData.add(new ClinicModel(7,"Kantipur Dental Collge Teaching  Hospital ","9812345678","New Road, Kathmandu, Nepal"));
        clinicData.add(new ClinicModel(8,"Himal Dental Hospital ","9812345678","New Road, Kathmandu, Nepal"));
        clinicData.add(new ClinicModel(9,"DENTAL CARE HOSPITAL PVT. LTD","9812345678","New Road, Kathmandu, Nepal"));
        clinicData.add(new ClinicModel(10,"Boudha Polyclinic","9812345678","New Road, Kathmandu, Nepal"));
        clinicData.add(new ClinicModel(11,"Himal Dental Hospital","9812345678","New Road, Kathmandu, Nepal"));

        clinicAdapter= new ClinicAdapter(this,clinicData);
        recyclerView.setAdapter(clinicAdapter);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));


        searchButton = findViewById(R.id.txtSearchClinic);
        searchButton.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                clinicAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });












    }

    private void setToolbar() {
        toolbarClinic= findViewById(R.id.toolbarClinic);
        setSupportActionBar(toolbarClinic);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back=findViewById(R.id.clinicBack);
        refesh=findViewById(R.id.clinicRefresh);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Clinics.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        refesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Clinics.this, " Refresh Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }


}


