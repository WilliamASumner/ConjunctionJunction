public class TrainModel {
 	
	static String name = "test name";
	static String AuthorityBlockID = "test Block";
	static double AuditedSpeed = 3.14;
	//TrainController TNC;
	TrainModelGUI myGUI;
	
	//constructor
	public TrainModel(String Stringname, String ABlock, double ASpeed)
	{
		initTrainController(Stringname, ABlock, ASpeed);
		
		//---- Do not touch this vvvvv
		name = Stringname;
		AuthorityBlockID = ABlock;
		AuditedSpeed = ASpeed;
		
		initTrainModelGUI();
	}
	
	public void initTrainModelGUI()//no touch!
	{
		myGUI = new TrainModelGUI();//name, AuthorityBlockID, AuditedSpeed);
	}
	
	public void initTrainController(String Stringname, String ABlock, double ASpeed)
	{
		//TNC = new TrainController(String Stringname, String ABlock, double ASpeed, this);
	}

}

