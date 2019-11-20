import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.Priority;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.Separator;

import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.WindowEvent;

import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;

import java.util.ArrayList;


public class SetupUI extends Application {
    private Scene scene = null;
    private Stage currentStage = null;

    public SetupUI() {
        setup();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void setup() { // called once to create UI

        GridPane root = new GridPane();

        Label helpLabel = new Label("The ConjunctionJunction Train Control System cannot run without first setting up the track model, please import a track layout in the track model module to begin.");
        HBox GeneralInfo = new HBox(helpLabel);
        GeneralInfo.setAlignment(Pos.CENTER);
        GeneralInfo.setSpacing(80);

        root.getChildren().addAll(GeneralInfo); // upper right

        scene = new Scene(root, 600,500);

    }


    @Override // not sure what this does?
    public void start(Stage primaryStage) { // entry point for all apps
        currentStage = primaryStage;
        if (scene == null)
            setup();

        primaryStage.setTitle("Setup Help"); // container for all of it
        primaryStage.setResizable(false);
        primaryStage.setScene(scene); // content container
        primaryStage.show();
    }

    public void setIsOccupied(boolean isOccupied) {
        /// 
    }

    public void setSpeed(double speed) {
    }

    public void setAuthority(String authority) {
    }

}
