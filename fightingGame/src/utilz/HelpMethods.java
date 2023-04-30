package utilz;

import java.awt.geom.Rectangle2D;

import main.Game;

public class HelpMethods {
	public static boolean CanMoveHere(float x,float width,float height) {
		if(!IsSolid(x))
			if(!IsSolid(x+width))
				if(!IsSolid(x+width))
					if(!IsSolid(x))
						return true;
		return false;
	}
	
	private static boolean IsSolid(float x) {
		if(x<=0 || x>=Game.GAME_WIDTH+100) {
			return true;
		}
		return false;
	}
}
