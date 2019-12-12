package cjunction; // conjunction junction package

import javafx.stage.Stage;
import java.lang.Math;
//mport java.String.format;

public class TrainModel {
	
	String name = "test name";
	double myPower = 10;//for TEST TRAIN
	boolean metricmode = true;
	
    String AuthorityBlockID = "test Block";
	String beaconData = "Default Beacon Data";
    double AuditedSpeed = 3.14;
    TrainControllerMain TNC = null;
    TrainController singleTNC = null;
    TrainModelGUI myGUI;
    Block currBlock;
    TkM trackModel = null;
	
	double mphPERmps = 2.23694;
	double feetPERm = 3.28084;
    

    Block AuditedAuthority;
    boolean[] Doors = new boolean[8];
    boolean lights = false;
    double temperature = 70;

    double powerCommand = 0; //kiloWatts
    double grade = 0;
    double gravity = 9.81;
	double radiansPerGrade = 0.015708/2;
	double radians = radiansPerGrade*grade;
	double rollingFrictionCoefficient = 0.002;
    static double kilosPerPound = 0.453592;
	double maxpower; // max acceleration at 2/3 mass


    static double trainMass = 40.9; //tons
    static int currPassengers = 0; 
	int maxPassengers = 222;
    static double passMass = 180; // pounds
    static double estimatedmass = kilosPerPound*(2000*trainMass + passMass*currPassengers);//kilos of train
    TrainControllerMain TNC_Main = null;
	
    public int setPassenger(int input)
    {
        currPassengers = input;
		if(currPassengers > maxPassengers)currPassengers=maxPassengers;
		if(currPassengers < 0)currPassengers=0;
        estimatedmass = kilosPerPound*(2000*trainMass + passMass*currPassengers);
		myGUI.pass_Label.setText("Passengers:\t" + currPassengers + "/222 \n");
		System.out.println("TrainModel: currPassengers: "+currPassengers);
        return currPassengers;
    }
	public int getPassenger()
	{
		return currPassengers;
	}
	public void allPassengerExit()
	{
		System.out.println("TrainModel: exitingPassengers: "+currPassengers);
		setPassenger(0);
		return;
	}
	public int randPassengerExit()
	{
		double randPercent = Math.random();
		int exitingPassengers = (int)Math.round(currPassengers * randPercent);
		return setPassengerExit(exitingPassengers);
	}
	public int setPassengerExit(int input)
	{
		if(input<0)input=0;
		if(input>currPassengers)input=currPassengers;
		int exitingPassengers = input;
		
		System.out.println("TrainModel: exitingPassengers: "+exitingPassengers);
		setPassenger(currPassengers-exitingPassengers);
		return exitingPassengers;
	}
	
