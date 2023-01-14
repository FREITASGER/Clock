package com.svalero.practicas.controller;

import com.svalero.practicas.task.TimerTask;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class TimerController implements Initializable {

    @FXML
    private Label lbCount;
    @FXML
    private Button btResume;
    @FXML
    private Button btStop;
    @FXML
    private Button btRestart;

    private TimerTask timerTask;
    private int atras;
    private static final Logger logger = LogManager.getLogger(TimerController.class);

    public TimerController(int atras) {
        this.atras = atras;
    }

    @FXML
    public void stopTimer() {
        if (this.timerTask != null)
            this.timerTask.cancel();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.timerTask = new TimerTask(atras);
        this.lbCount.setText(String.valueOf(atras));
        this.timerTask.messageProperty()
                .addListener((observableValue, oldValue, newValue) -> this.lbCount.setText(newValue));


        timerTask.stateProperty().addListener((observableValue, oldState, newState) -> {
            System.out.println(observableValue.toString());
            if (newState == Worker.State.SUCCEEDED) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("El temporizadr ha terminado");
                alert.show();
                this.lbCount.setStyle("-fx-text-fill: red;");
            }
        });
        new Thread(this.timerTask).start(); //Se crea el Thread con la tarea de descarga
    }
}
