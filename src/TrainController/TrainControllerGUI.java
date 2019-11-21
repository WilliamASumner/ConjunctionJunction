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

import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.layout.*; 
import javafx.stage.Stage; 
import javafx.scene.layout.*; 
import javafx.scene.paint.*; 
import javafx.scene.text.*; 
import javafx.geometry.*; 
import javafx.scene.layout.*; 
import javafx.scene.shape.*; 
import javafx.scene.paint.*; 
import javafx.scene.*; 
import java.io.*; 
import javafx.scene.image.*; 
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage; 
import javafx.application.Application; 

import javafx.stage.Stage; 

import javafx.geometry.*; 

import javafx.scene.*; 
import java.io.*; 
import javafx.scene.image.*; 

public class TrainControllerGUI extends Application implements EventHandler<ActionEvent> {
    double speed;
    String authority;
    String name;
    TrainController tnc;

    Slider speedSlider;

    //Buttons on TrainControllerGUI
    Button setSpeedButton = new Button("Set Speed");
    Button eBrake = new Button("E BRAKE: Currently OFF");
    Button sBrake = new Button("Service Brake");
    Button lights = new Button("Turn Lights ON");


    //Buttons for door control
    Button rDoor1 = new Button("Right Door 1");
    Button rDoor2 = new Button("Right Door 2");
    Button rDoor3 = new Button("Right Door 3");
    Button rDoor4 = new Button("Right Door 4");
    Button lDoor1 = new Button("Left Door 1");
    Button lDoor2 = new Button("Left Door 2");
    Button lDoor3 = new Button("Left Door 3");
    Button lDoor4 = new Button("Left Door 4");

    RadioButton autoRadioButton;
    RadioButton manualRadioButton;

    Slider tempSlider;

    Button setTempButton;

    public TrainControllerGUI() {
		/*
      speed = TrainController.getAuditedSpeed();
      authority = TrainController.getAuthority();
      name = TrainController.getName();
	  */
	  //speed = TrainController.auditedSpeed;
	  //authority = TrainController.authority;
	  //name = TrainController.trainName;
    }
    public TrainControllerGUI(Stage primaryStage, TrainController tc) {
      /*
      speed = TrainController.getAuditedSpeed();
      authority = TrainController.getAuthority();
      name = TrainController.getName();
      */
      tnc = tc;
	  
    }

