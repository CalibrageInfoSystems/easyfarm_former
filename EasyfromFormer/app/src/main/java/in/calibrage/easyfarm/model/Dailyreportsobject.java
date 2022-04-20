package in.calibrage.easyfarm.model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dailyreportsobject {

    @SerializedName("RepoFiles")
    @Expose
    private List<RepoFile> repoFiles = null;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("CycleCode")
    @Expose
    private String cycleCode;
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

    public List<RepoFile> getRepoFiles() {
        return repoFiles;
    }

    public void setRepoFiles(List<RepoFile> repoFiles) {
        this.repoFiles = repoFiles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCycleCode() {
        return cycleCode;
    }

    public void setCycleCode(String cycleCode) {
        this.cycleCode = cycleCode;
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


    public static class RepoFile {

        @SerializedName("FileBytes")
        @Expose
        private String fileBytes;
        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("UploadId")
        @Expose
        private Integer uploadId;
        @SerializedName("FileTypeId")
        @Expose
        private Integer fileTypeId;
        @SerializedName("FileName")
        @Expose
        private String fileName;
        @SerializedName("FileLocation")
        @Expose
        private String fileLocation;
        @SerializedName("FileExtension")
        @Expose
        private String fileExtension;
        @SerializedName("VideoEmbedUrl")
        @Expose
        private String videoEmbedUrl;
        @SerializedName("CreatedByUserId")
        @Expose
        private Integer createdByUserId;
        @SerializedName("CreatedDate")
        @Expose
        private String createdDate;

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

        public Integer getUploadId() {
            return uploadId;
        }

        public void setUploadId(Integer uploadId) {
            this.uploadId = uploadId;
        }

        public Integer getFileTypeId() {
            return fileTypeId;
        }

        public void setFileTypeId(Integer fileTypeId) {
            this.fileTypeId = fileTypeId;
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

        public String getVideoEmbedUrl() {
            return videoEmbedUrl;
        }

        public void setVideoEmbedUrl(String videoEmbedUrl) {
            this.videoEmbedUrl = videoEmbedUrl;
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


    public RepoFile() {

        this.fileBytes = fileBytes;
        this.uploadId = uploadId;
        this.id = id;
        this.fileLocation = fileLocation;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.createdByUserId = createdByUserId;
        this.createdDate = createdDate;
        this.fileTypeId = fileTypeId;
        this.videoEmbedUrl =videoEmbedUrl;

    }
}
    public Dailyreportsobject(List<RepoFile> dailyRepo) {
        this.repoFiles = dailyRepo;
    }
}