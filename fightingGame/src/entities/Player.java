package entities;
//import static utilz.Constaints.PlayerConstaints.*;
import static utilz.HelpMethods.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import gameState.Playing;
import main.Game;
import main.GameWindow;
import utilz.LoadSave;
public class Player extends Entity {
	protected BufferedImage[][] animations;
	public int aniTick=0,introTick=0,skillTick=0, aniIndex=1, aniSpeed =10;
	protected boolean  attacking = false, notMoveAnimation=false;
	protected boolean leftPressed,rightPressed,jumpPressed;
	protected float playerSpeed =1.5f * Game.SCALE;
	protected float xDrawOffset=50;
	protected int spriteAmount[]; //mang nay dung de luu so animation cua moi hanh dong
	//Jumping gravity
	protected float vy;        // vertical velocity
	protected int jumpsLeft;	
	//StatusBarUI
	protected BufferedImage statusBarImg;	
	protected int statusBarWidth = (int) (192 * Game.SCALE);
	protected int statusBarHeight = (int) (58 * Game.SCALE);
	protected int statusBarX = (int) (10 * Game.SCALE);
	protected int statusBarY = (int) (10 * Game.SCALE);	    
	protected int healthBarWidth = (int) (153 * Game.SCALE);
	protected int healthBarHeight = (int) (4 * Game.SCALE);
	protected int healthBarXStart = (int) (34 * Game.SCALE);
	protected int healthBarYStart = (int) (14 * Game.SCALE);
	protected int healthWidth = healthBarWidth;	
	protected int manaBarXStart = (int) (34 * Game.SCALE)+30;
	protected int manaBarYStart = (int) (14 * Game.SCALE)+30 ;
	protected int manaBarWidth = (int) (153 * Game.SCALE)-65;
	protected int manaBarHeight = (int) (4 * Game.SCALE);
	protected int manaWidth = manaBarWidth;	
	protected int maxHealth = 100;
	protected int maxMana = 50;
	protected int currentHealth = maxHealth;
	protected int curMana = 25;
	//Attack box
	protected Rectangle2D.Float attackBox;
	protected int flipX =0,flipW = 1;	
	protected boolean damage;
	protected Playing playing;	
	protected boolean attackedByEnemy=false;	
	private double scaleX = 0,scaleY = 0;
	private double translateX = 0,translateY = 0;
	private int countZoomIn = 0, countZoomOut=0; 
	private int FPS = 120;
	private int xOffSet = (int)(200*Game.SCALE),yOffSet = (int)(1*Game.SCALE);
	public Player(float x, float y,int width,int height,Playing playing) {
		super(x, y,width,height);
		this.playing=playing;
		initHitbox(x, y, (int)(28*Game.SCALE), (int)(100*Game.SCALE));
		initAttackBox();
        vy = 0;
        jumpsLeft = 2;  // allow double jumping
	}
	
	public void drawGameEnd(Graphics2D g2d) {
		hitbox.x=Game.GAME_WIDTH/2;

		
		double x = hitbox.x-xOffSet;
		double y = hitbox.y-hitbox.width-yOffSet;
		double xTrans  = x/FPS;
		double yTrans =y/FPS;
		double width = Game.GAME_WIDTH-x;
		double height = Game.GAME_HEIGHT-y;
		double scaleUpX = 1.5/FPS;
		double scaleUpY = 2.5/FPS;
		if(countZoomIn<FPS) {
			countZoomIn++;
			scaleX+=scaleUpX;
			scaleY+=scaleUpY;
			translateX-=xTrans;
			translateY-=yTrans;
			
		}
		g2d.scale(1+scaleX,1+scaleY);
		g2d.translate(translateX,translateY);	
	}
	
	
	
	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public int getFPS() {
		return FPS;
	}

	public void setFPS(int fPS) {
		FPS = fPS;
	}

	public int getCountZoomIn() {
		return countZoomIn;
	}

	public void setCountZoomIn(int countZoomIn) {
		this.countZoomIn = countZoomIn;
	}

	public int getCountZoomOut() {
		return countZoomOut;
	}

	public void setCountZoomOut(int countZoomOut) {
		this.countZoomOut = countZoomOut;
	}

	protected void initAttackBox() {
		
		
		x = hitbox.x + hitbox.width + (int)(10*Game.SCALE);
		attackBox  = new Rectangle2D.Float(x,y,(int)(20 * Game.SCALE),(int)(20 * Game.SCALE));
		
	}

	public void update() {
		
	}
	



