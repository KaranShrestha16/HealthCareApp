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

import java.util.ArrayList;
import java.util.List;

import Adapter.BloodBankAdapter;
import Model.BloodBankModel;


public class BloodBank extends AppCompatActivity {
    private Toolbar toolbarBloodBank;
    private ImageView back;
    private RecyclerView recyclerView;
    private BloodBankAdapter bloodBankAdapter;
    private List<BloodBankModel> bloodBankDate;
    private EditText txtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank);
        setToolbar();
        recyclerView= findViewById(R.id.bloodBankRecycleView);
        bloodBankDate= new ArrayList<>();
        bloodBankDate.add(new BloodBankModel("Annapurna Pharmacy P. Ltd. ","Maitighar, Kathmandu","9851091662","annapurnapharmacy.com"));
        bloodBankDate.add(new BloodBankModel("ATM Pharmacy ","Maitighar, Kathmandu","9851091662","annapurnapharmacy.com"));
        bloodBankDate.add(new BloodBankModel("RAJDHANI MEDICAL HALL","Kalimati-4, Kathmandu, Nepal","9801165986, 9861665986 ","www.atmpharmacy.com"));
        bloodBankDate.add(new BloodBankModel("Get Well Soon Nepal Pvt Ltd","Maitighar, Kathmandu","9851091662","annapurnapharmacy.com"));
        bloodBankDate.add(new BloodBankModel("G.M.P. Pharma","Maitighar, Kathmandu","9851091662","annapurnapharmacy.com"));
        bloodBankDate.add(new BloodBankModel("ROYALTRADERS","Kalimati-4, Kathmandu, Nepal","9801165986, 9861665986 ","www.atmpharmacy.com"));
        bloodBankDate.add(new BloodBankModel("OK Pharma - Online Pharmacy Pokhara","Maitighar, Kathmandu","9851091662","annapurnapharmacy.com"));
        bloodBankDate.add(new BloodBankModel("Rachana Orhto-Neuro Rehabilitation Center","Maitighar, Kathmandu","9851091662","annapurnapharmacy.com"));
        bloodBankDate.add(new BloodBankModel("Hemant Medicine Distributors","Kalimati-4, Kathmandu, Nepal","9801165986, 9861665986 ","www.atmpharmacy.com"));
        bloodBankDate.add(new BloodBankModel("Annapurna Pharmacy P. Ltd. ","Maitighar, Kathmandu","9851091662","annapurnapharmacy.com"));
        bloodBankDate.add(new BloodBankModel("ATM Pharmacy ","Maitighar, Kathmandu","9851091662","annapurnapharmacy.com"));
        bloodBankDate.add(new BloodBankModel("RAJDHANI MEDICAL HALL","Kalimati-4, Kathmandu, Nepal","9801165986, 9861665986 ","www.atmpharmacy.com"));

        bloodBankAdapter= new BloodBankAdapter(this, bloodBankDate);
        recyclerView.setAdapter(bloodBankAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


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
    private void setToolbar() {
        toolbarBloodBank= findViewById(R.id.toolbarBloodBank);
        setSupportActionBar(toolbarBloodBank);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back=findViewById(R.id.bloodBankBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(BloodBank.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

}
