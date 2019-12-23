package www.myandroidcode.mydoctor;

import android.Manifest;
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
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import API.HospitalAPI;
import API.PharmacyAPI;
import API.Url;
import Model.HospitalModel;
import Model.ResponseFromAPI;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddHospital extends AppCompatActivity {
    private ImageView back,img_hospital;
    private Toolbar toolbar_addHospital;
    private TextInputLayout txtInpute_hospital_name,txtInpute_hospital_address,txtInpute_hospital_contact,txtInpute_hospital_icu,txtInpute_hospital_general,txtInpute_hospital_emergency,txtInput_hospital_website;
    private TextInputEditText txt_hospital_name,txt_hospital_address,txt_hospital_contact,txt_hospital_icu,txt_hospital_general,txt_hospital_emergency,txt_hospital_website;
    private Button btn_addHospital,btn_addImage_hopsital;
    private String imageName, imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hospital);
        setToolbar();
        btn_addHospital=findViewById(R.id.btn_addHospital);
        btn_addImage_hopsital=findViewById(R.id.btn_addImage_hopsital);
        txtInpute_hospital_name=findViewById(R.id.txtInpute_hospital_name);
        txtInpute_hospital_address=findViewById(R.id.txtInpute_hospital_address);
        txtInpute_hospital_contact=findViewById(R.id.txtInpute_hospital_contact);
        txtInput_hospital_website=findViewById(R.id.txtInput_hospital_website);
        txtInpute_hospital_icu=findViewById(R.id.txtInput_hospital_icu);
        txtInpute_hospital_general=findViewById(R.id.txtInput_hospital_general);
        txtInpute_hospital_emergency=findViewById(R.id.txtInput_hospital_emergency);
        txt_hospital_name=findViewById(R.id.txt_hospital_name);
        txt_hospital_address=findViewById(R.id.txt_hospital_address);
        txt_hospital_contact=findViewById(R.id.txt_hospital_contact);
        txt_hospital_website=findViewById(R.id.txt_hospital_website);
        txt_hospital_general=findViewById(R.id.txt_hospital_general);
        txt_hospital_icu=findViewById(R.id.txt_hospital_icu);
        txt_hospital_emergency=findViewById(R.id.txt_hospital_emergency);
        btn_addImage_hopsital=findViewById(R.id.btn_addImage_hopsital);
        img_hospital=findViewById(R.id.img_hospital);


        btn_addImage_hopsital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(AddHospital.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    BroewsImage();
                } else {
                    ActivityCompat.requestPermissions(AddHospital.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
                }
            }
        });

        btn_addHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Validation()){
                    HospitalModel hospitalModel= new HospitalModel();
                    hospitalModel.setHOSPITAL_NAME(txt_hospital_name.getText().toString().trim());
                    hospitalModel.setADDRESS(txt_hospital_address.getText().toString().trim());
                    hospitalModel.setCONTACT(txt_hospital_contact.getText().toString().trim());
                    hospitalModel.setWEBSITE(txt_hospital_website.getText().toString().trim());
                    hospitalModel.setEMERGENCY(txt_hospital_emergency.getText().toString().trim());
                    hospitalModel.setICU(txt_hospital_icu.getText().toString().trim());
                    hospitalModel.setGENERAL(txt_hospital_general.getText().toString().trim());
                    hospitalModel.setIMAGE(imageName);

                    HospitalAPI hospitalAPI = Url.getInstance().create(HospitalAPI.class);
                    Call<ResponseFromAPI> responseFromAPICall= hospitalAPI.addHospital(Url.accessToken, hospitalModel);
                    responseFromAPICall.enqueue(new Callback<ResponseFromAPI>() {
                        @Override
                        public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(AddHospital.this, response.code(), Toast.LENGTH_SHORT).show();
                                return;
                            }else{

                                if(response.body().getStatus()){
                                    Toast.makeText(AddHospital.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    Intent intent= new Intent( AddHospital.this, AdminHospital.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(AddHospital.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    txt_hospital_address.setText(null);
                                    txt_hospital_name.setText(null);
                                    txt_hospital_contact.setText(null);
                                    txt_hospital_emergency.setText(null);
                                    txt_hospital_icu.setText(null);
                                    txt_hospital_general.setText(null);
                                    txt_hospital_emergency.setText(null);

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseFromAPI> call, Throwable t) {
                            Toast.makeText(AddHospital.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
            img_hospital.setImageBitmap(myBitmap);
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


    private Boolean Validation() {

        String name= txtInpute_hospital_name.getEditText().getText().toString().trim();
        String address= txtInpute_hospital_address.getEditText().getText().toString().trim();
        String contact= txtInpute_hospital_contact.getEditText().getText().toString().trim();
        String website= txtInput_hospital_website.getEditText().getText().toString().trim();
        String icu= txtInput_hospital_website.getEditText().getText().toString().trim();
        String emergency= txtInput_hospital_website.getEditText().getText().toString().trim();
        String general= txtInput_hospital_website.getEditText().getText().toString().trim();

        if(name.isEmpty()){
            txtInpute_hospital_name.setError("Hospital Name Required ");
            return  false;
        }else if(address.isEmpty()){
            txtInpute_hospital_name.setError(null);
            txtInpute_hospital_address.setError("Hospital Address Required ");
            return  false;
        }else if(contact.isEmpty()){
            txtInpute_hospital_name.setError(null);
            txtInpute_hospital_address.setError(null);
            txtInpute_hospital_address.setError("Hospital Contact Required ");
            return  false;
        }else if(website.isEmpty()){
            txtInpute_hospital_name.setError(null);
            txtInpute_hospital_address.setError(null);
            txtInpute_hospital_address.setError(null);
            txtInput_hospital_website.setError("Hospital Website required");

            return  false;
        }else if(general.isEmpty()){
            txtInpute_hospital_name.setError(null);
            txtInpute_hospital_address.setError(null);
            txtInpute_hospital_address.setError(null);
            txtInpute_hospital_address.setError(null);
            txtInpute_hospital_general.setError("Number of General Bed Required ");
            return  false;
        }else if(icu.isEmpty()){
            txtInpute_hospital_name.setError(null);
            txtInpute_hospital_address.setError(null);
            txtInpute_hospital_address.setError(null);
            txtInpute_hospital_address.setError(null);
            txtInpute_hospital_general.setError(null);
            txtInpute_hospital_icu.setError("Number of ICU Bed Required ");
            return  false;
        }else if(emergency.isEmpty()){
            txtInpute_hospital_name.setError(null);
            txtInpute_hospital_address.setError(null);
            txtInpute_hospital_address.setError(null);
            txtInpute_hospital_address.setError(null);
            txtInpute_hospital_general.setError(null);
            txtInpute_hospital_general.setError(null);
            txtInpute_hospital_general.setError("Hospital Website Required ");
            return  false;
        }else{
            txtInpute_hospital_name.setError(null);
            txtInpute_hospital_address.setError(null);
            txtInpute_hospital_address.setError(null);
            txtInpute_hospital_address.setError(null);
            return true;

        }

    }

    private void setToolbar() {
        toolbar_addHospital = findViewById(R.id.toolbar_addHospital);
        setSupportActionBar(toolbar_addHospital);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back = findViewById(R.id.add_hospitalBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddHospital.this, AdminHospital.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
