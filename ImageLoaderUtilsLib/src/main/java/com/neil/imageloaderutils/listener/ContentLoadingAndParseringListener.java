package com.neil.imageloaderutils.listener;

/**
 * 作者：Neil on 2016/8/4 10:37.
 * 邮箱：cn.neillee@gmail.com
 */

public interface ContentLoadingAndParseringListener<T> {
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

    /**
     * Is called when the String content was parseed into the T type
     *
     * @param url         Loading content url
     * @param responseStr Loading string content
     * @param response    Parsed result
     */
    void onParseingFinished(String url, String responseStr, T response);

    /**
     * Is called when error occured during the paesing proccess.
     *
     * @param url      Loading content url
     * @param response Loading string content
     * @param errorMsg Error message
     */
    void onParseingError(String url, String response, String errorMsg);
}
