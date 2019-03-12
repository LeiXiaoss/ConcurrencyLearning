package com.leixiao.note.thread;

import java.util.concurrent.TimeUnit;

public class Profiler {
    //ThreadLocal ,即线程对象
    //一个线程可以根据一个ThreadLocal 对象查询到绑定在这个线程上的一个值

    //第一次get()方法调用时，会初始化
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>(){
        protected Long initialValue(){
            return System.currentTimeMillis();
        }
    };

    public static final void begin(){
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end(){
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: "+ Profiler.end() + "mills");
    }


}
