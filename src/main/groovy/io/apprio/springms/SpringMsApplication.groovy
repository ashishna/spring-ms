package io.apprio.springms


import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.Bean
import org.springframework.core.task.TaskExecutor
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@SpringBootApplication
@EnableAsync
@EnableEurekaClient
class SpringMsApplication {

    static void main(String[] args) {
        System.setProperty('jasypt.encryptor.password','supersecretz')
        SpringApplication.run(SpringMsApplication, args)
    }
    
    @Bean(name="processExecutor")
    TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.threadNamePrefix = 'Async-'
        threadPoolTaskExecutor.corePoolSize = 3
        threadPoolTaskExecutor.maxPoolSize = 3
        threadPoolTaskExecutor.queueCapacity = 100
        return threadPoolTaskExecutor;
    }

}
