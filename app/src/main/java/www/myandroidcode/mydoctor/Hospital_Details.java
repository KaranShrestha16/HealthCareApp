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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private Toolbar toolbarHospital;
    private ImageView back,refesh,home;
    private ViewPager hospitalViewPager;
    private TabLayout hospitalTablayout;
    private Button icu,general,emergency;
    private TextView tv_toolbarTitle,tv_hospitalName,tv_hospitalAddress,tv_hospitalWebsite;
    private RelativeLayout RL_image;
    private String toolbarTitle;
    private int hospital_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__details);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            hospital_id= bundle.getInt("hospital_id");
        }
        setToolbar();
        tv_toolbarTitle=findViewById(R.id.tv_toolbarTitle);
        hospitalViewPager = findViewById(R.id.hospitalViewPager);
        hospitalTablayout=findViewById(R.id.hospitalTablayout);
        tv_hospitalName=findViewById(R.id.tv_hospitalName);
        tv_hospitalAddress=findViewById(R.id.tv_hospitalAddress);
        tv_hospitalWebsite=findViewById(R.id.tv_hospitalWebsite);
        RL_image=findViewById(R.id.RL_image);
        general=findViewById(R.id.btn_generalBed);
        icu=findViewById(R.id.btn_ICUBed);
        emergency=findViewById(R.id.btn_emergencyBed);

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

                    tv_toolbarTitle.setText(response.body().getNAME());
                    general.setText(response.body().getGENERAL()+" \n GENERAL");
                    icu.setText(response.body().getICU()+" \n ICU");
                    tv_hospitalName.setText(response.body().getNAME());
                    tv_hospitalAddress.setText( "Address: "+response.body().getADDRESS());
                    tv_hospitalWebsite.setText( "Website: "+response.body().getWEBSITE());
                }
            }

            @Override
            public void onFailure(Call<HospitalModel> call, Throwable t) {
                Toast.makeText(Hospital_Details.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


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



        HospitalFragmentAdapter adapter= new HospitalFragmentAdapter(getSupportFragmentManager());
        adapter.addHospitalFragment(new Hospital_Services_Fragment(),"Hospital Services");
        adapter.addHospitalFragment(new All_Doctor_List(),"All Doctors");
        hospitalViewPager.setAdapter(adapter);
        hospitalTablayout.setupWithViewPager(hospitalViewPager);
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
