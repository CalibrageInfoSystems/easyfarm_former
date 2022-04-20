package in.calibrage.easyfarm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUserInfoByUserCode {

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

        @SerializedName("UserDetails")
        @Expose
        private UserDetails userDetails;
        @SerializedName("BankDetails")
        @Expose
        private BankDetails bankDetails;
        @SerializedName("IdentityProofDetails")
        @Expose
        private List<IdentityProofDetail> identityProofDetails = null;

        public UserDetails getUserDetails() {
            return userDetails;
        }

        public void setUserDetails(UserDetails userDetails) {
            this.userDetails = userDetails;
        }

        public BankDetails getBankDetails() {
            return bankDetails;
        }

        public void setBankDetails(BankDetails bankDetails) {
            this.bankDetails = bankDetails;
        }

        public List<IdentityProofDetail> getIdentityProofDetails() {
            return identityProofDetails;
        }

        public void setIdentityProofDetails(List<IdentityProofDetail> identityProofDetails) {
            this.identityProofDetails = identityProofDetails;
        }
    }
    public class BankDetails {

        @SerializedName("BranchId")
        @Expose
        private Integer branchId;
        @SerializedName("BankName")
        @Expose
        private String bankName;
        @SerializedName("IFSCCode")
        @Expose
        private String iFSCCode;
        @SerializedName("BranchName")
        @Expose
        private String branchName;
        @SerializedName("BankAccountImage")
        @Expose
        private String bankAccountImage;
        @SerializedName("BankPassbookImage")
        @Expose
        private String bankPassbookImage;
        @SerializedName("CreatedBy")
        @Expose
        private String createdBy;
        @SerializedName("UpdatedBy")
        @Expose
        private String updatedBy;
        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("BankId")
        @Expose
        private Integer bankId;
        @SerializedName("BPFileName")
        @Expose
        private String bPFileName;
        @SerializedName("BPFileLocation")
        @Expose
        private String bPFileLocation;
        @SerializedName("BPFileExtension")
        @Expose
        private String bPFileExtension;
        @SerializedName("AccountHolderName")
        @Expose
        private String accountHolderName;
        @SerializedName("AccountNumber")
        @Expose
        private String accountNumber;
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
        @SerializedName("UserCode")
        @Expose
        private String userCode;
        @SerializedName("ServerUpdatedStatus")
        @Expose
        private Boolean serverUpdatedStatus;

        public Integer getBranchId() {
            return branchId;
        }

        public void setBranchId(Integer branchId) {
            this.branchId = branchId;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getIFSCCode() {
            return iFSCCode;
        }

        public void setIFSCCode(String iFSCCode) {
            this.iFSCCode = iFSCCode;
        }

        public String getBranchName() {
            return branchName;
        }

        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }

        public String getBankAccountImage() {
            return bankAccountImage;
        }

        public void setBankAccountImage(String bankAccountImage) {
            this.bankAccountImage = bankAccountImage;
        }

        public String getBankPassbookImage() {
            return bankPassbookImage;
        }

        public void setBankPassbookImage(String bankPassbookImage) {
            this.bankPassbookImage = bankPassbookImage;
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

        public Integer getBankId() {
            return bankId;
        }

        public void setBankId(Integer bankId) {
            this.bankId = bankId;
        }

        public String getBPFileName() {
            return bPFileName;
        }

        public void setBPFileName(String bPFileName) {
            this.bPFileName = bPFileName;
        }

        public String getBPFileLocation() {
            return bPFileLocation;
        }

        public void setBPFileLocation(String bPFileLocation) {
            this.bPFileLocation = bPFileLocation;
        }

        public String getBPFileExtension() {
            return bPFileExtension;
        }

        public void setBPFileExtension(String bPFileExtension) {
            this.bPFileExtension = bPFileExtension;
        }

        public String getAccountHolderName() {
            return accountHolderName;
        }

        public void setAccountHolderName(String accountHolderName) {
            this.accountHolderName = accountHolderName;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
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

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public Boolean getServerUpdatedStatus() {
            return serverUpdatedStatus;
        }

        public void setServerUpdatedStatus(Boolean serverUpdatedStatus) {
            this.serverUpdatedStatus = serverUpdatedStatus;
        }

    }

    public class IdentityProofDetail {

        @SerializedName("IdentityProof")
        @Expose
        private String identityProof;
        @SerializedName("IdProofName")
        @Expose
        private String idProofName;
        @SerializedName("CreatedBy")
        @Expose
        private String createdBy;
        @SerializedName("UpdatedBy")
        @Expose
        private String updatedBy;
        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("IdProofTypeId")
        @Expose
        private Integer idProofTypeId;
        @SerializedName("IdProofNumber")
        @Expose
        private String idProofNumber;
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
        @SerializedName("IsVerified")
        @Expose
        private Boolean isVerified;
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
        @SerializedName("UserCode")
        @Expose
        private String userCode;
        @SerializedName("ServerUpdatedStatus")
        @Expose
        private Boolean serverUpdatedStatus;

        public String getIdentityProof() {
            return identityProof;
        }

        public void setIdentityProof(String identityProof) {
            this.identityProof = identityProof;
        }

        public String getIdProofName() {
            return idProofName;
        }

        public void setIdProofName(String idProofName) {
            this.idProofName = idProofName;
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

        public Integer getIdProofTypeId() {
            return idProofTypeId;
        }

        public void setIdProofTypeId(Integer idProofTypeId) {
            this.idProofTypeId = idProofTypeId;
        }

        public String getIdProofNumber() {
            return idProofNumber;
        }

        public void setIdProofNumber(String idProofNumber) {
            this.idProofNumber = idProofNumber;
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

        public Boolean getIsVerified() {
            return isVerified;
        }

        public void setIsVerified(Boolean isVerified) {
            this.isVerified = isVerified;
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

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public Boolean getServerUpdatedStatus() {
            return serverUpdatedStatus;
        }

        public void setServerUpdatedStatus(Boolean serverUpdatedStatus) {
            this.serverUpdatedStatus = serverUpdatedStatus;
        }

    }

    public class UserDetails {

        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("UserId")
        @Expose
        private String userId;
        @SerializedName("Code")
        @Expose
        private String code;
        @SerializedName("FirstName")
        @Expose
        private String firstName;
        @SerializedName("MiddleName")
        @Expose
        private String middleName;
        @SerializedName("LastName")
        @Expose
        private String lastName;
        @SerializedName("UserName")
        @Expose
        private String userName;
        @SerializedName("FullName")
        @Expose
        private String fullName;
        @SerializedName("FatherName_GuardianName")
        @Expose
        private String fatherNameGuardianName;
        @SerializedName("Email")
        @Expose
        private String email;
        @SerializedName("Address1")
        @Expose
        private String address1;
        @SerializedName("Address2")
        @Expose
        private String address2;
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
        @SerializedName("GenderTypeId")
        @Expose
        private Integer genderTypeId;
        @SerializedName("Gender")
        @Expose
        private String gender;
        @SerializedName("DOB")
        @Expose
        private String dOB;
        @SerializedName("PrimaryPhoneNumber")
        @Expose
        private String primaryPhoneNumber;
        @SerializedName("SecondaryPhoneNumber")
        @Expose
        private String secondaryPhoneNumber;
        @SerializedName("AnnualIncomeTypeId")
        @Expose
        private Object annualIncomeTypeId;
        @SerializedName("AnnualIncome")
        @Expose
        private Object annualIncome;
        @SerializedName("CategoryTypeId")
        @Expose
        private Integer categoryTypeId;
        @SerializedName("Categorytype")
        @Expose
        private String categorytype;
        @SerializedName("EducationTypeId")
        @Expose
        private Integer educationTypeId;
        @SerializedName("EducationType")
        @Expose
        private String educationType;
        @SerializedName("EducationDegreeTypeId")
        @Expose
        private Integer educationDegreeTypeId;
        @SerializedName("EducationDegreeType")
        @Expose
        private String educationDegreeType;
        @SerializedName("SourceTypeId")
        @Expose
        private Object sourceTypeId;
        @SerializedName("SourceType")
        @Expose
        private Object sourceType;
        @SerializedName("EmployeeTypeId")
        @Expose
        private Object employeeTypeId;
        @SerializedName("EmployeeType")
        @Expose
        private Object employeeType;
        @SerializedName("PPFileName")
        @Expose
        private String pPFileName;
        @SerializedName("PPFileLocation")
        @Expose
        private String pPFileLocation;
        @SerializedName("PPFileExtension")
        @Expose
        private String pPFileExtension;
        @SerializedName("ProfilePic")
        @Expose
        private String profilePic;
        @SerializedName("MAFileName")
        @Expose
        private Object mAFileName;
        @SerializedName("MAFileLocation")
        @Expose
        private Object mAFileLocation;
        @SerializedName("MAFileExtension")
        @Expose
        private Object mAFileExtension;
        @SerializedName("MutualAgreement")
        @Expose
        private Object mutualAgreement;
        @SerializedName("ReportingManagerId")
        @Expose
        private Object reportingManagerId;
        @SerializedName("ReportingManager")
        @Expose
        private Object reportingManager;
        @SerializedName("ExpInYears")
        @Expose
        private Object expInYears;
        @SerializedName("ExpInMonths")
        @Expose
        private Object expInMonths;
        @SerializedName("RoleIds")
        @Expose
        private String roleIds;
        @SerializedName("Roles")
        @Expose
        private String roles;
        @SerializedName("ClusterIds")
        @Expose
        private Object clusterIds;
        @SerializedName("Clusters")
        @Expose
        private Object clusters;
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
        @SerializedName("IsActive")
        @Expose
        private Boolean isActive;
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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getFatherNameGuardianName() {
            return fatherNameGuardianName;
        }

        public void setFatherNameGuardianName(String fatherNameGuardianName) {
            this.fatherNameGuardianName = fatherNameGuardianName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public String getAddress2() {
            return address2;
        }

        public void setAddress2(String address2) {
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

        public Integer getGenderTypeId() {
            return genderTypeId;
        }

        public void setGenderTypeId(Integer genderTypeId) {
            this.genderTypeId = genderTypeId;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getDOB() {
            return dOB;
        }

        public void setDOB(String dOB) {
            this.dOB = dOB;
        }

        public String getPrimaryPhoneNumber() {
            return primaryPhoneNumber;
        }

        public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
            this.primaryPhoneNumber = primaryPhoneNumber;
        }

        public String getSecondaryPhoneNumber() {
            return secondaryPhoneNumber;
        }

        public void setSecondaryPhoneNumber(String secondaryPhoneNumber) {
            this.secondaryPhoneNumber = secondaryPhoneNumber;
        }

        public Object getAnnualIncomeTypeId() {
            return annualIncomeTypeId;
        }

        public void setAnnualIncomeTypeId(Object annualIncomeTypeId) {
            this.annualIncomeTypeId = annualIncomeTypeId;
        }

        public Object getAnnualIncome() {
            return annualIncome;
        }

        public void setAnnualIncome(Object annualIncome) {
            this.annualIncome = annualIncome;
        }

        public Integer getCategoryTypeId() {
            return categoryTypeId;
        }

        public void setCategoryTypeId(Integer categoryTypeId) {
            this.categoryTypeId = categoryTypeId;
        }

        public String getCategorytype() {
            return categorytype;
        }

        public void setCategorytype(String categorytype) {
            this.categorytype = categorytype;
        }

        public Integer getEducationTypeId() {
            return educationTypeId;
        }

        public void setEducationTypeId(Integer educationTypeId) {
            this.educationTypeId = educationTypeId;
        }

        public String getEducationType() {
            return educationType;
        }

        public void setEducationType(String educationType) {
            this.educationType = educationType;
        }

        public Integer getEducationDegreeTypeId() {
            return educationDegreeTypeId;
        }

        public void setEducationDegreeTypeId(Integer educationDegreeTypeId) {
            this.educationDegreeTypeId = educationDegreeTypeId;
        }

        public String getEducationDegreeType() {
            return educationDegreeType;
        }

        public void setEducationDegreeType(String educationDegreeType) {
            this.educationDegreeType = educationDegreeType;
        }

        public Object getSourceTypeId() {
            return sourceTypeId;
        }

        public void setSourceTypeId(Object sourceTypeId) {
            this.sourceTypeId = sourceTypeId;
        }

        public Object getSourceType() {
            return sourceType;
        }

        public void setSourceType(Object sourceType) {
            this.sourceType = sourceType;
        }

        public Object getEmployeeTypeId() {
            return employeeTypeId;
        }

        public void setEmployeeTypeId(Object employeeTypeId) {
            this.employeeTypeId = employeeTypeId;
        }

        public Object getEmployeeType() {
            return employeeType;
        }

        public void setEmployeeType(Object employeeType) {
            this.employeeType = employeeType;
        }

        public String getPPFileName() {
            return pPFileName;
        }

        public void setPPFileName(String pPFileName) {
            this.pPFileName = pPFileName;
        }

        public String getPPFileLocation() {
            return pPFileLocation;
        }

        public void setPPFileLocation(String pPFileLocation) {
            this.pPFileLocation = pPFileLocation;
        }

        public String getPPFileExtension() {
            return pPFileExtension;
        }

        public void setPPFileExtension(String pPFileExtension) {
            this.pPFileExtension = pPFileExtension;
        }

        public String getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(String profilePic) {
            this.profilePic = profilePic;
        }

        public Object getMAFileName() {
            return mAFileName;
        }

        public void setMAFileName(Object mAFileName) {
            this.mAFileName = mAFileName;
        }

        public Object getMAFileLocation() {
            return mAFileLocation;
        }

        public void setMAFileLocation(Object mAFileLocation) {
            this.mAFileLocation = mAFileLocation;
        }

        public Object getMAFileExtension() {
            return mAFileExtension;
        }

        public void setMAFileExtension(Object mAFileExtension) {
            this.mAFileExtension = mAFileExtension;
        }

        public Object getMutualAgreement() {
            return mutualAgreement;
        }

        public void setMutualAgreement(Object mutualAgreement) {
            this.mutualAgreement = mutualAgreement;
        }

        public Object getReportingManagerId() {
            return reportingManagerId;
        }

        public void setReportingManagerId(Object reportingManagerId) {
            this.reportingManagerId = reportingManagerId;
        }

        public Object getReportingManager() {
            return reportingManager;
        }

        public void setReportingManager(Object reportingManager) {
            this.reportingManager = reportingManager;
        }

        public Object getExpInYears() {
            return expInYears;
        }

        public void setExpInYears(Object expInYears) {
            this.expInYears = expInYears;
        }

        public Object getExpInMonths() {
            return expInMonths;
        }

        public void setExpInMonths(Object expInMonths) {
            this.expInMonths = expInMonths;
        }

        public String getRoleIds() {
            return roleIds;
        }

        public void setRoleIds(String roleIds) {
            this.roleIds = roleIds;
        }

        public String getRoles() {
            return roles;
        }

        public void setRoles(String roles) {
            this.roles = roles;
        }

        public Object getClusterIds() {
            return clusterIds;
        }

        public void setClusterIds(Object clusterIds) {
            this.clusterIds = clusterIds;
        }

        public Object getClusters() {
            return clusters;
        }

        public void setClusters(Object clusters) {
            this.clusters = clusters;
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

        public Boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
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
}
