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

import API.PharmacyAPI;
import API.Url;
import Model.PharmacyModel;
import Model.ResponseFromAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPharmacy extends AppCompatActivity {
    private ImageView back;
    private Toolbar toolbar_addDoctor;
    private TextInputLayout txtInpute_pharmacy_name, txtInpute_pharmacy_address, txtInpute_pharmacy_contact,txtInpute_pharmacy_website;
    private TextInputEditText txt_pharmacy_name, txt_pharmacy_address, txt_pharmacy_contact,txt_pharmacy_website;
    private Button btn_addPharmacy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pharmacy);
        txtInpute_pharmacy_name = findViewById(R.id.txtInpute_pharmacy_name);
        txtInpute_pharmacy_contact = findViewById(R.id.txtInpute_pharmcy_contact);
        txtInpute_pharmacy_address = findViewById(R.id.txtInpute_pharmacy_address);
        txtInpute_pharmacy_website = findViewById(R.id.txtInpute_pharmacy_website);
        txt_pharmacy_name = findViewById(R.id.txt_pharmacy_name);
        txt_pharmacy_address = findViewById(R.id.txt_pharmacy_address);
        txt_pharmacy_website = findViewById(R.id.txt_pharmacy_website);
        txt_pharmacy_contact = findViewById(R.id.txt_pharmacy_contact);
        btn_addPharmacy = findViewById(R.id.btn_addPharmacy);
        setToolbar();

        btn_addPharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Validation()) {
                    PharmacyModel pharmacyModel = new PharmacyModel();
                    pharmacyModel.setNAME(txt_pharmacy_name.getText().toString().trim());
                    pharmacyModel.setADDRESS(txt_pharmacy_address.getText().toString().trim());
                    pharmacyModel.setCONTACT(txt_pharmacy_contact.getText().toString().trim());
                    pharmacyModel.setWEBSITE(txt_pharmacy_website.getText().toString().trim());
                    PharmacyAPI pharmacyAPI = Url.getInstance().create(PharmacyAPI.class);
                    Call<ResponseFromAPI> responseFromAPICall = pharmacyAPI.addPharamcy(Url.accessToken, pharmacyModel);

                    responseFromAPICall.enqueue(new Callback<ResponseFromAPI>() {
                        @Override
                        public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(AddPharmacy.this, response.code(), Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                if (response.body().getStatus()) {
                                    Toast.makeText(AddPharmacy.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    txt_pharmacy_name.setText(null);
                                    txt_pharmacy_address.setText(null);
                                    txt_pharmacy_contact.setText(null);
                                    txt_pharmacy_website.setText(null);
                                } else {
                                    Toast.makeText(AddPharmacy.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseFromAPI> call, Throwable t) {
                            Toast.makeText(AddPharmacy.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });


    }

    private void setToolbar() {
        toolbar_addDoctor = findViewById(R.id.toolbar_addPharmacy);
        setSupportActionBar(toolbar_addDoctor);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back = findViewById(R.id.add_DoctorBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPharmacy.this, AdminDashboard.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public Boolean Validation() {

        String name = txtInpute_pharmacy_name.getEditText().getText().toString().trim();
        String address = txtInpute_pharmacy_address.getEditText().getText().toString().trim();
        String contact = txtInpute_pharmacy_contact.getEditText().getText().toString().trim();
        String website = txtInpute_pharmacy_website.getEditText().getText().toString().trim();
        if (name.isEmpty()) {
            txtInpute_pharmacy_name.setError("First name Field cannot be empty");
            return false;
        } else if (name.length() < 2 || name.length() > 25) {
            txtInpute_pharmacy_name.setError("Doctor Name should be at least 3 character ");
            return false;
        } else if (address.isEmpty()) {
            txtInpute_pharmacy_name.setError(null);
            txtInpute_pharmacy_address.setError("Address Field cannot be empty");
            return false;
        } else if (address.length() < 5) {
            txtInpute_pharmacy_name.setError(null);
            txtInpute_pharmacy_address.setError("Address should be at least 5 character  ");
            return false;
        } else if (contact.isEmpty()) {
            txtInpute_pharmacy_name.setError(null);
            txtInpute_pharmacy_address.setError(null);
            txtInpute_pharmacy_contact.setError("Contact Field cannot be empty");
            return false;
        } else if (contact.length() < 10) {
            txtInpute_pharmacy_name.setError(null);
            txtInpute_pharmacy_address.setError(null);
            txtInpute_pharmacy_contact.setError("Please Enter valid Contact Number ");
            return false;
        } else if (website.isEmpty()) {
            txtInpute_pharmacy_name.setError(null);
            txtInpute_pharmacy_address.setError(null);
            txtInpute_pharmacy_contact.setError(null);
            txtInpute_pharmacy_website.setError("Wwbsite Field cannot be empty");
            return false;
        } else if (website.length() < 10) {
            txtInpute_pharmacy_name.setError(null);
            txtInpute_pharmacy_address.setError(null);
            txtInpute_pharmacy_contact.setError(null);
            txtInpute_pharmacy_website.setError("Please Enter valid website Number ");
            return false;
        } else {
            txtInpute_pharmacy_name.setError(null);
            txtInpute_pharmacy_address.setError(null);
            txtInpute_pharmacy_contact.setError(null);
            txtInpute_pharmacy_website.setError(null);
            return true;
        }

    }
}


