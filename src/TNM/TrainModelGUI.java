import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TrainModelGUI extends Application {
    
	String myName = "wowee";
	String myAuthority = "A1";//Audited Authority Block
	double mydubSpeed = 0.0;
	//contructor
	public TrainModelGUI()// String inName, String inBlock, double inSpeed)
	{
		myName = TrainModel.name;
		myAuthority = TrainModel.AuthorityBlockID;
		mydubSpeed = TrainModel.AuditedSpeed;
	}

    @Override // not sure what this does?
    public void start(Stage primaryStage) { // entry point for all apps
        primaryStage.setTitle("Train Model GUI"); // container for all of it
		
		String finalstring = "Train Name: " + myName + "\nAudited Authority: " + myAuthority + "\nAudited Speed: " + mydubSpeed;
		/*
		int num = 0;
		Button btn = new Button();
        btn.setText("Say 'NUMBER'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println(num);
            }
        });
		root.getChildren().add(btn);
		*/

        StackPane root = new StackPane();		
		Label myLabel = new Label(finalstring);
        root.getChildren().add(myLabel);
		
        primaryStage.setScene(new Scene(root, 300 ,250)); // content container		
        primaryStage.show();
    }
}

