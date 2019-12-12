package cjunction; // conjunction junction package

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TrainModelGUI extends Application {

    boolean testmode = false;
	
	String myName = "wowee";
    String myAuthority = "A1";//Audited Authority Block
    double mydubSpeed = 0.0;
	boolean[] Doors = new boolean[8];
    TrainModel tnm;
	int failures = 0;
	
	FlowPane flowpane;
	
	//Labels (just info that is displayed)
		//speed labels
	Label currentSpeedLabel = new Label("Current Speed is" + mydubSpeed + " m/s\n");
	Label currentDistLabel = new Label("Current block");
	Label currentAccelerationLabel = new Label("Current acceler");
	Label currentMassLabel = new Label("Mass:\n");
	Label currentGravLabel = new Label("Mass:\n");
	Label currentFricLabel = new Label("Mass:\n");
	Label currentDragLabel = new Label("Mass:\n");
	Label currentPowerLabel = new Label("Current Power is" + mydubSpeed + " kW\n");
	Label pass_Label = new Label("Passengers:\t0/222 \n");
	Label simtime_Label = new Label("Simulated seconds/update: 1");
	Label currentFailsLabel = new Label("Failures: " + failures + " \n");
	
	
		//internal labels
	Label Temp_Label = new Label("Temperature");
	Label lights_Label = new Label("Lights: OFF \n");
	
	Label Ldoor1_Label = new Label("Ldoor1: Closed ");
	Label Ldoor2_Label = new Label("Ldoor2: Closed \n");
	Label Ldoor3_Label = new Label("Ldoor3: Closed \n");
	Label Ldoor4_Label = new Label("Ldoor4: Closed \n");
	
	Label Rdoor1_Label = new Label("Rdoor1: Closed \n");
	Label Rdoor2_Label = new Label("Rdoor2: Closed \n");
	Label Rdoor3_Label = new Label("Rdoor3: Closed \n");
	Label Rdoor4_Label = new Label("Rdoor4: Closed \n");
	
		//block info
	Label currentBlockLabel = new Label("Current block");
	Label blockLengthLabel = new Label("Current block");
	Label AudSpeed_Label = new Label("Current block");
	Label AudAuth_Label = new Label("Current block");
	Label underground_Label = new Label("Current block");
	Label grade_Label = new Label("Current block");
	Label linecolor_Label = new Label("Current block");
	Label beaconData_Label = new Label("Current block");
	Label progress_Label = new Label("Current block");

	
	
	

	//Buttons on TrainModelGUI
    Button EbrakeFailButton = new Button("EBrake: Working");
    Button SbrakeFailButton = new Button("SBrake: Working");
    Button EngineFailButton = new Button("Engine: Working");
    Button SignalFailButton = new Button("Signal: Working");
    Button eBrake = new Button("EMERGENCY BRAKE: Currently OFF");
    Button sBrake = new Button("SERVICE BRAKE: Currently OFF");
	
	//Test buttons
    Button add5pass_button = new Button("+5 Passengers");
    Button rem5pass_button = new Button("-5 Passengers");
    Button addRpass_button = new Button("+? Passengers");
    Button remRpass_button = new Button("-? Passengers");
    Button remApass_button = new Button("-* Passengers");
    Button add10kw_button = new Button("+1 kW");
    Button rem10kw_button = new Button("-1 kW");

    public TrainModelGUI(TrainModel TNM)// String inName, String inBlock, double inSpeed)
    {
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
		
		sBrake.setStyle("-fx-text-fill: black");
        sBrake.setMinWidth(400);
        sBrake.setMaxWidth(400);
        sBrake.setMinWidth(200);
        sBrake.setMaxWidth(200);
		
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
		
		//speed VBOX
		VBox speed = new VBox();
        speed.setMinWidth(200);
		speed.setStyle("-fx-border-color: black");
		
		speed.getChildren().add(currentSpeedLabel); //add speedlabel to brake flowpane
		speed.getChildren().add(currentPowerLabel); //add speedlabel to brake flowpane
		speed.getChildren().add(currentAccelerationLabel); //add speedlabel to brake flowpane
		speed.getChildren().add(currentFricLabel); //add speedlabel to brake flowpane
		speed.getChildren().add(currentGravLabel); //add speedlabel to brake flowpane
		speed.getChildren().add(currentDragLabel); //add speedlabel to brake flowpane
		speed.getChildren().add(currentDistLabel); //add speedlabel to brake flowpane
		speed.getChildren().add(pass_Label); //add speedlabel to brake flowpane
		speed.getChildren().add(currentMassLabel); //add speedlabel to brake flowpane
		speed.getChildren().add(simtime_Label); //add speedlabel to brake flowpane		
		
		flowpane.getChildren().add(speed); //add brake flowpane to main flowpane
		
		
		//block_VBOX
		
		VBox block_VBOX = new VBox();
        block_VBOX.setMinWidth(200);
		block_VBOX.setStyle("-fx-border-color: black");
		
		block_VBOX.getChildren().add(currentBlockLabel); //add speedlabel to brake flowpane
		block_VBOX.getChildren().add(linecolor_Label); //add speedlabel to brake flowpane
		block_VBOX.getChildren().add(blockLengthLabel); //add speedlabel to brake flowpane
		block_VBOX.getChildren().add(progress_Label); //add speedlabel to brake flowpane
		block_VBOX.getChildren().add(underground_Label); //add speedlabel to brake flowpane
		block_VBOX.getChildren().add(grade_Label); //add speedlabel to brake flowpane
		block_VBOX.getChildren().add(AudSpeed_Label); //add speedlabel to brake flowpane
		block_VBOX.getChildren().add(AudAuth_Label); //add speedlabel to brake flowpane
		block_VBOX.getChildren().add(beaconData_Label); //add speedlabel to brake flowpane
		
		
		flowpane.getChildren().add(block_VBOX); //add brake flowpane to main flowpane
		
		
		//internal VBOX
		VBox internal = new VBox();
        block_VBOX.setMinWidth(200);
		internal.setStyle("-fx-border-color: black");
		
		internal.getChildren().add(Temp_Label); //add speedlabel to brake flowpane
		internal.getChildren().add(lights_Label); //add speedlabel to brake flowpane
		
		internal.getChildren().add(Rdoor1_Label); //add speedlabel to brake flowpane
		internal.getChildren().add(Rdoor2_Label); //add speedlabel to brake flowpane
		internal.getChildren().add(Rdoor3_Label); //add speedlabel to brake flowpane
		internal.getChildren().add(Rdoor4_Label); //add speedlabel to brake flowpane
		
		internal.getChildren().add(Ldoor1_Label); //add speedlabel to brake flowpane
		internal.getChildren().add(Ldoor2_Label); //add speedlabel to brake flowpane
		internal.getChildren().add(Ldoor3_Label); //add speedlabel to brake flowpane
		internal.getChildren().add(Ldoor4_Label); //add speedlabel to brake flowpane
		
		flowpane.getChildren().add(internal); //add brake flowpane to main flowpane
		
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
		brake.getChildren().add(sBrake); //add eBrake to brake flowpane
        flowpane.getChildren().add(brake); //add brake flowpane to main flowpane
		
		//testing buttons flowpane
		FlowPane test = new FlowPane();
        test.setStyle("-fx-border-color: black");
		test.getChildren().add(add5pass_button); //add eBrake to brake flowpane
		test.getChildren().add(rem5pass_button); //add eBrake to brake flowpane
		test.getChildren().add(addRpass_button); //add eBrake to brake flowpane
		test.getChildren().add(remRpass_button); //add eBrake to brake flowpane
		test.getChildren().add(remApass_button); //add eBrake to brake flowpane
		test.getChildren().add(add10kw_button); //add eBrake to brake flowpane
		test.getChildren().add(rem10kw_button); //add eBrake to brake flowpane
        if(testmode)flowpane.getChildren().add(test); //add test flowpane to main flowpane
		
		
        eBrake.setOnAction(new eBrakeHandler());
        sBrake.setOnAction(new sBrakeHandler());
        EbrakeFailButton.setOnAction(new eBrakeFailHandler());
        SbrakeFailButton.setOnAction(new sBrakeFailHandler());
        EngineFailButton.setOnAction(new engineFailHandler());
        SignalFailButton.setOnAction(new signalFailHandler());
        add5pass_button.setOnAction(new add5pass_Handler());
        rem5pass_button.setOnAction(new rem5pass_Handler());
        addRpass_button.setOnAction(new addRpass_Handler());
        remRpass_button.setOnAction(new remRpass_Handler());
        remApass_button.setOnAction(new remApass_Handler());
        add10kw_button.setOnAction(new add10kw_Handler());
        rem10kw_button.setOnAction(new rem10kw_Handler());
		
		
	}
    @Override // not sure what this does?
    public void start(Stage primaryStage) { // entry point for all apps
        primaryStage.setTitle("Train Model GUI: "+ myName); // container for all of it
        initGUI();
        Scene scene = new Scene(flowpane, 601, 400);
        primaryStage.setScene(scene); // content container
        primaryStage.show();
    }
	
	///Brake button text control
	public void setEbrake(boolean newEbrake)
	{
		 if(newEbrake){
                    eBrake.setText("EMERGENCY BRAKE: Currently ON");
					eBrake.setStyle("-fx-text-fill: red");
                    System.out.println("TrainModelGUI: Emergency Brake ON...");
                }
                else{
                    eBrake.setText("EMERGENCY BRAKE: Currently OFF");
					eBrake.setStyle("-fx-text-fill: black");
                    System.out.println("TrainModelGUI: Emergency Brake OFF...");
                }
	}
	
	public void setSbrake(boolean newSbrake)
	{
		 if(newSbrake){
                    sBrake.setText("SERVICE BRAKE: Currently ON");
					sBrake.setStyle("-fx-text-fill: red");
                    System.out.println("TrainModelGUI: Service Brake ON...");
                }
                else{
                    sBrake.setText("SERVICE BRAKE: Currently OFF");
					sBrake.setStyle("-fx-text-fill: black");
                    System.out.println("TrainModelGUI: Service Brake OFF...");
                }
	}


    /////Action Listeners BUTTON STUFF
	class eBrakeHandler implements EventHandler<ActionEvent>
	{
        @Override
        public void handle(ActionEvent event)
		{
            boolean currentState;
                currentState = tnm.toggleEBrake();
                
        }
    }
	
	class sBrakeHandler implements EventHandler<ActionEvent>
	{
        @Override
        public void handle(ActionEvent event)
		{
            boolean currentState;
                if(testmode)currentState = tnm.toggleSBrake();
                
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
	
	class add5pass_Handler implements EventHandler<ActionEvent>
	{
        @Override
        public void handle(ActionEvent event)
		{
            tnm.setPassengerEnter(5);
        }
    }
    
	class rem5pass_Handler implements EventHandler<ActionEvent>
	{
        @Override
        public void handle(ActionEvent event)
		{
            tnm.setPassengerExit(5);
        }
    }
	
	class addRpass_Handler implements EventHandler<ActionEvent>
	{
        @Override
        public void handle(ActionEvent event)
		{
            tnm.randPassengerEnter();
        }
    }
    
	class remRpass_Handler implements EventHandler<ActionEvent>
	{
        @Override
        public void handle(ActionEvent event)
		{
            tnm.randPassengerExit();
        }
    }
	
	class remApass_Handler implements EventHandler<ActionEvent>
	{
        @Override
        public void handle(ActionEvent event)
		{
            tnm.allPassengerExit();
        }
    }
    
	class add10kw_Handler implements EventHandler<ActionEvent>
	{
        @Override
        public void handle(ActionEvent event)
		{
            tnm.myPower+=1;
        }
    }
    
	class rem10kw_Handler implements EventHandler<ActionEvent>
	{
        @Override
        public void handle(ActionEvent event)
		{
            if(tnm.myPower>=1)tnm.myPower-=1;
        }
    }
    
	public void update()
	{
		//System.out.println("TrainModelGUI: UPDATE IS WORKING");
		return;
	}
}

