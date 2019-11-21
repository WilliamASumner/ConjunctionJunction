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

    ComboBox trainMenu;

    public TrainControllerMainGUI(Stage primaryStage){
      /*
      speed = TrainController.getAuditedSpeed();
      authority = TrainController.getAuthority();
      name = TrainController.getName();
	  */

    }
    public TrainControllerMainGUI(TrainControllerMain tncM) {
        tncMain = tncM;
        /*
        speed = TrainController.getAuditedSpeed();
        authority = TrainController.getAuthority();
        name = TrainController.getName();
        */
        initGUI();
      }

      //Called from TrainControllerMain every time a train is created
      public void updateList(){
        trainMenu.getItems().removeAll(trainMenu.getItems());
        TrainController[] tncTempArray;
        tncArray = tncMain.getTrains();
        for(int i = 0; i < tncArray.length; i++){
            if (tncArray[i] != null){    
              trainMenu.getItems().addAll(tncArray[i].getName());
            }
                
        }
      }

      private void initGUI() {

           flowpane = new FlowPane();
           flowpane.setHgap(25); 

           powerConfigButton = new Button("Power Configuration");
           tncButton= new Button("Train Controller");

           //styling for our two buttons
           powerConfigButton.setStyle("-fx-text-fill: blue");
           powerConfigButton.setMinWidth(400);
           powerConfigButton.setMaxWidth(400);
           powerConfigButton.setMinWidth(100);
           powerConfigButton.setMaxWidth(100);
           tncButton.setStyle("-fx-text-fill: red");
           tncButton.setMinWidth(400);
           tncButton.setMaxWidth(400);
           tncButton.setMinWidth(100);
           tncButton.setMaxWidth(100);
           

           trainMenu = new ComboBox();
         //  MenuButton trainMenu = new MenuButton("Select a train: ");

         //  menuButton.getItems().addAll();

           //FlowPane for power config button
           FlowPane powerConfig = new FlowPane();

           powerConfig.setMinWidth(300);
           powerConfig.setStyle("-fx-border-color: black");
           powerConfig.getChildren().add(powerConfigButton);
           flowpane.getChildren().add(powerConfig);
          
           //FlowPane for power config button
           FlowPane trainControl = new FlowPane();
           trainControl.setMinWidth(300);
           trainControl.setStyle("-fx-border-color: black");
           trainControl.getChildren().add(tncButton);
           trainControl.getChildren().add(trainMenu);
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
           Scene scene = new Scene(flowpane, 300, 125);
           primaryStage.setScene(scene);
           primaryStage.show();
       }


       class powerConfigHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
          Stage newWindow = new Stage();
          tncMain.initPower().showGUI(newWindow);
        }
      }

      class tncHandler implements EventHandler<ActionEvent>{
      @Override
      public void handle(ActionEvent event){
        Stage newWindow = new Stage();
        for(int i = 0; i < tncArray.length; i++){
          if(trainMenu.getValue().equals(tncArray[i].getName())){
            System.out.println("OBJECT = " + tncArray[i]);
              tncArray[i].showGUI(newWindow);
          }
        }
      }
    }


       public void handle(ActionEvent event){
       Stage primaryStage = new Stage();

        //if train controller button is pressed, find the specific
        //train controller object and then open its GUI
        if(event.getSource() == tncButton){
          //System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
          for(int i = 0; i < tncArray.length; i++){
            if(trainMenu.getValue().equals(tncArray[i].getName())){
                //tncMain.showGUI(primaryStage, tncArray[i]);
               // System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXX");
                tncArray[i].showGUI(primaryStage);
            }
          }
        }
        else if(event.getSource() == powerConfigButton){
          powerGUI.start(primaryStage);
        }
       }
       public static void main(String[] args) {
           launch();
       }


}

