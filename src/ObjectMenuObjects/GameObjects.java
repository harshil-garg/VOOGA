package ObjectMenuObjects;
import java.util.*;
import grid.*;
import javafx.scene.image.ImageView;


public abstract class GameObjects {
    
    protected List<GridPaneNode> list;
    protected ImageView imageView;
    
    public GameObjects(){
        list = new ArrayList<GridPaneNode>();
        populateList();
        this.imageView = new ImageView();
    }
    public List<GridPaneNode> getList(){
        return list;
    }
    
    protected String reName(String a, int count){
        return a+"."+count+".png";
    }
    
    public abstract void populateList();
    
    public ImageView getImage(){
        return imageView;
    }
}
