package www.myandroidcode.mydoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import API.HospitalAPI;
import API.Url;
import Adapter.DoctorAdapter;
import Model.DoctorModel;
import Model.Doctor_HospitalModel;
import Model.HospitalModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorWithDepartment extends AppCompatActivity {
    private ImageView back;
    private Toolbar toolbardoctor;
    private RecyclerView recyclerView;
    private DoctorAdapter adapter;
    private EditText searchButton;
    private TextView tv_toolbarTitle2,txt_message;
    private int hospital_id;
    private String department,toolbarTitle2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_with_department);
        setToolbar();
        recyclerView= findViewById(R.id.recyclerView_doctor_with_department);
        txt_message= findViewById(R.id.txt_message);
        tv_toolbarTitle2= findViewById(R.id.tv_toolbarTitle2);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            hospital_id= bundle.getInt("hospital_id");
            department= bundle.getString("department");
            toolbarTitle2= bundle.getString("toolbarTitle_for");
        }

        txt_message.setText(department+"  Doctor List");
        tv_toolbarTitle2.setText(toolbarTitle2);

        HospitalAPI hospitalAPI= Url.getInstance().create(HospitalAPI.class);
        Call<List<Doctor_HospitalModel>> doctorByHospitaList= hospitalAPI.getDoctorByHospitalId(Url.accessToken,department,hospital_id);

        doctorByHospitaList.enqueue(new Callback<List<Doctor_HospitalModel>>() {
            @Override
            public void onResponse(Call<List<Doctor_HospitalModel>> call, Response<List<Doctor_HospitalModel>> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(DoctorWithDepartment.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    List<Doctor_HospitalModel> doctor_hospitalModelsData= response.body();
                   if (doctor_hospitalModelsData.isEmpty()){
                       Toast.makeText(DoctorWithDepartment.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                   }else {
                       Log.d("Data", doctor_hospitalModelsData+"");
                        adapter= new  DoctorAdapter(DoctorWithDepartment.this, doctor_hospitalModelsData);
                       recyclerView.setAdapter(adapter);
                       recyclerView.setLayoutManager(new LinearLayoutManager(DoctorWithDepartment.this));

                    searchButton = findViewById(R.id.txt_search_doctor_with_department);
                    searchButton.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            adapter.getFilter().filter(charSequence);
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

            }
        });









    }

    private void setToolbar() {
        toolbardoctor = findViewById(R.id.toolbar_doctorWithDepartment);
        setSupportActionBar(toolbardoctor);
        toolbardoctor.setTitle(toolbarTitle2);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back = findViewById(R.id.doctorBack12);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorWithDepartment.this, Hospitals.class);
                startActivity(intent);
                finish();
            }
        });



    }

}
