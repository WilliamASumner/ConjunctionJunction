import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TrackControllerGUI extends Application {

    TrackController CurrentController;
    String CurrentBlock;
    String Occupancy;
    String Status;
    String Line;
    String mode;
    Boolean Light1;
    Boolean Light2;
    Boolean Light3;
    String SwitchState;
    String CrossState;
    String BlockStatus;
    String PLCFile;
    Boolean OverrideOccupied;


    public static void main(String[] args) {
        launch(args);
    }

    @Override // not sure what this does?
    public void start(Stage primaryStage) { // entry point for all apps
        primaryStage.setTitle("Track Controller GUI"); // container for all of it
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello world!");
            }
        });

        TrackControllerMain trackControl = new TrackControllerMain();

        GridPane root = new GridPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 860,480)); // content container
        primaryStage.show();
    }

    void draw() {
        return;
    }
    public void ChangeSwitchState() {
        return;
    }
    public void ChangeBlockStatus() {
        return;
    }
    public void ChangeCrossingState() {
        return;
    }

    public void ChangePLC(String plc) {
        return;
    }
}

