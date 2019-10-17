import javafx.application.Application;
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
//import javafx.event.WindowEvent;

import javafx.animation.AnimationTimer;

public class MainUI extends Application {
    // fields
    
    static AnchorPane root;
    
    private Label myLabel;
    private TextField departureTime;
    private TextField authority;
    private TextField trainName;
    private TextField speed;

    private TrackControllerMain tkcm = null;
    private TrackController tkc = null;

    private TrainController tnc = null;

    private TkM tkm = null;
    //private TrackModel tkm = null;
    private CTC_GUI ctcg = null;
    private CTC     ctc = null;
    //private CTC ctcg = null;
    private TrainModel tnm = null;
    //
   public static void main(String[] args) {
        // launch CTC 
        //CTC newCTC = new CTC();
        // launch the app
        launch();
    }
    
    @Override
    public void start(Stage stage) {
        tkm = new TkM(); // initialize track controller

        tkcm = new TrackControllerMain(); // should this be elsewhere?
        tkc = tkcm.createTrackController("plc",null,tkm);


        // Create button
        Button CTC = new Button("CTC");
        // Register the event handler
        CTC.setOnAction(new CTCButtonHandler());

        // Create button
        Button trackController = new Button("Track Controller");
        // Register the event handler
        trackController.setOnAction(new TrackControllerButtonHandler());
        // Create button
        Button trackModel = new Button("Track Model");
        // Register the event handler
        trackModel.setOnAction(new TrackModelButtonHandler());

        // Create button
        Button trainModel = new Button("Train Model");
        // Register the event handler
        trainModel.setOnAction(new TrainModelButtonHandler());

        // Create button
        Button trainController = new Button("Train Controller");
        // Register the event handler
        trainController.setOnAction(
                new TrainControllerButtonHandler()
        );

        // Put the HBox, dispatchT, and myLabel in a VBox
        VBox vbox = new VBox(10, CTC, trackController, trackModel, trainModel, trainController);
        // set the VBox's alignment to center
        vbox.setAlignment(Pos.CENTER);

        // Create a scene with the VBox as its root node
        Scene scene = new Scene(vbox,300,200);
        stage.setTitle("Train Sim Home Page");
        stage.setScene(scene);

        //final long startNanoTime = System.nanoTime();

        /*new AnimationTimer() { // anonymous animation timer
            public void handle(long currentNanoTime) {
                double deltaT = (currentNanoTime - startNanoTime)/1000000000.0;

                // update all modules in succession
                //ctc.update()
                //tkc.update()
                //tkm.update()
                //tnm.update()
                //tnc.update()
                //System.out.println("here");
            }
        }.start(); */


        //stage.setOnCloseRequest(new closeWindowHandler());
        stage.show();

    }

    /**
     * Event handler class for CTC button.
     */
    class CTCButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            Stage newWindow = new Stage();
            if (ctc == null)
                ctc = new CTC(tkcm);
            if (ctcg == null)
                ctcg = new CTC_GUI(ctc,newWindow);
            ctcg.start(newWindow);
        }
    }
    
    /**
     * Event handler class for Track Controller button.
     */
    class TrackControllerButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            Stage newWindow = new Stage();
            System.out.println(tkc.toString());
            tkc.showGUI(newWindow);
        }
    }

    /**
     * Event handler class for Track model button.
     */
    class TrackModelButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            Stage newWindow = new Stage();
            if (tkm == null)
                tkm = new TkM();
            tkm.showGUI(newWindow);

        }
    }

    /**
     * Event handler class for Train model button.
     */
    class TrainModelButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
             Stage newWindow = new Stage();
            if (ctcg != null)
                tnm = ctcg.getTrainModel();
            if (tnm == null)
                tnm = new TrainModel();
            if (tnm != null)
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
            if (tnc == null && tnm == null)
                tnc = new TrainController();
            else if (tnc == null)
                tnc = tnm.TNC;
            if (tnc != null)
                tnc.showGUI(newWindow);
        }
    }

    /*
    class closeWindowHandler implements EventHandler<WindowEvent> {
        @Override
        public void handle(WindowEvent event) {
            stop();
        }
    }*/
}
