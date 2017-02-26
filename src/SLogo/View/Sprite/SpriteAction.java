package SLogo.View.Sprite;

import SLogo.View.CanvasViewImpl;

public abstract class SpriteAction {

	public abstract int invoke(CanvasViewImpl canvas, Sprite sprite, double[] args);
}
