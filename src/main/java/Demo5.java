/**
 * 死锁
 */
public class Demo5 {
    public static void main(String[] args) {
        new Thread(new Test(true)).start();
        new Thread(new Test(false)).start();
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