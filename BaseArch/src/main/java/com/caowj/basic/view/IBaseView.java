package com.caowj.basic.view;

import android.databinding.ViewDataBinding;

import com.caowj.basic.viewmodel.FastBaseViewModel;

/**
 * @author : yuanbingbing
 * @since : 2018/5/28 15:21
 */
public interface IBaseView<V extends ViewDataBinding, VM extends FastBaseViewModel> {

    /**
     * 指定ContentView的LayoutID
     *
     * @return R.layout.layoutid 视图布局id
     */
    int getContentViewId();

    /**
     * 实例化Activity/Fragment的ViewDataBinding
     *
     * @return ViewDataBinding实例对象
     */
    V getViewDataBinding();

    /**
     * 实例化MVVM中的ViewModel
     *
     * @return ViewModel实例对象
     */
    VM getViewModel();


//    /**
//     * 获取DataBinding的变量参数ID
//     *
//     * @return DataBinding的变量ID
//     */
//    @Deprecated
//    int getBindingVariableId();

//    /**
//     * 获取动态权限监听器
//     *
//     * @return 动态权限监听器
//     */
//    RequestPermissionListener getPermissionListener();


}
