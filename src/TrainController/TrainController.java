import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

import java.io.IOException;
import java.io.File;

public class TrainController{
    double currSpeed;
    // Block authority;
    String authority;
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
    VBox GUI;
    String trainName;
   // trainModel TrainModel;
    
    // //Train Controller Constructor
    // public TrainController(String name, String authority, double speed, TrainModel tm){
    //     auditedSpeed = speed;
    //     authority = authority;
    //     trainName = name;
    //     trainModel = tm;
    //     initGUI();
    // }

    //Train Controller Constructor
    public TrainController(String name, String authority, double speed){
        auditedSpeed = speed;
        authority = authority;
        trainName = name;
        //trainModel = tm;
        initGUI();
    }

    //Train Controller Constructor
    public TrainController(){
        auditedSpeed = 0.0;
        authority = "";
        trainName = "";
        //trainModel = tm;
        initGUI();
    }
    //Called when train controller is created
    void initGUI() {
        FXMLLoader loader = new FXMLLoader();
        String projectBaseDir = System.getProperty("user.dir") + "/src"; // get base dir
        String fxmlPath = projectBaseDir + "/TrainController/TrainController.fxml";
        File someFile = new File(fxmlPath);
        URL tryURL = null;
        try {
            tryURL = new File(fxmlPath).toURI().toURL();
        } catch (IOException e) {
            System.out.println(e);
        }

        if (tryURL != null) {
            loader.setLocation(tryURL);
//loader.setLocation(getClass().getResource("/TrainController.fxml"));
        }
        else
            System.out.println("ERROR DID NOT SET URL");

        try {
            GUI = loader.<VBox>load();
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Unable to find TrainController fxml");
            System.exit(0);
        }
    }

    //Called when train controller selected from main menu
    void showGUI(Stage primaryStage){
        Scene scene = new Scene(GUI);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Updates train's audited speed limit from the train model
    void updateAuditedSpeed(){
        //currSpeed = trainModel.getAuditedSpeed();
    }

    //Updates train's authority from the train model
    void updateAuthority(){
        //authority = trainModel.getAuthority();
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
   
