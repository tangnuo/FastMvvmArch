package com.caowj.fastdev.view.activity

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.caowj.fastdev.R
import com.caowj.fastdev.databinding.ActivityNewsBinding
import com.caowj.fastdev.model.bean.NewsBean
import com.caowj.fastdev.view.adapter.NewsAdapter
import com.caowj.fastdev.viewmodel.NewsViewModel

/**
 *  新闻中心
 *  作者：Caowj
 *  邮箱：caoweijian@kedacom.com
 *  日期：2021/4/23 11:34
 */
class NewsActivity : BaseActivity<ActivityNewsBinding, NewsViewModel>() {
    private var adapter: NewsAdapter? = null

    override fun getContentViewId() = R.layout.activity_news

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        super.initView()
        val manager = LinearLayoutManager(this)
        adapter = NewsAdapter()
        nViewDataBinding.rvNews.layoutManager = manager
        nViewDataBinding.rvNews.adapter = adapter
    }

    override fun initData() {
        super.initData()
        //数据请求成功通知
        //数据请求成功通知
        viewModel.getNews().observe(this,
            Observer<NewsBean> { TODO("Not yet implemented") })
    }

    /**
     * 按钮点击事件
     */
    fun requestData(view: View?) {
        viewModel.requestData()
    }

}
