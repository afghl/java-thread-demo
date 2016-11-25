package threaddemos;

/**
 * 卖票小程序
 */
public class Demo3 {
    public static void main(String[] args) {
        Thread t = new Ticket();
        t.start();
        t.start();
//        t.start();
//        t.start();
    }
}

class Ticket extends Thread {
    private static int count = 200;

    @Override
    public void run() {
        while (count > 0) {
            System.out.println(this.getName() + "sold tick: " + count--);
        }
    }
}
