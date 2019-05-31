package example.wait;

/**
* <pre>
* @ClassName:       ThreadWithWaitAndNotify
* @Description:     TODO -  wait()和notify()测试
* @Author:          dss
* @Date:            2019-05-31 17:15:29
* @Version:         1.0
* </pre>
*/

public class ThreadWithWaitAndNotify implements Runnable{
    private String name;
    private Object prev;
    private Object self;

    public ThreadWithWaitAndNotify(String name,Object prev,Object self){
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    public void run() {
        int count = 10;
        while (count > 0) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.print(name);
                    count--;
                    self.notify();
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) throws Exception{
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        ThreadWithWaitAndNotify pa = new ThreadWithWaitAndNotify("A", c, a);
        ThreadWithWaitAndNotify pb = new ThreadWithWaitAndNotify("B", a, b);
        ThreadWithWaitAndNotify pc = new ThreadWithWaitAndNotify("C", b, c);
        new Thread(pa).start();
        Thread.sleep(100);
        new Thread(pb).start();
        Thread.sleep(100);
        new Thread(pc).start();
        Thread.sleep(100);
    }
}
