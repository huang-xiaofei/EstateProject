/**
 * 
 */
package com.estate.frontier.executor;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author lenovo
 * @data 2019年7月13日 上午8:32:18
 */
@Configuration
public class ThreadPoolConfig {
	@Bean(name = "asyncPoolTaskExecutor")
	public ThreadPoolTaskExecutor getAsyncThreadPoolTaskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(1);
		taskExecutor.setMaxPoolSize(5);
		taskExecutor.setQueueCapacity(1);
		taskExecutor.setKeepAliveSeconds(60);
		taskExecutor.setThreadNamePrefix("w-2-pdf");
		// 线程池对拒绝任务（无线程可用）的处理策略，目前只支持AbortPolicy、CallerRunsPolicy；默认为后者
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		taskExecutor.initialize();
		return taskExecutor;
	}
}
