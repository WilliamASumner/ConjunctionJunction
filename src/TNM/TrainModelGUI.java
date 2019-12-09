import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import javafx.scene.layout.FlowPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TrainModelGUI extends Application {

    String myName = "wowee";
    String myAuthority = "A1";//Audited Authority Block
    double mydubSpeed = 0.0;
	boolean[] Doors = new boolean[8];
    TrainModel tnm;
	int failures = 0;
	
	FlowPane flowpane;
	
	//Labels (just info that is displayed)
	Label currentSpeedLabel = new Label("Current Speed is" + mydubSpeed + " m/s\n");
	Label currentPowerLabel = new Label("Current Power is" + mydubSpeed + " kW\n");
	Label currentPassLabel = new Label("Current Passengers is\n");
	Label currentFailsLabel = new Label("Failures: " + failures + " \n");

	//Buttons on TrainModelGUI
    Button EbrakeFailButton = new Button("EBrake: Working");
    Button SbrakeFailButton = new Button("SBrake: Working");
    Button EngineFailButton = new Button("Engine: Working");
    Button SignalFailButton = new Button("Signal: Working");
	
    Button eBrake = new Button("E BRAKE: Currently OFF");

    public TrainModelGUI(TrainModel TNM)// String inName, String inBlock, double inSpeed)
    {
        /*
           myName = inName;
           myAuthority = inBlock;
           mydubSpeed = inSpeed;
           */
        tnm = TNM;
        myName = TNM.name;
        myAuthority = TNM.AuthorityBlockID;
        mydubSpeed = TNM.AuditedSpeed;
    }

	public void initGUI()
	{
		
		eBrake.setStyle("-fx-text-fill: black");
        eBrake.setMinWidth(400);
        eBrake.setMaxWidth(400);
        eBrake.setMinWidth(200);
        eBrake.setMaxWidth(200);
		
		EbrakeFailButton.setStyle("-fx-text-fill: black");
        EbrakeFailButton.setMinWidth(400);
        EbrakeFailButton.setMaxWidth(400);
        EbrakeFailButton.setMinWidth(200);
        EbrakeFailButton.setMaxWidth(200);
		
		SbrakeFailButton.setStyle("-fx-text-fill: black");
        SbrakeFailButton.setMinWidth(400);
        SbrakeFailButton.setMaxWidth(400);
        SbrakeFailButton.setMinWidth(200);
        SbrakeFailButton.setMaxWidth(200);
		
		EngineFailButton.setStyle("-fx-text-fill: black");
        EngineFailButton.setMinWidth(400);
        EngineFailButton.setMaxWidth(400);
        EngineFailButton.setMinWidth(200);
        EngineFailButton.setMaxWidth(200);
		
		SignalFailButton.setStyle("-fx-text-fill: black");
        SignalFailButton.setMinWidth(400);
        SignalFailButton.setMaxWidth(400);
        SignalFailButton.setMinWidth(200);
        SignalFailButton.setMaxWidth(200);
		
		//Main Flowpane
		flowpane = new FlowPane();
		
		//speed flowpane
		FlowPane speed = new FlowPane();
		speed.setStyle("-fx-border-color: black");
		speed.getChildren().add(currentSpeedLabel); //add failures to brake flowpane
        flowpane.getChildren().add(speed); //add brake flowpane to main flowpane
		
		//failures text flowpane
		FlowPane fail1 = new FlowPane();
		fail1.setStyle("-fx-border-color: black");		
		fail1.getChildren().add(currentFailsLabel); //add failures to brake flowpane
        flowpane.getChildren().add(fail1); //add brake flowpane to main flowpane
		
		//failure buttons flowpane
		FlowPane fail = new FlowPane();
		fail.setStyle("-fx-border-color: black");		
		fail.getChildren().add(EbrakeFailButton); //add EbrakeFailButton to brake flowpane
		fail.getChildren().add(SbrakeFailButton); //add SbrakeFailButton to brake flowpane
		fail.getChildren().add(EngineFailButton); //add EngineFailButton to brake flowpane
		fail.getChildren().add(SignalFailButton); //add SignalFailButton to brake flowpane		
        flowpane.getChildren().add(fail); //add brake flowpane to main flowpane
		
		//ebrake button flowpane
        FlowPane brake = new FlowPane();
        brake.setStyle("-fx-border-color: black");
		brake.getChildren().add(eBrake); //add eBrake to brake flowpane
        flowpane.getChildren().add(brake); //add brake flowpane to main flowpane
		
		
        eBrake.setOnAction(new eBrakeHandler());
        EbrakeFailButton.setOnAction(new eBrakeFailHandler());
        SbrakeFailButton.setOnAction(new sBrakeFailHandler());
        EngineFailButton.setOnAction(new engineFailHandler());
        SignalFailButton.setOnAction(new signalFailHandler());
		
	}
    @Override // not sure what this does?
    public void start(Stage primaryStage) { // entry point for all apps
        primaryStage.setTitle("Train Model GUI"); // container for all of it


        initGUI();
		//int num = 0;
/*
        String finalstring = "Train Name: " + myName + "\nAudited Authority: " + myAuthority + "\nAudited Speed: " + mydubSpeed
		+ "\nDoor 0: " + Doors[0]+ "\nDoor 1: " + Doors[0]+ "\nDoor 2: " + Doors[0]+ "\nDoor 3: " + Doors[0]
		+ "\nDoor 4: " + Doors[0]+ "\nDoor 5: " + Doors[0]+ "\nDoor 6: " + Doors[0]+ "\nDoor 7: " + Doors[0];
*/
/*
        Button btn = new Button();
        btn.setText("Say 'NUMBER'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println(num);
            }
        });*/

        //StackPane root = new StackPane();
        //StackPane mystackpane = new StackPane();
        //root.getChildren().add(btn);

        //Label myLabel = new Label(finalstring);
        //root.getChildren().add(myLabel);
        //mystackpane.getChildren().add(EbrakeFailButton);
        Scene scene = new Scene(flowpane, 500, 300);

        primaryStage.setScene(scene); // content container
        //primaryStage.setScene(new Scene(mystackpane, 300 ,250)); // content container

        primaryStage.show();
    }
	public void setEbrake(boolean newEbrake)
	{
		 if(newEbrake){
                    eBrake.setText("E Brake: Currently ON");
					eBrake.setStyle("-fx-text-fill: red");
                    System.out.println("TrainModelGUI: Emergency Brake ON...");
                }
                else{
                    eBrake.setText("E Brake: Currently OFF");
					eBrake.setStyle("-fx-text-fill: black");
                    System.out.println("TrainModelGUI: Emergency Brake OFF...");
                }
	}

    //Action Listeners
	class eBrakeHandler implements EventHandler<ActionEvent>
	{
        @Override
        public void handle(ActionEvent event)
		{
            boolean currentState;
                currentState = tnm.toggleEBrake();
                
        }
    }
    
	class eBrakeFailHandler implements EventHandler<ActionEvent>
	{
        @Override
        public void handle(ActionEvent event)
		{
            boolean currentState;
            currentState = tnm.toggleEBrakeFail();
			if(currentState)
			{
				EbrakeFailButton.setText("EBrake: Failed");
				EbrakeFailButton.setStyle("-fx-text-fill: red");
				failures++;
			}
            else
			{
				EbrakeFailButton.setText("EBrake: Working");
				EbrakeFailButton.setStyle("-fx-text-fill: black");
				failures--;				
			}
			currentFailsLabel.setText("Failures: " + failures + " \n");
        }
    }
    
	class sBrakeFailHandler implements EventHandler<ActionEvent>
	{
        @Override
        public void handle(ActionEvent event)
		{
            boolean currentState;
            currentState = tnm.toggleSBrakeFail();
			if(currentState)
			{
				SbrakeFailButton.setText("SBrake: Failed");
				SbrakeFailButton.setStyle("-fx-text-fill: red");
				failures++;
			}
            else
			{
				SbrakeFailButton.setText("SBrake: Working");
				SbrakeFailButton.setStyle("-fx-text-fill: black");
				failures--;				
			}
			currentFailsLabel.setText("Failures: " + failures + " \n");
        }
    }
    
	class engineFailHandler implements EventHandler<ActionEvent>
	{
        @Override
        public void handle(ActionEvent event)
		{
            boolean currentState;
            currentState = tnm.toggleEngineFail();
			if(currentState)
			{
				EngineFailButton.setText("Engine: Failed");
				EngineFailButton.setStyle("-fx-text-fill: red");
				failures++;
			}
            else
			{
				EngineFailButton.setText("Engine: Working");
				EngineFailButton.setStyle("-fx-text-fill: black");
				failures--;				
			}
			currentFailsLabel.setText("Failures: " + failures + " \n");
        }
    }
    
	class signalFailHandler implements EventHandler<ActionEvent>
	{
        @Override
        public void handle(ActionEvent event)
		{
            boolean currentState;
            currentState = tnm.toggleSignalFail();
			if(currentState)
			{
				SignalFailButton.setText("Signal: Failed");
				SignalFailButton.setStyle("-fx-text-fill: red");
				failures++;
			}
            else
			{
				SignalFailButton.setText("Signal: Working");
				SignalFailButton.setStyle("-fx-text-fill: black");
				failures--;				
			}
			currentFailsLabel.setText("Failures: " + failures + " \n");
        }
    }
    
	public void update()
	{
		//System.out.println("TrainModelGUI: UPDATE IS WORKING");
		return;
	}
}

