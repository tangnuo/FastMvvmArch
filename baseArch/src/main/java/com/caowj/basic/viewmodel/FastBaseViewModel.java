package com.caowj.basic.viewmodel;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

/**
 * <pre>
 *     FastBaseViewModel
 *     作者：Caowj
 *     邮箱：caoweijian@kedacom.com
 *     日期：2021/4/23 10:39
 * </pre>
 */
public class FastBaseViewModel extends ViewModel implements Observable,LifecycleObserver {

    private PropertyChangeRegistry nCallbacks = new PropertyChangeRegistry();

    public FastBaseViewModel() {
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        nCallbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        nCallbacks.remove(callback);
    }

    /**
     * 通知所有字段属性值更改
     */
    protected void notifyChange() {
        nCallbacks.notifyCallbacks(this, 0, null);
    }

    /**
     * 通知指定字段属性值更改
     *
     * @param fieldId BR.* 字段id
     */
    protected void notifyPropertyChanged(int fieldId) {
        nCallbacks.notifyCallbacks(this, fieldId, null);
    }
}