	public int randPassengerEnter()
	{
		double randPercent = Math.random();
		int enteringPassengers = (int)Math.round((maxPassengers - currPassengers) * randPercent);
		return setPassengerEnter(enteringPassengers);
	}
	public int setPassengerEnter(int input)
	{
		if(input<0)input=0;
		if(input>(maxPassengers - currPassengers))input=(maxPassengers - currPassengers);
		int enteringPassengers = input;
		
		System.out.println("TrainModel: enteringPassengers: "+enteringPassengers);
		setPassenger(currPassengers+enteringPassengers);
		return enteringPassengers;
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
    double timePerUpdate = 0.2; //seconds


	double LEN = currBlockLength;
	double HEIT = grade*LEN/100;
	double COS = LEN/ Math.sqrt(LEN*LEN+HEIT*HEIT);
	double SIN = HEIT/ Math.sqrt(LEN*LEN+HEIT*HEIT);

    public boolean toggleEBrakeFail()
    {
        if (EbrakeFail)
        {
			EbrakeFail=false;
			System.out.println("TrainModel: EbrakeFail is now false: "+EbrakeFail);			
        }
        else
        {
            EbrakeFail=true;
			System.out.println("TrainModel: EbrakeFail is now true: "+EbrakeFail);
            Ebrake = false;
			System.out.println("TrainModel: Ebrake is now false: "+Ebrake);

        }
        if(singleTNC!=null)singleTNC.setEBrakeFailure(EbrakeFail);		
		if(singleTNC!=null)singleTNC.getGUI().setEbrake(Ebrake);
		myGUI.setEbrake(Ebrake);
        return EbrakeFail;
    }

    public boolean toggleSBrakeFail()
    {
        if (SbrakeFail)
        {
            SbrakeFail=false;
			System.out.println("TrainModel: SbrakeFail is now false: "+SbrakeFail);
        }
        else
        {
            SbrakeFail=true;
            Sbrake = false;
			System.out.println("TrainModel: SbrakeFail is now true: "+SbrakeFail);
			System.out.println("TrainModel: Sbrake is now false: "+Sbrake);
        }
		if(singleTNC!=null)singleTNC.setSBrakeFailure(SbrakeFail);		
		if(singleTNC!=null)singleTNC.getGUI().setSbrake(Sbrake);//James 
        return SbrakeFail;
    }
    public boolean toggleSignalFail()
    {
        if (signalFail)
        {
            signalFail=false;
			System.out.println("TrainModel: signalFail is now false: "+signalFail);
        }
        else
        {
            signalFail=true;
			System.out.println("TrainModel: signalFail is now true: "+signalFail);
        }
        if(singleTNC!=null)singleTNC.setSignalFailure(signalFail);
		myGUI.beaconData_Label.setText("Beacon Data:\t" + getBeaconData() + "\n");
        return signalFail;
    }
    public boolean toggleEngineFail()
    {
        if (engineFail)
        {
            engineFail=false;
			System.out.println("TrainModel: engineFail is now false: "+engineFail);
        }

        else
        {
            engineFail=true;
			System.out.println("TrainModel: engineFail is now true: "+engineFail);
            powerCommand = 0;
        }
		if(singleTNC!=null)singleTNC.setEngineFailure(engineFail);
        return engineFail;
    }

    /////constructor
    public TrainModel(String Stringname, String ABlock, Block startBlock, double ASpeed, TrainControllerMain TNCMain_input,TkM tm)
    {

        //---- Do not touch this vvvvv

        currBlock = startBlock;
        currBlock.isOccupied = true;
        setGrade(currBlock.getGrade());
        currBlockLength = currBlock.getLength();
        TNC_Main = TNCMain_input;
        if(TNC_Main!=null)initTrainController(Stringname, ABlock, ASpeed);

        trackModel = tm;
        name = Stringname;
        if(ABlock!=null)AuthorityBlockID = ABlock;
        AuditedSpeed = ASpeed;

        initTrainModelGUI();
		
		myGUI.simtime_Label.setText("Simulated seconds/update: "+String.format("%.3f", timePerUpdate));
    }

    private void nextBlockFunc()  
    {
        currBlock.isOccupied = false;
        currBlock = currBlock.getNextBlock();
        currBlock.isOccupied = true;
        setGrade(currBlock.getGrade());//setGrade(currBlock.getGrade());
        currBlockLength = currBlock.getLength();
        if(trackModel!=null)trackModel.updateOccupancy(currBlock);
		
		if(metricmode)
			myGUI.blockLengthLabel.setText("Block length:\t" + currBlockLength + " m\n");
		else
			myGUI.blockLengthLabel.setText("Block length:\t" + String.format("%.1f", feetPERm*currBlockLength) + " ft\n");
		myGUI.grade_Label.setText("Grade:\t\t" + grade + " \n");
		myGUI.currentBlockLabel.setText("Block:\t\t" + currBlock.getBlockID() + " \n");
		//myGUI.currentBlockLabel.setStyle("-fx-text-fill: red");
        //System.out.println("TrainModel: Red");
		
		myGUI.underground_Label.setText("Underground:\t" + currBlock.getIsUnderground() + " \n");
		myGUI.linecolor_Label.setText("Line Color:\t" + currBlock.getLineColor() + " \n");
        return;
    }
	
	private void setGrade(double newgrade)
	{
		grade = newgrade;
		//System.out.println("TrainModel: grade: "+grade);		
		radians = radiansPerGrade*grade;
		LEN = currBlockLength;
		HEIT = grade*LEN/100;
		COS = LEN/ Math.sqrt(LEN*LEN+HEIT*HEIT);
		SIN = HEIT/ Math.sqrt(LEN*LEN+HEIT*HEIT);
		
		// System.out.println("TrainModel: grade: "+grade);
		// System.out.println("TrainModel: radians: "+radians);
		// System.out.println("TrainModel: Math.cos(radians): "+Math.cos(radians));
		// System.out.println("TrainModel: Math.sin(radians): "+Math.sin(radians));
		// System.out.println("TrainModel: COS: "+COS);
		// System.out.println("TrainModel: SIN: "+SIN);
		// System.out.println("TrainModel: HEIT: "+HEIT);
		// System.out.println("TrainModel: LEN: "+LEN+"\n");
		
    }
    
    //@Returns String - Get the current block
    public String getCurrentBlock(){
        return currBlock.getBlockID();
    }
	
    //@Returns String - Get the Authority from the current block 
    public String getAuthority(){
        return AuthorityBlockID;
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
            if(singleTNC!=null)powerCommand = singleTNC.calculatePower();
			else
				powerCommand = myPower;
        }
		if (powerCommand < 0)
		{
			powerCommand = 0;
		}
		
        double retval = 1000 * powerCommand / (estimatedmass * velocity);
        //double frictionAcc = 0.5 * velocity;// Not sure if correct
        double frictionAcc = rollingFrictionCoefficient*gravity*COS;// Not sure if correct
		double gravAcc = gravity*SIN;//force of gravity
		if(metricmode)
		{
			myGUI.currentFricLabel.setText("Friction Acc:\t" + String.format("%.6f", -1*(frictionAcc))+ " m/s2 \n");
			myGUI.currentGravLabel.setText("Gravity Acc:\t" + String.format("%.6f", -1*(gravAcc))+ " m/s2 \n");
			myGUI.currentDragLabel.setText("F+G Acc:\t\t" + String.format("%.6f", -1*(frictionAcc+gravAcc))+ " m/s2 \n");
		}
		else
		{
			myGUI.currentFricLabel.setText("Friction Acc:\t" + String.format("%.6f", -1*mphPERmps*(frictionAcc))+ " mph/s \n");
			myGUI.currentGravLabel.setText("Gravity Acc:\t" + String.format("%.6f", -1*mphPERmps*(gravAcc))+ " mph/s \n");
			myGUI.currentDragLabel.setText("F+G Acc:\t\t" + String.format("%.6f", -1*mphPERmps*(frictionAcc+gravAcc))+ " mph/s \n");
		}
		//System.out.println("TrainModel: "+frictionAcc+" = "+rollingFrictionCoefficient+"*"+gravity+"*"+Math.cos(radians)+"+ "+gravity+"*"+Math.sin(radians));
        
        retval = retval - (frictionAcc+gravAcc);
		if(velocity == altvelocity)velocity = 0;//so the train doesn't drift forward
        return retval;
    }

