package com.zhangrui.aipai.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.zhangrui.aipai.R;

import butterknife.ButterKnife;

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
        progressDialog.setMessage("请稍候...");
        progressDialog.show();
    }

    @Override
    public void dissmissProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showError(CharSequence msg) {
        dissmissProgress();
        Snackbar.make(mRootView,msg,Snackbar.LENGTH_SHORT).show();
    }

    public void startActivity(Class target) {
        startActivity(new Intent(getActivity(), target));
    }
}
