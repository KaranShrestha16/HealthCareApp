package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import www.myandroidcode.mydoctor.DoctorWithDepartment;
import www.myandroidcode.mydoctor.R;

public class Hospital_Services_Fragment extends Fragment {
    FrameLayout fl_cardiology, fl_dential, fl_dermatology, fl_endocrine,fl_ent,fl_gastrology,fl_physican,fl_gynaecology
            ,fl_nepthrology,fl_neurology,fl_oncology,fl_opthalmology,fl_orthopedic,fl_paediatry,fl_pathology,fl_psychiatry,fl_pulmonology,
            fl_radiology, fl_surgeon,fl_urology;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hospital__services_, container, false);

        fl_cardiology = view.findViewById(R.id.fl_cardiology);
        fl_cardiology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getContext(), DoctorWithDepartment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("department","Cardiology");
                startActivity(intent);
            }
        });

        fl_dential = view.findViewById(R.id.fl_dentist);
        fl_dential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"Dentist Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        fl_dermatology = view.findViewById(R.id.fl_dermatology);
        fl_dermatology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"Dermatology Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        fl_endocrine = view.findViewById(R.id.fl_endocrine);
        fl_endocrine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"Endocrine Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        fl_ent = view.findViewById(R.id.fl_ent);
        fl_ent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"ENT Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        fl_gastrology = view.findViewById(R.id.fl_gastrology);
        fl_gastrology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"Gastrology Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        fl_physican = view.findViewById(R.id.fl_physician);
        fl_physican.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"General Physican Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        fl_gynaecology = view.findViewById(R.id.fl_gynaecology);
        fl_gynaecology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"Gynaecology  Clicked",Toast.LENGTH_SHORT).show();
            }
        });


        fl_nepthrology= view.findViewById(R.id.fl_nephrology);
        fl_nepthrology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"Nepthrology  Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        fl_neurology= view.findViewById(R.id.fl_neurology);
        fl_neurology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"Neurology  Clicked",Toast.LENGTH_SHORT).show();
            }
        });


        fl_oncology= view.findViewById(R.id.fl_oncology);
        fl_oncology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"Oncology  Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        fl_opthalmology= view.findViewById(R.id.fl_opthalmology);
        fl_opthalmology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"Opthalmology  Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        fl_orthopedic= view.findViewById(R.id.fl_orthopedic);
        fl_orthopedic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"Orthopedic  Clicked",Toast.LENGTH_SHORT).show();
            }
        });


        fl_paediatry= view.findViewById(R.id.fl_paediatry);
        fl_paediatry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"Paediatry  Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        fl_pathology= view.findViewById(R.id.fl_pathology);
        fl_pathology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"Pathology  Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        fl_psychiatry= view.findViewById(R.id.fl_psychiatry);
        fl_psychiatry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"Psychiatry  Clicked",Toast.LENGTH_SHORT).show();
            }
        });


        fl_pulmonology= view.findViewById(R.id.fl_pulmonology);
        fl_pulmonology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"Pulmonology  Clicked",Toast.LENGTH_SHORT).show();
            }
        });


        fl_radiology= view.findViewById(R.id.fl_radiology);
        fl_radiology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"Radiology  Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        fl_surgeon= view.findViewById(R.id.fl_radiology);
        fl_surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"Surgeon  Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        fl_urology= view.findViewById(R.id.fl_urology);
        fl_surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"Urology  Clicked",Toast.LENGTH_SHORT).show();
            }
        });














        return view;
    }


}
