package com.svalero.practicas.controller;

import com.svalero.practicas.task.StopWatchTask;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class StopWatchController implements Initializable {
    @FXML
    private Label lbCount;
    @FXML
    private Button btResume;
    @FXML
    private Button btStop;
    @FXML
    private Button btRestart;

    //Entero para contar segundos

    private StopWatchTask stopWatchTask;

    private static final Logger logger = LogManager.getLogger(StopWatchController.class);

    public StopWatchController() {

    }

    @FXML
    public void stopTimer() {
        if (this.stopWatchTask != null)
            this.stopWatchTask.cancel();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.stopWatchTask = new StopWatchTask();

        this.stopWatchTask.messageProperty()
                .addListener((observableValue, oldValue, newValue) -> this.lbCount.setText(newValue));

        new Thread(this.stopWatchTask).start(); //Se crea el Thread con la tarea de descarga
    }
}
