package cjunction; // conjunction junction package

import java.util.Random;
import java.util.ArrayList;

public class RandomBlockGenerator
{
    private static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIG   = "0123456789";
    private Random rng;
    private int blockSectionIndex;
    private int blockNumber;
    private int sectionLength;
    private Block prevBlock;

    public RandomBlockGenerator(long seed) { // for repeatability
        rng = new Random(seed);
        blockSectionIndex = 0;
        blockNumber = 0;
        sectionLength = rng.nextInt(100) + 50;
    }

    public RandomBlockGenerator() {
        rng = new Random();
    }

    public void reset() {
        rng = new Random();
        blockSectionIndex = 0;
        blockNumber = 0;
    }

    private String sequentialBlockID() {
        String blockID = ALPHA.charAt(blockSectionIndex) + "";
        blockID += blockNumber;
        blockSectionIndex  = (blockSectionIndex + 1) % ALPHA.length();
        blockNumber++;
        return blockID;
    }

    private String randomBlockID() {
        String blockID = ALPHA.charAt(rng.nextInt(ALPHA.length())) + "";
        for (int i = 0; i < rng.nextInt(2)+1;i++){
            blockID += DIG.charAt(rng.nextInt(DIG.length())) + "";
        }
        return blockID;
    }

    public Block generateBlock() {
        Block b = new Block();
        b.setIsOccupied(rng.nextBoolean()); // dummy values
        b.setLineColor(rng.nextBoolean() ? "green" : "red");
        b.setBlockID(sequentialBlockID());
        b.setGrade(rng.nextDouble()*3.0); // 1-3% grade
        b.setElevation(rng.nextDouble()); // 0-1 elevation
        b.setSpeedLimit(rng.nextDouble()*50); // 0-50mph
        b.setIsBidirectional(rng.nextBoolean());
        b.setLength(rng.nextDouble()*150);
        b.setAuditedSpeed(rng.nextDouble()*50);
        b.setAuditedAuthority(b);
        b.setIsUnderground(rng.nextBoolean());

        // these don't need to be random
        b.setType(BlockType.REGBLOCK);
        b.setStationName("Uninitialized");
        b.setSwitchState(SwitchState.MAIN);
        b.setCrossingState(CrossingState.UP);
        
        b.setNextBlock(null); // connect all the blocks together
        b.setPrevBlock(prevBlock);
        if(prevBlock != null) {
            prevBlock.setNextBlock(b);
        }
        prevBlock = b;
        return b;
    }
}
