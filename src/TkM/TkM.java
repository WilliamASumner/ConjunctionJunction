
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
      public String blockNum;

      public TkM() {
        grade = 0.5;
        elevation = 0.25;
        speed = 50;
        // failures.add("failure");
        // failures.add("failure2");
        // failures.add("failure3");
        length = 40;
        lineColor = "Red";
        blockNum = "A1";


      }

      public TkM(double gr, double elev, double sp,
                  double len, String lineCol, String blkN) {

                    gr = grade;
                    elev = elevation;
                    sp = speed;
                    // fails = failures;
                    len = length;
                    lineCol = lineColor;
                    blkN = blockNum;
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

      public void setBlockNum(String newNum) {
        blockNum = newNum;
      }

      public String getBlockNum() {
        return blockNum;
      }


      public String toString(TkM tm) {


        // String failArr = "";
        // ArrayList<String> trackFails = tm.getFailures();
        //
        // for(int i = 0; i < failures.size(); i++) {
        //   failArr += "\t" + trackFails.get(i) + "\n";
        // }

        return "For Block " + tm.getLineColor() + " " + tm.getBlockNum() + "\n" +
               "Grade: " + tm.getGrade() + "\n" +
               "Elevation: " + tm.getElevation() + "\n" +
               "Block length: " + tm.getLength() + "\n" +
               "Speed Limit: " + tm.getSpeed() + "\n\n\n";
               // +
               //"Failures: " + "\n" + failArr + "\n";
      }

	 @Override
	    public void start(Stage stage) {
        TkM t = new TkM();
        t.setGrade(0.4);
        t.setSpeed(60);
        t.setLength(80);
        t.setBlockNum("B2");
        t.setElevation(0.76);
        t.setLineColor("Green");
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
