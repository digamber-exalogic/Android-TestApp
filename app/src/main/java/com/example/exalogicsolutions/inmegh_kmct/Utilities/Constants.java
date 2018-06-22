package com.example.exalogicsolutions.inmegh_kmct.Utilities;

import android.content.Context;

public class Constants {

    //    public static final String Host = "http://kmct-testing.herokuapp.com/api/v1";
//    public static final String HostImage = "http://kmct-testing.herokuapp.com/";
    public static final String Host = "http://13.127.44.70/api/v1";
    public static final String HostImage = "http://13.127.44.70/";
    /*public static final String Host = "http://13.127.44.70/api/v1";
    public static final String HostImage = "http://13.127.44.70";*/
    /*public static final String Host = "http://192.168.1.46:4000/api/v1";
    public static final String HostImage = "http://192.168.1.46:4000/";*/
    public static final String CITY = "city";

    public static final String SECTION = "sec";
    public static final String STANDARD = "std";

    public static final String NAME = "name";
    public static final String ADRESS = "adress";

    public static final String TRIP_STUDENT = "trip_student";

    public static final String STATUSTRUE = "true";
    public static final String STATUSFALSE = "false";
    public static final String ONWARD = "Onward";
    public static final String LEAVEID = "leaveid";
    public static final String REMARK = "Remark";
    public static final String STATUS = "true";
    public static final String REASON = "Reason";
    public static final String FROMDATE = "27-06-2000";
    public static final String ENDDATE = "25-06-2000";
    public static final String HALFDAY = "No";
    public static final String LEAVETYPE = "Casual Leave";
    public static final int ADMISSIONNO = 1;

    public static final String YEARFRAGMENT = "topics";
    public static final String MONTHFRAGMENT = "sub_topics";
    public static final String DAILYFRAGMENT = "daily_topics";
    public static final String ADMINATTENDANCECHARACTEREMP = "Employee";
    public static final String ADMINATTENDANCECHARACTERSTU = "Student";
    public static final int SUCCESS = 200;
    public static final int SWR = 303;
    public static final int NDF = 404;
    public static final int CMF = 206;
    public static final int ON_TRIP = 2;
    public static final int ERROR = 0;
    public static final String FLAGTRUE = "true";
    public static final String FLAGFALSE = "false";

    public static final boolean TRUE = true;
    public static final boolean FALSE = false;

    public static final double NOT_SELECTED = 0;
    public static final double ON_PROGRESS = 1;
    public static final double COMPLETTED = 2;

    public static final int NULL_REMARK = 0;
    public static final int UPDATE_REMARK = 1;
    public static final int DATA_REMARK = 2;
    public static final int NORMAL_REMARK = 3;

    public static final int STATUSCOMPLETED = 1;
    public static final int STATUSNOTCOMPLETED = 0;
    public static final int PRESENT = 1;
    public static final int ABSENT = 2;
    public static final int NOT_MARK = 3;
    public static final int HOLIDAY = 4;
    public static final int WEEKNOTASSIGN = 9;
    public static final int FUTUREDATE = 5;
    public static final int STUDENTTIMETABLENOTASSIGN = 8;
    public static final int WORKDAYPREVIOUS = 10;
    public static final int HOLIDAYMARK = 11;
    public static final int STUDENTWORKNOTASSIGN = 7;
    public static final int NOWORKDAYTODAY = 3;
    public static final int LEAVEAPPLIEDATTENDANCE = 12;
    public static final int PREVIOUSDAYATTENDANCE = 13;
    public static final int LEAVEPENDINGATTENDANCE = 14;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 110;
    public static Context context;
    public static final String TEACHING = "Teaching";
    public static final String CLASS_TEACHER = "class teacher";

    public static final int SENT = 1;

    public static class Pref {
        public static final String KEY_REMARK = "abc";
        public static final String KEY_USERNAME = "user_name";
        public static final String KEY_EMAIL = "email";
        public static final String KEY_IMAGE = "image";
        public static final String KEY_TOKEN = "token";
        public static final String KEY_USER_TYPE = "user_type";
        public static final String KEY_USER_SUB_TYPE = "user_sub_type";
        public static final String KEY_TIME_TABLE_DOWNLOAD_OWN = "is_time_table_own";
        public static final String KEY_TIME_TABLE_DOWNLOAD_CLASS = "is_time_table_class";
        public static final String KEY_CURRENT_TIME_TABLE_STRUCTURE_ID = "current_structure_id";
        public static final String KEY_CURRENT_TIMESTAMP = "current_timestamp";
        public static final String KEY_FCM_REG_ID = "fcm_reg_id";
        public static final String KEY_CHAT_OPEN = "chat_open";

    }

    public static class Fragment {
        public static final String DEALER = "Dealer";
        public static final String FAVORITES = "FAVORITES";
        public static final String HISTORY = "HISTORY";
        public static final String HOME = "HOME";
        public static final String MOLARITY_CALCULATOR = "MOLARITY_CALCULATOR";
    }

    public static class Units {
        public static final double VALUE_OF_G_L = 1.0;
        public static final double VALUE_OF_MG_ML = 0.001;
        public static final double VALUE_OF_MICRO = 0.000001;
    }
}
