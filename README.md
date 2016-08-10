# ImageLoaderUtils

[English README.md](https://github.com/neilleecn/NetworkLoaderUtils/blob/master/README-en.md)

一个封装了图片加载/内容加载（例如字符串）的android库，它将网络请求框架与业务逻辑区分开。

借助这个库，使用者能够尽可能轻松地进行代码迭代而不会影响到其他一些不相干的代码，同时能够使得图片/内容加载部分的代码更加整齐且更具有可读性。

##  功能

- 从互联网/磁盘本地加载图片
- 下载图片
- 加载内容（如字符串）

## 使用

### 图片加载

`
    // 获取loader
    IImageLoaderWrapper imageLoader = LoaderFactory.getImageLoader();

    // 图片下载
    imageLoader.downloadImage(context, imageUrl, imagePath, imageName, loadingListener);

    // 图片显示
    imageLoader.displayImage(imageView, imageFile, option);
    imageLoader.displayImage(imageView, imageUrl, option);
    imageLoader.displayImage(imageView, imageUrl, option, loadingListener);
    imageLoader.displayImage(imageView, imageUrl, option, loadingListener, progressListener);
`

### 内容加载（以字符串为例）

`
    codes
`

更多细节请参照源代码

## 下载

库中已经提供了一些常用的实现类，这些能够满足大部分的基本的图片/内容加载需求。

下载jar包：(暂无)
[image-loader-utils-v1.0.0.jar](...)

使用者如果希望自定义（自选）网络请求框架，那么可以将lib[imageloaderutils]拷贝到自己的工程中，然后做你想做的！


## 声明

本项目的思路是参照了一篇博客的内容，其中有部分代码来自这位博主。由于时间较久，且未记录该博文/博客地址，因此不能提供相关参考。

在这里，对这位博主表示歉意，如果有读者看到相关文章，请与本人联系。
e-mail: cn.neillee@gmail.com
