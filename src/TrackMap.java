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

        }
        catch (FileNotFoundException e){
            System.out.println("FILENOT FOUND");
            System.out.println(e);
            System.exit(1);
        }

        String[] csvrows = csv.split("\n");

        System.out.println("csvrows LENGTH:" + csvrows.length);


        // 11 rows

        for (int i = 1; i < csvrows.length; i++) {
            if (csvrows[i].equals(" ,,,,,,,,,")) {
                break;
            }

            String[] elements = csvrows[i].split(",");


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
                else if (elements[6].toUpperCase().contains("UNDERGROUND")) {
                    newBlock.setIsUnderground(true);
                }
                newBlock.setSwitchState(SwitchState.MAIN);
                newBlock.setCrossingState(CrossingState.UP);

            }
            newBlock.setElevation(Double.parseDouble(elements[8]));
            int index = Integer.parseInt(elements[2]);

            map.add(index, newBlock);

        }

        if (map.get(1).getLineColor().equals("Red")) {
            this.orderTrack("rsrc/redOrder.csv");
        }
        else if (map.get(1).getLineColor().equals("Green")) {
            this.orderTrack("rsrc/greenOrder.csv");
        }

        return map;

    }


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
                map.get(c).setPrevBlock(yard);
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

        map.set(bid, block);
    }



}
