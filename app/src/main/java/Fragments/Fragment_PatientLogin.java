package Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import API.PatientAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import API.Url;
import Model.ResponseFromAPI;
import www.myandroidcode.mydoctor.MainActivity;
import www.myandroidcode.mydoctor.R;
import www.myandroidcode.mydoctor.Registration;


public class Fragment_PatientLogin extends Fragment {

    private FrameLayout fl_patientLogin;
    private TextInputLayout txt_patientEmail,txt_patientPassword;
    private TextView tv_patientSignUp,tv_patientForget_password;
    private ImageView facebook;


    private static final Pattern PASSWORD_PATTERN= Pattern.compile("^"+"(?=.*[0-9])"+ "(?=.*[a-z])" +"(?=.*[A-Z])"+"(?=.*[@#$%^&+=])"+"(?=\\S+$)"+".{8,}"+"$");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_fragment__patient_login, container, false);

        fl_patientLogin = view.findViewById(R.id.fl_patientLogin);
        txt_patientEmail = view.findViewById(R.id.txt_patientEmail);
        txt_patientPassword = view.findViewById(R.id.txt_patientPassword);
        tv_patientSignUp = view.findViewById(R.id.tv_patientSignup);
        tv_patientForget_password = view.findViewById(R.id.tv_patientForgotPassword);



        fl_patientLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=txt_patientEmail.getEditText().getText().toString().trim();
                String password=txt_patientPassword.getEditText().getText().toString().trim();

              if(Validation()){
                  PatientAPI patientAPI= Url.getInstance().create(PatientAPI.class);
                  Call<ResponseFromAPI> patientCall = patientAPI.CheckUser(email,password);
                  patientCall.enqueue(new Callback<ResponseFromAPI>() {
                      @Override
                      public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
                          if(!response.isSuccessful()){
                              Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                              return;
                          }else {
                             if(response.body().getStatus()){
                                 Url.accessToken = response.body().getAccessToken();
                                 Url.id = response.body().getId();
                                 Log.d("Patient Id", response.body().getId()+"");
                                 Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                 Intent intent = new Intent(getContext(), MainActivity.class);
                                 startActivity(intent);
                             }else{
                                 Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                             }
                          }
                      }

                      @Override
                      public void onFailure(Call<ResponseFromAPI> call, Throwable t) {
                          Toast.makeText(getContext(), "Error: "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                      }
                  });

              }

            }
        });

        tv_patientSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Registration.class);
                startActivity(intent);
            }
        });


        return view;
    }


    @SuppressLint("ResourceType")
    public Boolean Validation(){
    String email=txt_patientEmail.getEditText().getText().toString().trim();
    String password=txt_patientPassword.getEditText().getText().toString().trim();

    if(email.isEmpty()){
        txt_patientEmail.setError("Email Field cannot be empty");
        return false;
    }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        txt_patientEmail.setError("Please enter valid email address");
        return false;

    }else if(password.isEmpty()) {
        txt_patientEmail.setError(null);
        txt_patientPassword.setError("Password Field cannot be empty");
        return false;
    }else if(password.length()<8) {
        txt_patientEmail.setError(null);
        txt_patientPassword.setError("Password must at lest 8 charecter long");
        return false;
    }else if(!PASSWORD_PATTERN.matcher(password).matches()) {
        txt_patientEmail.setError(null);
        txt_patientPassword.setError("Password must include symbole,number and alphabet");
        return false;
    }else {
        txt_patientEmail.setError(null);
        txt_patientPassword.setError(null);
        return true;
    }

    }

}
