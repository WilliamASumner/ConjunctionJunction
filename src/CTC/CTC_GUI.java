import javafx.application.Application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.scene.paint.Color;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.List;

import java.io.*; 
import java.util.*; 

public class CTC_GUI extends Application {
    // fields
    private Label throughput;
    private TextField departureTime, suggAuthority, suggSpeed, trainName, speed;
    private ComboBox<String> comboAuthority, comboChooseTrack, comboChooseTrackMaintain, comboChooseBlock;
    private CheckBox cb1, cb2, cb3;
    private String cssLayout = "-fx-border-color: #7a7a7a;\n" +
                   "-fx-border-insets: 0;\n" +
                   "-fx-border-width: 0.5;\n";
                   
    private ObservableList<String> namesGreenLine = FXCollections.observableArrayList(), namesRedLine = FXCollections.observableArrayList();
    private ObservableList<String> data = FXCollections.observableArrayList(), dataSelectedQueuedTrain = FXCollections.observableArrayList();
    private ListView<String> stopsListView, selectedTrain = new ListView<String>(dataSelectedQueuedTrain);
    
    private VBox vboxMaintain, vboxAdd, vboxQueued, rightVbox, leftVbox;
    private HBox hboxAdd1, hboxAdd2, hboxAdd3, bottomControlOfModule;
    private Button deleteT, dispatchT, importSched, queueT;
    
    public TableView table;
    
    private Timeline timeline;
    
    private ObservableList<String> queuedItems;
    private ListView<String> queuedTrainsView; 
    
    private ArrayList<String> sched = new ArrayList<String>(5);
    
    private String[] TrackTypes = {"Green Line", "Red Line"};
    private String[] SwitchBlocks = new String[]{"G12", "G29", "G58", "G62", "G76", "G86", 
                                    "R9", "R15", "R27", "R32", "R38", "R43", "R52"};
    private List<String> list = Arrays.asList(SwitchBlocks);
    
    private Scene scene;
    
    private CTC newCTC;

    public CTCTrain tempQueuedTrain = null;
    
    BorderPane borderPane;
    Stage newStage;
    
    //Fields for interface comms
    String chosenBlock = "";
    private String currentLine = "green";
    
