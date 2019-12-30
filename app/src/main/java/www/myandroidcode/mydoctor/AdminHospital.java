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
import android.widget.Toast;

import java.util.List;

import API.HospitalAPI;
import API.Url;
import Adapter.AdminHospitalAdapter;
import Adapter.HospitalAdapter;
import Model.HospitalModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminHospital extends AppCompatActivity {
    private ImageView back, addHospital;
    private Toolbar toolbar_adminHospital;
    private RecyclerView recyclerView;
    private AdminHospitalAdapter adminHospitalAdapter;
    private EditText searchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_hospital);
        recyclerView= findViewById(R.id.admin_hospitalRecycleView);
        setToolbar();
        LoadHospiyalData();

    }

    private void setToolbar() {
        toolbar_adminHospital = findViewById(R.id.toolbar_adminHospital);
        setSupportActionBar(toolbar_adminHospital);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back = findViewById(R.id.admin_hospitalBack);
        addHospital = findViewById(R.id.admin_addHospital);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHospital.this, AdminDashboard.class);
                startActivity(intent);
                finish();
            }
        });

        addHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHospital.this, AddHospital.class);
                startActivity(intent);
                finish();
            }
        });

    }
    public void LoadHospiyalData() {
        HospitalAPI hospitalAPI = Url.getInstance().create(HospitalAPI.class);
        Call<List<HospitalModel>> hospitalData = hospitalAPI.getAll(Url.accessToken);
        hospitalData.enqueue(new Callback<List<HospitalModel>>() {
            @Override
            public void onResponse(Call<List<HospitalModel>> call, Response<List<HospitalModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(AdminHospital.this, "Hospital Datat Cannot load on recyclearview: "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    if(response.body().isEmpty()){
                        Toast.makeText(AdminHospital.this, "Hospital Data is empty", Toast.LENGTH_SHORT).show();
                    }else{
                        List<HospitalModel> hospitalModelList= response.body();
                        adminHospitalAdapter= new AdminHospitalAdapter(AdminHospital.this,hospitalModelList);
                        recyclerView.setAdapter(adminHospitalAdapter);
                        recyclerView.setLayoutManager( new LinearLayoutManager(AdminHospital.this));
                        searchButton = findViewById(R.id.admin_hospitalSearchButton);
                        searchButton.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                adminHospitalAdapter.getFilter().filter(charSequence);
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {

                            }
                        });

                    }
                }
            }

            @Override
            public void onFailure(Call<List<HospitalModel>> call, Throwable t) {
                Toast.makeText(AdminHospital.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }
}
