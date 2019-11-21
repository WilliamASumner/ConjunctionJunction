import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage; 
public class Power{
    private static double Ki;
    private static double Kp;
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

    public void showGUI(Stage primaryStage){
        powerGUI.start(primaryStage);
    }

    public static double calcPowerCommand(TrainController tnc){
        double powerOutput;
        double KiOut;
        double KpOut;
        double error = tnc.getSetSpeed() - tnc.getCurrSpeed();
        KiOut = Ki * error;
        KpOut = Kp * error;
        powerOutput = KiOut + KpOut;
        return powerOutput;
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


