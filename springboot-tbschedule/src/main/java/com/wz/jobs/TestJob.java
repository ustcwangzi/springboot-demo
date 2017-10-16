package com.wz.jobs;

import com.taobao.pamirs.schedule.IScheduleTaskDealMulti;
import com.taobao.pamirs.schedule.TaskItemDefine;
import com.wz.config.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * <p></p>
 *
 * @author wangzi
 */
@Component("testJob")
public class TestJob implements IScheduleTaskDealMulti<String> {
    private static final String SUFFIX_JOB = "tbschedule_";
    private static final List<String> INIT_LIST = Arrays.asList("a", "b", "c");
    @Autowired
    private JedisClient jedis;
    @Override
    public List<String> selectTasks(String s, String s1, int i, List<TaskItemDefine> list, int i1) throws Exception {
        System.out.println("开始获取任务...");
        List<String> tasks = new ArrayList<>();
        // 在获取不到任务时，执行将停止
        // 此处用Arrays.asList("a", "b", "c")来模拟从数据库取数据的情况
        // 同时在execute中将运行成功的任务放入redis
        for (String id : INIT_LIST){
            if (jedis.get(SUFFIX_JOB + id) == null){
                tasks.add(id);
            }
        }
        return tasks.size()>0? tasks : null;
    }

    @Override
    public Comparator<String> getComparator() {
        return null;
    }

    @Override
    public boolean execute(String[] strings, String s) throws Exception {
        System.out.println("正在运行任务：" + strings[0]);
        jedis.setex(SUFFIX_JOB + strings[0], strings[0], 60);
        return true;
    }
}
