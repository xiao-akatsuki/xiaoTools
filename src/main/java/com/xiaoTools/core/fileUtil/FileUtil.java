package com.xiaoTools.core.fileUtil;


import java.io.File;
import java.io.IOException;

/**
 * [文件工具类](File tools)
 * @author XiaoXunYao
 * @since 2021/5/18 9:58 上午
*/
public class FileUtil {

    /**
     * [初始化方法](Initialization method)
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/18 9:58 上午
    */
    public FileUtil() { }

    /**
     * [通过路径查看该路径下的[文件](包括文件夹)](View [file] (including folder) under the path through the path)
     * @description: zh - 会获取该路径下的所有文件包括文件夹。
     * @description: en - Will get all the files under the path, including the folder.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/18 10:16 上午
     * @param path: [路径](route)
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
     * [[创建文件](true创建成功，false创建失败)]([create file] (true created successfully, false created failed))
     * @description: zh - 输入一个文件流，将该文件进行创建操作，若没有父级目录，则先创建父级目录
     * @description: en - Enter a file stream and create the file. If there is no parent directory, create the parent directory first
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/18 1:19 下午
     * @param file: [需要创建的文件](Files to be created)
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
     * [通过完整的路径创建文件](Create the file through the full path)
     * @description: zh - 通过完整的路径创建文件，如果没有父级目录则会创建父级目录
     * @description: en - Create the file through the full path. If there is no parent directory, the parent directory will be created
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/18 3:32 下午
     * @param fullFilePath:[完整的路径](Complete path)
     * @return boolean
    */
    public static boolean touch(String fullFilePath){
        return fullFilePath != null && fullFilePath.length() != 0 && touch(file(fullFilePath));
    }

    /**
     * [创建文件夹](create folder)
     * @description: zh - 在该文件流的位置下创建一个文件夹
     * @description: en - Create a folder under the location of the file stream
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/18 1:36 下午
     * @param file: [目录地址](Directory address)
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
     * [删除文件](Delete file)
     * @description: zh - 通过删除文件的路径，进行删除操作
     * @description: en - Delete by deleting the path of the file
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/18 2:03 下午
     * @param file: [需要删除文件的路径](The path of the file needs to be deleted)
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
     * [清空文件夹内的文件](Empty files in folder)
     * @description: zh - 输入文件夹的地址，将会清空文件夹内的所有文件
     * @description: en - Entering the address of the folder will empty all the files in the folder
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/18 2:08 下午
     * @param directory: [需要清空的文件夹路径](Folder path to be cleared)
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
     * [获取文件的绝对路径](Get the absolute path of the file)
     * @description: zh - 通过文件的相对路径获取到该文件的绝对路径
     * @description: en - Get the absolute path of the file through the relative path of the file
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/18 4:49 下午
     * @param path: [文件的地址](The address of the file)
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
     * [创建父级目录](Create parent directory)
     * @description: zh - 将路径上的文件夹进行创建操作
     * @description: en - Create the folder on the path
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/18 1:27 下午
     * @param file: [父级目录是否存在](Does the parent directory exist)
     */
    private static void mkdirParentDirs(File file) {
        if (null != file) {
            mkdir(file.getParentFile());
        }
    }

    /**
     * [通过路径实现文件](Implement file by path)
     * @description: zh - 通过文件路径创建文件流
     * @description: en - Creating a file stream through a file path
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/18 3:39 下午
     * @param path: [文件的位置路径](The location path of the file)
     * @return java.io.File
    */
    private static File file(String path) {
        return null == path ? null : new File(getAbsolutePath(path));
    }

}
