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

        return view;


    }




}
