package UI;

import static utilz.Constaints.UI.VolumeButton.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import utilz.LoadSave;
import gameState.Menu;
public class VolumeButton extends PauseButton {

	private BufferedImage[] imgBufferedImages;
	private BufferedImage sliderBufferedImage;
	public int index,buttonX,minX,maxX;
	private boolean mouseOver,mousePressed;
//	private boolean beginGame = true;
	public VolumeButton(int buttonX,int x, int y, int width, int height) {
		super(x+width/2, y, VOLUME_WIDTH, height);
		this.buttonX = buttonX;
//		buttonX = x + width / 2;
		boundsRectangle.x = buttonX;
		this.x = x;
		this.width = width;
		minX = x + VOLUME_WIDTH/2;
		maxX = x + width - VOLUME_WIDTH/2;
		loadImgs();
	}
	
	
	private void loadImgs() {
		BufferedImage temp = LoadSave.GetSpriteAtlas("button", 6);
		imgBufferedImages = new BufferedImage[3];
		for(int i=0;i<imgBufferedImages.length;i++) {
			imgBufferedImages[i] = temp.getSubimage(i*VOLUME_DEFAULT_WIDTH,0, VOLUME_DEFAULT_WIDTH, VOLUME_DEFAULT_WIDTH);
		}
		sliderBufferedImage = temp.getSubimage(3 * VOLUME_DEFAULT_WIDTH, 0, SLIDER_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);
	}
	
	public void changeX(int x) {
		if(x<minX)
			buttonX = minX;
		else if(x>maxX)
			buttonX = maxX;
		else
			buttonX = x;
		boundsRectangle.x = buttonX - VOLUME_WIDTH/2;
	}
	public void update() {
		index = 0;
		if(mouseOver)
			index=1;
		if(mousePressed)
			index =2;
	}
	public void draw(Graphics g) {
		g.drawImage(sliderBufferedImage,x,y,width,height, null);
		g.drawImage(imgBufferedImages[index],buttonX - VOLUME_WIDTH/2,y,VOLUME_WIDTH,VOLUME_HEIGHT, null);
	}
	
	public void resetBool() {
		mouseOver= false;
		mousePressed= false;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
}
