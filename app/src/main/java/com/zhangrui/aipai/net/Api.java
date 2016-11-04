package com.zhangrui.aipai.net;

import com.zhangrui.aipai.mvp.model.Video;

import java.util.HashMap;
import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * DESC:
 * Created by zhangrui on 2016/11/3.
 */

public class Api {
    public interface AiPaiApi {
        public String AIPAI_BASE_URL = "http://newapi.meipai.com/output/";

        /**
         * 获取美拍视频列表
         * @param map
         * @return
         */
        @GET("channels_topics_timeline.json")
        Observable<List<Video>> getVideos(@QueryMap HashMap<String, Object> map);
    }


}
