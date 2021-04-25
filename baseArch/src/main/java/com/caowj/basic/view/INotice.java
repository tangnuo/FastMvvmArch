package com.caowj.basic.view;


/**
 * @author : yuanbingbing
 * @since : 2018/7/25 13:16
 */
public interface INotice {
    void showToast(String message);

    void showLoading();

    void showLoading(String message);

    void hideLoading();
}
