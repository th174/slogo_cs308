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
		move(new int[] {((Double)newProperties[2]).intValue(), ((Double) newProperties[3]).intValue()});
		setHidden((boolean)newProperties[4]);
	}
	
	private void setHidden(boolean hidden) {
		sprite.setHidden(hidden);
	}

	public void setPenColor(Color color) {
		penColor = color;
		
	}
	
	private int[] findIntercepts(double x, double y, double xVector, double yVector){
		int[] position = absoluteToZero(new int[] {(int) x,(int) y});
		if (checkVerticalOrHorizontalSlope(position[0], position[1], xVector, yVector) != null){
			return checkVerticalOrHorizontalSlope(position[0], position[1], xVector, yVector);
		}
		double m = yVector/xVector;
		double xRef = viewWidth/2;
		double yRef = viewHeight/2;
		if (xVector < 0){
			xRef *= -1;
		}
		if (yVector < 0){
			yRef *= -1;
		}
		double refVector = (position[1]-yRef)/(position[0]-xRef);
		double b = position[1] - m * position[0];
		double xInt;
		double yInt;
		if (Math.abs(m) > Math.abs(refVector)){
			//bouncing off top or bottom
			yInt = yRef;
			xInt = (yRef-b)/m;
		}
		else {
			//bouncing off right or left
			xInt = xRef;
			yInt = m*(xRef) + b;
		}
		return zeroToAbsolute(new int[] {(int) xInt, (int) yInt});
	}
	
	private int[] checkVerticalOrHorizontalSlope(int x, int y, double xVector, double yVector) {
		if (xVector == 0){
			if (yVector > 0){
				return zeroToAbsolute(new int[] {x, viewHeight/2});
			}
			else{
				return zeroToAbsolute(new int[] {x, -1 * viewHeight/2});
			}
		}
		if (yVector == 0){
			if (xVector > 0){
				return zeroToAbsolute(new int[] {viewWidth/2, y});
			}
			else{
				return zeroToAbsolute(new int[] {0-1 * viewWidth/2, y});
			}
		}
		return null;
	}
	
	private void move(int[] vector){
		vector[0] = Math.round(vector[0]);
		vector[1] = Math.round(vector[1]);
		ArrayList<int[]> linesToMake = new ArrayList<int[]>();
		addLinesToMake(vector, linesToMake);
		int[] finalPosition = sprite.getPosition();
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
	}
	
	private int[] zeroToAbsolute(int[] zeroPosition){
		return new int[] {zeroPosition[0]+viewWidth/2, -1*(zeroPosition[1]-viewHeight/2)};
	}
	
	private int[] absoluteToZero(int[] absolutePosition){
		return new int[] {absolutePosition[0]-viewWidth/2, (-1*absolutePosition[1])+viewHeight/2};
	}

	private void addLinesToMake(int[] vector, ArrayList<int[]> linesToMake) {
		//yVector is conventionally facing (+ is up), but this class uses unconventional (+ is down)
		int[] currLocation = sprite.getPosition();
		int[] nextLocation;
		int count = 0;
		while (count < 100){
			count++;
			nextLocation = new int[] {currLocation[0] + vector[0], currLocation[1] - vector[1]};
			if (nextLocation[0] > viewWidth || nextLocation[0] < 0 || nextLocation[1] > viewHeight || nextLocation[1] < 0){
				int[] intercepts = findIntercepts(currLocation[0], currLocation[1], vector[0], vector[1]);
				int deltaX = intercepts[0] - currLocation[0];
				int deltaY = intercepts[1] - currLocation[1];
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
				vector[0] -= deltaX;
				vector[1] += deltaY;
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
		return absoluteToZero(sprite.getPosition());
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