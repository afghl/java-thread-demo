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
    private int count = 200;

    public void run() {
        while (count > 0) {
            System.out.println("runnable" + Thread.currentThread().getName() + "sold tick: " + count--);
        }
    }
}
