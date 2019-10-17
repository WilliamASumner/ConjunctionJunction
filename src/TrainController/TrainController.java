import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage; 
public class TrainController{
    static double currSpeed = 60;
    // Block authority;
    static String authority = "test block";
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
    static String trainName = "train1";
    // trainModel TrainModel;
    TrainControllerGUI myGUI;
    
    // //Train Controller Constructor
    // public TrainController(String name, String authority, double speed, TrainModel tm){
    //     auditedSpeed = speed;
    //     authority = authority;
    //     trainName = name;
    //     trainModel = tm;
    //     initGUI();
    // }
    //
    //Train Controller Constructor
    public TrainController(){
        auditedSpeed = 0.0;
        authority = "";
        trainName = "";
        //trainModel = tm;
        initGUI();
    }
 
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
        myGUI = new TrainControllerGUI();
        
    }

    //Called when train controller selected from main menu
    void showGUI(Stage primaryStage){
        myGUI.start(primaryStage);
        // Scene scene = new Scene(GUI);
        // primaryStage.setScene(scene);
        // primaryStage.show();
    }

    //Updates train's audited speed limit from the train model
    public static double getAuditedSpeed(){
        return currSpeed;
    }

    //Updates train's authority from the train model
    public static String getAuthority(){
        return authority;
    }

    //Updates train's authority from the train model
    public static String getName(){
        return trainName;
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
   
