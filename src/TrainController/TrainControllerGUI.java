package cjunction; // conjunction junction package

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


public class TrainControllerGUI extends Application implements EventHandler<ActionEvent> {
    double speed;
    String authority;
    double powerCommand;
    String name;
    TrainController tnc;

    Slider speedSlider;

    FlowPane flowpane;

    //Buttons on TrainControllerGUI
    Button setSpeedButton = new Button("Set Speed");
    Button eBrake = new Button("E BRAKE: Currently OFF");
    Button sBrake = new Button("S BRAKE: Currently OFF");
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

    Label driverSetSpeed;
    Label currentTemp;
    Label powerLabel;

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
    public TrainControllerGUI(TrainController tc) {
      /*
      speed = TrainController.getAuditedSpeed();
      authority = TrainController.getAuthority();
      name = TrainController.getName();
      */
      tnc = tc;
	  
    }

    public void updatePowerCommand(){
        powerCommand = tnc.calculatePower();
       // powerLabel.setText("Current Power: " + Double.toString(powerCommand) + "kWatts");
    }

    public void initGUI(){
        autoRadioButton = new RadioButton("Automatic");
        setTempButton = new Button("Set Temp");

        eBrake.setStyle("-fx-text-fill: red");
        eBrake.setMinWidth(400);
        eBrake.setMaxWidth(400);
        eBrake.setMinWidth(200);
        eBrake.setMaxWidth(200);

        sBrake.setStyle("-fx-text-fill: blue");
        sBrake.setMinWidth(400);
        sBrake.setMaxWidth(400);
        sBrake.setMinWidth(200);
        sBrake.setMaxWidth(200);

        powerLabel = new Label("Current Power: 0 kWatts");

        Label modeSelectLabel = new Label("Select a Mode: "); 
        Label doorLabel = new Label("Door Status Control: "); 

        Label failureList = new Label("Track Circuit Failure: NOT Detected\nEmergency Brake Failure: NOT Detected\nService Brake Failure: NOT Detected\nEngine Failure: NOT Detected");
        Label failureTitle = new Label("Track Failure Status: ");
        
        autoRadioButton.setSelected(true);
        manualRadioButton = new RadioButton("Manual");
   
        ToggleGroup radioGroup = new ToggleGroup();

         speedSlider = new Slider(0, tnc.getSetSpeed(), 0);
         //speedSlider = new Slider(0, 80, 0);
         speedSlider.setShowTickLabels(true);


        //Uncomment first one when integrated with train model
         //driverSetSpeed = new Label("Current Set Speed Limit" + tnc.getSetSpeed());
         driverSetSpeed = new Label("Current Set Speed Limit " + tnc.getSetSpeed() + " MPH");



         currentTemp = new Label("Current Temperature: " + tnc.getTemp() + " Fahrenheit");

         tempSlider = new Slider(0, 80, 0);
         tempSlider.setShowTickLabels(true);
    
         autoRadioButton.setToggleGroup(radioGroup);
         manualRadioButton.setToggleGroup(radioGroup);
 
         HBox hbox = new HBox(autoRadioButton, manualRadioButton);
 
            
         flowpane = new FlowPane();
 
 
         FlowPane flowpane1 = new FlowPane();
         FlowPane speed = new FlowPane();
         FlowPane temp = new FlowPane();
         flowpane1.setStyle("-fx-border-color: black");
         speed.getChildren().add(speedSlider);
         speed.getChildren().add(setSpeedButton);
         speed.getChildren().add(driverSetSpeed);
         
         temp.getChildren().add(tempSlider);  
         temp.getChildren().add(setTempButton);
         temp.getChildren().add(currentTemp);

         flowpane1.getChildren().add(powerLabel);
         flowpane1.getChildren().add(speed);
         flowpane1.getChildren().add(temp);
         flowpane.getChildren().add(flowpane1);
        // flowpane1.getChildren().add(output);
        
 
 
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
 
         setSpeedButton.setOnAction(new setSpeedHandler());
         eBrake.setOnAction(new eBrakeHandler());
         sBrake.setOnAction(new sBrakeHandler());
         lights.setOnAction(new lightsHandler());
         rDoor1.setOnAction(new rDoor1Handler());
         rDoor2.setOnAction(new rDoor2Handler());
         rDoor3.setOnAction(new rDoor3Handler());
         rDoor4.setOnAction(new rDoor4Handler());
         lDoor1.setOnAction(new lDoor1Handler());
         lDoor2.setOnAction(new lDoor2Handler());
         lDoor3.setOnAction(new lDoor3Handler());
         lDoor4.setOnAction(new lDoor4Handler());
         setTempButton.setOnAction(new tempHandler());
         autoRadioButton.setOnAction(new autoModeHandler());
         manualRadioButton.setOnAction(new manualModeHandler());

        
    }
    

