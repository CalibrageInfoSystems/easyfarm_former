package in.calibrage.easyfarm.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forgotpasswordobject {

    @SerializedName("UsernameOrEmail")
    @Expose
    private String usernameOrEmail;

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

}