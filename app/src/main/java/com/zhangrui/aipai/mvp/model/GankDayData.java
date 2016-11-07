package com.zhangrui.aipai.mvp.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * DESC:
 * Created by zhangrui on 2016/11/7.
 */

public class GankDayData {


    private HashMap<String, List<GankData>> mResultList;
    private boolean error;
    private DayData results;
    private List<String> category;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public DayData getResults() {
        return results;
    }

    public void setResults(DayData results) {
        this.results = results;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public static class DayData {

    }


    public HashMap generateResult() {
        mResultList = new HashMap<>();
        Gson gson = new Gson();
        for (String key : category) {
            try {
                JSONObject jsonObject = new JSONObject(gson.toJson(results));
                mResultList.put(key, (List<GankData>) gson.fromJson(jsonObject.optString(key), new TypeToken<List<GankData>>() {
                }.getType()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mResultList;
    }
//
//    public HashMap<String, List<GankData>> getResultList() {
//        return mResultList;
//    }
//
//    public void setResultList(HashMap<String, List<GankData>> resultList) {
//        mResultList = resultList;
//    }
}
