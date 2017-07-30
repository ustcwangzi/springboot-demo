package com.wz.jobs;

import com.taobao.pamirs.schedule.IScheduleTaskDealMulti;
import com.taobao.pamirs.schedule.TaskItemDefine;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by wangzi on 2017-07-05.
 */
@Component("testJob")
public class TestJob implements IScheduleTaskDealMulti<String> {
    private List<String> tasks = null;
    private boolean flag = true;
    @Override
    public List<String> selectTasks(String s, String s1, int i, List<TaskItemDefine> list, int i1) throws Exception {
        System.out.println("开始获取任务...");
        //在获取不到任务时，执行将停止，此处用flag来模拟从数据库取数据的情况
        //实际情况下，可在execute中更新字段，在本方法中取数据
        if (flag){
            tasks = Arrays.asList("1", "2", "3", "4", "5");
            flag = false;
        } else {
            tasks = null;
        }
        return tasks;
    }

    @Override
    public Comparator<String> getComparator() {
        return null;
    }

    @Override
    public boolean execute(String[] strings, String s) throws Exception {
        System.out.println("正在运行任务：" + strings[0]);
        return false;
    }
}
