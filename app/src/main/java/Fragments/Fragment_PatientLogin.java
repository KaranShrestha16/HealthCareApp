package Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import www.myandroidcode.mydoctor.MainActivity;
import www.myandroidcode.mydoctor.R;
import www.myandroidcode.mydoctor.Registration;


public class Fragment_PatientLogin extends Fragment {

    private FrameLayout fl_patientLogin;
    private EditText txt_patientEmail,txt_patientPassword;
    private TextView tv_patientSignUp,tv_patientForget_password;
    private ImageView facebook;
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
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
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

}
