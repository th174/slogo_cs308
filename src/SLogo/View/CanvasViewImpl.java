package SLogo.View;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

import SLogo.View.Sprite.Sprite;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class CanvasViewImpl implements CanvasView {
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
	
	private boolean hidden;
	private boolean penDown;
	private Color penColor;
	
	public CanvasViewImpl(int aviewWidth, int aviewHeight){
		viewWidth = aviewWidth;
		viewHeight = aviewHeight;
		storeViewProperties();
		root = new Group();
		instantializeSprite();
	}
	
	public void update(Observable o, Object n){
		Object[] newProperties = (Object[]) n;
		setPen((boolean) newProperties[0]);
		setPenColor(Color.BLACK);
		sprite.setDirection(((Double) newProperties[1]).intValue());
		System.out.println("move: " + newProperties[2] +" " +  newProperties[3]);
		move(new int[] {((Double)newProperties[2]).intValue(), ((Double) newProperties[3]).intValue() * -1});
		setHidden((boolean)newProperties[4]);
	}
	
	private void setHidden(boolean hidden) {
		sprite.setHidden(hidden);
	}

	public void setPenColor(Color color) {
		penColor = color;
		
	}
	
	private int[] findIntercepts(double x, double y, double xVector, double yVector){
		if (checkVerticalOrHorizontalSlope(x, y, xVector, yVector) != null){
			return checkVerticalOrHorizontalSlope(x, y, xVector, yVector);
		}
		double vectorSlope = xVector/yVector;
		double xRef = 0;
		double yRef = 0;
		if (xVector > 0){
			xRef = viewWidth;
		}
		if (yVector > 0){
			yRef = viewHeight;
		}
		return interceptMath(x, y, xVector, yVector, vectorSlope, xRef, yRef);
	}

	public int[] interceptMath(double x, double y, double xVector, double yVector, double vectorSlope, double xRef,
			double yRef) {
		double refSlope = (y-yRef)/(x-xRef);
		double xInt;
		double yInt;
		if (Math.abs(vectorSlope) > Math.abs(refSlope)){
			//bouncing off top or bottom
			if (yVector > 0){
				//bottom
				yInt = viewWidth;
				xInt = x + (viewHeight - y)/vectorSlope;
			}
			else {
				//top
				yInt = 0;
				xInt = x + y/vectorSlope;
			}
		}
		else {
			//bouncing off right or left
			if (xVector > 0){
				//right
				xInt = viewWidth;
				yInt = y + (viewWidth - x)*vectorSlope;
			}
			else {
				//left
				xInt = 0;
				yInt = y + x*vectorSlope;
			}
		}
		return new int[] {(int) yInt, (int) xInt};
	}

	private int[] checkVerticalOrHorizontalSlope(double x, double y, double xVector, double yVector) {
		if (xVector == 0){
			if (yVector > 0){
				return new int[] {(int) x, viewWidth};
			}
			else{
				return new int[] {(int) x, 0};
			}
		}
		if (yVector == 0){
			if (xVector > 0){
				return new int[] {viewWidth, (int) y};
			}
			else{
				return new int[] {0, (int) y};
			}
		}
		return null;
	}

	private void move(int[] vector){
		vector[0] = Math.round(vector[0]);
		vector[1] = Math.round(vector[1]);
		ArrayList<int[]> linesToMake = new ArrayList<int[]>();
		addLinesToMake(vector, linesToMake);
		int[] finalPosition = sprite.getAbsolutePosition();
		for (int[] coordinates : linesToMake){
			if (penDown){
				Line line = new Line();
				line.setStartX(coordinates[0]);
				line.setStartY(coordinates[1]);
				line.setEndX(coordinates[2]);
				line.setEndY(coordinates[3]);
				line.setFill(penColor);
				root.getChildren().add(line);
			}
			finalPosition = new int[] {coordinates[2], coordinates[3]};
		}
		sprite.setPosition(finalPosition);
		System.out.println("pos" + finalPosition[0] + " " + finalPosition[1]);
	}

	private void addLinesToMake(int[] vector, ArrayList<int[]> linesToMake) {
		int[] currLocation = sprite.getAbsolutePosition();
		int[] nextLocation;
		while (true){
			nextLocation = new int[] {currLocation[0] + vector[0], currLocation[1] + vector[1]};
			if (nextLocation[0] > viewWidth || nextLocation[0] < 0 || nextLocation[1] > viewHeight || nextLocation[1] < 0){
				int[] intercepts = findIntercepts(currLocation[0], currLocation[1], vector[0], vector[1]);
				linesToMake.add(new int[] {currLocation[0], currLocation[1], intercepts[0], intercepts[1]});
				currLocation = intercepts;
				if (currLocation[0] == 0){
					currLocation[0] = viewWidth;
				}
				else if (currLocation[0] == viewWidth){
					currLocation[0] = 0;
				}
				if (currLocation[1] == 0){
					currLocation[1] = viewHeight;
				}
				else if (currLocation[1] == viewHeight){
					currLocation[1] = 0;
				}
			}
			else{
				linesToMake.add(new int[] {currLocation[0], currLocation[1], nextLocation[0], nextLocation[1]});
				break;
			}
		}
	}
	
	public void setImage(File imgFile){
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

	private void instantializeSprite(){
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
		return sprite.getZeroIndexedPosition();
	}

	@Override
	public double clearScreen(){
		root.getChildren().clear();
		instantializeSprite();
		//TODO: return distance moved
		return 0;
	}

	@Override
	public int setBackground(double index) {
		return 0;
	}

	@Override
	public int setPenColor(double index) {
		return 0;
	}

	@Override
	public int setPenSize(double index) {
		return 0;
	}

	@Override
	public int setShape(double index) {
		return 0;
	}

	@Override
	public int setPalette(double index, double r, double g, double b) {
		return 0;
	}

	@Override
	public int getPenColor() {
		return 0;
	}

	@Override
	public int getShape() {
		return 0;
	}

}