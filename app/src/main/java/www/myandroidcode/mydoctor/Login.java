package www.myandroidcode.mydoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
     private EditText ed_email,ed_password;
     private TextView tv_signup,tv_forget_password;
     private FrameLayout btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         btnLogin =(FrameLayout)findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(signup);
                finish();
            }
        });

        ImageView facebook =(ImageView)findViewById(R.id.facebook_round);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"facebook clicked",Toast.LENGTH_SHORT).show();

            }
        });


        ImageView twitter =(ImageView) findViewById(R.id.twitter_round);
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"twitter clicked",Toast.LENGTH_SHORT).show();

            }
        });

        tv_signup =(TextView) findViewById(R.id.tv_signup);
        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent signup = new Intent(getApplicationContext(),Registration.class);
                startActivity(signup);

            }
        });



    }
}
