package multithreading;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ExchangeRateFetcher {

    public static void main(String[] args) {
        List<String> sources = Arrays.asList("Source1", "Source2", "Source3", "Source4", "Source5");

        CompletableFuture[] futures = sources.stream()
                .map(source -> CompletableFuture.supplyAsync(() -> fetchExchangeRate(source)))
                .toArray(CompletableFuture[]::new);

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures);

        try {
            allOf.get(); // Wait for all CompletableFuture to complete
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Get the fastest response
        CompletableFuture<String> fastestResponse = CompletableFuture.anyOf(futures)
                .thenApply(result -> "Fastest response: " + result);

        try {
            System.out.println(fastestResponse.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Aggregate all responses into a JSON map
        Map<String, Double> exchangeRates = sources.stream()
                .collect(Collectors.toMap(source -> source, ExchangeRateFetcher::fetchExchangeRate));

        System.out.println("Exchange Rates: " + exchangeRates);
    }

    private static Double fetchExchangeRate(String source) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Math.random();
    }
}
