# ImageLoaderUtils

Android Library which wraps the loader of images or content(such as String) helps distinct the loader of network request frameworks with the specific business logic.

With this lib, you can easily replace some codes while don't affect disconnected codes as possible.Besides, this lib can make your image-loading codes more well-organized and highly-readable.

##  Features

- load images from the internet or disk files
- download images from the internet to the disk
- loading content(such as content)

## Usages

### load image

`
    // get the loader
    IImageLoaderWrapper imageLoader = LoaderFactory.getImageLoader();

    // download image
    imageLoader.downloadImage(context, imageUrl, imagePath, imageName, loadingListener);

    //display image
    imageLoader.displayImage(imageView, imageFile, option);
    imageLoader.displayImage(imageView, imageUrl, option);
    imageLoader.displayImage(imageView, imageUrl, option, loadingListener);
    imageLoader.displayImage(imageView, imageUrl, option, loadingListener, progressListener);
`

### load content(for example String type)

`
    codes
`

more info ---> the example module

## Downloads

In this lib, some standard implements are available.They can satisfy most basic needs.Just download the jar.

[image-loader-utils-v1.0.0.jar](...)

If you need more customisation, just copy the lib[imageloaderutils] to your projects and do what you want.

## Statement

The idea of this lib is from a blog article.Because time passed and I have no records of the article,so I am so sorry that I cannot give the references of the blog.

Thanks to the blog owner.If someone has some info, just contact me[e-mail:cn.neillee@gmail.com].

Many thanks to you all!