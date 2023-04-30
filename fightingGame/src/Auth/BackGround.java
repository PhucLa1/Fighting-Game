package Auth;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import utilz.LoadSave;

public class BackGround extends JPanel{
	BufferedImage imgBufferedImage;
	BackGround(){
		String fileBgPNG = LoadSave.readFile("backGround");
		String fileBgJPG = LoadSave.readFile("backGround");
		if(!fileBgPNG.contains(".png") && !fileBgJPG.contains(".jpg")) {
			fileBgPNG+=".png";
			fileBgJPG+=".jpg";
			
		}
		FileInputStream fin =null;
		 try {
			fin = new FileInputStream("backGround/"+fileBgPNG);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			try {
				fin = new FileInputStream("backGround/"+fileBgJPG);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		 try {
			imgBufferedImage =  ImageIO.read(fin);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setPanelSize();
	}
	private void setPanelSize() {
		Dimension size = new Dimension(GAME_WIDTH,GAME_HEIGHT);
//		setMinimumSize(size);
		setPreferredSize(size);
//		setMaximumSize(size);
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imgBufferedImage, 0,0,GAME_WIDTH,GAME_HEIGHT, null);
		g.setColor(new Color(255, 255, 255, 50));
		g.fillRect(400, 100, 450, 350);
	}
	
}
