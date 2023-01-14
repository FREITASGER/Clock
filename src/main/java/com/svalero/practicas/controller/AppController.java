package com.svalero.practicas.controller;


import com.svalero.practicas.task.ClockTask;
import com.svalero.practicas.util.R;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    /**
     * Declaramos todas los componentes de la pantalla creada en SceneBuilder
     */
    @FXML
    private Label lbClock;
    @FXML
    private Button btNewTimer;
    @FXML
    private Button btAddCrono;
    @FXML
    private Button btLog;
    @FXML
    private TextField tfTimer;
    @FXML
    private TextField tfProgram;
    @FXML
    private TabPane tpCrono;

//    private Map<String, StopWatchController> allCrono; // Creamos un mapa para guardar todas los cronometros
    private ClockTask clockTask;
    private int numero = 0;
    private int atras;

    /**
     * Método para inicializar el Mapa de las cronometros de TimerController
     */
    public AppController() {

    }

    @FXML
    public void startCrono(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(); //Creamos un objeto FMXLloader que se encargará de Montarnos la interfaz de lo otra ventana
            loader.setLocation(R.getUI("clock.fxml")); // Le pasamos la localización de la ventana diseñada con JavaFx
            numero++;

            StopWatchController stopWatchController = new StopWatchController(); //Creamos su propio controler desde su clase DownloadController para gestionar los botones y demás cosas
            loader.setController(stopWatchController); //cargamos el controller
            //Todo revisar si crearé un VBox o el padre será de otro tipo
            VBox timerBox = loader.load(); //En este caso el padre de la ventana es un Vbox en JavaFx

            tpCrono.getTabs().add(new Tab("cronometro numero " + numero , timerBox)); //Lo añadimos al panel de ventana de "gesDescargaHome.fxml"y añadimos una PESTAÑA por cada descarga que le damos.
            //Se puede hacer desde Scence en las propiedades del TabPane ->TabClosingPolicy poner ALL_TABS
//            timerBox.getScene().getWindow(); //Parar cerrar cada pestaña creada en el Tab

//            allCrono.put("cronometro numero " + numero , stopWatchController); //Cada vez que lancemos una descarga lo añadimos al mapa para poder cancelarlas todas, tenemos cada url de descarga asociado al objeto que se crea por cada descarga

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void startTimer(Event event) {
        try {
        atras = Integer.parseInt(tfTimer.getText());

        FXMLLoader loader = new FXMLLoader(); //Creamos un objeto FMXLloader que se encargará de Montarnos la interfaz de lo otra ventana
        loader.setLocation(R.getUI("clock.fxml")); // Le pasamos la localización de la ventana diseñada con JavaFx
        numero--;

        TimerController timerController = new TimerController(atras);
        loader.setController(timerController);

        VBox timerBox = loader.load(); //En este caso el padre de la ventana es un Vbox en JavaFx

        tpCrono.getTabs().add(new Tab("Cuenta Atras numero " + numero , timerBox)); //Lo añadimos al panel de ventana de "gesDescargaHome.fxml"y añadimos una PESTAÑA por cada descarga que le damos.
        //Se puede hacer desde Scence en las propiedades del TabPane ->TabClosingPolicy poner ALL_TABS
//        timerBox.getScene().getWindow(); //Parar cerrar cada pestaña creada en el Tab

//            allCrono.put("cronometro numero " + numero , stopWatchController); //Cada vez que lancemos una descarga lo añadimos al mapa para poder cancelarlas todas, tenemos cada url de descarga asociado al objeto que se crea por cada descarga

    } catch (IOException ioe) {
        ioe.printStackTrace();
    }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("holaaaa");
        this.clockTask = new ClockTask();
        this.lbClock.setText("hola");
        this.clockTask.messageProperty()
                .addListener((observableValue, oldValue, newValue) -> this.lbClock.setText(newValue));

        new Thread(this.clockTask).start(); //Se crea el Thread con la tarea de descarga
    }
}
