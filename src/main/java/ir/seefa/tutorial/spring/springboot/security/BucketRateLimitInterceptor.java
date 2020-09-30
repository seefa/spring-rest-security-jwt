package ir.seefa.tutorial.spring.springboot.security;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 30 Sep 2020 T 12:46:22
 */
public class BucketRateLimitInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Bucket bucket;
    private final int numTokens;

    public BucketRateLimitInterceptor(Bucket bucket, int numTokens) {
        this.bucket = bucket;
        this.numTokens = numTokens;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ConsumptionProbe probe = this.bucket.tryConsumeAndReturnRemaining(this.numTokens);

        if (probe.isConsumed()) {
            String remainingTokens = Long.toString(probe.getRemainingTokens());
            logger.info("===> Current remaining tokens value: " + remainingTokens);
            response.addHeader("X-Rate-Limit-Remaining", remainingTokens);
            return true;
        }

        String waitForRefillValue = Long.toString(TimeUnit.NANOSECONDS.toMillis(probe.getNanosToWaitForRefill()));
        logger.info("====> Number of requests exceeded. Current value wait for refill: " + waitForRefillValue);
        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        response.addHeader("X-Rate-Limit-Retry-After-Milliseconds", waitForRefillValue);
        return false;
    }
}
