package cjunction; // conjunction junction package

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage; 
import javafx.application.Application; 

import javafx.stage.Stage; 

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.ComboBox; 
import javafx.stage.Stage; 
import javafx.geometry.Insets; 


public class TrainModelMainGUI extends Application implements EventHandler<ActionEvent>{

    TrainModelMain tnmMain;
    //PowerGUI powerGUI;
    TrainModel tnm;
	TrainModel TEST_TrainModel;
    TrainModelGUI[] tnmGUIArray = new TrainModelGUI[20];
    TrainModel[] tnmArray;
    FlowPane  flowpane;
	boolean testtrain_exists = false;
    Button addTestTrain_btn;
    Button tnmButton;
	
	
	Block testBlockA = new Block();
	
	Block testBlockB = new Block();
	Block testBlockC = new Block();

    ComboBox trainMenu;

    public TrainModelMainGUI(Stage primaryStage)
	{
      /*
      speed = TrainModel.getAuditedSpeed();
      authority = TrainModel.getAuthority();
      name = TrainModel.getName();
	  */

    }
    public TrainModelMainGUI(TrainModelMain tnmM)
	{
        tnmMain = tnmM;
        /*
        speed = TrainModel.getAuditedSpeed();
        authority = TrainModel.getAuthority();
        name = TrainModel.getName();
        */
        initGUI();
    }

	//Called from TrainModelMain every time a train is created
	@SuppressWarnings("unchecked")
    public void updateList()
	{
        trainMenu.getItems().removeAll(trainMenu.getItems());
		
        TrainModel[] tnmTempArray;
		
        tnmArray = tnmMain.getTrains();
		
        for(int i = 0; i < tnmArray.length; i++)
		{
            if (tnmArray[i] != null)
			{    
				trainMenu.getItems().addAll(tnmArray[i].getName());
            }
        }
		
	}
	
	private void initGUI() 
	{

           flowpane = new FlowPane();
           flowpane.setHgap(25); 

           addTestTrain_btn = new Button("Add Test Train");
           tnmButton= new Button("Open Train Model");

           //styling for our two buttons
		   
           addTestTrain_btn.setStyle("-fx-text-fill: blue");
           addTestTrain_btn.setMinWidth(120);
		   
           tnmButton.setStyle("-fx-text-fill: blue");
           tnmButton.setMinWidth(120);
           

           trainMenu = new ComboBox();
         //  MenuButton trainMenu = new MenuButton("Select a train: ");

         //  menuButton.getItems().addAll();

           //FlowPane for power config button
		   
           FlowPane testtrain = new FlowPane();

           testtrain.setMinWidth(300);
           testtrain.setStyle("-fx-border-color: black");
           testtrain.getChildren().add(addTestTrain_btn);
           flowpane.getChildren().add(testtrain);
		   
          
           //FlowPane for power config button
           FlowPane trainControl = new FlowPane();
           trainControl.setMinWidth(300);
           trainControl.setStyle("-fx-border-color: black");
           trainControl.getChildren().add(tnmButton);
           trainControl.getChildren().add(trainMenu);
           flowpane.getChildren().add(trainControl);
           
           //Setting the margin of the pane  
           flowpane.setMargin(addTestTrain_btn, new Insets(20, 100, 20, 20)); 
           flowpane.setMargin(tnmButton, new Insets(20, 20, 20, 20)); 

	}

