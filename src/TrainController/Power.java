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

public class Power{
    private double Ki;
    private double Kp;
    private double powerOutput;
    private PowerGUI powerGUI;

    public Power(){
        this.init();
    }

    private void init(){
        //Set default values for Ki and Kp
        Ki = 50.0;
        Kp = 50.0;
        powerGUI = new PowerGUI(this);
    }

    public void calcPowerCommand(TrainController tnc){
        double KiOut;
        double KpOut;
        double error = tnc.driverSetSpeed - tnc.currSpeed;
        KiOut = Ki * error;
        KpOut = Kp * error;
        powerOutput = KiOut + KpOut;
    }

    public double getPowerCommand(){
        return powerOutput;
    }

    public void setKi(double i){
        Ki = i;
    }

    public void setKp(double p){
        Kp = p;
    }

    public double getKi(){
        return Ki;
    }

    public double getKp(){
        return Kp;
    }
}


