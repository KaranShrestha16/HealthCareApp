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

import API.AmbulanceAPI;
import API.PharmacyAPI;
import API.Url;
import Model.AmbulanceModel;
import Model.PharmacyModel;
import Model.ResponseFromAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAmbulance extends AppCompatActivity {
    private ImageView back;
    private Toolbar toolbar_addAmbulance;
    private TextInputLayout txtInpute_ambulance_name, txtInpute_ambulance_address, txtInpute_ambulance_contact;
    private TextInputEditText txt_ambulance_name, txt_ambulance_address, txt_ambulance_contact;
    private Button btn_addAmbulance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ambulance);
        txtInpute_ambulance_name = findViewById(R.id.txtInpute_ambulance_name);
        txtInpute_ambulance_contact = findViewById(R.id.txtInpute_ambulance_contact);
        txtInpute_ambulance_address = findViewById(R.id.txtInpute_ambulance_address);
        txt_ambulance_name = findViewById(R.id.txt_ambulance_name);
        txt_ambulance_address = findViewById(R.id.txt_ambulance_address);
        txt_ambulance_contact = findViewById(R.id.txt_ambulance_contact);
        btn_addAmbulance = findViewById(R.id.btn_addAmbulance);
        setToolbar();

        btn_addAmbulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Validation()) {
                  AmbulanceModel ambulanceModel = new AmbulanceModel();
                    ambulanceModel.setNAME(txt_ambulance_name.getText().toString().trim());
                    ambulanceModel.setADDRESS(txt_ambulance_address.getText().toString().trim());
                    ambulanceModel.setCONTACT(txt_ambulance_contact.getText().toString().trim());
                    AmbulanceAPI ambulanceAPI = Url.getInstance().create(AmbulanceAPI.class);
                    Call<ResponseFromAPI> responseFromAPICall = ambulanceAPI.addAmbulance(Url.accessToken, ambulanceModel);

                    responseFromAPICall.enqueue(new Callback<ResponseFromAPI>() {
                        @Override
                        public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(AddAmbulance.this, response.code(), Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                if (response.body().getStatus()) {
                                    Toast.makeText(AddAmbulance.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    txt_ambulance_name.setText(null);
                                    txt_ambulance_address.setText(null);
                                    txt_ambulance_contact.setText(null);
                                } else {
                                    Toast.makeText(AddAmbulance.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseFromAPI> call, Throwable t) {
                            Toast.makeText(AddAmbulance.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });


    }

    private void setToolbar() {
        toolbar_addAmbulance = findViewById(R.id.toolbar_addAmbulance);
        setSupportActionBar(toolbar_addAmbulance);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back = findViewById(R.id.add_AmbulanceBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAmbulance.this, AdminDashboard.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public Boolean Validation() {

        String name = txtInpute_ambulance_name.getEditText().getText().toString().trim();
        String address = txtInpute_ambulance_address.getEditText().getText().toString().trim();
        String contact = txtInpute_ambulance_contact.getEditText().getText().toString().trim();


        if (name.isEmpty()) {
            txtInpute_ambulance_name.setError("First name Field cannot be empty");
            return false;
        } else if (name.length() < 2 || name.length() > 25) {
            txtInpute_ambulance_name.setError("Doctor Name should be at least 3 character ");
            return false;
        } else if (address.isEmpty()) {
            txtInpute_ambulance_name.setError(null);
            txtInpute_ambulance_address.setError("Address Field cannot be empty");
            return false;
        } else if (address.length() < 5) {
            txtInpute_ambulance_name.setError(null);
            txtInpute_ambulance_address.setError("Address should be at least 5 character  ");
            return false;
        } else if (contact.isEmpty()) {
            txtInpute_ambulance_name.setError(null);
            txtInpute_ambulance_address.setError(null);
            txtInpute_ambulance_contact.setError("Contact Field cannot be empty");
            return false;
        } else if (contact.length() < 10) {
            txtInpute_ambulance_name.setError(null);
            txtInpute_ambulance_address.setError(null);
            txtInpute_ambulance_contact.setError("Please Enter valid Contact Number ");
            return false;
        } else {
            txtInpute_ambulance_name.setError(null);
            txtInpute_ambulance_address.setError(null);
            txtInpute_ambulance_contact.setError(null);
            return true;
        }

    }
}


