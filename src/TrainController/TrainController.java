package cjunction; // conjunction junction package

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
    double temperature = 70.0;
    Block currBlock;
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
    Power myPower;
    int set = 0;
    
    //Train Controller Constructor
    public TrainController(String name, String a, double speed, TrainModel trainModel){
        auditedSpeed = speed;
        authority = a;
        trainName = name;
        tm = trainModel;
		//System.out.println("TrainController: Name: "+name+"Block: "+authority+"Speed: "+auditedSpeed);
        initGUI();
        initPower();
    }

    /*create the Power class object, called by
    TrainControllerMainGUI the first time
    that the Power gui is opened*/
    public Power initPower(){
        myPower = new Power();
        return myPower;
    }

    /*returns this specific train controllers reference
    to its Power object, called from TrainControllerMainGUI*/
    public Power getPower(){
        return myPower;
    }



    //Called when train controller is created
    void initGUI(){
        myGUI = new TrainControllerGUI(this);

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
        setCurrBlock();
        currSpeed = getCurrSpeed();

        if(currSpeed > (1.05*auditedSpeed)){
            toggleServiceBrake();
            set = 1;
        }
        else if(set == 1){
            toggleServiceBrake();
            set = 0;
        }
        //System.out.println("TrainController: Speed = " +currSpeed);
        //myGUI.updatePowerCommand();
    }

    //Called when train controller selected from main menu
    void showGUI(Stage primaryStage){
       // System.out.println("TrainController: Hello from Train Controller:" + this);
        myGUI.start(primaryStage);
    }

    public void setCurrBlock(){
        currBlock = tm.getCurrentBlock();
    }

    public Block getCurrBlock(){
        return currBlock;
    }

    public double getCurrSpeed(){
        return tm.getVelocity();
    }

    public double getSetSpeed(){
        //if we are in automatic mode, then the set speed
        //is the audited speed limit of the track
        if(isAutomaticMode){
            if(auditedSpeed > 0){
                return auditedSpeed;
            }
            else{
                return 0;
            }
        }
        //if we are in manual mode, return driver set speed
        else{
            if(driverSetSpeed > 0){
                return driverSetSpeed;
            }
            else{
                return 0;
            }

        }
    }

    //Updates train's audited speed limit from the train model
    public void getAuditedSpeed(){
        if(!trackCircuitFailure){
            auditedSpeed = tm.getAuditedSpeed();
        }
    }

    //Updates train's authority from the train model
    public void getAuthority(){
        if(!trackCircuitFailure){
            authority = tm.getAuthority();
        }
    }

    //Updates train's authority from the train model
    public String getName(){
        return trainName;
    }

    //Set engine failure, called from TrainModel
    public void setEngineFailure(boolean state){
        engineFailure = state;
        myGUI.setPowerFailureText(state);
    }

    //Set track signal failure, called from TrainModel
    public void setSignalFailure(boolean state){
        trackCircuitFailure = state;
        myGUI.setSignalFailureText(state);
    }

    //Set e brake failure, called from TrainModel
    public void setEBrakeFailure(boolean state){
        eBrakeFailure = state;
        myGUI.setEBrakeFailureText(state);
    }

    //Set s brake failure, called from TrainModel
    public void setSBrakeFailure(boolean state){
        serviceBrakeFailure = state;
        myGUI.setSBrakeFailureText(state);
    }

    //Driver sets train controller mode to automatic
    public void setAutomaticMode(){
        /*
        Note: when we switch from automatic to manual mode we begin using
        the driverSetSpeed as the new set speed for the power calculations
        so as to not have the speed of the train affected by switching
        from auto to manual mode we set the driverSetSpeed to the current
        speed of the train
        */
        driverSetSpeed = currSpeed;
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
    public boolean setNewSpeed(double input){
        if(!isAutomaticMode){
            driverSetSpeed = input;
            return true;
        }
        return false;

    }

    //Set temperature of train cabin to newTemp value (fahrenheit)
    public void setTemp(double newTemp){
        temperature = newTemp;
        tm.setTemperature(newTemp);
    }

    public double getTemp(){
        return temperature;
    }

    //Toggle the emergency brake, called from TrainControllerGUI
    public boolean toggleEBrake(){
        boolean currentState;
        //if we do not currently have an e brake failure then toggle
        if(!eBrakeFailure){
            if(eBrakeOn){
                eBrakeOn = false;
            }
            else{
                eBrakeOn = true;
            }
            tm.toggleEBrake();
        }
        currentState = eBrakeOn;
        return currentState;
    }

    //Toggle the service brake, called from TrainControllerGUI
    public boolean toggleServiceBrake(){
        boolean currentState;
        //if we do not currently have an s brake failure then toggle
        if(!serviceBrakeFailure){
            if(sBrakeOn){
                sBrakeOn = false;
            }
            else{
                sBrakeOn = true;
            }
            tm.toggleSBrake();
        }
        currentState = sBrakeOn;
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

    //@returns double calculatePower - power command to Train Model in kiloWatts
    //Calculates power command based on current and desired speed
    public double calculatePower(){
        double powerOut;   //power command output, in kiloWatts
        //Power.calcPowerCommand(this);
        powerOut = myPower.calcPowerCommand(this);
       // System.out.println("TrainController: TRAIN: " + this + " - Power CMD " + powerOut);

       if(currBlock != null){
        if(eBrakeOn || sBrakeOn || currBlock.getBlockID() == authority){
            powerOut = 0;
        }
       }

        return powerOut;
    }



}
