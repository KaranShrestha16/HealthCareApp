package www.myandroidcode.mydoctor;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import API.DoctorAPI;
import API.HospitalAPI;
import API.Url;
import Adapter.AdminDoctorAdapter;
import Adapter.DoctorAdapterNext;
import Model.DoctorModel;
import Model.Doctor_HospitalModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminDoctor extends AppCompatActivity {
    private ImageView back, addDoctor;
    private Toolbar toolbar_adminDoctor;
    private RecyclerView recyclerView;
    private EditText searchButton;
    private int hospital_id;
    private String toolbarTitle2;
    private TextView tv_toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_doctor);
        recyclerView= findViewById(R.id.Admin_DcotorRecycleView);
        tv_toolbar_title= findViewById(R.id.tv_toolbar_title);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            hospital_id= bundle.getInt("hospital_id12");
            toolbarTitle2= bundle.getString("hospital_name12");
        }
        setToolbar();
        HospitalAPI hospitalAPI= Url.getInstance().create(HospitalAPI.class);
        Call<List<Doctor_HospitalModel>> doctorListCall= hospitalAPI.getDoctorById(Url.accessToken,hospital_id);
        doctorListCall.enqueue(new Callback<List<Doctor_HospitalModel>>() {
            @Override
            public void onResponse(Call<List<Doctor_HospitalModel>> call, Response<List<Doctor_HospitalModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(AdminDoctor.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    if (response.body().isEmpty()){
                        Toast.makeText(AdminDoctor.this, "No Doctor Added", Toast.LENGTH_SHORT).show();
                    }else{
                        List<Doctor_HospitalModel> doctorModelsData= response.body();
                        final AdminDoctorAdapter adminDoctorAdapter= new AdminDoctorAdapter(AdminDoctor.this, doctorModelsData);
                        recyclerView.setAdapter(adminDoctorAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(AdminDoctor.this));

                        searchButton = findViewById(R.id.hospitalSearchButton);
                        searchButton.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                adminDoctorAdapter.getFilter().filter(charSequence);
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {

                            }
                        });


                    }
                }
            }

            @Override
            public void onFailure(Call<List<Doctor_HospitalModel>> call, Throwable t) {
                Toast.makeText(AdminDoctor.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void setToolbar() {
        toolbar_adminDoctor = findViewById(R.id.toolbar_adminDoctor);
        setSupportActionBar(toolbar_adminDoctor);
        tv_toolbar_title.setText(toolbarTitle2);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back = findViewById(R.id.admin_DoctorBack);
        addDoctor = findViewById(R.id.admin_addDoctor);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDoctor.this, AdminDashboard.class);
                startActivity(intent);
                finish();
            }
        });

        addDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDoctor.this, AddDoctor.class);
                intent.putExtra("hospital_id13",hospital_id);
                startActivity(intent);
                finish();
            }
        });

    }













}
