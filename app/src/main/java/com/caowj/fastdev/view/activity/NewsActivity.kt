package com.caowj.fastdev.view.activity

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
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
    private lateinit var adapter: NewsAdapter

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
        nViewDataBinding.viewRef = this
        nViewDataBinding.viewModel = nViewModel

        //数据请求成功通知
        //数据请求成功通知
        viewModel.getNews().observe(this, Observer<MutableList<NewsBean.StoriesBean>> {
            it?.let {
                adapter.setNewData(it)
            }
        })
    }
}
