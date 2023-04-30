package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import UI.PaussedOverlay;
import utilz.LoadSave;

public class GameWindow{
	private JFrame jframe;
	
	public GameWindow(GamePanel gamePanel)  {
		jframe =new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(gamePanel);
		jframe.setResizable(false);
		jframe.pack();
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);
		
        jframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                	if(PaussedOverlay.xRead>765) {
                		PaussedOverlay.xRead = 765;
                	}
                	else if(PaussedOverlay.xRead<484) {
                		PaussedOverlay.xRead=484;
                	}
                	String buttonX = Integer.toString(PaussedOverlay.xRead);
                	
                	System.out.println(buttonX);
                	LoadSave.writeInFile("volume",buttonX );
                	
                	jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                	jframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
		
		
		//Nay la dung de khi ma khi minh an ung dung di ma bam nut thi no khong nhan nua
		jframe.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				gamePanel.getGame().windowFocusLost();
				
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}


}
