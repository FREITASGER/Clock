package com.svalero.practicas.task;

import javafx.concurrent.Task;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class StopWatchTask extends Task<Integer> {
    private int timerClock;

    private static final Logger logger = LogManager.getLogger(StopWatchTask.class);

    public StopWatchTask() {
        this.timerClock = 0;
    }

    @Override
    protected Integer call() throws Exception {

        logger.trace("Cronometro iniciado");

        while (true) {
            Thread.sleep(1000);
            this.timerClock++;
            updateMessage(String.valueOf(this.timerClock));
        }
    }
}
