package www.myandroidcode.mydoctor;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
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

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import API.PharmacyAPI;
import API.Url;
import Model.ReportModel;
import Model.ResponseFromAPI;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddReport extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private ImageView back,imageReport;
    private Toolbar toolbar_report;
    private TextInputLayout txtInpute_reportDescription,txtInpute_reportName;
    private TextInputEditText txt_reportDescription,txt_reportName;
    private Button btn_uploadReport,btn_addReport;
    private String imageName, imagePath;
    private TextView tv_reportDate,tv_showDateReport,imageError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);
        setToolbar();

        txt_reportName=findViewById(R.id.txt_reportName);
        txt_reportDescription=findViewById(R.id.txt_reportDescription);
        txtInpute_reportName=findViewById(R.id.txtInpute_reportName);
        txtInpute_reportDescription=findViewById(R.id.txtInpute_reportDescription);
        btn_uploadReport=findViewById(R.id.btn_uploadReport);
        btn_addReport=findViewById(R.id.btn_addReport);
        imageReport=findViewById(R.id.imageReport);
        tv_reportDate=findViewById(R.id.text_reportDate);
        tv_showDateReport=findViewById(R.id.tv_showDateReport);
        imageError=findViewById(R.id.imageError);

        tv_reportDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker();
            }
        });


        btn_uploadReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(AddReport.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    BroewsImage();
                } else {
                    ActivityCompat.requestPermissions(AddReport.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
                }
            }
        });

        btn_addReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Validation()){
                    ReportModel reportModel = new ReportModel();
                    reportModel.setPATIENT_ID(Url.id);
                    reportModel.setREPORT_IMAGE(imageName);
                    reportModel.setREPORT_DATE(tv_showDateReport.getText().toString().trim());
                    reportModel.setREPORT_NAME(txt_reportName.getText().toString().trim());
                    reportModel.setDESCRIPTION(txt_reportDescription.getText().toString().trim());

                    PharmacyAPI pharmacyAPI= Url.getInstance().create(PharmacyAPI.class);
                    Call<ResponseFromAPI> responseFromAPICall= pharmacyAPI.addReport(Url.accessToken,reportModel);
                    responseFromAPICall.enqueue(new Callback<ResponseFromAPI>() {
                        @Override
                        public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(AddReport.this, response.code(), Toast.LENGTH_SHORT).show();
                                return;
                            }else{
                                if(response.body().getStatus()){
                                    Toast.makeText(AddReport.this, "Report Insert Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent= new Intent(AddReport.this, Report.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    Toast.makeText(AddReport.this, "Insert Fail", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseFromAPI> call, Throwable t) {

                        }
                    });

                }
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


    public void DatePicker(){
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,this,year,month,day);
        datePickerDialog.getDatePicker();
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        tv_showDateReport.setText(dayOfMonth+"/"+(month+1)+"/"+year);
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





    private void setToolbar() {
        toolbar_report = findViewById(R.id.toolbar_addReport);
        setSupportActionBar(toolbar_report);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back = findViewById(R.id.addReportBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddReport.this, MyProfile.class);
                startActivity(intent);
                finish();
            }
        });

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
            imageReport.setImageBitmap(myBitmap);
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

    public Boolean Validation(){
        String name= txtInpute_reportName.getEditText().getText().toString().trim();
        String description= txtInpute_reportDescription.getEditText().getText().toString().trim();
        String date= tv_showDateReport.getText().toString().trim();
        Log.d("Image nmae", imageName+"");

        if(name.isEmpty()){
            txtInpute_reportName.setError("Report Name Required ");
            return  false;
        }else if(description.isEmpty()){
            txtInpute_reportName.setError(null);
            txtInpute_reportDescription.setError("Report Description Required ");
            return  false;
        }else if(date.isEmpty()){
            txtInpute_reportName.setError(null);
            txtInpute_reportDescription.setError(null);
            txtInpute_reportDescription.setFocusable(true);
            tv_showDateReport.setText("Report Date required");
            return  false;
        }else{
            txtInpute_reportName.setError(null);
            txtInpute_reportDescription.setError(null);
            imageError.setText("Report Imagedd required");
            return true;

        }
    }






}
