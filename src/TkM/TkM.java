
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.ArrayList;


public class TkM extends Application {

      public double grade;
      public double elevation;
      public double speed;
      // public ArrayList<String> failures;
      public double length;
      public String lineColor;
      public int blockID;
      public char section;
      public boolean isOccupied;
      Block b = new Block();

      public TkM() {
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

      public TkM(Block b) {

                    // gr = grade;
                    // elev = elevation;
                    // sp = speed;
                    // // fails = failures;
                    // len = length;
                    // lineCol = lineColor;
                    // blkc = section;
                    // blkID = blockID;
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
	        Label l = new Label(this.toString(t));
	        Scene scene = new Scene(new StackPane(l), 640, 480);
          stage.setTitle("Track Model UX");
	        stage.setScene(scene);
	        stage.show();
	    }

	    public static void main(String[] args) {

	        launch();
	    }

}
