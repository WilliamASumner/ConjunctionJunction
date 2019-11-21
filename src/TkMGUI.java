import javafx.application.Application;
import javafx.scene.Scene;
//import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.control.Button;
import java.net.URL;
import javafx.scene.layout.GridPane;
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
import javafx.scene.control.ComboBox;;
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

public class TkMGUI extends Application {

    private TkM t = null;
    private Scene scene = null;
    private Label l = null;
    GridPane grid = new GridPane();



    public TkMGUI(TkM tkm) {
        t = tkm;
        tInit();
    }

    public TkMGUI(){
        t = new TkM("red");
        //Creating a GridPane container

        tInit();
    }

    private void tInit() {
        Block b = t.red.map.get(6);

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        //Defining the Name text field
        final TextField name = new TextField();
        name.setPromptText("Enter the block ID. Ex. K65.");
        name.setPrefColumnCount(10);
        name.getText();
        GridPane.setConstraints(name, 100, 0);
        grid.getChildren().add(name);
        //Defining the Submit button
        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 101, 0);
        grid.getChildren().add(submit);
        //Defining the Clear button
        Button clear = new Button("Clear");
        GridPane.setConstraints(clear, 101, 1);
        grid.getChildren().add(clear);
        //Adding a Label
        final Label label = new Label();
        GridPane.setConstraints(label, 0, 3);
        GridPane.setColumnSpan(label, 2);
        grid.getChildren().add(label);

        //Setting an action for the Submit button
        submit.setOnAction(new EventHandler<ActionEvent>() {

        @Override
            public void handle(ActionEvent e) {
                if ((name.getText() != null && !name.getText().isEmpty())) {
                    String s = name.getText().toString();
                    int bid = Integer.parseInt(s.substring(1, s.length()));
                    Block b = t.red.map.get(bid);
                    update(new Stage(), b);
                } else {
                    label.setText("Not a valid blockID");
                }
             }
         });

        //Setting an action for the Clear button
        clear.setOnAction(new EventHandler<ActionEvent>() {

        @Override
            public void handle(ActionEvent e) {
                name.clear();
                label.setText(null);
            }
        });
        // b.setGrade(0);
        // b.setAuditedSpeed(70);
        // b.setLength(100);
        // b.setBlockID("K63");
        // b.setElevation(0);
        // b.setLineColor("Green");
        //b.setIsOccupied(true);
        //b.setAuditedAuthority(t.line.map.get(20));
        //l = new Label(b.toString());
        scene = new Scene(new StackPane(t.toString(t.red, b.getBlockID()), grid), 640, 480);




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
       public void update(Stage stage, Block b) {
           stage.setScene(new Scene(new StackPane(t.toString(t.red, b.getBlockID()), grid),640,480));
           stage.show();
           //l.setText(t.toString(t));
       }


}
