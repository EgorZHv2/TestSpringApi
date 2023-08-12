package springApp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;

import java.util.concurrent.Executor;
@Configuration
@EnableAsync
public class AsyncConfiguration {
    @Bean("threadPoolTaskExecutor")
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setQueueCapacity(50);
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.setThreadNamePrefix("apiThread-");
        executor.initialize();
        return new DelegatingSecurityContextAsyncTaskExecutor(executor);

    }
//    @Bean
//    @Autowired
//    @Qualifier("threadPoolTaskExecutor")
//    public DelegatingSecurityContextAsyncTaskExecutor taskExecutor (ThreadPoolTaskExecutor delegate) {
//        return new DelegatingSecurityContextAsyncTaskExecutor(delegate);
//    }
}
