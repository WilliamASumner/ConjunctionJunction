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

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;

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
    private Label label = null;
    GridPane grid = new GridPane();
    String line = "green";
    private Stage mystage = null;



    public TkMGUI(TkM tkm) {
        t = tkm;
        tInit();
        line = "green";
    }

    public TkMGUI(){
        line = "green";
    }

    private void tInit() {
        Block b = t.green.map.get(62);

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
        label = new Label();
        GridPane.setConstraints(label, 0, 3);
        GridPane.setColumnSpan(label, 2);
        grid.getChildren().add(label);

        String[] lines = {"green","red"};
        ObservableList<String> lineOptions = FXCollections.observableArrayList();
        for (String option: lines) {
            lineOptions.addAll(option);
        }
        ComboBox<String> lineBox = new ComboBox<String>(lineOptions);
        GridPane.setConstraints(lineBox, 100, 2);
        lineBox.getSelectionModel().select("green");
        lineBox.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                line = lineBox.getSelectionModel().getSelectedItem();
            }
        });
        grid.getChildren().add(lineBox);




        //Setting an action for the Submit button
        submit.setOnAction(new EventHandler<ActionEvent>() {

        @Override
            public void handle(ActionEvent e) {
                if ((name.getText() != null && !name.getText().isEmpty())) {
                    String s = name.getText().toString();
                    int bid = Integer.parseInt(s.substring(1, s.length()));
                    Block b = t.red.map.get(bid);//t.red.map.get(bid);
                    update(b);
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
        if (line.equals("green"))
            scene = new Scene(new StackPane(t.toString(t.green, b.getBlockID()), grid), 640, 480);
        else
            scene = new Scene(new StackPane(t.toString(t.red, b.getBlockID()), grid), 640, 480);




    }




    @Override
       public void start(Stage stage) {
           mystage = stage;
           stage.setTitle("Track Model UX");
           stage.setScene(scene);
           stage.show();
       }

       public static void main(String[] args) {
           launch();
       }
       public void update(Block b) {
           if (line.equals("green"))
               mystage.setScene(new Scene(new StackPane(t.toString(t.green, b.getBlockID()), grid), 640, 480));
           else
               mystage.setScene(new Scene(new StackPane(t.toString(t.red, b.getBlockID()), grid), 640, 480));


           //stage.show();
           //l.setText(t.toString(t));
       }


}
