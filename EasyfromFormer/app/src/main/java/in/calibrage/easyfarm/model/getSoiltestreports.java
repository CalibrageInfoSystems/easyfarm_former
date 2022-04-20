package in.calibrage.easyfarm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getSoiltestreports {

    @SerializedName("Result")
    @Expose
    private Result result;
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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
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


    public class Result {

        @SerializedName("PlotDetails")
        @Expose
        private PlotDetails plotDetails;
        @SerializedName("SoilDetails")
        @Expose
        private SoilDetails soilDetails;
        @SerializedName("PowerDetails")
        @Expose
        private PowerDetails powerDetails;
        @SerializedName("IrrigationDetails")
        @Expose
        private IrrigationDetails irrigationDetails;
        @SerializedName("SoilTestDetails")
        @Expose
        private List<SoilTestDetail> soilTestDetails = null;
        @SerializedName("CropCycleInfo")
        @Expose
        private List<CropCycleInfo> cropCycleInfo = null;

        public PlotDetails getPlotDetails() {
            return plotDetails;
        }

        public void setPlotDetails(PlotDetails plotDetails) {
            this.plotDetails = plotDetails;
        }

        public SoilDetails getSoilDetails() {
            return soilDetails;
        }

        public void setSoilDetails(SoilDetails soilDetails) {
            this.soilDetails = soilDetails;
        }

        public PowerDetails getPowerDetails() {
            return powerDetails;
        }

        public void setPowerDetails(PowerDetails powerDetails) {
            this.powerDetails = powerDetails;
        }

        public IrrigationDetails getIrrigationDetails() {
            return irrigationDetails;
        }

        public void setIrrigationDetails(IrrigationDetails irrigationDetails) {
            this.irrigationDetails = irrigationDetails;
        }

        public List<SoilTestDetail> getSoilTestDetails() {
            return soilTestDetails;
        }

        public void setSoilTestDetails(List<SoilTestDetail> soilTestDetails) {
            this.soilTestDetails = soilTestDetails;
        }

        public List<CropCycleInfo> getCropCycleInfo() {
            return cropCycleInfo;
        }

        public void setCropCycleInfo(List<CropCycleInfo> cropCycleInfo) {
            this.cropCycleInfo = cropCycleInfo;
        }
    }

    public class IrrigationDetails {

        @SerializedName("IrrigationType")
        @Expose
        private String irrigationType;
        @SerializedName("CreatedBy")
        @Expose
        private String createdBy;
        @SerializedName("UpdatedBy")
        @Expose
        private String updatedBy;
        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("PlotCode")
        @Expose
        private String plotCode;
        @SerializedName("IrrigationTypeId")
        @Expose
        private Integer irrigationTypeId;
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
        private Integer updatedByUserId;
        @SerializedName("UpdatedDate")
        @Expose
        private String updatedDate;
        @SerializedName("ServerUpdatedStatus")
        @Expose
        private Boolean serverUpdatedStatus;

        public String getIrrigationType() {
            return irrigationType;
        }

        public void setIrrigationType(String irrigationType) {
            this.irrigationType = irrigationType;
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

        public Integer getIrrigationTypeId() {
            return irrigationTypeId;
        }

        public void setIrrigationTypeId(Integer irrigationTypeId) {
            this.irrigationTypeId = irrigationTypeId;
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

        public Boolean getServerUpdatedStatus() {
            return serverUpdatedStatus;
        }

        public void setServerUpdatedStatus(Boolean serverUpdatedStatus) {
            this.serverUpdatedStatus = serverUpdatedStatus;
        }

    }


    public class PlotDetails {

        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("Code")
        @Expose
        private String code;
        @SerializedName("FarmerCode")
        @Expose
        private String farmerCode;
        @SerializedName("TotalPlotArea")
        @Expose
        private Double totalPlotArea;
        @SerializedName("AdaptedArea")
        @Expose
        private Double adaptedArea;
        @SerializedName("GPSPlotArea")
        @Expose
        private Double gPSPlotArea;
        @SerializedName("SurveyNumber")
        @Expose
        private String surveyNumber;
        @SerializedName("PassbookNumber")
        @Expose
        private String passbookNumber;
        @SerializedName("PlotOwnerShipTypeId")
        @Expose
        private Integer plotOwnerShipTypeId;
        @SerializedName("PlotOwnerShip")
        @Expose
        private String plotOwnerShip;
        @SerializedName("OwnerName")
        @Expose
        private Object ownerName;
        @SerializedName("OwnerContactNumber")
        @Expose
        private Object ownerContactNumber;
        @SerializedName("PlotStatusId")
        @Expose
        private Integer plotStatusId;
        @SerializedName("PlotStatus")
        @Expose
        private String plotStatus;
        @SerializedName("Address1")
        @Expose
        private String address1;
        @SerializedName("Address2")
        @Expose
        private Object address2;
        @SerializedName("CountryId")
        @Expose
        private Integer countryId;
        @SerializedName("CountryName")
        @Expose
        private String countryName;
        @SerializedName("StateId")
        @Expose
        private Integer stateId;
        @SerializedName("StateName")
        @Expose
        private String stateName;
        @SerializedName("DistrictId")
        @Expose
        private Integer districtId;
        @SerializedName("DistrictName")
        @Expose
        private String districtName;
        @SerializedName("MandalId")
        @Expose
        private Integer mandalId;
        @SerializedName("MandalName")
        @Expose
        private String mandalName;
        @SerializedName("VillageId")
        @Expose
        private Integer villageId;
        @SerializedName("VillageName")
        @Expose
        private String villageName;
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
        private Integer updatedByUserId;
        @SerializedName("UpdatedDate")
        @Expose
        private String updatedDate;
        @SerializedName("ServerUpdatedStatus")
        @Expose
        private Boolean serverUpdatedStatus;
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

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getFarmerCode() {
            return farmerCode;
        }

        public void setFarmerCode(String farmerCode) {
            this.farmerCode = farmerCode;
        }

        public Double getTotalPlotArea() {
            return totalPlotArea;
        }

        public void setTotalPlotArea(Double totalPlotArea) {
            this.totalPlotArea = totalPlotArea;
        }

        public Double getAdaptedArea() {
            return adaptedArea;
        }

        public void setAdaptedArea(Double adaptedArea) {
            this.adaptedArea = adaptedArea;
        }

        public Double getGPSPlotArea() {
            return gPSPlotArea;
        }

        public void setGPSPlotArea(Double gPSPlotArea) {
            this.gPSPlotArea = gPSPlotArea;
        }

        public String getSurveyNumber() {
            return surveyNumber;
        }

        public void setSurveyNumber(String surveyNumber) {
            this.surveyNumber = surveyNumber;
        }

        public String getPassbookNumber() {
            return passbookNumber;
        }

        public void setPassbookNumber(String passbookNumber) {
            this.passbookNumber = passbookNumber;
        }

        public Integer getPlotOwnerShipTypeId() {
            return plotOwnerShipTypeId;
        }

        public void setPlotOwnerShipTypeId(Integer plotOwnerShipTypeId) {
            this.plotOwnerShipTypeId = plotOwnerShipTypeId;
        }

        public String getPlotOwnerShip() {
            return plotOwnerShip;
        }

        public void setPlotOwnerShip(String plotOwnerShip) {
            this.plotOwnerShip = plotOwnerShip;
        }

        public Object getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(Object ownerName) {
            this.ownerName = ownerName;
        }

        public Object getOwnerContactNumber() {
            return ownerContactNumber;
        }

        public void setOwnerContactNumber(Object ownerContactNumber) {
            this.ownerContactNumber = ownerContactNumber;
        }

        public Integer getPlotStatusId() {
            return plotStatusId;
        }

        public void setPlotStatusId(Integer plotStatusId) {
            this.plotStatusId = plotStatusId;
        }

        public String getPlotStatus() {
            return plotStatus;
        }

        public void setPlotStatus(String plotStatus) {
            this.plotStatus = plotStatus;
        }

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public Object getAddress2() {
            return address2;
        }

        public void setAddress2(Object address2) {
            this.address2 = address2;
        }

        public Integer getCountryId() {
            return countryId;
        }

        public void setCountryId(Integer countryId) {
            this.countryId = countryId;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public Integer getStateId() {
            return stateId;
        }

        public void setStateId(Integer stateId) {
            this.stateId = stateId;
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

        public String getMandalName() {
            return mandalName;
        }

        public void setMandalName(String mandalName) {
            this.mandalName = mandalName;
        }

        public Integer getVillageId() {
            return villageId;
        }

        public void setVillageId(Integer villageId) {
            this.villageId = villageId;
        }

        public String getVillageName() {
            return villageName;
        }

        public void setVillageName(String villageName) {
            this.villageName = villageName;
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

        public Boolean getServerUpdatedStatus() {
            return serverUpdatedStatus;
        }

        public void setServerUpdatedStatus(Boolean serverUpdatedStatus) {
            this.serverUpdatedStatus = serverUpdatedStatus;
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

    }

    public class PowerDetails {

        @SerializedName("CreatedBy")
        @Expose
        private String createdBy;
        @SerializedName("UpdatedBy")
        @Expose
        private String updatedBy;
        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("PlotCode")
        @Expose
        private String plotCode;
        @SerializedName("IsAvailable")
        @Expose
        private Boolean isAvailable;
        @SerializedName("ServiceNumber")
        @Expose
        private Object serviceNumber;
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
        private Integer updatedByUserId;
        @SerializedName("UpdatedDate")
        @Expose
        private String updatedDate;
        @SerializedName("ServerUpdatedStatus")
        @Expose
        private Boolean serverUpdatedStatus;

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

        public Boolean getIsAvailable() {
            return isAvailable;
        }

        public void setIsAvailable(Boolean isAvailable) {
            this.isAvailable = isAvailable;
        }

        public Object getServiceNumber() {
            return serviceNumber;
        }

        public void setServiceNumber(Object serviceNumber) {
            this.serviceNumber = serviceNumber;
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

        public Boolean getServerUpdatedStatus() {
            return serverUpdatedStatus;
        }

        public void setServerUpdatedStatus(Boolean serverUpdatedStatus) {
            this.serverUpdatedStatus = serverUpdatedStatus;
        }

    }

    public class CropCycleInfo {

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

    }

    public class SoilTestDetail {

        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("PlotCode")
        @Expose
        private String plotCode;
        @SerializedName("CultureTypeId")
        @Expose
        private Integer cultureTypeId;
        @SerializedName("Nitrogen")
        @Expose
        private Double nitrogen;
        @SerializedName("Prosperous")
        @Expose
        private Double prosperous;
        @SerializedName("Potassium")
        @Expose
        private Double potassium;
        @SerializedName("Carbon")
        @Expose
        private Double carbon;
        @SerializedName("Hydrogen")
        @Expose
        private Double hydrogen;
        @SerializedName("Oxygen")
        @Expose
        private Double oxygen;
        @SerializedName("Sulphur")
        @Expose
        private Double sulphur;
        @SerializedName("Calcium")
        @Expose
        private Double calcium;
        @SerializedName("Magnesium")
        @Expose
        private Double magnesium;
        @SerializedName("FileName")
        @Expose
        private String fileName;
        @SerializedName("FileLocation")
        @Expose
        private String fileLocation;
        @SerializedName("FileExtension")
        @Expose
        private String fileExtension;
        @SerializedName("IsActive")
        @Expose
        private Boolean isActive;
        @SerializedName("ServerUpdatedStatus")
        @Expose
        private Boolean serverUpdatedStatus;
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
        @SerializedName("SoilCultureType")
        @Expose
        private String soilCultureType;
        @SerializedName("CreatedBy")
        @Expose
        private String createdBy;
        @SerializedName("UpdatedBy")
        @Expose
        private String updatedBy;
        @SerializedName("Image")
        @Expose
        private String image;

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

        public Integer getCultureTypeId() {
            return cultureTypeId;
        }

        public void setCultureTypeId(Integer cultureTypeId) {
            this.cultureTypeId = cultureTypeId;
        }

        public Double getNitrogen() {
            return nitrogen;
        }

        public void setNitrogen(Double nitrogen) {
            this.nitrogen = nitrogen;
        }

        public Double getProsperous() {
            return prosperous;
        }

        public void setProsperous(Double prosperous) {
            this.prosperous = prosperous;
        }

        public Double getPotassium() {
            return potassium;
        }

        public void setPotassium(Double potassium) {
            this.potassium = potassium;
        }

        public Double getCarbon() {
            return carbon;
        }

        public void setCarbon(Double carbon) {
            this.carbon = carbon;
        }

        public Double getHydrogen() {
            return hydrogen;
        }

        public void setHydrogen(Double hydrogen) {
            this.hydrogen = hydrogen;
        }

        public Double getOxygen() {
            return oxygen;
        }

        public void setOxygen(Double oxygen) {
            this.oxygen = oxygen;
        }

        public Double getSulphur() {
            return sulphur;
        }

        public void setSulphur(Double sulphur) {
            this.sulphur = sulphur;
        }

        public Double getCalcium() {
            return calcium;
        }

        public void setCalcium(Double calcium) {
            this.calcium = calcium;
        }

        public Double getMagnesium() {
            return magnesium;
        }

        public void setMagnesium(Double magnesium) {
            this.magnesium = magnesium;
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

        public String getFileExtension() {
            return fileExtension;
        }

        public void setFileExtension(String fileExtension) {
            this.fileExtension = fileExtension;
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

        public String getSoilCultureType() {
            return soilCultureType;
        }

        public void setSoilCultureType(String soilCultureType) {
            this.soilCultureType = soilCultureType;
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }

    public class SoilDetails {

        @SerializedName("SoilType")
        @Expose
        private String soilType;
        @SerializedName("CreatedBy")
        @Expose
        private String createdBy;
        @SerializedName("UpdatedBy")
        @Expose
        private String updatedBy;
        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("PlotCode")
        @Expose
        private String plotCode;
        @SerializedName("SoilTypeId")
        @Expose
        private Integer soilTypeId;
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
        private Integer updatedByUserId;
        @SerializedName("UpdatedDate")
        @Expose
        private String updatedDate;
        @SerializedName("ServerUpdatedStatus")
        @Expose
        private Boolean serverUpdatedStatus;

        public String getSoilType() {
            return soilType;
        }

        public void setSoilType(String soilType) {
            this.soilType = soilType;
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

        public Integer getSoilTypeId() {
            return soilTypeId;
        }

        public void setSoilTypeId(Integer soilTypeId) {
            this.soilTypeId = soilTypeId;
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

        public Boolean getServerUpdatedStatus() {
            return serverUpdatedStatus;
        }

        public void setServerUpdatedStatus(Boolean serverUpdatedStatus) {
            this.serverUpdatedStatus = serverUpdatedStatus;
        }

    }
}