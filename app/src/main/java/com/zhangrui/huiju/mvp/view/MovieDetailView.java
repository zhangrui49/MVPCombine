package com.zhangrui.huiju.mvp.view;

import com.zhangrui.huiju.base.BaseView;
import com.zhangrui.huiju.mvp.model.MovieDetail;

/**
 * DESC:
 * Created by zhangrui on 2017/3/23.
 */

public interface MovieDetailView extends BaseView {
    void getMovieDetail(MovieDetail movieDetail);
}
