import javafx.application.Application;
import javafx.scene.Scene;
//import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.net.URL;

public class TkMGUI extends Application {

    public TkMGUI() {

    }



    @Override
       public void start(Stage stage) {

           TkM t = new TkM();
           t.setGrade(0);
           t.setSpeed(70);
           t.setLength(100);
           t.setBlockID(63);
           t.setSection('K');
           t.setElevation(0);
           t.setLineColor("Green");
           t.setIsOccupied(false);
           t.setAuthority(" K63");
           // Text tx = new Text();
           // tx.setText("This is a text sample");
             Label l = new Label(t.toString(t));
             Scene scene = new Scene(new StackPane(l), 640, 480);
             stage.setTitle("Track Model UX");
             stage.setScene(scene);
             stage.show();

       }

       public static void main(String[] args) {
           launch();
       }


}
