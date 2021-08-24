package com.xiaoTools.date.timer.stopWatch;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.dateUtil.DateUtil;
import com.xiaoTools.util.fileUtil.fileUtil.FileUtil;
import com.xiaoTools.util.strUtil.StrUtil;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * [秒表封装](Stopwatch package)
 * @description: zh - 秒表封装
 * @description: en - Stopwatch package
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/23 5:11 下午
*/
public class StopWatch {
    public static StopWatch create(String id){
        return new StopWatch(id);
    }

    private final String id;
    private List<TaskInfo> taskList;

    /**
     * 存放任务名称和花费时间对象
     */
    public static final class TaskInfo {

        private final String taskName;
        private final long timeNanos;

        TaskInfo(String taskName, long timeNanos) {
            this.taskName = taskName;
            this.timeNanos = timeNanos;
        }

        /**
         * 获取任务名
         *
         * @return 任务名
         */
        public String getTaskName() {
            return this.taskName;
        }

        /**
         * 获取任务花费时间（单位：纳秒）
         *
         * @return 任务花费时间（单位：纳秒）
         * @see #getTimeMillis()
         * @see #getTimeSeconds()
         */
        public long getTimeNanos() {
            return this.timeNanos;
        }

        /**
         * 获取任务花费时间（单位：毫秒）
         *
         * @return 任务花费时间（单位：毫秒）
         * @see #getTimeNanos()
         * @see #getTimeSeconds()
         */
        public long getTimeMillis() {
            return DateUtil.nanosToMillis(this.timeNanos);
        }

        /**
         * 获取任务花费时间（单位：秒）
         *
         * @return 任务花费时间（单位：秒）
         * @see #getTimeMillis()
         * @see #getTimeNanos()
         */
        public double getTimeSeconds() {
            return DateUtil.nanosToSeconds(this.timeNanos);
        }
    }

    /**
     * 任务名称
     */
    private String currentTaskName;
    /**
     * 开始时间
     */
    private long startTimeNanos;

    /**
     * 最后一次任务对象
     */
    private TaskInfo lastTaskInfo;
    /**
     * 总任务数
     */
    private int taskCount;
    /**
     * 总运行时间
     */
    private long totalTimeNanos;

    /**
     * [构造，不启动任何任务](Construct, do not start any tasks)
     * @description: zh - 构造，不启动任何任务
     * @description: en - Construct, do not start any tasks
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 5:26 下午
    */
    public StopWatch() {
        this(Constant.EMPTY);
    }

    /**
     * 构造，不启动任何任务
     *
     * @param id 用于标识秒表的唯一ID
     */
    public StopWatch(String id) {
        this(id, Constant.TRUE);
    }

    /**
     * 构造，不启动任何任务
     *
     * @param id           用于标识秒表的唯一ID
     * @param keepTaskList 是否在停止后保留任务，{@code false} 表示停止运行后不保留任务
     */
    public StopWatch(String id, boolean keepTaskList) {
        this.id = id;
        if (keepTaskList) {
            this.taskList = new ArrayList<>();
        }
    }

    /**
     * [获取 StopWatch 的ID，用于多个秒表对象的区分](Gets the ID of StopWatch, which is used to distinguish multiple stopwatch objects)
     * @description: zh - 获取 StopWatch 的ID，用于多个秒表对象的区分
     * @description: en - Gets the ID of StopWatch, which is used to distinguish multiple stopwatch objects
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 5:30 下午
     * @return java.lang.String
    */
    public String getId() {
        return this.id;
    }

    /**
     * [设置是否在停止后保留任务，false 表示停止运行后不保留任务](Set whether to keep the task after stopping. False means not to keep the task after stopping)
     * @description: zh - 设置是否在停止后保留任务，false 表示停止运行后不保留任务
     * @description: en - Set whether to keep the task after stopping. False means not to keep the task after stopping
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 5:38 下午
     * @param keepTaskList: 是否在停止后保留任务
    */
    public void setKeepTaskList(boolean keepTaskList) {
        if (keepTaskList) {
            if (Constant.NULL == this.taskList) {
                this.taskList = new ArrayList<>();
            }
        } else {
            this.taskList = null;
        }
    }

