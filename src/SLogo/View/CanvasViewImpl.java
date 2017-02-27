package SLogo.View;

import java.io.File;
import java.util.ResourceBundle;

import SLogo.View.Sprite.Sprite;
import SLogo.View.Sprite.SpriteAction;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Line;

public class CanvasViewImpl implements CanvasView{
	private final static String RESOURCES_PATH = "resources/View/";
	private final static String PROPERTIES_FILENAME = "CanvasView";
	private ResourceBundle viewResources;
	private Group root;
	private Sprite sprite;
	private int viewWidth;
	private int viewHeight;
	private int spriteWidth;
	private int spriteHeight;
	
	public CanvasViewImpl(int aviewWidth, int aviewHeight) throws InvalidImageFileException{
		viewWidth = aviewWidth;
		viewHeight = aviewHeight;
		storeViewProperties();
		root = new Group();
		instantializeSprite();
		root.getChildren().add(sprite.getImageView());
	}
	
	public void setImage(File imgFile) throws InvalidImageFileException{
		sprite.setImage(imgFile);
	}

	private void storeViewProperties() {
		viewResources = ResourceBundle.getBundle(RESOURCES_PATH + PROPERTIES_FILENAME);
		 spriteWidth = Integer.parseInt(viewResources.getString("spriteWidth"));
		 spriteHeight = Integer.parseInt(viewResources.getString("spriteHeight"));
	}

	private void instantializeSprite() throws InvalidImageFileException {
		File defaultSpriteFile = new File(viewResources.getString("defaultTurtleFilename"));
		sprite = new Sprite(defaultSpriteFile, spriteWidth, spriteHeight, viewWidth, viewHeight);
	}

	public Node getView(){
		return root;
	}

	public int execute(String action, double... args){
		try {
			System.out.println("SpriteAction_" + action);
			SpriteAction currAction = (SpriteAction) Class.forName("SLogo.View.Sprite.SpriteAction_" + action).newInstance();
			return currAction.invoke(this, sprite, args);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public void drawLine(int[] initPos, int[] endPos){
		Line line = new Line();
		line.setStartX(initPos[0]);
		line.setStartY(initPos[1]);
		line.setEndX(endPos[0]);
		line.setEndY(endPos[1]);
		root.getChildren().add(line);
	}

	@Override
	public void clearScreen() {
		// TODO Auto-generated method stub
		
	}

}
