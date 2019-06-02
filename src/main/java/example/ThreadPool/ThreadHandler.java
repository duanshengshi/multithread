package example.ThreadPool;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadHandler implements Runnable {
    private String name;
    public ThreadHandler(String name) {
        this.name = "thread"+name;
    }
    public void run() {
        System.out.println( name +" Start. Time = "+new Date());
        processCommand();
        System.out.println( name +" End. Time = "+new Date());
    }
    private void processCommand() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
//建立ExecutorService线程池
//    ExecutorService executorService = Executors.newCachedThreadPool();

    //获取当前系统的CPU 数目
//    int cpuNums = Runtime.getRuntime().availableProcessors();
    //ExecutorService通常根据系统资源情况灵活定义线程池大小
//    ExecutorService executorService1 =Executors.newFixedThreadPool(cpuNums * POOL_SIZE);

}
