package com.zhangrui.huiju.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.zhangrui.huiju.R;

import butterknife.ButterKnife;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * DESC:
 * Created by zhangrui on 2016/11/3.
 */

public abstract class BaseFragment extends Fragment implements BaseView {

    public Activity mActivity;
    public View mRootView;
    public ProgressDialog progressDialog;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mActivity = getActivity();
        initView();
    }

    public Toolbar setTitle(String title) {
        Toolbar toolbar = (Toolbar) mActivity.findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        return toolbar;
    }

    public void showToast(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public abstract void initView();

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(mActivity);
        progressDialog.setMessage("加载中...");
        progressDialog.show();
    }

    @Override
    public void dismissProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showError(CharSequence msg) {
        dismissProgress();
        Snackbar.make(mRootView, msg, Snackbar.LENGTH_SHORT).show();
    }

    public void startActivity(Class target) {
        startActivity(new Intent(getActivity(), target));
    }

    public void showShare(String title, String content, String imgUrl, String url) {
        ShareSDK.initSDK(getActivity());
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(title);
//        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText(content);
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(imgUrl);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
//        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("汇聚");
//        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        //   oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(getActivity());
    }
}
