package com.xiaoTools.core.randomUtil;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * [产生随机的字符串](Generate random strings)
 * @description: zh - 产生随机的字符串
 * @description: en - Generate random strings
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/4 8:58 上午
*/
public class RandomUtil {
    /**
     * [随机的基本数字。](Random basic number.)
     */
    private static final String BASE_NUMBER = "0123456789";

    /**
     * [基本的字符串](Basic string)
     */
    public static final String BASE_CHAR = "abcdefghijklmnopqrstuvwxyz";

    /**
     * [基本的小写字符串加数字](Basic lowercase string plus number)
     */
    public static final String BASE_CHAR_NUMBER_LOWERCASE = "abcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * [基本的大写字符串加数字] (Basic uppercase string plus number)
     */
    public static final String BASE_CHAR_NUMBER_CAPITALIZATION = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * [初始化方法。](Initialization method.)
     * @description: zh - 初始化方法。
     * @description: en - Initialization method.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 9:05 上午
    */
    public RandomUtil() { }

    /**
     * [随机获取SHA 1 PRNG](Sha 1 PRNG was randomly obtained)
     * @description: zh - 随机获取SHA 1 PRNG
     * @description: en - Sha 1 PRNG was randomly obtained
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 9:32 上午
     * @param seed: 产生随机的数组。
     * @return java.security.SecureRandom
    */
    public static SecureRandom getSHA1PRNGRandom(byte[] seed) {
        SecureRandom random;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException var3) {
            throw new NullPointerException(var3.getMessage());
        }
        if (null != seed) {
            random.setSeed(seed);
        }
        return random;
    }

    /**
     * [获取随机数](Get random number)
     * @description: zh - 获取随机数
     * @description: en - Get random number
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 9:46 上午
     * @param isSecure: [判断是否获取安全的随机数还是多线程的随机数](Determine whether to obtain a safe random number or a multithreaded random number)
     * @return java.util.Random
    */
    public static Random getRandom(boolean isSecure){
        return isSecure ? getSecureRandom() : getRandom();
    }

    /**
     * [获得指定范围内的随机数](Get the random number within the specified range)
     * @description: zh - 获得指定范围内的随机数
     * @description: en - Get the random number within the specified range
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 9:59 上午
     * @param origin: 最小值
     * @param bound: 最大值
     * @return java.lang.Integer
    */
    public static Integer randomInt(int origin,int bound){
        return getRandom().nextInt(origin,bound);
    }

    /**
     * [获取随机数](get random number)
     * @description: zh - 获取随机数
     * @description: en - get random number
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 10:14 上午
     * @return java.lang.Integer
    */
    public static Integer randomInt(){
        return getRandom().nextInt();
    }

    /**
     * [获取最大值内的随机数](Get the random number within the maximum value)
     * @description: zh - 获取最大值内的随机数
     * @description: en - Get the random number within the maximum value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 10:47 上午
     * @param bound: [最大值](Maximum)
     * @return java.lang.Integer
    */
    public static Integer randomInt(int bound){
        return getRandom().nextInt(bound);
    }

    /**
     * [获取随机的数字long类型的](Get the random number of long type)
     * @description: zh - 获取随机的数字long类型的
     * @description: en - Get the random number of long type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 10:56 上午
     * @return java.lang.Long
    */
    public static Long randomLong(){
        return getRandom().nextLong();
    }

    /**
     * [获取指定范围的随机值](Gets a random value in the specified range)
     * @description: zh - 获取指定范围的随机值
     * @description: en - Gets a random value in the specified range
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 11:00 上午
     * @param origin: [最小范围](Minimum range)
     * @param bound: [最大范围](Maximum range)
     * @return java.lang.Long
    */
    public static Long randomLong(long origin,long bound){
        return getRandom().nextLong(origin,bound);
    }

    /**
     * [从最大的范围内获取随机值](Get random values from the largest range)
     * @description: zh - 从最大的范围内获取随机值
     * @description: en - Get random values from the largest range
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 11:03 上午
     * @param bound: [最大范围](Maximum range)
     * @return java.lang.Long
    */
    public static Long randomLong(long bound){
        return getRandom().nextLong(bound);
    }

    /**
     * [](Get random number)
     * @description: zh - 获取随机数
     * @description: en - Get random number
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 11:14 上午
     * @return java.lang.Double
    */
    public static Double randomDouble(){
        return getRandom().nextDouble();
    }

    /**
     * [获取范围内的随机数](Get the random number in the range)
     * @description: zh - 获取范围内的随机数
     * @description: en - Get the random number in the range
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 11:15 上午
     * @param origin: 最小范围
     * @param bound: 最大范围
     * @return java.lang.Double
    */
    public static Double randomDouble(double origin,double bound){
        return getRandom().nextDouble(origin,bound);
    }

    /**
     * [在最大范围内进行随机产生](Random generation in the maximum range)
     * @description: zh - 在最大范围内进行随机产生
     * @description: en - Random generation in the maximum range
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 11:30 上午
     * @param bound: [最大范围](Maximum range)
     * @return java.lang.Double
    */
    public static Double randomDouble(double bound){
        return getRandom().nextDouble(bound);
    }

    /**
     * [根据长度获取对应数量的随机数](Get the corresponding number of random numbers according to the length)
     * @description: zh - 根据长度获取对应数量的随机数
     * @description: en - Get the corresponding number of random numbers according to the length
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 12:44 下午
     * @param length: 根据长度获取相应数量的随机数
     * @return byte[]
    */
    public static byte[] randomBytes(int length){
        byte[] bytes = new byte[length];
        getRandom().nextBytes(bytes);
        return bytes;
    }

    /**
     * [随机获得列表中的元素](Randomly get the elements in the list)
     * @description: zh - 随机获得列表中的元素
     * @description: en - Randomly get the elements in the list
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 1:24 下午
     * @param arrays: [随机获得列表中的元素的数组](Gets an array of elements in the list at random)
     * @return T
    */
    public static <T> T randomElem(T[] arrays){
        return randomElem(arrays,arrays.length);
    }

    /**
     * [获取列表中随机的元素](Get the random elements in the list)
     * @description: zh - 获取列表中随机的元素
     * @description: en - Get the random elements in the list
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 1:32 下午
     * @param arrays: [列表](list)
     * @param length: [长度](length)
     * @return T
    */
    public static <T> T randomElem(T[] arrays,int length){
        if (arrays.length < length){
            length = arrays.length;
        }
        return arrays[randomInt(length)];
    }

    /**
     * [获取列表中随机的元素](Get the random elements in the list)
     * @description: zh - 获取列表中随机的元素
     * @description: en - Get the random elements in the list
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 1:57 下午
     * @param list: [列表](list)
     * @return T
    */
    public static <T> T randomElem(List<T> list){
        return randomElem(list,list.size());
    }

    /**
     * [获取列表中随机的元素](Get the random elements in the list)
     * @description: zh - 获取列表的随机的元素
     * @description: en - Get the random elements in the list
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 3:16 下午
     * @param list: [集合列表](Set list)
     * @param length: [选择的长度](Selected length)
     * @return T
    */
    public static <T> T randomElem(List<T> list,int length){
        if (list.size() < length) {
            length = list.size();
        }
        return list.get(randomInt(length));
    }

    /**
     *
     * @description: zh - 通过集合链表获取总数的元素
     * @description: en -
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 3:33 下午
     * @param list: 集合列表
     * @param count: 随机的总数
     * @return java.util.List<T>
    */
    public static <T> List<T> randomElements(List<T> list,int count){
        List<T> result = new ArrayList<>(count);
        while (result.size() < count){
            result.add(randomElem(list));
        }
        return result;
    }

    /**
     * [产生的随机字符串加数字](Generated random string plus number)
     * @description: zh - 产生的随机字符串加数字
     * @description: en - Generated random string plus number
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 6:36 下午
     * @param length: [产生随机数字的长度](The length of the generated random number)
     * @return java.lang.String
    */
    public static String randomStringLow(int length){
        //设置产生的随机字符串加数字的长度
        StringBuilder result = new StringBuilder(length);
        //判断长度是否小于1，如果小于1，则安装长度等于1处理
        if (length < 1) { length = 1; }
        //获取父级字符串的长度
        int baseLength = BASE_CHAR_NUMBER_LOWERCASE.length();
        //进行循环
        for(int i = 0; i < length; ++i) {
            //使用方法获取随机的位置
            int number = randomInt(baseLength);
            //将该位置的字符串添加
            result.append(BASE_CHAR_NUMBER_LOWERCASE.charAt(number));
        }
        //返回字符串
        return result.toString();
    }

    /**
     * [通过随机的字符串获取大写的随机字符串](Get upper case random string by random string)
     * @description: zh - 通过随机的字符串获取大写的随机字符串
     * @description: en - Get upper case random string by random string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 7:18 下午
     * @param length: [随机字符串的长度](The length of a random string)
     * @return java.lang.String
    */
    public static String randomStringCap(int length){
        //设置产生的随机字符串加数字的长度
        StringBuilder result = new StringBuilder(length);
        //判断长度是否小于1，如果小于1，则安装长度等于1处理
        if (length < 1) { length = 1; }
        //获取父级字符串的长度
        int baseLength = BASE_CHAR_NUMBER_CAPITALIZATION.length();
        //进行循环
        for(int i = 0; i < length; ++i) {
            //使用方法获取随机的位置
            int number = randomInt(baseLength);
            //将该位置的字符串添加
            result.append(BASE_CHAR_NUMBER_CAPITALIZATION.charAt(number));
        }
        //返回字符串
        return result.toString();
    }

    /**
     * [获得一个只包含数字的字符串](Gets a string containing only numbers)
     * @description: zh - 获得一个只包含数字的字符串
     * @description: en - Gets a string containing only numbers
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 7:32 下午
     * @param length: [随机的数字长度](Random number length)
     * @return java.lang.String
    */
    public static String randomNumber(int length){
        //设置产生的随机字符串加数字的长度
        StringBuilder result = new StringBuilder(length);
        //判断长度是否小于1，如果小于1，则安装长度等于1处理
        if (length < 1) { length = 1; }
        //获取父级字符串的长度
        int baseLength = BASE_NUMBER.length();
        //进行循环
        for(int i = 0; i < length; ++i) {
            //使用方法获取随机的位置
            int number = randomInt(baseLength);
            //将该位置的字符串添加
            result.append(BASE_NUMBER.charAt(number));
        }
        //返回字符串
        return result.toString();
    }

    /**
     * [多线程环境下生产随机数。](Multithreading environment to produce random numbers.)
     * @description: zh - 多线程环境下生产随机数。
     * @description: en - Multithreading environment to produce random numbers.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 9:09 上午
     * @return java.util.concurrent.ThreadLocalRandom
     */
    private static ThreadLocalRandom getRandom(){ return ThreadLocalRandom.current(); }

    /**
     * [通过数组完成安全随机数的创建](Through the array to complete the creation of safe random number)
     * @description: zh - 通过数组完成安全随机数的创建
     * @description: en - Through the array to complete the creation of safe random number
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 9:14 上午
     * @param seed: [数组](arrays)
     * @return java.security.SecureRandom
     */
    private static SecureRandom createSecureRandom(byte[] seed) {
        return null == seed ? new SecureRandom() : new SecureRandom(seed);
    }

    /**
     * [获取安全的随机数字](Get secure random numbers)
     * @description: zh - 获取安全的随机数字
     * @description: en - Get secure random numbers
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 9:19 上午
     * @return java.security.SecureRandom
     */
    private static SecureRandom getSecureRandom(){
        return getSecureRandom(null);
    }

    /**
     * [获取安全的数组。](Gets a secure array.)
     * @description: zh - 获取安全的数组。
     * @description: en - Gets a secure array.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 9:20 上午
     * @param seed: [产生的数字的数组](An array of generated numbers)
     * @return java.security.SecureRandom
     */
    private static SecureRandom getSecureRandom(byte[] seed) {
        return createSecureRandom(seed);
    }
}
