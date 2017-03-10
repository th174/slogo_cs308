package SLogo.View.Sprite;

import SLogo.View.TurtleMath;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {
	private int myID;
	private Image defaultSpriteIMG;
	private ImageView spriteIV;
	private Image spriteImg;
	private int spriteWidth;
	private int spriteHeight;
	private int viewWidth;
	private int viewHeight;
	private int direction;
	private double[] position;
	private boolean hidden;
	private boolean penDown;
	private PropertiesDisplay propDisp;
	private TurtleMath tMath;
	
	public Sprite(int ID, Image adefaultSpriteIMG, int aspriteWidth, int aspriteHeight, int aviewWidth, int aviewHeight){
		myID = ID;
		spriteWidth = aspriteWidth;
		spriteHeight = aspriteHeight;
		viewWidth = aviewWidth;
		viewHeight = aviewHeight;
		defaultSpriteIMG = adefaultSpriteIMG;
		spriteIV = new ImageView();
		setImage(defaultSpriteIMG);
		position = new double[] {viewWidth/2, viewHeight/2};
		setPosition(position);
		setDirection(90);
		setHidden(false);
		tMath = new TurtleMath();
		propDisp = new PropertiesDisplay(myID, tMath.absoluteToZero(viewWidth, viewHeight, getPosition()), 
				getDirection(), getHidden(), penDown);
		spriteIV.setOnMouseClicked(e -> propDisp.toggleDisplay(e));
	}

	public void setImage(Image newSpriteIMG){
		spriteImg = newSpriteIMG;
		spriteIV.setImage(spriteImg);
		spriteIV.setFitWidth(spriteWidth);
		spriteIV.setFitHeight(spriteHeight);
	}

	public void setPen(boolean newPen){
		penDown = newPen;
	}
	
	public ImageView getImageView(){
		return spriteIV;
	}
	
	/**
	 * @return Array of length 2: First element is the X-coordinate, second element is the Y-coordinate. Center is (0,0)
	 */
	public double[] getPosition(){
		return position;
	}
	
	public void setPosition(double[] newPos){
		position = newPos;
		spriteIV.setX(position[0] - spriteWidth/2);
		spriteIV.setY(position[1] - spriteHeight/2);
	}
	
	public double getDirection(){
		return direction - 90;
	}
	
	public void setDirection(int degrees){
		direction = 90 - degrees;
		spriteIV.setRotate(direction);
	}
	
	public boolean getHidden(){
		return hidden;
	}
	
	public void setHidden(boolean newHidden){
		hidden = newHidden;
		spriteIV.setVisible(!hidden);
	}
	
	public int[] getViewSize(){
		return new int[] {viewWidth, viewHeight};
	}

}
