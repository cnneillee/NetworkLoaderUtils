package com.neil.imageloaderutils.wrapper;

import com.neil.imageloaderutils.listener.ContentLoadingListener;
import com.neil.imageloaderutils.listener.ContentLoadingAndParseringListener;
import com.neil.imageloaderutils.listener.ContentParseCallback;

/**
 * 作者：Neil on 2016/7/29 23:00.
 * 邮箱：cn.neillee@gmail.com
 */

public interface IContentLoaderWrapper {
    /**
     * Load content
     *
     * @param url             Loading content url
     * @param loadingListener Content loading callback
     */
    void loadContent(String url, ContentLoadingListener loadingListener);

    /**
     * Load content
     *
     * @param url               Loading content url
     * @param parseringListener Content parsing callback
     */
    <T> void loadContent(String url, ContentLoadingAndParseringListener<T> parseringListener, Class<T> classOfT);

    /**
     * Load content
     *
     * @param url               Loading content url
     * @param parseringListener Content parsing callback
     * @param callback          Parsing callback
     */
    <T> void loadContent(String url, ContentLoadingAndParseringListener<T> parseringListener, ContentParseCallback<T> callback, Class<T> classOfT);
}
