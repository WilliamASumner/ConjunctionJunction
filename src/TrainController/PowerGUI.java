package cjunction; // conjunction junction package

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

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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

public class PowerGUI extends Application implements EventHandler<ActionEvent> {
    int Ki;
    int Kp;
    Power p;
    FlowPane flowpane;
    Button KiButton;
    Button KpButton;
    TextField KiValue;
    TextField KpValue;
    Label currentKi;
    Label currentKp;

    public PowerGUI(Power powerRef) {
        p=powerRef;
        initGUI();
		/*
      speed = TrainController.getAuditedSpeed();
      authority = TrainController.getAuthority();
      name = TrainController.getName();
	  */

    }
    public PowerGUI(Stage primaryStage) {
      /*
      speed = TrainController.getAuditedSpeed();
      authority = TrainController.getAuthority();
      name = TrainController.getName();
	  */
    }

    public void initGUI(){
        FlowPane Kiflowpane = new FlowPane();
        FlowPane Kpflowpane = new FlowPane();
        flowpane = new FlowPane();

        Kiflowpane.getChildren().add(new Label("Set Ki: "));
        KiValue = new TextField();
        Kiflowpane.getChildren().add(KiValue);
        KiButton = new Button("Set new Ki");
        Kiflowpane.getChildren().add(KiButton);
        currentKi = new Label("Current Ki Value: " + Double.toString(p.getKi()));
        Kiflowpane.getChildren().add(currentKi);

        Kpflowpane.getChildren().add(new Label("Set Kp: "));
        KpValue = new TextField();
        Kpflowpane.getChildren().add(KpValue);
        KpButton = new Button("Set new Kp");
        Kpflowpane.getChildren().add(KpButton);
        currentKp = new Label("Current Kp Value: " + Double.toString(p.getKp()));
        Kpflowpane.getChildren().add(currentKp);


        flowpane.getChildren().add(Kiflowpane);
        flowpane.getChildren().add(Kpflowpane);

    }



    @Override
       public void start(Stage primaryStage) {
           primaryStage.setTitle("Power Configuration UI");

           KiButton.setOnAction(new KiButtonHandler());
           KpButton.setOnAction(new KpButtonHandler());

           //Labels to display the current Ki and Kp values
          // Label currKi = new Label("Current Ki: " + p.getKi());
          // Label currKp = new Label("Current Kp: " + p.getKp());

           Scene scene = new Scene(flowpane, 500, 300);
           primaryStage.setScene(scene);
           primaryStage.show();
       }

       public class KiButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            p.setKi(Double.valueOf(KiValue.getText()));
            currentKi.setText("Current Ki Value: " + Double.toString(p.getKi()));
        }
      }


      public class KpButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            p.setKp(Double.valueOf(KpValue.getText()));
            currentKp.setText("Current Kp Value: " + Double.toString(p.getKp()));
        }
      }


       public static void main(String[] args) {
           launch();
       }

       //Action Listeners for our button
       public void handle(ActionEvent event){
           
       }

}
	
