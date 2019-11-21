import javafx.application.Application;
import javafx.scene.Scene;
//import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.net.URL;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;



public class TkM {

      private TkMGUI tkmg = null;
      public String authority;
      Stage s = null;
      public ArrayList<TrackMap> trackmaps = new ArrayList<TrackMap>();
      TrackMap line = new TrackMap();
      TrackMap red = new TrackMap();
      TrackMap green = new TrackMap();
      TrainControllerMain tnC;
      TrackControllerMain tkc = new TrackControllerMain();

      public TkM(String lineColor) {
        tnC = newtnC;
        this.buildTrackMaps("redFile.csv", "greenFile.csv");
        if (lineColor.equals("Red")) {
          line = this.red;
        }
        else {
          line = this.green;
        }
        System.out.println(this.toString(red, red.map.get(5).getBlockID()));
        tkmg = new TkMGUI(this);

      }

      public TkM(TrainControllerMain newtnC) {
        tnC = newtnC;
        this.buildTrackMaps("redFile.csv", "greenFile.csv");

       System.out.println(this.toString(red, red.map.get(5).getBlockID()));
       tkmg = new TkMGUI(this);

     }

      public void addTrackController(TrackControllerMain newtkc) {
        tkc = newtkc;
      }

      public ArrayList<TrackMap> buildTrackMaps(String redFile, String greenFile) {

    	  red.parseFile(redFile);
    	  green.parseFile(greenFile);

    	  trackmaps.add(0, red);
    	  trackmaps.add(1, green);
        red = this.trackmaps.get(0);
        green = this.trackmaps.get(1);

    	  return trackmaps;
      }

      public ArrayList<Block> getRed() {
        return trackmaps.get(0).map;
      }

      public ArrayList<Block> getGreen() {
        return trackmaps.get(1).map;
      }


      public void createTrain(String name, String authority, Block b, double speed, TrainControllerMain TnC) {
        TrainModel newTrain = new TrainModel(name, authority, b, speed, TnC);
      }



     public void showGUI(Stage stage){
       tkmg.start(stage);
       s = stage;
     }



     public TextFlow toString(TrackMap line, String blockID) {

       int bid = Integer.parseInt(blockID.substring(1, blockID.length()));
       ArrayList<Block> tm = line.map;
       Block b = tm.get(bid);
       String occ = "";
       if (!b.getIsOccupied()) {
         occ = "FREE";
       }
       else {
         occ = "OCCUPIED";
       }

       Text t1 = new Text("For Block ");
       t1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
       Text t1l = new Text(b.getLineColor() + " " + b.getBlockID() + ":\n");
       t1l.setFont(Font.font("Verdana", 20));
       t1l.setFill(Color.GREEN);
       Text t2 = new Text("Grade: ");
       t2.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
       Text t2l = new Text(b.getGrade() + "\n");
       t2l.setFont(Font.font("Verdana",  20));
       Text t3 = new Text("Elevation: ");
       t3.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
       Text t3l = new Text(b.getElevation() + "\n");
       t3l.setFont(Font.font("Verdana",  20));
       Text t4 = new Text("Block length: ");
       t4.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
       Text t4l = new Text(b.getLength() + "\n");
       t4l.setFont(Font.font("Verdana",  20));
       Text t5 = new Text("Speed Limit: ");
       t5.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
       Text t5l = new Text(b.getSpeedLimit() + "\n\n");
       t5l.setFont(Font.font("Verdana",  20));
       Text t6 = new Text("Occupation: ");
       t6.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
       Text t6l = new Text(occ);
       t6l.setFont(Font.font("Verdana",  20));
       Text t7 = new Text("\nAuthority:");
       t7.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
       Text t7l = new Text(b.getNextBlock() + "\n\n\n");
       t7l.setFont(Font.font("Verdana",  20));




              TextFlow tf = new TextFlow(t1,t1l,t2,t2l,t3,t3l,t4,t4l,t5,t5l,t6,t6l,t7,t7l);
              return tf;
     }

     public Block getBlock(String blockID, String lineColor) {
       lineColor = lineColor.toLowerCase();
       int bid = Integer.parseInt(blockID.substring(1, blockID.length()));
       if (lineColor.equals("Red")) {
         return red.map.get(bid);
       }
       else if (lineColor.equals("Green")) {
         return green.map.get(bid);
       }
       else {
         return null;
       }
     }

      // public static void main(String[] args) {
      //
      //   TkM t = new TkM("Red");
      //  t.buildTrackMaps("redFile.csv", "redFile.csv");
      //  ArrayList<Block> a = t.trackmaps.get(0).map;
      //   System.out.println(t.red.map.size());
      // //  TrackMap r = t.trackmaps.get(0);
      //   ArrayList<Block> aaa = t.red.getBlocksBySection("A");
      //   System.out.println(aaa.size());
      //   System.out.println(t.red.map.get(5).getIsOccupied());
      //
      // Block b = t.red.map.get(10);
      // Block next = b.getNextBlock();
      // System.out.println(next.getBlockID());
      //
      //   //Block b = t.trackmaps.get(0).sendBlock(1);
    	//   //System.out.println(b.getBlockID() + ", " + b.getLineColor());
      //
      // }



}
