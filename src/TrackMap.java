package cjunction; // conjunction junction package

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class TrackMap {
    Block yard;
    public ArrayList<Block> map = new ArrayList<Block>();


    public TrackMap() {

    }

    public ArrayList<Block> parseFile(String filename)  {
        yard = new Block();
        yard.setBlockID("A0");
        yard.setNextBlock(yard);
        map.add(0, yard);

        //System.out.println(filename + ", " + filename.substring(filename.length()-3,filename.length()));
        if (!(filename.substring(filename.length()-3,filename.length()).equals("csv"))) {

            return map;
        }

        String csv = "";
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                csv+=sc.nextLine()+"\n";
            }
            //System.out.println(csv);
        }
        catch (FileNotFoundException e){
            System.out.println("FILENOT FOUND");
            System.out.println(e);
            System.exit(1);
        }

        String[] csvrows = csv.split("\n");


        // 11 rows

        for (int i = 1; i < csvrows.length; i++) {
            //System.out.println(csvrows[i]);
            if (csvrows[i].equals(" ,,,,,,,,,")) {
                break;
            }

            String[] elements = csvrows[i].split(",");
            //  System.out.println(elements.length);

            // for (int j = 0; j <= 8; j++) {
            //   System.out.print(elements[j] + " ");
            // }
            Block newBlock = new Block();
            newBlock.setLineColor(elements[0]);
            newBlock.setBlockID(elements[1]+elements[2]+"");
            newBlock.setLength(Double.parseDouble(elements[3]));
            newBlock.setGrade(Double.parseDouble(elements[4]));
            newBlock.setSpeedLimit(Double.parseDouble(elements[5]));
            //audited speed hardcoded
            newBlock.setAuditedSpeed(50.0);
            if (elements[6] != null) {
                if (elements[6].toUpperCase().contains("STATION")) {
                    newBlock.setType(BlockType.STATIONBLOCK);
                    String[] info = elements[6].split(";");
                    if (info.length == 2) {
                        //System.out.println(info[1]);
                        newBlock.setStationName(info[1]);
                    }
                    else
                        newBlock.setStationName(info[0]);
                }
                else if (elements[6].toUpperCase().contains("SWITCH")) {
                    newBlock.setType(BlockType.SWITCHBLOCK);
                }
                else if (elements[6].toUpperCase().contains("CROSSING")) {
                    newBlock.setType(BlockType.CROSSBLOCK);
                }
                newBlock.setSwitchState(SwitchState.MAIN);
                newBlock.setCrossingState(CrossingState.UP);

            }
            newBlock.setElevation(Double.parseDouble(elements[8]));
            int index = Integer.parseInt(elements[2]);
            //System.out.println(index + ", " + newBlock.getBlockID());
            map.add(index, newBlock);
            // System.out.println(newBlock.getBlockID() + ", " + newBlock.getLineColor());
            // System.out.println("THIS" + map.get(index).getBlockID());
            // System.out.println("LAST"+map.get(index-1).getBlockID());

        }

        // System.out.println(map.get(1).getBlockID());
        // System.out.println(map.get(2).getBlockID());
        // System.out.println(map.get(3).getBlockID());
        //System.out.println(map.get(1).getLineColor().equals("Red"));
        if (map.get(1).getLineColor().equals("Red")) {
            this.orderTrack("rsrc/redOrder.csv");
            //this.addSwitches("rsrc/redSwitch.csv");
        }
        else if (map.get(1).getLineColor().equals("Green")) {
            this.orderTrack("rsrc/greenOrder.csv");
        }

        return map;

    }


    // public void addSwitches(String filename) {
    //   if (!(filename.substring(filename.length()-3,filename.length()).equals("csv"))) {
    //     return;
    //   }
    //
    //   String csv = "";
    //   try {
    //     File file = new File(filename);
    //     Scanner sc = new Scanner(file);
    //     while (sc.hasNextLine()) {
    //       csv+=sc.nextLine()+"\n";
    //     }
    //   }
    //   catch (FileNotFoundException e){
    //       System.out.println("FILE NOT FOUND");
    //       System.out.println(e);
    //       System.exit(1);
    //   }
    //   String[] csvrows = csv.split("\n");
    //   for (int i = 1; i < csvrows.length-1; i++) {
    //     if (csvrows[i].equals("")) {
    //       continue;
    //     }
    //
    //     String[] switches = csvrows[i].split(",");
    //     int bid = Integer.parseInt(switches[0].substring(1, switches[0].length()));
    //     int forkbid = Integer.parseInt(switches[1].substring(1, switches[0].length()));
    //     map.get(bid).setNextBlockIDFork(map.get(forkbid));
    //     System.out.println(map.get(bid).getBlockID() + ", main: " + map.get(bid).getNextBlockVal().getBlockID() + ", fork: " + map.get(bid).getNextBlockIDFork().getBlockID());
    //     }
    //
    // }

    public void orderTrack(String filename) {
        if (!(filename.substring(filename.length()-3,filename.length()).equals("csv"))) {
            return;
        }

        String csv = "";
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                csv+=sc.nextLine()+"\n";
            }
        }
        catch (FileNotFoundException e){
            System.out.println("FILE NOT FOUND");
            System.out.println(e);
            System.exit(1);
        }
        String[] csvrows = csv.split("\n");
        for (int i = 0; i < csvrows.length-1; i++) {
            if (csvrows[i].equals("")) {
                break;
            }

            String[] curr = csvrows[i].split(",");
            String[] next = csvrows[i+1].split(",");
            int bid= Integer.parseInt(curr[1]);
            String section = curr[0];

            if (i==0) {

                int n = Integer.parseInt(next[1]);
                int c = Integer.parseInt(curr[1]);
                map.get(c).setPrevBlock(null);
                map.get(c).setNextBlock(map.get(n));
            }
            else {
                String[] prev = csvrows[i-1].split(",");
                int p = Integer.parseInt(prev[1]);
                int n = Integer.parseInt(next[1]);
                int c = Integer.parseInt(curr[1]);
                map.get(c).setPrevBlock(map.get(p));
                map.get(c).setNextBlock(map.get(n));
            }

        }

    }

    public void buildTrack(Block[] block) {

        for (int i = 0; i < block.length; i++) {
            int bid = Integer.parseInt(block[i].getBlockID().substring(1,block[i].getBlockID().length()-1));
            map.add(bid, block[i]);
        }
    }

    public Block sendBlock(int blockID) {
        return map.get(blockID);
    }

    public ArrayList<Block> getBlocksBySection(String section) {
        ArrayList<Block> bySection = new ArrayList<Block>();
        for (int i = 1; i < map.size(); i++) {
            Block b = map.get(i);
            String s = b.getBlockID().charAt(0)+"";
            if (s.equals(section)){
                bySection.add(map.get(i));
            }
        }
        return bySection;
    }



    public void updateBlock(Block block) {
        int bid = Integer.parseInt(block.getBlockID().substring(1,block.getBlockID().length()-1));
        //  Block oldBlock = map.get(bid);
        map.set(bid, block);
    }


    /*public static void main(String[] args) {
      TrackMap t = new TrackMap();

      t.parseFile("redFile.csv");
      ArrayList<Block> aaa = t.getBlocksBySection("A");
    //System.out.println(aaa.size());
    Block b = t.map.get(10);
    Block next = b.getNextBlockVal();
    System.out.println(next.getBlockID());
    }*/


}
