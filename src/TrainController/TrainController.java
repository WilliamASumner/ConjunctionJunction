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
    static double driverSetSpeed;
    static String authority = "test block";
    static double auditedSpeed = 70;
    boolean[] doors = new boolean[8];
    double powerCommand;
    boolean isAutomaticMode;
    boolean engineFailure;
    boolean trackCircuitFailure;
    boolean eBrakeFailure;
    boolean serviceBreakFailure;
    boolean lightsOn;
    static String trainName = "train1";
    TrainControllerGUI myGUI;
    boolean eBrakeON;
    boolean sBrakeON;
    
    // Block authority;
    
    String beaconData;
   
    // Block currBlock;
    
    
    // trainModel TrainModel;
    
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
		System.out.println("Name: "+name+"Block: "+authority+"Speed: "+auditedSpeed);
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

   //Driver sets train controller mode to automatic
    public void setAutomaticMode(){
        isAutomaticMode = true;
    }

    //Driver sets train controller mode to manual
    public void setManualMode(){
        isAutomaticMode = false;
    }

    //Set speed of train to new driverSetSpeed, only if train is
    //in manual mode
    void setNewSpeed(int driverSetSpeed){
        if(!isAutomaticMode){
            currSpeed = driverSetSpeed;
        }
        
    }
    
    //@returns int calulcalatePower - power command to Train Model in kiloWatts 
    //Calculates power command based on current and desired speed
    double calculatePower(double desiredSpeed, double currentSpeed){
        int powerOut;   //power command output, in kiloWatts

        powerOut = 0;
        return powerOut;
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

    
}
