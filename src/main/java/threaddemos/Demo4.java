package threaddemos;

/**
 * 使用runnable
 */
public class Demo4 {
    public static void main(String[] args) {
        Runnable t = new Demo4Ticket();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
    }
}

class Demo4Ticket implements Runnable {
    private static int count = 200;

    public void run() {
        while (count > 0) {
            this.sold();
        }
    }

    private synchronized static void sold() {
        if (count == 0) return;
        System.out.println("runnable " + Thread.currentThread().getName() + " sold tick: " + count--);
    }
}


class Singleton {
    private static Singleton s;
    private Singleton() {}

    public static Singleton getInstance() {
        if (s == null)
            synchronized (Singleton.class) {
                if (s == null) s = new Singleton();
            }
        return s;
    }
}