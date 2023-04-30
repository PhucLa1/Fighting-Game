package main;

import java.awt.Dimension;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarInputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.plaf.synth.SynthDesktopIconUI;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
//import static utilz.Constaints.PlayerConstaints.*;
//import static utilz.Constaints.Direction.*;

public class GamePanel extends JPanel {
	
	private MouseInputs mouseInputs;
	private Game game;
	
	
	public GamePanel(Game game) {	
		mouseInputs= new MouseInputs(this);
		this.game=game;
		this.setDoubleBuffered(true);
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	


	private void setPanelSize() {
		Dimension size = new Dimension(GAME_WIDTH,GAME_HEIGHT);
//		setMinimumSize(size);
		setPreferredSize(size);
//		setMaximumSize(size);
		
	}

	
	public void updateGame() {

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);
	}
	
	public Game getGame() {
		return game;
	}





}
