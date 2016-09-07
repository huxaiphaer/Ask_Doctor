package model;

import android.graphics.Bitmap;

/**
 * Created by HUZY_KAMZ on 2/7/2016.
 */
public class HealthCentreModel {


    private String HealthCentreName;
    private Bitmap bitmap;
    private String Photo;
    private String  Area;
    private String District;
    private String TypeHealthCare;
    private String OpeningHours;
    private String HealthCentreInfo;


    public String getHealthCentreName() {
        return HealthCentreName;
    }

    public void setHealthCentreName(String healthCentreName) {
        HealthCentreName = healthCentreName;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getTypeHealthCare() {
        return TypeHealthCare;
    }

    public void setTypeHealthCare(String typeHealthCare) {
        TypeHealthCare = typeHealthCare;
    }

    public String getOpeningHours() {
        return OpeningHours;
    }

    public void setOpeningHours(String openingHours) {
        OpeningHours = openingHours;
    }

    public String getHealthCentreInfo() {
        return HealthCentreInfo;
    }

    public void setHealthCentreInfo(String healthCentreInfo) {
        HealthCentreInfo = healthCentreInfo;
    }
}
