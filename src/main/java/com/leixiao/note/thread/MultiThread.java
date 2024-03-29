package com.leixiao.note.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class MultiThread {
    //打印当前线程
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);

        for (ThreadInfo threadInfo : threadInfos){
            System.out.println("["+threadInfo.getThreadId()+"]"+threadInfo.getThreadName());
        }

        //[6]Monitor Ctrl-Break
        //[5]Attach Listener
        //[4]Signal Dispatcher   分发处理发送给JVM信号的线程
        //[3]Finalizer   调用对象finalizer方法的线程
        //[2]Reference Handler   清除Reference的线程
        //[1]main   main线程，用户程序入口
    }
}
