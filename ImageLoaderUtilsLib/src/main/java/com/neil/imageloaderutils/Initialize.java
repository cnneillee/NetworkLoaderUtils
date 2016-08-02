package com.neil.imageloaderutils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * 作者：Neil on 2016/7/30 11:22.
 * 邮箱：cn.neillee@gmail.com
 */

public class Initialize {
    /**
     * Fresco框架初始化
     *
     * @param context 如工程多处使用，则传入Application，
     *                否则，则在使用的activity的setContentView()前传入activity的context进行初始化即可
     */
    public static void initializeFresco(Context context) {
        Fresco.initialize(context);
    }

    /**
     * Volley框架初始化
     *
     * @param context 如工程多处使用，则传入Application，
     *                否则，则在使用的activity的setContentView()前传入activity的context进行初始化即可
     * @return RequestQueue请求队列
     */
    public static RequestQueue initializeVolley(Context context) {
        return Volley.newRequestQueue(context);
    }

    /**
     * 初始化Universal-Image-Loader框架的参数设置
     *
     * @param context 如工程多处使用，则传入Application，
     *                否则，则在使用的activity的setContentView()前传入activity的context进行初始化即可
     */
    public static void initializeUniversial(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        Md5FileNameGenerator md5FileNameGenerator = new Md5FileNameGenerator();
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(md5FileNameGenerator);
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }
}
