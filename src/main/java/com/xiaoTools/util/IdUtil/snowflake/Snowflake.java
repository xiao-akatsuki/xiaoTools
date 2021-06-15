package com.xiaoTools.util.IdUtil.snowflake;

import com.xiaoTools.lang.constant.Constant;

import java.io.Serial;
import java.io.Serializable;

/**
 * [雪花ID算法](Snowflake ID algorithm)
 * @description: zh - 雪花ID算法
 * @description: en - Snowflake ID algorithm
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/6 8:51 下午
*/
public class Snowflake implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

//    ----------------------------------------------- 每一部分占用的位数

    /**
     * 序列号占用的位数
     */
    private final static long SEQUENCE_BIT = 12;

    /**
     * 机器标识占用的位数
     */
    private final static long MACHINE_BIT = 5;

    /**
     * 数据中心占用的位数
     */
    private final static long DATA_CENTER_BIT = 5;

//    ----------------------------------------------- 每一部分的最大值

    /**
     * 最大序列
     */
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    /**
     * 最大机器数
     */
    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);

    /**
     * 最大数据中心数量
     */
    private final static long MAX_DATA_CENTER_NUM = ~(-1L << DATA_CENTER_BIT);

//    ----------------------------------------------- 每一部分向左的位移

    /**
     * 机器左侧
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;

    /**
     * 数据中心左侧
     */
    private final static long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;

    /**
     * 左时间戳
     */
    private final static long TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;

    /**
     * 数据中心
     */
    private long dataCenterId;

    /**
     * 机器标识
     */
    private long machineId;

    /**
     * 序列号
     */
    private long sequence = 0L;

    /**
     * 上一次时间戳
     */
    private long lastTimeStamp = -1L;

    /**
     *
     * @description: zh - 根据指定的数据中心ID和机器标志ID生成指定的序列号
     * @description: en - Generates the specified serial number based on the specified data center ID and machine logo ID
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 7:40 上午
     * @param dataCenterId: [数据中心ID](Data center ID)
     * @param machineId: [机器标志ID](Machine logo ID)
    */
    public Snowflake(long dataCenterId, long machineId) {
        if (dataCenterId > MAX_DATA_CENTER_NUM || dataCenterId < 0) { throw new IllegalArgumentException("DtaCenterId can't be greater than MAX_DATA_CENTER_NUM or less than 0！"); }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) { throw new IllegalArgumentException("MachineId can't be greater than MAX_MACHINE_NUM or less than 0！"); }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    /**
     * [通过雪花算法计算出机器序号](The machine serial number is calculated by snowflake algorithm)
     * @description: zh - 通过雪花算法计算出机器序号
     * @description: en - The machine serial number is calculated by snowflake algorithm
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 8:43 上午
     * @param id: [雪花算法计算出来的序号](The serial number calculated by snowflake algorithm)
     * @return long
    */
    public long getWorkerId(long id) { return id >> MACHINE_LEFT & ~(-1L << DATA_CENTER_BIT); }

    /**
     * [根据Snowflake的ID，获取数据中心id](According to the ID of snowflake, get the data center ID)
     * @description: zh - 根据Snowflake的ID，获取数据中心id
     * @description: en - According to the ID of snowflake, get the data center ID
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 8:52 上午
     * @param id: [雪花算法计算出来的序号](The serial number calculated by snowflake algorithm)
     * @return long
    */
    public long getDataCenterId(long id) { return id >> DATA_CENTER_LEFT & ~(-1L << DATA_CENTER_BIT); }

    /**
     * [根据Snowflake的ID，获取生成时间](According to the ID of snowflake, get the generation time)
     * @description: zh - 根据Snowflake的ID，获取生成时间
     * @description: en - According to the ID of snowflake, get the generation time
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 8:55 上午
     * @param id: [雪花算法计算出来的序号](The serial number calculated by snowflake algorithm)
     * @return long
    */
    public long getGenerateDateTime(long id) {
        return (id >> TIMESTAMP_LEFT & ~(-1L << 41L)) + Constant.START_TIMESTAMP;
    }

    /**
     * [产生下一个ID](Generate next ID)
     * @description: zh - 产生下一个ID
     * @description: en - Generate next ID
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 7:40 上午
     * @return long
    */
    public synchronized long nextId() {
        long currTimeStamp = getNewTimeStamp();
        if (currTimeStamp < lastTimeStamp) { throw new RuntimeException("Clock moved backwards.  Refusing to generate id"); }
        if (currTimeStamp == lastTimeStamp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) { currTimeStamp = getNextMill(); }
        } else {/*不同毫秒内，序列号置为0*/sequence = 0L; }
        lastTimeStamp = currTimeStamp;
        return
                //时间戳部分
                (currTimeStamp - Constant.START_TIMESTAMP) << TIMESTAMP_LEFT
                //数据中心部分
                | dataCenterId << DATA_CENTER_LEFT
                //机器标识部分
                | machineId << MACHINE_LEFT
                //序列号部分
                | sequence;
    }

    /**
     * [产生String类型的id](Generate ID of string type)
     * @description: zh - 产生String类型的id
     * @description: en - Generate ID of string type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 7:35 上午
     * @return java.lang.String
    */
    public String idString(){ return Long.toString(nextId()); }

    /**
     * [产生Long类型的id](Generate ID of long type)
     * @description: zh - 产生Long类型的id
     * @description: en - Generate ID of long type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 7:35 上午
     * @return java.lang.Long
    */
    public Long idLong(){ return nextId(); }

    /**
     * [获取下一个频率](Get the next frequency)
     * @description: zh - 获取下一个频率
     * @description: en - Get the next frequency
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 7:45 上午
     * @return long
     */
    private long getNextMill() {
        long mill = getNewTimeStamp();
        while (mill <= lastTimeStamp) {
            mill = getNewTimeStamp();
        }
        return mill;
    }

    /**
     * [获取新的时间戳](Get a new timestamp)
     * @description: zh - 获取新的时间戳
     * @description: en - Get a new timestamp
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 7:41 上午
     * @return long
     */
    private long getNewTimeStamp() {
        return System.currentTimeMillis();
    }
}
