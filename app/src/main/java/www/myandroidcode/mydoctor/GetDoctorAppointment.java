package www.myandroidcode.mydoctor;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import API.PatientAPI;
import API.Url;
import Model.AppointmentModel;
import Model.ResponseFromAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDoctorAppointment extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private ImageView back, doctor_image;
    private Toolbar toolbar;
    private TextView tv_doctorName, picdate,showdate, department;
    private TextInputEditText symptoms;
    private TextInputLayout text_symptoms;
    private Button btn_appointment;
    private int hospital_id, doctor_id;
    private String toolbarTitle2,dcotorName,departmentName,doctorImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_doctor_appointment);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            hospital_id = bundle.getInt("hospitalid_toAappointment");
            doctor_id = bundle.getInt("doctorid_toAappointment");
            toolbarTitle2 = bundle.getString("toolbarTitle_toAappointment");
            dcotorName = bundle.getString("doctor_name");
            departmentName = bundle.getString("department_doctor");
            doctorImage = bundle.getString("doctor_imagees");
        }


        Log.d("Hero", hospital_id + "-----" + doctor_id + toolbarTitle2);

        doctor_image = findViewById(R.id.image_doctor_appointment);
        tv_doctorName = findViewById(R.id.tv_doctorName_appointment);
        department = findViewById(R.id.tv_department_appointment);
        picdate = findViewById(R.id.text_appointmentDate);
        showdate = findViewById(R.id.text_showDate);
        symptoms = findViewById(R.id.text_symptoms);
        text_symptoms = findViewById(R.id.txt_symptoms);
        btn_appointment = findViewById(R.id.btn_getAppointment);
        setToolbar();
        toolbar.setTitle(toolbarTitle2);

        Log.d("Dcctor",dcotorName+"" );

        tv_doctorName.setText("Doctor:  "+dcotorName);
        department.setText(departmentName);

        String imgpath = Url.BASE_URL + doctorImage;
        StrictMode();
        try{
            URL url = new URL(imgpath);
            doctor_image.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        }catch (IOException e) {
            e.printStackTrace();
        }


        picdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker();
            }
        });

        btn_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              if(Validation()){

                  AppointmentModel appointmentModel = new AppointmentModel();
                  appointmentModel.setHOSPITAL_ID(hospital_id);
                  appointmentModel.setDOCTOR_ID(doctor_id);
                  appointmentModel.setPATIENT_ID(Url.id);
                  appointmentModel.setCURRENT_SYMPTOMS(symptoms.getText().toString().trim());
                  appointmentModel.setAPPOINTMENT_DATE(showdate.getText().toString().trim());
                  appointmentModel.setPATIENT_ID(Url.id);
                  PatientAPI patientAPI = Url.getInstance().create(PatientAPI.class);
                  Call<ResponseFromAPI>  responseFromAPICall= patientAPI.getDoctorAppointment(Url.accessToken,appointmentModel);
                  responseFromAPICall.enqueue(new Callback<ResponseFromAPI>() {
                      @Override
                      public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
                          if(!response.isSuccessful()){
                              Toast.makeText(GetDoctorAppointment.this, response.code(), Toast.LENGTH_SHORT).show();
                              return;
                          }else{
                              if(response.body().getStatus()){
                                  Toast.makeText(GetDoctorAppointment.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                              }else {
                                  Toast.makeText(GetDoctorAppointment.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                              }


                              symptoms.setText("");
                              showdate.setText("");



                          }
                      }

                      @Override
                      public void onFailure(Call<ResponseFromAPI> call, Throwable t) {
                          Toast.makeText(GetDoctorAppointment.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                      }
                  });
              }

            }
        });



    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar_doctorWithDepartment);
        setSupportActionBar(toolbar);
        toolbar.setTitle(toolbarTitle2);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back = findViewById(R.id.doctorBackAppointment);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetDoctorAppointment.this, Hospitals.class);
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
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        showdate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
    }



    public Boolean Validation(){

       String txt_symptoms= symptoms.getText().toString().trim();
       String date= showdate.getText().toString().trim();

       if(txt_symptoms.isEmpty()){
           text_symptoms.setError("Please enter your symptoms ");
           return  false;
       }else if(date.isEmpty()){
           text_symptoms.setError(null);
           showdate.setText("Please Select date for appointment ");
           return  false;
       }
        text_symptoms.setError(null);
        return  true;
    }
    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }



}
