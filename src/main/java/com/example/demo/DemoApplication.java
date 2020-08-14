package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;



@EnableAsync
@SpringBootApplication
public class DemoApplication  {



    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {//for back ti back call
        return restTemplateBuilder.build();
    }

    @Bean
    public TaskExecutor taskExecutorUrgent() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(100);
        threadPoolTaskExecutor.setQueueCapacity(1000);
//        threadPoolTaskExecutor.setDaemon();
//        threadPoolTaskExecutor.setAllowCoreThreadTimeOut();
//        threadPoolTaskExecutor.setKeepAliveSeconds();
        return threadPoolTaskExecutor;
    }


    @Bean
    public TaskExecutor fileMultipleUpload() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(5);
        threadPoolTaskExecutor.setMaxPoolSize(5);
        threadPoolTaskExecutor.setKeepAliveSeconds(60*60*60);
        threadPoolTaskExecutor.setQueueCapacity(10);
        return threadPoolTaskExecutor;
    }





}
