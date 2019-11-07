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

import Adapter.HospitalAdapter;
import Model.HospitalModel;


public class Hospitals extends AppCompatActivity  {
    private Toolbar toolbarHospital;
    private ImageView back,refesh;
    private  RecyclerView recyclerView;
    private HospitalAdapter   hospitalAdapter;
    private List<HospitalModel> hospitalData;
    private EditText searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_hospitals);
//        getSupportActionBar().hide();

        setToolbar();
       recyclerView= findViewById(R.id.hospitalRecycleView);
       hospitalData= new ArrayList<>();

       hospitalData.add(new HospitalModel(1,"Bir Hospital ","9812345678","New Road, Kathmandu, Nepal"));
       hospitalData.add(new HospitalModel(2,"Grand International Hospital ","9812345678","New Road, Kathmandu, Nepal"));
       hospitalData.add(new HospitalModel(3,"Om International Hospital ","9812345678","New Road, Kathmandu, Nepal"));
       hospitalData.add(new HospitalModel(4,"Meridian Health Care Hospital ","9812345678","New Road, Kathmandu, Nepal"));
       hospitalData.add(new HospitalModel(5,"Dirghayu Guru Hospital ","9812345678","New Road, Kathmandu, Nepal"));
       hospitalData.add(new HospitalModel(6,"Metro Kathmandu Hospital ","9812345678","New Road, Kathmandu, Nepal"));
       hospitalData.add(new HospitalModel(7,"Kantipur Dental Collge Teaching  Hospital ","9812345678","New Road, Kathmandu, Nepal"));
       hospitalData.add(new HospitalModel(8,"Himal Dental Hospital ","9812345678","New Road, Kathmandu, Nepal"));
       hospitalData.add(new HospitalModel(9,"Kathmandu Model Hospital ","9812345678","New Road, Kathmandu, Nepal"));
       hospitalData.add(new HospitalModel(10,"B & B Hospital ","9812345678","New Road, Kathmandu, Nepal"));
       hospitalData.add(new HospitalModel(11,"Civil Service Hospital ","9812345678","New Road, Kathmandu, Nepal"));

       hospitalAdapter= new HospitalAdapter(this,hospitalData);
       recyclerView.setAdapter(hospitalAdapter);
       recyclerView.setLayoutManager( new LinearLayoutManager(this));


        searchButton = findViewById(R.id.hospitalSearchButton);
        searchButton.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                hospitalAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });















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
