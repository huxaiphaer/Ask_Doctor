package parser.jdhau.me;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


import model.DoctorsModel;

/**
 * Created by HUZY_KAMZ on 2/5/2016.
 */
public class DoctorsParser {
    public static List<DoctorsModel> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<DoctorsModel> flowerList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                DoctorsModel doctor = new DoctorsModel();
                doctor.setDrName(obj.getString("DoctorsName"));
                doctor.setHospitalName(obj.getString("HealthCentreName"));
                doctor.setSpecialisation(obj.getString("Specialisation"));
                doctor.setPhoto(obj.getString("photo"));
                doctor.setInformation(obj.getString("Information"));
                doctor.setWorkingHours(obj.getString("WorkingHours"));
                doctor.setPhoneNumber(obj.getString("PhoneNumber"));
                doctor.setEmail(obj.getString("Email"));

                flowerList.add(doctor);
            }

            return flowerList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
