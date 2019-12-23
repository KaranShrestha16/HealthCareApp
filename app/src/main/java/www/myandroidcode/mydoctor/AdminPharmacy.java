package www.myandroidcode.mydoctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import API.PharmacyAPI;
import API.Url;
import Adapter.PharmacyAdapter;
import Model.PharmacyModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminPharmacy extends AppCompatActivity {
    private Toolbar toolbarPharmacy;
    private ImageView back;
    private RecyclerView recyclerView;
    private PharmacyAdapter pharmacyAdapter;
    private EditText txtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__admin_phamacy);

        setToolbar();
        recyclerView= findViewById(R.id.pharmacyRecycleView);

        PharmacyAPI pharmacyAPI = Url.getInstance().create(PharmacyAPI.class);
        Call<List<PharmacyModel>> pharmacyData= pharmacyAPI.getAll(Url.accessToken);
        pharmacyData.enqueue(new Callback<List<PharmacyModel>>() {
            @Override
            public void onResponse(Call<List<PharmacyModel>> call, Response<List<PharmacyModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(AdminPharmacy.this, "Error: "+ response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    List<PharmacyModel> pharmacyModelList= response.body();
                    pharmacyAdapter= new PharmacyAdapter(AdminPharmacy.this, pharmacyModelList);
                    recyclerView.setAdapter(pharmacyAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(AdminPharmacy.this));
                    txtSearch = findViewById(R.id.txt_search_pharmacy);
                    txtSearch.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            pharmacyAdapter.getFilter().filter(charSequence);
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });


                }
            }

            @Override
            public void onFailure(Call<List<PharmacyModel>> call, Throwable t) {
                Toast.makeText(AdminPharmacy.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });





    }

    private void setToolbar() {
        toolbarPharmacy= findViewById(R.id.toolbarPharmacy);
        setSupportActionBar(toolbarPharmacy);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back=findViewById(R.id.pharmacyBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AdminPharmacy.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }
}
