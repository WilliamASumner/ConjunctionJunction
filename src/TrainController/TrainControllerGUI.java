import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TrainControllerGUI extends Application {
    double speed;
    String authority;
    String name;

    public TrainControllerGUI() {
      speed = TrainController.getAuditedSpeed();
      authority = TrainController.getAuthority();
      name = TrainController.getName();
    }



    @Override
       public void start(Stage stage) {
           stage.setTitle(name + " - Train Controller UI");

            // // define UI Layout
            // ColumnConstraints col1 = new ColumnConstraints();
            // col1.setPercentWidth(50);
            // col1.setHalignment(HPos.CENTER);
            //  col2 = new ColumnConstraints();
            // col2.setPercentWidth(50);
            // col2.setHalignment(HPos.CENTER);
            // root.getColumnConstraints().addAll(col1,col2);

            // RowConstraints row1 = new RowConstraints();
            // row1.setPercentHeight(50);
            // row1.setValignment(VPos.CENTER);
            // RowConstraints row2 = new RowConstraints();
            //  row2.setPercentHeight(50);
            // row2.setValignment(VPos.CENTER);
            // root.getRowConstraints().addAll(row1,row2);


           // TrainController train = new TrainController();
           //speed = train.getSpeed(0);
           //authority = train.getAuthority;
           String output = "Train Name: " + name + "\nAudited Authority" + authority + "\nAudited Speed Limit" + speed;
           
        //    Label AuthorityLabel = new Label("Authority: ");
        //    Label SpeedLabel = new Label("Speed: ");
        //    Label l = new Label(t.toString(t));
        //    Scene scene = new Scene(new StackPane(l), 640, 480);
           
           StackPane root = new StackPane();
           Label myLabel = new Label(output);
           root.getChildren().add(myLabel);

           stage.setScene(new Scene(root,300,250));
           stage.show();

       }

       public static void main(String[] args) {
           launch();
       }


}
