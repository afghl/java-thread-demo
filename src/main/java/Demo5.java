/**
 * 死锁
 */
public class Demo5 {
    public static void main(String[] args)
    {
        Thread.currentThread().interrupt();
        System.out.println("是否停止1？" + Thread.interrupted());
        System.out.println("是否停止2？" + Thread.interrupted());
        System.out.println("end!");
    }
}

class Test implements Runnable {
    private static Object a = new Object();
    private static Object b = new Object();
    private boolean flag;

    Test (boolean f) {
        flag = f;
    }

    public void run() {
        while (true) {
            if (flag) {
                synchronized (a) {
                    System.out.println("if a");
                    synchronized (b) {
                        System.out.println("if b");
                    }
                }
            } else {
                synchronized (b) {
                    System.out.println("else b");
                    synchronized (a) {
                        System.out.println("else a");
                    }
                }
            }
        }
    }
}