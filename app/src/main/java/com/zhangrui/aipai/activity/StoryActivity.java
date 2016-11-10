package com.zhangrui.aipai.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zhangrui.aipai.R;
import com.zhangrui.aipai.base.BaseMvpActivity;
import com.zhangrui.aipai.databinding.ActivityStoryBinding;
import com.zhangrui.aipai.mvp.presenter.StoryPresenter;
import com.zhangrui.aipai.mvp.view.StoryView;
import com.zhangrui.aipai.viewmodel.Story;

import butterknife.Bind;

public class StoryActivity extends BaseMvpActivity<StoryPresenter> implements StoryView {
    ActivityStoryBinding binding;
    String id;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.image)
    ImageView mImage;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.body)
    WebView mWebview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_story);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_story);
        id = getIntent().getStringExtra("id");
        getStory(id);
    }

    @Override
    public void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_pink_500_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setWebView();
    }

    @Override
    public void getStory(Story story) {
        binding.setStory(story);
        if (TextUtils.isEmpty(story.getShareUrl())) {
            mWebview.loadDataWithBaseURL("file:///android_asset/", story.getBody(), "text/html", "UTF-8", "http//:daily.zhihu.com/");
        } else {
            mWebview.loadUrl(story.getShareUrl());
        }

        Glide.with(this)
                .load(story.getImage())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .animate(R.anim.alpha_in)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into((mImage));

    }

    @Override
    public StoryPresenter setPresenter() {
        return new StoryPresenter(this);
    }

    private void getStory(String id) {
        mPresenter.getStory(id);
    }

    private void setWebView() {
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setBuiltInZoomControls(true);
        mWebview.getSettings().setDisplayZoomControls(false);
        mWebview.getSettings().setSupportZoom(true);
        mWebview.getSettings().setDomStorageEnabled(true);
        mWebview.getSettings().setDatabaseEnabled(true);
        mWebview.getSettings().setLoadWithOverviewMode(true);
        mWebview.getSettings().setUseWideViewPort(true);
        mWebview.getSettings().setAppCacheEnabled(true);
        mWebview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        mWebview.getSettings().setAppCachePath(getCacheDir().getAbsolutePath() + "/webViewCache");
        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
            }
        });
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebview.canGoBack()) {
                mWebview.goBack();
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {

        super.onPause();
        if (mWebview != null)
            mWebview.onPause();
    }

    @Override
    protected void onResume() {

        super.onResume();
        if (mWebview != null)
            mWebview.onResume();
    }

    @Override
    public void onDestroy() {
        if (mWebview != null){
            ((ViewGroup) mWebview.getParent()).removeView(mWebview);
            mWebview.destroy();
            mWebview = null;
        }
        super.onDestroy();
    }
}
