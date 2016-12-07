package ui.scenes.editor.objects;

import ui.GridPaneNode;
import block.BlockType;
import javafx.scene.image.Image;

public class Flower1 extends GameObject {

    private final String PATH = "resources/images/tiles/decorations/flower-1";

    public Flower1() {
        super();
        Image image = new Image(PATH + ".png");
        myImageView.setImage(image);
        myIconPath = PATH;
        myBlockType = BlockType.DECORATION;
    }

    @Override
    public void populateList() {
        String name = PATH + ".png";
        GridPaneNode tempNode = new GridPaneNode(0, 0, name);
        myImageTiles.add(tempNode);
    }
}