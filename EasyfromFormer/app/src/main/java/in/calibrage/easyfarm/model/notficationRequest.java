package in.calibrage.easyfarm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class notficationRequest {

    @SerializedName("UserId")
    @Expose
    private Integer userId;
    @SerializedName("SearchValue")
    @Expose
    private String searchValue;
    @SerializedName("FromDate")
    @Expose
    private String fromDate;
    @SerializedName("ToDate")
    @Expose
    private String toDate;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

}
