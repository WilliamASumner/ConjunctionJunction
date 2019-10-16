
public class TrainController(
    int currSpeed;
    // Block authority;
    String authority;
    int auditedSpeed;   //CTC suggested speed limit
    int currTemp;
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

    string trainName;
    trainModel trainModel;
    
    //Train Controller Constructor
    public TrainController(string name, trainModel tm){
        trainName = name;
        trainModel = tm;
    }

    //Updates train's audited speed limit from the train model
    updateAuditedSpeed(){
        currSpeed = trainModel.getAuditedSpeed();
    }

    //Updates train's authority from the train model
    updateAuthority(){
        authority = trainModel.getAuthority();
    }

    //Driver sets train controller mode to automatic
    setAutomaticMode(){
        isAutomaticMode = TRUE;
    }

    //Driver sets train controller mode to manual
    setManualMode(){
        isAutomaticMode = FALSE;
    }

    //Set speed of train to new driverSetSpeed, only if it not above
    //speed limit
    setNewSpeed(integer driverSetSpeed){

        currSpeed = driverSetSpeed();
        return 0;
    }

    int calculatePower(int desiredSpeed, int currentSpeed){
        int powerOut;   //power command output, in kiloWatts

        powerOut = 0;
        return powerOut;
    }
    
)
