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


public class TrainModelMainGUI extends Application implements EventHandler<ActionEvent>{

    TrainModelMain tnmMain;
    //PowerGUI powerGUI;
    TrainModelGUI[] tnmGUIArray = new TrainModelGUI[20];
    TrainModel[] tnmArray;
    FlowPane  flowpane;
    //Button powerConfigButton;
    Button tnmButton;

    ComboBox trainMenu;

    public TrainModelMainGUI(Stage primaryStage){
      /*
      speed = TrainModel.getAuditedSpeed();
      authority = TrainModel.getAuthority();
      name = TrainModel.getName();
	  */

    }
    public TrainModelMainGUI(TrainModelMain tnmM) {
        tnmMain = tnmM;
        /*
        speed = TrainModel.getAuditedSpeed();
        authority = TrainModel.getAuthority();
        name = TrainModel.getName();
        */
        initGUI();
      }

      //Called from TrainModelMain every time a train is created
      @SuppressWarnings("unchecked")
      public void updateList(){
        tnmArray = tnmMain.getTrains();
        for(int i = 0; i < tnmArray.length; i++){
            if (tnmArray[i] != null)
                trainMenu.getItems().addAll(tnmArray[i].getName());
        }
      }

      private void initGUI() {

           flowpane = new FlowPane();
           flowpane.setHgap(25); 

           //powerConfigButton = new Button("Power Configuration");
           tnmButton= new Button("Train Model");

           //styling for our two buttons
           /*powerConfigButton.setStyle("-fx-text-fill: blue");
           powerConfigButton.setMinWidth(400);
           powerConfigButton.setMaxWidth(400);
           powerConfigButton.setMinWidth(100);
           powerConfigButton.setMaxWidth(100);*/
           tnmButton.setStyle("-fx-text-fill: red");
           tnmButton.setMinWidth(400);
           tnmButton.setMaxWidth(400);
           tnmButton.setMinWidth(100);
           tnmButton.setMaxWidth(100);
           

           trainMenu = new ComboBox();
         //  MenuButton trainMenu = new MenuButton("Select a train: ");

         //  menuButton.getItems().addAll();

           //FlowPane for power config button
           /*
		   FlowPane powerConfig = new FlowPane();

           powerConfig.setMinWidth(300);
           powerConfig.setStyle("-fx-border-color: black");
           powerConfig.getChildren().add(powerConfigButton);
           flowpane.getChildren().add(powerConfig);
		   */
          
           //FlowPane for train Model config button
           FlowPane trainControl = new FlowPane();
           trainControl.setMinWidth(300);
           trainControl.setStyle("-fx-border-color: black");
           trainControl.getChildren().add(tnmButton);
           trainControl.getChildren().add(trainMenu);
           flowpane.getChildren().add(trainControl);
           
           //Setting the margin of the pane  
           //flowpane.setMargin(powerConfigButton, new Insets(20, 100, 20, 20)); 
           flowpane.setMargin(tnmButton, new Insets(20, 20, 20, 20)); 


      }
      
    @Override
       public void start(Stage primaryStage) {
           primaryStage.setTitle("Train Model Module");  
           Scene scene = new Scene(flowpane, 300, 125);
           primaryStage.setScene(scene);
           primaryStage.show();
       }

       public void handle(ActionEvent event){
        Stage primaryStage = new Stage();

        //if train Model button is pressed, find the specific
        //train Model object and then open its GUI
        if(event.getSource() == tnmButton){
          for(int i = 0; i < tnmArray.length; i++){
            if(trainMenu.getValue().equals(tnmArray[i].getName())){
                tnmMain.showGUI(primaryStage, tnmArray[i]);
            }
          }
        }
		/*
        else if(event.getSource() == powerConfigButton){
          powerGUI.start(primaryStage);
        }*/
       }
       public static void main(String[] args) {
           launch();
       }


}
