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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import API.HospitalAPI;
import API.Url;
import Adapter.HospitalAdapter;
import Model.HospitalModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Hospitals extends AppCompatActivity  {
    private Toolbar toolbarHospital;
    private ImageView back,refesh;
    private  RecyclerView recyclerView;
    private HospitalAdapter   hospitalAdapter;
    private EditText searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);
        recyclerView= findViewById(R.id.hospitalRecycleView);


        setToolbar();
        LoadHospiyalData();





    }

    public void LoadHospiyalData() {

        final HospitalAPI hospitalAPI= Url.getInstance().create(HospitalAPI.class);
        Call<List<HospitalModel>> hospitalData = hospitalAPI.getAll(Url.accessToken);
        hospitalData.enqueue(new Callback<List<HospitalModel>>() {
            @Override
            public void onResponse(Call<List<HospitalModel>> call, Response<List<HospitalModel>> response) {
               if (!response.isSuccessful()){
                   Toast.makeText(Hospitals.this, "Hospital Datat Cannot load on recyclearview: "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
               }else {
                   List<HospitalModel> hospitalModelList= response.body();
                   hospitalAdapter= new HospitalAdapter(Hospitals.this,hospitalModelList);
                   recyclerView.setAdapter(hospitalAdapter);
                   recyclerView.setLayoutManager( new LinearLayoutManager(Hospitals.this));
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

            }

            @Override
            public void onFailure(Call<List<HospitalModel>> call, Throwable t) {

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
