package multithreading;

import java.util.concurrent.*;

class CallableTask implements Callable<String>{
    private final String name;

    public CallableTask(String name){
        this.name = name;
    }
    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "Hello" + name;
    }
}
public class CallableRunner {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> submit = executorService.submit(new CallableTask("Muskan"));
        String s = submit.get();
        System.out.println(s);
            System.out.println("Main called");
        executorService.shutdown();
    }
}