    public CTC_GUI(CTC nctc, Stage stage) {
        newStage = stage;
        newCTC = nctc;

        BorderPane borderPane = new BorderPane();
        // Create a BorderPane object
        borderPane.setStyle("-fx-border-insets: 5;\n");
        
        //MAINTAIN TRACK(BOTTOM LEFT VIEW)--------------------------------------------------------------
        // Create 'maintain track' view
        
        // Track Line Selections
        comboChooseTrackMaintain = new ComboBox<>();
        for (String option: TrackTypes)
            comboChooseTrackMaintain.getItems().add(option);
        comboChooseTrackMaintain.setPromptText("Choose Track"); 
        // Create combobox for block selections
        comboChooseBlock = new ComboBox<>();
        comboChooseBlock.setPromptText("Choose Block"); 
        // Add listener such that when line is chosen, populate block combobox with number of blocks
        // that the selected line contains
        comboChooseTrackMaintain.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                public void changed(ObservableValue<? extends String> ov, 
                    String old_val, String new_val) {
                        if(new_val.equals("Green Line")){
                            // Remove previously added items 
                            comboChooseBlock.getItems().clear();
                            // Add blocks that the green line contains
                            for (int i = 1; i <= 150; i++)
                                comboChooseBlock.getItems().add("G"+i);
                            // Add listener such that when block is chosen, populate checkbox to reflect
                            // whether or not the chosen block is switch...
                            comboChooseBlock.getSelectionModel().selectedItemProperty().addListener(
                                new ChangeListener<String>() {
                                    public void changed(ObservableValue<? extends String> ov, 
                                        String old_val, String new_val) {
                                            // If chosen block is a switch
                                            if(list.contains(new_val)){
                                                // block to be sent to Track Controller
                                                chosenBlock = new_val;
                                                cb1.setIndeterminate(false);
                                                cb1.setSelected(false);
                                                cb2.setIndeterminate(false);
                                                cb2.setSelected(false);
                                                cb3.setIndeterminate(false);
                                                cb3.setSelected(false);
                                            }
                                            else{
                                                // block to be sent to Track Controller
                                                chosenBlock = new_val;
                                                cb1.setIndeterminate(false);
                                                cb1.setSelected(false);
                                                cb2.setIndeterminate(false);
                                                cb2.setSelected(false);
                                                cb3.setIndeterminate(true);                                             
                                            }
                                        }
                                });
                        }
                        else{
                            // Remove previously added items 
                            comboChooseBlock.getItems().clear();
                            // Add blocks that the red line contains
                            for (int i = 1; i <= 76; i++)
                                comboChooseBlock.getItems().add("R"+i);
                            // Add listener such that when block is chosen, populate checkbox to reflect
                            // whether or not the chosen block is switch...
                            comboChooseBlock.getSelectionModel().selectedItemProperty().addListener(
                                new ChangeListener<String>() {
                                    public void changed(ObservableValue<? extends String> ov, 
                                        String old_val, String new_val) {
                                            // If chosen block is a switch
                                            if(list.contains(new_val)){
                                                // block to be sent to Track Controller
                                                chosenBlock = new_val;                                              
                                                cb1.setIndeterminate(false);
                                                cb1.setSelected(false);
                                                cb2.setIndeterminate(false);
                                                cb2.setSelected(false);
                                                cb3.setIndeterminate(false);
                                                cb3.setSelected(false);
                                            }
                                            else{
                                                // block to be sent to Track Controller
                                                chosenBlock = new_val;                                              
                                                cb1.setIndeterminate(false);
                                                cb1.setSelected(false);
                                                cb2.setIndeterminate(false);
                                                cb2.setSelected(false);
                                                cb3.setIndeterminate(true);                                             
                                            }
                                        }
                                });
                        }
                    }   
        });
        
        HBox hboxMaintain = new HBox(5, comboChooseTrackMaintain, comboChooseBlock);
        
        //Create checkboxs for 'Repair, Close, and Switch' blocks
        cb1 = new CheckBox("Repair Block");
        cb1.setOnAction(new repairCheckBoxHandler());
        cb1.setIndeterminate(true);
        cb2 = new CheckBox("Close Block");
        cb2.setOnAction(new closeCheckBoxHandler());
        cb2.setIndeterminate(true);
        cb3 = new CheckBox("Switch Block");
        cb3.setOnAction(new switchCheckBoxHandler());
        cb3.setIndeterminate(true);
                
        // Create a VBox
        vboxMaintain = new VBox(5, getStackPane(new Text("Track Maintenance"), 250.0), hboxMaintain, 
                new VBox(5, cb1, cb2, cb3));
        vboxMaintain.setStyle(cssLayout);
        //----------------------------------------------------------------------------
        
        //ADD TRAIN(BOTTOM CENTER VIEW)--------------------------------------------------------------------
        // Track Line Selections
        comboChooseTrack = new ComboBox<>();
        for (String option: TrackTypes)
            comboChooseTrack.getItems().add(option);
        comboChooseTrack.setPromptText("Choose Track");
       
        // Create view for inputting schedule
        stopsListView = new ListView<String>(data);
        stopsListView.setPrefSize(160, 70);
        for (int i = 0; i < 15; i++)
            data.add("Add Stop");
        stopsListView.setItems(data);
        
       // Set listener to detect choice of line-green or red-thus populating based on selection
        comboChooseTrack.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
        {
            public void changed(ObservableValue<? extends String> ov, 
                                String old_val, String new_val) {
                // Get Selected line color
                String selection = comboChooseTrack.getSelectionModel().getSelectedItem();
                // Add stations in accordance to selected line color
                addLineStations(selection);
            }
        });
       // Set listener to detect choice of individual stations
        stopsListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> ov, 
                        String old_val, String new_val) {
                            int curIndex = stopsListView.getEditingIndex();
                            if(!new_val.equals("Add Stop") && curIndex != -1){
                                System.out.println(new_val + " added to index: " + curIndex);
                                sched.add(curIndex, new_val);
                            }
                    }
        });                 
       
        // create a TextField for inputing 'train name'
        trainName = new TextField();
        trainName.setPromptText("Train Name");
        
        // Create 'Import Schedule' button
        importSched = new Button("Import Schedule");
        // Register the event handler
  //      dispatchT.setOnAction(new DispatchButtonHandler());       
        
        // create a TextField for inputting speed of train
        speed = new TextField();        
        speed.setPromptText("Suggested Speed");
        
        // create a TextField for inputting departure time
        departureTime = new TextField();
        departureTime.setPromptText("Depature time");
        
        // Create queue button
        queueT = new Button("Queue Train");
        // Register the event handler
        queueT.setOnAction(new QueueButtonHandler());
        
        // Put in HBoxAdd
        hboxAdd1 = new HBox(50, comboChooseTrack, importSched); 
        hboxAdd2 = new HBox(5, stopsListView, new VBox(5, trainName, departureTime, speed));
        hboxAdd3 = new HBox(5, queueT);
        
        vboxAdd = new VBox(5, getStackPane(new Text("Add Train"), 315.0), hboxAdd1, hboxAdd2, hboxAdd3);
        vboxAdd.setStyle(cssLayout);
        //--------------------------------------------------------------------------------------------
        
        //SELECT A QUEUED TRAIN(BOTTOM RIGHT VIEW)-----------------------------------------------------------------------
        // Create 'Select a Queued Train' view
        queuedTrainsView = new ListView<String>();
        queuedItems =FXCollections.observableArrayList ();
        queuedTrainsView.setItems(queuedItems);
        queuedTrainsView.setPrefWidth(250);
        queuedTrainsView.setPrefHeight(120);

        // When no trains are queued, set a label indicator
        queuedTrainsView.setPlaceholder(new Label("No Currently Queued Trains"));

        selectedTrain.setPrefWidth(250);
        selectedTrain.setPrefHeight(120);
        // Add listener to populate leftmost bottom-GUI pane with selected queued train's schedule 
        queuedTrainsView.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                public void changed(ObservableValue<? extends String> ov, 
                    String old_val, String new_val) {
                    //  selectedTrain.setEditable(true);
                        System.out.println("old_val= "+ old_val + ", new_val= "+new_val);               
                        // Remove previously selected trains schedules
                        if(old_val != null){
                            tempQueuedTrain = newCTC.findQueuedTrain(old_val);
                            for (int i = 0; i < tempQueuedTrain.schedule.size(); i++) {
                                System.out.println("IN SELECT A QUEUED TRAIN:(REMOVING)-> "+tempQueuedTrain.schedule.get(i));
                                dataSelectedQueuedTrain.remove(tempQueuedTrain.schedule.get(i));
                                dataSelectedQueuedTrain.remove(null);
                            }       
                            selectedTrain.setItems(dataSelectedQueuedTrain);
                        }
                        // Find selected train
                        tempQueuedTrain = newCTC.findQueuedTrain(new_val);
                        for (int i = 0; i < tempQueuedTrain.schedule.size(); i++) {
                            dataSelectedQueuedTrain.add(tempQueuedTrain.schedule.get(i));
                            dataSelectedQueuedTrain.remove(null);
                        }               
                        selectedTrain.setItems(dataSelectedQueuedTrain);    
                    }   
        });
        
        // Create Dispatch button
        dispatchT = new Button("Dispatch Selected Train");
        // Register the event handler
        dispatchT.setOnAction(new DispatchButtonHandler()); 
 
        // Create Delete button
        deleteT = new Button("Delete Train");
        // Register the event handler
        deleteT.setOnAction(new DeleteButtonHandler()); 
 
        HBox queuedHBox = new HBox(5, queuedTrainsView, selectedTrain);
        VBox vboxQueued = new VBox(5, getStackPane(new Text("Select a Queued Train"), 510.0),
                                    queuedHBox, new HBox(80, dispatchT, deleteT));
        vboxQueued.setStyle(cssLayout);
        //--------------------------------------------------------------------------------------------
        
        
        //FINALIZE BOTTOM VIEW---------------------------------------------------------------   
        // Create an HBox to contain the 'Track Maintenance', 'Add Train', and 'Select a Queued Train'
        // containers created above...
        bottomControlOfModule = new HBox(1, vboxMaintain, vboxAdd, vboxQueued);
        
        // Add to bottom of BorderPane
        borderPane.setBottom(bottomControlOfModule);
        //---------------------------------------------------------------
        
        
        //DISPATCHED TRAINS(RIGHT VIEW)---------------------------------------------------------------
        // Create table view 
        table = new TableView();
        
        TableColumn<String, CTCTrain> trainNameCol = new TableColumn<>("Train Name");
        // Set the 'trainNameCol' to accept a field named 'trainName'
        trainNameCol.setCellValueFactory(new PropertyValueFactory<>("trainName"));
        
        TableColumn<String, CTCTrain> curBlockCol = new TableColumn<>("Cur Block");
        // Set the 'curBlockCol' to accept a field named 'curBlkNum'
        curBlockCol.setCellValueFactory(new PropertyValueFactory<>("curBlkID"));
        
        TableColumn<String, CTCTrain> authCol = new TableColumn<>("Authority");
        // Set the 'authCol' to accept a field named 'curBlkNum'
        authCol.setCellValueFactory(new PropertyValueFactory<>("curAuthority"));    
        
        TableColumn<String, CTCTrain> speedCol = new TableColumn<>("Speed");
        // Set the 'speedCol' to accept a field named 'curSpeed'
        speedCol.setCellValueFactory(new PropertyValueFactory<>("curSpeed"));   
        
        // Add all cols to the table
        //@SuppressWarnings("unchecked")
        table.getColumns().addAll(trainNameCol, curBlockCol, authCol, speedCol);
        
        // When no trains are dispatched, set a label indicator
        table.setPlaceholder(new Label("No Currently Dispatched Trains"));
