package parser.jdhau.me;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.DoctorsModel;
import model.HealthCentreModel;
import model.NewsModel;

/**
 * Created by HUZY_KAMZ on 2/7/2016.
 */
public class NewsParser  {

    public static List<NewsModel> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<NewsModel> flowerList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                NewsModel healthc = new NewsModel();
                healthc.setDetails(obj.getString("Details"));
                healthc.setHeadlines(obj.getString("Headlines"));
                healthc.setPhoto(obj.getString("photo"));


                flowerList.add(healthc);
            }

            return flowerList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
