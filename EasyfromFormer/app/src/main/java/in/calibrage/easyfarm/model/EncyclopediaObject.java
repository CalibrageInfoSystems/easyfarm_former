package in.calibrage.easyfarm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EncyclopediaObject {
    @SerializedName("CategoryId")
    @Expose
    private Object categoryId;
    @SerializedName("StateIds")
    @Expose
    private Object stateIds;
    @SerializedName("IsActive")
    @Expose
    private Object isActive;

    public Object getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Object categoryId) {
        this.categoryId = categoryId;
    }

    public Object getStateIds() {
        return stateIds;
    }

    public void setStateIds(Object stateIds) {
        this.stateIds = stateIds;
    }

    public Object getIsActive() {
        return isActive;
    }

    public void setIsActive(Object isActive) {
        this.isActive = isActive;
    }

}