/*      
        ListView<String> dispatchedTrains = new ListView<String>();
        ObservableList<String> items =FXCollections.observableArrayList (
                                "Single", "Double", "Suite", "Family App");
        dispatchedTrains.setItems(items);
        dispatchedTrains.setPrefWidth(320);
        //dispatchedTrains.setPrefHeight(120);
*/

        // Add listener to populate leftmost bottom-GUI pane with selected queued train's schedule 
        //@SuppressWarnings("unchecked")
        table.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<Object>() {
                public void changed(ObservableValue<? extends Object> ov, 
                    Object old_val, Object new_val) {
                        /*
                                selectedTrain.setEditable(true);
                                // Remove previously selected trains schedules
                                dataSelectedQueuedTrain.removeAll("Add Stop");
                                selectedTrain.setItems(dataSelectedQueuedTrain);
                                for (int i = 0; i < 18; i++) {
                                    dataSelectedQueuedTrain.add("Add Stop");
                                }
                                selectedTrain.setItems(dataSelectedQueuedTrain);    
                        */
                        System.out.println("SELECTED: " + new_val + " IN DISPATCHED TRAINS VIEWER.");
                    }   
        });     
        
        

        // create a TextField for inputting suggested authority
        suggAuthority = new TextField();
        suggAuthority.setPromptText("Suggested Authority");
        
        // create a TextField for inputting suggested speed
        suggSpeed = new TextField();
        suggSpeed.setPromptText("Suggested Speed");     
    
        // Create send data to train button
        Button sendData = new Button("Send Suggestions");
        // Register the event handler
 //       queueT.setOnAction(new QueueButtonHandler()); 
    
    
        // Add to a VBox
        rightVbox = new VBox(0, getStackPane(new Text("Dispatched Trains"), 320.0), table, 
                         new HBox(20, suggAuthority, suggSpeed), sendData);       
        rightVbox.setStyle(cssLayout);
   //     rightVbox.getChildren().add(myLabel);
        // Create top-right BorderPane
        borderPane.setRight(rightVbox);
        //---------------------------------------------------------------
        

        //CREATE CENTER VIEW--------------------------------------------------------------
        String projectBaseDir = "file:"+System.getProperty("user.dir") + "/src"; // get base dir
        String imgPath = projectBaseDir + "/CTC/trackLayout.jpg";
        Image trackLayout = new Image(imgPath);
        // create an ImageViw object
        ImageView imageView = new ImageView(trackLayout);
        imageView.setFitWidth(500);
        imageView.setFitHeight(350);
        // put imageView into center of BorderPane
        borderPane.setCenter(imageView);
        //---------------------------------------------------------------

        
        //CREATE LEFT VIEW---------------------------------------------------------------
        // construct the digitalClock pieces.
        Label digitalClock = new Label();
        
        // Create Stack pane to contain clock
        StackPane stackAddClock = new StackPane();
        Rectangle addRec = new Rectangle(250.0, 50.0);
        addRec.setFill(Color.BLACK);
        stackAddClock.getChildren().addAll(addRec, digitalClock);
        stackAddClock.setAlignment(Pos.CENTER); 
        
        digitalClock.setTextFill(Color.WHITE);
        digitalClock.setStyle("-fx-font-size: 3em;");
        digitalClock.setId("digitalClock");
        
