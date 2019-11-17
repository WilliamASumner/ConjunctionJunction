import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;
import javafx.stage.FileChooser;

import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.geometry.Insets;

public class TrackControllerGUI extends Application {

    private TrackControllerMain tkcm;
    private TrackController CurrentController;
    private String CurrentBlock;
    private String Occupancy;
    private String Status;
    private String Line;
    private String mode;
    private Boolean Light1;
    private Boolean Light2;
    private Boolean Light3;
    private String SwitchState;
    private String CrossState;
    private String BlockStatus;
    private String PLCFile;
    private Boolean OverrideOccupied;
    private Block currentBlock;

    private String BlockAuthority = "";
    private double BlockSpeed = 0.0;
    String status = "";


    private Label AuthorityVal;
    private Label SpeedVal;
    private Label StatusVal;
    private Label OccupancyVal;

    private Scene scene = null;
    private Stage currentStage = null;

    public TrackControllerGUI(TrackControllerMain m, TrackController tkc) {
        tkcm = m;
        CurrentController = tkc;
        setup();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void setup() {

        final FileChooser fileChooser = new FileChooser();

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


        String[] ControllerNames = tckm.GetControllerNames();
        ObservableList<String> ControllerOptions = FXCollections.observableArrayList();
        for (String option: ControllerNames) {
            ControllerOptions.addAll(option);
        }
        @SuppressWarnings("unchecked")
        ComboBox controllerBox = new ComboBox(ControllerOptions);
        controllerBox.getSelectionModel().selectFirst();


        String[] BlockNames = tkc.GetControlledBlocks();//,"2","3","4","5"};
        ObservableList<String> BoxOptions = FXCollections.observableArrayList();
        for (String option: BlockNames) {
            BoxOptions.addAll(option);
        }

        @SuppressWarnings("unchecked")
        ComboBox blockBox = new ComboBox(BoxOptions);
        blockBox.getSelectionModel().selectFirst();

        ObservableList<String> ModeOptions = FXCollections.observableArrayList("Automatic","Manual");
        @SuppressWarnings("unchecked")
        ComboBox modeBox = new ComboBox(ModeOptions);
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

        Label LineInfo = new Label("Line: " + tkc.GetLine());
        Label OccupancyLabel = new Label("Occupancy: ");
        OccupancyVal = new Label("Unoccupied");

        CheckBox OccupancyCheckBox = new CheckBox("Override to Occupied");
        OccupancyCheckBox.setIndeterminate(false); // only true/false

        Label AuthorityLabel = new Label("Authority: ");
        Label SpeedLabel = new Label("Speed: ");
        AuthorityVal = new Label(BlockAuthority);
        SpeedVal = new Label(String.valueOf(BlockSpeed));

        Label StatusLabel = new Label("Status: ");
        StatusVal = new Label(status);

        HBox AuthBox = new HBox();
        AuthBox.getChildren().addAll(AuthorityLabel,AuthorityVal);
        HBox SpeedBox = new HBox();
        SpeedBox.getChildren().addAll(SpeedLabel,SpeedVal);

        HBox OccBox = new HBox();
        OccBox.getChildren().addAll(OccupancyLabel,OccupancyVal);

        HBox StatusBox = new HBox();
        StatusBox.getChildren().addAll(StatusLabel,StatusVal);

        VBox GeneralInfo = new VBox();
        GeneralInfo.setPadding(new Insets(10));
        GeneralInfo.setSpacing(8);
        GeneralInfo.getChildren().addAll(LineInfo,OccBox,OccupancyCheckBox); // upper right
        GeneralInfo.getChildren().addAll(AuthBox,SpeedBox,StatusBox); // upper right

        GridPane.setConstraints(GeneralInfo,1,0);

        root.getChildren().addAll(GeneralInfo); // upper right


        String projectBaseDir = "file:"+System.getProperty("user.dir") + "/src"; // get base dir
        String imgPath = projectBaseDir + "/TkC/switch-greyed-out.png";
        Image SwitchImage = new Image(imgPath);

        ImageView switchImageView = new ImageView(SwitchImage);
        switchImageView.setFitWidth(160);
        switchImageView.setFitHeight(120);
        GridPane.setConstraints(switchImageView,0,1);

        root.getChildren().addAll(switchImageView); // middle left

        imgPath = projectBaseDir + "/TkC/crossing-greyed-out.png";
        Image CrossingImage = new Image(imgPath);
        ImageView crossImageView = new ImageView(CrossingImage);
        crossImageView.setFitWidth(160);
        crossImageView.setFitHeight(120);
        GridPane.setConstraints(crossImageView,1,1);
        root.getChildren().addAll(crossImageView); // middle right

        Button importButton = new Button("Import PLC");
        importButton.setPrefWidth(400);
        importButton.setPrefHeight(200);
        importButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                fileChooser.setTitle("Choose a PLC Program");
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("PLC Program","*.plc"),
                        new FileChooser.ExtensionFilter("All Files","*.*")
                );

                File file = fileChooser.showOpenDialog(currentStage);
                if (file != null) {
                    try {
                        Desktop.getDesktop().open(file);
                    } catch (IOException ex) {
                        System.out.println("Error: could not open file");
                    }
                }
            }
        });
        GridPane.setConstraints(importButton,0,2);

        root.getChildren().addAll(importButton); // lower left

        scene = new Scene(root, 860,480);

        //root.getChildren().addAll(,l,l2); // lower right

    }


    @Override // not sure what this does?
    public void start(Stage primaryStage) { // entry point for all apps
        currentStage = primaryStage;
        if (scene == null)
            setup();

        primaryStage.setTitle(tkc.GetName()); // container for all of it

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                tkcm.remove(this); // remove this TrackControllerGUI
            }
        }
        
        primaryStage.setScene(scene); // content container
        primaryStage.show();
    }

    public void setIsOccupied(boolean isOccupied) {
        /// 
    }
    public void setSpeed(double speed) {
    }
    public void setAuthority(String authority) {
    }


    void update() {
        AuthorityVal.setText(currentBlock.getAuthority());
        SpeedVal.setText(String.valueOf(currentBlock.getSpeed()));
        StatusVal.setText(currentBlock.getStatus());
        OccupancyVal.setText(currentBlock.getOccupancy());

        if (currentBlock.type == "switch") {
            // update image accordingly
        } else {
            // grey out
        }

        if (currentBlock.type == "crossing" {
        } else {
            //grey out
        }
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
