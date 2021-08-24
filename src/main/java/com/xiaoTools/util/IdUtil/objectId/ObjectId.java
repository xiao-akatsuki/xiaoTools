package com.xiaoTools.util.IdUtil.objectId;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.randomUtil.RandomUtil;
import com.xiaoTools.util.strUtil.StrUtil;

import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * [MongoDB ID生成策略实现](Implementation of mongodb ID generation strategy)
 * @description: zh - MongoDB ID生成策略实现
 * @description: en - Implementation of mongodb ID generation strategy
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/6 8:08 下午
*/
public class ObjectId {
    /**
     * [ObjectID的初始化方法](Initialization method of objectID)
     * @description: zh - ObjectID的初始化方法
     * @description: en - Initialization method of objectID
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 8:09 下午
    */
    public ObjectId() { }

    /**
     * [线程安全的下一个随机数,每次生成自增+1](Thread safety of the next random number, each generation self incrementing + 1)
     */
    private static final AtomicInteger NEXT_INC = new AtomicInteger(RandomUtil.randomInt());

    /**
     * [获取机器信息](Get machine information)
     */
    private static final int MACHINE;

    /**
     * [初始化机器信息 = 机器码 + 进程码](Initialization machine information = machine code + process code)
     * @description: zh - 初始化机器信息 = 机器码 + 进程码
     * @description: en - Initialization machine information = machine code + process code
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 9:20 上午
    */
    static {
        try {
            // 机器码
            int machinePiece;
            try {
                StringBuilder netSb = new StringBuilder();
                // 返回机器所有的网络接口
                Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
                // 遍历网络接口
                while (e.hasMoreElements()) {
                    NetworkInterface ni = e.nextElement();
                    // 网络接口信息
                    netSb.append(ni.toString());
                }
                // 保留后两位
                machinePiece = netSb.toString().hashCode() << Constant.SIXTEEN;
            } catch (Throwable e) {
                // 出问题随机生成,保留后两位
                machinePiece = (RandomUtil.randomInt()) << Constant.SIXTEEN;
            }
            // 进程码
            // 因为静态变量类加载可能相同,所以要获取进程ID + 加载对象的ID值
            final int processPiece;
            // 进程ID初始化
            int processId = new java.util.Random().nextInt();
            try {
                // 获取进程ID
                processId = java.lang.management.ManagementFactory.getRuntimeMXBean().getName().hashCode();
            } catch (Throwable ignored) { }
            ClassLoader loader = ObjectId.class.getClassLoader();
            // 返回对象哈希码,无论是否重写hashCode方法
            int loaderId = loader != Constant.NULL ? System.identityHashCode(loader) : Constant.ZERO;
            // 进程ID + 对象加载ID
            // 保留前2位
            String processSb = Integer.toHexString(processId) +
                    Integer.toHexString(loaderId);
            processPiece = processSb.hashCode() & Constant.BYTE_THREE;
            // 生成机器信息 = 取机器码的后2位和进程码的前2位
            MACHINE = machinePiece | processPiece;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * [判断字符串是否是合格的ObjectID](Determine whether the string is a qualified ObjectID)
     * @description: zh - 判断字符串是否是合格的ObjectID
     * @description: en - Determine whether the string is a qualified ObjectID
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 9:39 上午
     * @param s: 字符串
     * @return boolean
    */
    public static boolean isValid(String s) {
        if (s == Constant.NULL) {
            return Constant.FALSE;
        }
        s = StrUtil.removeAll(s, Constant.DASH);
        final int len = s.length();
        if (len != Constant.TWENTY_FOUR) {
            return Constant.FALSE;
        }

        char c;
        for (int i = Constant.ZERO; i < len; i++) {
            c = s.charAt(i);
            if (c >= Constant.CHAR_ZERO && c <= Constant.CHAR_NINE) {
                continue;
            }
            if (c >= Constant.CHAR_DOWN_A && c <= Constant.CHAR_DOWN_F) {
                continue;
            }
            if (c >= Constant.CHAR_UP_A && c <= Constant.CHAR_UP_F) {
                continue;
            }
            return Constant.FALSE;
        }
        return Constant.TRUE;
    }

    /**
     * [产生随机的ObjectID](Generate random ObjectID)
     * @description: zh - 产生随机的ObjectID
     * @description: en - Generate random ObjectID
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 9:29 上午
     * @param withHyphen: 是否需要产生带`-`的ObjectID
     * @return java.lang.String
    */
    public static String nextId(boolean withHyphen){
        byte[] b = new byte[Constant.TWELVE];
        ByteBuffer bb = ByteBuffer.wrap(b);
        //4位
        bb.putInt((int) (System.currentTimeMillis() / Constant.ONE_THOUSAND));
        //4位
        bb.putInt(MACHINE);
        //4位
        bb.putInt(NEXT_INC.getAndIncrement());
        StringBuilder buf = new StringBuilder(Constant.TWENTY_FOUR);
        // 原来objectId格式化太慢
        byte[] array = bb.array();
        for (int i = Constant.ZERO; i < array.length; i++) {
            if (withHyphen && i % Constant.FOUR == Constant.ZERO && i != Constant.ZERO) { buf.append(Constant.DASH); }
            int t = array[i] & Constant.BYTE_TWO;
            if (t < Constant.SIXTEEN) {
                buf.append(Constant.STRING_ZERO).append(Integer.toHexString(t));
            }else {
                buf.append(Integer.toHexString(t));
            }
        }
        return buf.toString();
    }

    /**
     * [产生一个带`-`的ObjectID](Generate an ObjectID with '-')
     * @description: zh - 产生一个带`-`的ObjectID
     * @description: en - Generate an ObjectID with '-'
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 9:33 上午
     * @return java.lang.String
    */
    public static String nextId(){
        return nextId(Constant.TRUE);
    }

    /**
     * [产生一个不带`-`的ObjectID](Generate an ObjectID without '-')
     * @description: zh - 产生一个不带`-`的ObjectID
     * @description: en - Generate an ObjectID without '-'
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 9:34 上午
     * @return java.lang.String
    */
    public static String simpleNextId(){
        return nextId(Constant.FALSE);
    }

}
