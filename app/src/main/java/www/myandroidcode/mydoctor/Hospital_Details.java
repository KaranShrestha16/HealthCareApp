package www.myandroidcode.mydoctor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import API.HospitalAPI;
import API.Url;
import Adapter.HospitalFragmentAdapter;
import Fragments.All_Doctor_List;
import Fragments.Hospital_Services_Fragment;
import Model.HospitalModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Hospital_Details extends AppCompatActivity {
    FrameLayout fl_cardiology, fl_dential, fl_dermatology, fl_endocrine,fl_ent,fl_gastrology,fl_physican,fl_gynaecology
            ,fl_nepthrology,fl_neurology,fl_oncology,fl_opthalmology,fl_orthopedic,fl_paediatry,fl_pathology,fl_psychiatry,fl_pulmonology,
            fl_radiology, fl_surgeon,fl_urology;
    private Toolbar toolbarHospital;
    private ImageView back,refesh,home;
    private ViewPager hospitalViewPager;
    private TabLayout hospitalTablayout;
    private Button icu,general,emergency;
    private TextView tv_toolbarTitle,tv_hospitalName,tv_hospitalAddress,tv_hospitalWebsite;
    private RelativeLayout RL_image;
    private int hospital_id;
    private String toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__details);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            hospital_id= bundle.getInt("hospitalid");
        }
        tv_toolbarTitle=findViewById(R.id.tv_toolbarTitle);
        tv_hospitalName=findViewById(R.id.tv_hospitalName);
        tv_hospitalAddress=findViewById(R.id.tv_hospitalAddress);
        tv_hospitalWebsite=findViewById(R.id.tv_hospitalWebsite);
        RL_image=findViewById(R.id.RL_image);
        general=findViewById(R.id.btn_generalBed);
        icu=findViewById(R.id.btn_ICUBed);
        emergency=findViewById(R.id.btn_emergencyBed);


        setToolbar();
        HospitalAPI hospitalAPI= Url.getInstance().create(HospitalAPI.class);
        retrofit2.Call<HospitalModel> hospitalModelCall= hospitalAPI.getHospitalById(Url.accessToken,hospital_id);
        hospitalModelCall.enqueue(new Callback<HospitalModel>() {
            @Override
            public void onResponse(Call<HospitalModel> call, Response<HospitalModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Hospital_Details.this, "Hospital_id Could not find", Toast.LENGTH_SHORT).show();
                    return;
                }else {

                    String imgpath = Url.BASE_URL + response.body().getIMAGE();
                    StrictMode();
                    try{
                        URL url = new URL(imgpath);
                        Bitmap bmImg = BitmapFactory.decodeStream((InputStream) url.getContent());
                        BitmapDrawable background = new BitmapDrawable(bmImg);
                        RL_image.setBackgroundDrawable(background);
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                    tv_toolbarTitle.setText(response.body().getHOSPITAL_NAME());
                    toolbarTitle=response.body().getHOSPITAL_NAME();
                    general.setText(response.body().getGENERAL()+" \n GENERAL");
                    icu.setText(response.body().getICU()+" \n ICU");
                    emergency.setText(response.body().getEMERGENCY()+" \n EMERGENCY");
                    tv_hospitalName.setText(response.body().getHOSPITAL_NAME());
                    tv_hospitalAddress.setText( "Address: "+response.body().getADDRESS());
                    tv_hospitalWebsite.setText(response.body().getWEBSITE());


                }
            }

            @Override
            public void onFailure(Call<HospitalModel> call, Throwable t) {
                Toast.makeText(Hospital_Details.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        ClickFunction();
//        tv_hospitalWebsite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
//                intent.addCategory(Intent.CATEGORY_BROWSABLE);
//                intent.setData(Uri.parse(tv_hospitalWebsite.getText().toString().trim()));
//                startActivity(intent);
//            }
//        });
    }

    public void ClickFunction()
    {

        Log.d("toolbarTitle2", toolbarTitle+"");
        fl_cardiology = findViewById(R.id.fl_cardiology);
        fl_cardiology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Hospital_Details.this, DoctorWithDepartment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("department","Cadiology");
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("toolbarTitle_for",toolbarTitle);
                startActivity(intent);
            }
        });
        fl_dential = findViewById(R.id.fl_dentist);
        fl_dential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Hospital_Details.this, DoctorWithDepartment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("department","Dentist");
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("toolbarTitle_for",toolbarTitle);

                startActivity(intent);
            }
        });

        fl_dermatology = findViewById(R.id.fl_dermatology);
        fl_dermatology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(Hospital_Details.this, DoctorWithDepartment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("department","Dermatology");
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("toolbarTitle_for",toolbarTitle);
                startActivity(intent);
            }
        });

        fl_endocrine =findViewById(R.id.fl_endocrine);
        fl_endocrine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(Hospital_Details.this, DoctorWithDepartment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("department","Endocrine");
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("toolbarTitle_for",toolbarTitle);
                startActivity(intent);
            }
        });

        fl_ent = findViewById(R.id.fl_ent);
        fl_ent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(Hospital_Details.this, DoctorWithDepartment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("department","ENT");
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("toolbarTitle_for",toolbarTitle);

                startActivity(intent);
            }
        });

        fl_gastrology = findViewById(R.id.fl_gastrology);
        fl_gastrology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(Hospital_Details.this, DoctorWithDepartment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("department","Gastrology");
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("toolbarTitle_for",toolbarTitle);
                startActivity(intent);
            }
        });

        fl_physican =  findViewById(R.id.fl_physician);
        fl_physican.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(Hospital_Details.this, DoctorWithDepartment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("department","General Physician");
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("toolbarTitle_for",toolbarTitle);
                startActivity(intent);
            }
        });

        fl_gynaecology =  findViewById(R.id.fl_gynaecology);
        fl_gynaecology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(Hospital_Details.this, DoctorWithDepartment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("department","Gynaecology");
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("toolbarTitle_for",toolbarTitle);
                startActivity(intent);
            }
        });


        fl_nepthrology=findViewById(R.id.fl_nephrology);
        fl_nepthrology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(Hospital_Details.this, DoctorWithDepartment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("department","Nephrology");
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("toolbarTitle_for",toolbarTitle);
                startActivity(intent);
            }
        });

        fl_neurology= findViewById(R.id.fl_neurology);
        fl_neurology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(Hospital_Details.this, DoctorWithDepartment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("department","Neurology");
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("toolbarTitle_for",toolbarTitle);
                startActivity(intent);
            }
        });


        fl_oncology= findViewById(R.id.fl_oncology);
        fl_oncology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(Hospital_Details.this, DoctorWithDepartment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("department","Oncology");
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("toolbarTitle_for",toolbarTitle);
                startActivity(intent);
            }
        });

        fl_opthalmology= findViewById(R.id.fl_opthalmology);
        fl_opthalmology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(Hospital_Details.this, DoctorWithDepartment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("department","Opthalmology");
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("toolbarTitle_for",toolbarTitle);
                startActivity(intent);
            }
        });

        fl_orthopedic= findViewById(R.id.fl_orthopedic);
        fl_orthopedic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(Hospital_Details.this, DoctorWithDepartment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("department","Orthopedic");
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("toolbarTitle_for",toolbarTitle);
                startActivity(intent);
            }
        });


        fl_paediatry= findViewById(R.id.fl_paediatry);
        fl_paediatry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(Hospital_Details.this, DoctorWithDepartment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("department","Paediatry");
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("toolbarTitle_for",toolbarTitle);
                startActivity(intent);
            }
        });

        fl_pathology= findViewById(R.id.fl_pathology);
        fl_pathology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(Hospital_Details.this, DoctorWithDepartment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("department","Pathology");
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("toolbarTitle_for",toolbarTitle);
                startActivity(intent);
            }
        });

        fl_psychiatry= findViewById(R.id.fl_psychiatry);
        fl_psychiatry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(Hospital_Details.this, DoctorWithDepartment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("department","Psychiatry");
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("toolbarTitle_for",toolbarTitle);
                startActivity(intent);
            }
        });


        fl_pulmonology= findViewById(R.id.fl_pulmonology);
        fl_pulmonology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Hospital_Details.this, DoctorWithDepartment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("department","Endocrine");
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("toolbarTitle_for",toolbarTitle);
                startActivity(intent);
            }
        });


        fl_radiology= findViewById(R.id.fl_radiology);
        fl_radiology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Hospital_Details.this, DoctorWithDepartment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("department","Endocrine");
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("toolbarTitle_for",toolbarTitle);
                startActivity(intent);
            }
        });

        fl_surgeon=  findViewById(R.id.fl_radiology);
        fl_surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Hospital_Details.this, DoctorWithDepartment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("department","Surgeon");
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("toolbarTitle_for",toolbarTitle);
                startActivity(intent);
            }
        });

        fl_urology= findViewById(R.id.fl_urology);
        fl_urology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(Hospital_Details.this, DoctorWithDepartment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("department","Urology");
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("toolbarTitle_for",toolbarTitle);
                startActivity(intent);
            }
        });






    }


    private void setToolbar() {
        toolbarHospital= findViewById(R.id.toolbarHospital_details);
        setSupportActionBar(toolbarHospital);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back=findViewById(R.id.hospital_details_Back);
        refesh=findViewById(R.id.hospital_details_Refresh);
        home=findViewById(R.id.homeHospitalDetails);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Hospital_Details.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Hospital_Details.this,Hospitals.class);
                startActivity(intent);
                finish();
            }
        });


        refesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Hospital_Details.this, " Refresh Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

}
