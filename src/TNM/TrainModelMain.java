import javafx.stage.Stage; 
public class TrainModelMain{
   
    static TrainModelMainGUI myGUI;
    static TrainModel[] tnmArray = new TrainModel[20];
    static int trainCount = 0;
    // //Train Model Constructor
    // public TrainModel(String name, String authority, double speed, TrainModel tm){
    //     auditedSpeed = speed;
    //     authority = authority;
    //     trainName = name;
    //     trainModel = tm;
    //     initGUI();
    // }
    //
    //Train Model Constructor
    public TrainModelMain(){

        //trainModel = tm;
        initGUI();
    }
 
    //Update function called from mainui, calls the subsequent update
    //function for each train Model object
    public void update(){
        for(int i = 0; i < tnmArray.length; i++){
            if (tnmArray[i] != null)
                tnmArray[i].update();
        }
    }

    public TrainModel[] getTrains(){
        return tnmArray;
    }

    
    public static TrainModel createTrain(String Stringname, String ABlock, Block startBlock, double ASpeed, TrainControllerMain TNCMain_input,TkM tm)
	{
        //create our train object
        TrainModelGUI tnmGUI;
        TrainModel tnm = new TrainModel(Stringname, ABlock, startBlock, ASpeed, TNCMain_input,tm);
        tnmGUI = tnm.getGUI();
        //add it to our array of train objects
        tnmArray[trainCount] = tnm;
        //increment our internal counter of trains
        trainCount++;
        myGUI.updateList();
        return tnm;
    }

    //Called when train Model is created
    void initGUI(){
        myGUI = new TrainModelMainGUI(this);
        
    }


    //Called when train Model selected from main menu
    void showGUI(Stage primaryStage){
        myGUI.start(primaryStage);
        // Scene scene = new Scene(GUI);
        // primaryStage.setScene(scene);
        // primaryStage.show();
    }

    //Called when train Model selected from main menu
    void showGUI(Stage primaryStage, TrainModel tnm){
        TrainModelGUI myGUI = tnm.getGUI();
        myGUI.start(primaryStage);
        // Scene scene = new Scene(GUI);
        // primaryStage.setScene(scene);
        // primaryStage.show();
    }
  public TrainModel[] getTrainArray()
	{
		return tnmArray;
	}
    
}

