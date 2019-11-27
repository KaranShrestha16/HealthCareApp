package www.myandroidcode.mydoctor;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BIM extends AppCompatActivity {
    private Toolbar toolbarBIM;
    private ImageView back,refesh;
    private TextView tv_BIM_result;
    private TextInputEditText txt_weight,txt_height;
    private Button btn_calculate;
    private Double weight ,height,result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bim);
        setToolbar();
        txt_weight= findViewById(R.id.txt_BIM_weight);
        txt_height= findViewById(R.id.txt_BIM_height);
        btn_calculate= findViewById(R.id.btn_BIM_Calculate);
        tv_BIM_result= findViewById(R.id.tv_BIM_result);

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if (Validation()){
                   weight = Double.parseDouble(txt_weight.getText().toString().trim());
                   height= Double.parseDouble(txt_height.getText().toString().trim());
                   Double realHeight=height*0.304800;
                   result= weight/(realHeight*realHeight);

                   if (result < 18.5){
                       tv_BIM_result.setText("Your BIM is "+ result+".\n This is Underweight");
                   } else  if (result > 18.5 && result < 24.9){
                       tv_BIM_result.setText("Your BIM is "+ result+".\n This is Normal");
                   }else  if (result > 24.9 && result < 29.9){
                       tv_BIM_result.setText("Your BIM is "+ result+". This is Overweight");
                   } else {
                       tv_BIM_result.setText("Your BIM is "+ result+".\n This is Obesity");
                   }

                   txt_weight.setText("");
                   txt_height.setText("");
               }

            }
        });
    }
    private void setToolbar() {
        toolbarBIM= findViewById(R.id.toolbarBIM);
        setSupportActionBar(toolbarBIM);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        back=findViewById(R.id.BIMBack);
        refesh=findViewById(R.id.BIMRefresh);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(BIM.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        refesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BIM.this, " Refresh Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public Boolean Validation(){
        if (TextUtils.isEmpty(txt_weight.getText())) {
            txt_weight.requestFocus();
            txt_weight.setError("Please enter Your Weight");
            return false;
        }else  if (TextUtils.isEmpty(txt_height.getText())) {
            txt_height.requestFocus();
            txt_height.setError("Please enter Your Height");
            return false;
        }else if (Double.parseDouble(txt_weight.getText().toString().trim()) < 30 || Double.parseDouble(txt_weight.getText().toString().trim()) > 130) {
            txt_weight.setError("You weight is incorrect");
            txt_weight.requestFocus();
            return false;
        }else  if (Double.parseDouble(txt_height.getText().toString().trim()) < 4 || Double.parseDouble(txt_height.getText().toString().trim())  > 8) {
            txt_height.requestFocus();
            txt_height.setError("You Height is incorrect");
            return false;
        }


        return true;
    }

}
