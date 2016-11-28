package threaddemos;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by afghl on 16/11/28.
 */
public class FutureDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int start1 = 1;
        int end1 = 100;
        int start2 = 101;
        int end2 = 200;
        int start3 = 201;
        int end3 = 300;

        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.submit()

        List<Callable<Integer>> tasks = Arrays.asList(
                () -> FutureDemo.sum(start1, end1),
                () -> FutureDemo.sum(start2, end2),
                () -> FutureDemo.sum(start3, end3)
        );

        List<Future<Integer>> results = executor.invokeAll(tasks);

        while (!results.stream().allMatch(Future::isDone));

        System.out.println(results.stream().reduce(future -> {
            try {
                return future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }));

        executor.shutdown();
    }

    public static int sum(int s, int e) {
        int sum = 0;
        for (int i = s; i <= e; i++)
            sum += i;
        return sum;
    }
}
