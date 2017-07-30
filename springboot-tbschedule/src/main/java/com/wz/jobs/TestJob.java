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
    @Override
    public List<String> selectTasks(String s, String s1, int i, List<TaskItemDefine> list, int i1) throws Exception {
        System.out.println("开始获取任务...");
        List<String> tasks = Arrays.asList("1", "2", "3", "4", "5");
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
