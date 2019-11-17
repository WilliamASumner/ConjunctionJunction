import java.util.HashMap;

public class TrackMap {
  HashMap<String, Block> map = new HashMap<String, Block>();


  public TrackMap() {

  }

  public void buildTrack(Block[] block) {
    for (int i = 0; i < block.length; i++) {
      map.put(block[i].getBlockID(), block[i]);
    }
  }

  public Block sendBlock(String blockID) {
    return map.get(blockID);
  }

  public void updateTrack(HashMap<String, Block> track) {

  }

  public void updateBlock(Block block) {
    Block oldBlock = map.get(block.getBlockID());
    map.replace(block.getBlockID(), oldBlock, block);
  }




}