    public void update()
    {
        
		AuditedAuthority = currBlock.getAuditedAuthority();
		if(AuditedAuthority!=null)
			AuthorityBlockID=AuditedAuthority.getBlockID();//sometimes its randomly null
		
		AuditedSpeed = currBlock.getAuditedSpeed();
		if (Sbrake ==false && Ebrake==false)
        {
            acceleration = CalcAcceleration();
        }
       //System.out.println("TrainModel: Acceleration of Train "+name+": "+acceleration);
       //System.out.println("TrainModel: Velocity of Train "+name+": "+velocity);

        velocity = velocity + acceleration * timePerUpdate;
        if (velocity <=0) velocity =0;
		if(velocity >20) velocity =20;//max speed is 20 m/s

        distanceTraveled = distanceTraveled + velocity * timePerUpdate;
		//myGUI.currentBlockLabel.setStyle("-fx-color: black");

        if (distanceTraveled > currBlockLength)
        {
            distanceTraveled = distanceTraveled - currBlockLength;
            nextBlockFunc();
			AuditedAuthority = currBlock.getAuditedAuthority();
			AuditedSpeed = currBlock.getAuditedSpeed();
			
        }
		
		myGUI.currentPowerLabel.setText("Power:\t\t" + String.format("%.6f", powerCommand) + " kW\n");
		myGUI.progress_Label.setText("Progress:\t\t" + String.format("%.1f", distanceTraveled*100/currBlockLength) + " %\n");
		myGUI.Temp_Label.setText("Temperature:\t" + String.format("%.1f", temperature)+ " F \n");
		
		if(metricmode)
		{
			myGUI.currentDistLabel.setText("Position:\t\t" + String.format("%.6f", distanceTraveled) + " m\n");
			myGUI.currentSpeedLabel.setText("Speed:\t\t" + String.format("%.6f", velocity) + " m/s \n");
			myGUI.currentAccelerationLabel.setText("Acceleration:\t" + String.format("%.6f", acceleration)+ " m/s2 \n");
			myGUI.AudSpeed_Label.setText("AuditedSpeed:\t\t" + AuditedSpeed + " m/s\n");
			myGUI.currentMassLabel.setText("Mass:\t" + estimatedmass + " kg \n");
		}
		else
		{
			myGUI.currentDistLabel.setText("Position:\t\t" + String.format("%.6f", feetPERm*distanceTraveled) + " ft\n");
			myGUI.currentSpeedLabel.setText("Speed:\t\t" + String.format("%.6f", mphPERmps*velocity) + " mph \n");
			myGUI.currentAccelerationLabel.setText("Acceleration:\t" + String.format("%.6f", mphPERmps*acceleration)+ " mph/s \n");
			myGUI.AudSpeed_Label.setText("AuditedSpeed:\t\t" + String.format("%.2f", mphPERmps*AuditedSpeed) + " mph\n");
			myGUI.currentMassLabel.setText("Mass:\t" + estimatedmass/kilosPerPound + " lbs \n");
		}
		
		myGUI.beaconData_Label.setText("Beacon Data:\n" + getBeaconData() + "\n");
		myGUI.AudAuth_Label.setText("AuditedAuthority:\t" + AuditedAuthority + " \n");
		
		
		
		myGUI.update();
        return;
    }

