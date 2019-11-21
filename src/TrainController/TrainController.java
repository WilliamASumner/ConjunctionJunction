import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage; 

//import javafx.event.ActionEvent;




public class TrainController{
    private double currSpeed = 60;
    private double driverSetSpeed = 0;
    String authority = "test block";
    double auditedSpeed = 70;
    String beaconData;
    boolean serviceBrake_isActive;
    boolean[] Doors = new boolean[8];
    double powerCommand;
    double temperature;
    // Block currBlock;
    boolean eBrakeOn;
    boolean sBrakeOn;
    boolean isAutomaticMode = true;
    boolean engineFailure;
    boolean trackCircuitFailure;
    boolean eBrakeFailure;
    boolean serviceBrakeFailure;
    boolean lightsOn = false;
    String trainName = "train1";
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
    public TrainController(String name, String a, double speed, TrainModel trainModel){
        auditedSpeed = speed;
        authority = a;
        trainName = name;
        tm = trainModel;
		//System.out.println("Name: "+name+"Block: "+authority+"Speed: "+auditedSpeed);
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
        myGUI.start(primaryStage);
        // Scene scene = new Scene(GUI);
        // primaryStage.setScene(scene);
        // primaryStage.show();
    }

    

    public double getCurrSpeed(){
        return currSpeed;
    }

    public double getSetSpeed(){
        //if we are in automatic mode, then the set speed
        //is the audited speed limit of the track
        if(isAutomaticMode){
            return auditedSpeed;
        }
        //if we are in manual mode, return driver set speed
        else{
            return driverSetSpeed;
        }
    }

    //Updates train's audited speed limit from the train model
    public void getAuditedSpeed(){
    //    auditedSpeed = tm.getAuditedSpeed();
    }

    //Updates train's authority from the train model
    public void getAuthority(){
    //    authority = tm.getAuthority();
    }

    //Updates train's authority from the train model
    public String getName(){
        return trainName;
    }

    public void setEngineFailure(boolean state){
        engineFailure = state;
    }

    public void setSignalFailure(boolean state){
        trackCircuitFailure = state;
    }

    public void setEBrakeFailure(boolean state){
        eBrakeFailure = state;
    }

    public void setSBrakeFailure(boolean state){
        serviceBrakeFailure = state;
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
            lightsOn = false;
        }
        else{
            lightsOn = true;
        }  
        tm.toggleLights();
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
        tm.setTemperature(newTemp);
    }

    //Toggle the emergency brake, called from TrainControllerGUI
    public boolean toggleEBrake(){
        boolean currentState;
        if(eBrakeOn){
            eBrakeOn = false;
        }
        else{
            eBrakeOn = true;
        }
        tm.toggleEBrake();
        currentState = eBrakeOn;
        return currentState;
    }

    //Toggle the service brake, called from TrainControllerGUI
    public boolean toggleServiceBrake(){
        boolean currentState;
        if(sBrakeOn){
            eBrakeOn = false;
        }
        else{
            eBrakeOn = true;
        }
        tm.toggleSBrake();
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
            }
            else{
                Doors[doorNum] = true;
                tm.setDoorStatus(Doors);
            }
            currentState = Doors[doorNum];
            return currentState;
    }
    //@returns dobule calculatePower - power command to Train Model in kiloWatts 
    //Calculates power command based on current and desired speed
    public double calculatePower(){
        double powerOut;   //power command output, in kiloWatts
        //Power.calcPowerCommand(this);
        powerOut = Power.calcPowerCommand(this);
        powerOut = 0.01; // TODO FIXME
        return powerOut;
    }


    
}