//        Button button = new Button();
//        button.setText("Start/Pause");

        timeline = new Timeline(
            new KeyFrame(Duration.seconds(1),
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    Calendar calendar = Calendar.getInstance();
                    String hourString   = pad(2, '0', calendar.get(Calendar.HOUR)   == 0 ? "12" : calendar.get(Calendar.HOUR) + "");
                    String minuteString = pad(2, '0', calendar.get(Calendar.MINUTE) + "");
                    String secondString = pad(2, '0', calendar.get(Calendar.SECOND) + "");
                    String ampmString   = calendar.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
                    digitalClock.setText(hourString + ":" + minuteString + ":" + secondString + " " + ampmString);
                }
            })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
        // Create Throughput view
        throughput = new Label("Throughput = 0");
        
        // Create Stack pane to contain clock
        StackPane stackAddThroughput = new StackPane();
        Rectangle addRec2 = new Rectangle(250.0, 50.0);
        addRec2.setFill(Color.BLACK);
        stackAddThroughput.getChildren().addAll(addRec2, throughput);
        stackAddThroughput.setAlignment(Pos.CENTER); 
        
        throughput.setTextFill(Color.WHITE);
        throughput.setStyle("-fx-font-size: 2em;");
        
/*  
        imgPath = projectBaseDir + "/CTC/digital_clock.jpg";
        Image clock = new Image(imgPath);
        // create an ImageViw object
        ImageView imageView2 = new ImageView(clock);
        imageView2.setFitWidth(250);
        imageView2.setFitHeight(150);
*/        
        leftVbox = new VBox(0, stackAddClock, stackAddThroughput);
        leftVbox.setStyle(cssLayout);
        // put imageView2 into left of BorderPane
        borderPane.setLeft(leftVbox);
        //----------------------------------------------------------------
        
        
        // Create a scene with the borderPane as its root node
        scene = new Scene(borderPane);//(new StackPane(l), 640, 480);
        stage.setTitle("CTC Module");
        stage.setWidth(1100);
        stage.setHeight(585);
        stage.setScene(scene);
    }
    
    @Override
    public void start(Stage stage) {
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    public TrainModel getTrainModel(){
        return null;
    }

    public String pad(int fieldWidth, char padChar, String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i < fieldWidth; i++) {
          sb.append(padChar);
        }
        sb.append(s);

        return sb.toString();
    }

    public StackPane getStackPane(Text textAdd, double length){
        // Create StackPane for pronounced viewer section title
        StackPane stackAdd = new StackPane();
        Rectangle addTrainRec = new Rectangle(length, 25.0);
        addTrainRec.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
        new Stop[]{
        new Stop(0,Color.web("#d0d0d0")),
        new Stop(0.5, Color.web("#f4f4f4")),
        new Stop(1,Color.web("#ffffff")),}));
        addTrainRec.setStroke(Color.web("#7a7a7a"));
        addTrainRec.setArcHeight(3.5);
        addTrainRec.setArcWidth(3.5);
        stackAdd.getChildren().addAll(addTrainRec, textAdd);
        stackAdd.setAlignment(Pos.CENTER);          
        return stackAdd;
    }
    
    public void addLineStations(String selection){
        if(selection.equals("Green Line")){
            //Set current line for dispatched trains to green
            currentLine = "green";
            // Add green line station names
            namesGreenLine.addAll(
                "PIONEER","EDGEBROOK","STATION", "WHITED", 
                "SOUTH BANK", "CENTRAL", "INGLEWOOD",
                "OVERBROOK", "GLENBURY", "DORMONT",
                "MT LEBANON", "POPLAR", "CASTLE SHANNON"
            );
            stopsListView.setCellFactory(ComboBoxListCell.forListView(namesGreenLine));
            stopsListView.setEditable(true);
/*          stopsListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> ov, 
                        String old_val, String new_val) {
                            int curIndex = stopsListView.getEditingIndex();
                            if(!new_val.equals("Add Stop") && curIndex != -1){
                                System.out.println(new_val + " added to index: " + curIndex);
                                sched.add(curIndex, new_val);
                            }
                        }
                }); */              
        }
        else if(selection.equals("Red Line")){
            //Current line for trains to red
            currentLine = "red";
            // Add red line station names
            namesRedLine.addAll(
                "SHADYSIDE","HERRON AVE","SWISSVILLE", "PENN STATION", 
                "STEEL PLAZA", "FIRST AVE", "STATION SQUARE",
                "SOUTH HILLS JUNCTION"
            );                          
            stopsListView.setCellFactory(ComboBoxListCell.forListView(namesRedLine));
            stopsListView.setEditable(true);
/*          stopsListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> ov, 
                        String old_val, String new_val) {
                            int curIndex = stopsListView.getEditingIndex();
                            if(!new_val.equals("Add Stop") && curIndex != -1){
                                System.out.println(new_val + " added to index: " + curIndex);
                                sched.add(curIndex, new_val);
                            }
                        }
                });*/
        }       
    }

