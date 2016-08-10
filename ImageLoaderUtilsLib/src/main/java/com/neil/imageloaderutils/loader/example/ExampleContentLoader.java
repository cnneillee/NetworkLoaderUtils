package com.neil.imageloaderutils.loader.example;

import com.neil.imageloaderutils.listener.ContentLoadingListener;
import com.neil.imageloaderutils.listener.ContentLoadingAndParseringListener;
import com.neil.imageloaderutils.listener.ContentParseCallback;
import com.neil.imageloaderutils.wrapper.IContentLoaderWrapper;

/**
 * 作者：Neil on 2016/7/29 23:08.
 * 邮箱：cn.neillee@gmail.com
 */

public class ExampleContentLoader implements IContentLoaderWrapper {

    @Override
    public void loadContent(String url, ContentLoadingListener loadingListener) {

    }

    @Override
    public <T> void loadContent(String url, ContentLoadingAndParseringListener<T> parseringListener, Class<T> classOfT) {

    }

    @Override
    public <T> void loadContent(String url, ContentLoadingAndParseringListener<T> parseringListener, ContentParseCallback<T> callback, Class<T> classOfT) {

    }
}
