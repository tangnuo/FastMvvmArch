package com.caowj.fastdev.model.net

import android.support.annotation.NonNull
import com.caowj.fastdev.BuildConfig
import com.caowj.lib_network.retrofit.HttpRetrofitFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit


object HttpServicesFactory {
    private var httpRetrofitFactory: HttpRetrofitFactory = HttpRetrofitFactory.instance(object : HttpRetrofitFactory.Interceptor() {
        override fun baseUrl(): String {
            return BuildConfig.HTTP_BASE_URL
        }

        override fun okHttpClient(okHttpClient: OkHttpClient.Builder) {}
        override fun retrofit(retrofit: Retrofit.Builder) {}
    })

    private fun <T> createService(@NonNull clazz: Class<T>): T {
        return httpRetrofitFactory.createHttpService(clazz)
    }

    @get:Synchronized
    val httpServiceApi: HttpServiceApi
        get() = createService(HttpServiceApi::class.java)

    init {
        //init HttpRetrofitFactory
    }
}
