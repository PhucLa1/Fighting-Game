package UI;

import java.awt.Rectangle;

public class PauseButton {
	protected int x,y,width,height;
	protected Rectangle boundsRectangle;
	
	public PauseButton(int x,int y,int width,int height) {
		this.x = x;
		this.y= y;
		this.width = width;
		this.height = height;
		
		createBounds();
	}
	
	public void createBounds() {
		boundsRectangle = new Rectangle(x,y,width,height);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Rectangle getBoundsRectangle() {
		return boundsRectangle;
	}

	public void setBoundsRectangle(Rectangle boundsRectangle) {
		this.boundsRectangle = boundsRectangle;
	}
	
	
	
}
