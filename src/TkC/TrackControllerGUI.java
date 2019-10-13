import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.geometry.Insets;

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

        TrackControllerMain trackControl = new TrackControllerMain();

        GridPane root = new GridPane();

        // define UI Layout
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        col1.setHalignment(HPos.CENTER);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        col2.setHalignment(HPos.CENTER);
        root.getColumnConstraints().addAll(col1,col2);

        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(33);
        row1.setValignment(VPos.CENTER);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(34);
        row2.setValignment(VPos.CENTER);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(33);
        root.getRowConstraints().addAll(row1,row2,row3);


        String[] ControllerNames = {"G1","G2","G3"};
        ComboBox controllerBox = new ComboBox();
        for (String option: ControllerNames) {
            controllerBox.getItems().add(option);
        }
        controllerBox.getSelectionModel().selectFirst();


        String[] BlockNames = {"1","2","3","4","5"};
        ComboBox blockBox = new ComboBox();
        for (String option: BlockNames) {
            blockBox.getItems().add(option);
        }
        blockBox.getSelectionModel().selectFirst();

        String[] modes = {"Automatic","Manual"};
        ComboBox modeBox = new ComboBox();
        for (String option: modes) {
            modeBox.getItems().add(option);
        }
        modeBox.getSelectionModel().selectFirst();

        /*GridPane.setConstraints(controllerBox,0,0);
        GridPane.setConstraints(blockBox,0,1);
        GridPane.setConstraints(modeBox,0,2);*/

        VBox MainControls = new VBox();
        MainControls.setPadding(new Insets(10));
        MainControls.setSpacing(8);
        MainControls.getChildren().addAll(controllerBox,blockBox,modeBox); // upper left

        GridPane.setConstraints(MainControls,0,0);

        root.getChildren().addAll(MainControls); // upper left

        Label LineInfo = new Label("Line: ");
        Label OccupancyInfo = new Label("Occupancy: ");
        Label StatusInfo = new Label("Status: ");

        CheckBox OccupancyCheckBox = new CheckBox("Override to Occupied");
        OccupancyCheckBox.setIndeterminate(false); // only true/false



        VBox GeneralInfo = new VBox();
        GeneralInfo.setPadding(new Insets(10));
        GeneralInfo.setSpacing(8);
        GeneralInfo.getChildren().addAll(LineInfo,OccupancyInfo,StatusInfo,OccupancyCheckBox); // upper left

        GridPane.setConstraints(GeneralInfo,1,0);

        root.getChildren().addAll(GeneralInfo); // upper left

        //root.getChildren().addAll(,l,l2); // upper right

        //root.getChildren().addAll(,l,l2); // middle left
        //root.getChildren().addAll(,l,l2); // middle right

        //root.getChildren().addAll(,l,l2); // lower left
        //root.getChildren().addAll(,l,l2); // lower right


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
