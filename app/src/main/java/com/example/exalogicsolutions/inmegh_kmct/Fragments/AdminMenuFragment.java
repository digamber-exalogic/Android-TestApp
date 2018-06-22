package com.example.exalogicsolutions.inmegh_kmct.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.exalogicsolutions.inmegh_kmct.Activities.AdminDashboardActivity;
import com.example.exalogicsolutions.inmegh_kmct.Activities.AdminMenuCollegeInfo;
import com.example.exalogicsolutions.inmegh_kmct.Activities.AdminMenuSms;
import com.example.exalogicsolutions.inmegh_kmct.Activities.AdminMenuExam;
import com.example.exalogicsolutions.inmegh_kmct.Activities.AdminMenuHolidays;
import com.example.exalogicsolutions.inmegh_kmct.Activities.AdminMenuLeave;
import com.example.exalogicsolutions.inmegh_kmct.Activities.AdminMenuStudentAttendance;
import com.example.exalogicsolutions.inmegh_kmct.Activities.AdminMenuMail;
import com.example.exalogicsolutions.inmegh_kmct.Activities.AdminMenuTimetable;
import com.example.exalogicsolutions.inmegh_kmct.Activities.MailTabbedActivity;
import com.example.exalogicsolutions.inmegh_kmct.Adapters.MenuAdapter;
import com.example.exalogicsolutions.inmegh_kmct.Models.Menu;
import com.example.exalogicsolutions.inmegh_kmct.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminMenuFragment extends Fragment {

    MenuAdapter menuAdapter;
    ListView myModule_list;
    ArrayList<Menu> items;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_admin_menu, container, false);
        /*Log.i("myModule_list1", String.valueOf(myModule_list));*/
        myModule_list = view.findViewById(R.id.list_id);
        /*Log.i("myModule_list2", String.valueOf(myModule_list));*/

        items = new ArrayList<Menu>();

        items.add(new Menu("College Info", R.drawable.ic_domain_black_24dp));
        items.add(new Menu("Timetable", R.drawable.ic_alarm_black_24dp));
        items.add(new Menu("Leave", R.drawable.ic_content_paste_black_24dp));
        items.add(new Menu("Holidays", R.drawable.ic_holiday_black_24dp));
        items.add(new Menu("Exam", R.drawable.ic_exam_black_24dp));
        items.add(new Menu("Email", R.drawable.ic_email1_black_24dp));
        items.add(new Menu("Sms", R.drawable.ic_sms_black_24dp));
        items.add(new Menu("Student Attendance", R.drawable.ic_student_attendance));

        menuAdapter = new MenuAdapter(getActivity(), items);
        /*Log.i("myModule_list3", String.valueOf(myModule_list));*/
        myModule_list.setAdapter(menuAdapter);

        myModule_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if (position == 0) {

                    Intent intent = new Intent(getActivity(), AdminMenuCollegeInfo.class);
                    startActivity(intent);
                } else if (position == 1) {

                    Intent intent = new Intent(getActivity(), AdminMenuTimetable.class);
                    startActivity(intent);
                } else if (position == 2) {

                    Intent intent = new Intent(getActivity(), AdminMenuLeave.class);
                    startActivity(intent);
                } else if (position == 3) {

                    Intent intent = new Intent(getActivity(), AdminMenuHolidays.class);
                    startActivity(intent);
                } else if (position == 4) {

                    Intent intent = new Intent(getActivity(), AdminDashboardActivity.class);
                    startActivity(intent);
                } else if (position == 5) {

                    Intent intent = new Intent(getActivity(), AdminMenuMail.class);
                    startActivity(intent);
                } else if (position == 6) {

                    Intent intent = new Intent(getActivity(), AdminMenuSms.class);
                    startActivity(intent);
                } else if (position == 7) {

                    Intent intent = new Intent(getActivity(), AdminMenuStudentAttendance.class);
                    startActivity(intent);
                }
            }
        });

        return view;

    }
}
