import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

public class TrainController{
    double currSpeed = 12;
    // Block authority;
    String authority = "test block";
    double auditedSpeed;   //CTC suggested speed limit
    double currTemp;
    boolean isUnderground;
    String beaconData;
    boolean eBrake_isActive;
    boolean serviceBrake_isActive;
    boolean[] LeftDoors = new boolean[4];
    boolean[] RightDoors = new boolean[4];
    int powerCommand;
    // Block currBlock;
    boolean isAutomaticMode;
    boolean engineFailure;
    boolean trackCircuitFailure;
    boolean eBrakeFailure;
    boolean serviceBreakFailure;
    boolean lightsOn;
    String trainName = "train1";
    // trainModel TrainModel;
    TrainController myGUI;
    
    // //Train Controller Constructor
    // public TrainController(String name, String authority, double speed, TrainModel tm){
    //     auditedSpeed = speed;
    //     authority = authority;
    //     trainName = name;
    //     trainModel = tm;
    //     initGUI();
    // }

    //Train Controller Constructor
    public TrainController(String name, String a, double speed){
        auditedSpeed = speed;
        authority = a;
        trainName = name;
        //trainModel = tm;
        initGUI();
    }
    //Called when train controller is created
    void initGUI(){
        
    }

    //Called when train controller selected from main menu
    void showGUI(){
        myGUI = new TrainControllerGUI();
        // Scene scene = new Scene(GUI);
        // primaryStage.setScene(scene);
        // primaryStage.show();
    }

    //Updates train's audited speed limit from the train model
    void updateAuditedSpeed(){
        currSpeed = trainModel.getAuditedSpeed();
    }

    //Updates train's authority from the train model
    void updateAuthority(){
        authority = trainModel.getAuthority();
    }

    //Driver sets train controller mode to automatic
    void setAutomaticMode(){
        isAutomaticMode = true;
    }

    //Driver sets train controller mode to manual
    void setManualMode(){
        isAutomaticMode = false;
    }

    //Set speed of train to new driverSetSpeed, only if it not above
    //speed limit
    void setNewSpeed(int driverSetSpeed){
        currSpeed = driverSetSpeed;
    }

    //@returns int calulcalatePower - power command to Train Model in kiloWatts 
    //Calculates power command based on current and desired speed
    int calculatePower(int desiredSpeed, int currentSpeed){
        int powerOut;   //power command output, in kiloWatts

        powerOut = 0;
        return powerOut;
    }
    
}
   
