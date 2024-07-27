package com.enovka.gemini4j.infrastructure.http;

import com.enovka.gemini4j.infrastructure.tool.BaseClass;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A rate limiter that controls the rate of requests based on a sliding window
 * time frame. This rate limiter allows a fixed number of requests within a
 * specified time window and tracks the timestamps of requests to enforce the
 * rate limit.
 *
 * @author Everson Novka &lt;enovka@gmail.com&gt;
 * @since 0.1.1
 */
public class RateLimiter extends BaseClass {

    private final int requestsPerWindow;
    private final Duration windowDuration;
    private final Queue<Instant> requestTimestamps;

    /**
     * Constructs a new RateLimiter with the specified rate limit parameters.
     *
     * @param requestsPerWindow The maximum number of requests allowed within
     * the time window.
     * @param windowDuration The duration of the sliding time window.
     */
    public RateLimiter(int requestsPerWindow, Duration windowDuration) {
        this.requestsPerWindow = requestsPerWindow;
        this.windowDuration = windowDuration;
        this.requestTimestamps = new LinkedList<>();
    }

    /**
     * Acquires a permit from the rate limiter, blocking if necessary until a
     * permit becomes available within the rate limit. This method ensures that
     * the rate of requests does not exceed the configured limit.
     */
    public synchronized void acquire() {
        logDebug("Acquiring permit from rate limiter.");
        cleanupExpiredTimestamps();

        while (requestTimestamps.size() >= requestsPerWindow) {
            Duration timeToWait = Duration.between(requestTimestamps.peek(),
                    Instant.now());
            long waitMillis = timeToWait.toMillis();
            if (waitMillis > 0) {
                logInfo(String.format(
                        "Rate limit reached. Waiting for %d milliseconds.",
                        waitMillis));
                try {
                    wait(waitMillis);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    logError(
                            "Interrupted while waiting for rate limiter permit.",
                            e);
                    // Rethrow as RuntimeException to avoid checked exceptions
                    throw new RuntimeException(e);
                }
            }
            cleanupExpiredTimestamps();
        }

        requestTimestamps.offer(Instant.now());
        logDebug("Permit acquired.");
    }

    /**
     * Removes timestamps from the queue that are older than the specified time
     * window. This method ensures that only timestamps within the current
     * sliding window are considered for rate limiting.
     */
    private void cleanupExpiredTimestamps() {
        Instant now = Instant.now();
        while (!requestTimestamps.isEmpty() && Duration.between(
                requestTimestamps.peek(), now).compareTo(windowDuration) > 0) {
            requestTimestamps.poll();
        }
    }
}