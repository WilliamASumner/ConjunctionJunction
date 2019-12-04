import javafx.stage.Stage;
import java.lang.Math;

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
	double radiansPerGrade = 0.015708;
	double radians = radiansPerGrade*grade;
	double rollingFrictionCoefficient = 0.002;
    static double kilosPerPound = 0.453592;
	double maxpower; // max acceleration at 2/3 mass


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
    double timePerUpdate = 1;

    public void toggleEBrakeFail()
    {
        if (EbrakeFail)
        {

            EbrakeFail=false;
            singleTNC.setSBrakeFailure(false);
			System.out.println("EbrakeFail is now false: "+EbrakeFail);
        
			
        }
        else
        {
            EbrakeFail=true;
            singleTNC.setSBrakeFailure(true);
			System.out.println("EbrakeFail is now true: "+EbrakeFail);
            Ebrake = false;
			System.out.println("Ebrake is now false: "+Ebrake);

        }
        return;
    }

    public void toggleSBrakeFail()
    {
        if (SbrakeFail)
        {
            SbrakeFail=false;
            singleTNC.setSBrakeFailure(false);
			System.out.println("SbrakeFail is now false: "+SbrakeFail);
        }
        else
        {
            SbrakeFail=true;
            Sbrake = false;
            singleTNC.setSBrakeFailure(true);
			System.out.println("SbrakeFail is now true: "+SbrakeFail);
			System.out.println("Sbrake is now false: "+Sbrake);
        }
        return;
    }
    public void toggleSignalFail()
    {
        if (signalFail)
        {
            signalFail=false;
            singleTNC.setSignalFailure(false);
			System.out.println("signalFail is now false: "+signalFail);
        }

        else
        {
            signalFail=true;
            singleTNC.setSignalFailure(true);
			System.out.println("signalFail is now true: "+signalFail);
        }
        return;
    }
    public void toggleEngineFail()
    {
        if (engineFail)
        {
            engineFail=false;
            singleTNC.setEngineFailure(false);
			System.out.println("engineFail is now false: "+engineFail);
        }

        else
        {
            engineFail=true;
            singleTNC.setEngineFailure(true);
			System.out.println("engineFail is now true: "+engineFail);
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
        grade = currBlock.getGrade();//setGrade(currBlock.getGrade());
        currBlockLength = currBlock.getLength();
        trackModel.updateOccupancy(currBlock);
        return;
    }
	
	private void setGrade(double newgrade)
	{
		grade = newgrade;
		System.out.println("grade: "+grade);
		radians = radiansPerGrade*grade;
		System.out.println("radians: "+radians);
		
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
        double frictionforce = 0.5 * velocity;// Not sure if correct
        //double frictionforce = rollingFrictionCoefficient*estimatedmass*gravity*Math.sin(radians);// Not sure if correct
		//double drag = frictionforce + estimatedmass*gravity*Math.cos(radians);
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
        if (velocity <=0) velocity =0;
		if(velocity >20) velocity =20;//max speed is 20 m/s

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
		myGUI.Doors = input;
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
	
	public double getVelocity()
	{
		return velocity;
	}

    public void toggleEBrake()
    {
        if (EbrakeFail==false)
        {
            if (Ebrake == false)
            {
                Ebrake = true;
                acceleration = EbrakeAcc;
				System.out.println("Ebrake is now true: "+Ebrake);
				
            }
            else
            {
                Ebrake = false;
				System.out.println("Ebrake is now false: "+Ebrake);
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
				System.out.println("Sbrake is now true: "+Sbrake);
            }
            else
            {
                Sbrake = false;
				System.out.println("Sbrake is now false: "+Sbrake);
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
	public Block getCurrBlock()
	{
		return currBlock;
	}
	
	public TrainModelGUI getGUI()
	{
		return myGUI;
	}
	public String getName()
	{
		return name;
	}


    public void initTrainController(String Stringname, String ABlock, double ASpeed)
    {
        System.out.println("TNC_MAINIS************************" + TNC_Main);
        singleTNC = TNC_Main.createTrain(Stringname, ABlock, ASpeed,this);
        // TODO SCRUTINIZE
    }

}

