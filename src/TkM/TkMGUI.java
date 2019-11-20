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
        t = new TkM("red");
        tInit();
    }

    private void tInit() {
        Block b = t.red.map.get(67);
        // b.setGrade(0);
        // b.setAuditedSpeed(70);
        // b.setLength(100);
        // b.setBlockID("K63");
        // b.setElevation(0);
        // b.setLineColor("Green");
        // b.setIsOccupied(false);
        b.setAuditedAuthority(t.line.map.get(70));
        //l = new Label(b.toString());
        scene = new Scene(new StackPane(t.toString(t.red, "O67")), 640, 480);
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
           stage.setScene(new Scene(new StackPane(t.toString(t.red, "O67")),640,480));
           stage.show();
           //l.setText(t.toString(t));
       }


}
