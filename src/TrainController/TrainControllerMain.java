package cjunction; // conjunction junction package

import javafx.stage.Stage; 
public class TrainControllerMain{
   
    TrainControllerMainGUI myGUI;
    TrainController[] tncArray = new TrainController[20];
    int trainCount = 0;
    Power p;
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
        initGUI();
    }
 
    //Update function called from mainui, calls the subsequent update
    //function for each train controller object
    public void update(){
        
        for(int i = 0; i < tncArray.length; i++){
            if (tncArray[i] != null)
                tncArray[i].update();
        }
    }

    public TrainController[] getTrains(){
        return tncArray;
    }

    
    public TrainController createTrain(String name, String authority, double speed, TrainModel tm){
        //create our train object
        TrainControllerGUI tncGUI;
        TrainController tnc = new TrainController(name, authority, speed, tm);
        tncGUI = tnc.getGUI();
        //add it to our array of train objects
        tncArray[trainCount] = tnc;
        //increment our internal counter of trains
        trainCount++;
        myGUI.updateList();
        return tnc;
    }

    //Called when train controller is created
    void initGUI(){
        myGUI = new TrainControllerMainGUI(this);    
    }

  
    

    //Called when train controller selected from main menu
    void showGUI(Stage primaryStage){
        myGUI.start(primaryStage);

    }

   
  public TrainController[] getTrainArray(){
      return tncArray;
  }
    
}
