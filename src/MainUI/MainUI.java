import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.WindowEvent;

import javafx.animation.AnimationTimer;
import java.io.FileNotFoundException;

public class MainUI extends Application {
    
    static AnchorPane root;
    
    private Label myLabel;
    private TextField departureTime;
    private TextField authority;
    private TextField trainName;
    private TextField speed;

    private TrackControllerMain tkcm = null;

    private TrainControllerMain tnc = null;
    private TrainModelMain tnm = null;
    private Button CTC = null;
    private Button trackModel = null;
    private Button trainModel = null;
    private Button trainController = null;
    private Button trackController = null;
    private Button stopButton = null;

    private boolean updateBoolean = true;

    private TkM tkm = null;
    private CTC_GUI ctcg = null;
    private CTC     ctc = null;

   public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage)  {
        // Module instantiations
        tnc = new TrainControllerMain();
        tnm = new TrainModelMain();
        tkcm = new TrackControllerMain();
        tkm = new TkM(tnc); // initialize track model
        ctc = new CTC();

        // Track Controller Connections
        tkcm.setTrackModel(tkm);
        tkcm.createControllers();
        tkcm.setCTC(ctc);

        //CTC Connections
        ctc.addTrackModel(tkm);
        ctc.addTrackController(tkcm);

        //Track Model Connections
        tkm.addTrackController(tkcm);

        Label programTitle = new Label("Conjunction Junction");
        Label programTitle2= new Label("Train Simulation Software");
        programTitle.setAlignment(Pos.CENTER);

        // Create button
        CTC = new Button("CTC");
        // Register the event handler
        CTC.setOnAction(new CTCButtonHandler());
        //CTC.setDisable(true);

        // Create button
        trackController = new Button("Track Controller");
        // Register the event handler
        trackController.setOnAction(new TrackControllerButtonHandler());
        //trackController.setDisable(true);
        // Create button
        trackModel = new Button("Track Model");
        // Register the event handler
        trackModel.setOnAction(new TrackModelButtonHandler());

        // Create button
        trainModel = new Button("Train Model");
        // Register the event handler
        trainModel.setOnAction(new TrainModelButtonHandler());
        //trainModel.setDisable(true);

        // Create button
        trainController = new Button("Train Controller");
        //trainController.setDisable(true);
        // Register the event handler
        trainController.setOnAction(
                new TrainControllerButtonHandler()
        );

        stopButton = new Button("Stop");
        stopButton.setOnAction(new stopHandler());

        // Put the HBox, dispatchT, and myLabel in a VBox
        VBox vbox = new VBox(10,programTitle,programTitle2, CTC, trackController, trackModel, trainModel, trainController,stopButton);
        // set the VBox's alignment to center
        vbox.setAlignment(Pos.CENTER);

        // Create a scene with the VBox as its root node
        Scene scene = new Scene(vbox,300,500);
        stage.setTitle("Train Sim Home Page");
        stage.setScene(scene);

        //final long startNanoTime = System.nanoTime();

        new AnimationTimer() { // anonymous animation timer
            public void handle(long currentNanoTime) {
                //double deltaT = (currentNanoTime - startNanoTime)/1000000000.0;
                if (updateBoolean) {

                    // update all modules in succession
                    ctc.update();
                    tkcm.update();
                    tkm.update();
                    tnm.update();
                    tnc.update();
                    try {
                        Thread.sleep(1); // FIXME, add variable timing
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }.start();


        stage.setOnCloseRequest(new closeWindowHandler());
        stage.show();

    }

    /**
     * Event handler class for CTC button.
     */
    class CTCButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            Stage newWindow = new Stage();
            ctc.showGUI(newWindow);
        }
    }
    
    /**
     * Event handler class for Track Controller button.
     */
    class TrackControllerButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            Stage newWindow = new Stage();
            tkcm.showGUI(newWindow);
        }
    }

    /**
     * Event handler class for Track model button.
     */
    class TrackModelButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            Stage newWindow = new Stage();
            tkm.showGUI(newWindow);

        }
    }

    /**
     * Event handler class for Train model button.
     */
    class TrainModelButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            Stage newWindow = new Stage(); // new window
            tnm.showGUI(newWindow);
        }
    }

    /**
     * Event handler class for Train model button.
     */
    class TrainControllerButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            Stage newWindow = new Stage();
            tnc.showGUI(newWindow);
        }
    }

    class stopHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            if (updateBoolean)
                stopButton.setText("Start");
            else
                stopButton.setText("Stop");
            updateBoolean = !updateBoolean;
        }
    }

    class closeWindowHandler implements EventHandler<WindowEvent> {
        @Override
        public void handle(WindowEvent event) {
            System.out.println("Closing...");
            try {
                tkcm.stop(); // stop modules
            } catch (Exception e) {
                System.out.println(e);
            }

            try { // close everything
                Platform.exit();
                System.exit(0);
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }
}
