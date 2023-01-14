package com.svalero.practicas.task;

import javafx.concurrent.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClockTask extends Task<Integer> {

    private DateTimeFormatter dtf; //Clase java para el formato tiempo

    private static final Logger logger = LogManager.getLogger(ClockTask.class); //Para disponer de un log de las Cronometro

    /**
     * Constructor para dar formato a la tiempo
     */
    public ClockTask() {
        dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    }

    @Override
    protected Integer call() throws Exception {
        logger.trace("Hora iniciada");

        while (true) {
            Thread.sleep(1000);
            LocalDateTime now = LocalDateTime.now();
            updateMessage(dtf.format(now));
        }
    }

}
