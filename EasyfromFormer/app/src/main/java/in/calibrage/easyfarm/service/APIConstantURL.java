package in.calibrage.easyfarm.service;

public interface APIConstantURL {

public static  final  String LOCAL_URL="http://183.82.111.111/EasyFarm/API/api/";

    String Getstate ="State/GetStates/1/null";


    String Getdistricts ="Districts/GetDistricts/";

    String Getmandals ="Mandal/GetMandals/";

    String Getvillages ="Village/GetVillages/";

    String Getgender="TypeCdDmt/1";
    String GetPlotstatus = "TypeCdDmt/14";
    String GetPlotownership = "TypeCdDmt/16";

    String Getroles = "Role/GetAllRoles/null/true/true";


    String externalRegistration = "User/ExternalRegistration";
    String login_url = "User/MobileLogin";
    String UpdateDeviseToken = "User/UpdateDeviseTokenByUserId";
    String GetAddressByPinCode ="Village/GetAddressByPinCode/";

    String GetPlotsByFarmerCode = "Plots/GetPlotsByFarmerCode/";
    String GetUserInfoByUserCode ="User/GetUserInfoByUserCode/";
    String GetEncyclopediaDetails ="Encyclopedia/GetEncyclopediaDetails";
    String GetActiveEncyclopediaCategoryDetails ="LookUp/GetLookUpData/NULL/128";
    String GetsoildetailsplotCode = "Plots/GetPlotsByPlotCode/";

    String GetIssue ="TypeCdDmt/" ;
    String post_Complaint ="Complaints/AddUpdateComplaint";
    String Get_Complaint ="Complaints/GetFarmerComplaints";
    String Get_Complaintrepo ="Complaints/GetComplaintRepository";
    String Forgotpassword_url = "User/ForgotPassword";
    String GetComplaintsByPlotCode ="Complaints/GetComplaintsByPlotCode/";
    String GetTermsheet ="Plots/GetCropCycleInfo/NULL/";
    String GetGeoBoundaries ="Plots/GetGeoBoundaries/";
    String GetCropCycleDetailsByCode ="CropCycle/GetCropCycleDetailsByCode/";
    String getNotifications ="Notification/GetNotifications";


    String post_dailyreports ="CropCycle/AddUpdateCropCycleDailyUploads";
    String get_dailyreports ="CropCycle/GetCropCycleDailyUpload";
    String VendorServicesByPlotCode ="Vendor/GetVendorServicesByPlotCode/";
    String post_service ="Vendor/AddUpdateServiceOrder";
    String getServiceOrdersByFarmerId ="Vendor/GetServiceOrdersByFarmerId/";
    String readnotificationById ="Notification/ReadNotificationById/";
    String notificationunreadcount ="Notification/GetCount/";


}
