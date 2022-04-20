package in.calibrage.easyfarm.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetGeoBoundaries {

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

    @SerializedName("FarmerCode")
    @Expose
    private String farmerCode;
    @SerializedName("FarmerName")
    @Expose
    private String farmerName;
    @SerializedName("PlotCode")
    @Expose
    private String plotCode;
    @SerializedName("TotalPlotArea")
    @Expose
    private Double totalPlotArea;
    @SerializedName("AdaptedArea")
    @Expose
    private Double adaptedArea;
    @SerializedName("GPSPlotArea")
    @Expose
    private Double gPSPlotArea;
    @SerializedName("VillageId")
    @Expose
    private Integer villageId;
    @SerializedName("VillageName")
    @Expose
    private String villageName;
    @SerializedName("MandalId")
    @Expose
    private Integer mandalId;
    @SerializedName("MandalName")
    @Expose
    private String mandalName;
    @SerializedName("DistrictId")
    @Expose
    private Integer districtId;
    @SerializedName("DistrictName")
    @Expose
    private String districtName;
    @SerializedName("StateId")
    @Expose
    private Integer stateId;
    @SerializedName("StateName")
    @Expose
    private String stateName;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("UpdatedDate")
    @Expose
    private String updatedDate;
    @SerializedName("coordinates")
    @Expose
    private List<List<List<Double>>> coordinates = null;
    @SerializedName("GeoCategoryTypeId")
    @Expose
    private Integer geoCategoryTypeId;

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

    public String getPlotCode() {
        return plotCode;
    }

    public void setPlotCode(String plotCode) {
        this.plotCode = plotCode;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public List<List<List<Double>>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<List<Double>>> coordinates) {
        this.coordinates = coordinates;
    }

    public Integer getGeoCategoryTypeId() {
        return geoCategoryTypeId;
    }

    public void setGeoCategoryTypeId(Integer geoCategoryTypeId) {
        this.geoCategoryTypeId = geoCategoryTypeId;
    }}}