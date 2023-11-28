package multithreading;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultipleCallableRunner {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        List<CallableTask> list=List.of(new CallableTask("Kajal"),new CallableTask("Kashish"),new CallableTask("Naman"));
 //       List<Future<String>> results = executorService.invokeAll(list);

//        for(Future<String> result : results) {
//            String s = result.get();
//            System.out.println(s);
//        }
        String resultsAny = executorService.invokeAny(list);//Executor Service Wait for only the fastest task using invokeAny
        System.out.println(resultsAny);
        System.out.println("Main called");
        executorService.shutdown();

    }
}
