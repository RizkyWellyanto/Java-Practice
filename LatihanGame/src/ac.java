import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ac extends JPanel {
	
	public int lc=0;
	
	public Rectangle[] room;
	
	public ac() {
		getRoom();
		this.setBackground(Color.BLACK);	
		}
	
	public void getRoom() {
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		for(int i=0;i<room.length; i++) {
			g.fill3DRect (room[i].x, room[i].y, room[i].width, room[i].height, false);
		}
	}	
}