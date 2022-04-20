package in.calibrage.easyfarm.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetVendorServices {

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

    @SerializedName("VendorServiceFiles")
    @Expose
    private List<VendorServiceFile> vendorServiceFiles = null;
    @SerializedName("UOMType")
    @Expose
    private String uOMType;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("VendorCategoryName")
    @Expose
    private String vendorCategoryName;
    @SerializedName("VendorSubCategoryName")
    @Expose
    private String vendorSubCategoryName;
    @SerializedName("Village")
    @Expose
    private String village;
    @SerializedName("Mandal")
    @Expose
    private String mandal;
    @SerializedName("District")
    @Expose
    private String district;
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;
    @SerializedName("UpdatedBy")
    @Expose
    private String updatedBy;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("VendorCategoryId")
    @Expose
    private Integer vendorCategoryId;
    @SerializedName("VendorSubCategoryId")
    @Expose
    private Integer vendorSubCategoryId;
    @SerializedName("ServiceName")
    @Expose
    private String serviceName;
    @SerializedName("BrandName")
    @Expose
    private String brandName;
    @SerializedName("UOMTypeId")
    @Expose
    private Integer uOMTypeId;
    @SerializedName("Price")
    @Expose
    private Double price;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("StatusTypeId")
    @Expose
    private Integer statusTypeId;
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
    @SerializedName("Comments")
    @Expose
    private Object comments;
    @SerializedName("Size")
    @Expose
    private Object size;
    @SerializedName("Discount")
    @Expose
    private String discount;
    @SerializedName("ContactNumber")
    @Expose
    private String contactNumber;
    @SerializedName("ContactPerson")
    @Expose
    private String contactPerson;
    @SerializedName("VillageId")
    @Expose
    private Integer villageId;
    @SerializedName("MandalId")
    @Expose
    private Integer mandalId;
    @SerializedName("DistrictId")
    @Expose
    private Integer districtId;
    @SerializedName("StateId")
    @Expose
    private Integer stateId;
    @SerializedName("CountryId")
    @Expose
    private Integer countryId;
    @SerializedName("OpeningTime")
    @Expose
    private String openingTime;
    @SerializedName("ClosingTime")
    @Expose
    private String closingTime;

    public List<VendorServiceFile> getVendorServiceFiles() {
        return vendorServiceFiles;
    }

    public void setVendorServiceFiles(List<VendorServiceFile> vendorServiceFiles) {
        this.vendorServiceFiles = vendorServiceFiles;
    }

    public String getUOMType() {
        return uOMType;
    }

    public void setUOMType(String uOMType) {
        this.uOMType = uOMType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVendorCategoryName() {
        return vendorCategoryName;
    }

    public void setVendorCategoryName(String vendorCategoryName) {
        this.vendorCategoryName = vendorCategoryName;
    }

    public String getVendorSubCategoryName() {
        return vendorSubCategoryName;
    }

    public void setVendorSubCategoryName(String vendorSubCategoryName) {
        this.vendorSubCategoryName = vendorSubCategoryName;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getMandal() {
        return mandal;
    }

    public void setMandal(String mandal) {
        this.mandal = mandal;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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

    public Integer getVendorCategoryId() {
        return vendorCategoryId;
    }

    public void setVendorCategoryId(Integer vendorCategoryId) {
        this.vendorCategoryId = vendorCategoryId;
    }

    public Integer getVendorSubCategoryId() {
        return vendorSubCategoryId;
    }

    public void setVendorSubCategoryId(Integer vendorSubCategoryId) {
        this.vendorSubCategoryId = vendorSubCategoryId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getUOMTypeId() {
        return uOMTypeId;
    }

    public void setUOMTypeId(Integer uOMTypeId) {
        this.uOMTypeId = uOMTypeId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatusTypeId() {
        return statusTypeId;
    }

    public void setStatusTypeId(Integer statusTypeId) {
        this.statusTypeId = statusTypeId;
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

    public Object getComments() {
        return comments;
    }

    public void setComments(Object comments) {
        this.comments = comments;
    }

    public Object getSize() {
        return size;
    }

    public void setSize(Object size) {
        this.size = size;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Integer getVillageId() {
        return villageId;
    }

    public void setVillageId(Integer villageId) {
        this.villageId = villageId;
    }

    public Integer getMandalId() {
        return mandalId;
    }

    public void setMandalId(Integer mandalId) {
        this.mandalId = mandalId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }



public class VendorServiceFile {

    @SerializedName("Image")
    @Expose
    private String image;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("VendorServiceId")
    @Expose
    private Integer vendorServiceId;
    @SerializedName("FileName")
    @Expose
    private String fileName;
    @SerializedName("FileLocation")
    @Expose
    private String fileLocation;
    @SerializedName("FileExtension")
    @Expose
    private String fileExtension;
    @SerializedName("IsDefaultImage")
    @Expose
    private Boolean isDefaultImage;
    @SerializedName("CreatedByUserId")
    @Expose
    private Integer createdByUserId;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVendorServiceId() {
        return vendorServiceId;
    }

    public void setVendorServiceId(Integer vendorServiceId) {
        this.vendorServiceId = vendorServiceId;
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

    public Boolean getIsDefaultImage() {
        return isDefaultImage;
    }

    public void setIsDefaultImage(Boolean isDefaultImage) {
        this.isDefaultImage = isDefaultImage;
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
    }}}}