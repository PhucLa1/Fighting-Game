package gameState;

import java.awt.event.MouseEvent;

import UI.MenuButton;
import main.Game;

public class State {
	protected Game game;
	
	public State(Game game) {
		this.game=game;
	}

	public Game getGame() {
		return game;
	}
	public boolean isIn(MouseEvent e,MenuButton mButton) {
		return mButton.getBounds().contains(e.getX(),e.getY());
	}
}
