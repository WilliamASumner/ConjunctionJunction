//import javafx.application.Application;
//import javafx.scene.Scene;
////import javafx.fxml.FXMLLoader;
//import javafx.scene.control.Label;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
import java.util.ArrayList;
//import java.net.URL;
//import javafx.scene.text.TextFlow;
//import javafx.scene.text.Text;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.scene.paint.Color;



public class TkM {

      //private TkMGUI tkmg = null;
      public String authority;
      //Stage s = null;
      public ArrayList<TrackMap> trackmaps = new ArrayList<TrackMap>();

      public TkM() {
        System.out.println(this.toString());
        //tkmg = new TkMGUI(this);


      }

      public ArrayList<TrackMap> buildTrackMaps(String redFile, String greenFile) {
    	  TrackMap red = new TrackMap();
    	  TrackMap green = new TrackMap();

    	  red.parseFile(redFile);
    	  green.parseFile(greenFile);

    	  trackmaps.add(0, red);
    	  trackmaps.add(1, green);

    	  return trackmaps;
      }


//      public void showGUI(Stage stage){
//        tkmg.start(stage);
//        s = stage;
//      }
//
//
//      public void setGrade(double newGrade) {
//        grade = newGrade;
//      }
//
//      public double getGrade() {
//        return grade;
//      }
//
//
//      public void setElevation(double newElevation) {
//        elevation = newElevation;
//
//      }
//
//      public double getElevation() {
//        return elevation;
//      }
//
//
//      public void setSpeed(double newSpeed) {
//        speed = newSpeed;
//        if (tkmg != null)
//            tkmg.update(s);
//      }
//
//      public double getSpeed() {
//        return speed;
//      }
//
//
//      // public void addFailures(String fail) {
//      //   failures.add(fail);
//      // }
//      //
//      // public void removeFailures(String fail) {
//      //   failures.remove(fail);
//      // }
//      //
//      // public ArrayList<String> getFailures() {
//      //   return failures;
//      // }
//
//      public void setLength(double newLength) {
//        length = newLength;
//      }
//
//      public double getLength() {
//        return length;
//      }
//
//      public void setLineColor(String newColor) {
//        lineColor = newColor;
//      }
//
//      public String getLineColor() {
//        return lineColor;
//      }
//
//      public void setBlockID(int newID) {
//        blockID = newID;
//      }
//
//      public int getBlockID() {
//        return blockID;
//      }
//
//      public char getSection() {
//        return section;
//      }
//
//      public void setSection(char newSection) {
//        section = newSection;
//      }
//
//      public void setIsOccupied(boolean occ) {
//        isOccupied = occ;
//        if (tkmg != null)
//            tkmg.update(s);
//      }
//
//      public boolean getIsOccupied() {
//        return isOccupied;
//      }
//
//      public void setAuthority(String newAuth) {
//        authority = newAuth;
//      }
//
//      public String getAuthority() {
//        return authority;
//      }
//
//
//      public TextFlow toString(TkM tm) {
//        // String failArr = "";
//        // ArrayList<String> trackFails = tm.getFailures();
//        //
//        // for(int i = 0; i < failures.size(); i++) {
//        //   failArr += "\t" + trackFails.get(i) + "\n";
//        // }
//        tm = this;
//        String occ = "";
//        if (!tm.getIsOccupied()) {
//          occ = "FREE";
//        }
//        else {
//          occ = "OCCUPIED";
//        }
//
//        Text t1 = new Text("For Block ");
//        t1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
//        Text t1l = new Text(tm.getLineColor() + " " + tm.getSection() + tm.getBlockID() + ":\n");
//        t1l.setFont(Font.font("Verdana", 20));
//        t1l.setFill(Color.GREEN);
//        Text t2 = new Text("Grade: ");
//        t2.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
//        Text t2l = new Text(tm.getGrade() + "\n");
//        t2l.setFont(Font.font("Verdana",  20));
//        Text t3 = new Text("Elevation: ");
//        t3.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
//        Text t3l = new Text(tm.getElevation() + "\n");
//        t3l.setFont(Font.font("Verdana",  20));
//        Text t4 = new Text("Block length: ");
//        t4.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
//        Text t4l = new Text(tm.getLength() + "\n");
//        t4l.setFont(Font.font("Verdana",  20));
//        Text t5 = new Text("Speed Limit: ");
//        t5.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
//        Text t5l = new Text(tm.getSpeed() + "\n\n");
//        t5l.setFont(Font.font("Verdana",  20));
//        Text t6 = new Text("Occupation: ");
//        t6.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
//        Text t6l = new Text(occ);
//        t6l.setFont(Font.font("Verdana",  20));
//        Text t7 = new Text("\nAuthority:");
//        t7.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
//        Text t7l = new Text(tm.getAuthority() + "\n\n\n");
//        t7l.setFont(Font.font("Verdana",  20));
//
//
//               // +
//               //"Failures: " + "\n" + failArr + "\n";
//
//               TextFlow tf = new TextFlow(t1,t1l,t2,t2l,t3,t3l,t4,t4l,t5,t5l,t6,t6l,t7,t7l);
//               return tf;
//      }

      public static void main(String[] args) {

        TkM t = new TkM();
        t.buildTrackMaps("redFile.csv", "redFile.csv");
        ArrayList<Block> a = t.trackmaps.get(0).map;
        System.out.println(a.size());
        //Block b = t.trackmaps.get(0).sendBlock(1);
    	  //System.out.println(b.getBlockID() + ", " + b.getLineColor());

      }



}
