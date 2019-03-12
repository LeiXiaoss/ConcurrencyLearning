package com.leixiao.note.thread;

import java.util.concurrent.TimeUnit;

//安全的停止线程
public class Shutdown {

    private static class Runner implements Runnable{
        private long i;
        private volatile boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void cancel(){
            on = false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runner one = new Runner();
        Thread countThread = new Thread(one,"CountThread");
        countThread.start();

        //睡眠一秒，main线程对CountThread线程进行中断，使CountThread感知中断而结束
        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();

        Runner two = new Runner();
        countThread = new Thread(two,"CountThread");
        countThread.start();

        TimeUnit.SECONDS.sleep(1);
        two.cancel();
    }
}

