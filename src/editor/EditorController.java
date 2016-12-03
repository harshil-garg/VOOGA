package editor;

import block.Block;
import block.BlockFactory;
import block.BlockType;
import block.DecorationBlock;
import engine.EngineController;
import grid.Grid;
import grid.GridWorld;
import grid.RenderedGrid;
import player.Player;
import interactions.Interaction;
import xml.GridWorldAndPlayer;
import xml.GridXMLHandler;

/**
 * This is the controller for the game editor. It allows the backend and frontend to talk to each other while the editor
 * is being created.
 * @author Aninda Manocha, Filip Mazurek
 */

public class EditorController {
    private BlockFactory blockFactory;
    private GridXMLHandler xmlHandler;
    private GridWorld gridWorld;
    private Grid currentGrid;
    private RenderedGrid renderedGrid;
    private int myNumRows;
    private int myNumColumns;
    private Player player;

    public EditorController() {
        blockFactory = new BlockFactory();
        xmlHandler = new GridXMLHandler();
        gridWorld = new GridWorld();
    }

    public void addGrid(int row, int col) {
        Grid newGrid = new Grid(row, col);
        gridWorld.addGrid(newGrid);
        gridWorld.updateGrid();
        changeGrid();
    }

    public void changeGrid() {
        currentGrid = gridWorld.getCurrentGrid();
        renderedGrid = new RenderedGrid(currentGrid);
        myNumRows =  currentGrid.getNumRows();
        myNumColumns = currentGrid.getNumCols();
    }

    public void addBlock(String name, BlockType blockType, int row, int col) {
        Block block = blockFactory.createBlock(name, blockType, row, col);
        System.out.println("NAME " + name);
        System.out.println("BLOCK " + block.getName());
        currentGrid.setBlock(row, col, block);
        System.out.println("GRID");
        for(int i = 0; i < myNumRows; i++) {
            for(int j = 0; j < myNumColumns; j++) {
                //System.out.println(currentGrid.getBlock(i,j).getName());
                //System.out.println(renderedGrid.get(i,j));
            }
        }
    }

    public void addPlayer(String name, int row, int col) {
        player = new Player(name, row, col);
        System.out.println("player added");
    }

    public void movePlayer(int row, int col) {
        player.setRow(row);
        player.setCol(col);
    }

    public void addInteraction(int row, int col, Interaction interaction){
        currentGrid.getBlock(row, col).addInteraction(interaction);
    }

    /*****METHODS FOR FRONTEND TO CALL*****/

    /**
     * Gets the row in which the player is located
     * @return the row
     */
    public int getRow() {
        return myNumRows;
    }

    /**
     * Gets the column in which the player is located
     * @return the column
     */
    public int getCol() {
        return myNumColumns;
    }

    /**
     * Gets the block located in a specific row and column
     * @param row - the specific row
     * @param col - the specific column
     * @return the block
     */
    public String getBlock(int row, int col) {
        return renderedGrid.get(row, col);
    }

    /**
     * Saves the editor by taking in the name of the file to contain the information and calling the data handling
     * method
     * @param file - the name of the file containing the editor information
     */
    public void saveEditor(String file) {
        xmlHandler.saveContents(file, gridWorld, player);
    }

    /**
     * Loads an editor that is stored in a specific file by calling the data handling method and storing the grid world
     * and player
     * @param file - the specific file
     */
    public void loadEditor(String file) {
        GridWorldAndPlayer gridWorldAndPlayer = xmlHandler.loadContents(file);
        player = gridWorldAndPlayer.getPlayer();
        gridWorld = gridWorldAndPlayer.getGridWorld();
        changeGrid();
    }

    /**
     *
     * @param file
     */
    public void saveEngine(String file) {
        xmlHandler.saveContents(file, gridWorld, player);
    }

    public EngineController runEngine() {
        return (new EngineController(player, gridWorld));
    }
}
