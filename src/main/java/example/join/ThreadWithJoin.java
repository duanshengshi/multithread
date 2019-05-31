package example.join;

/**
* <pre>
* @ClassName:       ThreadWithJoin
* @Description:     TODO -  加上join
* @Author:          dss
* @Date:            2019-05-31 16:24:17
* @Version:         1.0
* </pre>
*/

public class ThreadWithJoin {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"主线程运行开始!");
        ThreadNoJoin thread1 = new ThreadNoJoin("线程1");
        ThreadNoJoin thread2 = new ThreadNoJoin("线程2");
        ThreadNoJoin thread3 = new ThreadNoJoin("线程3");
        thread1.start();//start()方法的调用后并不是立即执行多线程代码，而是使得该线程变为可运行态（Runnable）,什么时候运行是由操作系统决定的
        thread2.start();
        thread3.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+ "主线程运行结束!");
    }
}
