import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class TrackMap {
  public ArrayList<Block> map = new ArrayList<Block>();


  public TrackMap() {

  }

  public void parseFile(String filename) {
    map.add(0, null);
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
      //System.out.println(csv);
    }
    catch (FileNotFoundException e){
      return;
    }

    String[] csvrows = csv.split("\n");
    Block newBlock = new Block();

    // 11 rows

    for (int i = 1; i < csvrows.length; i++) {
      if (csvrows[i].equals(" ,,,,,,,,,,,")) {
        break;
      }

        String[] elements = csvrows[i].split(",");

      // for (int j = 0; j <= 8; j++) {
      //   System.out.print(elements[j] + " ");
      // }
        newBlock.setLineColor(elements[0]);
        newBlock.setBlockID(elements[2]+elements[1]+"");
        newBlock.setLength(Double.parseDouble(elements[3]));
        newBlock.setGrade(Double.parseDouble(elements[4]));
        newBlock.setSpeedLimit(Double.parseDouble(elements[5]));
        if (elements[6] != null) {
          //set block type to specific type
        }
        newBlock.setElevation(Double.parseDouble(elements[8]));
        int index = Integer.parseInt(elements[2]);
        //System.out.println(index);
        map.add(index, newBlock);
      //  System.out.println(newBlock.getBlockID() + ", " + newBlock.getLineColor());




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



  public void updateBlock(Block block) {
    int bid = Integer.parseInt(block.getBlockID().substring(1,block.getBlockID().length()-1));
  //  Block oldBlock = map.get(bid);
    map.set(bid, block);
  }


  // public static void main(String[] args) {
  //   TrackMap t = new TrackMap();
  //
  //   t.parseFile("redFile.csv");
  // }


}
