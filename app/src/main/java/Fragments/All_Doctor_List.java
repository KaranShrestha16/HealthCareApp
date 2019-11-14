package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import Adapter.DoctorAdapter;
import Model.DoctorModel;
import www.myandroidcode.mydoctor.R;

public class All_Doctor_List extends Fragment {


    private RecyclerView recyclerView;
    private DoctorAdapter doctorAdapter;
    private List<DoctorModel> doctorData;
    private EditText searchButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all__doctor__list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView_fragmentdoctor);
        doctorData = new ArrayList<>();

        doctorData.add(new DoctorModel("Dr. Karan Shrestha ", "MBBS MS (Pediatric Surgery & Neonatal Surgen)"));
        doctorData.add(new DoctorModel("Dr. Giri Raj Raut ", " MS- General Surgery: Kathmandu University Hospital"));
        doctorData.add(new DoctorModel("Dr. Rojin Miya ", " MS- General Surgery: Kathmandu University Hospita)"));
        doctorData.add(new DoctorModel("Dr. Rojin Miya ", " MS- General Surgery: Kathmandu University Hospita)"));
        doctorData.add(new DoctorModel("Dr. Bibek Lama", " MS- General Surgery: Kathmandu University Hospital"));
        doctorData.add(new DoctorModel("Dr. Yubraj Shrestha ", " MS- General Surgery: Kathmandu University Hospital"));
        doctorData.add(new DoctorModel("Dr. Kritika Shrestha ", " MS- General Surgery: Kathmandu University Hospital"));
        doctorData.add(new DoctorModel("Dr. Prakriti Lama ", " MBBS MS (Pediatric Surgery & Neonatal Surgen)"));
        doctorData.add(new DoctorModel("Dr. Dimphu Lama ", " MS- General Surgery: Kathmandu University Hospital"));
        doctorData.add(new DoctorModel("Dr. Kishowr Raut ", " MBBS MS (Pediatric Surgery & Neonatal Surgen)"));
        doctorData.add(new DoctorModel("Dr. Mahesh Shretha ", " MS- General Surgery: Kathmandu University Hospital"));
        doctorData.add(new DoctorModel("Dr. Raman Fadera ", " MS- General Surgery: Kathmandu University Hospital"));
        doctorData.add(new DoctorModel("Dr. Arjun Shrestha ", " MBBS MS (Pediatric Surgery & Neonatal Surgen)"));
        doctorData.add(new DoctorModel("Dr. Rishave Shresthas ", " MS- General Surgery: Kathmandu University Hospital"));
        doctorData.add(new DoctorModel("Dr. Namarata Rai ", " MS- General Surgery: Kathmandu University Hospital"));
        doctorData.add(new DoctorModel("Dr. Sagar Shrestha ", " MBBS MS (Pediatric Surgery & Neonatal Surgen)"));
        doctorData.add(new DoctorModel("Dr. Anu Lama ", " MS- General Surgery: Kathmandu University Hospital"));
        doctorData.add(new DoctorModel("Dr. Raju Lama ", " MBBS MS (Pediatric Surgery & Neonatal Surgen"));

        doctorAdapter= new DoctorAdapter(getContext(),doctorData);
        recyclerView.setAdapter(doctorAdapter);
        recyclerView.setLayoutManager( new LinearLayoutManager(getContext()));
        searchButton = view.findViewById(R.id.txt_searchfragment);
        searchButton.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                doctorAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




        return view;


    }




}
