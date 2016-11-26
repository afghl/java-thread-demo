import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * 从任务中产生返回值
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();
        for (int i = 0; i < 10; i++)
            results.add(es.submit(new TaskWithResult(i)));
        for (Future<Integer> fi : results)
            System.out.println(fi.get());
        es.shutdown();
    }
}

class TaskWithResult implements Callable<Integer> {
    private int id;
    TaskWithResult (int id) {
        this.id = id;
    }

    public Integer call() throws Exception {
        return id + 100;
    }
}
