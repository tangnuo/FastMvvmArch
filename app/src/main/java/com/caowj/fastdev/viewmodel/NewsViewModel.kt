package com.caowj.fastdev.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.caowj.fastdev.model.bean.NewsBean
import com.caowj.fastdev.model.net.HttpServicesFactory


class NewsViewModel : BaseViewModel() {
    /**
     * 当数据请求成功回调
     */
    private var news: MutableLiveData<NewsBean> = MutableLiveData()

    /**
     * 请求网络数据
     */
    fun requestData() {
        HttpServicesFactory.httpServiceApi.getNews()
            .enqueue(object : HttpRequestCallback<NewsBean>() {
                override fun onMySuccess(result: NewsBean) {
                    super.onMySuccess(result)
                    news.value = result
                }

                override fun onMyFailure(status: Int, message: String?) {
                    super.onMyFailure(status, message)
                }
            })
    }

    fun getNews(): MutableLiveData<NewsBean> {
        return news
    }
}
