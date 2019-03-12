package com.leixiao.note.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Priority {
    //为不同线程设置优先级

    private static volatile boolean notStart = true;
    private static volatile boolean notEnd = true;

    public static void main(String[] args) throws InterruptedException {
        List<Job> jobs = new ArrayList<>();

        for (int i=0;i<10;i++){
            int priority = i>=5?Thread.MAX_PRIORITY:Thread.MIN_PRIORITY;

            Job job = new Job(priority);
            jobs.add(job);
            Thread thread = new Thread(job,"Thread"+i);
            thread.setPriority(priority);
            thread.start();
        }
        notStart = false;
        TimeUnit.SECONDS.sleep(10);
        notEnd = false;

        for (Job job : jobs){
            System.out.println("Job Priority:" + job.priority +", Count:" + job.jobCount);
        }
    }

    static class Job implements Runnable{
        private int priority;
        private long jobCount;

        public Job(int priority) {
            this.priority = priority;
        }

        @Override
        public void run() {
            while (notStart){
                //暂停当前正在执行的线程对象，并执行其他线程
                Thread.yield();
            }
            while (notEnd){
                Thread.yield();
                jobCount++;
            }
        }
    }
}
