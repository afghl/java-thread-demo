package threaddemos;

/**
 * 获取线程信息
 */
public class Demo2 extends Thread{
    public Demo2(String n) {
        super(n);
    }

    public void run() {
        System.out.println(getName());
    }
}

class Demo2Main {
    public static void main(String[] args) {
        new Demo2("thead name").start();
        System.out.println(Thread.currentThread().getName());
    }

}