package com.neil.imageloaderutils.loader.myimpl;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.neil.imageloaderutils.Initialize;
import com.neil.imageloaderutils.listener.ContentLoadingListener;
import com.neil.imageloaderutils.listener.ContentParseCallback;
import com.neil.imageloaderutils.listener.ContentLoadingAndParseringListener;
import com.neil.imageloaderutils.wrapper.IContentLoaderWrapper;

/**
 * 作者：Neil on 2016/8/4 10:49.
 * 邮箱：cn.neillee@gmail.com
 */

public class VolleyContentLoader implements IContentLoaderWrapper {
    private RequestQueue mQueue;

    public VolleyContentLoader(Context context) {
        this.mQueue = Initialize.initializeVolley(context);
    }

    @Override
    public void loadContent(final String url, final ContentLoadingListener loadingListener) {
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loadingListener.onLoadingFinished(url, response);
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingListener.onLoadingError(url, error.getMessage());
            }
        };
        StringRequest request = new StringRequest(url, listener, errorListener);
        mQueue.add(request);
    }

    @Override
    public <T> void loadContent(final String url, final ContentLoadingAndParseringListener<T> parseringListener, Class<T> classOfT) {
        loadContent(url, parseringListener, null, classOfT);
    }

    @Override
    public <T> void loadContent(final String url, final ContentLoadingAndParseringListener<T> parseringListener,
                                final ContentParseCallback<T> callback, final Class<T> classOfT) {
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseringListener.onLoadingFinished(url, response);
                T parsedResult;
                // 进行解析
                try {
                    if (callback != null) {//发生回调，让用户处理解析过程
                        parsedResult = callback.parseContent(response);
                    } else {//不产生回调，此处进行默认处理
                        parsedResult = defaultParse(response, classOfT);
                    }
                    parseringListener.onParseingFinished(url, response, parsedResult);
                } catch (Exception e) {
                    parseringListener.onParseingError(url, response, e.getMessage());
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                parseringListener.onLoadingError(url, error.getMessage());
            }
        };
        StringRequest request = new StringRequest(url, listener, errorListener);
        mQueue.add(request);
    }

    private <T> T defaultParse(String response, Class<T> classOfT) {
        Gson gson = new Gson();
        T result;
        try {
            result = gson.fromJson(response, classOfT);
            return result;
        } catch (JsonSyntaxException e) {
            throw new JsonSyntaxException(e.getMessage(), e.getCause());
        }
    }
}
