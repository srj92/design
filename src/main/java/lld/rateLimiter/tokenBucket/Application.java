package lld.rateLimiter.tokenBucket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    public static void main(String[] args) {
        UserBuckerCreator userBuckerCreator = new UserBuckerCreator(1);
        ExecutorService executors = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 12; i++) {
            executors.execute(() -> userBuckerCreator.accessApplication(1));
        }
        executors.shutdown();
    }

}