// EVENT HANDLERS-----------------------------------------------------------------------------------------
    
    /**
     * Event handler class for repair block checkbox.
     */
    class repairCheckBoxHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            // if chosen block is yet to be selected, ignore this event
            if(chosenBlock.equals(""));
            else// SEND TO CTC REPAIR BLOCK
                // only when selected
                if(cb1.isSelected())
                    newCTC.repairBlock(chosenBlock);    
        }
    }
    
    /**
     * Event handler class for close block checkbox.
     */
    class closeCheckBoxHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            // if chosen block is yet to be selected, ignore this event
            if(chosenBlock.equals(""));
            else// SEND TO CTC CLOSE BLOCK
                // only when selected
                if(cb2.isSelected())
                    newCTC.closeBlock(chosenBlock);
        }
    }

    /**
     * Event handler class for switch block checkbox.
     */
    class switchCheckBoxHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            // if chosen block is yet to be selected, ignore this event
            if(chosenBlock.equals(""));
            else if(list.contains(chosenBlock))// SEND TO CTC SWITCH BLOCK
                // only when selected
                if(cb3.isSelected())
                    newCTC.switchBlock(chosenBlock);
        }
    }   

    /**
     * Event handler class for queueT button.
     */
    class QueueButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            String departTime = departureTime.getText();
            String tName = trainName.getText();
            String speedMPH = speed.getText();
            
            // Create temporary CTCTrain to add to queued train viewer
            CTCTrain tempTrain = new CTCTrain(newCTC.getTkM());
            // Set CTCTrain's schedule
            System.out.println("SCHED=" + sched);
            tempTrain.setSchedule(sched);
            sched.clear();
            // Set name
            tempTrain.setName(tName);

            //Set line
            tempTrain.setLine(currentLine);
            
            // Determine if speed is at or under limit