    /**
     * [开始默认的新任务](Start default new task)
     * @description: zh - 开始默认的新任务
     * @description: en - Start default new task
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 6:00 下午
    */
    public void start() throws IllegalStateException {
        start(Constant.EMPTY);
    }

    /**
     * [开始指定名称的新任务](Starts a new task with the specified name)
     * @description: zh - 开始指定名称的新任务
     * @description: en - Starts a new task with the specified name
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 6:07 下午
     * @param taskName: 新开始的任务名称
    */
    public void start(String taskName) throws IllegalStateException {
        if (Constant.NULL != this.currentTaskName) {
            throw new IllegalStateException("Can't start StopWatch: it's already running");
        }
        this.currentTaskName = taskName;
        this.startTimeNanos = System.nanoTime();
    }

    /**
     * [停止当前任务](Stop current task)
     * @description: zh - 停止当前任务
     * @description: en - Stop current task
     * @version: V1.0
     * @author XiaoXunYao
     * @throws IllegalStateException : 任务没有开始
     * @since 2021/6/23 7:33 下午
    */
    public void stop() throws IllegalStateException {
        if (Constant.NULL == this.currentTaskName) {
            throw new IllegalStateException("Can't stop StopWatch: it's not running");
        }
        final long lastTime = System.nanoTime() - this.startTimeNanos;
        this.totalTimeNanos += lastTime;
        this.lastTaskInfo = new TaskInfo(this.currentTaskName, lastTime);
        if (Constant.NULL != this.taskList) {
            this.taskList.add(this.lastTaskInfo);
        }
        ++this.taskCount;
        this.currentTaskName = null;
    }

    /**
     * [检查是否有正在运行的任务](Check for running tasks)
     * @description: zh - 检查是否有正在运行的任务
     * @description: en - Check for running tasks
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 7:33 下午
     * @return boolean
    */
    public boolean isRunning() {
        return (this.currentTaskName != Constant.NULL);
    }

    /**
     * [获取当前任务名，null 表示无任务](Get the current task name. Null means no task)
     * @description: zh - 获取当前任务名，null 表示无任务
     * @description: en - Get the current task name. Null means no task
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 7:36 下午
     * @return java.lang.String
    */
    public String currentTaskName() {
        return this.currentTaskName;
    }

    /**
     * [获取最后任务的花费时间（纳秒）](Time spent getting the last task (nanoseconds))
     * @description: zh - 获取最后任务的花费时间（纳秒）
     * @description: en - Time spent getting the last task (nanoseconds)
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 7:37 下午
     * @return long
    */
    public long getLastTaskTimeNanos() throws IllegalStateException {
        if (this.lastTaskInfo == Constant.NULL) {
            throw new IllegalStateException("No tasks run: can't get last task interval");
        }
        return this.lastTaskInfo.getTimeNanos();
    }

    /**
     * [获取最后的任务名](Get the last task name)
     * @description: zh - 获取最后的任务名
     * @description: en - Get the last task name
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 7:38 下午
     * @return long
    */
    public long getLastTaskTimeMillis() throws IllegalStateException {
        if (this.lastTaskInfo == Constant.NULL) {
            throw new IllegalStateException("No tasks run: can't get last task interval");
        }
        return this.lastTaskInfo.getTimeMillis();
    }

    /**
     * [获取最后的任务对象](Get the last task object)
     * @description: zh - 获取最后的任务对象
     * @description: en - Get the last task object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 7:38 下午
     * @return com.xiaoTools.date.timer.stopWatch.StopWatch.TaskInfo
    */
    public TaskInfo getLastTaskInfo() throws IllegalStateException {
        if (this.lastTaskInfo == Constant.NULL) {
            throw new IllegalStateException("No tasks run: can't get last task info");
        }
        return this.lastTaskInfo;
    }

