package block;

import block.GateBlock;
import block.BlockType;

import java.util.HashSet;
import java.util.Set;

/**
 * A top-level switch class which may interact with board objects which respond to a switch.
 *
 * @author Filip Mazurek
 */
public abstract class SwitchBlock extends Block {
    private Set<GateBlock> myGates;

    public SwitchBlock(String name, int row, int col) {
        super(name, row, col);
        myGates = new HashSet<>();
    }

    public void toggleGates() {
        for(GateBlock oneGate : myGates) {
            oneGate.toggleOpenStatus();
        }

        // notify front end to change look of switch
        setChanged();
        notifyObservers(new BlockUpdateNotification(BlockUpdateType.RE_RENDER, getRow(), getCol()));
    }

    public void connectToGate(GateBlock gate) {
        myGates.add(gate);
    }

    public void disconnectFromGate(GateBlock gate) {
        myGates.remove(gate);
    }
}