package com.xiaoTools.core.IdUtil.uuid;

import com.xiaoTools.core.randomUtil.RandomUtil;
import com.xiaoTools.core.strUtil.StrUtil;

import java.io.Serial;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * [重写创建UUID的生成策略](Override the build policy for creating UUIDs)
 * @description: zh - 重写创建UUID的生成策略
 * @description: en - Override the build policy for creating UUIDs
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/3 4:01 下午
*/
public class UUID implements Serializable {

    @Serial
    private static final long serialVersionUID = -1185015143654744140L;

    /**
     * [最大信号位](Maximum signal bit)
     */
    private final long mostSigBits;
    /**
     * [最小信号位](Minimum signal bit)
     */
    private final long leastSigBits;

    /**
     * [产生的UUID有参方法。](The generated UUID is a parametric method.)
     * @description: zh - 产生的UUID有参方法。
     * @description: en - The generated UUID is a parametric method.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 8:43 上午
     * @param mostSigBits: [最大信号位](most Sig Bits)
     * @param leastSigBits: [最小信号位](least Sig Bits)
    */
    public UUID(long mostSigBits, long leastSigBits) {
        this.mostSigBits = mostSigBits;
        this.leastSigBits = leastSigBits;
    }

    /**
     * [UUID的初始化创建方法](Initialization method of UUID)
     * @description: zh - UUID的初始化创建方法
     * @description: en - Initialization method of UUID
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 11:15 上午
     * @param data: [需要创建的UUID的数据](Data of UUID to be created)
    */
    private UUID(byte[] data){
        long msb = 0L;
        long lsb = 0L;
        assert data.length == 16 : "data must be 16 bytes in length";
        int i;
        for(i = 0; i < 8; ++i) { msb = msb << 8 | (long)(data[i] & 255); }
        for(i = 8; i < 16; ++i) { lsb = lsb << 8 | (long)(data[i] & 255); }
        this.mostSigBits = msb;
        this.leastSigBits = lsb;
    }

    /**
     * [随机产生UUID，并且可以通过参数进行获取是否安全的UUID](UUID is generated randomly, and whether it is safe or not can be obtained through parameters)
     * @description: zh - 随机产生UUID，并且可以通过参数进行获取是否安全的UUID
     * @description: en - UUID is generated randomly, and whether it is safe or not can be obtained through parameters
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 11:22 上午
     * @param isSecure: [产生是否安全的UUID](Generate a UUID that is safe or not)
     * @return com.xiaoTools.core.IdUtil.uuid.UUID
    */
    private static UUID randomUUID(boolean isSecure){
        Random ng = isSecure ? UUID.Holder.NUMBER_GENERATOR : RandomUtil.getRandom();
        byte[] randomBytes = new byte[16];
        ng.nextBytes(randomBytes);
        randomBytes[6] = (byte)(randomBytes[6] & 15);
        randomBytes[6] = (byte)(randomBytes[6] | 64);
        randomBytes[8] = (byte)(randomBytes[8] & 63);
        randomBytes[8] = (byte)(randomBytes[8] | 128);
        return new UUID(randomBytes);
    }

    /**
     * [产生快速的UUID](Generate fast UUID)
     * @description: zh - 产生快速的UUID
     * @description: en - Generate fast UUID
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 11:28 上午
     * @return com.xiaoTools.core.IdUtil.uuid.UUID
    */
    public static UUID fastUUID(){
        return randomUUID(false);
    }

    /**
     * [产生安全的UUID](Generate a secure UUID)
     * @description: zh - 产生安全的UUID
     * @description: en - Generate a secure UUID
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 11:29 上午
     * @return com.xiaoTools.core.IdUtil.uuid.UUID
    */
    public static UUID randomUUID(){
        return randomUUID(true);
    }

