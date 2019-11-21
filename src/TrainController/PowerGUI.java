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

public class PowerGUI extends Application implements EventHandler<ActionEvent> {
    int Ki;
    int Kp;
    Power p;

    public PowerGUI(Power p) {
        p=p;
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



    @Override
       public void start(Stage primaryStage) {
           primaryStage.setTitle("Power UI");

           //Labels to display the current Ki and Kp values
           Label currKi = new Label("Current Ki: " + p.getKi());
           Label currKp = new Label("Current Kp: " + p.getKp());

           Label Ki = new Label("Set Ki:    "); 
           Label Kp = new Label("Set Kp:    "); 
           
           FlowPane flowpane = new FlowPane();

           Scene scene = new Scene(flowpane, 500, 300);
           primaryStage.setScene(scene);
           primaryStage.show();
       }

       public static void main(String[] args) {
           launch();
       }


}
