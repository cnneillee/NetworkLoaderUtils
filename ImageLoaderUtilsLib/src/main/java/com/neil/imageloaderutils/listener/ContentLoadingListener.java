package com.neil.imageloaderutils.listener;

/**
 * 作者：Neil on 2016/8/4 10:28.
 * 邮箱：cn.neillee@gmail.com
 */

public interface ContentLoadingListener {
    /**
     * Is called when the content was loaded from the internet.
     *
     * @param url      Loading content Url
     * @param response Loaded content
     */
    void onLoadingFinished(String url, String response);


    /**
     * Is called when error occured during the loading proccess.
     *
     * @param url      Loading content Url
     * @param errorMsg Error message
     */
    void onLoadingError(String url, String errorMsg);
}
