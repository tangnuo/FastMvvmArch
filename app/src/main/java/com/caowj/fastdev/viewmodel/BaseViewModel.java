package com.caowj.fastdev.viewmodel;

import com.caowj.basic.viewmodel.FastBaseViewModel;


public class BaseViewModel extends FastBaseViewModel {

//    public class HttpRequestCallback<T> implements Callback<HttpBaseResult<T>> {
//        MutableLiveData<T> nLiveData;
//
//        public HttpRequestCallback() {
//            showLoading();
//        }
//
//        public HttpRequestCallback(MutableLiveData<T> liveData) {
//            showLoading();
//            nLiveData = liveData;
//        }
//
//        @Override
//        public final void onResponse(Call<HttpBaseResult<T>> call, Response<HttpBaseResult<T>> response) {
//            onComplete();
//            HttpBaseResult<T> baseBean = response.body();
//            if (baseBean == null) {
//                onFailure(HttpBaseResult.STATUS_FAILURE, "请求失败,请稍后再试");
//                return;
//            }
//            if (baseBean.isSuccess()) {
//                onSuccess(baseBean.getData());
//            } else {
//                onFailure(baseBean.getStatus(), baseBean.getResult());
//            }
//        }
//
//        @Override
//        public final void onFailure(Call<HttpBaseResult<T>> call, Throwable t) {
//            onComplete();
//            if (t instanceof ConnectException) {
//                onFailure(HttpBaseResult.STATUS_NETWORK_UNCONNECTED, "UnConnect Network");
//            } else if (t instanceof SocketTimeoutException) {
//                onFailure(HttpBaseResult.STATUS_NETWORK_READTIME_OUT, "ReadTime Out");
//            } else {
//                onFailure(HttpBaseResult.STATUS_EXCEPTION, t.getMessage());
//            }
//        }
//
//        public void onSuccess(T result) {
//            if (nLiveData != null) {
//                nLiveData.postValue(result);
//            }
//        }
//
//        public void onFailure(int status, String message) {
//            showToast(message, TOAST_ERROR);
//        }
//
//        public void onComplete() {
//            hideLoading();
//        }
//
//    }


}
