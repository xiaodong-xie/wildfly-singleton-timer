package com.xxd.wildfly.singleton.timer;

import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.ejb.AccessTimeout;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@LocalBean
public class Timer {

    private static final int BATCH_SIZE = 4000;
    private static Logger LOG = LoggerFactory.getLogger(Timer.class);

    private int index = 0;

    @Inject
    private SessionBean sessionBean;

    @Schedule(persistent = false, info = "job-run-periodically", dayOfWeek = "*", hour = "*", minute = "*", second =
        "*/20")
    @AccessTimeout(value = 30L, unit = TimeUnit.SECONDS)
    public void runPeriodically() {
        LOG.info("started to runPeriodically, current index : {}...", index);
        List<User> userList = sessionBean.findAll();
        if (userList.size() != index) {
            LOG.error("Size should be equal to index {}, but size is : {}", index, userList.size());
        } else {
            LOG.info("user list size is : {}", userList.size());
        }
        try {
            Thread.sleep(21000);
        } catch (InterruptedException e) {
            LOG.warn("timer interrupted...", e);
            Thread.currentThread().interrupt();
        }

        for (int i = index; i < index + BATCH_SIZE; i++) {
            User user = new User("xxd-" + i);
            sessionBean.create(user);
        }
        index = index + BATCH_SIZE;
        // uncomment the following 2 lines will fix the issue...
        // userList = sessionBean.findAll();
        // LOG.info("before finishing, user list size is : {}", userList.size());
        LOG.info("finished to runPeriodically...");
    }

}
