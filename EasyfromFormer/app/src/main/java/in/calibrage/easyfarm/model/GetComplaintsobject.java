package in.calibrage.easyfarm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetComplaintsobject {

    @SerializedName("UserId")
    @Expose
    private Integer userId;
    @SerializedName("StatusTypeId")
    @Expose
    private Integer statusTypeId;
    @SerializedName("ComplaintTypeId")
    @Expose
    private Object complaintTypeId;
    @SerializedName("FromDate")
    @Expose
    private Object fromDate;
    @SerializedName("ToDate")
    @Expose
    private Object toDate;
    @SerializedName("CanAssign")
    @Expose
    private Boolean canAssign;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatusTypeId() {
        return statusTypeId;
    }

    public void setStatusTypeId(Integer statusTypeId) {
        this.statusTypeId = statusTypeId;
    }

    public Object getComplaintTypeId() {
        return complaintTypeId;
    }

    public void setComplaintTypeId(Object complaintTypeId) {
        this.complaintTypeId = complaintTypeId;
    }

    public Object getFromDate() {
        return fromDate;
    }

    public void setFromDate(Object fromDate) {
        this.fromDate = fromDate;
    }

    public Object getToDate() {
        return toDate;
    }

    public void setToDate(Object toDate) {
        this.toDate = toDate;
    }

    public Boolean getCanAssign() {
        return canAssign;
    }

    public void setCanAssign(Boolean canAssign) {
        this.canAssign = canAssign;
    }

}
