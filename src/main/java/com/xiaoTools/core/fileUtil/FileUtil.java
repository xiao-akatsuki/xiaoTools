package com.xiaoTools.core.fileUtil;


import java.io.File;
import java.io.IOException;

/**
 * 文件工具类
 * @author HCY
 * @since 2021/5/18 9:58 上午
*/
public class FileUtil {

    /**
     * 默认方法
     * @author HCY
     * @since 2021/5/18 9:58 上午
    */
    public FileUtil() { }

    /**
     * 通过路径查看该路径下的[文件](包括文件夹)
     * @author HCY
     * @since 2021/5/18 10:16 上午
     * @param path: 路径
     * @return java.io.File[]
    */
    public static File[] ls(String path){
        if ( path == null ||path.length() == 0 ){
            return null;
        }else {
            File file = new File(path);
            return file.listFiles();
        }
    }

    /**
     * [创建文件](true创建成功，false创建失败)
     * @author HCY
     * @since 2021/5/18 1:19 下午
     * @param file: 需要创建的文件
     * @return boolean
    */
    public static boolean touch(File file){
        if (null == file){
            return false;
        }else {
            //判断文件是否存在
            if (!file.exists()){
                mkdirParentDirs(file);
                //创建文件
                try {
                    if (!file.createNewFile()) {
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 通过完整的路径创建文件
     * @author HCY
     * @since 2021/5/18 3:32 下午
     * @param fullFilePath:完整的路径
     * @return boolean
    */
    public static boolean touch(String fullFilePath){
        return fullFilePath != null && fullFilePath.length() != 0 && touch(file(fullFilePath));
    }

    /**
     * 创建文件夹
     * @author HCY
     * @since 2021/5/18 1:36 下午
     * @param file: 目录地址
     * @return boolean
    */
    public static boolean mkdir(File file) {
        //判断文件是否为空
        if (file == null) {
            return false;
        } else {
            //判断文件是否存在
            if (!file.exists()) {
                //创建文件夹
                return file.mkdirs();
            }
            return true;
        }
    }

    /**
     * 删除文件
     * @author HCY
     * @since 2021/5/18 2:03 下午
     * @param file: 需要删除文件的路径
     * @return boolean
    */
    public static boolean del(File file){
        //判断file是否为空
        if (file != null && file.exists()){
            //判断该文件路径是否指向的是文件夹
            if (file.isDirectory()) {
                //清空该文件夹内的所有操作
                if (!clean(file)) {
                    return false;
                }
            }
            //删除文件夹
            return file.delete();
        }else {
            return true;
        }
    }

    /**
     * 清空文件夹内的文件
     * @author HCY
     * @since 2021/5/18 2:08 下午
     * @param directory: 需要清空的文件夹路径
     * @return boolean
    */
    public static boolean clean(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (null != files) {
                for (File file : files) {
                    if (!del(file)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 获取文件的绝对路径
     * @author HCY
     * @since 2021/5/18 4:49 下午
     * @param path: 文件的地址
     * @return java.lang.String
    */
    public static String getAbsolutePath(String path) {
        File file = new File(path);
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            return file.getAbsolutePath();
        }
    }

    /**
     * 创建父级目录
     * @author HCY
     * @since 2021/5/18 1:27 下午
     * @param file: 父级目录是否存在
     */
    private static void mkdirParentDirs(File file) {
        if (null != file) {
            mkdir(file.getParentFile());
        }
    }

    /**
     * 通过路径实现文件
     * @author HCY
     * @since 2021/5/18 3:39 下午
     * @param path: 文件的位置路径
     * @return java.io.File
    */
    private static File file(String path) {
        return null == path ? null : new File(getAbsolutePath(path));
    }

}
