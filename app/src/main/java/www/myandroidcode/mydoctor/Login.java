package www.myandroidcode.mydoctor;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import Adapter.LoginFragmentAdapter;
import Fragments.Fragment_AdminLogin;
import Fragments.Fragment_PatientLogin;

public class Login extends AppCompatActivity {
    private ViewPager loginViewPager;
    private TabLayout loginTablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginTablayout=findViewById(R.id.loginTablayout);
        loginViewPager=findViewById(R.id.loginViewPager);

        LoginFragmentAdapter adapter= new LoginFragmentAdapter(getSupportFragmentManager());
        adapter.addLoginFragment(new Fragment_PatientLogin(),"Patient");
        adapter.addLoginFragment(new Fragment_AdminLogin(),"Admin");

        loginViewPager.setAdapter(adapter);
        loginTablayout.setupWithViewPager(loginViewPager);




    }
}
