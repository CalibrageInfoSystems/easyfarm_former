package in.calibrage.easyfarm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCropCycleDailyUploadobject {

    @SerializedName("CropCycle")
    @Expose
    private String cropCycle;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;

    public String getCropCycle() {
        return cropCycle;
    }

    public void setCropCycle(String cropCycle) {
        this.cropCycle = cropCycle;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}