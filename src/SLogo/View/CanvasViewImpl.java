package SLogo.View;

import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import SLogo.View.Sprite.Sprite;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class CanvasViewImpl implements CanvasView, Observer{
	private final static String RESOURCES_PATH = "resources/View/";
	private final static String PROPERTIES_FILENAME = "CanvasView";
	private ResourceBundle viewResources;
	private Group root;
	private Sprite sprite;
	private int viewWidth;
	private int viewHeight;
	private int spriteWidth;
	private int spriteHeight;
	private String defaultTurtleFilename;
	
	private boolean penDown;
	private Color penColor;
	
	public CanvasViewImpl(int aviewWidth, int aviewHeight) throws InvalidImageFileException{
		viewWidth = aviewWidth;
		viewHeight = aviewHeight;
		storeViewProperties();
		root = new Group();
		instantializeSprite();
		root.getChildren().add(sprite.getImageView());
	}
	
	public void update(Observable o, Object n){
		Object[] newProperties = (Object[]) n;
		setPen((boolean) newProperties[0]);
		setPenColor(Color.BLACK);
		sprite.setDirection((int) newProperties[2]);
		move(new int[] {(int) newProperties[2], (int) newProperties[3]});
	}
	
	public void setPenColor(Color color) {
		penColor = color;
	}

	private void move(int[] newPosition){
		if (penDown){
			Line line = new Line();
			line.setStartX(sprite.getPosition()[0]);
			line.setStartY(sprite.getPosition()[1]);
			line.setEndX(newPosition[0]);
			line.setEndY(newPosition[0]);
			line.setFill(penColor);
			root.getChildren().add(line);
		}
		sprite.setPosition(newPosition);
	}
	
	public void setImage(File imgFile) throws InvalidImageFileException{
		sprite.setImage(imgFile);
	}
	
	public void setPen(boolean newPen){
		penDown = newPen;
	}

	private void storeViewProperties() {
		viewResources = ResourceBundle.getBundle(RESOURCES_PATH + PROPERTIES_FILENAME);
		 spriteWidth = Integer.parseInt(viewResources.getString("spriteWidth"));
		 spriteHeight = Integer.parseInt(viewResources.getString("spriteHeight"));
		 defaultTurtleFilename = viewResources.getString("defaultTurtleFilename");
	}

	private void instantializeSprite() throws InvalidImageFileException {
		File defaultSpriteFile = new File(defaultTurtleFilename);
		sprite = new Sprite(defaultSpriteFile, spriteWidth, spriteHeight, viewWidth, viewHeight);
	}

	public Node getView(){
		return root;
	}
}
