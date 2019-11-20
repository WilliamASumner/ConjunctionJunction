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
import javafx.scene.layout.Region;
import javafx.scene.layout.Priority;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.Separator;

import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.WindowEvent;

import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;

import java.util.ArrayList;


public class TrackControllerGUI extends Application {

    private String resourceBaseDir = "file:"+System.getProperty("user.dir") + "/imgs"; // get base dir


    private TrackControllerMain tkcm;
    private TrackController CurrentController;
    private Block CurrentBlock;
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

    private String BlockAuthority = "";
    private double BlockSpeed = 0.0;
    String status = "";

    //disable-able gui items
    private HBox sStateBox;
    private HBox cStateBox;
    private HBox bStatusBox;
    private CheckBox OccupancyCheckBox;

    private ImageView switchImageView;
    private ImageView crossImageView;

    // Images
    Image CrossingImageGrey = new Image(resourceBaseDir + "/crossing-greyed-out.png");

    Image CrossingImageDown = new Image(resourceBaseDir + "/crossing-on.png");
    Image CrossingImageUp = new Image(resourceBaseDir + "/crossing-off.png");

    Image SwitchImageGrey = new Image(resourceBaseDir + "/switch-greyed-out.png");
    Image SwitchImageMain = new Image(resourceBaseDir + "/switch-main.png");
    Image SwitchImageFork = new Image(resourceBaseDir + "/switch-fork.png");

    // changable labels
    private Label AuthorityVal;
    private Label SpeedVal;
    private Label StatusVal;
    private Label OccupancyVal;
    private Label currPLC;

    private Scene scene = null;
    private Stage currentStage = null;

