package com.neil.imageloaderutils.listener;

import android.graphics.Bitmap;
import android.view.View;

import com.neil.imageloaderutils.assist.FailReason;

/**
 * 作者：Neil on 2016/8/1 16:39.
 * 邮箱：cn.neillee@gmail.com
 */

public interface ImageDownloadListener {
    /**
     * Is called when image downloading task was started
     *
     * @param imageUri Loading image URI
     */
    void onDownoadingStarted(String imageUri, String path);

    /**
     * Is called when an error was occurred during image downloading
     *
     * @param imageUri   Loading image URI
     * @param failReason {@linkplain com.neil.imageloaderutils.assist.FailReason The reason} why image
     *                   downloading was failed
     */
    void onDownloadingFailed(String imageUri, FailReason failReason, String path);

    /**
     * Is called when image is downloaded successfully
     *
     * @param imageUri    Loaded image URI
     * @param loadedImage Bitmap of downloaded and decoded image
     */
    void onDownloadingComplete(String imageUri, Bitmap loadedImage, String path);

    /**
     * Is called when image downloading task was cancelled because View for image was reused in newer task
     *
     * @param imageUri Loading image URI
     */
    void onDownloadingCancelled(String imageUri, String path);
}
