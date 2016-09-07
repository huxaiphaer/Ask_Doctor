package parser.jdhau.me;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.NewsModel;
import model.TipModel;

/**
 * Created by HUZY_KAMZ on 2/21/2016.
 */
public class TipsParser {

    public static List<TipModel> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<TipModel> flowerList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                TipModel healthc = new TipModel();
                healthc.setTip(obj.getString("Tip"));
                healthc.setDoctor(obj.getString("Doctor"));



                flowerList.add(healthc);
            }

            return flowerList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
