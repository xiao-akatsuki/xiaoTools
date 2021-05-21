package com.xiaoTools.core.fileUtil;


import java.io.*;

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
        } catch (Exception e) {
            return file.getAbsolutePath();
        }
    }

    /**
     * [复制文件，将所需要复制的文件复制到目标的目录](Copy file, copy the file to the target directory)
     * @description: zh - 输入源文件的地址和目标文件的地址进行文件的复制
     * @description: en - Input the address of the source file and the address of the target file to copy the file
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/20 8:09 下午
     * @param source: 源文件的地址
     * @param target: 目标文件的地址
     * @return boolean
    */
    public static boolean copyFile(String source,String target){
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(source);
            //文件存在时
            if (oldfile.exists()) {
                //读入原文件
                InputStream inStream = new FileInputStream(source);
                FileOutputStream fs = new FileOutputStream(target);
                byte[] buffer = new byte[1444];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    //字节数 文件大小
                    bytesum += byteread;
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * [将源文件流的文件复制到目标的文件](Copy files from the source file stream to the destination file)
     * @description: zh - 输入源文件流和目标文件流，将源文件复制到目标文件处
     * @description: en - Input the source file stream and the target file stream to copy the source file to the target file
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/21 7:49 上午
     * @param resource: [源文件的文件流](The file stream of the source file)
     * @param target: [目标文件的文件流](The file stream of the target file)
     * @return boolean
    */
    public static boolean copyFile(File resource, File target){
        try {
            // 输入流 --> 从一个目标读取数据
            // 输出流 --> 向一个目标写入数据
            // 文件输入流并进行缓冲
            FileInputStream inputStream = new FileInputStream(resource);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            // 文件输出流并进行缓冲
            FileOutputStream outputStream = new FileOutputStream(target);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

            // 缓冲数组
            // 大文件 可将 1024 * 2 改大一些，但是 并不是越大就越快
            byte[] bytes = new byte[1024 * 2];
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes, 0, len);
            }
            // 刷新输出缓冲流
            bufferedOutputStream.flush();
            //关闭流
            bufferedInputStream.close();
            bufferedOutputStream.close();
            inputStream.close();
            outputStream.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * [输入源文件夹地址和目标文件夹地址，将源文件夹地址中的所有文件复制到目标地址文件，若目标地址并未指向已经存在的文件夹，则先创建文件夹，在进行复制。](Input the address of the source folder and the address of the destination folder, copy all the files in the address of the source folder to the file of the destination address. If the destination address does not point to the existing folder, create the folder first, and then copy.)
     * @description: zh - 输入源文件夹流和目标文件夹流，将源文件夹中的所有文件复制到目标文件夹中。
     * @description: en - Input the source folder stream and the destination folder stream to copy all the files in the source folder to the destination folder.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/21 7:59 上午
     * @param resource: [源文件夹地址](Source folder address)
     * @param target: [目标文件夹地址](Destination folder address)
     * @return boolean
    */
    public static boolean copyFolder(String resource, String target){
        try {
            File resourceFile = new File(resource);
            if (!resourceFile.exists()) {
                return false;
            }
            File targetFile = new File(target);
            if (!targetFile.exists()) {
                mkdir(targetFile);
            }
            // 获取源文件夹下的文件夹或文件
            File[] resourceFiles = resourceFile.listFiles();
            for (File file : resourceFiles) {
                File file1 = new File(targetFile.getAbsolutePath() + File.separator + resourceFile.getName());
                // 复制文件
                if (file.isFile()) {
                    // 在 目标文件夹（B） 中 新建 源文件夹（A），然后将文件复制到 A 中
                    // 这样 在 B 中 就存在 A
                    if (!file1.exists()) {
                        file1.mkdirs();
                    }
                    File targetFile1 = new File(file1.getAbsolutePath() + File.separator + file.getName());
                    copyFile(file, targetFile1);
                }
                // 复制文件夹
                // 复制源文件夹
                if (file.isDirectory()) {
                    String dir1 = file.getAbsolutePath();
                    // 目的文件夹
                    String dir2 = file1.getAbsolutePath();
                    copyFolder(dir1, dir2);
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * [输入源文件流和目标文件流，将源文件流中的所有文件复制到目标文件处。](Input the source file stream and the target file stream, and copy all the files in the source file stream to the target file.)
     * @description: zh - 输入源文件流，将源文件流中的所有文件复制到目标文件处。
     * @description: en - Input the source file stream and copy all the files in the source file stream to the destination file.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/21 7:40 下午
     * @param resource: [源文件夹地址](Source folder address)
     * @param target: [目标文件地址](Destination file address)
     * @return boolean
    */
    public static boolean copyFolder(File resource, File target){
        try {
            if (!resource.exists()) {
                return false;
            }
            if (!target.exists()) {
                mkdir(target);
            }
            // 获取源文件夹下的文件夹或文件
            File[] resourceFiles = resource.listFiles();
            for (File file : resourceFiles) {
                File file1 = new File(target.getAbsolutePath() + File.separator + resource.getName());
                // 复制文件
                if (file.isFile()) {
                    // 在 目标文件夹（B） 中 新建 源文件夹（A），然后将文件复制到 A 中
                    // 这样 在 B 中 就存在 A
                    if (!file1.exists()) {
                        file1.mkdirs();
                    }
                    File targetFile1 = new File(file1.getAbsolutePath() + File.separator + file.getName());
                    copyFile(file, targetFile1);
                }
                // 复制文件夹
                // 复制源文件夹
                if (file.isDirectory()) {
                    String dir1 = file.getAbsolutePath();
                    // 目的文件夹
                    String dir2 = file1.getAbsolutePath();
                    copyFolder(dir1, dir2);
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * [输入源文件的地址流将该文件的名字进行修改](Enter the address stream of the source file and modify the name of the file)
     * @description: zh - 输入源文件的地址流将该文件的名字进行修改
     * @description: en - Enter the address stream of the source file and modify the name of the file
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/21 8:24 下午
     * @param file: [需要修改名字的文件或文件夹地址](The address of the file or folder whose name needs to be changed)
     * @param newName: [修改的新名字](Modified new name)
     * @return boolean
    */
    public static boolean rename(String file,String newName){
        File f = new File(file);
        // 判断原文件是否存在（防止文件名冲突）
        if (!f.exists()) {
            return false;
        }
        newName = newName.trim();
        // 文件名不能为空
        if ("".equals(newName) || newName == null) {
            return false;
        }
        String newFilePath = null;
        // 判断是否为文件夹
        if (f.isDirectory()) {
            newFilePath = file.substring(0, file.lastIndexOf("/")) + "/" + newName;
        } else {
            newFilePath = file.substring(0, file.lastIndexOf("/")) + "/" + newName
                    + file.substring(file.lastIndexOf("."));
        }
        File nf = new File(newFilePath);
        try {
            // 修改文件名
            return f.renameTo(nf);
        } catch (Exception err) {
            err.printStackTrace();
            return false;
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
