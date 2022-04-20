package in.calibrage.easyfarm.model;

public class date_object {

    private  String date;
    private  String imageurl;
    private  String vediourl;

    public date_object(String date, String imageurl, String vediourl) {
        this.date = date;
        this.imageurl = imageurl;
        this.vediourl = vediourl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getVediourl() {
        return vediourl;
    }

    public void setVediourl(String vediourl) {
        this.vediourl = vediourl;
    }
}
