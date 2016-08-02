package com.neil.imageloaderutils.loader.myimpl;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.neil.imageloaderutils.Initialize;
import com.neil.imageloaderutils.R;
import com.neil.imageloaderutils.listener.ImageDownloadListener;
import com.neil.imageloaderutils.listener.ImageLoadingListener;
import com.neil.imageloaderutils.listener.ImageLoadingProgressListener;
import com.neil.imageloaderutils.wrapper.IImageLoaderWrapper;

import java.io.File;

/**
 * 作者：Neil on 2016/7/30 11:26.
 * 邮箱：cn.neillee@gmail.com
 */

/**
 * 使用Fresco框架的ImageLoader
 */
public class FrescoImageLoader implements IImageLoaderWrapper {

    private final static String LOG_TAG = FrescoImageLoader.class.getSimpleName();

    public FrescoImageLoader(Context context) {
//        Initialize.initializeFresco(context);
    }

    /**
     * 显示图片
     *
     * @param imageView 显示图片的ImageView
     * @param imageFile 图片文件
     * @param option    显示参数设置
     */
    @Override
    public void displayImage(ImageView imageView, File imageFile, final DisplayOption option) {
        displayImage(imageView, imageFile.getAbsolutePath(), option, null);
    }

    /**
     * 显示图片
     *
     * @param imageView 显示图片的ImageView
     * @param imageUrl  图片资源的URL
     * @param option    显示参数设置
     */
    @Override
    public void displayImage(ImageView imageView, String imageUrl, DisplayOption option) {
        displayImage(imageView, imageUrl, option, null);
    }

    /**
     * 显示图片
     *
     * @param imageView 显示图片的ImageView
     * @param imageUrl  图片资源的URL
     * @param option    显示参数设置
     * @param listener  图片加载监听事件
     */
    @Override
    public void displayImage(ImageView imageView, String imageUrl, DisplayOption option, ImageLoadingListener listener) {
        displayImage(imageView, imageUrl, option, listener, null);
    }

    /**
     * 显示图片
     *
     * @param imageView        显示图片的ImageView
     * @param imageUrl         图片资源的URL
     * @param option           显示参数设置
     * @param listener         图片加载监听事件
     * @param progressListener 图片加载进度监听事件
     */
    @Override
    public void displayImage(ImageView imageView, final String imageUrl, final DisplayOption option, ImageLoadingListener listener, final ImageLoadingProgressListener progressListener) {
        // 首先确保使用的是fresco的imageview控件
        if (!judgeIsSimpleDraweeView(imageView)) {
            Log.e(LOG_TAG, "请将控件定义为com.facebook.drawee.view.SimpleDraweeView");
            return;
        }
        final SimpleDraweeView simpleDraweeView = (SimpleDraweeView) imageView;

        int imageLoadingResId = R.drawable.img_default;
        int imageErrorResId = R.drawable.img_error;
        if (option != null) {
            imageLoadingResId = option.loadingResId;
            imageErrorResId = option.loadErrorResId;
        }
        simpleDraweeView.setImageResource(imageLoadingResId);

        final int finalFailedResId = imageErrorResId;
        ControllerListener clistener = new BaseControllerListener<ImageInfo>() {
            // 图片加载成功时会被调用
            @Override
            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);
                Log.e(LOG_TAG, imageUrl + "加载成功！");
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                super.onFailure(id, throwable);
                Log.e(LOG_TAG, imageUrl + "\t加载失败" + throwable.getMessage());
                simpleDraweeView.setImageResource(finalFailedResId);
                progressListener.onProgressUpdate(imageUrl, simpleDraweeView, 0, 100);
            }
        };

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(imageUrl)
                .setControllerListener(clistener)
                .build();
        simpleDraweeView.setController(controller);
    }

    /**
     * 图片下载
     *
     * @param context   上下文
     * @param imageUrl  图片资源的URL
     * @param imagePath 图片下载的目录
     * @param imageName 图片保存路径
     * @param listener  图片下载的监听事件
     */
    @Override
    public void downloadImage(Context context, String imageUrl, String imagePath, int imageName, ImageDownloadListener listener) {

    }

    /**
     * 由于Fresco的图片加载使用的是SimpleDraweeView类型，故此处进行判断
     *
     * @return 判定似乎否为DraweeView
     */
    private boolean judgeIsSimpleDraweeView(ImageView imageView) {
        return imageView instanceof SimpleDraweeView;
    }
}
