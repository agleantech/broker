package com.lean.broker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "proc")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor poolExecutor = new ThreadPoolTaskExecutor();
        poolExecutor.setThreadNamePrefix("async-");
        poolExecutor.setCorePoolSize(3);
        poolExecutor.setMaxPoolSize(3);
        poolExecutor.setQueueCapacity(200);
        poolExecutor.afterPropertiesSet();
        return poolExecutor;
    }
}
