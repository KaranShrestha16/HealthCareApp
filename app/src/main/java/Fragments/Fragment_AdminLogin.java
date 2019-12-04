package Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
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
import API.Url;
import Model.ResponseFromAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.myandroidcode.mydoctor.AdminDashboard;
import www.myandroidcode.mydoctor.R;

public class Fragment_AdminLogin extends Fragment {
    private FrameLayout fl_adminLogin;
    private TextInputLayout txt_adminEmail,txt_adminPassword;
    private TextView tv_adminForget_password;
    private ImageView facebook; private static final Pattern PASSWORD_PATTERN= Pattern.compile("^"+"(?=.*[0-9])"+ "(?=.*[a-z])" +"(?=.*[A-Z])"+"(?=.*[@#$%^&+=])"+"(?=\\S+$)"+".{8,}"+"$");



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment__admin_login, container, false);

        fl_adminLogin = view.findViewById(R.id.fl_adminLogin);
        txt_adminEmail = view.findViewById(R.id.txt_adminEmail);
        txt_adminPassword = view.findViewById(R.id.txt_adminPassword_login);
        tv_adminForget_password = view.findViewById(R.id.tv_adminForgotPassword);

        fl_adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Validation()){
                    String email=txt_adminEmail.getEditText().getText().toString().trim();
                    String password=txt_adminPassword.getEditText().getText().toString().trim();
                    PatientAPI patientAPI= Url.getInstance().create(PatientAPI.class);
                    Call<ResponseFromAPI> patientCall = patientAPI.CheckUserAdmin(email,password);
                    patientCall.enqueue(new Callback<ResponseFromAPI>() {
                        @Override
                        public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                return;
                            }else {
                                if(response.body().getStatus()){
                                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getContext(), AdminDashboard.class);
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

        return view;




    }
    @SuppressLint("ResourceType")
    public Boolean Validation(){
        String email=txt_adminEmail.getEditText().getText().toString().trim();
        String password=txt_adminPassword.getEditText().getText().toString().trim();

        if(email.isEmpty()){
            txt_adminEmail.setError("Email Field cannot be empty");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txt_adminEmail.setError("Please enter valid email address");
            return false;

        }else if(password.isEmpty()) {
            txt_adminEmail.setError(null);
            txt_adminPassword.setError("Password Field cannot be empty");
            return false;
        }else if(!PASSWORD_PATTERN.matcher(password).matches()) {
            txt_adminEmail.setError(null);
            txt_adminPassword.setError("Password Do not match");
            return false;
        }else {
            txt_adminEmail.setError(null);
            txt_adminPassword.setError(null);
            return true;
        }

    }






}