//          newCTC.getBlockSpeed();
            // Set speed            
            tempTrain.setSpeed(speedMPH);
            // Set depart time
            tempTrain.setDepartureTime(departTime);
            // Add to queued trains
            newCTC.queueNewTrain(tempTrain);
            
            queuedItems.add(tempTrain.toString());
            queuedTrainsView.setItems(queuedItems);
            
            // Clear input boxes
            departureTime.setText(""); 
            trainName.setText(""); 
            speed.setText(""); 

            System.out.println("IN QUEUE BUTTON HANDLER: " + tempTrain.schedule);

            // Reset view after each queued train
            //stopsListView.getItems().clear();
            //data.clear();
//          for (int i = 0; i < 15; i++)
//              data.add("Add Stop");
//          stopsListView.setItems(data);           

            //myLabel.setText(tName + "\t\tK63" + "\t\t" + auth + "\t\t" + speedMPH);
            //rightVbox.getChildren().add(myLabel);         
        }
    }


    /**
     * Event handler class for dispatchT button.
     */
    class DispatchButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            table.getItems().add(tempQueuedTrain);
            // Add to disptached trains
            newCTC.dispatchQueuedTrain(tempQueuedTrain);
            
            // Remove 'queuedTrain' from 'Select a Queued Train' view
            queuedItems.remove(tempQueuedTrain.toString());
            queuedTrainsView.setItems(queuedItems);
            
            // check to see if there are any trains queued
            if(queuedTrainsView.getItems().isEmpty())
                selectedTrain.getItems().clear();   // no queued trains, remove all 
                                                    //  selected schedules from view
            newCTC.deleteQueuedTrain(tempQueuedTrain); // delete train from queued trains
        }
    }
    
    /**
     * Event handler class for deleteT button.
     */
    class DeleteButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            queuedItems.remove(tempQueuedTrain.toString());
            queuedTrainsView.setItems(queuedItems);
            
            // check to see if there are any trains queued
            if(queuedTrainsView.getItems().isEmpty())
                selectedTrain.getItems().clear();   // no queued trains, remove all 
                                                    //  selected schedules from view
            newCTC.deleteQueuedTrain(tempQueuedTrain); // delete train from queued trains
            
        }
    }
}
