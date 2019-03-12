package com.leixiao.note.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

//等待/通知机制，典型的等待通知范式
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) {
        Thread waitThread = new Thread(new Wait(),"WaitThread");
        waitThread.start();

        SleepUtils.second(1);

        Thread notifyThread = new Thread(new Notify(),"NotifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable{

        @Override
        public void run() {
            synchronized (lock){
                while (flag){
                    try{
                        System.out.println(Thread.currentThread() +
                                "flag is true. wait@ "+
                                new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        //调用wait()会释放锁
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() +
                        "flag is false. running@ "+
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable{

        @Override
        public void run() {
            synchronized (lock){
                System.out.println(Thread.currentThread() +
                        "hold lock. notify@" +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));

                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }

            //需要和wait线程抢占锁
            synchronized (lock){
                System.out.println(Thread.currentThread() +
                        "hold lock again. sleep@" +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }
}
