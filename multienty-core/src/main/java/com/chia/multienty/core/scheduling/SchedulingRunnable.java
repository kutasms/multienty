package com.chia.multienty.core.scheduling;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chia.multienty.core.parameter.base.SchedulingJobDisableParameter;
import com.chia.multienty.core.pojo.SchedulingJob;
import com.chia.multienty.core.service.SchedulingJobService;
import com.chia.multienty.core.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Objects;

@Slf4j
public class SchedulingRunnable implements Runnable{

    private SchedulingJob job;


    public SchedulingRunnable(SchedulingJob job) {
       this.job = job;
    }

    @Override
    public void run() {
        log.info("定时任务开始执行 - bean：{}，方法：{}，参数：{}",
                job.getBeanName(), job.getMethodName(), job.getParams());
        long startTime = System.currentTimeMillis();
        try{
            Object target = SpringUtil.getBean(job.getBeanName());
            Method method = null;
            Class<?>[] clazzArray = null;
            if(StringUtils.isNotEmpty(job.getParams())) {

                String[] types = job.getParamTypes().split(",");
                clazzArray = new Class[types.length];
                if(types.length > 0) {
                    for(int i=0;i<types.length;i++) {
                        Class<?> clazz = Class.forName(types[i].trim());
                        clazzArray[i] = clazz;
                    }
                }
                method = target.getClass().getDeclaredMethod(job.getMethodName(), clazzArray);
            } else {
                method = target.getClass().getDeclaredMethod(job.getMethodName());
            }
            ReflectionUtils.makeAccessible(method);
            if(StringUtils.isNotEmpty(job.getParams())) {
                JSONArray paramArray = JSONObject.parseArray(job.getParams());
                Object[] args = new Object[paramArray.size()];
                for(int i=0;i<paramArray.size();i++) {
                    String arg = JSONObject.toJSONString(paramArray.get(i));
                    Object object = JSONObject.parseObject(arg, clazzArray[i]);
                    args[i] = object;
                }
                method.invoke(target, args);
            } else {
                method.invoke(target);
            }
            if(job.getType().equals(SchedulingJobType.ONCE.getValue())) {
                // 一次性任务立即结束
                SchedulingJobService service = SpringUtil.getBean(SchedulingJobService.class);
                service.disable(new SchedulingJobDisableParameter()
                        .setJobId(job.getJobId()));
            }
        }
        catch (Exception ex) {
            log.error(String.format("定时任务执行异常 - bean：%s，方法：%s，参数：%s ",
                    job.getBeanName(), job.getMethodName(), job.getParams()), ex);
        }
        long times = System.currentTimeMillis() - startTime;
        log.info("定时任务执行结束 - bean：{}，方法：{}，参数：{}，耗时：{} 毫秒",
                job.getBeanName(), job.getMethodName(), job.getParams(), times);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchedulingRunnable that = (SchedulingRunnable) o;
        if (job.getParams() == null) {
            return job.getBeanName().equals(that.job.getBeanName()) &&
                    job.getMethodName().equals(that.job.getMethodName()) &&
                    that.job.getParams() == null &&
                    that.job.getParamTypes() == null;
        }

        return job.getBeanName().equals(that.job.getBeanName()) &&
                job.getMethodName().equals(that.job.getMethodName()) &&
                job.getParams().equals(that.job.getParams()) &&
                job.getParamTypes().equals(that.job.getParamTypes());
    }

    @Override
    public int hashCode() {
        if (job.getParams() == null) {
            return Objects.hash(job.getBeanName(), job.getMethodName());
        }
        return Objects.hash(job.getBeanName(), job.getMethodName(), job.getParams(), job.getParamTypes());
    }
}
