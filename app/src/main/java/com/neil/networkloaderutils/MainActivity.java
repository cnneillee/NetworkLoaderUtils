package com.neil.networkloaderutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.neil.imageloaderutils.LoaderFactory;
import com.neil.imageloaderutils.wrapper.IImageLoaderWrapper;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private ImageView iv1;
    private ImageView iv2;

    private String[] urls = {
            "http://image5.tuku.cn/wallpaper/Landscape%20Wallpapers/8294_2560x1600.jpg",
            "http://img2.sucaifengbao.com/813/813b_109_XVTb.jpg",
            "http://img1.gtimg.com/edu/pics/hv1/157/163/1128/73389922.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        loadData();
    }

    private void loadData() {
        IImageLoaderWrapper.DisplayOption option = new IImageLoaderWrapper.DisplayOption();
        option.loadErrorResId = R.drawable.img_error;
        option.loadingResId = R.drawable.img_default;
        LoaderFactory.getImageLoaderWithType(this, 0).displayImage(iv, urls[0], option);
        LoaderFactory.getImageLoaderWithType(this, 1).displayImage(iv1, urls[1], option);
        LoaderFactory.getImageLoaderWithType(this, 2).displayImage(iv2, urls[2], option);
    }

    private void initViews() {
        iv = (ImageView) findViewById(R.id.img);
        iv1 = (ImageView) findViewById(R.id.img1);
        iv2 = (ImageView) findViewById(R.id.img2);
    }
}
