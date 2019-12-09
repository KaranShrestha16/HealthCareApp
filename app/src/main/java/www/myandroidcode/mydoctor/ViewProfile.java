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
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import API.PatientAPI;
import API.PharmacyAPI;
import API.Url;
import Model.PatientModel;
import Model.ResponseFromAPI;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewProfile extends AppCompatActivity {
    private ImageView back,userImage;
    private Toolbar toolbar;
    private TextInputEditText txt_userName,txt_userAddress,txt_userContact,txt_userEmail,txt_userBloodGroup;
    private TextView tv_userBirthdate;
    private Button btn_UpdateProfile,btn_uploadUserImage;
    private RadioGroup rdGender;
    private RadioButton rbGender,radiomaleUser,radiofemaleUser,radioOtherUser;
    private String imageName, imagePath,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        setToolbar();
        txt_userName= findViewById(R.id.txt_userName);
        txt_userAddress= findViewById(R.id.txt_userAddress);
        txt_userContact= findViewById(R.id.txt_userContact);
        txt_userEmail= findViewById(R.id.txt_userEmail);
        txt_userBloodGroup= findViewById(R.id.txt_userBloodGroup);
        tv_userBirthdate= findViewById(R.id.tv_userBirthdate);
        btn_UpdateProfile= findViewById(R.id.btn_UpdateProfile);
        btn_uploadUserImage= findViewById(R.id.btn_uploadUserImage);
        radioOtherUser= findViewById(R.id.radioOtherUser);
        radiomaleUser= findViewById(R.id.radioMaleUser);
        radiofemaleUser= findViewById(R.id.radioFemaleUser);
        userImage= findViewById(R.id.userImage);
        rdGender = findViewById(R.id.radioGenderUser);

        final PatientAPI patientAPI = Url.getInstance().create(PatientAPI.class);
        final Call<PatientModel> patientModelCall= patientAPI.getPatientById(Url.accessToken,Url.id);
        patientModelCall.enqueue(new Callback<PatientModel>() {
            @Override
            public void onResponse(Call<PatientModel> call, Response<PatientModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(ViewProfile.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    String imgpath = Url.BASE_URL + response.body().getIMAGE();
                    StrictMode();
                    try{
                        URL url = new URL(imgpath);
                        userImage.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
                    }catch (IOException e) {
                        e.printStackTrace();
                    }


                    txt_userName.setText(response.body().getNAME());
                    txt_userAddress.setText(response.body().getADDRESS());
                    txt_userContact.setText(response.body().getCONTACT());
                    txt_userEmail.setText(response.body().getEMAIL());
                    txt_userBloodGroup.setText(response.body().getBLOOD_GROUP());
                    tv_userBirthdate.setText(response.body().getBIRTHDATE());
                    password=response.body().getPASSWORD();
                    imageName=response.body().getIMAGE();


                    String gender=response.body().getGENDER();
                    if(gender.equals("Male")){
                        radiomaleUser.setChecked(true);
                        radiofemaleUser.setChecked(false);
                        radioOtherUser.setChecked(false);
                    }else if(gender.equals("Female")){
                        radiofemaleUser.setChecked(true);
                        radioOtherUser.setChecked(false);
                        radiomaleUser.setChecked(false);
                    }else if(gender.equals("Other")){
                        radioOtherUser.setChecked(true);
                        radiofemaleUser.setChecked(false);
                        radiomaleUser.setChecked(false);
                    }

                }
            }

            @Override
            public void onFailure(Call<PatientModel> call, Throwable t) {
                Toast.makeText(ViewProfile.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        btn_uploadUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(ViewProfile.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    BroewsImage();
                } else {
                    ActivityCompat.requestPermissions(ViewProfile.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
                }
            }
        });

        btn_UpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PatientModel patientModel= new PatientModel();
                int selectedId = rdGender.getCheckedRadioButtonId();
                rbGender = findViewById(selectedId);
                String gender = rbGender.getText().toString().trim();

                patientModel.setNAME(txt_userName.getText().toString().trim());
                patientModel.setGENDER(gender);
                patientModel.setIMAGE(imageName);
                patientModel.setCONTACT(txt_userContact.getText().toString().trim());
                patientModel.setADDRESS(txt_userAddress.getText().toString().trim());
                patientModel.setEMAIL(txt_userEmail.getText().toString().trim());
                patientModel.setBLOOD_GROUP(txt_userBloodGroup.getText().toString().trim());
                patientModel.setBIRTHDATE(tv_userBirthdate.getText().toString().trim());
                patientModel.setPASSWORD(password);

                Call<ResponseFromAPI> responseFromAPICall=patientAPI.updateUserProfile(Url.accessToken,patientModel,Url.id);
                responseFromAPICall.enqueue(new Callback<ResponseFromAPI>() {
                    @Override
                    public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(ViewProfile.this, response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }else{
                            if(response.body().getStatus()){
                                Toast.makeText(ViewProfile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent= new Intent(ViewProfile.this, MyProfile.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(ViewProfile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseFromAPI> call, Throwable t) {
                        Toast.makeText(ViewProfile.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

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
            userImage.setImageBitmap(myBitmap);
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

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar_viewProfile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back = findViewById(R.id.viewProfileBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewProfile.this, MyProfile.class);
                startActivity(intent);
                finish();
            }
        });

    }



}
