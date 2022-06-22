package lld.rateLimiter.tokenBucket;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TokenBucket implements RateLimiter {

    private int bucketCapacity;
    private int refreshRate;
    private AtomicInteger currentCapacity = new AtomicInteger();
    private AtomicLong lastUpdatedTime = new AtomicLong();

    public TokenBucket(int bucketCapacity, int refreshRate) {
        this.bucketCapacity = bucketCapacity;
        this.refreshRate = refreshRate;
        currentCapacity.getAndSet(bucketCapacity);
        lastUpdatedTime.getAndSet(System.currentTimeMillis());
    }

    @Override
    public boolean grantAccess() {
        refreshBucket();
        if (currentCapacity.get() > 0) {
            currentCapacity.decrementAndGet();
            return true;
        }
        return false;
    }

    private void refreshBucket() {
        long currentTime = System.currentTimeMillis();
        int additionalTokens = (int) ((currentTime - lastUpdatedTime.get()) / 1000) * refreshRate;
        int currCapacity = Math.min((currentCapacity.get() + additionalTokens), bucketCapacity);
        currentCapacity.getAndSet(currCapacity);
        lastUpdatedTime.getAndSet(currentTime);
    }

}