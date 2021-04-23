package com.caowj.fastdev.view.activity

import android.os.Bundle
import com.caowj.fastdev.R
import com.caowj.fastdev.databinding.ActivityNewsBinding
import com.caowj.fastdev.viewmodel.NewsViewModel

/**
 *  新闻中心
 *  作者：Caowj
 *  邮箱：caoweijian@kedacom.com
 *  日期：2021/4/23 11:34
 */
class NewsActivity : BaseActivity<ActivityNewsBinding, NewsViewModel>() {

    override fun getContentViewId() = R.layout.activity_news

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nViewDataBinding.tvMsg.text = "新闻中心"
    }

}
