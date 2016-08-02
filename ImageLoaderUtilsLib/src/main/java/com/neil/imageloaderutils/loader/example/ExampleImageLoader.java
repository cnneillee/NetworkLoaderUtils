package com.neil.imageloaderutils.loader.example;

import android.content.Context;
import android.widget.ImageView;

import com.neil.imageloaderutils.listener.ImageDownloadListener;
import com.neil.imageloaderutils.listener.ImageLoadingListener;
import com.neil.imageloaderutils.listener.ImageLoadingProgressListener;
import com.neil.imageloaderutils.wrapper.IImageLoaderWrapper;

import java.io.File;

/**
 * 作者：Neil on 2016/7/29 23:05.
 * 邮箱：cn.neillee@gmail.com
 */

/**
 * IImageLoaderWrapper的具体实现类
 */
public class ExampleImageLoader implements IImageLoaderWrapper {
    @Override
    public void displayImage(ImageView imageView, File imageFile, DisplayOption option) {

    }

    @Override
    public void displayImage(ImageView imageView, String imageUrl, DisplayOption option) {

    }

    @Override
    public void displayImage(ImageView imageView, String imageUrl, DisplayOption option, ImageLoadingListener listener) {

    }

    @Override
    public void displayImage(ImageView imageView, String imageUrl, DisplayOption option, ImageLoadingListener listener, ImageLoadingProgressListener progressListener) {

    }

    @Override
    public void downloadImage(Context context, String imageUrl, String imagePath, int imageName, ImageDownloadListener listener) {

    }
}
