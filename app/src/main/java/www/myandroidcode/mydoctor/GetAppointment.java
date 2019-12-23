package www.myandroidcode.mydoctor;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import API.DoctorAPI;
import API.PatientAPI;
import API.Url;
import Model.AppointmentModel;
import Model.DoctorModel;
import Model.Doctor_HospitalModel;
import Model.ResponseFromAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class GetAppointment extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {
    private ImageView back, tv_doctor_image;
    private Toolbar toolbar;
    private TextView tv_doctorName, date, department, show;
    private TextInputEditText symptoms;
    private TextInputLayout text_symptoms;
    private Button btn_appointment;
    private int doctor_id;
    private Spinner spinner_hospital_name;
    private List<Integer> hospital_idList;
    private List<String> hospital_nameList;
    private int hospital_id;
    private String spinnerValue,hospital_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_appointment);
        tv_doctor_image = findViewById(R.id.image_doctor_appointmentNext);
        tv_doctorName = findViewById(R.id.tv_doctorName_appointmentNext);
        department = findViewById(R.id.tv_department_appointmentNext);
        date = findViewById(R.id.text_appointmentDateNext);
        symptoms = findViewById(R.id.text_symptomsNext);
        text_symptoms = findViewById(R.id.txt_symptomsNext);
        btn_appointment = findViewById(R.id.btn_getAppointmentNext);
        show = findViewById(R.id.text_showDateNext);
        spinner_hospital_name = findViewById(R.id.spinner_hospital_name);
        setToolbar();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            doctor_id = bundle.getInt("doctot_id12");
        }
        hospital_idList = new ArrayList<>();
        hospital_nameList= new ArrayList<String>();
        final DoctorAPI doctorAPI = Url.getInstance().create(DoctorAPI.class);

        Call<DoctorModel> doctorModelCall = doctorAPI.getById(Url.accessToken, doctor_id);
        doctorModelCall.enqueue(new Callback<DoctorModel>() {
            @Override
            public void onResponse(Call<DoctorModel> call, Response<DoctorModel> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(GetAppointment.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    String imgpath = Url.BASE_URL + response.body().getDOCTOR_IMAGE();
                    StrictMode();
                    try {
                        URL url = new URL(imgpath);
                        tv_doctor_image.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    tv_doctorName.setText(response.body().getDOCTOR_NAME());
                    department.setText(response.body().getQUALIFICATION());


                }

            }

            @Override
            public void onFailure(Call<DoctorModel> call, Throwable t) {
                Toast.makeText(GetAppointment.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Call<List<Doctor_HospitalModel>> doctorHospitalList = doctorAPI.getDoctorByHospitalId(Url.accessToken, doctor_id);
        doctorHospitalList.enqueue(new Callback<List<Doctor_HospitalModel>>() {
            @Override
            public void onResponse(Call<List<Doctor_HospitalModel>> call, Response<List<Doctor_HospitalModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(GetAppointment.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (response.body().isEmpty()) {
                        Toast.makeText(GetAppointment.this, "Doctor Data is not found", Toast.LENGTH_SHORT).show();
                    } else {
                        List<Doctor_HospitalModel> doctor_hospitalModelsData = response.body();
                        for (Doctor_HospitalModel str : doctor_hospitalModelsData) {
                            hospital_nameList.add(str.getHOSPITAL_NAME());
                            hospital_idList.add(str.getHOSPITAL_ID());

                        }

                        ArrayAdapter arrayAdapterSpinner = new ArrayAdapter(getApplication(), android.R.layout.simple_spinner_item, hospital_nameList);
                        arrayAdapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_hospital_name.setAdapter(arrayAdapterSpinner);







                    }
                }
            }

            @Override
            public void onFailure(Call<List<Doctor_HospitalModel>> call, Throwable t) {
                Toast.makeText(GetAppointment.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        spinner_hospital_name.setOnItemSelectedListener(this);





        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker();
            }
        });

        btn_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Validation()) {
                    Toast.makeText(GetAppointment.this, show.getText()+"ggg", Toast.LENGTH_SHORT).show();
                    AppointmentModel appointmentModel = new AppointmentModel();
                    appointmentModel.setHOSPITAL_ID(hospital_id);
                    appointmentModel.setDOCTOR_ID(doctor_id);
                    appointmentModel.setPATIENT_ID(Url.id);
                    appointmentModel.setCURRENT_SYMPTOMS(symptoms.getText().toString().trim());
                    appointmentModel.setAPPOINTMENT_DATE(show.getText().toString().trim());
                    appointmentModel.setPATIENT_ID(Url.id);
                    PatientAPI patientAPI = Url.getInstance().create(PatientAPI.class);
                    Call<ResponseFromAPI>  responseFromAPICall= patientAPI.getDoctorAppointment(Url.accessToken,appointmentModel);
                    responseFromAPICall.enqueue(new Callback<ResponseFromAPI>() {
                        @Override
                        public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(GetAppointment.this, response.code(), Toast.LENGTH_SHORT).show();
                                return;
                            }else{
                                if(response.body().getStatus()){
                                    Toast.makeText(GetAppointment.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    Intent intent= new Intent(GetAppointment.this, MainActivity.class);
                                    startActivity(intent);

                                }else {
                                    Toast.makeText(GetAppointment.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                symptoms.setText("");
                                show.setText("");

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseFromAPI> call, Throwable t) {
                            Toast.makeText(GetAppointment.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });



    }



    private void setToolbar() {
        toolbar = findViewById(R.id.toolbarGetAppointment);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back = findViewById(R.id.getAppointmentBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetAppointment.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public void DatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        show.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
    }

    public Boolean Validation() {

        String txt_symptoms = symptoms.getText().toString().trim();
        String date = show.getText().toString().trim();

        if (txt_symptoms.isEmpty()) {
            text_symptoms.setError("Please enter your symptoms ");
            return false;
        } else if (date.isEmpty()) {
            text_symptoms.setError(null);
            show.setText("Please Select date for appointment ");
            return false;
        }
        text_symptoms.setError(null);
        return true;
    }

    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        hospital_id = hospital_idList.get((int) adapterView.getItemIdAtPosition(i));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
