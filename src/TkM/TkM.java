import javafx.application.Application;
import javafx.scene.Scene;
//import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.net.URL;



public class TkM {

      public double grade;
      public double elevation;
      public double speed;
      // public ArrayList<String> failures;
      public double length;
      public String lineColor;
      public int blockID;
      public char section;
      public boolean isOccupied;
      TkMGUI tkmg = new TkMGUI();
      //VBox GUI;

      public TkM() {
        //this.initGUI();
        // grade = b.getGrade();
        // elevation = b.getElevation();
        // speed = b.getSpeed();
        // // failures.add("failure");
        // // failures.add("failure2");
        // // failures.add("failure3");
        // length = b.getLength();
        // lineColor = b.getLineColor();
        // blockID = b.getBlockID();
        // section = b.getSection();


      }

      // public TkM() {
      //   initGUI();
      //
      //
      //               // gr = grade;
      //               // elev = elevation;
      //               // sp = speed;
      //               // // fails = failures;
      //               // len = length;
      //               // lineCol = lineColor;
      //               // blkc = section;
      //               // blkID = blockID;
      // }

      // public void initGUI() {
      //   FXMLLoader loader = new FXMLLoader();
      //   loader.setLocation(new URL("D:\Documents\Classes\Senior\softwareeng\javafiles\ConjunctionJunction\src\TkM"));
      //   GUI = loader.<VBox>load();
      // }

      public void showGUI(Stage stage){
        tkmg.start(stage);
      }


      public void setGrade(double newGrade) {
        grade = newGrade;
      }

      public double getGrade() {
        return grade;
      }


      public void setElevation(double newElevation) {
        elevation = newElevation;

      }

      public double getElevation() {
        return elevation;
      }


      public void setSpeed(double newSpeed) {
        speed = newSpeed;
      }

      public double getSpeed() {
        return speed;
      }


      // public void addFailures(String fail) {
      //   failures.add(fail);
      // }
      //
      // public void removeFailures(String fail) {
      //   failures.remove(fail);
      // }
      //
      // public ArrayList<String> getFailures() {
      //   return failures;
      // }

      public void setLength(double newLength) {
        length = newLength;
      }

      public double getLength() {
        return length;
      }

      public void setLineColor(String newColor) {
        lineColor = newColor;
      }

      public String getLineColor() {
        return lineColor;
      }

      public void setBlockID(int newID) {
        blockID = newID;
      }

      public int getBlockID() {
        return blockID;
      }

      public char getSection() {
        return section;
      }

      public void setSection(char newSection) {
        section = newSection;
      }

      public void setIsOccupied(boolean occ) {
        isOccupied = occ;
      }

      public boolean getIsOccupied() {
        return isOccupied;
      }


      public String toString(TkM tm) {


        // String failArr = "";
        // ArrayList<String> trackFails = tm.getFailures();
        //
        // for(int i = 0; i < failures.size(); i++) {
        //   failArr += "\t" + trackFails.get(i) + "\n";
        // }
        String occ = "";
        if (!tm.getIsOccupied()) {
          occ = "FREE";
        }
        else {
          occ = "OCCUPIED";
        }

        return "For Block " + tm.getLineColor() + " " + tm.getSection() + tm.getBlockID() + "\n" +
               "Grade: " + tm.getGrade() + "\n" +
               "Elevation: " + tm.getElevation() + "\n" +
               "Block length: " + tm.getLength() + "\n" +
               "Speed Limit: " + tm.getSpeed() + "\n\n" + "Occupation: " + occ + "\n\n\n";
               // +
               //"Failures: " + "\n" + failArr + "\n";
      }

	 // @Override
	 //    public void start(Stage stage) {
   //
   //        TkM t = new TkM();
   //        this.setGrade(0);
   //        this.setSpeed(70);
   //        this.setLength(100);
   //        this.setBlockID(63);
   //        this.setSection('K');
   //        this.setElevation(0);
   //        this.setLineColor("Green");
   //        this.setIsOccupied(false);
  	//         Label l = new Label(this.toString(t));
  	//         Scene scene = new Scene(GUI);
   //          stage.setTitle("Track Model UX");
  	//         stage.setScene(scene);
  	//         stage.show();
   //
	 //    }

	    // public static void main(String[] args) {
      //
	    //     launch();
	    // }

}
