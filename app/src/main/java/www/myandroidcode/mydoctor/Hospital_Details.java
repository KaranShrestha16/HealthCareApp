package www.myandroidcode.mydoctor;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Adapter.HospitalFragmentAdapter;
import Fragments.All_Doctor_List;
import Fragments.Hospital_Services_Fragment;

public class Hospital_Details extends AppCompatActivity {

    private ViewPager hospitalViewPager;
    private TabLayout hospitalTablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__details);

        hospitalViewPager = findViewById(R.id.hospitalViewPager);
        hospitalTablayout=findViewById(R.id.hospitalTablayout);

        HospitalFragmentAdapter adapter= new HospitalFragmentAdapter(getSupportFragmentManager());
        adapter.addHospitalFragment(new Hospital_Services_Fragment(),"Hospital Services");
        adapter.addHospitalFragment(new All_Doctor_List(),"All Doctors");

        hospitalViewPager.setAdapter(adapter);
        hospitalTablayout.setupWithViewPager(hospitalViewPager);
    }
}
