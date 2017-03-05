package SLogo.View;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import SLogo.View.Sprite.Sprite;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class CanvasViewImpl implements CanvasView {
	private static final String defaultMapPropertiesFilename = "data/defaultViewMapProperties.xml";
	private SLogoGUIImpl gui;
	private Group root;
	private Sprite sprite;
	private int viewWidth;
	private int viewHeight;
	private int spriteWidth;
	private int spriteHeight;
	private String defaultTurtleFilename;
	private Map<Double, Color> colorMap;
	private Map<Double, String> imageMap;
	
	private boolean turtleHidden;
	private boolean penDown;
	private double penColor;
	private double penWidth;
	private double currentImageIndex;
	
	public CanvasViewImpl(int aviewWidth, int aviewHeight, SLogoGUIImpl SLogoGUI){
		gui = SLogoGUI;
		viewWidth = aviewWidth;
		viewHeight = aviewHeight;
		root = new Group();
		instantializeSprite();
		colorMap = new HashMap<Double, Color>();
		imageMap = new HashMap<Double, String>();
		XMLParser.populateMaps(colorMap, imageMap, defaultMapPropertiesFilename);
	}

	public void update(Observable o, Object n){
		Object[] newProperties = (Object[]) n;
		setPen((boolean) newProperties[0]);
		setPenColor(penColor);
		sprite.setDirection(((Double) newProperties[1]).intValue());
		move(new int[] {((Double)newProperties[2]).intValue(), ((Double) newProperties[3]).intValue()});
		setHidden((boolean)newProperties[4]);
	}
	
	private void setHidden(boolean hidden) {
		sprite.setHidden(hidden);
		turtleHidden = hidden;
	}

	public int setPenColor(double index) {
		penColor = index;
		return (int) index;
	}
	
	public int getPenColor(){
		for (HashMap.Entry<Double, Color> e : colorMap.entrySet()) {
		    Double indexEntry = (Double) e.getKey();
		    Color colorEntry = (Color) e.getValue();
		    if (colorEntry.equals(colorMap.get(penColor))){
		    	return indexEntry.intValue();
		    }
		}
		new ErrorPrompt("Color index cannot be found");
		return 0;
	}
	
	public int setPenSize(double index){
		penWidth = (int) index;
		return (int) index;
	}
	
	public int setShape(double index){
		sprite.setImage(new File(imageMap.get(index)));
		currentImageIndex = index;
		return (int) index;
	}
	
	public int getShape(){
		return (int) currentImageIndex;
	}
	
	public int setBackground(double index){
		gui.setTurtleBackgroundColor(colorMap.get(index));
		return (int) index;
	}
	
	public int setPalette(double index, double r, double g, double b){
		Color color = Color.rgb((int) r, (int) g, (int) b, .99);
		colorMap.put(index, color);
		return (int) index;
	}
	
	public int[] getPalette(double index){
		Color color = colorMap.get(index);
		return new int[] { (int) color.getRed(), (int) color.getGreen(),  (int) color.getBlue()};
	}
	
	private void move(int[] vector){
		vector[0] = Math.round(vector[0]);
		vector[1] = Math.round(vector[1]);
		ArrayList<int[]> linesToMake = new ArrayList<int[]>();
		TurtleMath.addLinesToMake(viewWidth, viewHeight, sprite.getPosition(), vector, linesToMake);
		int[] finalPosition = sprite.getPosition();
		for (int[] coordinates : linesToMake){
			if (penDown){
				Line line = new Line();
				line.setStartX(coordinates[0]);
				line.setStartY(coordinates[1]);
				line.setEndX(coordinates[2]);
				line.setEndY(coordinates[3]);
				line.setFill(colorMap.get(penColor));
				line.setStrokeWidth(penWidth);
				root.getChildren().add(line);
			}
			finalPosition = new int[] {coordinates[2], coordinates[3]};
		}
		sprite.setPosition(finalPosition);
	}
	
	public void setImage(String filename){
		currentImageIndex = imageMap.size();
		imageMap.put((double) currentImageIndex, filename);
		sprite.setImage(new File(filename));
	}
	
	public void setPen(boolean newPen){
		penDown = newPen;
	}

	private void instantializeSprite(){
		currentImageIndex = imageMap.size();
		imageMap.put(currentImageIndex, defaultTurtleFilename);
		File defaultSpriteFile = new File(defaultTurtleFilename);
		sprite = new Sprite(defaultSpriteFile, spriteWidth, spriteHeight, viewWidth, viewHeight);
		root.getChildren().add(sprite.getImageView());
	}

	public Node getView(){
		return root;
	}
	
	/**
	 * 
	 * @return	Sprite's absolute location
	 */
	public int[] getSpritePosition(){
		return TurtleMath.absoluteToZero(viewWidth, viewHeight, sprite.getPosition());
	}

	@Override
	public double clearScreen(){
		root.getChildren().clear();
		instantializeSprite();
		return 0;
	}

}