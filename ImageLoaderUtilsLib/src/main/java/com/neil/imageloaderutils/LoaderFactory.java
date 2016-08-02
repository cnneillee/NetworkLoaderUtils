package com.neil.imageloaderutils;

/**
 * 作者：Neil on 2016/7/29 22:57.
 * 邮箱：cn.neillee@gmail.com
 */

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

    public enum TYPE {
        VOLLEY, UNIVERSIAL, FRESSCO
    }

    private LoaderFactory() {
    }

    /**
     * 获取图片加载器
     *
     * @return 返回IImageLoaderWrapper（接口），隐藏了具体的实现
     */
    public static IImageLoaderWrapper getImageLoader(Context context) {
        if (sInstanceImageLoader == null) {
            synchronized (LoaderFactory.class) {
                if (sInstanceImageLoader == null) {
                    sInstanceImageLoader = new VolleyImageLoader(context);
                }
            }
        }
        return sInstanceImageLoader;
    }

    private static int currentType = -1;
    private static IImageLoaderWrapper[] wrapper = new IImageLoaderWrapper[3];

    /**
     * 获取图片加载器
     *
     * @return 返回IImageLoaderWrapper（接口），隐藏了具体的实现
     */
    public static IImageLoaderWrapper getImageLoaderWithType(Context context, int type) {
        sInstanceImageLoader = wrapper[type];
        if (sInstanceImageLoader == null) {
            synchronized (LoaderFactory.class) {
                if (sInstanceImageLoader == null) {
                    switch (type) {
                        case 0:
                            wrapper[type] = new FrescoImageLoader(context);
                            break;
                        case 1:
                            wrapper[type] = new UniversalAndroidImageLoader(context);
                            break;
                        case 2:
                            wrapper[type] = new VolleyImageLoader(context);
                            break;
                    }
                    sInstanceImageLoader = wrapper[type];
                }
            }
        }
        return sInstanceImageLoader;
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
