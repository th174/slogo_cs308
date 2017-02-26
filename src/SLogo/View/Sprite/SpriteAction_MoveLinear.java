package SLogo.View.Sprite;

import SLogo.View.CanvasViewImpl;

public class SpriteAction_MoveLinear extends SpriteAction{
	private Sprite sprite;
	private int distance;
	private int[] position;
	private int[] newPosition;
	private double direction;

	public int invoke(CanvasViewImpl canvas, Sprite asprite, double[] args){
		sprite = asprite;
		distance = (int) args[0];
		position = sprite.getPosition();
		direction = sprite.getDirection();
		int changeX = (int) Math.cos(Math.toRadians(direction)) * distance;
		int changeY = (int) Math.sin(Math.toRadians(direction)) * distance;
		changePosition(new int[] {changeX, changeY});
		canvas.drawLine(position, newPosition);
		sprite.setPosition(newPosition);
		return distance;
	}

	private void changePosition(int[] changes) {
		newPosition = new int[2];
		for (int i = 0; i < changes.length; i++){
			if((newPosition[i] + changes[i]) < 0){
				newPosition[i] = newPosition[i] + changes[i] + sprite.getViewSize()[i];
			}
			else if ((newPosition[i] + changes[i] > sprite.getViewSize()[i])){
				newPosition[i] = newPosition[i] + changes[i] - sprite.getViewSize()[i];
			}
		}
	}
}
