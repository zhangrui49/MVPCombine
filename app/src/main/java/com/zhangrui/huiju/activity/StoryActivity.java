package com.zhangrui.huiju.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zhangrui.huiju.R;
import com.zhangrui.huiju.base.BaseMvpActivity;
import com.zhangrui.huiju.databinding.ActivityStoryBinding;
import com.zhangrui.huiju.mvp.presenter.StoryPresenter;
import com.zhangrui.huiju.mvp.view.StoryView;
import com.zhangrui.huiju.viewmodel.Story;

import butterknife.Bind;

import static com.zhangrui.huiju.R.id.toolbar;

public class StoryActivity extends BaseMvpActivity<StoryPresenter> implements StoryView {
    ActivityStoryBinding binding;
    String id;
    @Bind(toolbar)
    Toolbar mToolbar;
    @Bind(R.id.image)
    ImageView mImage;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.body)
    WebView mWebview;
    @Bind(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    @Bind(R.id.relative)
    RelativeLayout mRelativeLayout;


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
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_pink_500_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setWebView();
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    mRelativeLayout.setVisibility(View.INVISIBLE);
                } else {
                    mRelativeLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void getStory(Story story) {
        binding.setStory(story);
        if (TextUtils.isEmpty(story.getShareUrl())) {
             mWebview.loadDataWithBaseURL("http://daily.zhihu.com/", story.getBody(), "text/html", "UTF-8", "http://daily.zhihu.com/");
           // mWebview.loadData(story.getBody(), "text/html", "gbk");
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
                // view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (mWebview.canGoBack()) {
//                mWebview.goBack();
//                return true;
//            } else {
//                finish();
//            }
//        }
//        return super.onKeyDown(keyCode, event);
//    }

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
        if (mWebview != null) {
            ((ViewGroup) mWebview.getParent()).removeView(mWebview);
            mWebview.destroy();
            mWebview = null;
        }
        super.onDestroy();
    }
}