    public TrackControllerGUI(TrackControllerMain m, TrackController tkc) {
        tkcm = m;
        CurrentController = tkc;
        CurrentBlock = new Block();//tm.getBlock(tkc.GetControlledBlocks()[0]);
        setup();
        update(); // update GUI accordingly
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void setup() { // called once to create UI

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

        

        String[] ControllerNames = tkcm.GetControllerNames();
        ObservableList<String> ControllerOptions = FXCollections.observableArrayList();
        for (String option: ControllerNames) {
            ControllerOptions.addAll(option);
        }
        ComboBox<String> controllerBox = new ComboBox<String>(ControllerOptions);
        controllerBox.getSelectionModel().select(CurrentController.toString());
        controllerBox.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String newTkc = controllerBox.getSelectionModel().getSelectedItem();
                CurrentController = tkcm.GetController(newTkc);
                tkcm.SetController(newTkc);
            }
        });


        String[] BlockNames = CurrentController.GetControlledBlocks();//,"2","3","4","5"};
        ObservableList<String> BoxOptions = FXCollections.observableArrayList();
        for (String option: BlockNames) {
            BoxOptions.addAll(option);
        }

        ComboBox<String> blockBox = new ComboBox<String>(BoxOptions);
        blockBox.getSelectionModel().selectFirst();
        blockBox.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("selected new block: " + blockBox.getSelectionModel().getSelectedItem());
            }
        });


        ObservableList<String> ModeOptions = FXCollections.observableArrayList("Automatic","Manual");
        ComboBox<String> modeBox = new ComboBox<String>(ModeOptions);
        modeBox.getSelectionModel().selectFirst();
        modeBox.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("selected new mode: " + modeBox.getSelectionModel().getSelectedItem());
            }
        });


        Label ctrl = new Label("Controller: ");
        Label blk = new Label( "Block: ");
        Label mode = new Label("Mode: ");

        Region spacer1 = new Region();
        Region spacer2 = new Region();
        Region spacer3 = new Region();
        HBox.setHgrow(spacer1,Priority.ALWAYS);
        HBox.setHgrow(spacer2,Priority.ALWAYS);
        HBox.setHgrow(spacer3,Priority.ALWAYS);

        HBox controllerHBox = new HBox(ctrl,spacer1,controllerBox);
        HBox blockHBox = new HBox(blk,spacer2,blockBox);
        HBox modeHBox = new HBox(mode,spacer3,modeBox);


        /*GridPane.setConstraints(controllerBox,0,0);
        GridPane.setConstraints(blockBox,0,1);
        GridPane.setConstraints(modeBox,0,2);*/

        VBox MainControls = new VBox();
        MainControls.setPadding(new Insets(10));
        MainControls.setSpacing(8);
        MainControls.getChildren().addAll(controllerHBox,blockHBox,modeHBox); // upper left

        GridPane.setConstraints(MainControls,0,0);

        root.getChildren().addAll(MainControls); // upper left

        Label LineInfo = new Label("Line: " + CurrentController.GetLine());
        Label OccupancyLabel = new Label("Occupancy: ");
        OccupancyVal = new Label("Unoccupied");

        OccupancyCheckBox = new CheckBox("Override to Occupied");
        OccupancyCheckBox.setIndeterminate(false); // only true/false
        OccupancyCheckBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(OccupancyCheckBox.isSelected() ? "checked" : "unchecked");
                //controllerHBox.setDisable(true); // useful for turning off
            }
        });



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


        Separator MiddleDividerLow  = new Separator();
        Separator MiddleDividerHigh = new Separator();
        Separator MiddleDivider     = new Separator();

        MiddleDividerLow.setValignment(VPos.BOTTOM);
        MiddleDividerHigh.setValignment(VPos.TOP);

        MiddleDivider.setHalignment(HPos.RIGHT);
        MiddleDivider.setOrientation(Orientation.VERTICAL);

        GridPane.setColumnSpan(MiddleDividerHigh,2);
        GridPane.setColumnSpan(MiddleDividerLow,2);



        ImageView switchImageView = new ImageView(SwitchImageGrey);
        switchImageView.setFitWidth(160);
        switchImageView.setFitHeight(120);
        //GridPane.setConstraints(switchImageView,0,1); // middle left
        Circle light1 = new Circle();
        light1.setCenterX(100.0f);
        light1.setCenterY(100.0f);
        light1.setRadius(50.0f);
        light1.setFill(Color.GREEN);

        Circle light2 = new Circle();
        light2.setCenterX(100.0f);
        light2.setCenterY(150.0f);
        light2.setRadius(50.0f);
        light2.setFill(Color.RED);


        Circle light3 = new Circle();
        light3.setCenterX(150.0f);
        light3.setCenterY(100.0f);
        light3.setRadius(50.0f);
        light3.setFill(Color.YELLOW);

        VBox vertLights = new VBox(light2,light3);

        HBox LightsAndSwitch = new VBox(light1,switchImageView,light3);

        //root.getChildren().addAll(switchImageView); // middle left

        ImageView crossImageView = new ImageView(CrossingImageGrey);
        crossImageView.setFitWidth(160);
        crossImageView.setFitHeight(120);
        //GridPane.setConstraints(crossImageView,1,1);

        HBox middleHBox = new HBox(switchImageView,MiddleDivider,crossImageView);
        middleHBox.setAlignment(Pos.CENTER);
        middleHBox.setSpacing(80);

        VBox middleVBox = new VBox(MiddleDividerHigh,middleHBox,MiddleDividerLow);

        GridPane.setColumnSpan(middleVBox,2);
        GridPane.setConstraints(middleVBox,0,1);

        //root.getChildren().addAll(crossImageView); // middle right
        root.getChildren().addAll(middleVBox); // middle right

        Label currProgLabel = new Label("Current PLC Program: ");
        currPLC = new Label("None");

        HBox currProgBox = new HBox(currProgLabel,currPLC);
        currProgBox.setAlignment(Pos.CENTER);

        Button importButton = new Button("Import PLC");
        importButton.setPrefWidth(200);
        importButton.setPrefHeight(100);
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
                    } /*catch (ParsingException p) {
                        System.out.println(p);
                    } */ // TODO add this
                }
                currPLC.setText(file.getName());
            }
        });


        VBox PLCBox = new VBox(currProgBox,importButton);
        PLCBox.setAlignment(Pos.CENTER);

        GridPane.setConstraints(PLCBox,0,2);
        root.getChildren().addAll(PLCBox); // lower left

        String[] SwitchStates = {"MAIN","FORK"};
        ObservableList<String> SwStates = FXCollections.observableArrayList();
        for (String option: SwitchStates) {
            SwStates.addAll(option);
        }
        ComboBox<String> SwitchBox = new ComboBox<String>(SwStates);
        SwitchBox.getSelectionModel().selectFirst();
        SwitchBox.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("selected new switch state: " + SwitchBox.getSelectionModel().getSelectedItem());
            }
        });

        String[] CrossingStates = {"UP","DOWN"};
        ObservableList<String> CrStates = FXCollections.observableArrayList();
        for (String option: CrossingStates) {
            CrStates.addAll(option);
        }
        ComboBox<String> CrossBox = new ComboBox<String>(CrStates);
        CrossBox.getSelectionModel().selectFirst();
        CrossBox.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("selected new crossing state: " + CrossBox.getSelectionModel().getSelectedItem());
            }
        });

        String[] BlockStatuses = {"SIGNAL FAILURE", "RAIL FAILURE"};
        ObservableList<String> BStates = FXCollections.observableArrayList();
        for (String option: BlockStatuses) {
            BStates.addAll(option);
        }
        ComboBox<String> BlockBox = new ComboBox<String>(BStates);
        BlockBox.getSelectionModel().selectFirst();
        BlockBox.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("selected new block status: " + BlockBox.getSelectionModel().getSelectedItem());
            }
        });


        Label sw = new Label("  Switch:");
        Label cr = new Label("  Crossing:");
        Label status = new Label("  Block Status:");

        Region spacer4 = new Region();
        Region spacer5 = new Region();
        Region spacer6 = new Region();
        HBox.setHgrow(spacer4,Priority.ALWAYS);
        HBox.setHgrow(spacer5,Priority.ALWAYS);
        HBox.setHgrow(spacer6,Priority.ALWAYS);

        sStateBox = new HBox(sw,spacer4,SwitchBox);
        cStateBox = new HBox(cr,spacer5,CrossBox);
        bStatusBox = new HBox(status,spacer6,BlockBox);

        VBox lowerRight = new VBox(sStateBox,cStateBox,bStatusBox);
        lowerRight.setSpacing(8);

        GridPane.setConstraints(lowerRight,1,2);
        root.getChildren().addAll(lowerRight); // lower right

        scene = new Scene(root, 600,500);

    }


    @Override // not sure what this does?
    public void start(Stage primaryStage) { // entry point for all apps
        currentStage = primaryStage;
        if (scene == null)
            setup();

        primaryStage.setTitle(CurrentController.GetName()); // container for all of it
        primaryStage.setResizable(false);
        TrackControllerGUI thisGUI = this;

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                tkcm.removeGUI(thisGUI); // remove this TrackControllerGUI
            }
        });
        
        primaryStage.setScene(scene); // content container
        primaryStage.show();
    }

    public void setManualMode() {

        sStateBox.setDisable(true);
        cStateBox.setDisable(true);
        bStatusBox.setDisable(true);
    }

    public void setAutomaticMode() {
        sStateBox.setDisable(true);
        cStateBox.setDisable(true);
        bStatusBox.setDisable(true);
    }

    public void setIsOccupied(boolean isOccupied) {
        //CurrentBlock.setIsOccupied(true)
    }

    public void setSpeed(double speed) {
        //CurrentBlock.setSpeed(speed);
    }
    public void setAuthority(String authority) {
        //CurrentBlock.setAuthority(authority);
    }

    public void checkUI(){ //disable/enable elements depending on block
        /*
        if (CurrentBlock.getFailures().size() != 0) // if errors
            StatusVal.setText("BROKEN");
        else
            StatusVal.setText("CLEAR");

        if (CurrentBlock.getIsOccupied() && !OverrideOccupied)  {
            OccupancyVal.setText("OCCUPIED");
            OccupancyCheckBox.setDisable(true); // cant change for train
        }
        else  {
            OccupancyVal.setText("UNOCCUPIED");
            OccupancyCheckBox.setDisable(false);
        }

        if (!mode.equals("AUTO") && CurrentBlock.getType() == BlockType.SWITCHBLOCK) {
                sStateBox.setDisable(false);
        } else {
                sStateBox.setDisable(true);
        }

        if (!mode.equals("AUTO") && CurrentBlock.getType() == BlockType.CROSSBLOCK) {
            cStateBox.setDisable(false);
        } else {
            cStateBox.setDisable(true);
            //grey out
        } */
    }

    public void update() {

        /*AuthorityVal.setText(CurrentBlock.getAuditedAuthority());
        SpeedVal.setText(String.valueOf(CurrentBlock.getAuditedSpeed()));
        checkUI();
        */
    }

    public void ChangeSwitchState(SwitchState s) {
        //CurrentBlock.setSwitchState(s);
    }

    public void ChangeBlockStatus(String failure) {
        //CurrentBlock.addFailure(failure)
        return;
    }

    public void ChangeCrossingState(CrossingState s) {
        //CurrentBlock.setCrossingState(s);
        return;
    }

    public void ChangePLC(String plc) {
        return;
    }
}
