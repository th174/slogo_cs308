package SLogo.View.Sprite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {
	private File defaultSpriteFile;
	private ImageView spriteIV;
	private Image spriteImg;
	private int spriteWidth;
	private int spriteHeight;
	private int viewWidth;
	private int viewHeight;
	private int direction;
	private int position[];
	private boolean hidden;
	
	public Sprite(File adefaultSpriteFile, int aspriteWidth, int aspriteHeight, int aviewWidth, int aviewHeight){
		spriteWidth = aspriteWidth;
		spriteHeight = aspriteHeight;
		viewWidth = aviewWidth;
		viewHeight = aviewHeight;
		defaultSpriteFile = adefaultSpriteFile;
		spriteIV = new ImageView();
		setImage(defaultSpriteFile);
		position = new int[] {viewWidth/2 - spriteWidth/2, viewHeight/2 - spriteHeight/2};
		spriteIV.setX(position[0]);
		spriteIV.setY(position[1]);
		setDirection(90);
		setHidden(false);
	}
	
	public void setImage(File newSpriteFile){
		try {
			spriteImg = new Image(new FileInputStream(newSpriteFile));
			spriteIV.setImage(spriteImg);
			spriteIV.setFitWidth(spriteWidth);
			spriteIV.setFitHeight(spriteHeight);
		} catch (IOException e) {
	    	System.out.println("Image Error");
			//JOptionPane.showMessageDialog(null, "Invalid File");
		}
	}
	
	public ImageView getImageView(){
		return spriteIV;
	}
	
	/**
	 * @return Array of length 2: First element is the X-coordinate, second element is the Y-coordinate
	 */
	public int[] getPosition(){
		return position;
	}
	
	public void setPosition(int[] newPos){
		position = newPos;
		spriteIV.setX(position[0]);
		spriteIV.setY(position[1]);
	}
	
	public int getDirection(){
		return direction;
	}
	
	public void setDirection(int newDirection){
		spriteIV.setRotate(newDirection - direction);
		direction = newDirection;
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