	@Override
	public void start(Stage primaryStage)
		{
			primaryStage.setTitle("Train Model Module");
			addTestTrain_btn.setOnAction(new addTestTrainHandler());
			tnmButton.setOnAction(new tnmHandler());
			Scene scene = new Scene(flowpane, 350, 130);
			primaryStage.setScene(scene);
			primaryStage.show();
		}

		
		class addTestTrainHandler implements EventHandler<ActionEvent>
		{
			@Override
			public void handle(ActionEvent event)
			{
				if(testtrain_exists==false)
				{
					System.out.println("TrainModelMainGUI: TEST TRAIN!");
					testtrain_exists = true;
					
					testBlockA.LineColor = "green";
					testBlockA.BlockID = "testBlockA";
					testBlockA.Grade = 0.0;//-173.2050808;//57.73502692;//100;//173.2050808;//
					testBlockA.Elevation = 0.0;
					testBlockA.SpeedLimit = 20.0;
					testBlockA.nextBlockID = testBlockB;
					testBlockA.prevBlockID = testBlockC;
					testBlockA.IsBidirectional = false;
					testBlockA.Length = 100.0;
					testBlockA.AuditedSpeed = 10;
					testBlockA.AuditedAuthority = testBlockC;
					testBlockA.IsUnderground = false;
					testBlockA.type = BlockType.REGBLOCK;
					testBlockA.stationName = "Test Station";
					testBlockA.switchState = SwitchState.MAIN;
					testBlockA.crossingState = CrossingState.UP;
					testBlockA.circuit = ErrorState.GOOD; // start as functioning
					testBlockA.power = ErrorState.GOOD;
					testBlockA.signal = ErrorState.GOOD;
					testBlockA.numFailures = 0;
					
				
					testBlockB.LineColor = "red";
					testBlockB.BlockID = "testBlockB";
					testBlockB.Grade = -173.2050808;//100;//57.73502692;//
					testBlockB.Elevation = 0;
					testBlockB.SpeedLimit = 19.0;
					testBlockB.nextBlockID = testBlockC;
					testBlockB.prevBlockID = testBlockA;
					testBlockB.IsBidirectional = true;
					testBlockB.Length = 155.0;
					testBlockB.AuditedSpeed = 15;
					testBlockB.AuditedAuthority = testBlockA;
					testBlockB.IsUnderground = true;
					testBlockB.type = BlockType.REGBLOCK;
					testBlockB.stationName = "Uninitialized";
					testBlockB.switchState = SwitchState.MAIN;
					testBlockB.crossingState = CrossingState.UP;
					testBlockB.circuit = ErrorState.GOOD; // start as functioning
					testBlockB.power = ErrorState.GOOD;
					testBlockB.signal = ErrorState.GOOD;
					testBlockB.numFailures = 0;
				
					testBlockC.LineColor = "red";
					testBlockC.BlockID = "testBlockC";
					testBlockC.Grade = -57.73502692;//100;//173;//
					testBlockC.Elevation = 0;
					testBlockC.SpeedLimit = 19.0;
					testBlockC.nextBlockID = testBlockA;
					testBlockC.prevBlockID = testBlockB;
					testBlockC.IsBidirectional = true;
					testBlockC.Length = 155.0;
					testBlockC.AuditedSpeed = 15;
					testBlockC.AuditedAuthority = testBlockB;
					testBlockC.IsUnderground = true;
					testBlockC.type = BlockType.REGBLOCK;
					testBlockC.stationName = "Uninitialized";
					testBlockC.switchState = SwitchState.MAIN;
					testBlockC.crossingState = CrossingState.UP;
					testBlockC.circuit = ErrorState.GOOD; // start as functioning
					testBlockC.power = ErrorState.GOOD;
					testBlockC.signal = ErrorState.GOOD;
					testBlockC.numFailures = 0;
					
					TEST_TrainModel = tnmMain.createTrain("TEST TRAIN", "testBlockB", testBlockA, 10, null,null);
					//add the train
					TEST_TrainModel.myGUI.testmode = true;
					addTestTrain_btn.setStyle("-fx-text-fill: red");
					addTestTrain_btn.setText("Test Train Added");
				}
			}
        }
      

      class tnmHandler implements EventHandler<ActionEvent>{
      @Override
      public void handle(ActionEvent event){
        Stage newWindow = new Stage();
		if(trainMenu.getValue()!=null)
        for(int i = 0; i < tnmArray.length; i++){
			
			if(tnmArray!=null && tnmArray[i]!=null){
				if(trainMenu.getValue().equals(tnmArray[i].getName())){
					//System.out.println("TrainModelMainGUI: OBJECT = " + tnmArray[i]);
					tnmArray[i].showGUI(newWindow);
				}
			}
        }
      }
    }


       public void handle(ActionEvent event)
	   {
		   Stage primaryStage = new Stage();

			//if train Model button is pressed, find the specific
			//train Model object and then open its GUI
			if(event.getSource() == tnmButton)
			{
			  //System.out.println("TrainModelMainGUI: YYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
			  for(int i = 0; i < tnmArray.length; i++)
			  {
				if(trainMenu.getValue().equals(tnmArray[i].getName()))
				{
					//tnmMain.showGUI(primaryStage, tnmArray[i]);
				   // System.out.println("TrainModelMainGUI: XXXXXXXXXXXXXXXXXXXXXXXXX");
					tnmArray[i].showGUI(primaryStage);
				}
			  }
			}
			/*
			else if(event.getSource() == powerConfigButton){
			  powerGUI.start(primaryStage);
			}*/
       }
       public static void main(String[] args)
	   {
           launch();
       }
}

