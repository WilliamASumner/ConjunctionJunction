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

public class MainUI extends Application {
    // fields
    
    static AnchorPane root;
    
    private Label myLabel;
    private TextField departureTime;
    private TextField authority;
    private TextField trainName;
    private TextField speed;

    private TrackControllerGUI tkcg = null;

    private TrackControllerMain tkc = null;
    //private TrackModel tkm = null;
    //private CTC ctc = null;
    
    public static void main(String[] args) {
        // launch CTC 
        //CTC newCTC = new CTC();
        // launch the app
        launch();
    }
    
    @Override
    public void start(Stage stage) {
        
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
        trainController.setOnAction(new TrainControllerButtonHandler());
        
        // Put the HBox, dispatchT, and myLabel in a VBox
        VBox vbox = new VBox(10, CTC, trackController, trackModel, trainModel, trainController);
        // set the VBox's alignment to center
        vbox.setAlignment(Pos.CENTER);
        
        // Create a scene with the VBox as its root node
        Scene scene = new Scene(vbox);//(new StackPane(l), 640, 480);
        stage.setTitle("Train Sim Home Page");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Event handler class for CTC button.
     */
    class CTCButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            Stage newWindow = new Stage();
            System.out.println("CTC Office");
            //ctcg = new CTC_GUI();
            //ctcg.start(newWindow);
        }
    }
    
    /**
     * Event handler class for Track Controller button.
     */
    class TrackControllerButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            Stage newWindow = new Stage();
            System.out.println("Track Controller");
            tkcg = new TrackControllerGUI();
            tkcg.start(newWindow);
        }
    }

    /**
     * Event handler class for Track model button.
     */
    class TrackModelButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            Stage newWindow = new Stage();
            System.out.println("Track Model");
            //tkmg = new TrackControllerGUI();
            //tkmg.start(newWindow);

        }
    }

    /**
     * Event handler class for Train model button.
     */
    class TrainModelButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
             Stage newWindow = new Stage();
            System.out.println("Train Model");
            //tnmg = new TrackControllerGUI();
            //tnmg.start(newWindow);

        }
    }

    /**
     * Event handler class for Train model button.
     */
    class TrainControllerButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            Stage newWindow = new Stage();
            System.out.println("Train Controller");
            //tncg = new TrainControllerGUI();
            //tncg.start(newWindow);

        }
    }   
}
