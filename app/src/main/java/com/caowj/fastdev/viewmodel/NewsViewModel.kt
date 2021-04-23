package com.caowj.fastdev.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.caowj.fastdev.model.bean.NewsBean


class NewsViewModel:BaseViewModel() {
    /**
     * 当数据请求成功回调
     */
    private var news: MutableLiveData<NewsBean> = MutableLiveData()

    /**
     * 请求网络数据
     */
    fun requestData() {
//        showDialog.setValue(true, "加载中")
//        val disposable: Disposable = Api.getInstance().news()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : Consumer<NewsBean?>() {
//                @Throws(Exception::class)
//                fun accept(newsBean: NewsBean?) {
//                    showDialog.setValue(false)
//                    news.setValue(newsBean)
//                }
//            }, object : Consumer<Throwable?>() {
//                @Throws(Exception::class)
//                fun accept(throwable: Throwable?) {
//                    showDialog.setValue(false)
//                    /*
//                         * 发生了错误，通知UI层
//                         */error.setValue("发生错误了")
//                }
//            })
//        addDisposable(disposable)
    }

    fun getNews(): MutableLiveData<NewsBean> {
        return news
    }
}
