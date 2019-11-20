import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class TrackMap {
  public ArrayList<Block> map = new ArrayList<Block>();


  public TrackMap() {

  }

  public ArrayList<Block> parseFile(String filename) {
    map.add(0, new Block());
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
      return map;
    }

    String[] csvrows = csv.split("\n");


    // 11 rows

    for (int i = 1; i < csvrows.length; i++) {
      if (csvrows[i].equals(" ,,,,,,,,,,,")) {
        break;
      }

        String[] elements = csvrows[i].split(",");

      // for (int j = 0; j <= 8; j++) {
      //   System.out.print(elements[j] + " ");
      // }
        Block newBlock = new Block();
        newBlock.setLineColor(elements[0]);
        newBlock.setBlockID(elements[1]+elements[2]+"");
        newBlock.setLength(Double.parseDouble(elements[3]));
        newBlock.setGrade(Double.parseDouble(elements[4]));
        newBlock.setSpeedLimit(Double.parseDouble(elements[5]));
        if (elements[6] != null) {
          //set block type to specific type
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

    return map;

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


  // public static void main(String[] args) {
  //   TrackMap t = new TrackMap();
  //
  //   t.parseFile("redFile.csv");
  //   ArrayList<Block> aaa = t.getBlocksBySection("A");
  //   System.out.println(aaa.size());
  // }


}
