package in.calibrage.easyfarm.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnreadCount {


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

        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("UserId")
        @Expose
        private Integer userId;
        @SerializedName("Desc")
        @Expose
        private String desc;
        @SerializedName("RaisedByUserId")
        @Expose
        private Integer raisedByUserId;
        @SerializedName("RaisedBy")
        @Expose
        private Object raisedBy;
        @SerializedName("IsRead")
        @Expose
        private Boolean isRead;
        @SerializedName("Header")
        @Expose
        private String header;
        @SerializedName("IsActive")
        @Expose
        private Boolean isActive;
        @SerializedName("NotificationTypeId")
        @Expose
        private Integer notificationTypeId;
        @SerializedName("CreatedByUserId")
        @Expose
        private Integer createdByUserId;
        @SerializedName("CreatedDate")
        @Expose
        private String createdDate;
        @SerializedName("UpdatedByUserId")
        @Expose
        private Integer updatedByUserId;
        @SerializedName("UpdatedDate")
        @Expose
        private String updatedDate;
        @SerializedName("CreatedBy")
        @Expose
        private String createdBy;
        @SerializedName("UpdatedBy")
        @Expose
        private String updatedBy;
        @SerializedName("NotificationType")
        @Expose
        private String notificationType;
        @SerializedName("NotificationGeneratedOn")
        @Expose
        private String notificationGeneratedOn;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public Integer getRaisedByUserId() {
            return raisedByUserId;
        }

        public void setRaisedByUserId(Integer raisedByUserId) {
            this.raisedByUserId = raisedByUserId;
        }

        public Object getRaisedBy() {
            return raisedBy;
        }

        public void setRaisedBy(Object raisedBy) {
            this.raisedBy = raisedBy;
        }

        public Boolean getIsRead() {
            return isRead;
        }

        public void setIsRead(Boolean isRead) {
            this.isRead = isRead;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
        }

        public Integer getNotificationTypeId() {
            return notificationTypeId;
        }

        public void setNotificationTypeId(Integer notificationTypeId) {
            this.notificationTypeId = notificationTypeId;
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

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

        public String getNotificationType() {
            return notificationType;
        }

        public void setNotificationType(String notificationType) {
            this.notificationType = notificationType;
        }

        public String getNotificationGeneratedOn() {
            return notificationGeneratedOn;
        }

        public void setNotificationGeneratedOn(String notificationGeneratedOn) {
            this.notificationGeneratedOn = notificationGeneratedOn;
        }

    }
}
