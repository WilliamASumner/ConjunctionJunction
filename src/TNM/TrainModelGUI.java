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
    TrainModel tnm;

    Button EbrakeFailButton = new Button("EBrake Fail");

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

    @Override // not sure what this does?
    public void start(Stage primaryStage) { // entry point for all apps
        primaryStage.setTitle("Train Model GUI"); // container for all of it


        int num = 0;

        String finalstring = "Train Name: " + myName + "\nAudited Authority: " + myAuthority + "\nAudited Speed: " + mydubSpeed;

        Button btn = new Button();
        btn.setText("Say 'NUMBER'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println(num);
            }
        });



        StackPane root = new StackPane();
        //StackPane mystackpane = new StackPane();
        root.getChildren().add(btn);

        Label myLabel = new Label(finalstring);
        root.getChildren().add(myLabel);
        //mystackpane.getChildren().add(EbrakeFailButton);

        primaryStage.setScene(new Scene(root, 300 ,250)); // content container
        //primaryStage.setScene(new Scene(mystackpane, 300 ,250)); // content container

        primaryStage.show();
    }

    //Action Listeners
    public void handle(ActionEvent event)
    {
        if(event.getSource()==EbrakeFailButton)
        {
            tnm.toggleEBrakeFail();
        }
    }
}

