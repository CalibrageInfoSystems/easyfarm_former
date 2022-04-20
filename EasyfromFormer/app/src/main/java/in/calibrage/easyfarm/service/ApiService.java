package in.calibrage.easyfarm.service;



import in.calibrage.easyfarm.model.AddUpdateServiceresponse;
import in.calibrage.easyfarm.model.DailyreportsResponse;
import in.calibrage.easyfarm.model.Forgotpasswordresponse;
import in.calibrage.easyfarm.model.GetActiveEncyclopediaCategoryDetails;
import in.calibrage.easyfarm.model.GetComplaintRepositoryResponse;
import in.calibrage.easyfarm.model.GetComplaintsByPlotCode;
import in.calibrage.easyfarm.model.GetComplaintsobject;
import in.calibrage.easyfarm.model.GetComplaintsresponse;
import in.calibrage.easyfarm.model.GetCropCycleDailyUploadresponse;
import in.calibrage.easyfarm.model.GetCropCycleDetailsByCode;
import in.calibrage.easyfarm.model.GetDistricts;
import in.calibrage.easyfarm.model.GetEncyclopediaDetails;
import in.calibrage.easyfarm.model.GetGender;
import in.calibrage.easyfarm.model.GetGeoBoundaries;
import in.calibrage.easyfarm.model.GetMandals;
import in.calibrage.easyfarm.model.GetOwnershipStatus;
import in.calibrage.easyfarm.model.GetPlotStatus;
import in.calibrage.easyfarm.model.GetPlotsByFarmerCode;
import in.calibrage.easyfarm.model.GetRoles;
import in.calibrage.easyfarm.model.GetServiceOrdersByFarmerId;
import in.calibrage.easyfarm.model.GetStates;
import in.calibrage.easyfarm.model.GetUserInfoByUserCode;
import in.calibrage.easyfarm.model.GetVendorServices;
import in.calibrage.easyfarm.model.GetVillages;

import com.google.gson.JsonObject;

import in.calibrage.easyfarm.model.GetVillagesbypincode;
import in.calibrage.easyfarm.model.Getcomplaints;
import in.calibrage.easyfarm.model.LoginResponse;
import in.calibrage.easyfarm.model.ReadNotificationById;
import in.calibrage.easyfarm.model.RegistrationResponse;
import in.calibrage.easyfarm.model.ResponseComplaint;
import in.calibrage.easyfarm.model.Termsheet;
import in.calibrage.easyfarm.model.UnreadCount;
import in.calibrage.easyfarm.model.getNotificationResponse;
import in.calibrage.easyfarm.model.getSoiltestreports;
import retrofit2.http.Body;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Completable;
import rx.Observable;

public interface ApiService {


    @GET
    Observable<GetStates> getstates(@Url String url);


    @GET
    Observable<GetDistricts> getdistricts(@Url String url);

    @GET
    Observable<GetMandals> getmandals(@Url String url);

    @GET
    Observable<GetVillages> getvillages(@Url String url);

    @GET
    Observable<GetVillagesbypincode> getvillagesbypincode(@Url String url);



    @GET
    Observable<GetRoles> getrole(@Url String url);

    @GET
    Observable<GetPlotStatus> getstatus(@Url String url);

    @GET
    Observable<GetOwnershipStatus> getownership(@Url String url);


    @GET
    Observable<GetUserInfoByUserCode> UserInfoByUserCode(@Url String url);

    @GET
    Observable<GetGender> getender(@Url String url);

    @GET
    Observable<GetPlotsByFarmerCode> getPlotsByFarmerCode(@Url String url);

    @GET
    Observable<getSoiltestreports> getSoildetailsbyplotcode(@Url String url);




    @POST(APIConstantURL.externalRegistration)
    Observable<RegistrationResponse> externalRegistrationPage(@Body JsonObject data);

    @POST(APIConstantURL.login_url)
    Observable<LoginResponse>getLoginPage (@Body JsonObject data);
    @POST(APIConstantURL.UpdateDeviseToken)
    Observable<DevisetokenResopnse>gettokenresponse(@Body JsonObject data);


    @GET
    Observable<GetActiveEncyclopediaCategoryDetails> getCategoryDetails(@Url String url);

    @POST(APIConstantURL.GetEncyclopediaDetails)
    Observable<GetEncyclopediaDetails> getEncyclopediaDetails(@Body JsonObject data);

    @GET
    Observable<Getcomplaints> getIssuestypes(@Url String url);


    @POST(APIConstantURL.post_Complaint)
    Observable<ResponseComplaint> postComplaint(@Body JsonObject data);

    @GET
    Observable<GetComplaintsByPlotCode> GetExitingcomplaints(@Url String url);

    @POST(APIConstantURL.Get_Complaint)
    Observable<GetComplaintsresponse> GetExitingcomplaints(@Body JsonObject data);

    @POST(APIConstantURL.Get_Complaintrepo)
    Observable<GetComplaintRepositoryResponse> Getcomplaintsfilerepo(@Body JsonObject data);

    @POST(APIConstantURL.getNotifications)
    Observable<getNotificationResponse> getNoticationRepo(@Body JsonObject data);

    @POST(APIConstantURL.Forgotpassword_url)
    Observable<Forgotpasswordresponse>getforgotpassowd (@Body JsonObject data);

    @GET
    Observable<Termsheet> Gettermsheet(@Url String url);
    @GET
    Observable<GetGeoBoundaries> Getgeoboundaries(@Url String url);


    @GET
    Observable<GetCropCycleDetailsByCode> getcropdetails(@Url String url);


    @POST(APIConstantURL.post_dailyreports)
    Observable<DailyreportsResponse> postdailyreports(@Body JsonObject data);
    @POST(APIConstantURL.get_dailyreports)
    Observable<GetCropCycleDailyUploadresponse>getdailyreports(@Body JsonObject data);


    @GET
    Observable<GetVendorServices> getVendorServicesByPlotCode(@Url String url);

    @GET
    Observable<ReadNotificationById> readNnotification(@Url String url);

    @GET
    Observable<UnreadCount> unreadCount(@Url String url);

    @POST(APIConstantURL.post_service)
    Observable<AddUpdateServiceresponse> postService(@Body JsonObject data);
    @GET
    Observable<GetServiceOrdersByFarmerId> GetServiceOrdersByFarmerId(@Url String url);






}
