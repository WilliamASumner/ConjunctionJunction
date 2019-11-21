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

public class TrainControllerGUI extends Application {
    double speed;
    String authority;
    String name;

    public TrainControllerGUI() {
		/*
      speed = TrainController.getAuditedSpeed();
      authority = TrainController.getAuthority();
      name = TrainController.getName();
	  */
	  speed = TrainController.auditedSpeed;
	  authority = TrainController.authority;
	  name = TrainController.trainName;
    }
    public TrainControllerGUI(Stage primaryStage) {
      /*
      speed = TrainController.getAuditedSpeed();
      authority = TrainController.getAuthority();
      name = TrainController.getName();
	  */
	  speed = TrainController.auditedSpeed;
	  authority = TrainController.authority;
	  name = TrainController.trainName;
    }



    @Override
       public void start(Stage primaryStage) {
           primaryStage.setTitle(name + " - Train Controller UI");

           Button setSpeedButton = new Button("Set Speed");
           Button eBrake = new Button("E BRAKE");
           Button sBrake = new Button("Service Brake");
           Button lights = new Button("Turn Lights ON");
           eBrake.setStyle("-fx-text-fill: red");
           eBrake.setMinWidth(400);
           eBrake.setMaxWidth(400);
           eBrake.setMinWidth(100);
           eBrake.setMaxWidth(100);
           //Buttons for door control
           Button rDoor1 = new Button("Right Door 1");
           Button rDoor2 = new Button("Right Door 2");
           Button rDoor3 = new Button("Right Door 3");
           Button rDoor4 = new Button("Right Door 4");
           Button lDoor1 = new Button("Left Door 1");
           Button lDoor2 = new Button("Left Door 2");
           Button lDoor3 = new Button("Left Door 3");
           Button lDoor4 = new Button("Left Door 4");

           Label output = new Label("Train Name: " + name + "\nAudited Authority: " + authority + "\nAudited Speed Limit: " + speed); 
          
           Label modeSelectLabel = new Label("Select a Mode: "); 
           Label doorLabel = new Label("Door Status Control: "); 

           Label failureList = new Label("Track Circuit Failure: NOT Detected\nEmergency Brake Failure: NOT Detected\nService Brake Failure: NOT Detected\nEngine Failure: NOT Detected");
           Label failureTitle = new Label("Track Failure Status: ");
           RadioButton radioButton1 = new RadioButton("Automatic");
           radioButton1.setSelected(true);
           RadioButton radioButton2 = new RadioButton("Manual");
   
           ToggleGroup radioGroup = new ToggleGroup();

           Slider speedSlider = new Slider(0, speed, 0);
           speedSlider.setShowTickLabels(true);

           Slider tempSlider = new Slider(0, 80, 0);
           tempSlider.setShowTickLabels(true);
   
           radioButton1.setToggleGroup(radioGroup);
           radioButton2.setToggleGroup(radioGroup);

           HBox hbox = new HBox(radioButton1, radioButton2);

           
           FlowPane flowpane = new FlowPane();

           Button setTempButton = new Button("Set Temp");
 

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

       public static void main(String[] args) {
           launch();
       }


}
