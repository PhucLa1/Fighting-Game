package gameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import UI.MenuButton;
import main.Game;
import utilz.LoadSave;

public class Menu extends State implements Statemethods {
	
	private MenuButton[] buttons = new MenuButton[3];
	private BufferedImage backgroundImage,backgroundMenu;
	private int menuX,menuY,menuWidth,menuHeight;

	public Menu(Game game) {
		
		super(game);
		loadButton();
		loadBackGround();
		
	}

	private void loadBackGround() {
		backgroundImage = LoadSave.GetSpriteAtlas("button", 2);
		backgroundMenu = LoadSave.GetSpriteAtlas("background", 2);
		menuWidth = (int)(backgroundImage.getWidth() * game.SCALE);
		menuHeight = (int)(backgroundImage.getHeight() * game.SCALE);
		menuX = Game.GAME_WIDTH / 2 - menuWidth/2;
		menuY = (int) (45 *Game.SCALE);
		
	}

	private void loadButton() {
		buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (150 * Game.SCALE), 0, GameState.PLAYING);
		buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (220 * Game.SCALE), 1, GameState.OPTIONS);
		buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (290 * Game.SCALE), 2, GameState.QUIT);
		
		
	}

	@Override
	public void update() {
//		System.out.println(beginGame);
		for(MenuButton mButton : buttons) {
			
			mButton.update();
			
		}
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(backgroundMenu, 0, 0, Game.GAME_WIDTH,  Game.GAME_HEIGHT, null);

		g.drawImage(backgroundImage, menuX,menuY, menuWidth, menuHeight, null);
		for(MenuButton mButton : buttons) {
			mButton.draw(g);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for(MenuButton mButton : buttons) {
			if(isIn(e, mButton)) {
				mButton.setMousePressed(true);
				break;
			}
		}
		
	}



	@Override
	/*Nếu con trỏ chuột nằm trong phạm vi 
	 * của một nút, phương thức kiểm tra 
	 * xem nút đó đã được nhấn chuột trước đó (isMousePressed()). 
	 * Nếu nút đã được nhấn chuột, phương thức sẽ gọi phương thức applyGamestate() 
	 * của MenuButton để áp dụng trạng thái trò chơi được liên kết với nút đó.
		Sau khi xử lý tất cả các MenuButton, phương thức sẽ gọi phương thức resetButtons() 
		để đặt lại trạng thái của tất cả các nút.*/
	public void mouseReleased(MouseEvent e) {
		for(MenuButton mButton : buttons) {
			if(isIn(e, mButton)) {
				if(mButton.isMousePressed()) {
					mButton.applyGamestate();  
				}
				break;
			}
		}
		resetButtons();
	}

	private void resetButtons() {
		for(MenuButton mButton : buttons) {
			mButton.resetBools();
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for(MenuButton mButton : buttons) {
//			System.out.println(1);
			mButton.setMouseOver(false);
		}
		
		for(MenuButton mButton : buttons) {
			if(isIn(e, mButton)) {
				mButton.setMouseOver(true);
				break;
			}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			GameState.state =GameState.PLAYING;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	
}
