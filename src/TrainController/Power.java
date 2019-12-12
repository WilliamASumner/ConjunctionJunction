package cjunction; // conjunction junction package

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage; 

public class Power{
    public double Ki = 1.0;
    public double Kp = 1.0;
    public double safetyLimit = 0.1;
   
    private double powerOutput;
    private PowerGUI powerGUI;

    //Power Constructor
    public Power(){
        this.init();
    }

    private void init(){
        //Set default values for Ki and Kp
        Ki = 1.0;
        Kp = 1.0;
        powerGUI = new PowerGUI(this);
    }

    public void showGUI(Stage primaryStage){
        powerGUI = new PowerGUI(this);
        powerGUI.start(primaryStage);
    }

    /*@RETURNS double powerCommand - Safety-Critical function called
    by calculatePower() function within TrainController. TMR System
    implemented where the powerCommand is calculated three times and 
    the difference is checked to make sure it is within our 
    safetyLimit (currently set to 0.1)*/
    public double calcPowerCommand(TrainController tnc){
        double power1 = calculatePower1(tnc);
        double power2 = calculatePower1(tnc);
        double power3 = calculatePower1(tnc);

        /*If any of our two power calculations are within a safetyLimit(0.1) to eachother,
        return the average of the two calculations, if not return 0 */
        if((power1 - power2)/power1 < safetyLimit){
            return (power1 + power2)/2;
        }
        else if((power1 - power3)/power1 < safetyLimit){
            return (power1 + power3)/2;
        }
        else if((power2 - power3)/power2 < safetyLimit){
            return (power2 + power3)/2;
        }
        else
            return 0;
    }

    //First Power Calculation for TMR System
    private double calculatePower1(TrainController tnc){
        double powerOutput;
        double KiOut;
        double KpOut;
        double error = tnc.getSetSpeed() - tnc.getCurrSpeed();
        KpOut = Kp * error;
        KiOut = Ki * error;
        powerOutput = KiOut + KpOut;
        return powerOutput;
    }

    //Second Power Calculation for TMR System
    private double calculatePower2(TrainController tnc){
        double powerOutput;
        double KiOut;
        double KpOut;
        double error = tnc.getSetSpeed() - tnc.getCurrSpeed();
        KiOut = Ki * error;
        KpOut = Kp * error;
        powerOutput = KiOut + KpOut;
        return powerOutput;
    }

    //Third Power Calculation for TMR System
    private double calculatePower3(TrainController tnc){
        double powerOutput;
        double error = tnc.getSetSpeed() - tnc.getCurrSpeed();
        powerOutput = (Ki*error) + (Kp*error);
        return powerOutput;
    }


    public double getPowerCommand(){
        return powerOutput;
    }

    //Set a new Ki value
    public void setKi(double i){
        System.out.println("Power: Ki: " + Ki + " --> "+ i);
		Ki = i;
    }

    //Set a new Kp value
    public void setKp(double p){
        System.out.println("Power: Kp: " + Kp + " --> "+ p);
        Kp = p;
    }

    public double getKi(){
        return Ki;
    }

    public double getKp(){
        return Kp;
    }
}


