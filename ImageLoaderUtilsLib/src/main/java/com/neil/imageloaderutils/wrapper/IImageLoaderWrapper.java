package com.neil.imageloaderutils.wrapper;

import android.content.Context;
import android.widget.ImageView;

import com.neil.imageloaderutils.listener.ImageDownloadListener;
import com.neil.imageloaderutils.listener.ImageLoadingListener;
import com.neil.imageloaderutils.listener.ImageLoadingProgressListener;

import java.io.File;

/**
 * 作者：Neil on 2016/7/29 23:00.
 * 邮箱：cn.neillee@gmail.com
 */

public interface IImageLoaderWrapper {
    /**
     * 显示图片
     *
     * @param imageView 显示图片的ImageView
     * @param imageUrl  图片资源的URL
     * @param option    显示参数设置
     */
    void displayImage(ImageView imageView, String imageUrl, DisplayOption option);

    /**
     * 显示图片
     *
     * @param imageView 显示图片的ImageView
     * @param imageUrl  图片资源的URL
     * @param option    显示参数设置
     * @param listener  图片加载监听事件
     */
    void displayImage(ImageView imageView, String imageUrl, DisplayOption option, ImageLoadingListener listener);

    /**
     * 显示图片
     *
     * @param imageView        显示图片的ImageView
     * @param imageUrl         图片资源的URL
     * @param option           显示参数设置
     * @param listener         图片加载监听事件
     * @param progressListener 图片加载进度监听事件
     */
    void displayImage(ImageView imageView, String imageUrl, DisplayOption option, ImageLoadingListener listener, ImageLoadingProgressListener progressListener);


    /**
     * 图片加载参数
     */
    public static class DisplayOption {
        /**
         * 加载中的资源id
         */
        public int loadingResId;
        /**
         * 加载失败的资源id
         */
        public int loadErrorResId;
    }
}
