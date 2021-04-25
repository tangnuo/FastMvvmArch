package com.caowj.fastdev.view.activity;


import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.caowj.basic.base.FastBaseActivity;
import com.caowj.fastdev.viewmodel.BaseViewModel;


public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends FastBaseActivity<V, VM>  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void initData() {
    }

    protected void initView() {
    }

}