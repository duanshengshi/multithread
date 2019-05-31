package example.yield;

/** 
* <pre> 
* @ClassName:       ThreadWitnYield 
* @Description:     TODO -  
* @Author:          dss 
* @Date:            2019-05-31 16:49:29 
* @Version:         1.0 
* </pre> 
*/

public class ThreadWitnYield extends Thread{

    public ThreadWitnYield(String name){
        super(name);
    }
    @Override
    public void run() {
        for (int i = 1; i <= 50; i++) {
            System.out.println("" + this.getName() + "-----" + i);
            // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
            if (i ==30) {
                this.yield();
            }
        }
    }

    public static void main(String[] args) {
        ThreadWitnYield threadWitnYield1 = new ThreadWitnYield("线程1");
        ThreadWitnYield threadWitnYield2 = new ThreadWitnYield("线程2");
        ThreadWitnYield threadWitnYield3 = new ThreadWitnYield("线程3");
        ThreadWitnYield threadWitnYield4 = new ThreadWitnYield("线程4");
        threadWitnYield1.start();
        //默认优先级为中级NORM_PRIORITY
        threadWitnYield2.start();
        //设置优先级为最高
        threadWitnYield3.setPriority(Thread.MAX_PRIORITY);
        threadWitnYield3.start();
        //设置优先级为最低
        threadWitnYield4.setPriority(Thread.MIN_PRIORITY);
        threadWitnYield4.start();
    }
}
