package com.example.demo;

import com.example.demo.controller.MetadataController;
import com.example.demo.util.encoder.Md5Encoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.sql.DataSource;

@EnableAsync
@SpringBootApplication()
public class DemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,  args);
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
        threadPoolTaskExecutor.setKeepAliveSeconds(60 * 60 * 24);
        threadPoolTaskExecutor.setQueueCapacity(10);
        return threadPoolTaskExecutor;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Md5Encoder();
    }

    @Bean
    public TokenStore tokenStore(DataSource d){
        return new JdbcTokenStore(d);
    }


}
