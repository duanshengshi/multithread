package example.ThreadPool;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

public class TestThreadPools {
    public static void main(String[] args) {
        executeFixedDelay();
    }

    public static void testCachedThreadPool() {
        System.out.println("Main: Starting at: "+ new Date());
        ExecutorService exec = Executors.newCachedThreadPool();   //创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE
        for(int i = 0; i < 10; i++) {
            exec.execute(new ThreadHandler(String.valueOf(i)));
        }
        exec.shutdown();  //执行到此处并不会马上关闭线程池,但之后不能再往线程池中加线程，否则会报错
//        exec.shutdownNow();
        System.out.println("Main: Finished all threads at"+ new Date());
    }

   /* FixedThreadPool模式会使用一个优先固定数目的线程来处理若干数目的任务。规定数目的线程处理所有任务，一
     旦有线程处理完了任务就会被用来处理新的任务(如果有的话)。这种模式与上面的CachedThreadPool是不同的，
     CachedThreadPool模式下处理一定数量的任务的线程数目是不确定的。而FixedThreadPool模式下最多 的线
     程数目是一定的。*/
   /* 创建了一个固定大小的线程池，大小为5.也就说同一时刻最多只有5个线程能运行。并且线程执行完成后就从
    线程池中移出。它也不能保证放入的线程能按顺序执行。这要看在等待运行的线程的竞争状态了。*/
    public static void testFixThreadPool() {
        System.out.println("Main Thread: Starting at: "+ new Date());
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 10; i++) {
            exec.execute(new ThreadHandler(String.valueOf(i)));
        }
        exec.shutdown();  //执行到此处并不会马上关闭线程池
        System.out.println("Main Thread: Finished at:"+ new Date());
    }

   /* 其实这个就是创建只能运行一条线程的线程池。它能保证线程的先后顺序执行，
    并且能保证一条线程执行完成后才开启另一条新的线程
    等价于ExecutorService exec = Executors.newFixedThreadPool(1)*/
    public static void testSingleThreadPool() {
        System.out.println("Main Thread: Starting at: "+ new Date());
        ExecutorService exec = Executors.newSingleThreadExecutor();   //创建大小为1的固定线程池;
        for(int i = 0; i < 10; i++) {
            exec.execute(new ThreadHandler(String.valueOf(i)));
        }
        exec.shutdown();  //执行到此处并不会马上关闭线程池
        System.out.println("Main Thread: Finished at:"+ new Date());
    }

//    这是一个计划线程池类，它能设置线程执行的先后间隔及执行时间等
    public static void testScheduledThreadPool() {
        System.out.println("Main Thread: Starting at: "+ new Date());
        ScheduledThreadPoolExecutor exec = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(10);   //创建大小为10的线程池
        for(int i = 0; i < 10; i++) {
            exec.schedule(new ThreadHandler(String.valueOf(i)), 10, TimeUnit.SECONDS);//延迟10秒执行
        }
        exec.shutdown();  //执行到此处并不会马上关闭线程池
        while(!exec.isTerminated()){
            //wait for all tasks to finish
        }
        System.out.println("Main Thread: Finished at:"+ new Date());
    }

    //sScheduledThreadPoolExecutor的cheduleAtFixedRate方法 按指定频率周期执行某个任务
 /*   public ScheduledFuture<?> scheduleAtFixedRate(Runnable command,
                                                  long initialDelay,
                                                  long period,
                                                  TimeUnit unit);
    command：执行线程
    initialDelay：初始化延时
    period：两次开始执行最小间隔时间
    unit：计时单位*/

    /**
     * 初始化延迟0ms开始执行，每隔2000ms重新执行一次任务
     * @author dss
     */
    //每隔2秒执行一次，注意，如果上次的线程还没有执行完成，那么会阻塞下一个线程的执行。即使线程池设置得足够大。
    /*间隔指的是连续两次任务开始执行的间隔。对于scheduleAtFixedRate方法，当执行任务的时间大于我们指定的间隔时间时，
     它并不会在指定间隔时开辟一个新的线程并发执行这个任务。而是等待该线程执行完毕。*/
    public static void executeFixedRate() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);
        executor.scheduleAtFixedRate(
                new ThreadHandler("1"),
                0,
                2000,
                TimeUnit.MILLISECONDS);
    }

    /**
     * 以固定延迟时间进行执行
     * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
     */
    public static void executeFixedDelay() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);
        executor.scheduleWithFixedDelay(
                new ThreadHandler(""),
                0,
                2000,
                TimeUnit.MILLISECONDS);
    }


    /**
     * 每天晚上9点执行一次
     * 每天定时安排任务进行执行
     */
    public static void executeEightAtNightPerDay() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        long oneDay = 24 * 60 * 60 * 1000;
        long initDelay  = getTimeMillis("21:00:00") - System.currentTimeMillis();
        initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;

        executor.scheduleAtFixedRate(
                new ThreadHandler(""),
                initDelay,
                oneDay,
                TimeUnit.MILLISECONDS);
    }

    /**
     * 获取指定时间对应的毫秒数
     * @param time "HH:mm:ss"
     * @return
     */
    private static long getTimeMillis(String time) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");
            Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);
            return curDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
