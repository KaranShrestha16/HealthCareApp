package www.myandroidcode.mydoctor;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Pattern;

import API.PatientAPI;
import Model.PatientModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import API.Url;
import Model.ResponseFromAPI;


public class Registration extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private EditText txtBirthDate;
    private TextView tv_cancle;
    private Button btn_save;
    private TextInputLayout txt_fname,txt_lname,txt_address,txt_contact, txt_birthdate,txt_email, txt_password,txt_re_password,txt_BirthDate;
    private static final Pattern PASSWORD_PATTERN= Pattern.compile("^"+"(?=.*[0-9])"+ "(?=.*[a-z])" +"(?=.*[A-Z])"+"(?=.*[@#$%^&+=])"+"(?=\\S+$)"+".{8,}"+"$");
    private RadioGroup rdGender;
    private RadioButton rbGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        txtBirthDate=findViewById(R.id.txtBirthDate);
        btn_save=findViewById(R.id.btn_save_registration);
        tv_cancle=findViewById(R.id.tv_cancle_registration);
        txt_fname=findViewById(R.id.txtFirstname);
        txt_lname=findViewById(R.id.txtLastname);
        txt_address=findViewById(R.id.txtAddress);
        txt_contact=findViewById(R.id.txtcontact);
        txt_birthdate=findViewById(R.id.txt_BirthDate);
        txt_email=findViewById(R.id.txtEmail);
        txt_password=findViewById(R.id.txtPassword);
        txt_re_password=findViewById(R.id.txtRePassword);
        rdGender = findViewById(R.id.radioGender);


        txtBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Birthdate",txt_birthdate.getEditText().getText().toString().trim());

              if(Validation()){
                  String name= txt_fname.getEditText().getText().toString().trim()+" "+ txt_lname.getEditText().getText().toString().trim();
                  int selectedId = rdGender.getCheckedRadioButtonId();
                  rbGender = findViewById(selectedId);
                  String gender = rbGender.getText().toString().trim();

                  PatientAPI patientAPI= Url.getInstance().create(API.PatientAPI.class);
                  PatientModel patientModel= new PatientModel();
                  patientModel.setNAME(name);
                  patientModel.setADDRESS(txt_address.getEditText().getText().toString().trim());
                  patientModel.setGENDER(gender);
                  patientModel.setCONTACT(txt_contact.getEditText().getText().toString().trim());
                  patientModel.setBIRTHDATE(txt_birthdate.getEditText().getText().toString().trim());
                  patientModel.setEMAIL(txt_email.getEditText().getText().toString().trim());
                  patientModel.setPASSWORD(txt_password.getEditText().getText().toString().trim());
                  patientModel.setIMAGE(" ");
                  patientModel.setBLOOD_GROUP("null");


                  final Call<ResponseFromAPI> registerPatient = patientAPI.patientRegistration(patientModel);
                  registerPatient.enqueue(new Callback<ResponseFromAPI>() {
                      @Override
                      public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
                          if(!response.isSuccessful()){
                              Toast.makeText(Registration.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                              txt_email.setError("Please enter new email");
                          }else {
                              if (response.body().getStatus()) {
                                  Intent intent = new Intent(Registration.this, Login.class);
                                  Toast.makeText(Registration.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                  startActivity(intent);
                                  finish();
                              }else {
                                  Toast.makeText(Registration.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                              }
                          }
                      }

                      @Override
                      public void onFailure(Call<ResponseFromAPI> call, Throwable t) {
                          Toast.makeText(Registration.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                      }
                  });




              }
            }
        });

        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Registration.this,Login.class);
                startActivity(intent);
                finish();
            }
        });





    }

    public void DatePicker(){
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,this,year,month,day);
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        txtBirthDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
    }

    public Boolean Validation(){

        String fname= txt_fname.getEditText().getText().toString().trim();
        String lname= txt_lname.getEditText().getText().toString().trim();
        String address= txt_address.getEditText().getText().toString().trim();
        String contact= txt_contact.getEditText().getText().toString().trim();
        String birthdate= txt_birthdate.getEditText().getText().toString().trim();
        String email= txt_email.getEditText().getText().toString().trim();
        String password= txt_password.getEditText().getText().toString().trim();
        String repassword= txt_re_password.getEditText().getText().toString().trim();

        if(fname.isEmpty()){
            txt_fname.setError("First name Field cannot be empty");
            return false;
        }else if(fname.length()< 2|| fname.length()> 15){
            txt_fname.setError("First Name should be at least 3 character ");
            return false;
        }else if(lname.isEmpty()){
            txt_fname.setError(null);
            txt_lname.setError("Last name Field cannot be empty");
            return false;
        }else if(lname.length()< 2){

            txt_fname.setError(null);
            txt_lname.setError("Last Name should be at least 3 character ");
            return false;
        }else if(address.isEmpty()){
            txt_fname.setError(null);
            txt_lname.setError(null);
            txt_address.setError("Address Field cannot be empty");
            return false;
        }else if(address.length()< 5){
            txt_fname.setError(null);
            txt_lname.setError(null);
            txt_address.setError("Address should be at least 5 character  ");
            return false;
        }else if(contact.isEmpty()){
            txt_fname.setError(null);
            txt_address.setError(null);
            txt_contact.setError("Contact Field cannot be empty");
            return false;
        }else if(contact.length()< 10){
            txt_fname.setError(null);
            txt_address.setError(null);
            txt_contact.setError("Please Enter valid Contact Number ");
            return false;
        }else if(birthdate.isEmpty()){
            txt_fname.setError(null);
            txt_contact.setError(null);
            txt_birthdate.setError("BirthDate Field cannot be empty");
            return false;
        }else if(email.isEmpty()){
            txt_fname.setError(null);
            txt_birthdate.setError(null);
            txt_email.setError("Email Field cannot be empty");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txt_birthdate.setError(null);
            txt_fname.setError(null);
            txt_email.setError("Please enter valid email address");
            return false;
        }else if(password.isEmpty()){
            txt_email.setError(null);
            txt_fname.setError(null);
            txt_password.setError("Password Field cannot be empty");
            return false;
        }else if(password.length()<8) {
            txt_email.setError(null);
            txt_fname.setError(null);
            txt_password.setError("Password must at lest 8 charecter long");
            return false;
        }else  if(!PASSWORD_PATTERN.matcher(password).matches()) {
            txt_email.setError(null);
            txt_password.setError("Password must include symbole,number, lower and upper case");
            return false;
        }else if(repassword.isEmpty()){
            txt_password.setError(null);
            txt_fname.setError(null);
            txt_re_password.setError("Password Do not Match");
            return false;
        }else if(!repassword.equals(password)){
            txt_password.setError(null);
            txt_fname.setError(null);
            txt_re_password.setError("Password Do not Match");
            return false;
        }else {
            txt_fname.setError(null);
            txt_lname.setError(null);
            txt_address.setError(null);
            txt_contact.setError(null);
            txt_birthdate.setError(null);
            txt_email.setError(null);
            txt_password.setError(null);
            txt_re_password.setError(null);
            return  true;

        }




    }


}
