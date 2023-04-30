package level;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class LevelManager {
	
	private Game game;
	private BufferedImage images;
	private int x,y;
	
	public LevelManager(Game game) {
		this.game=game;
		
		images = LoadSave.GetSpriteAtlas("background",1);
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


	public void draw(Graphics g) {
		g.drawImage(images, x, y,GAME_WIDTH,GAME_HEIGHT, null);
	}
	
	public void update() {
		
	}
}
