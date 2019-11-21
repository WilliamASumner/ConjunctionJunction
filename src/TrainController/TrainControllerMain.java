import javafx.stage.Stage; 
public class TrainControllerMain{
   
    TrainControllerMainGUI myGUI;
    TrainController[] tncArray = new TrainController[20];
    int trainCount = 0;
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
    public TrainControllerMain(){

        //trainModel = tm;
        initGUI();
        initPower();
    }
 
    //Update function called from mainui, calls the subsequent update
    //function for each train controller object
    public void update(){
        for(int i = 0; i < tncArray.length; i++){
            tncArray[i].update();
        }
    }

    public TrainController[] getTrains(){
        return tncArray;
    }

    
    public void createTrain(String name, String authority, double speed){
        //create our train object
        TrainControllerGUI tncGUI;
        TrainController tnc = new TrainController(name, authority, speed);
        tncGUI = tnc.getGUI();
        //add it to our array of train objects
        tncArray[trainCount] = tnc;
        //increment our internal counter of trains
        trainCount++;
        myGUI.updateList();
    }

    //Called when train controller is created
    void initGUI(){
        myGUI = new TrainControllerMainGUI(this);
        
    }

    void showGUI(){
        myGUI.start(primaryStage,this);
    }

    void initPower(){
        Power p = new Power();
    }

    //Called when train controller selected from main menu
    void showGUI(Stage primaryStage){
        myGUI.start(primaryStage);
        // Scene scene = new Scene(GUI);
        // primaryStage.setScene(scene);
        // primaryStage.show();
    }

  public TrainController[] getTrainArray(){
      return tncArray;
  }
    
}