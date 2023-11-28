package multithreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture=new CompletableFuture<String>();
        completableFuture.get ();
        completableFuture.complete(  "return some dummy data..... ");
    }
}
