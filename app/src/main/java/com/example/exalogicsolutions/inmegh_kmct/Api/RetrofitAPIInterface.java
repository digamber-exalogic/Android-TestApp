package com.example.exalogicsolutions.inmegh_kmct.Api;

import com.example.exalogicsolutions.inmegh_kmct.Models.CollegeInfoResponse.CollegeInfo;
import com.example.exalogicsolutions.inmegh_kmct.Models.EmailResponse.ComposemailResponse;
import com.example.exalogicsolutions.inmegh_kmct.Models.EmailResponse.EmailReadResponse;
import com.example.exalogicsolutions.inmegh_kmct.Models.EmailResponse.MailResponse;
import com.example.exalogicsolutions.inmegh_kmct.Models.EventResponse.AdminEventViewResponse;
import com.example.exalogicsolutions.inmegh_kmct.Models.EventResponse.ShowEvent.ShowResponse;
import com.example.exalogicsolutions.inmegh_kmct.Models.HolidayResponse.HolidayDeleteResponse;
import com.example.exalogicsolutions.inmegh_kmct.Models.HolidayResponse.HolidayView;
import com.example.exalogicsolutions.inmegh_kmct.Models.HolidayResponse.HolidayViewStatus;
import com.example.exalogicsolutions.inmegh_kmct.Models.LeaveResponse.AdminLeaveResponse;
import com.example.exalogicsolutions.inmegh_kmct.Models.NoticeBoardResponse.AdminNoticeResponse;
import com.example.exalogicsolutions.inmegh_kmct.Models.SignInResponse;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.QueryMap;

public interface RetrofitAPIInterface {

    @POST("/registrations/sign_in")
    @Headers({
            "Accept: application/json"
    })
    public void signIn(@Body JsonObject loginDetails, Callback<SignInResponse> status);

    @GET("/indexes/college_info")
    public void getCollegeInfo(Callback<CollegeInfo> collegeInfoCallback);

    @GET("/holidays")
    public void holidays(Callback<HolidayViewStatus> holidayviewstatusmodelCallback);

    @GET("/holidays/delete")
    public void holidaydelete(@QueryMap Map<String, String> params, Callback<HolidayDeleteResponse> holidaydeleteresponseCallback);

    @POST("/holidays")
    public void CreateHoliday(@Body JsonObject message, Callback<JsonObject> jsonObjectCallback);

    @POST("/holidays/update_holiday")
    public void updateHoliday(@Body JsonObject message, Callback<JsonObject> jsonObjectCallback);

    @GET("/registrations/sign_out")
    public void logout(Callback<JSONObject> logout);

    @GET("/leave_applications")
    public void getAdminLeaveDetails(Callback<AdminLeaveResponse> activeSubmissionResponseCallback);

    @GET("/leave_applications/approve")
    public void adminleaveapproved(@QueryMap Map<String, String> params, Callback<AdminLeaveResponse> adminLeaveResponseCallback);

    @GET("/leave_applications/reject")
    public void adminleaveReject(@QueryMap Map<String, String> params, Callback<AdminLeaveResponse> adminLeaveResponseCallback);

   /* @GET("/events/delete_event")
    public void deleteEventView(@QueryMap Map<String, String> params, Callback<JsonObject> jsonObjectCallback);*/

    @GET("/events/show_event")
    public void showeventView(@QueryMap Map<String, String> params, Callback<ShowResponse> jsonObjectCallback);

    /*@GET("/grievances/discussion_forum")
    public void getDiscussionIndex(@QueryMap Map<String, String> params, Callback<DiscussionIndexResponse> discussionIndexResponseCallback);*/

    @GET("/events")
    public void getEventViewList(@QueryMap Map<String, String> params, Callback<AdminEventViewResponse> discussionIndexResponseCallback);

    @GET("/indexes/notice_board")
    public void getNoticeBoard(Callback<AdminNoticeResponse> noticeBoardResponseCallback);

    @POST("/emails/sent_mail_delete")
    public void sentdeleteMail(@QueryMap Map<String, String> params, Callback<JsonObject> jsonObjectCallback);

    @POST("/emails/trash_mail_delete")
    public void trashdeleteMail(@QueryMap Map<String, String> params, Callback<JsonObject> todoJson);

    @POST("/emails/email_detail_view")
    public void getReadMailDetails(@QueryMap Map<String, String> params, Callback<EmailReadResponse> collegeInfoCallback);

    @GET("/emails/user_emails")
    public void getComposedetails(Callback<ComposemailResponse> composemailResponseCallback);

    @GET("/emails/mail_box")
    public void getMaildetails(Callback<MailResponse> mailResponseCallback);

    @POST("/emails/move_to_trash")
    public void deleteMail(@QueryMap Map<String, String> params, Callback<JsonObject> todoJson);

    @POST("/emails/send_email")
    public void OnSend(@Body JsonObject message, Callback<JsonObject> jsonObjectCallback);

    @POST("/directories/students_directory")
    public void getStudentSmsDetails(@QueryMap Map<String, String> params, Callback<EmailReadResponse> collegeInfoCallback);

    @POST("/directories/parents_directory")
    public void getParentSmsDetails(@QueryMap Map<String, String> params, Callback<EmailReadResponse> collegeInfoCallback);

    @POST("/directories/employees_directory")
    public void getEmployeeSmsDetails(@QueryMap Map<String, String> params, Callback<EmailReadResponse> collegeInfoCallback);

    @POST("/sent_messages/sending_messages_for_students")
    public void getStudentSendSms(@QueryMap Map<String, String> params, Callback<EmailReadResponse> collegeInfoCallback);

    @POST("/sent_messages/sending_messages_for_parents")
    public void getParentSendSms(@QueryMap Map<String, String> params, Callback<EmailReadResponse> collegeInfoCallback);

    @POST("/sent_messages/sending_messages_for_employees")
    public void getEmployeeSendSms(@QueryMap Map<String, String> params, Callback<EmailReadResponse> collegeInfoCallback);
}
