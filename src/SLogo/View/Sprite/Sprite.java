package SLogo.View.Sprite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
		position = new int[] {viewWidth/2, viewHeight/2};
		setPosition(position);
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
	    	showError("Invalid Image File or Index");
		}
	}
	
	private void showError (String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Image File Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
	
	public ImageView getImageView(){
		return spriteIV;
	}
	
	/**
	 * @return Array of length 2: First element is the X-coordinate, second element is the Y-coordinate. Center is (0,0)
	 */
	public int[] getPosition(){
		return position;
	}
	
	public void setPosition(int[] newPos){
		position = newPos;
		spriteIV.setX(position[0] - spriteWidth/2);
		spriteIV.setY(position[1] - spriteHeight/2);
	}
	
	public int getDirection(){
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
