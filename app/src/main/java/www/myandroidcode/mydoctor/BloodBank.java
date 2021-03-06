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

import API.BloodBankAPI;
import API.Url;
import Adapter.BloodBankAdapter;
import Model.BloodBankModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BloodBank extends AppCompatActivity {
    private Toolbar toolbarBloodBank;
    private ImageView back;
    private RecyclerView recyclerView;
    private BloodBankAdapter bloodBankAdapter;
    private EditText txtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank);
        setToolbar();
        recyclerView= findViewById(R.id.bloodBankRecycleView);

        BloodBankAPI bloodBankAPI = Url.getInstance().create(BloodBankAPI.class);
        Call<List<BloodBankModel>> bloodbankdate= bloodBankAPI.getAll(Url.accessToken);
        bloodbankdate.enqueue(new Callback<List<BloodBankModel>>() {
            @Override
            public void onResponse(Call<List<BloodBankModel>> call, Response<List<BloodBankModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(BloodBank.this, "Error: "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    List<BloodBankModel> bloodBankModelList= response.body();
                    bloodBankAdapter= new BloodBankAdapter(BloodBank.this, bloodBankModelList);
                    recyclerView.setAdapter(bloodBankAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(BloodBank.this));
                    txtSearch = findViewById(R.id.txt_search_bloodBnak);
                    txtSearch.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            bloodBankAdapter.getFilter().filter(charSequence);
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<List<BloodBankModel>> call, Throwable t) {
                Toast.makeText(BloodBank.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });








    }
    private void setToolbar() {
        toolbarBloodBank= findViewById(R.id.toolbarBloodBank);
        setSupportActionBar(toolbarBloodBank);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back=findViewById(R.id.bloodBankBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(BloodBank.this,AdminDashboard.class);
                startActivity(intent);
                finish();
            }
        });



    }

}