    /**
     * [通过字节依据产生UUID](Generating UUID by byte)
     * @description: zh - 通过字节依据产生UUID
     * @description: en - Generating UUID by byte
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 1:02 下午
     * @param name: [产生UUID的依据](Basis of UUID generation)
     * @return com.xiaoTools.core.IdUtil.uuid.UUID
    */
    public static UUID nameUUIDFromBytes(byte[] name) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var3) {
            throw new InternalError("MD5 not supported");
        }
        byte[] md5Bytes = md.digest(name);
        md5Bytes[6] = (byte)(md5Bytes[6] & 15);
        md5Bytes[6] = (byte)(md5Bytes[6] | 48);
        md5Bytes[8] = (byte)(md5Bytes[8] & 63);
        md5Bytes[8] = (byte)(md5Bytes[8] | 128);
        return new UUID(md5Bytes);
    }

    /**
     * [根据所提交的值产生UUID](Generate UUID based on submitted value)
     * @description: zh - 根据所提交的值产生UUID
     * @description: en - Generate UUID based on submitted value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 1:14 下午
     * @param value: [根据该数据产生UUID](The UUID is generated according to the data)
     * @return com.xiaoTools.core.IdUtil.uuid.UUID
    */
    public static UUID fromString(String value){
        String[] components = value.split("-");
        if (components.length != 5) {
            throw new IllegalArgumentException("Invalid UUID string: " + value);
        } else {
            for(int i = 0; i < 5; ++i) { components[i] = "0x" + components[i]; }
            long mostSigBits = Long.decode(components[0]);
            mostSigBits <<= 16;
            mostSigBits |= Long.decode(components[1]);
            mostSigBits <<= 16;
            mostSigBits |= Long.decode(components[2]);
            long leastSigBits = Long.decode(components[3]);
            leastSigBits <<= 48;
            leastSigBits |= Long.decode(components[4]);
            return new UUID(mostSigBits, leastSigBits);
        }
    }

    /**
     * [获取最低有效位](Get the least significant bit)
     * @description: zh - 获取最低有效位
     * @description: en - Get the least significant bit
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 1:19 下午
     * @return long
    */
    public long getLeastSignificantBits() {
        return this.leastSigBits;
    }

    /**
     * [获取最高有效位](Get the most significant bit)
     * @description: zh - 获取最高有效位
     * @description: en - Get the most significant bit
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 1:20 下午
     * @return long
    */
    public long getMostSignificantBits() {
        return this.mostSigBits;
    }

    /**
     * [获取版本位](Get version bit)
     * @description: zh - 获取版本位
     * @description: en - Get version bit
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 1:20 下午
     * @return int
    */
    public int version() {
        return (int)(this.mostSigBits >> 12 & 15L);
    }

    /**
     * [获取实体](Get entity)
     * @description: zh - 获取实体
     * @description: en - Get entity
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 1:36 下午
     * @return int
    */
    public int variant() {
        return (int)(this.leastSigBits >>> (int)(64L - (this.leastSigBits >>> 62)) & this.leastSigBits >> 63);
    }

    /**
     * [获取时间戳](Get timestamp)
     * @description: zh - 获取时间戳
     * @description: en - Get timestamp
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 2:00 下午
     * @return long
    */
    public long timestamp() throws UnsupportedOperationException {
        this.checkTimeBase();
        return (this.mostSigBits & 4095L) << 48 | (this.mostSigBits >> 16 & 65535L) << 32 | this.mostSigBits >>> 32;
    }

    /**
     * [获取时钟序列](Get clock sequence)
     * @description: zh - 获取时钟序列
     * @description: en - Get clock sequence
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 2:00 下午
     * @return int
    */
    public int clockSequence() throws UnsupportedOperationException {
        this.checkTimeBase();
        return (int)((this.leastSigBits & 4611404543450677248L) >>> 48);
    }

    /**
     * [获取节点](Get node)
     * @description: zh - 获取节点
     * @description: en - Get node
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 2:26 下午
     * @return long
    */
    public long node() throws UnsupportedOperationException {
        this.checkTimeBase();
        return this.leastSigBits & 281474976710655L;
    }

    /**
     * [检查时基](Check time base)
     * @description: zh - 检查时基
     * @description: en - Check time base
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 2:19 下午
    */
    private void checkTimeBase() {
        if (this.version() != 1) {
            throw new UnsupportedOperationException("Not a time-based UUID");
        }
    }

    /**
     * [返回此UUID 的字符串表现形式](Returns the string representation of this UUID)
     * @description: zh - 返回此UUID 的字符串表现形式
     * @description: en - Returns the string representation of this UUID
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 3:20 下午
     * @return java.lang.String
    */
    @Override
    public String toString() {
        return toString(false);
    }

    /**
     * [输出](output)
     * @description: zh - 输出
     * @description: en - output
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 3:13 下午
     * @param isSimple: [判断是否需要加上短杠](Judge whether short bars are needed)
     * @return java.lang.String
    */
    public String toString(boolean isSimple) {
        StringBuilder builder = StrUtil.builder(isSimple ? 32 : 36);
        builder.append(digits(this.mostSigBits >> 32, 8));
        if (!isSimple) { builder.append('-'); }
        builder.append(digits(this.mostSigBits >> 16, 4));
        if (!isSimple) { builder.append('-'); }
        builder.append(digits(this.mostSigBits, 4));
        if (!isSimple) { builder.append('-'); }
        builder.append(digits(this.leastSigBits >> 48, 4));
        if (!isSimple) { builder.append('-'); }
        builder.append(digits(this.leastSigBits, 12));
        return builder.toString();
    }

    /**
     * [获取数字](Get digits)
     * @description: zh - 获取数字
     * @description: en - Get digits
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 2:26 下午
     * @param val: [判断值](Judgment value)
     * @param digits: [数字](number)
     * @return java.lang.String
    */
    private static String digits(long val, int digits) {
        long hi = 1L << digits * 4;
        return Long.toHexString(hi | val & hi - 1L).substring(1);
    }

    /**
     * [重写hashCode](Rewrite hashcode)
     * @description: zh - 重写hashCode
     * @description: en - Rewrite hashcode
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 2:30 下午
     * @return int
    */
    @Override
    public int hashCode() {
        long hilo = this.mostSigBits ^ this.leastSigBits;
        return (int)(hilo >> 32) ^ (int)hilo;
    }

    /**
     * [重写equals方法](Override the equals method)
     * @description: zh - 重写equals方法
     * @description: en - Override the equals method
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 2:31 下午
     * @param obj: 和UUID判断的值
     * @return boolean
    */
    @Override
    public boolean equals(Object obj) {
        if (null != obj && obj.getClass() == UUID.class) {
            UUID id = (UUID)obj;
            return this.mostSigBits == id.mostSigBits && this.leastSigBits == id.leastSigBits;
        } else {
            return false;
        }
    }

    /**
     * [比较UUID](Compare UUID)
     * @description: zh - 比较UUID
     * @description: en - Compare UUID
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 2:32 下午
     * @param val: [比较的UUID](UUID of comparison)
     * @return int
    */
    public int compareTo(UUID val) {
        int compare = Long.compare(this.mostSigBits, val.mostSigBits);
        if (0 == compare) {
            compare = Long.compare(this.leastSigBits, val.leastSigBits);
        }
        return compare;
    }

    /**
     * [持有人](holder)
     * @description: zh - 持有人
     * @description: en - holder
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 11:23 上午
    */
    private static class Holder {
        static final SecureRandom NUMBER_GENERATOR = RandomUtil.getSecureRandom();
        private Holder() { }
    }
}
