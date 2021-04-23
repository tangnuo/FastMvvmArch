package com.caowj.basic.base;

import android.app.Activity;
import android.app.Dialog;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caowj.basic.helper.FastCoreHelper;
import com.caowj.basic.view.IBaseView;
import com.caowj.basic.viewmodel.FastBaseViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * <pre>
 *     FastBaseFragment
 *     作者：Caowj
 *     邮箱：caoweijian@kedacom.com
 *     日期：2021/4/23 10:49
 * </pre>
 */
public abstract class FastBaseFragment<V extends ViewDataBinding, VM extends FastBaseViewModel> extends Fragment implements IBaseView<V, VM> {

    //private LifecycleRegistry nLifecycleRegistry;

    protected V nViewDataBinding;
    protected VM nViewModel;


    @Override
    public abstract int getContentViewId();

    @Override
    public V getViewDataBinding() {
        return nViewDataBinding;
    }

  

    @Override
    public VM getViewModel() {
        if (nViewModel == null) {
            Class<VM> viewModelClass = FastCoreHelper.getViewModelClass(this);
            if (viewModelClass != null) {
                nViewModel = ViewModelProviders.of(getActivity()).get(viewModelClass);
            }

        }

        return nViewModel;
    }


    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
//        nLifecycleRegistry = new LifecycleRegistry(this);
//        nLifecycleRegistry.markState(Lifecycle.State.INITIALIZED);

        super.onCreate(savedInstanceState);
//        getLifecycle().addObserver(new LegoMessageAnnotationsHandler(this));

        getViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        int contentViewId = getContentViewId();
        if (contentViewId > 0) {
            View rootView = inflater.inflate(contentViewId, container, false);
            nViewDataBinding = DataBindingUtil.bind(rootView);
            if (nViewModel != null) {
                getLifecycle().addObserver(nViewModel);
                // 添加此行代码，LiveData类型数据被绑定后能刷新UI
                nViewDataBinding.setLifecycleOwner(this);
                
                //绑定ViewModel
                // nViewDataBinding.setVariable(getBindingVariableId(), nViewModel);
                nViewDataBinding.executePendingBindings();
            } else {
                Log.w("ViewDataBinding", "can't find instance of ViewModel,please use the annotation of @ViewModel ,or override method of getViewModel() ");
            }

            return rootView;
        } else {
            Log.w("ViewDataBinding", "can't find ContentViewId,please use the annotation of @ContentView ,or override method of getContentViewId() ");
        }

        return null;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        nLifecycleRegistry.markState(Lifecycle.State.CREATED);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
//        nLifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    @Override
    public void onResume() {
        super.onResume();
//        nLifecycleRegistry.markState(Lifecycle.State.RESUMED);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        nLifecycleRegistry.markState(Lifecycle.State.DESTROYED);
//        nLifecycleRegistry = null;

    }
    
}
