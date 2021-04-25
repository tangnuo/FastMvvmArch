package com.caowj.fastdev.model.net

import com.caowj.fastdev.model.bean.HttpBaseResult
import com.caowj.fastdev.model.bean.NewsBean
import retrofit2.Call
import retrofit2.http.GET

interface HttpServiceApi {

    /**
     * 新闻列表
     * @return Call<HttpBaseResult<NewsBean>>
     */
    @GET("https://news-at.zhihu.com/api/4/news/latest")
    fun getNews(): Call<HttpBaseResult<NewsBean>>
}