    public void setDoorStatus(boolean[] input)
    {
        Doors = input;
		myGUI.Doors = input;
		if(input[0])
		{
			myGUI.Rdoor1_Label.setText("Rdoor1: Open \n");
		}
		else{
			myGUI.Rdoor1_Label.setText("Rdoor1: Closed \n");
		}
		if(input[1])
		{
			myGUI.Rdoor2_Label.setText("Rdoor2: Open \n");
		}
		else{
			myGUI.Rdoor2_Label.setText("Rdoor2: Closed \n");
		}
		if(input[2])
		{
			myGUI.Rdoor3_Label.setText("Rdoor3: Open \n");
		}
		else{
			myGUI.Rdoor3_Label.setText("Rdoor3: Closed \n");
		}
		if(input[3])
		{
			myGUI.Rdoor4_Label.setText("Rdoor4: Open \n");
		}
		else{
			myGUI.Rdoor4_Label.setText("Rdoor4: Closed \n");
		}
		if(input[4])
		{
			myGUI.Ldoor1_Label.setText("Ldoor1: Open \n");
		}
		else{
			myGUI.Ldoor1_Label.setText("Ldoor1: Closed \n");
		}
		if(input[5])
		{
			myGUI.Ldoor2_Label.setText("Ldoor2: Open \n");
		}
		else{
			myGUI.Ldoor2_Label.setText("Ldoor2: Closed \n");
		}
		if(input[6])
		{
			myGUI.Ldoor3_Label.setText("Ldoor3: Open \n");
		}
		else{
			myGUI.Ldoor3_Label.setText("Ldoor3: Closed \n");
		}
		if(input[7])
		{
			myGUI.Ldoor4_Label.setText("Ldoor4: Open \n");
		}
		else{
			myGUI.Ldoor4_Label.setText("Ldoor4: Closed \n");
		}
		System.out.println("TrainModel: Doors changed");
        
    }

    public void toggleLights()
    {
        if (lights)
		{
			
            lights = false;
			myGUI.lights_Label.setText("Lights: OFF \n");
		}
        else
		{
			
            lights = true;
			myGUI.lights_Label.setText("Lights: ON \n");
		}
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

    public boolean toggleEBrake()
    {
        if (EbrakeFail==false)
        {
            if (Ebrake == false)
            {
                Ebrake = true;
                acceleration = EbrakeAcc;
				System.out.println("TrainModel: Ebrake is now true: "+Ebrake);
				
            }
            else
            {
                Ebrake = false;
				System.out.println("TrainModel: Ebrake is now false: "+Ebrake);
            }
			
			if(singleTNC!=null)singleTNC.getGUI().setEbrake(Ebrake);//James 
			myGUI.setEbrake(Ebrake);
        }
        return Ebrake;// false;
    }

    public boolean toggleSBrake()
    {
        if (SbrakeFail==false && Ebrake==false)
        {
            if (Sbrake == false)
            {
                Sbrake = true;
                acceleration = SbrakeAcc;
				System.out.println("TrainModel: Sbrake is now true: "+Sbrake);
            }
            else
            {
                Sbrake = false;
				System.out.println("TrainModel: Sbrake is now false: "+Sbrake);
            }
			
			if(singleTNC!=null)singleTNC.getGUI().setSbrake(Sbrake);//James 
			myGUI.setSbrake(Sbrake);
            //return true;
        }
        return Sbrake;// false;
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
	
	public String getBeaconData()
	{
		if(signalFail)return "FAILURE";
		return beaconData;
	}
	
	public String setBeaconData(String input)
	{
		beaconData = input;
		return beaconData;
	}


    public void initTrainController(String Stringname, String ABlock, double ASpeed)
    {
        System.out.println("TrainModel: TNC_MAINIS************************" + TNC_Main);
        singleTNC = TNC_Main.createTrain(Stringname, ABlock, ASpeed,this);
        // TODO SCRUTINIZE
    }

}

