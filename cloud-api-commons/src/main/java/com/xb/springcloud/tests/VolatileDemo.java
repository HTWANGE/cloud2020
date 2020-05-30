package com.xb.springcloud.tests;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by XB on 2020/5/25.
 */
class MyData{
    //int number = 0;
    volatile int number = 0; //加了 volatile 修饰使得number具有可见性

    public void addTo60(){
        this.number = 60;
    }

    //请注意，此时number前面是加了volatile关键字修饰的，volatile不保证原子性
    //关键字synchronized可以保证在同一时刻，只有一个线程可以执行某个方法或某个代码块，同时synchronized可以保证一个线程的变化可见（可见性），即可以代替volatile
    //这里使用synchronized有点大材小用
    //public synchronized void addPlusPlus(){
    public void addPlusPlus(){
        this.number ++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addMyAtomic(){
        atomicInteger.getAndIncrement();
    }
}

/**
 * 1 验证volatile的可见性
 *  1.1 假如 int number = 0;, number变量之前根本没有添加volatile关键字修饰，没有可见性
 *  1.2 添加了volatile，可以解决可见性问题
 *
 * 2 验证volatile不保证原子性
 *  2.1 原子性指的是什么意思？
 *      不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割，需要整体完整，要么同时成功，要么同时失败。
 *  2.2 volatile不保证原子性的案例演示
 *
 *  2.3 why
 *
 *  2.4如何解决原子性？
 *      * 加synchronized（杀鸡用牛刀）
 *      * 直接使用JUC（java.util.concurrent）下的AtomicInteger
 */
public class VolatileDemo {
    public static void main(String [] args) { //main是一切方法的运行入口
        MyData myData = new MyData();
        for(int i = 1; i <= 20; i ++){
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++){
                    myData.addPlusPlus();
                    myData.addMyAtomic();
                }
            }, String.valueOf(i)).start();
        }

        //需要等待上面 20 个线程都全部计算完成后，再用main线程取得最终的结果值看是多少
        //为什么大于2？，因为后台默认有两个线程，一个main线程，一个GC线程
        while(Thread.activeCount() > 2){
            Thread.yield();
        }
        
        System.out.println(Thread.currentThread().getName() + "\t int type, finally number value：" + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t AtomicInteger type, finally number value：" + myData.atomicInteger);
    }

    //volatile 可以保证可见性，及时通知其它线程，主物理内存的值已经被修改
    public static void seeOkByVolatile(){
        MyData myData = new MyData(); //资源类

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, "aaa").start();*/

        //Lambda表达式
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            //暂停一会儿线程
            try{TimeUnit.SECONDS.sleep(3);}catch (InterruptedException e){e.printStackTrace();}
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t update number value：" + myData.number);
        },"AAA").start();

        //第二个线程是main线程
        while(myData.number == 0){
            //main线程就一直在这里等待循环，直到 number 值不再等于零
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over, main get number value：" + myData.number);
    }
}