    @Override
       public void start(Stage primaryStage) {
        initGUI();
        
        //primaryStage.setTitle(name + " - Train Controller UI");

        
        //Label output = new Label("Train Name: " + name + "\nAudited Authority: " + authority + "\nAudited Speed Limit: " + speed); 
        
        Scene scene = new Scene(flowpane, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
       }
	   
	   public void setEbrake(boolean newEbrake)
	{
		 if(newEbrake){
                    eBrake.setText("E Brake: Currently ON");
                    System.out.println("TrainControllerGUI: Turning Emergency Brake ON...");
                }
                else{
                    eBrake.setText("E Brake: Currently OFF");
                    System.out.println("TrainControllerGUI: Turning Emergency Brake OFF...");
                }
	}


      public class setSpeedHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            boolean test = tnc.setNewSpeed(speedSlider.getValue());
            if(test){
                driverSetSpeed.setText("Current Set Speed Limit: " + speedSlider.getValue() + " MPH");
                System.out.println("TrainControllerGUI: Setting new speed of " + speedSlider.getValue());
            }
        }
      }

      
      class eBrakeHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            boolean currentState;
                currentState = tnc.toggleEBrake();
        }
      }

      class sBrakeHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            boolean currentState;
                currentState = tnc.toggleServiceBrake();
                if(currentState){
                    sBrake.setText("S Brake: Currently ON");
                    System.out.println("TrainControllerGUI: Turning Service Brake ON...");
                }
                else{
                    sBrake.setText("S Brake: Currently OFF");
                    System.out.println("TrainControllerGUI: Turning Service Brake OFF...");

                }
        }
      }
      class lightsHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            boolean currentState;
            currentState = tnc.setLights();
            if (currentState){
                lights.setText("Lights Currently ON");
                System.out.println("TrainControllerGUI: Turning Lights ON...");
            }
            else{
                lights.setText("Lights Currently OFF");
                System.out.println("TrainControllerGUI: Turning Lights OFF...");
            }
        }
      }

      class rDoor1Handler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            boolean currentState;
            currentState = tnc.toggleDoor(0);
            if(currentState){
                rDoor1.setText("Right Door 1 Currently Open");
                System.out.println("TrainControllerGUI: Opening Right Door 1...");
            }
            else{
                rDoor1.setText("Right Door 1 Currently Closed");
                System.out.println("TrainControllerGUI: Closing Right Door 1...");
            }
        }
      }

      class rDoor2Handler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            boolean currentState;
            currentState = tnc.toggleDoor(1);
            if(currentState){
                rDoor2.setText("Right Door 2 Currently Open");
                System.out.println("TrainControllerGUI: Opening Right Door 2 ...");
            }
            else{
                rDoor2.setText("Right Door 2 Currently Closed");
                System.out.println("TrainControllerGUI: Closing Right Door 2...");
            }
        }
      }

      class rDoor3Handler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            boolean currentState;
            currentState = tnc.toggleDoor(2);
            if(currentState){
                rDoor3.setText("Right Door 3 Currently Open");
                System.out.println("TrainControllerGUI: Opeing Right Door 3...");
            }
            else{
                rDoor3.setText("Right Door 3 Currently Closed");
                System.out.println("TrainControllerGUI: Closing Right Door 3...");
            }
        }
      }

      class rDoor4Handler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            boolean currentState;
            currentState = tnc.toggleDoor(3);
            if(currentState){
                rDoor4.setText("Right Door 4 Currently Open");
                System.out.println("TrainControllerGUI: Opening Right Door 4...");
            }
            else{
                rDoor4.setText("Right Door 4 Currently Closed");
                System.out.println("TrainControllerGUI: Closing Right Door 4...");
            }
        }
      }

      class lDoor1Handler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            boolean currentState;
            currentState = tnc.toggleDoor(4);
            if(currentState){
                lDoor1.setText("Left Door 1 Currently Open");
                System.out.println("TrainControllerGUI: Opening Left Door 1...");
            }
            else{
                lDoor1.setText("Left Door 1 Currently Closed");
                System.out.println("TrainControllerGUI: Closing Left Door 1...");
            }
        }
      }

      class lDoor2Handler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            boolean currentState;
            currentState = tnc.toggleDoor(5);
            if(currentState){
                lDoor2.setText("Left Door 2 Currently Open");
                System.out.println("TrainControllerGUI: Opening Left Door 2...");
            }
            else{
                lDoor2.setText("Left Door 2 Currently Closed");
                System.out.println("TrainControllerGUI: Closing Left Door 2...");
            }
        }
      }

      class lDoor3Handler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            boolean currentState;
            currentState = tnc.toggleDoor(6);
            if(currentState){
                lDoor3.setText("Left Door 3 Currently Open");
                System.out.println("TrainControllerGUI: Opening Left Door 3...");
            }
            else{
                lDoor3.setText("Left Door 3 Currently Closed");
                System.out.println("TrainControllerGUI: Closing Left Door 3...");
            }
        }
      }

      class lDoor4Handler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            boolean currentState;
            currentState = tnc.toggleDoor(7);
            if(currentState){
                lDoor4.setText("Left Door 4 Currently Open");
                System.out.println("TrainControllerGUI: Opening Left Door 4...");
            }
            else{
                lDoor4.setText("Left Door 4 Currently Closed");
                System.out.println("TrainControllerGUI: Closing Left Door 4...");
            }
        }
      }

      class tempHandler implements EventHandler<ActionEvent>{
          @Override
          public void handle(ActionEvent event){
            tnc.setTemp(tempSlider.getValue());
            currentTemp.setText("TrainControllerGUI: Current Temperature: " + tnc.getTemp() + " Fahrenheit");
          }
      }
      

      class autoModeHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            tnc.setAutomaticMode();
        }
    }

    class manualModeHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            tnc.setManualMode();
        }
    }

    public void handle(ActionEvent event){
        

    }
      

       public static void main(String[] args) {
           launch();
       }


}


