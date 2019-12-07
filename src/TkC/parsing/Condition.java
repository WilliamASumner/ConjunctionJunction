
public class Condition {

    private Block block; // block to be polled
    private AttributeType attrib; // attribute to be polled
    private boolean value; // value to compare with
    private CompType comparison; // comparison to be made
    private ChainType chainOp; // operator to chain with previous op

    public Condition() {
        chainOp = ChainType.NONE; // default to nothing
    }

    public void setBlock(Block b) {
        block = b;
    }

    public void setAttrib(AttributeType a) {
        attrib = a;
    }

    public void setValue(boolean v) {
        value = v;
    }

    public void setComparison(CompType c) {
        comparison = c;
    }

    public void setChainOp(ChainType ch) {
        chainOp = ch;
    }

    public ChainType getChainOp() {
        return chainOp;
    }

    private boolean valueOf(SwitchState s) {
        if (s == SwitchState.MAIN)
            return true;
        return false;
    }

    public boolean isEqual() {
        switch(attrib) {
            case OCCUPANCY:
                return block.getIsOccupied() == value;
            case SWITCHSTATE:
                return valueOf(block.getSwitchState()) == value;
            default:
                //System.out.println("ERROR: invalid condition attr");
                return false;
        }
    }
    public boolean evaluate() {
        switch(comparison) {
            case EQ:
                return isEqual();
            case NEQ:
                return !isEqual();
            default:
                //System.out.println("ERROR: invalid comparison op");
                return false;
        }
    }

    @Override
    public String toString() {
        return block.getBlockID() +"." + attrib + " " + comparison + " " + value + " " + chainOp;
    }
}
