package com.example.exalogicsolutions.inmegh_kmct.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.exalogicsolutions.inmegh_kmct.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeachersFragment extends Fragment {
    private AppCompatSpinner spinCourse, spinDepartment;
    private AppCompatCheckBox chkAll;
    private RecyclerView rcvSmsTeacher;


    public TeachersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teachers, container, false);

        spinCourse = view.findViewById(R.id.spinCourse);
        spinDepartment = view.findViewById(R.id.spinDepartment);
        chkAll = view.findViewById(R.id.chkAll);
        rcvSmsTeacher = view.findViewById(R.id.rcvSmsTeacher);

        return view;
    }

}
