import javafx.stage.Stage;

public class TrainModel {
    
    String name = "test name";
    String AuthorityBlockID = "test Block";
    double AuditedSpeed = 3.14;
    TrainControllerMain TNC = null;
    TrainModelGUI myGUI;

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
    public TrainModel(String Stringname, String ABlock, double ASpeed)
    {
        initTrainController(Stringname, ABlock, ASpeed);
        
        //---- Do not touch this vvvvv
        name = Stringname;
        AuthorityBlockID = ABlock;
        AuditedSpeed = ASpeed;
        
        initTrainModelGUI();
    }

    public void showGUI(Stage newStage) {
        myGUI.start(newStage);
    }
    
    public void initTrainModelGUI()//no touch!
    {
        myGUI = new TrainModelGUI();//name, AuthorityBlockID, AuditedSpeed);
    }

    
    public void initTrainController(String Stringname, String ABlock, double ASpeed)
    {
        //TNC = new TrainController(Stringname, ABlock, ASpeed);
    }

}

