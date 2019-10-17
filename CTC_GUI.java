import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


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

public class CTC_GUI extends Application {
	// fields
	private Label myLabel;
	private TextField departureTime;
	private TextField authority;
	private TextField trainName;
	private TextField speed;
	private VBox rightVbox;
	private String cssLayout = "-fx-border-color: black;\n" +
                   "-fx-border-insets: 0;\n" +
                   "-fx-border-width: 1;\n";
  	
    public static void main(String[] args) {
		// launch CTC 
		//CTC newCTC = new CTC();
		// launch the app
        launch();
    }
	
	@Override
    public void start(Stage stage) {
		
		// Create a BorderPane object
		BorderPane borderPane = new BorderPane();
	//	borderPane.setStyle(cssLayout);
		
		//--------------------------------------------------------------
		// Add image of track
		Image trackLayout = new Image("file:trackLayout.jpg");
		// create an ImageViw object
		ImageView imageView = new ImageView(trackLayout);
		imageView.setFitWidth(500);
		imageView.setFitHeight(350);
		// put imageView into center of BorderPane
		borderPane.setCenter(imageView);
		//---------------------------------------------------------------
		
		
		//---------------------------------------------------------------
		// The bottom portion of the BorderPane Layout Container
		// Create 'maintain track' view
		HBox hboxMaintain = new HBox(10, new Label("Choose Track"), new Label("Choose Block"));
		// Create a VBox
		VBox vboxMaintain = new VBox(10, new Label("Track Maintenance"), hboxMaintain, new Label("Maintenance Type"));
		vboxMaintain.setStyle(cssLayout);

		// Create 'Add Train' view
//		ListView<String> addTrains = new ListView<>();
		
		Label promptLabelTName = new Label("Train name: ");
		// create a TextField for input
		trainName = new TextField();		
		
		Label promptLabelAuth = new Label("Authority (Station Name): ");
		// create a TextField for input
		authority = new TextField();		
		
		Label promptLabelSpeed = new Label("Speed in MPH: ");
		// create a TextField for input
		speed = new TextField();		
		
		// create a label to display a prompt
		Label promptLabelDepart = new Label("Dispatch time in form: 00:00:00");
		// create a TextField for input
		departureTime = new TextField();
		
		// Create button
		Button dispatchT = new Button("Dispatch Train");
		
		// Register the event handler
		dispatchT.setOnAction(new DispatchButtonHandler());
		
		// create an empty label to display dispatched train
		myLabel = new Label();
		
		// Put in HBoxAdd
		HBox hboxAdd1 = new HBox(10, promptLabelTName, trainName, promptLabelAuth, authority);
		HBox hboxAdd2 = new HBox(10, promptLabelSpeed, speed, promptLabelDepart, departureTime);
		
		// Create StackPane for pronounced viewer section title
		StackPane stackAdd = new StackPane();
		Rectangle addTrainRec = new Rectangle(600.0, 25.0);
		addTrainRec.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
		new Stop[]{
        new Stop(0,Color.web("#C0C0C0")),
        new Stop(0.5, Color.web("#B1B1B1")),
        new Stop(1,Color.web("#A2A2A2")),}));
		addTrainRec.setStroke(Color.web("#CFCFCF"));
		addTrainRec.setArcHeight(3.5);
		addTrainRec.setArcWidth(3.5);
		Text textAdd = new Text("Add Train");
		stackAdd.getChildren().addAll(addTrainRec, textAdd);
		stackAdd.setAlignment(Pos.CENTER_LEFT);          
		
		VBox vboxAdd = new VBox(10, stackAdd, hboxAdd1, hboxAdd2, dispatchT);
		vboxAdd.setStyle(cssLayout);

		// Create 'Select a Queued Train' view
//		HBox hboxQueued = new HBox(10, new Label("Choose Track"), new Label("Choose Block"));
		// Create a VBox
		VBox vboxQueued = new VBox(10, new Label("Select a Queued Train"), new Label("Queued Trains"));
		vboxQueued.setStyle(cssLayout);
	
		// Create an HBox to contain the different functions for the dispatcher
		HBox bottomControlOfModule = new HBox(10, vboxMaintain, vboxAdd, vboxQueued);
		
		
		// Add to bottom of BorderPane
		borderPane.setBottom(bottomControlOfModule);
		//---------------------------------------------------------------
		
		
		//---------------------------------------------------------------
		// Create right BorderPane
		Label name = new Label("Train Name");
		Label block = new Label("Current Block");
		Label a = new Label("Authority");
		Label spd = new Label("Speed");
		
		HBox dispatchedTrainsViewer = new HBox(10, name, block, a, spd);
		
		// Create StackPane for pronounced viewer section title
		StackPane stackDepat = new StackPane();
		Rectangle departTrainRec = new Rectangle(250.0, 25.0);
		departTrainRec.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
		new Stop[]{
        new Stop(0,Color.web("#C0C0C0")),
        new Stop(0.5, Color.web("#B1B1B1")),
        new Stop(1,Color.web("#A2A2A2")),}));
		departTrainRec.setStroke(Color.web("#CFCFCF"));
		departTrainRec.setArcHeight(3.5);
		departTrainRec.setArcWidth(3.5);
		Text textDepart = new Text("Dispatched Trains");
		stackDepat.getChildren().addAll(departTrainRec, textDepart);
		stackDepat.setAlignment(Pos.CENTER_LEFT);
		
		// Add to a VBox
		rightVbox = new VBox(10, stackDepat, dispatchedTrainsViewer);		
		rightVbox.setStyle(cssLayout);
		
		borderPane.setRight(rightVbox);
		//---------------------------------------------------------------
		
		
		//---------------------------------------------------------------
		// Create left BorderPane
		// Add image of clock
		Image clock = new Image("file:digital_clock.jpg");
		// create an ImageViw object
		ImageView imageView2 = new ImageView(clock);
		imageView2.setFitWidth(250);
		imageView2.setFitHeight(150);
		
		VBox leftVbox = new VBox(10, imageView2, new Label("Throughput = 0"));
		leftVbox.setStyle(cssLayout);
		// put imageView2 into left of BorderPane
		borderPane.setLeft(leftVbox);
		//----------------------------------------------------------------
		
		
		// Create a scene with the borderPane as its root node
        Scene scene = new Scene(borderPane);//(new StackPane(l), 640, 480);
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
			myLabel.setText(tName + "\t\tYARD" + "\t\t" + auth + "\t\t" + speedMPH);
			rightVbox.getChildren().add(myLabel);
			
			//UPDATE NEW TRAIN IN CTC
		//	CTC.addTrain(tName, auth);
			// SEND DISPATCHED TRAIN INFO TO TRACK CONTROLLER
		//	CTC.dispatchQueuedTrain(tName)
		}
	}
	
	/**
	 * Anonymous inner class for CTC functions
	 */
//	public class CTC{
//	} 
}