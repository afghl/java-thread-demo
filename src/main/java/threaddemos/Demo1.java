package threaddemos;

public class Demo1 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++)
            System.out.println("running in demo1 thread...." + i);
    }
}

class Main {
    public static void main(String[] args) {
        Thread th = new Demo1();
        th.start();

        Thread th1 = new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++)
                    System.out.println("running in demo1 thread1!!!!...." + i);
            }
        };

        th1.start();
        for (int i = 0; i < 10; i++)
            System.out.println("running in main thread...." + i);
    }
}
