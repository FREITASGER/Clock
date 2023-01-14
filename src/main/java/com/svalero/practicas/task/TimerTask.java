package com.svalero.practicas.task;

import javafx.concurrent.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TimerTask extends Task<Integer> {

    private int timerClock;
    private static final Logger logger = LogManager.getLogger(TimerTask.class); //Para disponer de un log de las Descargas

    public TimerTask(int atras) {
        this.timerClock = atras;
    }


    @Override
    protected Integer call() throws Exception {
        logger.trace("Cuenta atras iniciado");
        updateProgress(0, 1);
        do {
            Thread.sleep(1000);
            this.timerClock--;
            updateMessage(String.valueOf(this.timerClock));
        } while (this.timerClock > 0);
        updateProgress(1, 1);

        return null;
    }
}
