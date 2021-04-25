package com.caowj.fastdev.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import com.caowj.basic.viewmodel.FastBaseViewModel;
import com.caowj.fastdev.model.bean.HttpBaseResult;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BaseViewModel extends FastBaseViewModel {

    public class HttpRequestCallback<T> implements Callback<HttpBaseResult<T>> {
        MutableLiveData<T> nLiveData;

        public HttpRequestCallback() {
            showLoading();
        }

        public HttpRequestCallback(MutableLiveData<T> liveData) {
            showLoading();
            nLiveData = liveData;
        }

        @Override
        public final void onResponse(Call<HttpBaseResult<T>> call, Response<HttpBaseResult<T>> response) {
            onMyComplete();
            HttpBaseResult<T> baseBean = response.body();
            if (baseBean == null) {
                onMyFailure(HttpBaseResult.STATUS_FAILURE, "请求失败,请稍后再试");
                return;
            }
            if (baseBean.isSuccess()) {
                onMySuccess(baseBean.getResult());
            } else {
                onMyFailure(baseBean.getStatus(), baseBean.getMessage());
            }
        }

        @Override
        public final void onFailure(Call<HttpBaseResult<T>> call, Throwable t) {
            onMyComplete();
            if (t instanceof ConnectException) {
                onMyFailure(HttpBaseResult.STATUS_NETWORK_UNCONNECTED, "UnConnect Network");
            } else if (t instanceof SocketTimeoutException) {
                onMyFailure(HttpBaseResult.STATUS_NETWORK_READTIME_OUT, "ReadTime Out");
            } else {
                onMyFailure(HttpBaseResult.STATUS_EXCEPTION, t.getMessage());
            }
        }

        ///////////////////////自定义方法//////////////////////

        public void onMySuccess(T result) {
            if (nLiveData != null) {
                nLiveData.postValue(result);
            }
        }

        public void onMyFailure(int status, String message) {
            showToast(message);
        }

        public void onMyComplete() {
            hideLoading();
        }
    }

}
