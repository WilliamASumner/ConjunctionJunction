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
    private TrackController currentController;
    private Block currentBlock;
    private String status;
    private String mode;
    private String blockStatus;
    private String PLCFile;
    private boolean overrideOccupied;

    private String blockAuthority = "";
    private double blockSpeed = 0.0;

    //disable-able gui items
    private HBox sStateBox;
    private HBox cStateBox;
    private HBox bStatusBox;
    private CheckBox occupancyCheckBox;

    // changable gui items
    private ImageView switchImageView;
    private ImageView crossImageView;

    private Circle light1;
    private Circle light2;
    private Circle light3;

    private ComboBox<String> blockBox;
    private ComboBox<String> crossBox;
    private ComboBox<String> switchBox;

    // Images
    Image crossImageGrey = new Image(resourceBaseDir + "/crossing-greyed-out.png");

    Image crossImageDown = new Image(resourceBaseDir + "/crossing-on.png");
    Image crossImageUp = new Image(resourceBaseDir + "/crossing-off.png");

    Image switchImageGrey = new Image(resourceBaseDir + "/switch-greyed-out.png");
    Image switchImageMain = new Image(resourceBaseDir + "/switch-main.png");
    Image switchImageFork = new Image(resourceBaseDir + "/switch-fork.png");

    // changable labels
    private Label authorityVal;
    private Label speedVal;
    private Label statusVal;
    private Label occupancyVal;
    private Label currPLC;

    private Scene scene = null;
    private Stage currentStage = null;

    public TrackControllerGUI(TrackControllerMain m, TrackController tkc) {
        tkcm = m;
        currentController = tkc;
        currentBlock = tkcm.tm.getBlock(currentController.getControlledBlocks()[0],tkc.getLine());
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

        

        String[] ControllerNames = tkcm.getControllerNames();
        ObservableList<String> ControllerOptions = FXCollections.observableArrayList();
        for (String option: ControllerNames) {
            ControllerOptions.addAll(option);
        }
        ComboBox<String> controllerBox = new ComboBox<String>(ControllerOptions);
        controllerBox.getSelectionModel().select(currentController.toString());
        controllerBox.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String newTkc = controllerBox.getSelectionModel().getSelectedItem();
                currentController = tkcm.getController(newTkc);
                tkcm.setController(newTkc);

                String[] BlockNames = currentController.getControlledBlocks();//,"2","3","4","5"};
                ObservableList<String> BoxOptionsNew = FXCollections.observableArrayList();
                for (String option: BlockNames) {
                    BoxOptionsNew.addAll(option);
                }
                blockBox.setItems(BoxOptionsNew);
                blockBox.getSelectionModel().selectFirst();
                if (occupancyCheckBox.isSelected()) { // disable

                    occupancyCheckBox.setSelected(false);
                    currentBlock.setIsOccupied(false);
                    overrideOccupied = false;
                }

            }
        });

        String[] BlockNames = currentController.getControlledBlocks();//,"2","3","4","5"};
        ObservableList<String> BoxOptions = FXCollections.observableArrayList();
        for (String option: BlockNames) {
            BoxOptions.addAll(option);
        }

        blockBox = new ComboBox<String>(BoxOptions);
        blockBox.getSelectionModel().selectFirst();
        blockBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (blockBox.getSelectionModel().getSelectedItem() != null) {
                    //System.out.println(blockBox.getSelectionModel().getSelectedItem());
                    //System.out.println(currentController.getLine());
                    currentBlock = tkcm.tm.getBlock(blockBox.getSelectionModel().getSelectedItem(),currentController.getLine());
                    if (currentBlock.getBlockID().equals("C12")) { // switch
                        //System.out.println("Here");
                        currentBlock.setType(BlockType.SWITCHBLOCK);
                        currentBlock.setSwitchState(SwitchState.FORK);
                    }
                    checkUI();
                }
            }
        });


        ObservableList<String> ModeOptions = FXCollections.observableArrayList("Automatic","Manual");
        ComboBox<String> modeBox = new ComboBox<String>(ModeOptions);
        modeBox.getSelectionModel().select(currentController.getMode());
        mode = currentController.getMode();
        modeBox.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //System.out.println("selected new mode: " + modeBox.getSelectionModel().getSelectedItem());
                mode = modeBox.getSelectionModel().getSelectedItem();
                currentController.setMode(mode);
                checkUI();
            }
        });


        Label ctrl = new Label("Controller: ");
        Label blk = new Label( "Block: ");
        Label modeLabel = new Label("Mode: ");

        Region spacer1 = new Region();
        Region spacer2 = new Region();
        Region spacer3 = new Region();
        HBox.setHgrow(spacer1,Priority.ALWAYS);
        HBox.setHgrow(spacer2,Priority.ALWAYS);
        HBox.setHgrow(spacer3,Priority.ALWAYS);

        HBox controllerHBox = new HBox(ctrl,spacer1,controllerBox);
        HBox blockHBox = new HBox(blk,spacer2,blockBox);
        HBox modeHBox = new HBox(modeLabel,spacer3,modeBox);


        /*GridPane.setConstraints(controllerBox,0,0);
        GridPane.setConstraints(blockBox,0,1);
        GridPane.setConstraints(modeBox,0,2);*/

        VBox MainControls = new VBox();
        MainControls.setPadding(new Insets(10));
        MainControls.setSpacing(8);
        MainControls.getChildren().addAll(controllerHBox,blockHBox,modeHBox); // upper left

        GridPane.setConstraints(MainControls,0,0);

        root.getChildren().addAll(MainControls); // upper left

        Label LineInfo = new Label("Line: " + currentController.getLine());
        Label OccupancyLabel = new Label("Occupancy: ");
        occupancyVal = new Label("UNOCCUPIED");

        occupancyCheckBox = new CheckBox("Override to Occupied");
        occupancyCheckBox.setIndeterminate(false); // only true/false
        occupancyCheckBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println(occupancyCheckBox.isSelected() ? "checked" : "unchecked");
                currentBlock.setIsOccupied(occupancyCheckBox.isSelected());
                overrideOccupied = !overrideOccupied;
                //controllerHBox.setDisable(true); // useful for turning off
                checkUI();
            }
        });



        Label AuthorityLabel = new Label("Authority: ");
        Label SpeedLabel = new Label("Speed: ");
        authorityVal = new Label(blockAuthority);
        speedVal = new Label(String.valueOf(blockSpeed));

        Label StatusLabel = new Label("Status: ");
        statusVal = new Label("CLEAR");

        HBox AuthBox = new HBox();
        AuthBox.getChildren().addAll(AuthorityLabel,authorityVal);
        HBox SpeedBox = new HBox();
        SpeedBox.getChildren().addAll(SpeedLabel,speedVal);

        HBox OccBox = new HBox();
        OccBox.getChildren().addAll(OccupancyLabel,occupancyVal);

        HBox StatusBox = new HBox();
        StatusBox.getChildren().addAll(StatusLabel,statusVal);

        VBox GeneralInfo = new VBox();
        GeneralInfo.setPadding(new Insets(10));
        GeneralInfo.setSpacing(8);
        GeneralInfo.getChildren().addAll(LineInfo,OccBox,occupancyCheckBox); // upper right
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



        switchImageView = new ImageView(switchImageGrey);
        switchImageView.setFitWidth(160);
        switchImageView.setFitHeight(120);
        //GridPane.setConstraints(switchImageView,0,1); // middle left
        light1 = new Circle();
        light1.setCenterX(0.0f);
        light1.setCenterY(0.0f);
        light1.setRadius(15.0f);
        light1.setFill(Color.GREEN);
        light1.setStroke(Color.BLACK);
        light1.setStrokeWidth(2.0);

        light2 = new Circle();
        light2.setCenterX(0.0f);
        light2.setCenterY(0.0f);
        light2.setRadius(15.0f);
        light2.setFill(Color.RED);
        light2.setStroke(Color.BLACK);
        light2.setStrokeWidth(2.0);

        Region lightSpacer = new Region();
        VBox.setVgrow(lightSpacer,Priority.ALWAYS);


        light3 = new Circle();
        light3.setCenterX(0.0f);
        light3.setCenterY(100.0f);
        light3.setRadius(15.0f);
        light3.setFill(Color.YELLOW);
        light3.setStroke(Color.BLACK);
        light3.setStrokeWidth(2.0);

        VBox vertLights = new VBox(light2,lightSpacer,light3);

        HBox LightsAndSwitch = new HBox(light1,switchImageView,vertLights);

        //root.getChildren().addAll(switchImageView); // middle left

        crossImageView = new ImageView(crossImageGrey);
        crossImageView.setFitWidth(160);
        crossImageView.setFitHeight(120);
        //GridPane.setConstraints(crossImageView,1,1);

        HBox middleHBox = new HBox(LightsAndSwitch,MiddleDivider,crossImageView);
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
        switchBox = new ComboBox<String>(SwStates);
        switchBox.getSelectionModel().selectFirst();
        switchBox.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //System.out.println("selected new switch state: " + switchBox.getSelectionModel().getSelectedItem());
                String s = switchBox.getSelectionModel().getSelectedItem();
                if (s.equals("FORK"))
                    currentBlock.setSwitchState(SwitchState.FORK);
                else
                    currentBlock.setSwitchState(SwitchState.MAIN);

                checkUI();
            }
        });

        String[] CrossingStates = {"UP","DOWN"};
        ObservableList<String> CrStates = FXCollections.observableArrayList();
        for (String option: CrossingStates) {
            CrStates.addAll(option);
        }
        crossBox = new ComboBox<String>(CrStates);
        crossBox.getSelectionModel().selectFirst();
        crossBox.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //System.out.println("selected new crossing state: " + crossBox.getSelectionModel().getSelectedItem());
                String s = crossBox.getSelectionModel().getSelectedItem();
                if (s.equals("UP"))
                    currentBlock.setCrossingState(CrossingState.UP);
                else
                    currentBlock.setCrossingState(CrossingState.DOWN);

                checkUI();
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
                //System.out.println("selected new block status: " + BlockBox.getSelectionModel().getSelectedItem());
            }
        });


        Label sw = new Label("  Switch:");
        Label cr = new Label("  Crossing:");
        Label statusBoxLabel = new Label("  Block Status:");

        Region spacer4 = new Region();
        Region spacer5 = new Region();
        Region spacer6 = new Region();
        HBox.setHgrow(spacer4,Priority.ALWAYS);
        HBox.setHgrow(spacer5,Priority.ALWAYS);
        HBox.setHgrow(spacer6,Priority.ALWAYS);

        sStateBox = new HBox(sw,spacer4,switchBox);
        cStateBox = new HBox(cr,spacer5,crossBox);
        bStatusBox = new HBox(statusBoxLabel,spacer6,BlockBox);

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

        primaryStage.setTitle(currentController.getName()); // container for all of it
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
        //currentBlock.setIsOccupied(true)
    }

    public void setSpeed(double speed) {
        //currentBlock.setSpeed(speed);
    }
    public void setAuthority(String authority) {
        //currentBlock.setAuthority(authority);
    }

    public void checkUI(){ //disable/enable elements depending on block
        if (currentBlock.getFailures() != null && currentBlock.getFailures().length != 0) // if errors
            statusVal.setText("BROKEN");
        else
            statusVal.setText("CLEAR");

        if (currentBlock.getIsOccupied() && !overrideOccupied)  {
            occupancyVal.setText("OCCUPIED");
            occupancyCheckBox.setDisable(true); // cant change for train
        }
        else if (currentBlock.getIsOccupied()) {
            occupancyVal.setText("OCCUPIED");
        }
        else  {
            occupancyVal.setText("UNOCCUPIED");
            occupancyCheckBox.setDisable(false);
        }

        if (currentBlock.getType() == BlockType.SWITCHBLOCK) {
            switchBox.getSelectionModel().select(currentBlock.getSwitchState().toString());
        }

        if (currentBlock.getType() == BlockType.CROSSBLOCK) {
            crossBox.getSelectionModel().select(currentBlock.getCrossingState().toString());
        }

        if (!mode.equals("Automatic") && currentBlock.getType() == BlockType.SWITCHBLOCK) {
                sStateBox.setDisable(false);
        } else {
                sStateBox.setDisable(true);
        }

        if (!mode.equals("Automatic") && currentBlock.getType() == BlockType.CROSSBLOCK) {
            cStateBox.setDisable(false);
        } else {
            cStateBox.setDisable(true);
            //grey out
        }

        // UI greying out
        //System.out.println("Current block type is" + currentBlock.getType());
        if (currentBlock.getType() == BlockType.SWITCHBLOCK) {
            if (currentBlock.getSwitchState() == SwitchState.MAIN) {
                switchImageView.setImage(switchImageMain);
                light1.setFill(Color.GREEN);
                light2.setFill(Color.GREEN);
                light3.setFill(Color.RED);
            }
            else {
                switchImageView.setImage(switchImageFork);
                light1.setFill(Color.GREEN);
                light2.setFill(Color.RED);
                light3.setFill(Color.GREEN);
            }
            crossImageView.setImage(crossImageGrey);
        } else if (currentBlock.getType() == BlockType.CROSSBLOCK) {
            if (currentBlock.getCrossingState() == CrossingState.UP)
                crossImageView.setImage(crossImageUp);
            else
                crossImageView.setImage(crossImageDown);
            switchImageView.setImage(switchImageGrey);
            light1.setFill(Color.GREY);
            light2.setFill(Color.GREY);
            light3.setFill(Color.GREY);
        } else {
            crossImageView.setImage(crossImageGrey);
            switchImageView.setImage(switchImageGrey);
            light1.setFill(Color.GREY);
            light2.setFill(Color.GREY);
            light3.setFill(Color.GREY);
        }

    }

    public void update() {
        if (currentBlock.getAuditedAuthority() == null) {
            currentBlock.setAuditedAuthority(currentBlock);
        }
        authorityVal.setText(currentBlock.getAuditedAuthority().getBlockID());
        speedVal.setText(String.valueOf(currentBlock.getAuditedSpeed()));
        checkUI();
    }

    public void ChangeSwitchState(SwitchState s) {
        //currentBlock.setSwitchState(s);
    }

    public void ChangeBlockStatus(String failure) {
        //currentBlock.addFailure(failure)
        return;
    }

    public void ChangeCrossingState(CrossingState s) {
        //currentBlock.setCrossingState(s);
        return;
    }

    public void ChangePLC(String plc) {
        return;
    }
}
