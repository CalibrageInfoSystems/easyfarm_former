package in.calibrage.easyfarm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetComplaintRepositoryobject {

    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("IsVideoRecording")
    @Expose
    private Boolean isVideoRecording;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getIsVideoRecording() {
        return isVideoRecording;
    }

    public void setIsVideoRecording(Boolean isVideoRecording) {
        this.isVideoRecording = isVideoRecording;
    }

}