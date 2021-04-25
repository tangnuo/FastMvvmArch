package com.caowj.basic.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.caowj.basic.helper.FastCoreHelper;
import com.caowj.basic.view.IBaseView;
import com.caowj.basic.view.INotice;
import com.caowj.basic.viewmodel.FastBaseViewModel;


/**
 * <pre>
 *     FastBaseActivity
 *     作者：Caowj
 *     邮箱：caoweijian@kedacom.com
 *     日期：2021/4/23 10:32
 * </pre>
 */
public abstract class FastBaseActivity<V extends ViewDataBinding, VM extends FastBaseViewModel>
        extends AppCompatActivity implements LifecycleOwner, IBaseView<V, VM>, INotice {

    protected V nViewDataBinding;
    protected VM nViewModel;
    private LifecycleRegistry nLifecycleRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Lifecycle lifecycle = getLifecycle();
        if (lifecycle instanceof LifecycleRegistry) {
            nLifecycleRegistry = (LifecycleRegistry) lifecycle;
        } else {
            nLifecycleRegistry = new LifecycleRegistry(this);
        }

//        nLifecycleRegistry.markState(Lifecycle.State.INITIALIZED);
        super.onCreate(savedInstanceState);
        performDataBinding();
        // 初始化
        initFastObserver();
        // 添加此行代码，LiveData类型数据被绑定后能刷新UI
        if (nViewDataBinding != null) {
            nViewDataBinding.setLifecycleOwner(this);
        }

//        nLifecycleRegistry.markState(Lifecycle.State.CREATED);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    @Override
    public abstract int getContentViewId();

    @Override
    public V getViewDataBinding() {
        if (nViewDataBinding == null) {
            int contentViewId = getContentViewId();
            if (contentViewId > 0) {
                nViewDataBinding = DataBindingUtil.setContentView(this, contentViewId);
            } else {
                Log.w("ViewDataBinding", "can't find ContentViewId,please use the annotation of @ContentView ,or override method of getContentViewId() ");
            }

        }
        return nViewDataBinding;
    }

    @Override
    public VM getViewModel() {
        if (nViewModel == null) {
            Class<VM> viewModelClass = FastCoreHelper.getViewModelClass(this);
            if (viewModelClass != null) {
                nViewModel = ViewModelProviders.of(this).get(viewModelClass);
            }
        }
        return nViewModel;
    }


    /**
     * MVVM参数绑定初始化
     */
    private void performDataBinding() {
        //设置视图内容
        getViewDataBinding();
        //实例化ViewModel
        getViewModel();
//        initViewDataBinding();
        if (nViewModel != null) {
            nLifecycleRegistry.addObserver(nViewModel);
        }
    }

    /**
     * 初始化监听ViewModel的数据变化
     */
    @CallSuper
    private void initFastObserver() {
        VM viewModel = getViewModel();
        if (viewModel != null && viewModel instanceof FastBaseViewModel) {
            ((FastBaseViewModel) viewModel).showLoadingLiveData.observe(this, new Observer<String>() {
                @Override
                public void onChanged(@Nullable String loadingMessage) {
                    if (loadingMessage == null) {
                        FastBaseActivity.this.hideLoading();
                    } else {
                        FastBaseActivity.this.showLoading(loadingMessage);
                    }
                }
            });
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        // TODO: 2021/4/25 弹窗待实现   可参考：NoticeManager
    }

    @Override
    public void showLoading(String message) {
        // TODO: 2021/4/25 弹窗待实现   可参考：NoticeManager
    }

    @Override
    public void hideLoading() {
        // TODO: 2021/4/25 弹窗待实现   可参考：NoticeManager
    }
    //    /**
//     * 初始化DataBinding 的Variable变量字段
//     */
//    private void initViewDataBinding() {
//        //绑定Model
//        if (nViewDataBinding != null) {
//            boolean result = LegoAnnotationHandler.setViewModelFieldValueOfViewDataBinding(nViewDataBinding, nViewModel);
//            if (!result) {
//                LegoLog.w("ViewDataBinding", "can't invoke the method of " + nViewDataBinding.getClass().getName() + "#setViewModel,please add the variable of 'viewModel' to " + getResources().getResourceName(getContentViewId()) + ".xml");
//            }
//            result = LegoAnnotationHandler.setViewFieldValueOfViewDataBinding(nViewDataBinding, this);
//            if (!result) {
//                LegoLog.w("ViewDataBinding", "can't invoke the method of " + nViewDataBinding.getClass().getName() + "#setViewRef,please add the variable of 'viewRef' to " + getResources().getResourceName(getContentViewId()) + ".xml");
//            }
//        }
//    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        nLifecycleRegistry.markState(Lifecycle.State.STARTED);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        nLifecycleRegistry.markState(Lifecycle.State.RESUMED);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        nLifecycleRegistry.markState(Lifecycle.State.DESTROYED);
        nLifecycleRegistry = null;

    }

}
