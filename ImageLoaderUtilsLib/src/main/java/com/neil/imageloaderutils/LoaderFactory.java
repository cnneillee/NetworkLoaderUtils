package com.neil.imageloaderutils;

/**
 * 作者：Neil on 2016/7/29 22:57.
 * 邮箱：cn.neillee@gmail.com
 */

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.renderscript.Type;

import com.neil.imageloaderutils.loader.example.ExampleContentLoader;
import com.neil.imageloaderutils.loader.example.ExampleImageLoader;
import com.neil.imageloaderutils.loader.myimpl.FrescoImageLoader;
import com.neil.imageloaderutils.loader.myimpl.UniversalAndroidImageLoader;
import com.neil.imageloaderutils.loader.myimpl.VolleyImageLoader;
import com.neil.imageloaderutils.wrapper.IContentLoaderWrapper;
import com.neil.imageloaderutils.wrapper.IImageLoaderWrapper;

/**
 * Loader的工厂类
 */
public class LoaderFactory {
    private static IImageLoaderWrapper sInstanceImageLoader;
    private static IContentLoaderWrapper sInstanceContentLoader;
    private static TYPE sImageLoaderType;

    public enum TYPE {
        VOLLEY, UNIVERSIAL, FRESSCO

    }

    private LoaderFactory() {
    }

    public static void initialize(Context context, TYPE imageLoaderType) {
        sImageLoaderType = imageLoaderType;
        initializeWithType(context, imageLoaderType);
        sInstanceImageLoader = getImageLoaderWithType(context, imageLoaderType);
    }

    /**
     * 获取图片加载器
     *
     * @return 返回IImageLoaderWrapper（接口），隐藏了具体的实现
     */
    public static IImageLoaderWrapper getImageLoader(Context context) {
        if (sInstanceImageLoader == null) {
            throw new IllegalArgumentException("has not initialized");
        }
        return sInstanceImageLoader;
    }

    /**
     * 获取图片加载器
     *
     * @return 返回IImageLoaderWrapper（接口），隐藏了具体的实现
     */
    public static IImageLoaderWrapper getImageLoaderWithType(Context context, TYPE type) {
        if (sInstanceImageLoader == null) {
            synchronized (LoaderFactory.class) {
                if (sInstanceImageLoader == null) {
                    if (type.equals(TYPE.FRESSCO)) {
                        sInstanceImageLoader = new FrescoImageLoader(context);
                    } else if (type.equals(TYPE.UNIVERSIAL)) {
                        sInstanceImageLoader = new UniversalAndroidImageLoader(context);
                    } else if (type.equals(TYPE.VOLLEY)) {
                        sInstanceImageLoader = new VolleyImageLoader(context);
                    }
                }
            }
        }
        return sInstanceImageLoader;
    }

    private static void initializeWithType(Context context, TYPE type) {
        if (type.equals(TYPE.FRESSCO)) {
            Initialize.initializeVolley(context);
        } else if (type.equals(TYPE.UNIVERSIAL)) {
            Initialize.initializeVolley(context);
        } else if (type.equals(TYPE.VOLLEY)) {
            Initialize.initializeVolley(context);
        }
    }

    /**
     * 获取内容加载器
     *
     * @return 返回IContentLoaderWrapper（接口），隐藏了具体的实现
     */
    public static IContentLoaderWrapper getContentLoader() {
        if (sInstanceContentLoader == null) {
            synchronized (LoaderFactory.class) {
                if (sInstanceContentLoader == null) {
                    sInstanceContentLoader = new ExampleContentLoader();
                }
            }
        }
        return sInstanceContentLoader;
    }
}
