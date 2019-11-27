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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.PharmacyAdapter;
import Model.HospitalModel;
import Model.PharmacyModel;

public class Pharmacy extends AppCompatActivity {
    private Toolbar toolbarPharmacy;
    private ImageView back;
    private RecyclerView recyclerView;
    private PharmacyAdapter pharmacyAdapter;
    private List<PharmacyModel> pharmacyDate;
    private EditText txtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phamacy);

        setToolbar();
        recyclerView= findViewById(R.id.pharmacyRecycleView);
        pharmacyDate= new ArrayList<>();
        pharmacyDate.add(new PharmacyModel("Annapurna Pharmacy P. Ltd. ","Maitighar, Kathmandu","9851091662","annapurnapharmacy.com"));
        pharmacyDate.add(new PharmacyModel("ATM Pharmacy ","Maitighar, Kathmandu","9851091662","annapurnapharmacy.com"));
        pharmacyDate.add(new PharmacyModel("RAJDHANI MEDICAL HALL","Kalimati-4, Kathmandu, Nepal","9801165986, 9861665986 ","www.atmpharmacy.com"));
        pharmacyDate.add(new PharmacyModel("Get Well Soon Nepal Pvt Ltd","Maitighar, Kathmandu","9851091662","annapurnapharmacy.com"));
        pharmacyDate.add(new PharmacyModel("G.M.P. Pharma","Maitighar, Kathmandu","9851091662","annapurnapharmacy.com"));
        pharmacyDate.add(new PharmacyModel("ROYALTRADERS","Kalimati-4, Kathmandu, Nepal","9801165986, 9861665986 ","www.atmpharmacy.com"));
        pharmacyDate.add(new PharmacyModel("OK Pharma - Online Pharmacy Pokhara","Maitighar, Kathmandu","9851091662","annapurnapharmacy.com"));
        pharmacyDate.add(new PharmacyModel("Rachana Orhto-Neuro Rehabilitation Center","Maitighar, Kathmandu","9851091662","annapurnapharmacy.com"));
        pharmacyDate.add(new PharmacyModel("Hemant Medicine Distributors","Kalimati-4, Kathmandu, Nepal","9801165986, 9861665986 ","www.atmpharmacy.com"));
        pharmacyDate.add(new PharmacyModel("Annapurna Pharmacy P. Ltd. ","Maitighar, Kathmandu","9851091662","annapurnapharmacy.com"));
        pharmacyDate.add(new PharmacyModel("ATM Pharmacy ","Maitighar, Kathmandu","9851091662","annapurnapharmacy.com"));
        pharmacyDate.add(new PharmacyModel("RAJDHANI MEDICAL HALL","Kalimati-4, Kathmandu, Nepal","9801165986, 9861665986 ","www.atmpharmacy.com"));

        pharmacyAdapter= new PharmacyAdapter(this, pharmacyDate);
        recyclerView.setAdapter(pharmacyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


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

    private void setToolbar() {
        toolbarPharmacy= findViewById(R.id.toolbarPharmacy);
        setSupportActionBar(toolbarPharmacy);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back=findViewById(R.id.pharmacyBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Pharmacy.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }
}
