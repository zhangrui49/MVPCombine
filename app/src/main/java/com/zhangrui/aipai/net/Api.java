package com.zhangrui.aipai.net;

import com.zhangrui.aipai.mvp.model.Gank;
import com.zhangrui.aipai.mvp.model.GankDayData;
import com.zhangrui.aipai.mvp.model.Video;

import java.util.HashMap;
import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
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
         *
         * @param map
         * @return
         */
        @GET("channels_topics_timeline.json")
        Observable<List<Video>> getVideos(@QueryMap HashMap<String, Object> map);
    }

    public interface GankApi {

        public String GANK_BASE_URL = "http://gank.io/api/";

        @GET("data/{type}/{pageSize}/{page}")
        Observable<Gank> getGankData(@Path("type") String type,@Path("pageSize") int pageSize, @Path("page") int page);

        @GET("day/{year}/{month}/{day}")
        Observable<GankDayData> getGankDayData(@Path("year") int year, @Path("month") int month,
                                            @Path("day") int day);
    }


}
