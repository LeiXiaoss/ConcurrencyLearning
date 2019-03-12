package com.leixiao.note.thread;

import java.util.concurrent.TimeUnit;

public class Daemon {
    //Daemon是一种支持型线程，JVM不存在Daemon线程时，JVM将退出
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(),"DaemonRunner");
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable{

        @Override
        public void run() {
            try{
                SleepUtils.second(10);
            }finally {
                //不会执行
                System.out.println("DaemonThread finally run");
            }
        }
    }

}
