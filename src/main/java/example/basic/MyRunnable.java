package example.basic;

/**
 * <pre>
 * @ClassName: dss
 * @Description: TODO - ${description}
 * @Author: $Author$
 * @Date: ${Date}
 * @Version: 1.0
 * </pre>
 */
public class MyRunnable implements Runnable{
    private String name;
    public MyRunnable(String name){
        this.name = name;
    }
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行  :  " + i);
            try {
                Thread.sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new MyRunnable("线程4")).start();
        new Thread(new MyRunnable("线程5")).start();
        new Thread(new MyRunnable("线程6")).start();
    }
}
