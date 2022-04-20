package in.calibrage.easyfarm.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Complaintobject {

    @SerializedName("AssignToUserId")
    @Expose
    private Integer assignToUserId;
    @SerializedName("Code")
    @Expose
    private Object code;
    @SerializedName("Comments")
    @Expose
    private String comments;
    @SerializedName("ComplaintTypeIds")
    @Expose
    private String complaintTypeIds;
    @SerializedName("CreatedByUserId")
    @Expose
    private Integer createdByUserId;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("PlotCode")
    @Expose
    private String plotCode;
    @SerializedName("RepoFiles")
    @Expose
    private List<RepoFile> repoFiles = null;
    @SerializedName("ServerUpdatedStatus")
    @Expose
    private Boolean serverUpdatedStatus;
    @SerializedName("StatusTypeId")
    @Expose
    private Integer statusTypeId;
    @SerializedName("UpdatedByUserId")
    @Expose
    private Integer updatedByUserId;
    @SerializedName("UpdatedDate")
    @Expose
    private String updatedDate;

    public Integer getAssignToUserId() {
        return assignToUserId;
    }

    public void setAssignToUserId(Integer assignToUserId) {
        this.assignToUserId = assignToUserId;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComplaintTypeIds() {
        return complaintTypeIds;
    }

    public void setComplaintTypeIds(String complaintTypeIds) {
        this.complaintTypeIds = complaintTypeIds;
    }

    public Integer getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Integer createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getPlotCode() {
        return plotCode;
    }

    public void setPlotCode(String plotCode) {
        this.plotCode = plotCode;
    }

    public List<RepoFile> getRepoFiles() {
        return repoFiles;
    }

    public void setRepoFiles(List<RepoFile> repoFiles) {
        this.repoFiles = repoFiles;
    }

    public Boolean getServerUpdatedStatus() {
        return serverUpdatedStatus;
    }

    public void setServerUpdatedStatus(Boolean serverUpdatedStatus) {
        this.serverUpdatedStatus = serverUpdatedStatus;
    }

    public Integer getStatusTypeId() {
        return statusTypeId;
    }

    public void setStatusTypeId(Integer statusTypeId) {
        this.statusTypeId = statusTypeId;
    }

    public Integer getUpdatedByUserId() {
        return updatedByUserId;
    }

    public void setUpdatedByUserId(Integer updatedByUserId) {
        this.updatedByUserId = updatedByUserId;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }



public static class RepoFile {

    @SerializedName("FileBytes")
    @Expose
    private String fileBytes;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("ComplaintCode")
    @Expose
    private Object complaintCode;
    @SerializedName("FileName")
    @Expose
    private Object fileName;
    @SerializedName("FileLocation")
    @Expose
    private Object fileLocation;
    @SerializedName("FileExtension")
    @Expose
    private String fileExtension;
    @SerializedName("IsAudioRecording")
    @Expose
    private Boolean isAudioRecording;
    @SerializedName("IsResult")
    @Expose
    private Boolean isResult;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("CreatedByUserId")
    @Expose
    private Integer createdByUserId;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("UpdatedByUserId")
    @Expose
    private Object updatedByUserId;
    @SerializedName("UpdatedDate")
    @Expose
    private String updatedDate;
    @SerializedName("ServerUpdatedStatus")
    @Expose
    private Boolean serverUpdatedStatus;

    public String getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(String fileBytes) {
        this.fileBytes = fileBytes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getComplaintCode() {
        return complaintCode;
    }

    public void setComplaintCode(Object complaintCode) {
        this.complaintCode = complaintCode;
    }

    public Object getFileName() {
        return fileName;
    }

    public void setFileName(Object fileName) {
        this.fileName = fileName;
    }

    public Object getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(Object fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public Boolean getIsAudioRecording() {
        return isAudioRecording;
    }

    public void setIsAudioRecording(Boolean isAudioRecording) {
        this.isAudioRecording = isAudioRecording;
    }

    public Boolean getIsResult() {
        return isResult;
    }

    public void setIsResult(Boolean isResult) {
        this.isResult = isResult;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Integer createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Object getUpdatedByUserId() {
        return updatedByUserId;
    }

    public void setUpdatedByUserId(Object updatedByUserId) {
        this.updatedByUserId = updatedByUserId;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Boolean getServerUpdatedStatus() {
        return serverUpdatedStatus;
    }

    public void setServerUpdatedStatus(Boolean serverUpdatedStatus) {
        this.serverUpdatedStatus = serverUpdatedStatus;
    }
    public RepoFile( ) {
        this.id = id;
        this.fileBytes = fileBytes;
        this.fileLocation = fileLocation;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.isActive = isActive;
        this.isAudioRecording = isAudioRecording;
        this.isResult =isResult;
        this.serverUpdatedStatus =serverUpdatedStatus;
        this.complaintCode =complaintCode;
        this.createdByUserId = createdByUserId;
        this.createdDate = createdDate;
        this.updatedByUserId=updatedByUserId;
        this.updatedDate=updatedDate;
    }

}

    public Complaintobject( List<RepoFile> visitRepo) {

        this.repoFiles = visitRepo;
    }

    public Complaintobject() {
    }
}