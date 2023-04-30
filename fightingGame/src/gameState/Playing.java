package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.xml.transform.sax.SAXTransformerFactory;


import Sound.Sound;
import UI.PaussedOverlay;
import entities.Player;
import entities.PlayerI;
import entities.PlayerII;
import level.LevelManager;
import main.Game;
import utilz.LoadSave;

public  class Playing  extends State implements Statemethods{

	private PlayerI playerI;
	private PlayerII playerII;
	private LevelManager levelManager;
	private PaussedOverlay pauseOverlay;
	private boolean paused = false;
	private boolean gameOver;
	private Sound sound;

	private int x,y;
	
	public Playing(Game game) {
		super(game);
		initClasses();
	}

	
	private void initClasses() {
		levelManager = new LevelManager(game);
		playerI = new PlayerI(200, 100,150,200,this);	
		playerII =  new PlayerII(1000, 100,150,200,this);
		pauseOverlay = new PaussedOverlay(this);
		String nameSound = LoadSave.readFile("nameSound");
		sound = new Sound("Sound/"+nameSound+".wav");
	}
	
	public void windowFocusLost() {
		playerI.resetDirBooleans();
		playerII.resetDirBooleans();
	}
	
	

	
	
	@Override
	public void update() {
		if(!paused) {
			levelManager.update();
			playerI.update(playerII);
			playerII.update(playerI);
			sound.playMusic();
			sound.setVolume(changeVol(pauseOverlay.getSound()));
		}
		else if(gameOver) {
//			System.out.println(1);
		}
		else {
			sound.pauseMusic();
			pauseOverlay.update();
			sound.pauseMusic();
			
		}	
	}
	
	private float changeVol(int xSound) {
		int max=283; xSound = pauseOverlay.getSound()-483;
		double soundX = Math.log10((float)xSound/max)*20;
		return (float)soundX;
	}
	
	public boolean isGameOver() {
		return gameOver;
	}


	@Override
	public void draw(Graphics g) {
		if(paused)	{
			g.setColor(new Color(0,0,0,100));
			g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
			pauseOverlay.draw(g);
		}	
		else if(gameOver) {	
			Graphics2D g2d=(Graphics2D)g;
			g2d.setColor(Color.red);
			g2d.setFont(new Font("SansSerif", Font.ITALIC, 60));
			if(playerI.getCurrentHealth()==0) {
				playerII.drawGameEnd(g2d);
				levelManager.draw(g2d);
				if(playerII.getCountZoomIn()==playerII.getFPS()) {
					playerII.drawWin(g2d);
					int writeX = (int)(-playerII.getTranslateX());
					int writeY = (int)(-playerII.getTranslateY())+150;
					g2d.drawString("Winner",writeX ,writeY);
				}
			}else if(playerII.getCurrentHealth()==0) {
				playerI.drawGameEnd(g2d);
				levelManager.draw(g2d);
				if(playerI.getCountZoomIn()==playerI.getFPS()) {
					playerI.drawWin(g2d);
					int writeX = (int)(-playerI.getTranslateX());
					int writeY = (int)(-playerI.getTranslateY())+150;
					g2d.drawString("Winner",writeX ,writeY);
				}
			}	
		}
		else {
			levelManager.draw(g);
			playerI.render(g,playerII);
			playerII.render(g,playerI);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseDragged(MouseEvent e) {
		if(paused)
			pauseOverlay.mouseDragged(e);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(paused)
			pauseOverlay.mousePressed(e);	
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(paused) {
			pauseOverlay.mouseReleased(e);
		}	
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		if(paused)
			pauseOverlay.mouseMoved(e);
		
	}
	
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}





	@Override
	public void keyPressed(KeyEvent e) {
		keyPressedForPlayer1(e);
		keyPressedForPlayer2(e);
		
	}
	
	public void keyPressedForPlayer1(KeyEvent e) {
		
		//Di chuyen
		switch(e.getKeyCode()) {
		case KeyEvent.VK_D:
			playerI.setRightPressed(true);
			break;
		case KeyEvent.VK_A:
			playerI.setLeftPressed(true);
			break;
		case KeyEvent.VK_K:
			playerI.setJumpPressed(true);
			break;
		case KeyEvent.VK_ESCAPE:
			paused = !paused;
			break;
		}
		
		
		//Tan cong
		switch(e.getKeyCode()) {
		case KeyEvent.VK_J:
			playerI.setAttacking(true);
			break;
		case KeyEvent.VK_U:
			playerI.setUPressed(true);
			break;
		case KeyEvent.VK_I:
			playerI.setIPressed(true);
			break;
		case KeyEvent.VK_O:
			playerI.setOPressed(true);
			break;
		}	
	}
	
	
	//Key for player2
	public void keyPressedForPlayer2(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
//			System.out.println("Phai");
			playerII.setRightPressed(true);
			break;
		case KeyEvent.VK_LEFT:
//			System.out.println("Trai");
			playerII.setLeftPressed(true);
			break;
		case KeyEvent.VK_NUMPAD2:
//			System.out.println("Nhay");
			playerII.setJumpPressed(true);
			break;
		}
//		//Tan cong
		switch(e.getKeyCode()) {
		case KeyEvent.VK_NUMPAD1:
//			System.out.println("Tan cong binh thuong");
			playerII.setAttacking(true);
			break;
		case KeyEvent.VK_NUMPAD4:
//			System.out.println("Tan cong binh thuong");
			playerII.setNumpad_4(true);
			break;
		case KeyEvent.VK_NUMPAD5:
//			System.out.println("Tan cong binh thuong");
			playerII.setNumpad_5(true);
			break;
		case KeyEvent.VK_NUMPAD6:
//			System.out.println("Tan cong binh thuong");
			playerII.setNumpad_6(true);
			break;
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		keyReleasedForPlayer1(e);
		keyReleasedForPlayer2(e);
		
	}
	
	public void keyReleasedForPlayer1(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_D:
			playerI.setRightPressed(false);
			break;
		case KeyEvent.VK_A:
			playerI.setLeftPressed(false);
			break;
		case KeyEvent.VK_K:
			playerI.setJumpPressed(false);
			break;
		}
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_J:
			playerI.setAttacking(false);
			break;
		}
	}
	
	public void keyReleasedForPlayer2(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			playerII.setRightPressed(false);
			break;
		case KeyEvent.VK_LEFT:
			playerII.setLeftPressed(false);
			break;
		case KeyEvent.VK_NUMPAD2:
			playerII.setJumpPressed(false);
			break;
		}
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_NUMPAD1:
			System.out.println("Tan cong binh thuong");
			playerII.setAttacking(false);
			break;
		}
	}


	public void unpauseGame() {
		paused= false;
		
	}



	
}
