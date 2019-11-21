import javafx.stage.Stage;

public class TrainModel {

    String name = "test name";
	
    String AuthorityBlockID = "test Block";
    double AuditedSpeed = 3.14;
	Block AuditedAuthority;
	
    TrainControllerMain TNC = null;
    TrainController singleTNC = null;
    TrainModelGUI myGUI;
    Block currBlock;
    TkM trackModel = null;


    boolean[] Doors = new boolean[8];
    boolean lights = false;
    double temperature = 70;

    double powerCommand = 0; //kiloWatts
    double grade = 0;
    double gravity = 9.81;
    static double kilosPerPound = 0.453592;


    static double trainMass = 40.9; //tons
    static int currPassengers = 0; 
    static double passMass = 180; // pounds
    static double estimatedmass = kilosPerPound*(2000*trainMass + passMass*currPassengers);//kilos of train
    TrainControllerMain TNC_Main = null;
    public void setPassenger(int input)
    {
        currPassengers = input;
        estimatedmass = kilosPerPound*(2000*trainMass + passMass*currPassengers);
        return;
    }

    boolean EbrakeFail = false;
    boolean Ebrake = false;
    double EbrakeAcc = -2.73; //meters/second^2

    boolean SbrakeFail = false;
    boolean Sbrake = false;
    double SbrakeAcc = -1.2; //meters/second^2

    boolean signalFail = false;
    boolean engineFail = false;

    double currBlockLength = 1000;

    double distanceTraveled = 0;
    double velocity = 0;
    double altvelocity = 0.01;
    double acceleration = 0;
    double timePerUpdate = 3;

    public void toggleEBrakeFail()
    {
        if (EbrakeFail)
        {

            EbrakeFail=false;
            singleTNC.setSBrakeFailure(false);
        }
        else
        {
            EbrakeFail=true;
            singleTNC.setSBrakeFailure(true);
            Ebrake = false;
        }
        return;
    }

    public void toggleSBrakeFail()
    {
        if (SbrakeFail)
        {
            SbrakeFail=false;
            singleTNC.setSBrakeFailure(false);
        }
        else
        {
            SbrakeFail=true;
            Sbrake = false;
            singleTNC.setSBrakeFailure(true);
        }
        return;
    }
    public void toggleSignalFail()
    {
        if (signalFail)
        {
            signalFail=false;
            singleTNC.setSignalFailure(false);
        }

        else
        {
            signalFail=true;
            singleTNC.setSignalFailure(true);
        }
        return;
    }
    public void toggleEngineFail()
    {
        if (engineFail)
        {
            engineFail=false;
            singleTNC.setEngineFailure(false);
        }

        else
        {
            engineFail=true;
            singleTNC.setEngineFailure(true);
            powerCommand = 0;
        }
        return;
    }

    /////constructor
    public TrainModel(String Stringname, String ABlock, Block startBlock, double ASpeed, TrainControllerMain TNCMain_input,TkM tm)
    {

        //---- Do not touch this vvvvv

        currBlock = startBlock;
        currBlock.isOccupied = true;
        grade = currBlock.getGrade();
        currBlockLength = currBlock.getLength();
        TNC_Main = TNCMain_input;
        initTrainController(Stringname, ABlock, ASpeed);

        trackModel = tm;
        name = Stringname;
        AuthorityBlockID = ABlock;
        AuditedSpeed = ASpeed;

        initTrainModelGUI();
    }

    private void nextBlockFunc()  
    {
        currBlock.isOccupied = false;
        currBlock = currBlock.getNextBlock();
        currBlock.isOccupied = true;
        grade = currBlock.getGrade();
        currBlockLength = currBlock.getLength();
        trackModel.updateOccupancy(currBlock);
        return;
    }

    private double CalcAcceleration()
    {
        if (velocity == 0)
        {
            velocity = altvelocity;
        }
        if (engineFail)
        {
            powerCommand = 0;
        }
        else
        {
            powerCommand = singleTNC.calculatePower();
        }
        double retval = 1000 * powerCommand / (estimatedmass * velocity);
        double frictionforce = 0.5 * velocity * velocity;
        retval = retval - (frictionforce);
        return retval;
    }

    public void update()
    {
        
		AuditedAuthority = currBlock.getAuditedAuthority();
		AuditedSpeed = currBlock.getAuditedSpeed();
		if (Sbrake ==false && Ebrake==false)
        {
            acceleration = CalcAcceleration();
        }
        System.out.println("Acceleration of Train "+name+": "+acceleration);
        System.out.println("Velocity of Train "+name+": "+velocity);

        velocity = velocity + acceleration * timePerUpdate;
        if ((Sbrake || Ebrake) && velocity <=0) velocity =0;

        distanceTraveled = distanceTraveled + velocity * timePerUpdate;

        if (distanceTraveled > currBlockLength)
        {
            distanceTraveled = distanceTraveled - currBlockLength;
            nextBlockFunc();
			AuditedAuthority = currBlock.getAuditedAuthority();
			AuditedSpeed = currBlock.getAuditedSpeed();
        }
        return;
    }

    public void setDoorStatus(boolean[] input)
    {
        Doors = input;
    }

    public void toggleLights()
    {
        if (lights)
            lights = false;
        else
            lights = true;
        return;
    }

    public void setTemperature(double inputTemp)
    {
        temperature = inputTemp;
    }
	
	double getAuditedSpeed() {
        return AuditedSpeed;
    }

    Block getAuditedAuthority() {
        return AuditedAuthority;
    }

    public void toggleEBrake()
    {
        if (EbrakeFail==false)
        {
            if (Ebrake == false)
            {
                Ebrake = true;
                acceleration = EbrakeAcc;
            }
            else
            {
                Ebrake = false;
            }
            //return true;
        }
        return;// false;
    }

    public void toggleSBrake()
    {
        if (SbrakeFail==false && Ebrake==false)
        {
            if (Sbrake == false)
            {
                Sbrake = true;
                acceleration = SbrakeAcc;
            }
            else
            {
                Sbrake = false;
            }
            //return true;
        }
        return;// false;
    }
	
	
	/////// SOFTWARE STUFF ///////////

    public void showGUI(Stage newStage) {
        myGUI.start(newStage);
    }

    public void initTrainModelGUI()//no touch!
    {
        myGUI = new TrainModelGUI(this);//name, AuthorityBlockID, AuditedSpeed);
    }


    public void initTrainController(String Stringname, String ABlock, double ASpeed)
    {
        System.out.println("TNC_MAINIS************************" + TNC_Main);
        singleTNC = TNC_Main.createTrain(Stringname, ABlock, ASpeed,this);
        // TODO SCRUTINIZE
    }

}

