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
	
	FlowPane flowpane;

	//Buttons on TrainModelGUI
    Button EbrakeFailButton = new Button("EBrake: Working");
    Button SbrakeFailButton = new Button("SBrake: Working");
    Button EngineFailButton = new Button("Engine: Working");
    Button SignalFailButton = new Button("Signal: Working");
	
    Button eBrake = new Button("E BRAKE: Currently OFF");
	
	Label currentFailsLabel;

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
		
		eBrake.setStyle("-fx-text-fill: red");
        eBrake.setMinWidth(400);
        eBrake.setMaxWidth(400);
        eBrake.setMinWidth(200);
        eBrake.setMaxWidth(200);
		
		EbrakeFailButton.setStyle("-fx-text-fill: red");
        EbrakeFailButton.setMinWidth(400);
        EbrakeFailButton.setMaxWidth(400);
        EbrakeFailButton.setMinWidth(200);
        EbrakeFailButton.setMaxWidth(200);
		
		flowpane = new FlowPane();
		
		FlowPane fail = new FlowPane();
		fail.setStyle("-fx-border-color: black");
		fail.getChildren().add(EbrakeFailButton); //add eBrake to brake flowpane
        flowpane.getChildren().add(fail); //add brake flowpane to main flowpane
		
		
        FlowPane brake = new FlowPane();
        brake.setStyle("-fx-border-color: black");
		brake.getChildren().add(eBrake); //add eBrake to brake flowpane
        flowpane.getChildren().add(brake); //add brake flowpane to main flowpane
		
		
        eBrake.setOnAction(new eBrakeHandler());
        EbrakeFailButton.setOnAction(new eBrakeFailHandler());
		
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
                    System.out.println("Turning Emergency Brake ON...");
                }
                else{
                    eBrake.setText("E Brake: Currently OFF");
                    System.out.println("Turning Emergency Brake OFF...");
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
			}
            else
			{
				EbrakeFailButton.setText("EBrake: Working");				
			}
        }
    }
    
	public void handle(ActionEvent event)
    {
        if(event.getSource()==EbrakeFailButton)
        {
            tnm.toggleEBrakeFail();
        }
    }
}

