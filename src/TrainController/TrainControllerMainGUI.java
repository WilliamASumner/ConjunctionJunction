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
import java.util.*;
import javafx.scene.image.*; 


public class TrainControllerMainGUI extends Application implements EventHandler<ActionEvent>{

    TrainControllerMain tncMain;
    PowerGUI powerGUI;
    TrainController tnc;
    TrainControllerGUI[] tncGUIArray = new TrainControllerGUI[20];
    TrainController[] tncArray;
    FlowPane  flowpane;
    Button powerConfigButton;
    Button tncButton;
    Power power;
    Scene scene;
    int j;

    ComboBox trainMenuController;  //train menu for Train Controller selection
    ComboBox trainMenuPowerConfig; //train menu for Power Config selection

    public TrainControllerMainGUI(TrainControllerMain tncM) {
        tncMain = tncM;
        initGUI();
      }

      //Called from TrainControllerMain every time a train is created
      @SuppressWarnings("unchecked")
      public void updateList(){

        tncArray = tncMain.getTrains();
        TrainController[] tncArrayTemp = new TrainController[20];
        //If our current block is A0, then we are back at the yard so we dereference train
        for(int i = 0; i < tncArray.length; i++){
          if (tncArray[i] != null && tncArray[i].getCurrBlock() != null && tncArray[i].getCurrBlock().getBlockID() != null ){
            if(tncArray[i].getCurrBlock().getBlockID() != "A0"){
              tncArrayTemp[i] = tncArray[i];
            }    
            
         }
        }  

        for(int i = 0; i < tncArray.length; i++){
        System.out.println("orig = " + tncArray[i]);
        System.out.println("temp = " + tncArrayTemp[i]);
        }
        //tncArray = tncArrayTemp;

        trainMenuController.getItems().removeAll(trainMenuController.getItems());
        trainMenuPowerConfig.getItems().removeAll(trainMenuPowerConfig.getItems());

        
        for(int i = 0; i < tncArray.length; i++){
            if (tncArray[i] != null){    
              trainMenuController.getItems().addAll(tncArray[i].getName());
              trainMenuPowerConfig.getItems().addAll(tncArray[i].getName());
            }
                
        }


      }

      private void initGUI() {

           flowpane = new FlowPane();
           flowpane.setHgap(25); 

           powerConfigButton = new Button("Train Controller Power Parameters");
           tncButton= new Button("Train Controller");

           //styling for our two buttons
           powerConfigButton.setStyle("-fx-text-fill: blue");
           powerConfigButton.setMinWidth(800);
           powerConfigButton.setMaxWidth(800);
           powerConfigButton.setMinWidth(200);
           powerConfigButton.setMaxWidth(200);
           tncButton.setStyle("-fx-text-fill: red");
           tncButton.setMinWidth(400);
           tncButton.setMaxWidth(400);
           tncButton.setMinWidth(100);
           tncButton.setMaxWidth(100);
           

           trainMenuController = new ComboBox();
           trainMenuPowerConfig = new ComboBox();

           //FlowPane for power config button
           FlowPane powerConfig = new FlowPane();

           powerConfig.setMinWidth(300);
           powerConfig.setStyle("-fx-border-color: black");
           powerConfig.getChildren().add(powerConfigButton);
           powerConfig.getChildren().add(trainMenuPowerConfig);
           flowpane.getChildren().add(powerConfig);
          
           //FlowPane for power config button
           FlowPane trainControl = new FlowPane();
           trainControl.setMinWidth(300);
           trainControl.setStyle("-fx-border-color: black");
           trainControl.getChildren().add(tncButton);
           trainControl.getChildren().add(trainMenuController);
           flowpane.getChildren().add(trainControl);
           
           //Setting the margin of the pane  
           flowpane.setMargin(powerConfigButton, new Insets(20, 100, 20, 20)); 
           flowpane.setMargin(tncButton, new Insets(20, 20, 20, 20)); 


      }
      
    @Override
       public void start(Stage primaryStage) {
           primaryStage.setTitle("Train Controller Module");  
           powerConfigButton.setOnAction(new powerConfigHandler());
           tncButton.setOnAction(new tncHandler());
           if(scene == null){
            scene = new Scene(flowpane, 300, 125);
           }

           primaryStage.setScene(scene);
           primaryStage.show();
       }


       class powerConfigHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
          //if selection is not empty
          if(trainMenuPowerConfig.getValue() != null){
          Stage newWindow = new Stage();
          //iterate through array of trains and find the selected one
          for(int i = 0; i < tncArray.length; i++){
            if(tncArray!=null && tncArray[i]!=null){
              
              if(trainMenuPowerConfig.getValue().equals(tncArray[i].getName())){
                /*  Note: if its the first time power Config button is pressed,
                    then we call initPower() to create the power object,
                    if it is second or later time pressing we call
                    getPower() to get the power object to show
                */
              if(j == 1){
                  tncArray[i].initPower().showGUI(newWindow);
                  j = 0;
              }
              else{
                tncArray[i].getPower().showGUI(newWindow);
              }
              }
            }
          }  
        }

        }
      }

      class tncHandler implements EventHandler<ActionEvent>{
      @Override
      public void handle(ActionEvent event){
        if(trainMenuController.getValue() != null){
          Stage newWindow = new Stage();
          for(int i = 0; i < tncArray.length; i++){
          if(tncArray!=null && tncArray[i]!=null){
            if(trainMenuController.getValue().equals(tncArray[i].getName())){
                tncArray[i].showGUI(newWindow);
            }
          }
          }
        }
      }
    }

       public void handle(ActionEvent event){
       
       }

       
       public static void main(String[] args) {
           launch();
       }


}
