package example.join;

/**
* <pre>
* @ClassName:       ThreadNoJoin
* @Description:     TODO -  不加join线程
* @Author:          dss
* @Date:            2019-05-31 16:23:48
* @Version:         1.0
* </pre>
*/

public class ThreadNoJoin extends Thread{
    public ThreadNoJoin(String name){
        super(name);
    }
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 线程运行开始!");
        for (int i = 0; i < 5; i++) {
            System.out.println(getName() + "运行  :  " + i);
            try {
                sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 线程运行结束!");
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"主线程运行开始!");
        ThreadNoJoin thread1 = new ThreadNoJoin("线程1");
        ThreadNoJoin thread2 = new ThreadNoJoin("线程2");
        ThreadNoJoin thread3 = new ThreadNoJoin("线程3");
        thread1.start();//start()方法的调用后并不是立即执行多线程代码，而是使得该线程变为可运行态（Runnable）,什么时候运行是由操作系统决定的
        thread2.start();
        thread3.start();
        System.out.println(Thread.currentThread().getName()+ "主线程运行结束!");
    }
}
