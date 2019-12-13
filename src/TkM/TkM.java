package cjunction; // conjunction junction package

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

import java.io.FileNotFoundException;

// A0's next block is A0 indefinitely
// switch directionality
// failures as string array display fail or not
// throughput
// signal color (create enum)
// beacon: station name
// temp -> track heaters


public class TkM {

    private TkMGUI tkmg = null;
    public String authority;
    Stage s = null;
    public ArrayList<TrackMap> trackmaps = new ArrayList<TrackMap>();
    TrackMap line = new TrackMap();
    TrackMap red = new TrackMap();
    TrackMap green = new TrackMap();
    TrainControllerMain tnC;
    TrackControllerMain tkc = null;
    ArrayList<TrainModel> trains = new ArrayList<TrainModel>();
    private boolean heater;

    /*public TkM(String lineColor, TrainControllerMain newtnC) {
        tnC = newtnC;
        this.buildTrackMaps("rsrc/redFile.csv", "rsrc/greenFile.csv");
        if (lineColor.equals("Red")) {
            line = this.red;
        }
        else {
            line = this.green;
        }
        System.out.println(this.toString(red, red.map.get(5).getBlockID()));
        tkmg = new TkMGUI(this);

    }*/

    public TkM(TrainControllerMain newtnC) {
       tnC = newtnC;
        this.buildTrackMaps("rsrc/redFile.csv", "rsrc/greenFile.csv");

        //System.out.println(this.toString(red, red.map.get(5).getBlockID()));
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
        TrainModel newTrain = TrainModelMain.createTrain(name, authority, b, speed, TnC,this);
        if (!(trains.contains(newTrain))) {
        trains.add(newTrain);
        }
    }

    public void update() {
        for(TrainModel t: trains) {
            t.update();
        }
    }

    public int getThroughput(TrainModel tm) {
      int total = 0;
      total -= tm.randPassengerExit();
      total += tm.randPassengerEnter();

      return total;
    }



    public void showGUI(Stage stage){
        tkmg.start(stage);
        s = stage;
    }

    public void showTrainModelGUI(Stage stage) {
        TrainModel t = trains.get(0);
        //if (t != null)
            //t.showGUI();
    }







    public TextFlow toString(TrackMap line, String blockID) {

        //System.out.println("############" + blockID);
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

        String yardB;
        if (b.getPrevBlock().getBlockID().equals("A0")) {
          yardB = "YARD";
        }
        else {
          yardB = b.getPrevBlock().getBlockID();
        }

        String heat;
        if (heater == true) {
          heat = "ON";
        }
        else {
          heat = "OFF";
        }

        String fails = "";
        ArrayList<String> failArr = b.getFailures();
        if (failArr.size() == 0) {
          fails = "None";
        }
        for (int i = 0; i < failArr.size(); i++) {
          fails += "\n"+failArr.get(i);
        }

        int tickets = 0;
        if (trains.size()>0) {
         tickets = this.getThroughput(trains.get(0));
        }

        String beacon;
        if (b.getType() == BlockType.STATIONBLOCK) {
          beacon = b.getStationName() +"";
        }
        else {
          beacon = "Not A Station";
        }

        String cross;
        if (b.getType() == BlockType.CROSSBLOCK) {
          cross = b.getCrossingState() +"";
        }
        else {
          cross = "Not A Crossing";
        }


        Text t1 = new Text("For Block ");
        t1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Text t1l = new Text(b.getLineColor() + " " + b.getBlockID() + ":\n");
        t1l.setFont(Font.font("Verdana", 20));
        if (b.getLineColor().equals("Green")) {
          t1l.setFill(Color.GREEN);
        }
        else if (b.getLineColor().equals("Red")) {
          t1l.setFill(Color.RED);
        }
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
        Text t5l = new Text(b.getSpeedLimit() + "");
        t5l.setFont(Font.font("Verdana",  20));
        Text t6 = new Text("\nOccupation: ");
        t6.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Text t6l = new Text(occ + "\n");
        t6l.setFont(Font.font("Verdana",  20));
        Text t14 = new Text("\nCrossing: ");
        t14.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Text t14l = new Text(cross);
        t14l.setFont(Font.font("Verdana",  20));
        Text t7 = new Text("\nSwitch State:");
        t7.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Text t7l = new Text(b.getSwitchState() + "");
        t7l.setFont(Font.font("Verdana",  20));
        Text t8 = new Text("\nNext Block:");
        t8.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Text t8l = new Text(b.getNextBlockVal() + "");
        t8l.setFont(Font.font("Verdana",  20));
        Text t9 = new Text("\nPrev Block:");
        t9.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Text t9l = new Text(yardB + "\n");
        t9l.setFont(Font.font("Verdana",  20));
        Text t10 = new Text("\nBlock Type:");
        t10.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Text t10l = new Text(b.getType() + "");
        t10l.setFont(Font.font("Verdana",  20));

        Text t16 = new Text("\nTickets:");
        t16.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Text t16l = new Text(tickets+"");
        t16l.setFont(Font.font("Verdana",  20));

        Text t13 = new Text("\nBeacon Data:");
        t13.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Text t13l = new Text(beacon);
        t13l.setFont(Font.font("Verdana",  20));

        Text t15 = new Text("\nUnderground:");
        t15.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Text t15l = new Text(b.getIsUnderground()+"");
        t15l.setFont(Font.font("Verdana",  20));

        Text t11 = new Text("\nHeater:");
        t11.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Text t11l = new Text(heat);
        t11l.setFont(Font.font("Verdana",  20));
        Text t12 = new Text("\nFailures:");
        t12.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Text t12l = new Text(fails);
        t12l.setFont(Font.font("Verdana",  20));
        TextFlow tf = new TextFlow(t1,t1l,t2,t2l,t3,t3l,t4,t4l,t5,t5l,t6,t6l,t14,t14l,t7,t7l,t8,t8l,t9,t9l,t10,t10l,t16,t16l,t13,t13l,t15,t15l,t11,t11l,t12,t12l);
        return tf;
    }

    public void turnOnHeater(double d) {
      if (d < 32.0) {
        heater = true;
      }
      else {
        heater = false;
      }
    }

    public Block getBlock(String blockID, String lineColor) {
        lineColor = lineColor.toLowerCase();
        int bid = Integer.parseInt(blockID.substring(1, blockID.length()));
        if (lineColor.equals("red")) {
            if (bid >= red.map.size()) {
                return null;
            } else {
                return red.map.get(bid);
            }
        }
        else if (lineColor.equals("green")) {
            if (bid >= green.map.size()) {
                return null;
            } else {
                return green.map.get(bid);
            }
        }
        else {
            return null;
        }
    }

    public void updateOccupancy(Block b) {
        tkc.updateOccupancy(b);
    }

    public void removeTrain(int index) {
      trains.remove(index);
    }

    public void updateTkM() {
      for (int i = 0; i < trains.size(); i++) {
        Block b = trains.get(i).getCurrBlock();
        if (b.getBlockID().equals("A0")) {
          removeTrain(i);
        }
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
    // Block next = b.getNextBlockVal();
    // System.out.println(next.getBlockID());
    //
    //   //Block b = t.trackmaps.get(0).sendBlock(1);
    //   //System.out.println(b.getBlockID() + ", " + b.getLineColor());
    //
    // }



}
