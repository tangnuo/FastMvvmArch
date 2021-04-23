package com.caowj.fastdev.view.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.caowj.common.adapter.BaseDBRVAdapter;
import com.caowj.fastdev.BR;
import com.caowj.fastdev.R;
import com.caowj.fastdev.databinding.ItemNesBinding;
import com.caowj.fastdev.model.bean.NewsBean;

/**
 * <pre>
 *     NewsAdapter
 *     作者：Caowj
 *     邮箱：caoweijian@kedacom.com
 *     日期：2021/4/23 17:29
 * </pre>
 */
public class NewsAdapter extends BaseDBRVAdapter<NewsBean.StoriesBean, ItemNesBinding> {

    public NewsAdapter() {
        super(R.layout.item_nes, BR.bean);
    }

    /**
     * 绑定Adapter的ImageView
     *
     * @param imageView
     * @param url       图片地址
     */
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
