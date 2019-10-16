import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class CTC_GUI extends Application {
	// fields
	private Label myLabel;
	private TextField departureTime;
	private TextField authority;
	private TextField trainName;
	private TextField speed;
	
    public static void main(String[] args) {
		// launch CTC 
		//CTC newCTC = new CTC();
		// launch the app
        launch();
    }
	
	@Override
    public void start(Stage stage) {
		
		Label promptLabelTName = new Label("Enter train name: ");
		// create a TextField for input
		trainName = new TextField();		
		
		Label promptLabelAuth = new Label("Enter an authority (Station Name): ");
		// create a TextField for input
		authority = new TextField();		
		
		Label promptLabelSpeed = new Label("Enter a speed in MPH: ");
		// create a TextField for input
		speed = new TextField();		
		
		// create a label to display a prompt
		Label promptLabelDepart = new Label("Enter a dispatch time in the form: 00:00:00");
		// create a TextField for input
		departureTime = new TextField();
		
		// Create button
		Button dispatchT = new Button("Dispatch Train");
		
		// Register the event handler
		dispatchT.setOnAction(new DispatchButtonHandler());
		
		// create an empty label to display dispatched train
		myLabel = new Label();
		
		// Put in an HBox
		HBox hbox0 = new HBox(10, promptLabelTName, trainName);
		HBox hbox = new HBox(10, promptLabelAuth, authority);
		HBox hbox1 = new HBox(10, promptLabelSpeed, speed);
		HBox hbox2 = new HBox(10, promptLabelDepart, departureTime);
		
		// Put the HBox, dispatchT, and myLabel in a VBox
		VBox vbox = new VBox(10, hbox0, hbox, hbox1, hbox2, dispatchT, myLabel);
		// set the VBox's alignment to center
		vbox.setAlignment(Pos.CENTER);
		
		// Create a scene with the VBox as its root node
        Scene scene = new Scene(vbox);//(new StackPane(l), 640, 480);
		stage.setTitle("CTC Module");
        stage.setScene(scene);
        stage.show();
    }

	/**
	 * Event handler class for dispatchT button.
	 */
	class DispatchButtonHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event){
			String departTime = departureTime.getText();
			String auth = authority.getText();
			String tName = trainName.getText();
			String speedMPH = speed.getText();
			myLabel.setText(tName + " dispatched at " + departTime + " traveling at " + speedMPH + " MPH with authority " + auth);
			
		}
	}
	
	/**
	 * Anonymous inner class for CTC functions
	 */
//	public class CTC{
//	} 
}