    @Override
       public void start(Stage primaryStage) {
        //Update the GUI information with the specific train controller object's data
        //speed = tnc.getAuditedSpeed();
        //authority = tnc.getAuthority();
        //name = tnc.getName();

        setSpeedButton.setOnAction(this);
        eBrake.setOnAction(this);
        sBrake.setOnAction(this);
        lights.setOnAction(this);

        primaryStage.setTitle(name + " - Train Controller UI");

        eBrake.setStyle("-fx-text-fill: red");
        eBrake.setMinWidth(400);
        eBrake.setMaxWidth(400);
        eBrake.setMinWidth(100);
        eBrake.setMaxWidth(100);
           

        Label output = new Label("Train Name: " + name + "\nAudited Authority: " + authority + "\nAudited Speed Limit: " + speed); 
          
        Label modeSelectLabel = new Label("Select a Mode: "); 
        Label doorLabel = new Label("Door Status Control: "); 

        Label failureList = new Label("Track Circuit Failure: NOT Detected\nEmergency Brake Failure: NOT Detected\nService Brake Failure: NOT Detected\nEngine Failure: NOT Detected");
        Label failureTitle = new Label("Track Failure Status: ");
        autoRadioButton = new RadioButton("Automatic");
        autoRadioButton.setSelected(true);
        manualRadioButton = new RadioButton("Manual");
   
        ToggleGroup radioGroup = new ToggleGroup();

        speedSlider = new Slider(0, speed, 0);
        speedSlider.setShowTickLabels(true);

        tempSlider = new Slider(0, 80, 0);
        tempSlider.setShowTickLabels(true);
   
        autoRadioButton.setToggleGroup(radioGroup);
        manualRadioButton.setToggleGroup(radioGroup);

        HBox hbox = new HBox(autoRadioButton, manualRadioButton);

           
        FlowPane flowpane = new FlowPane();

        setTempButton = new Button("Set Temp");
 

        //Panel for output for iteration 2
        FlowPane flowpane1 = new FlowPane();
        flowpane1.setStyle("-fx-border-color: black");
        flowpane.getChildren().add(flowpane1);
        flowpane1.getChildren().add(output);
        flowpane1.getChildren().add(speedSlider);
        flowpane1.getChildren().add(setSpeedButton);
        flowpane1.getChildren().add(tempSlider);  
        flowpane1.getChildren().add(setTempButton);


        //Panel for mode select
        FlowPane modeSelect = new FlowPane();
        modeSelect.setStyle("-fx-border-color: black");
        flowpane.getChildren().add(modeSelect);
        modeSelect.getChildren().add(modeSelectLabel);
        modeSelect.getChildren().add(hbox);

        //Panel for failure status
        FlowPane failure = new FlowPane();
        failure.setStyle("-fx-border-color: black");
        flowpane.getChildren().add(failure);
        failure.getChildren().add(failureTitle);
        failure.getChildren().add(failureList);

        FlowPane brake = new FlowPane();
        brake.setStyle("-fx-border-color: black");
        //emergency brake
        brake.getChildren().add(eBrake);
        //service brake
        brake.getChildren().add(sBrake);
        brake.getChildren().add(lights);
        flowpane.getChildren().add(brake);
          

        //Panel for Door Status
        FlowPane doorStatus = new FlowPane();
        doorStatus.setStyle("-fx-border-color: black");
        doorStatus.getChildren().add(doorLabel);
        doorStatus.getChildren().add(rDoor1);
        doorStatus.getChildren().add(rDoor2);
        doorStatus.getChildren().add(rDoor3);
        doorStatus.getChildren().add(rDoor4);
        doorStatus.getChildren().add(lDoor1);
        doorStatus.getChildren().add(lDoor2);
        doorStatus.getChildren().add(lDoor3);
        doorStatus.getChildren().add(lDoor4);
        flowpane.getChildren().add(doorStatus);

        Scene scene = new Scene(flowpane, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
       }

       //Action Listeners for our button
       public void handle(ActionEvent event){
            if(event.getSource()==setSpeedButton){
                tnc.newDriverSetSpeed(speedSlider.getValue());
            }
            else if(event.getSource()==eBrake){
                boolean currentState;
                currentState = tnc.toggleEBrake();
                if(currentState){
                    eBrake.setText("E Brake: Currently ON");
                }
                else{
                    eBrake.setText("E Brake: Currently OFF");

                }
            }
            else if(event.getSource()==sBrake){
                boolean currentState;
                currentState = tnc.toggleServiceBrake();
                if(currentState){
                    eBrake.setText("S Brake: Currently ON");
                }
                else{
                    eBrake.setText("S Brake: Currently OFF");

                }
            }
            else if(event.getSource()==lights){
                boolean currentState;
                currentState = tnc.setLights();
                if (currentState){
                    lights.setText("Lights Currently ON");
                }
                else{
                    lights.setText("Lights Currently OFF");
                }
            }
            else if(event.getSource()==rDoor1){
                boolean currentState;
                currentState = tnc.toggleDoor(0);
                if(currentState){
                    rDoor1.setText("Right Door 1 Currently Open");
                }
                else{
                    rDoor1.setText("Right Door 1 Currently Closed");
                }
            }
            else if(event.getSource()==rDoor2){
                boolean currentState;
                currentState = tnc.toggleDoor(1);
                if(currentState){
                    rDoor2.setText("Right Door 2 Currently Open");
                }
                else{
                    rDoor2.setText("Right Door 2 Currently Closed");
                }
            }
            else if(event.getSource()==rDoor3){
                boolean currentState;
                currentState = tnc.toggleDoor(2);
                if(currentState){
                    rDoor3.setText("Right Door 3 Currently Open");
                }
                else{
                    rDoor3.setText("Right Door 3 Currently Closed");
                }
            }
            else if(event.getSource()==rDoor4){
                boolean currentState;
                currentState = tnc.toggleDoor(3);
                if(currentState){
                    rDoor4.setText("Right Door 4 Currently Open");
                }
                else{
                    rDoor4.setText("Right Door 4 Currently Closed");
                }
            }
            else if(event.getSource()==lDoor1){
                boolean currentState;
                currentState = tnc.toggleDoor(4);
                if(currentState){
                    lDoor1.setText("Left Door 1 Currently Open");
                }
                else{
                    lDoor1.setText("Left Door 1 Currently Closed");
                }
            }
            else if(event.getSource()==lDoor2){
                boolean currentState;
                currentState = tnc.toggleDoor(5);
                if(currentState){
                    lDoor2.setText("Left Door 2 Currently Open");
                }
                else{
                    lDoor2.setText("Left Door 2 Currently Closed");
                }
            }
            else if(event.getSource()==lDoor3){
                boolean currentState;
                currentState = tnc.toggleDoor(6);
                if(currentState){
                    lDoor3.setText("Left Door 3 Currently Open");
                }
                else{
                    lDoor3.setText("Left Door 3 Currently Closed");
                }
            }
            else if(event.getSource()==lDoor4){
                boolean currentState;
                currentState = tnc.toggleDoor(7);
                if(currentState){
                    lDoor4.setText("Left Door 4 Currently Open");
                }
                else{
                    lDoor4.setText("Left Door 4 Currently Closed");
                }
            }
            //If set temp button is pressed, set temp to temp slider value
            else if(event.getSource()==setTempButton){
                tnc.setTemp(tempSlider.getValue());
            }
            else if(autoRadioButton.isSelected()){
                tnc.setAutomaticMode();
            }
            else if(manualRadioButton.isSelected()){
                tnc.setManualMode();
            }
       }

       public static void main(String[] args) {
           launch();
       }


}
