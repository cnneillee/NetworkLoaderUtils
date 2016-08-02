package com.neil.imageloaderutils.loader.myimpl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.storage.StorageManager;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.neil.imageloaderutils.Initialize;
import com.neil.imageloaderutils.R;
import com.neil.imageloaderutils.assist.FailReason;
import com.neil.imageloaderutils.listener.ImageDownloadListener;
import com.neil.imageloaderutils.listener.ImageLoadingListener;
import com.neil.imageloaderutils.listener.ImageLoadingProgressListener;
import com.neil.imageloaderutils.utils.SDCardHelper;
import com.neil.imageloaderutils.wrapper.IImageLoaderWrapper;

import java.io.File;

/**
 * 作者：Neil on 2016/7/30 12:19.
 * 邮箱：cn.neillee@gmail.com
 */

/**
 * 使用Volley框架的ImageLoader
 */
public class VolleyImageLoader implements IImageLoaderWrapper {
    private RequestQueue mQueue;
    private static final String LOG_TAG = VolleyImageLoader.class.getSimpleName();

    public VolleyImageLoader(Context context) {
        this.mQueue = Initialize.initializeVolley(context);
    }

    /**
     * 显示图片
     *
     * @param imageView 显示图片的ImageView
     * @param imageFile 图片文件
     * @param option    显示参数设置
     */
    @Override
    public void displayImage(ImageView imageView, File imageFile, DisplayOption option) {
        int imageLoadingResId = R.drawable.img_default;
        int imageErrorResId = R.drawable.img_error;
        if (option != null) {
            imageLoadingResId = option.loadingResId;
            imageErrorResId = option.loadErrorResId;
        }

        imageView.setImageResource(imageLoadingResId);

        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());

        if (bitmap == null) {
            Log.e(LOG_TAG, "解析图片文件出错，图片路径为:|" + imageFile.getAbsolutePath() + "|");
            imageView.setImageResource(imageErrorResId);
        } else {
            imageView.setImageBitmap(bitmap);
        }
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
    public void displayImage(final ImageView imageView, final String imageUrl, final DisplayOption option, final ImageLoadingListener listener) {
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
    public void displayImage(final ImageView imageView, final String imageUrl, final DisplayOption option, final ImageLoadingListener listener, ImageLoadingProgressListener progressListener) {
        if (progressListener != null) Log.e(LOG_TAG, "暂不支持加载图片的进度回调");

        if (option != null) imageView.setImageResource(option.loadingResId);

        Response.Listener<Bitmap> bitmapListener = new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imageView.setImageBitmap(response);
                if (listener != null) listener.onLoadingComplete(imageUrl, imageView, response);
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (option != null) imageView.setImageResource(option.loadingResId);
                FailReason failReason = new FailReason(FailReason.FailType.UNKNOWN, error.getCause());
                listener.onLoadingFailed(imageUrl, imageView, failReason);
            }
        };

        ImageRequest imageRequest = new ImageRequest(imageUrl, bitmapListener, 0, 0, Bitmap.Config.RGB_565, errorListener);
        mQueue.add(imageRequest);
    }

    /**
     * 显示图片
     *
     * @param context   上下文
     * @param imageUrl  图片资源的URL
     * @param imagePath 图片下载的目录
     * @param imageName 图片保存路径
     * @param listener  图片下载的监听事件
     */
    @Override
    public void downloadImage(final Context context, final String imageUrl, final String imagePath, final int imageName, final ImageDownloadListener listener) {
        listener.onDownoadingStarted(imageUrl, imagePath);

        Response.Listener<Bitmap> bitmapListener = new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                boolean saveResult = SDCardHelper.saveBitmapToSDCardPrivateCacheDir(response, imagePath + imageName, context);
                if (saveResult)
                    listener.onDownloadingFailed(imagePath, new FailReason(FailReason.FailType.UNKNOWN, null), imagePath);
                else
                    listener.onDownloadingComplete(imageUrl, response, imagePath);
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onDownloadingFailed(imageUrl, new FailReason(FailReason.FailType.UNKNOWN, error.getCause()), imagePath);
            }
        };

        ImageRequest imageRequest = new ImageRequest(imageUrl, bitmapListener, 0, 0, Bitmap.Config.ARGB_8888, errorListener);
        mQueue.add(imageRequest);
    }
}
