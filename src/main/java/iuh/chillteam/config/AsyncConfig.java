package iuh.chillteam.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Async Configuration
 * Enables asynchronous processing for email and other background tasks
 */
@Configuration
@EnableAsync
@Slf4j
public class AsyncConfig {

    /**
     * Email Task Executor
     * Handles email sending in background threads
     */
    @Bean(name = "emailTaskExecutor")
    public Executor emailTaskExecutor() {
        log.info("Configuring email task executor");

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        
        // Core pool size: số thread luôn chạy
        executor.setCorePoolSize(2);
        
        // Max pool size: số thread tối đa
        executor.setMaxPoolSize(5);
        
        // Queue capacity: số task chờ trong hàng đợi
        executor.setQueueCapacity(100);
        
        // Thread name prefix
        executor.setThreadNamePrefix("email-async-");
        
        // Rejection policy: chính sách khi queue đầy
        // CallerRunsPolicy: thread gọi sẽ thực thi task nếu queue đầy
        executor.setRejectedExecutionHandler(new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());
        
        // Wait for tasks to complete on shutdown
        executor.setWaitForTasksToCompleteOnShutdown(true);
        
        // Await termination timeout (seconds)
        executor.setAwaitTerminationSeconds(60);
        
        executor.initialize();
        
        log.info("Email task executor configured successfully");
        return executor;
    }

    /**
     * General Task Executor
     * Handles other background tasks (notifications, etc.)
     */
    @Bean(name = "generalTaskExecutor")
    public Executor generalTaskExecutor() {
        log.info("Configuring general task executor");

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(200);
        executor.setThreadNamePrefix("async-");
        executor.setRejectedExecutionHandler(new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();

        log.info("General task executor configured successfully");
        return executor;
    }
}
