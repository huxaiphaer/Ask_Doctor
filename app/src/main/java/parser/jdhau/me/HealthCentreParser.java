package parser.jdhau.me;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.DoctorsModel;
import model.HealthCentreModel;

/**
 * Created by HUZY_KAMZ on 2/7/2016.
 */
public class HealthCentreParser  {

    public static List<HealthCentreModel> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<HealthCentreModel> flowerList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                HealthCentreModel healthc = new HealthCentreModel();
                healthc.setArea(obj.getString("Area"));
                healthc.setDistrict(obj.getString("District"));
                healthc.setHealthCentreInfo(obj.getString("HealthCentreInformation"));
                healthc.setOpeningHours(obj.getString("OpeningHours"));
                healthc.setTypeHealthCare(obj.getString("TypeHealthCentre"));
                healthc.setPhoto(obj.getString("photo"));
                healthc.setHealthCentreName(obj.getString("HealthCentreName"));


                flowerList.add(healthc);
            }

            return flowerList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
