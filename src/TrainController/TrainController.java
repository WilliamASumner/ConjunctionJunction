import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioEqualizer;
import javafx.stage.Stage; 
public class TrainController{
    static double currSpeed = 60;
    double driverSetSpeed = 0;
    static String authority = "test block";
    static double auditedSpeed = 70;
    String beaconData;
    boolean serviceBrake_isActive;
    boolean[] Doors = new boolean[8];
    double powerCommand;
    double temperature;
    // Block currBlock;
    boolean eBrakeOn;
    boolean sBrakeOn;
    boolean isAutomaticMode;
    boolean engineFailure;
    boolean trackCircuitFailure;
    boolean eBrakeFailure;
    boolean serviceBreakFailure;
    boolean lightsOn;
    static String trainName = "train1";
    TrainModel tm;
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
		System.out.println("Name: "+name+"Block: "+authority+"Speed: "+auditedSpeed);
        initGUI();
    }
    //Called when train controller is created
    void initGUI(){
        myGUI = new TrainControllerGUI();
        
    }

    public TrainControllerGUI getGUI(){
        return myGUI;
    }

    //function called on each system tick
    public void update(){
        powerCommand = calculatePower();
        //If either brake is active then power command is 0
        if(sBrakeOn || eBrakeOn){
            powerCommand = 0;
        }
        getAuditedSpeed();
        getAuthority();
    }

    //Called when train controller selected from main menu
    void showGUI(Stage primaryStage){
        myGUI.start(primaryStage,this);
        // Scene scene = new Scene(GUI);
        // primaryStage.setScene(scene);
        // primaryStage.show();
    }

    //Updates train's audited speed limit from the train model
    public static void getAuditedSpeed(){
        auditedSpeed = tm.getAuditedSpeed();
    }

    //Updates train's authority from the train model
    public static String getAuthority(){
        authority = tm.getAuthority();
    }

    //Updates train's authority from the train model
    public String getName(){
        return trainName;
    }

    //Driver sets train controller mode to automatic
    public void setAutomaticMode(){
        isAutomaticMode = true;
    }

    //Driver sets train controller mode to manual
    public void setManualMode(){
        isAutomaticMode = false;
    }

    //toggle the lights
    //called from TrainControllerGUI when the toggle lights button
    //is pressed
    public boolean setLights(){
        boolean currentState;
        if(lightsOn){
            tm.lightsOn = false;
            lightsOn = false;
        }
        else{
            tm.lightsOn = true;
            lightsOn = true;
        }  
        currentState = lightsOn;
        return currentState;
    }

    //Set speed of train to new driverSetSpeed, only if it not above
    //speed limit
    public void setNewSpeed(int driverSetSpeed){
        currSpeed = driverSetSpeed;
    }

    //Set temperature of train cabin to newTemp value (fahrenheit)
    public void setTemp(double newTemp){
        temperature = newTemp;
    }

    //Toggle the emergency brake, called from TrainControllerGUI
    public boolean toggleEBrake(){
        boolean currentState;
        if(eBrakeOn){
            tm.EBrake_isActive = false;
            eBrakeOn = false;
        }
        else{
            tm.EBrake_isActive = true;
            eBrakeOn = true;
        }
        currentState = eBrakeOn;
        return currentState;
    }

    //Toggle the service brake, called from TrainControllerGUI
    public boolean toggleSBrake(){
        boolean currentState;
        if(sBrakeOn){
            tm.toggleEBrake();
            eBrakeOn = false;
        }
        else{
            tm.toggleSBrake();
            eBrakeOn = true;
        }
        currentState = eBrakeOn;
        return currentState;
    }

    //Driver inputs a new driver set speed, called from TrainControllerGUI
    public void newDriverSetSpeed(double setSpeed){
        //check that the driver setSpeed is less our speed limit
        if((setSpeed < auditedSpeed) && (setSpeed > 0) && (!isAutomaticMode)){
            driverSetSpeed = setSpeed;
        }
    }

    public boolean toggleDoor(int doorNum){
        boolean currentState;
            if(Doors[doorNum]){
                Doors[doorNum] = false;
                tm.setDoorStatus(Doors);
                //ADD UPDATE TO TRAIN MODEL DOOR STATUS
            }
            else{
                Doors[doorNum] = true;
                tm.setDoorStatus(Doors);
                 //ADD UPDATE TO TRAIN MODEL DOOR STATUS
            }
            currentState = Doors[doorNum];
            return currentState;
    }
    //@returns int calulcalatePower - power command to Train Model in kiloWatts 
    //Calculates power command based on current and desired speed
    private double calculatePower(){
        int powerOut;   //power command output, in kiloWatts
        Power.calcPowerCommand(this);
        powerOut = Power.getPowerCommand();
        return powerOut;
    }
    
}