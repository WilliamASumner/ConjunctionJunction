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

import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.animation.Timeline;
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
    private Button speedUpButton = null;
    private Button speedDownButton = null;

    private Timeline timeline;
    private int timeMultiplier = 1;
    private Label multiplierDisplay;

    private boolean isStopped = false;

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

        speedDownButton = new Button("Slow down");
        speedDownButton.setOnAction(new slowDownHandler());

        speedUpButton = new Button("Speedup");
        speedUpButton.setOnAction(new speedUpHandler());

        HBox timeControls = new HBox(speedDownButton,stopButton,speedUpButton);
        timeControls.setAlignment(Pos.CENTER);

        multiplierDisplay = new Label("1x");


        // Put the HBox, dispatchT, and myLabel in a VBox
        VBox vbox = new VBox(10,programTitle,programTitle2, CTC, trackController, trackModel, trainModel, trainController,timeControls,multiplierDisplay);
        // set the VBox's alignment to center
        vbox.setAlignment(Pos.CENTER);

        stage.setOnCloseRequest(new closeWindowHandler());

        // Create a scene with the VBox as its root node
        Scene scene = new Scene(vbox,300,500);
        stage.setTitle("Train Sim Home Page");
        stage.setScene(scene);

        timeMultiplier = 1;
        updateTimeline();

        //final long startNanoTime = System.nanoTime();

        stage.show();

    }

    /**
     * Update timer
     */
    private void updateTimeline() {
        if (timeline != null)
            timeline.stop();
        timeline = new Timeline(
            new KeyFrame(Duration.millis(1000.0/timeMultiplier), // default time is 1 second per update
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    ctc.update();
                    tkcm.update();
                    tkm.update();
                    tnm.update();
                    tnc.update();
                }
            })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Event handler class for CTC button.
     */
    class CTCButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            Stage newWindow = new Stage();
            newWindow.setTitle("CTC Module");
            newWindow.setWidth(1100);
            newWindow.setHeight(630);
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
            if (isStopped) {
                stopButton.setText("Stop");
                timeline.play();
            } else {
                stopButton.setText("Start");
                timeline.stop();
            }
            isStopped = !isStopped;
        }
    }

    class slowDownHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            switch (timeMultiplier) {
                case 4:
                    timeMultiplier = 2;
                    break;
                case 10:
                    timeMultiplier = 4;
                    break;
                case 20:
                    timeMultiplier = 10;
                    break;
                case 25:
                    timeMultiplier = 20;
                    break;
                case 50:
                    timeMultiplier = 25;
                    break;
                case 100:
                    timeMultiplier = 50;
                    break;
                default: // 1 -> 1, 2 -> 1
                    timeMultiplier = 1;
            }
            multiplierDisplay.setText(timeMultiplier+"x");
            updateTimeline();
        }
    }

    class speedUpHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            switch (timeMultiplier) {
                case 1:
                    timeMultiplier = 2;
                    break;
                case 2:
                    timeMultiplier = 4;
                    break;
                case 4:
                    timeMultiplier = 10;
                    break;
                case 10:
                    timeMultiplier = 20;
                    break;
                case 20:
                    timeMultiplier = 25;
                    break;
                case 25:
                    timeMultiplier = 50;
                    break;
                case 50:
                    timeMultiplier = 100;
                    break;
                case 100:
                    timeMultiplier = 100;
                    break;
                default:
                    timeMultiplier = 1;
            }
            multiplierDisplay.setText(timeMultiplier+"x");
            updateTimeline();
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