	protected void updateAttackBox() {
		if(rightPressed) {
			attackBox.x = hitbox.x + hitbox.width + (int)(10*Game.SCALE);
		}else if(leftPressed) {
			attackBox.x = hitbox.x  -(int)(10*Game.SCALE) -attackBox.width;
		}
		attackBox.y =hitbox.y + (int)(Game.SCALE *40);	
	}

	protected void changeHealth(int value) {
		currentHealth += value;
		if(currentHealth<=0) {
			currentHealth = 0;
			//Doi thu thang
		}else if(currentHealth>=maxHealth) {
			currentHealth=maxHealth;
		}
	}
	protected void changeMana(int mana) {
		curMana+=mana;
		if(curMana>maxMana) {
			curMana=maxMana;
		}else if(curMana<=0) {
			curMana=0;
		}
	}
	protected void updateHealthBar() {
		healthWidth = (int) ((currentHealth / (float) maxHealth) * healthBarWidth);	
	}
	protected void updateManaBar() {
		manaWidth = (int) ((curMana/(float)maxMana)*manaBarWidth);
//		System.out.println(manaBarWidth);
	}

	
	

	public double getTranslateX() {
		return translateX;
	}

	public void setTranslateX(double translateX) {
		this.translateX = translateX;
	}

	public double getTranslateY() {
		return translateY;
	}

	public void setTranslateY(double translateY) {
		this.translateY = translateY;
	}

	public void jumpUpdate() {
//		System.out.println(jumpsLeft);
		hitbox.y += vy;    // move vertically
        vy += 0.1;  // add gravity effect
        if (hitbox.y >= 400) {   // check for landing
        	hitbox.y = 400;
            vy = 0;
            jumpsLeft = 2;  // reset jumps
        }
        if (jumpsLeft == 0 && hitbox.y >= 250 ) {
            jumpsLeft = 1;  // allow single jump after falling
        }
    }
	
	  public void jump() {
	        if ( jumpPressed==true && jumpsLeft > 0) {
	            if (jumpsLeft == 2) {
	                vy = -8;   // set upward velocity for double jump
	            } else if (jumpPressed==true && jumpsLeft == 1) {
	                vy = -4;    // set upward velocity for single jump
	            }
	            jumpsLeft--;
	        }
	 }
	public void render(Graphics g) {
		drawHitbox(g);
	}
	
	protected void drawAttackBox(Graphics g) {
//		g.setColor(Color.red);
//		g.drawRect((int)attackBox.x -lvlOffSet,(int) attackBox.y, (int)attackBox.width,(int) attackBox.height);
		
	}

	protected void drawUI(Graphics g) {
		g.drawImage(statusBarImg,statusBarX,statusBarY,statusBarWidth,statusBarHeight,null);
		g.setColor(Color.red);
		g.fillRect(healthBarXStart+statusBarX, healthBarYStart + statusBarY, healthWidth, healthBarHeight);
		
		g.setColor(Color.yellow);
		g.fillRect(manaBarXStart, manaBarYStart+ statusBarY, manaWidth, manaBarHeight);
	}


	public int isHealth() {
		return currentHealth;
	}	
	protected void resetAniTick() {
		aniTick=0;
		aniIndex=1;
		
	}

	protected void updatePos() {
		notMoveAnimation=false;
		if(!leftPressed && !rightPressed && !jumpPressed) {
			return ;
		}
		float xSpeed = 0;
		if(jumpPressed==true) {
			jump();
		}
		
		if(leftPressed==true) {
			xSpeed = -playerSpeed;
			flipX = width;
			flipW =-1;
		}
		else if(rightPressed==true) {
			xSpeed = playerSpeed;
			flipX =0;
			flipW =1;
		}
		
		if(CanMoveHere(hitbox.x+xSpeed, width, height)) {
			hitbox.x+=xSpeed;

		}else {
			notMoveAnimation=true;
		}
	}

	public void resetDirBooleans() {
		leftPressed=false;
		rightPressed=false;
	}
	
	public void setAttacking(boolean attacking) {
		this.attacking=attacking;
	}
	
	public boolean isLeftPressed() {
		return leftPressed;
	}

	public void setLeftPressed(boolean leftPressed) {
		this.leftPressed = leftPressed;
	}

	public boolean isRightPressed() {
		return rightPressed;
	}

	public void setRightPressed(boolean rightPressed) {
		this.rightPressed = rightPressed;
	}
	public void setJumpPressed(boolean jumpPressed) {
		this.jumpPressed = jumpPressed;
		
	}
	
	public boolean isJumpPressed() {
		return jumpPressed;
	}

	public boolean isDamage() {
		return damage;
	}

	public void setDamage(boolean damage) {
		this.damage = damage;
	}
}
