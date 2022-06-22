package lld.rateLimiter.tokenBucket;

import java.util.HashMap;
import java.util.Map;

public class UserBuckerCreator {

    Map<Integer, TokenBucket> bucket = new HashMap<>();

    public UserBuckerCreator(int id) {
        bucket.put(id, new TokenBucket(10, 10));
    }

    void accessApplication(int id) {
        if (bucket.get(id).grantAccess()) {
            System.out.println(Thread.currentThread().getName() + " access allowed");
        } else {
            System.out.println(Thread.currentThread().getName() + " access blocked");
        }
    }

}