package com.wxy.demo.task;

import net.javacrumbs.shedlock.core.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@Component注解用于对那些比较中立的类进行注释；
//相对与在持久层、业务层和控制层分别采用 @Repository、@Service 和 @Controller 对分层中的类进行注释
@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
public class MultithreadScheduleTask {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private int count = 0;

    @Value("${majun.intervel}")
    private Long intervel;

//    @Async
//    @Scheduled(fixedDelay = 5000)  //间隔1秒
    public void first() throws Exception {
        System.err.println("第一个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
        logger.error("第一个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
        //UtgardCommon.exterlFuncation();
        //UcomItemIdBatch.batchWork();
    }

//    @Async
//    @Scheduled(fixedDelay = 3000)
    public void second() {
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.err.println(MessageFormat.format("fixedDelay==={0}", format));
        String t = "";
        for (int i = 0; i < 80000; i++) {
            t = t + "S";
            logger.debug(t);
        }
        String endformat = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        logger.info(MessageFormat.format("fixedDelay@@@@@@@@@@@@@{0}", endformat));
    }


//    @Scheduled(fixedRate = 3000)
    public void task1() throws InterruptedException {
        count++;
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.err.println(MessageFormat.format("fixedRate同步==={0}", format));
        if (count == 3) {
//            throw new RuntimeException();
        }
        String endformat = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(MessageFormat.format("fixedRate同步###{0}", endformat));
//        logger.info("task1:{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }


    //    @Scheduled(cron = "0 30 10 ? * *")
    @Scheduled(cron = "${majun.cron}")
    //参数自己根据情况设置
    @SchedulerLock(name = "sugExpiredSMS-lock", lockAtMostFor = 1000*10, lockAtLeastFor = 1000*5)
    public void task2() {
        logger.info("task2###{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    //    @Scheduled(cron = "0 30 10 ? * *")
//    @Scheduled(cron = "${lock.cron}")
    //参数自己根据情况设置
//    @SchedulerLock(name = "majunExpiredSMS-lock", lockAtMostFor = 1000*10, lockAtLeastFor = 1000*5)
    public void task3() {
        logger.info("task3==={}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
