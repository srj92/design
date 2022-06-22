package lld.rateLimiter.tokenBucket;

public interface RateLimiter {
    boolean grantAccess();
}
