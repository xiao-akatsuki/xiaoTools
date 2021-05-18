package com.hcy.example;

import com.hcy.code.filUtil.FileUtil;

import java.io.File;

/**
 * 实现FileUtil的使用模板
 * @author HCY
 * @since 2021/5/18 1:50 下午
*/
public class FileUtilExample {
    public static void main(String[] args) {
        String path = "/Users/huchengye/Desktop/博客文章/one.md";
        System.out.println(FileUtil.touch(path));
    }
}
