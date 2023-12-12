package com.vds.springbootx.service;

import com.vds.demofactories.FactoryBean;
import com.vds.springbootx.entity.JournalEntry;
import com.vds.springbootx.repository.JournalEntryRepository;
import com.vds.springbootx.utils.CustomLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
@Slf4j
public class JournalEntryServiceSlf4j {
    private static final long MAX_WAIT_TIME = 10000L;
    private final JournalEntryRepository journalEntryRepository;

    public JournalEntryServiceSlf4j(FactoryBean factoryBean, JournalEntryRepository journalEntryRepository,
                                    @Value("${message}") String message) {
        this.journalEntryRepository = journalEntryRepository;
        initData();
        log.info("INFOINFO");
        log.warn("WARNINGWARNING");
        log.debug("DEBUGDEBUG");
        log.error("ERRORERROR");
    }

    private void initData() {
        try {
            this.save(
                    new JournalEntry("title1", "summary1", "1/1/2011"),
                    new JournalEntry("title2", "summary2", "2/2/2011"),
                    new JournalEntry("title3", "summary3", "3/3/2011"),
                    new JournalEntry("title4", "summary4", "4/4/2011")
            );
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void save(JournalEntry... journalEntries) {
        for (JournalEntry journalEntry : journalEntries) {
            journalEntryRepository.save(journalEntry);
        }
    }

    public List<JournalEntry> getAllJournals() {
        return journalEntryRepository.findAll();
    }

    public List<JournalEntry> getJournalsByTitleContains(String title) {
        return journalEntryRepository.findAllByTitleContaining(title);
    }

    protected static void validatePool(int i, ThreadPoolTaskExecutor taskExecutor) {
        long startTime = System.currentTimeMillis();
        if (taskExecutor.getActiveCount() >= 2) {
//            System.out.println("validatePool --- wait for #" + i);
            sleep(100);
            if (System.currentTimeMillis() - startTime > MAX_WAIT_TIME) {
                throw new RuntimeException("validatePool --- wait timeout");
            }
        }
        System.out.println("validatePool --- done #" + i);
    }

    private static ThreadPoolTaskExecutor initPool() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(100);
        taskExecutor.setQueueCapacity(1000);
        taskExecutor.setMaxPoolSize(500);
        taskExecutor.setRejectedExecutionHandler((runnable, executor) -> {
            try {
                log.info("ThreadPoolTaskExecutor current size {}", executor.getQueue().size());
                executor.getQueue().put(runnable);
                log.info("ThreadPoolTaskExecutor rejected task so put it to queue again "
                                + "ThreadPoolTaskExecutor: {}, orderCompletedCount: {}, queue: {}",
                        executor.getActiveCount(),
                        executor.getCompletedTaskCount(),
                        executor.getQueue().size());
            } catch (InterruptedException ex) {
                log.error("ThreadPoolTaskExecutor rejected exception {}", ex.getMessage(), ex);
                Thread.currentThread().interrupt();
            }
        });
        taskExecutor.initialize();
        return taskExecutor;
    }

    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            log.error("Thread.sleep exception ", e);
        }
    }

    public static void main(String[] args) {
        ThreadPoolTaskExecutor taskExecutor = initPool();
        for (int i = 0; i < 3000; ++i) {
//            validatePool(i, taskExecutor);
            int finalI = i;
            taskExecutor.submit(() -> {
                log.info("taskExecutor execute task #" + finalI +
                        " // active: " + taskExecutor.getActiveCount() +
                        " // q: " + taskExecutor.getThreadPoolExecutor().getQueue().size());
                sleep(10000);
                log.info("taskExecutor task #" + finalI + " done");
            });
            log.info("getCompletedTaskCount {}", taskExecutor.getThreadPoolExecutor().getCompletedTaskCount());
        }
    }
}
