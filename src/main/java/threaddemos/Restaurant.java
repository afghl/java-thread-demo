package threaddemos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Meal {
    private final int num;

    Meal(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Meal " + num;
    }
}

class WaitPerson implements Runnable {
    private Restaurant r;

    WaitPerson(Restaurant res) {
        r = res;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (r.meal == null)
                        wait();
                }
                System.out.println("Waitperson got " + r.meal);
                synchronized (r.chef) {
                    r.meal = null;
                    r.chef.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("waitperson interrupted");
        }

    }
}

class Chef implements Runnable {
    private Restaurant r;
    private int count = 0;

    Chef (Restaurant res) {
        r = res;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (r.meal != null)
                        wait();
                }
                if (++count == 10) {
                    System.out.println("out of food, closing...");
                    r.exec.shutdownNow();
                }
                System.out.println("Order up");
                synchronized (r.waitPerson) {
                    r.meal = new Meal(count);
                    r.waitPerson.notifyAll();
                }
                Thread.sleep(1590);
            }
        } catch (InterruptedException e) {
            System.out.println("chef interrunpted");
        }
    }
}
public class Restaurant {
    Meal meal;
    WaitPerson waitPerson = new WaitPerson(this);
    ExecutorService exec = Executors.newCachedThreadPool();
    Chef chef = new Chef(this);
    public Restaurant() {
        exec.execute(chef);
        exec.execute(waitPerson);
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}
