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

import API.AmbulanceAPI;
import API.Url;
import Adapter.AmbulanceAdapter;
import Model.AmbulanceModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminAmbulance extends AppCompatActivity {
    private Toolbar toolbarAmbulance;
    private ImageView admin_addAmbulance,back;
    private RecyclerView recyclerView;
    private EditText searchButton;
    private AmbulanceAdapter ambulanceAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_ambulance);
        recyclerView= findViewById(R.id.bambulanceRecycleView_admin);
        setToolbar();
        LoadHospiyalData();
    }

    private void LoadHospiyalData() {
        final AmbulanceAPI ambulanceAPI= Url.getInstance().create(AmbulanceAPI.class);
        Call<List<AmbulanceModel>> ambulanceData = ambulanceAPI.getAll(Url.accessToken);
        ambulanceData.enqueue(new Callback<List<AmbulanceModel>>() {
            @Override
            public void onResponse(Call<List<AmbulanceModel>> call, Response<List<AmbulanceModel>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(AdminAmbulance.this, "Hospital Datat Cannot load on recyclearview: "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    List<AmbulanceModel> ambulanceModelList= response.body();
                    ambulanceAdapter= new AmbulanceAdapter(AdminAmbulance.this,ambulanceModelList);
                    recyclerView.setAdapter(ambulanceAdapter);
                    recyclerView.setLayoutManager( new LinearLayoutManager(AdminAmbulance.this));
                    searchButton = findViewById(R.id.txt_searchAmbulance_admin);
                    searchButton.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }
                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            ambulanceAdapter.getFilter().filter(charSequence);
                        }
                        @Override
                        public void afterTextChanged(Editable editable) {
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<AmbulanceModel>> call, Throwable t) {
                Toast.makeText(AdminAmbulance.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void setToolbar() {
        toolbarAmbulance= findViewById(R.id.toolbarAmbulance_admin);
        setSupportActionBar(toolbarAmbulance);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        admin_addAmbulance=findViewById(R.id.admin_addAmbulance);
        back=findViewById(R.id.ambulanceBack_admin);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AdminAmbulance.this,AdminDashboard.class);
                startActivity(intent);
                finish();
            }
        });

        admin_addAmbulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AdminAmbulance.this,AddAmbulance.class);
                startActivity(intent);
                finish();
            }
        });




    }
}
