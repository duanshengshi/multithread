package example.Future;

import java.util.Random;
import java.util.concurrent.*;

public class FutureTest {
    public static void main(String[] args) {
        System.out.println("main Thread begin at:"+ System.nanoTime());
        ExecutorService executor = Executors.newCachedThreadPool();
        HandleCallable task1 = new HandleCallable("1");
        HandleCallable task2 = new HandleCallable("2");
        HandleCallable task3 = new HandleCallable("3");
        Future<Integer> result1 = executor.submit(task1);
        Future<Integer> result2 = executor.submit(task2);
        Future<Integer> result3 = executor.submit(task3);
        executor.shutdown();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        try {
            System.out.println("task1运行结果:"+result1.get());
            System.out.println("task2运行结果:"+result2.get());
            System.out.println("task3运行结果:"+result3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("main Thread finish at:"+ System.nanoTime());
    }

}
class HandleCallable implements Callable<Integer> {
    private String name;
    public HandleCallable(String name) {
        this.name = name;
    }

    public Integer call() throws Exception {
        System.out.println("task"+ name + "开始进行计算");
        Thread.sleep(3000);
        int sum = new Random().nextInt(300);
        int result = 0;
        for (int i = 0; i < sum; i++)
            result += i;
        return result;
    }
}
