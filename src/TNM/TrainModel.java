import javafx.stage.Stage;

public class TrainModel {
    
	
    static String name = "test name";
    static String AuthorityBlockID = "test Block";
    static double AuditedSpeed = 3.14;
    TrainController TNC = null;
	TrainControllerMain TNC_Main;
    TrainModelGUI myGUI;
	Block currBlock;
	
	
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
	
	public void toggleEbrakeFail()
	{
		if (EbrakeFail)
			EbrakeFail=false;
		else
		{
			EbrakeFail=true;
			Ebrake = false;
		}
		return;
	}
	
	public void toggleSbrakeFail()
	{
		if (SbrakeFail)
			SbrakeFail=false;
		else
		{
			SbrakeFail=true;
			Sbrake = false;
		}
		return;
	}
	

	public void toggleSignalFail()
	{
		if (signalFail)
			signalFail=false;
		else
		{
			signalFail=true;
		}
		return;
	}
	public void toggleEngineFail()
	{
		if (engineFail)
			engineFail=false;
		else
		{
			engineFail=true;
			powerCommand = 0;
		}
		return;
	}

    public TrainModel()
    {
        initTrainController("", "", 0.0);
        
        //---- Do not touch this vvvvv
        name = "";
        AuthorityBlockID = "";
        AuditedSpeed = 0.0;
        
        initTrainModelGUI();
    }
    //constructor
    public TrainModel(String Stringname, String ABlock, Block startBlock, double ASpeed)
    {
        initTrainController(Stringname, ABlock, ASpeed);
        
        //---- Do not touch this vvvvv
		
		currBlock = startBlock;
		currBlock.isOccupied = true;
		grade = currBlock.getGrade();
		currBlockLength = currBlock.getLength();
		
        name = Stringname;
        AuthorityBlockID = ABlock;
        AuditedSpeed = ASpeed;

        initTrainModelGUI();
    }
	
	private Block nextBlockFunc()
	{
		currBlock.isOccupied = false;
		currBlock = currBlock.getNextBlock();
		currBlock.isOccupied = true;
		grade = currBlock.getGrade();
		currBlockLength = currBlock.getLength();
		return;
	}
	
	public void toggleEbrake()
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
	
	public void toggleSbrake()
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
			powerCommand = TNC.calculatePower();
		}
		double retval = 1000 * powerCommand / (estimatedmass * velocity);
		return retval;
	}
	
	public void update()
	{
		if (Sbrake ==false && Ebrake==false)
		{
			acceleration = CalcAcceleration();
		}
		velocity = velocity + acceleration * timePerUpdate;
		distanceTraveled = distanceTraveled + velocity * timePerUpdate;
		
		if (distanceTraveled > currBlockLength)
		{
			distanceTraveled = distanceTraveled - currBlockLength;
			nextBlockFunc();
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

    public void showGUI(Stage newStage) {
        myGUI.start(newStage);
    }
    
    public void initTrainModelGUI()//no touch!
    {
        myGUI = new TrainModelGUI(this);//name, AuthorityBlockID, AuditedSpeed);
    }

    
    public void initTrainController(String Stringname, String ABlock, double ASpeed)
    {
        TNC = new TrainController(Stringname, ABlock, ASpeed);
    }

}

