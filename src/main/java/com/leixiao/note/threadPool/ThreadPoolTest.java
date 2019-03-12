package com.leixiao.note.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    static class MyTask implements Runnable{
        private int taskNum;

        public MyTask(int taskNum) {
            this.taskNum = taskNum;
        }

        @Override
        public void run() {
            System.out.println("正在执行task:" + taskNum);
            try {
                Thread.currentThread().sleep(4000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,200, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(5));

        for (int i=0;i<20;i++){
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数量："+executor.getPoolSize()+"," +
                    "工作队列的等待执行的任务数："+executor.getQueue().size()+","+
                    "已完成的任务数："+executor.getCompletedTaskCount());
        }
        System.out.println(executor.getCompletedTaskCount());
        executor.shutdown();
    }
}