    /**
     * [获取所有任务的总花费时间（纳秒）](Get the total time spent on all tasks (nanoseconds))
     * @description: zh - 获取所有任务的总花费时间（纳秒）
     * @description: en - Get the total time spent on all tasks (nanoseconds)
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 7:40 下午
     * @return long
    */
    public long getTotalTimeNanos() {
        return this.totalTimeNanos;
    }

    /**
     * [获取所有任务的总花费时间（毫秒）](Total time taken to get all tasks (MS))
     * @description: zh - 获取所有任务的总花费时间（毫秒）
     * @description: en - Total time taken to get all tasks (MS)
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 7:42 下午
     * @return long
    */
    public long getTotalTimeMillis() {
        return DateUtil.nanosToMillis(this.totalTimeNanos);
    }

    /**
     * [获取所有任务的总花费时间（秒）](Total time spent getting all tasks (seconds))
     * @description: zh - 获取所有任务的总花费时间（秒）
     * @description: en - Total time spent getting all tasks (seconds)
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 7:43 下午
     * @return double
    */
    public double getTotalTimeSeconds() {
        return DateUtil.nanosToSeconds(this.totalTimeNanos);
    }

    /**
     * [获取任务数](Get the number of tasks)
     * @description: zh - 获取任务数
     * @description: en - Get the number of tasks
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 7:43 下午
     * @return int
    */
    public int getTaskCount() {
        return this.taskCount;
    }

    /**
     * [获取任务列表](Get task list)
     * @description: zh - 获取任务列表
     * @description: en - Get task list
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 7:44 下午
     * @return com.xiaoTools.date.timer.stopWatch.StopWatch.TaskInfo[]
    */
    public TaskInfo[] getTaskInfo() {
        if (Constant.NULL == this.taskList) {
            throw new UnsupportedOperationException("Task info is not being kept!");
        }
        return this.taskList.toArray(new TaskInfo[Constant.ZERO]);
    }

    /**
     * [获取任务信息](Get task information)
     * @description: zh - 获取任务信息
     * @description: en - Get task information
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 7:49 下午
     * @return java.lang.String
    */
    public String shortSummary() {
        return StrUtil.format("StopWatch '{}': running time = {} ns", this.id, this.totalTimeNanos);
    }

    public String prettyPrint() {
        StringBuilder sb = new StringBuilder(shortSummary());
        sb.append(FileUtil.getLineSeparator());
        if (Constant.NULL == this.taskList) {
            sb.append("No task info kept");
        } else {
            sb.append("---------------------------------------------").append(FileUtil.getLineSeparator());
            sb.append("ns         %     Task name").append(FileUtil.getLineSeparator());
            sb.append("---------------------------------------------").append(FileUtil.getLineSeparator());

            final NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMinimumIntegerDigits(Constant.NINE);
            nf.setGroupingUsed(Constant.FALSE);

            final NumberFormat pf = NumberFormat.getPercentInstance();
            pf.setMinimumIntegerDigits(Constant.THREE);
            pf.setGroupingUsed(Constant.FALSE);
            for (TaskInfo task : getTaskInfo()) {
                sb.append(nf.format(task.getTimeNanos())).append(Constant.STRING_BIG_SPACE);
                sb.append(pf.format((double) task.getTimeNanos() / getTotalTimeNanos())).append(Constant.STRING_BIG_SPACE);
                sb.append(task.getTaskName()).append(FileUtil.getLineSeparator());
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(shortSummary());
        if (Constant.NULL != this.taskList) {
            for (TaskInfo task : this.taskList) {
                sb.append("; [").append(task.getTaskName()).append("] took ").append(task.getTimeNanos()).append(" ns");
                long percent = Math.round(Constant.DOUBLE_HUNDRED * task.getTimeNanos() / getTotalTimeNanos());
                sb.append(" = ").append(percent).append(Constant.PERCENT_SIGN);
            }
        } else {
            sb.append("; no task info kept");
        }
        return sb.toString();
    }
}
