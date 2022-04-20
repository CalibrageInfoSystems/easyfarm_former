package in.calibrage.easyfarm.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetComplaintsresponse {

    @SerializedName("ListResult")
    @Expose
    private List<ListResult> listResult = null;
    @SerializedName("IsSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("AffectedRecords")
    @Expose
    private Integer affectedRecords;
    @SerializedName("EndUserMessage")
    @Expose
    private String endUserMessage;
    @SerializedName("ValidationErrors")
    @Expose
    private List<Object> validationErrors = null;
    @SerializedName("Exception")
    @Expose
    private Object exception;

    public List<ListResult> getListResult() {
        return listResult;
    }

    public void setListResult(List<ListResult> listResult) {
        this.listResult = listResult;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getAffectedRecords() {
        return affectedRecords;
    }

    public void setAffectedRecords(Integer affectedRecords) {
        this.affectedRecords = affectedRecords;
    }

    public String getEndUserMessage() {
        return endUserMessage;
    }

    public void setEndUserMessage(String endUserMessage) {
        this.endUserMessage = endUserMessage;
    }

    public List<Object> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<Object> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Object getException() {
        return exception;
    }

    public void setException(Object exception) {
        this.exception = exception;
    }

public class ListResult {

    @SerializedName("PlotCode")
    @Expose
    private String plotCode;
    @SerializedName("FarmerCode")
    @Expose
    private String farmerCode;
    @SerializedName("FarmerName")
    @Expose
    private String farmerName;
    @SerializedName("PlotSize")
    @Expose
    private Double plotSize;
    @SerializedName("StateId")
    @Expose
    private Integer stateId;
    @SerializedName("StateCode")
    @Expose
    private String stateCode;
    @SerializedName("StateName")
    @Expose
    private String stateName;
    @SerializedName("DistrictId")
    @Expose
    private Integer districtId;
    @SerializedName("DistrictCode")
    @Expose
    private String districtCode;
    @SerializedName("DistrictName")
    @Expose
    private String districtName;
    @SerializedName("MandalId")
    @Expose
    private Integer mandalId;
    @SerializedName("MandalCode")
    @Expose
    private String mandalCode;
    @SerializedName("MandalName")
    @Expose
    private String mandalName;
    @SerializedName("ClusterId")
    @Expose
    private Integer clusterId;
    @SerializedName("ClusterCode")
    @Expose
    private String clusterCode;
    @SerializedName("Cluster")
    @Expose
    private String cluster;
    @SerializedName("VillageId")
    @Expose
    private Integer villageId;
    @SerializedName("VillageCode")
    @Expose
    private String villageCode;
    @SerializedName("VillageName")
    @Expose
    private String villageName;
    @SerializedName("ComplaintCode")
    @Expose
    private String complaintCode;
    @SerializedName("ComplaintId")
    @Expose
    private Integer complaintId;
    @SerializedName("ComplaintDate")
    @Expose
    private String complaintDate;
    @SerializedName("ComplaintRaisedBy")
    @Expose
    private String complaintRaisedBy;
    @SerializedName("ComplaintTypeId")
    @Expose
    private String complaintTypeId;
    @SerializedName("ComplaintType")
    @Expose
    private String complaintType;
    @SerializedName("AssignedTo")
    @Expose
    private Object assignedTo;
    @SerializedName("AssignToUserId")
    @Expose
    private Integer assignToUserId;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("StatusTypeId")
    @Expose
    private Integer statusTypeId;
    @SerializedName("StatusChangedDate")
    @Expose
    private String statusChangedDate;
    @SerializedName("IsImage")
    @Expose
    private Integer isImage;
    @SerializedName("IsVoiceRecording")
    @Expose
    private Integer isVoiceRecording;
    @SerializedName("Comments")
    @Expose
    private String comments;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("ServerUpdatedStatus")
    @Expose
    private Boolean serverUpdatedStatus;
    @SerializedName("RaisedByUserId")
    @Expose
    private Integer raisedByUserId;

    public String getPlotCode() {
        return plotCode;
    }

    public void setPlotCode(String plotCode) {
        this.plotCode = plotCode;
    }

    public String getFarmerCode() {
        return farmerCode;
    }

    public void setFarmerCode(String farmerCode) {
        this.farmerCode = farmerCode;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public Double getPlotSize() {
        return plotSize;
    }

    public void setPlotSize(Double plotSize) {
        this.plotSize = plotSize;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Integer getMandalId() {
        return mandalId;
    }

    public void setMandalId(Integer mandalId) {
        this.mandalId = mandalId;
    }

    public String getMandalCode() {
        return mandalCode;
    }

    public void setMandalCode(String mandalCode) {
        this.mandalCode = mandalCode;
    }

    public String getMandalName() {
        return mandalName;
    }

    public void setMandalName(String mandalName) {
        this.mandalName = mandalName;
    }

    public Integer getClusterId() {
        return clusterId;
    }

    public void setClusterId(Integer clusterId) {
        this.clusterId = clusterId;
    }

    public String getClusterCode() {
        return clusterCode;
    }

    public void setClusterCode(String clusterCode) {
        this.clusterCode = clusterCode;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public Integer getVillageId() {
        return villageId;
    }

    public void setVillageId(Integer villageId) {
        this.villageId = villageId;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getComplaintCode() {
        return complaintCode;
    }

    public void setComplaintCode(String complaintCode) {
        this.complaintCode = complaintCode;
    }

    public Integer getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Integer complaintId) {
        this.complaintId = complaintId;
    }

    public String getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(String complaintDate) {
        this.complaintDate = complaintDate;
    }

    public String getComplaintRaisedBy() {
        return complaintRaisedBy;
    }

    public void setComplaintRaisedBy(String complaintRaisedBy) {
        this.complaintRaisedBy = complaintRaisedBy;
    }

    public String getComplaintTypeId() {
        return complaintTypeId;
    }

    public void setComplaintTypeId(String complaintTypeId) {
        this.complaintTypeId = complaintTypeId;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public Object getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Object assignedTo) {
        this.assignedTo = assignedTo;
    }

    public int getAssignToUserId() {
        return assignToUserId;
    }

    public void setAssignToUserId(Integer assignToUserId) {
        this.assignToUserId = assignToUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatusTypeId() {
        return statusTypeId;
    }

    public void setStatusTypeId(Integer statusTypeId) {
        this.statusTypeId = statusTypeId;
    }

    public String getStatusChangedDate() {
        return statusChangedDate;
    }

    public void setStatusChangedDate(String statusChangedDate) {
        this.statusChangedDate = statusChangedDate;
    }

    public Integer getIsImage() {
        return isImage;
    }

    public void setIsImage(Integer isImage) {
        this.isImage = isImage;
    }

    public Integer getIsVoiceRecording() {
        return isVoiceRecording;
    }

    public void setIsVoiceRecording(Integer isVoiceRecording) {
        this.isVoiceRecording = isVoiceRecording;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getServerUpdatedStatus() {
        return serverUpdatedStatus;
    }

    public void setServerUpdatedStatus(Boolean serverUpdatedStatus) {
        this.serverUpdatedStatus = serverUpdatedStatus;
    }

    public Integer getRaisedByUserId() {
        return raisedByUserId;
    }

    public void setRaisedByUserId(Integer raisedByUserId) {
        this.raisedByUserId = raisedByUserId;
    }
}}