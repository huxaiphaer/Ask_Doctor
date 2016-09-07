package model;

import android.graphics.Bitmap;

/**
 * Created by HUZY_KAMZ on 2/20/2016.
 */
public class NewsModel {

    private String Headlines;
    private String Details;
    private Bitmap bitmap;
    private String photo;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getHeadlines() {
        return Headlines;
    }

    public void setHeadlines(String headlines) {
        Headlines = headlines;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }
}
