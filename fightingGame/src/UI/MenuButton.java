package UI;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gameState.GameState;
import utilz.LoadSave;
import static utilz.Constaints.UI.Button.*;
public class MenuButton {
	private int xPos,yPos,rowIndex,index;
	private int xOffsetCenter = B_WIDTH/2;
	private GameState state;
	private ArrayList<BufferedImage> imgs = new ArrayList<>();
	private boolean mouseOver,mousePressed;
	private Rectangle bounds;
	public MenuButton(int xPos,int yPos,int rowIndex,GameState state) {
		this.xPos=xPos;
		this.yPos=yPos;
		this.rowIndex=rowIndex;
		this.state=state;
		loadImgs();
		initBounds();
	}
	private void initBounds() {
		bounds =  new Rectangle(xPos-xOffsetCenter,yPos,B_WIDTH,B_HEIGHT);
		
	}
	private void loadImgs() {
//		imgs = new BufferedImage[3];
		BufferedImage temp= LoadSave.GetSpriteAtlas("button",1);
		for(int i=0;i<3;i++) {
			BufferedImage addImage=temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
			imgs.add(addImage);
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(imgs.get(index), xPos-xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
	}
	
	public void update() {
		index = 0;
		if(mouseOver==true) {
			index=1;
		}
		if(mousePressed==true) {
			index = 2;
		}
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
	
	public void applyGamestate() {
		GameState.state = state;
	}
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
}
