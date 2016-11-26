package ObjectMenuObjects;

import java.util.*;
import grid.GridPaneNode;
import javafx.scene.image.Image;

public class Flower1 extends GameObjects{

    private final String PATH = "resources/images/Sprites/Declaration/Flower/flower1";
    public Flower1 () {
        super();
        Image image = new Image(PATH+".png");
        this.imageView.setImage(image);
    }

    @Override
    public void populateList () {
        String name = reName(PATH,1);
        GridPaneNode tempNode = new GridPaneNode(0,0,name);
        list.add(tempNode);
    }

}
