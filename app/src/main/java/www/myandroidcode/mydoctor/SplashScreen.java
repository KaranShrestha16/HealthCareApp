package www.myandroidcode.mydoctor;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageView=findViewById(R.id.image_splash_screen);
        textView=findViewById(R.id.tv_splash_screen);

        imageView.setAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.fade_animation_top));
        textView.setAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.fade_animation_button));

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, Login.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }
}
