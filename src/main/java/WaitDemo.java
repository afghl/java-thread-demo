/**
 * waitdemo
 */
public class WaitDemo {
    public static void main(String[] args)  {
        synchronized (new WaitDemo()) {
            try {
                new WaitDemo().wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
