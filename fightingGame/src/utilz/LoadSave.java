package utilz;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class LoadSave {
//	public static final int PLAYER_ATLAS = "/Itachi";
	
	public static BufferedImage GetSpriteAtlas(int playerAction,int actionIndex) {
		BufferedImage image=null;
		InputStream iStream = LoadSave.class.getResourceAsStream("/Itachi/"+playerAction+"/"+actionIndex+".png");
		try {
			image = ImageIO.read(iStream);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				iStream.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return image;
	}
	
	public static BufferedImage GetSpriteAtlas(String fileName,int indexImage) {
		BufferedImage image=null;
		InputStream iStream = LoadSave.class.getResourceAsStream("/"+fileName+"/"+indexImage+".png");
		try {
			image = ImageIO.read(iStream);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				iStream.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return image;
	}
	
	public static BufferedImage GetSpriteAtlas(String fileName, int playerAction,int actionIndex) {
		BufferedImage image=null;
		InputStream iStream = LoadSave.class.getResourceAsStream("/"+fileName+"/"+playerAction+"/"+actionIndex+".png");
		try {
			image = ImageIO.read(iStream);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				iStream.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return image;
	}
	
	public static String readFile(String fileName) {
		StringBuilder stringBuilder = new StringBuilder();
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader("fileSaveData/"+fileName+".txt"));
	        String line = reader.readLine();
	        while (line != null) {
	            stringBuilder.append(line);
	            line = reader.readLine();
	            if (line != null) {
	                stringBuilder.append(" ");
	            }
	        }
	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return stringBuilder.toString();
	}
	
	public static void writeInFile(String fileName,String data) {

	        try {
	            FileWriter fileWriter = new FileWriter("fileSaveData/"+fileName+".txt");
	            fileWriter.write(data);
	            fileWriter.close();
	            System.out.println("Data written to file successfully.");
	        } catch (IOException e) {
	            System.out.println("An error occurred while writing to file.");
	            e.printStackTrace();
	        }
	}
}
