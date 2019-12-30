package www.myandroidcode.mydoctor;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.List;

import API.DoctorAPI;
import API.PharmacyAPI;
import API.Url;
import Model.DoctorModel;
import Model.Doctor_HospitalModel;
import Model.Hospital_DoctorModel;
import Model.ResponseFromAPI;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDoctor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ImageView back, img_doctor_addDoctor;
    private Toolbar toolbar_addDoctor;
    private TextInputLayout txtInpute_adddoctor_name, txtInpute_adddoctor_address, txtInpute_adddoctor_contact, txtInpute_adddoctor_qulification;
    private TextInputEditText txt_adddoctor_name, txt_adddoctor_address, txt_adddoctor_contact, txt_adddoctor_qualification;
    private RadioGroup rdGender;
    private RadioButton rbGender;
    private Spinner spinner_department;
    private Button btn_addDoctor, btn_addImage_doctor;
    private String imageName, department, imagePath;
    private int hospital_id;
    private String[] departmentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);

        txtInpute_adddoctor_name = findViewById(R.id.txtInpute_adddoctor_name);
        txtInpute_adddoctor_address = findViewById(R.id.txtInpute_adddoctor_address);
        txtInpute_adddoctor_contact = findViewById(R.id.txtInpute_adddoctor_contact);
        txtInpute_adddoctor_qulification = findViewById(R.id.txtInpute_adddoctor_qulification);
        txt_adddoctor_name = findViewById(R.id.txt_adddoctor_name);
        txt_adddoctor_address = findViewById(R.id.txt_adddoctor_address);
        txt_adddoctor_contact = findViewById(R.id.txt_adddoctor_contact);
        txt_adddoctor_qualification = findViewById(R.id.txt_adddoctor_qualification);
        btn_addDoctor = findViewById(R.id.btn_addDoctor);
        img_doctor_addDoctor = findViewById(R.id.img_doctor_addDoctor);
        btn_addImage_doctor = findViewById(R.id.btn_addImage_doctor);
        spinner_department = findViewById(R.id.spinner_department);
        rdGender = findViewById(R.id.radioGenderadmin);
        imageName = "myImage-1575685501226.png";

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            hospital_id = bundle.getInt("hospital_id13");
        }

        setToolbar();

        departmentList = new String[]{"Cardiology", "Dentist", "Dermatology", "Endocrine", "ENT", "Gastrology", "General Physician", "Gynaecology",
                "Nephrology", "Neurology", "Oncology", "Opthalmology", "Paediatry", "Orthopedic", "Pathology", "Psychiatry", "Padiology",
                "Surgeon", "Urology", "Pulmonology"};
        ArrayAdapter arrayAdapterSpinner = new ArrayAdapter(getApplication(), android.R.layout.simple_spinner_item, departmentList);
        arrayAdapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_department.setAdapter(arrayAdapterSpinner);
        spinner_department.setOnItemSelectedListener(this);

        btn_addImage_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(AddDoctor.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    BroewsImage();
                } else {
                    ActivityCompat.requestPermissions(AddDoctor.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
                }
            }
        });


        btn_addDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Validation()) {
                    DoctorModel doctorModel = new DoctorModel();
                    int selectedId = rdGender.getCheckedRadioButtonId();
                    rbGender = findViewById(selectedId);
                    String gender = rbGender.getText().toString().trim();
                    doctorModel.setDOCTOR_NAME(txt_adddoctor_name.getText().toString().trim());
                    doctorModel.setDOCTOR_ADDRESS(txt_adddoctor_address.getText().toString().trim());
                    doctorModel.setDOCTOR_CONTACT(txt_adddoctor_contact.getText().toString().trim());
                    doctorModel.setQUALIFICATION(txt_adddoctor_qualification.getText().toString().trim());
                    doctorModel.setDOCTOR_IMAGE(imageName);
                    doctorModel.setGENDER(gender);
                    final DoctorAPI doctorAPI = Url.getInstance().create(DoctorAPI.class);
                    Call<DoctorModel> doctorModelCall = doctorAPI.addDoctor(Url.accessToken, doctorModel);
                    doctorModelCall.enqueue(new Callback<DoctorModel>() {
                        @Override
                        public void onResponse(Call<DoctorModel> call, Response<DoctorModel> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(AddDoctor.this, response.code(), Toast.LENGTH_SHORT).show();
                                return;
                            } else {

                                System.out.println(response.body().getDOCTOR_ID()+"");
                                    if(response.body().getDOCTOR_ID()==0){
                                        Toast.makeText(AddDoctor.this, "Doctor Name already exit", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Hospital_DoctorModel doctor_hospitalModel = new Hospital_DoctorModel();
                                        doctor_hospitalModel.setDOCTOR_ID(response.body().getDOCTOR_ID());
                                        doctor_hospitalModel.setDEPARTMENT(department);
                                        doctor_hospitalModel.setHOSPITAL_ID(hospital_id);
                                        Call<ResponseFromAPI> responseFromAPICall = doctorAPI.addDoctorHospital(Url.accessToken, doctor_hospitalModel);
                                        responseFromAPICall.enqueue(new Callback<ResponseFromAPI>() {

                                            @Override
                                            public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
                                                if (!response.isSuccessful()) {
                                                    Toast.makeText(AddDoctor.this, response.code(), Toast.LENGTH_SHORT).show();
                                                    return;
                                                } else {
                                                    if (response.body().getStatus()) {
                                                        Toast.makeText(AddDoctor.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                                        txt_adddoctor_name.setText(null);
                                                        txt_adddoctor_address.setText(null);
                                                        txt_adddoctor_contact.setText(null);
                                                        txt_adddoctor_qualification.setText(null);
                                                        imageName = "myImage-1575685501226.png";
                                                        img_doctor_addDoctor.setImageDrawable(getResources().getDrawable(R.drawable.ic_user));

                                                    } else {
                                                        Toast.makeText(AddDoctor.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                }


                                            }

                                            @Override
                                            public void onFailure(Call<ResponseFromAPI> call, Throwable t) {
                                                Toast.makeText(AddDoctor.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                            }
                        }

                        @Override
                        public void onFailure(Call<DoctorModel> call, Throwable t) {
                            Toast.makeText(AddDoctor.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }


            }
        });
    }

    private void setToolbar() {
        toolbar_addDoctor = findViewById(R.id.toolbar_addDoctor);
        setSupportActionBar(toolbar_addDoctor);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back = findViewById(R.id.add_DoctorBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddDoctor.this, AdminDashboard.class);
                startActivity(intent);
                finish();
            }
        });


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            BroewsImage();
        } else {
            Toast.makeText(this, "Please provide permission", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("resultCode", resultCode + "");
        Log.d("requestCode", requestCode + "");
        Log.d("data", data + "");
        if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            imagePath = getRealPathFromUri(uri);
            previewImage(imagePath);
            UploadImage();


        }
    }

    private void UploadImage() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("myImage", file.getName(), requestBody);

        PharmacyAPI pharmacyAPI = Url.getInstance().create(PharmacyAPI.class);
        Call<ResponseFromAPI> responseCall = pharmacyAPI.uploadImage(body);
        StrictMode();
        try {
            Response<ResponseFromAPI> responseFromAPIImageResponse = responseCall.execute();
            imageName = responseFromAPIImageResponse.body().getFileName();
        } catch (IOException e) {

            Toast.makeText(this, "Error:", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    private void BroewsImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    private void previewImage(String imagePath) {

        File imgfile = new File(imagePath);
        if (imgfile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgfile.getAbsolutePath());
            img_doctor_addDoctor.setImageBitmap(myBitmap);
        }

    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(), uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }


    public Boolean Validation() {

        String name = txtInpute_adddoctor_name.getEditText().getText().toString().trim();
        String address = txtInpute_adddoctor_address.getEditText().getText().toString().trim();
        String contact = txtInpute_adddoctor_contact.getEditText().getText().toString().trim();
        String qulification = txtInpute_adddoctor_qulification.getEditText().getText().toString().trim();

        if (name.isEmpty()) {
            txtInpute_adddoctor_name.setError("First name Field cannot be empty");
            return false;
        } else if (name.length() < 2 || name.length() > 25) {
            txtInpute_adddoctor_name.setError("Doctor Name should be at least 3 character ");
            return false;
        } else if (address.isEmpty()) {
            txtInpute_adddoctor_name.setError(null);
            txtInpute_adddoctor_address.setError("Address Field cannot be empty");
            return false;
        } else if (address.length() < 5) {
            txtInpute_adddoctor_name.setError(null);
            txtInpute_adddoctor_address.setError("Address should be at least 5 character  ");
            return false;
        } else if (contact.isEmpty()) {
            txtInpute_adddoctor_name.setError(null);
            txtInpute_adddoctor_address.setError(null);
            txtInpute_adddoctor_contact.setError("Contact Field cannot be empty");
            return false;
        } else if (contact.length() < 10) {
            txtInpute_adddoctor_name.setError(null);
            txtInpute_adddoctor_address.setError(null);
            txtInpute_adddoctor_contact.setError("Please Enter valid Contact Number ");
            return false;
        } else if (qulification.isEmpty()) {
            txtInpute_adddoctor_name.setError(null);
            txtInpute_adddoctor_address.setError(null);
            txtInpute_adddoctor_contact.setError(null);

            txtInpute_adddoctor_qulification.setError("Please Enter Doctor Qualification ");
            return false;
        } else if (qulification.length() < 3) {
            txtInpute_adddoctor_name.setError(null);
            txtInpute_adddoctor_address.setError(null);
            txtInpute_adddoctor_contact.setError(null);
            txtInpute_adddoctor_qulification.setError("Doctor Qualification letter should be at least 4 character ");
            return false;
        } else {
            txtInpute_adddoctor_name.setError(null);
            txtInpute_adddoctor_address.setError(null);
            txtInpute_adddoctor_contact.setError(null);
            txtInpute_adddoctor_qulification.setError(null);
            return true;

        }


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        department = departmentList[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
