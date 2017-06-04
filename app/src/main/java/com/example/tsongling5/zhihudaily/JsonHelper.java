package com.example.tsongling5.zhihudaily;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 15-2-3.
 */
public class JsonHelper {
    public static List<ZhihuItem> parseJsonToList(String json) throws JSONException {
        JSONObject newsContent = new JSONObject(json);
        JSONArray newsArray = newsContent.getJSONArray("stories");
        List<ZhihuItem> newsList = new ArrayList<ZhihuItem>();
        for (int i = 0; i < newsArray.length(); i++) {
            JSONObject newsInJson = newsArray.getJSONObject(i);
            int id = newsInJson.optInt("id");
            String title = newsInJson.optString("title");
            String image = "";
            if (newsInJson.has("images")) {
                image = (String) newsInJson.getJSONArray("images").get(0);

            }
            ZhihuItem news = new ZhihuItem(id, image,title);
            newsList.add(news);
        }
        return newsList;
    }

    public static NewsDetail parseJsonToDetail(String json) throws JSONException {
        Gson gson = new Gson();
        return gson.fromJson(json, NewsDetail.class);
    }
}
