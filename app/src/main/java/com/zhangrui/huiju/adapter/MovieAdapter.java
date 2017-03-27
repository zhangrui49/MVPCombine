package com.zhangrui.huiju.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhangrui.huiju.R;
import com.zhangrui.huiju.mvp.model.MovieDetail;

import java.util.List;

/**
 * DESC:
 * Created by zhangrui on 2017/3/23.
 */

public class MovieAdapter extends BaseQuickAdapter<MovieDetail> {

    public MovieAdapter(List<MovieDetail> data) {
        super(R.layout.item_movie, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MovieDetail movieDetail) {
        Glide.with(mContext).load(movieDetail.getImages().getLarge()).into((ImageView) baseViewHolder.getView(R.id.image));
        baseViewHolder.setText(R.id.title, movieDetail.getTitle());
        if (movieDetail.getCountries() != null) {
            StringBuilder country = new StringBuilder();
            for (String s : movieDetail.getCountries()) {
                country.append(s).append("/");
            }
            baseViewHolder.setText(R.id.country, country.deleteCharAt(country.length() - 1).toString());
        } else {
            baseViewHolder.getView(R.id.country).setVisibility(View.GONE);
        }
        if (movieDetail.getGenres() != null) {
            StringBuilder genres = new StringBuilder();
            for (String s : movieDetail.getGenres()) {
                genres.append(s).append(" ");
            }
            baseViewHolder.setText(R.id.genres, genres.deleteCharAt(genres.length() - 1).toString());
        } else {
            baseViewHolder.getView(R.id.genres).setVisibility(View.GONE);
        }
        baseViewHolder.setText(R.id.year, movieDetail.getYear());
        baseViewHolder.setText(R.id.director, movieDetail.getDirectors().get(0).getName());
        baseViewHolder.setRating(R.id.rating, (float) movieDetail.getRating().getAverage());
    }
}
