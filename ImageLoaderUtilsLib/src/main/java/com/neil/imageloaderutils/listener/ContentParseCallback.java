package com.neil.imageloaderutils.listener;

/**
 * 作者：Neil on 2016/8/4 10:55.
 * 邮箱：cn.neillee@gmail.com
 */

public interface ContentParseCallback <T>{
    T parseContent(String content);
}
