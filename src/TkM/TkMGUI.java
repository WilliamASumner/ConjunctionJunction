import javafx.application.Application;
import javafx.scene.Scene;
//import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.net.URL;

public class TkMGUI extends Application {

    private TkM t = null;
    private Scene scene = null;
    private Label l = null;

    public TkMGUI(TkM tkm) {
        t = tkm;
        tInit();
    }

    public TkMGUI(){
        t = new TkM();
        tInit();
    }

    private void tInit() {
        t.setGrade(0);
        t.setSpeed(70);
        t.setLength(100);
        t.setBlockID(63);
        t.setSection('K');
        t.setElevation(0);
        t.setLineColor("Green");
        t.setIsOccupied(false);
        t.setAuthority("K63");
        //l = new Label(t.toString());
        scene = new Scene(new StackPane(t.toString(t)), 640, 480);
    }




    @Override
       public void start(Stage stage) {
           stage.setTitle("Track Model UX");
           stage.setScene(scene);
           stage.show();
       }

       public static void main(String[] args) {
           launch();
       }
       public void update(Stage stage) {
           stage.setScene(new Scene(new StackPane(t.toString(t)),640,480));
           stage.show();
           //l.setText(t.toString(t));
       }


}
