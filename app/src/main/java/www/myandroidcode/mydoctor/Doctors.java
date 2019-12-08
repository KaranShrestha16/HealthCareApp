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

import java.util.List;

import API.DoctorAPI;
import API.Url;


import Adapter.DoctorAdapterNext;
import Adapter.PharmacyAdapter;
import Model.DoctorModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Doctors extends AppCompatActivity {
    private Toolbar toolbardoctor;
    private ImageView back, refesh;
    private RecyclerView recyclerView;
    private EditText searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
        setToolbar();
        recyclerView= findViewById(R.id.recyclerView_doctor);

        DoctorAPI doctorAPI= Url.getInstance().create(DoctorAPI.class);
        Call<List<DoctorModel>> doctorListCall= doctorAPI.getAll(Url.accessToken);
        doctorListCall.enqueue(new Callback<List<DoctorModel>>() {
            @Override
            public void onResponse(Call<List<DoctorModel>> call, Response<List<DoctorModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Doctors.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    if (response.body().isEmpty()){
                        Toast.makeText(Doctors.this, "Doctor Data Not Found", Toast.LENGTH_SHORT).show();
                    }else{
                        List<DoctorModel> doctorModelsData= response.body();
                        final DoctorAdapterNext doctorAdapterNext= new DoctorAdapterNext(Doctors.this, doctorModelsData);
                        recyclerView.setAdapter(doctorAdapterNext);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Doctors.this));

                        searchButton = findViewById(R.id.txtSearchDoctor);
                        searchButton.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                doctorAdapterNext.getFilter().filter(charSequence);
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {

                            }
                        });


                    }
                }
            }

            @Override
            public void onFailure(Call<List<DoctorModel>> call, Throwable t) {
                Toast.makeText(Doctors.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
                Intent i = new Intent(Doctors.this, Doctors.class);
                finish();
                overridePendingTransition(0, 0);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

    }
}
