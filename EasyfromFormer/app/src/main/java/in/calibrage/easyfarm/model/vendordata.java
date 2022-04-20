package in.calibrage.easyfarm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


    public class vendordata implements Serializable {

        private List<VendorServiceFile> vendorServiceFiles = null;
        private String uOMType;
        private String status;
        private String vendorCategoryName;
        private String vendorSubCategoryName;
        private String village;
        private String mandal;
        private String district;
        private String state;
        private String country;
        private String createdBy;
        private String updatedBy;
        private Integer id;

        private Integer vendorCategoryId;
        private Integer vendorSubCategoryId;
        private String serviceName;
        private String brandName;
        private Integer uOMTypeId;
        private Double price;
        private String description;
        private Integer statusTypeId;
        private Boolean isActive;
        private Integer createdByUserId;
        private String createdDate;
        private Integer updatedByUserId;
        private String updatedDate;
        private Object comments;
        private Object size;

        public vendordata( String uOMType, String status, String vendorCategoryName, String vendorSubCategoryName, String village, String mandal, String district, String state, String country, String createdBy, String updatedBy, Integer id, Integer vendorCategoryId, Integer vendorSubCategoryId, String serviceName, String brandName, Integer uOMTypeId, Double price, String description, Integer statusTypeId, Boolean isActive, Integer createdByUserId, String createdDate, Integer updatedByUserId, String updatedDate, Object comments, Object size, String discount, String contactNumber, String contactPerson, Integer villageId, Integer mandalId, Integer districtId, Integer stateId, Integer countryId, String openingTime, String closingTime) {
         ///   this.vendorServiceFiles = vendorServiceFiles;
            this.uOMType = uOMType;
            this.status = status;
            this.vendorCategoryName = vendorCategoryName;
            this.vendorSubCategoryName = vendorSubCategoryName;
            this.village = village;
            this.mandal = mandal;
            this.district = district;
            this.state = state;
            this.country = country;
            this.createdBy = createdBy;
            this.updatedBy = updatedBy;
            this.id = id;
            this.vendorCategoryId = vendorCategoryId;
            this.vendorSubCategoryId = vendorSubCategoryId;
            this.serviceName = serviceName;
            this.brandName = brandName;
            this.uOMTypeId = uOMTypeId;
            this.price = price;
            this.description = description;
            this.statusTypeId = statusTypeId;
            this.isActive = isActive;
            this.createdByUserId = createdByUserId;
            this.createdDate = createdDate;
            this.updatedByUserId = updatedByUserId;
            this.updatedDate = updatedDate;
            this.comments = comments;
            this.size = size;
            this.discount = discount;
            this.contactNumber = contactNumber;
            this.contactPerson = contactPerson;
            this.villageId = villageId;
            this.mandalId = mandalId;
            this.districtId = districtId;
            this.stateId = stateId;
            this.countryId = countryId;
            this.openingTime = openingTime;
            this.closingTime = closingTime;
        }

        private String discount;
        private String contactNumber;
        private String contactPerson;
        private Integer villageId;
        private Integer mandalId;
        private Integer districtId;
        private Integer stateId;
        private Integer countryId;
        private String openingTime;
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

        public Object getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public Object getContactNumber() {
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

        public Object getOpeningTime() {
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
            }
        }
    }