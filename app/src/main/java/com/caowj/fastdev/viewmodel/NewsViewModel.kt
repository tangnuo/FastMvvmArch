package com.caowj.fastdev.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.caowj.fastdev.model.bean.NewsBean
import com.caowj.fastdev.model.net.HttpServicesFactory
import com.caowj.fastdev.util.GsonUtil
import com.caowj.lib_logs.LegoLog
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsViewModel : BaseViewModel() {
    /**
     * 当数据请求成功回调
     */
    private var news: MutableLiveData<MutableList<NewsBean.StoriesBean>> = MutableLiveData()

    /**
     * 请求网络数据
     */
    fun requestData() {
        LegoLog.d("获取新闻列表")
//        {
//            "date": "20210425",
//            "stories": [{
//                "image_hue": "0x735a4f",
//                "title": "小事 · 求学长路漫漫多崎岖",
//                "url": "https:\/\/daily.zhihu.com\/story\/9735377",
//                "hint": "VOL.1291",
//                "ga_prefix": "042507",
//                "images": ["https:\/\/pic2.zhimg.com\/v2-9eb8d85e56471bfccdd4518167ab16c8.jpg?source=8673f162"],
//                "type": 0,
//                "id": 9735377
//            }],
//            "top_stories": [{
//                "image_hue": "0x2e2320",
//                "hint": "作者 \/ 章牧之",
//                "url": "https:\/\/daily.zhihu.com\/story\/9735304",
//                "image": "https:\/\/pic3.zhimg.com\/v2-9145abcce9999ce65560f6eee35c932a.jpg?source=8673f162",
//                "title": "大家都内推，内推还有什么意义？",
//                "ga_prefix": "042307",
//                "type": 0,
//                "id": 9735304
//            }]
//        }

        HttpServicesFactory.httpServiceApi.getNews()
            .enqueue(object : Callback<Any> {
                override fun onFailure(call: Call<Any>, t: Throwable) {
                    LegoLog.d("接口异常：" + t.message)
                }

                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    LegoLog.d("接口成功${response.body()}")

                    val jsonObject: JsonObject = GsonUtil.toJsonObject(response.body())
                    val stories = jsonObject["stories"].asJsonArray
                    val list =
                        GsonUtil.getObjectList(stories.toString(), NewsBean.StoriesBean::class.java)
                    news.value = list
                }
            })
    }

    fun getNews(): MutableLiveData<MutableList<NewsBean.StoriesBean>> {
        return news
    }
}
