package example.basic;

/**
* <pre>
* @ClassName:       MyThread
* @Description:     TODO -  多线程学习
* @Author:          dss
* @Date:            2019-05-31 14:40:30
* @Version:         1.0
* </pre>
*/

public class MyThread extends Thread{
    public MyThread(String name){
        super(name);
    }
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(getName() + "运行  :  " + i);
            try {
                sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyThread thread1 = new MyThread("线程1");
        MyThread thread2 = new MyThread("线程2");
        MyThread thread3 = new MyThread("线程3");
        thread1.start();//start()方法的调用后并不是立即执行多线程代码，而是使得该线程变为可运行态（Runnable）,什么时候运行是由操作系统决定的
        thread2.start();
        thread3.start();
    }
}
