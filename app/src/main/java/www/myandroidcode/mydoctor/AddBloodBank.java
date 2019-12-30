package www.myandroidcode.mydoctor;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import API.BloodBankAPI;
import API.PharmacyAPI;
import API.Url;
import Model.BloodBankModel;
import Model.PharmacyModel;
import Model.ResponseFromAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBloodBank extends AppCompatActivity {
    private ImageView back;
    private Toolbar toolbar_addBloodBank;
    private TextInputLayout txtInpute_bloodbank_name, txtInpute_bloodbank_address, txtInpute_bloodbank_contact;
    private TextInputEditText txt_bloodbank_name, txt_bloodbank_address, txt_bloodbank_contact;
    private Button btn_addBloodBank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_blood_bank);
        txtInpute_bloodbank_name = findViewById(R.id.txtInpute_bloodbank_name);
        txtInpute_bloodbank_contact = findViewById(R.id.txtInpute_bloodbank_contact);
        txtInpute_bloodbank_address = findViewById(R.id.txtInpute_bloodbank_address);
        txt_bloodbank_name = findViewById(R.id.txt_bloodbank_name);
        txt_bloodbank_address = findViewById(R.id.txt_bloodbank_address);
        txt_bloodbank_contact = findViewById(R.id.txt_bloodbank_contact);
        btn_addBloodBank = findViewById(R.id.btn_addBloodBankAdmin);
        setToolbar();

        btn_addBloodBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Validation()) {

                    BloodBankModel bloodBankModel = new BloodBankModel();
                    bloodBankModel.setNAME(txt_bloodbank_name.getText().toString().trim());
                    bloodBankModel.setADDRESS(txt_bloodbank_address.getText().toString().trim());
                    bloodBankModel.setCONTACT(txt_bloodbank_contact.getText().toString().trim());
                    BloodBankAPI bloodBankAPI = Url.getInstance().create(BloodBankAPI.class);
                    Call<ResponseFromAPI> responseFromAPICall = bloodBankAPI.addBloodBank(Url.accessToken, bloodBankModel);

                    responseFromAPICall.enqueue(new Callback<ResponseFromAPI>() {
                        @Override
                        public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(AddBloodBank.this, response.code(), Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                 if (response.body().getStatus()) {
                                    Toast.makeText(AddBloodBank.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    txt_bloodbank_name.setText(null);
                                    txt_bloodbank_address.setText(null);
                                    txt_bloodbank_contact.setText(null);
                                } else {
                                    Toast.makeText(AddBloodBank.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseFromAPI> call, Throwable t) {
                            Toast.makeText(AddBloodBank.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });


    }

    private void setToolbar() {
        toolbar_addBloodBank = findViewById(R.id.toolbar_addBloodBank);
        setSupportActionBar(toolbar_addBloodBank);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back = findViewById(R.id.add_BloodBankBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddBloodBank.this, AdminDashboard.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public Boolean Validation() {

        String name = txtInpute_bloodbank_name.getEditText().getText().toString().trim();
        String address = txtInpute_bloodbank_address.getEditText().getText().toString().trim();
        String contact = txtInpute_bloodbank_contact.getEditText().getText().toString().trim();
        if (name.isEmpty()) {
            txtInpute_bloodbank_name.setError("First name Field cannot be empty");
            return false;
        } else if (name.length() < 2 || name.length() > 25) {
            txtInpute_bloodbank_name.setError("Doctor Name should be at least 3 character ");
            return false;
        } else if (address.isEmpty()) {
            txtInpute_bloodbank_name.setError(null);
            txtInpute_bloodbank_address.setError("Address Field cannot be empty");
            return false;
        } else if (address.length() < 5) {
            txtInpute_bloodbank_name.setError(null);
            txtInpute_bloodbank_address.setError("Address should be at least 5 character  ");
            return false;
        } else if (contact.isEmpty()) {
            txtInpute_bloodbank_name.setError(null);
            txtInpute_bloodbank_address.setError(null);
            txtInpute_bloodbank_contact.setError("Contact Field cannot be empty");
            return false;
        } else if (contact.length() < 10) {
            txtInpute_bloodbank_name.setError(null);
            txtInpute_bloodbank_address.setError(null);
            txtInpute_bloodbank_contact.setError("Please Enter valid Contact Number ");
            return false;
        }  else {
            txtInpute_bloodbank_name.setError(null);
            txtInpute_bloodbank_address.setError(null);
            txtInpute_bloodbank_contact.setError(null);
            return true;
        }

    }
}


