package com.wxy.demo;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//定时任务锁
//defaultLockAtMostFor 指定在执行节点结束时应保留锁的默认时间使用ISO8601 Duration格式
//作用就是在被加锁的节点挂了时，无法释放锁，造成其他节点无法进行下一任务
//这里默认30s
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
