package com.neil.imageloaderutils.loader.myimpl;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.neil.imageloaderutils.Initialize;
import com.neil.imageloaderutils.R;
import com.neil.imageloaderutils.listener.ImageDownloadListener;
import com.neil.imageloaderutils.listener.ImageLoadingListener;
import com.neil.imageloaderutils.listener.ImageLoadingProgressListener;
import com.neil.imageloaderutils.utils.SDCardHelper;
import com.neil.imageloaderutils.wrapper.IImageLoaderWrapper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.download.ImageDownloader;

import java.io.File;

/**
 * 使用Univresial-image-loader框架的ImageLoader
 */
public class UniversalAndroidImageLoader implements IImageLoaderWrapper {

    private final static String LOG_TAG = UniversalAndroidImageLoader.class.getSimpleName();

    private final static String HTTP = "http";
    private final static String HTTPS = "https";

    public UniversalAndroidImageLoader(Context context) {
//        Initialize.initializeUniversial(context);
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
        this.displayImage(imageView, imageUrl, option, null, null);
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
    public void displayImage(ImageView imageView, String imageUrl, DisplayOption option, final ImageLoadingListener listener) {
        this.displayImage(imageView, imageUrl, option, listener, null);
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
    public void displayImage(ImageView imageView, String imageUrl, DisplayOption option, ImageLoadingListener listener, ImageLoadingProgressListener progressListener) {
        int imageLoadingResId = R.drawable.img_default;
        int imageErrorResId = R.drawable.img_error;
        if (option != null) {
            imageLoadingResId = option.loadingResId;
            imageErrorResId = option.loadErrorResId;
        }

        if (progressListener == null) {
            displayImage(imageView, imageUrl, option, listener);
            return;
        }

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(imageLoadingResId)
                .showImageForEmptyUri(imageErrorResId)
                .showImageOnFail(imageErrorResId)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        if (imageUrl.startsWith(HTTPS)) {
            String uri = ImageDownloader.Scheme.HTTPS.wrap(imageUrl);
            ImageLoader.getInstance().displayImage(uri, imageView, options, loadingListenerTrans(listener), progressListenerTrans(progressListener));
        } else if (imageUrl.startsWith(HTTP)) {
            String uri = ImageDownloader.Scheme.HTTP.wrap(imageUrl);
            ImageLoader.getInstance().displayImage(uri, imageView, options, loadingListenerTrans(listener), progressListenerTrans(progressListener));
        }
    }

    /**
     * 实现从本lib下的FailReason与imageLoader框架间的转化
     *
     * @param reason 框架内的FailReason
     * @return 本lib下的FailReason
     */
    private static com.neil.imageloaderutils.assist.FailReason failReasonTrans(FailReason reason) {
        Throwable cause = reason.getCause();
        FailReason.FailType failType = reason.getType();
        com.neil.imageloaderutils.assist.FailReason failReason = null;
        if (failType.equals(FailReason.FailType.IO_ERROR)) {
            failReason = new com.neil.imageloaderutils.assist.FailReason(com.neil.imageloaderutils.assist.FailReason.FailType.IO_ERROR, cause);
        } else if (failType.equals(FailReason.FailType.DECODING_ERROR)) {
            failReason = new com.neil.imageloaderutils.assist.FailReason(com.neil.imageloaderutils.assist.FailReason.FailType.DECODING_ERROR, cause);
        } else if (failType.equals(FailReason.FailType.NETWORK_DENIED)) {
            failReason = new com.neil.imageloaderutils.assist.FailReason(com.neil.imageloaderutils.assist.FailReason.FailType.NETWORK_DENIED, cause);
        } else if (failType.equals(FailReason.FailType.OUT_OF_MEMORY)) {
            failReason = new com.neil.imageloaderutils.assist.FailReason(com.neil.imageloaderutils.assist.FailReason.FailType.OUT_OF_MEMORY, cause);
        } else if (failType.equals(FailReason.FailType.UNKNOWN)) {
            failReason = new com.neil.imageloaderutils.assist.FailReason(com.neil.imageloaderutils.assist.FailReason.FailType.UNKNOWN, cause);
        }
        return failReason;
    }

    /**
     * 实现从本lib下的ImageLoadingProgressListener与imageLoader框架间的转化
     *
     * @param listener 框架内的ImageLoadingListener
     * @return 本lib下的ImageLoadingListener
     */
    private com.nostra13.universalimageloader.core.listener.ImageLoadingListener loadingListenerTrans(final ImageLoadingListener listener) {
        if (listener == null) return null;
        com.nostra13.universalimageloader.core.listener.ImageLoadingListener imageLoadingListener = new com.nostra13.universalimageloader.core.listener.ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
                listener.onLoadingStarted(s, view);
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
                com.neil.imageloaderutils.assist.FailReason myFailReason = failReasonTrans(failReason);
                listener.onLoadingFailed(s, view, myFailReason);
            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                listener.onLoadingComplete(s, view, bitmap);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {
                listener.onLoadingCancelled(s, view);
            }
        };
        return imageLoadingListener;
    }

    /**
     * 实现从本lib下的ImageLoadingProgressListener与imageLoader框架间的转化
     *
     * @param listener 框架内的ImageLoadingProgressListener
     * @return 本lib下的ImageLoadingProgressListener
     */
    private com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener progressListenerTrans(final ImageLoadingProgressListener listener) {
        if (listener == null) return null;
        return new com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String s, View view, int i, int i1) {
                listener.onProgressUpdate(s, view, i, i1);
            }
        };
    }
}
