package in.calibrage.easyfarm.model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Termsheet {

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
        @SerializedName("PlotCode")
        @Expose
        private String plotCode;
        @SerializedName("CycleCode")
        @Expose
        private String cycleCode;
        @SerializedName("StatusTypeId")
        @Expose
        private Integer statusTypeId;
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
        @SerializedName("CropInfoId")
        @Expose
        private Integer cropInfoId;
        @SerializedName("MinDuration")
        @Expose
        private Integer minDuration;
        @SerializedName("MaxDuration")
        @Expose
        private Integer maxDuration;
        @SerializedName("Amount")
        @Expose
        private Double amount;
        @SerializedName("FileName")
        @Expose
        private String fileName;
        @SerializedName("FileLocation")
        @Expose
        private String fileLocation;
        @SerializedName("FileExtention")
        @Expose
        private String fileExtention;
        @SerializedName("CropId")
        @Expose
        private Integer cropId;
        @SerializedName("CropName")
        @Expose
        private String cropName;
        @SerializedName("SeedId")
        @Expose
        private Integer seedId;
        @SerializedName("SeedName")
        @Expose
        private String seedName;
        @SerializedName("SeasonTypeId")
        @Expose
        private Integer seasonTypeId;
        @SerializedName("SeasonName")
        @Expose
        private String seasonName;
        @SerializedName("DistrictId")
        @Expose
        private Integer districtId;
        @SerializedName("DistrictName")
        @Expose
        private String districtName;
        @SerializedName("State")
        @Expose
        private String state;
        @SerializedName("Country")
        @Expose
        private String country;
        @SerializedName("Status")
        @Expose
        private String status;
        @SerializedName("TermSheet")
        @Expose
        private String termSheet;
        @SerializedName("CreatedBy")
        @Expose
        private String createdBy;
        @SerializedName("UpdatedBy")
        @Expose
        private String updatedBy;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getPlotCode() {
            return plotCode;
        }

        public void setPlotCode(String plotCode) {
            this.plotCode = plotCode;
        }

        public String getCycleCode() {
            return cycleCode;
        }

        public void setCycleCode(String cycleCode) {
            this.cycleCode = cycleCode;
        }

        public Integer getStatusTypeId() {
            return statusTypeId;
        }

        public void setStatusTypeId(Integer statusTypeId) {
            this.statusTypeId = statusTypeId;
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

        public Integer getCropInfoId() {
            return cropInfoId;
        }

        public void setCropInfoId(Integer cropInfoId) {
            this.cropInfoId = cropInfoId;
        }

        public Integer getMinDuration() {
            return minDuration;
        }

        public void setMinDuration(Integer minDuration) {
            this.minDuration = minDuration;
        }

        public Integer getMaxDuration() {
            return maxDuration;
        }

        public void setMaxDuration(Integer maxDuration) {
            this.maxDuration = maxDuration;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileLocation() {
            return fileLocation;
        }

        public void setFileLocation(String fileLocation) {
            this.fileLocation = fileLocation;
        }

        public String getFileExtention() {
            return fileExtention;
        }

        public void setFileExtention(String fileExtention) {
            this.fileExtention = fileExtention;
        }

        public Integer getCropId() {
            return cropId;
        }

        public void setCropId(Integer cropId) {
            this.cropId = cropId;
        }

        public String getCropName() {
            return cropName;
        }

        public void setCropName(String cropName) {
            this.cropName = cropName;
        }

        public Integer getSeedId() {
            return seedId;
        }

        public void setSeedId(Integer seedId) {
            this.seedId = seedId;
        }

        public String getSeedName() {
            return seedName;
        }

        public void setSeedName(String seedName) {
            this.seedName = seedName;
        }

        public Integer getSeasonTypeId() {
            return seasonTypeId;
        }

        public void setSeasonTypeId(Integer seasonTypeId) {
            this.seasonTypeId = seasonTypeId;
        }

        public String getSeasonName() {
            return seasonName;
        }

        public void setSeasonName(String seasonName) {
            this.seasonName = seasonName;
        }

        public Integer getDistrictId() {
            return districtId;
        }

        public void setDistrictId(Integer districtId) {
            this.districtId = districtId;
        }

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTermSheet() {
            return termSheet;
        }

        public void setTermSheet(String termSheet) {
            this.termSheet = termSheet;
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

    }}