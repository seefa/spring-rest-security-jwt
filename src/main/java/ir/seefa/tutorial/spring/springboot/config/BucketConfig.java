package ir.seefa.tutorial.spring.springboot.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import ir.seefa.tutorial.spring.springboot.security.BucketRateLimitInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 30 Sep 2020 T 13:03:13
 */
@Configuration
public class BucketConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        Refill refill = Refill.greedy(60, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(5, refill).withInitialTokens(1);

        Bucket bucket = Bucket4j.builder().addLimit(limit).build();
        registry.addInterceptor(new BucketRateLimitInterceptor(bucket, 1)).addPathPatterns("/api/**");
    }
}
