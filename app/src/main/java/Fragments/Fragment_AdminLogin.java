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

import www.myandroidcode.mydoctor.AdminDashboard;
import www.myandroidcode.mydoctor.MainActivity;
import www.myandroidcode.mydoctor.R;

public class Fragment_AdminLogin extends Fragment {
    private FrameLayout fl_adminLogin;
    private EditText txt_adminEmail,txt_adminPassword;
    private TextView tv_adminForget_password;
    private ImageView facebook;


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
                Intent intent = new Intent(getActivity(), AdminDashboard.class);
                startActivity(intent);
            }
        });

        return view;




    }




}
