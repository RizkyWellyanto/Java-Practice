import javax.swing.*;
import java.awt.*;

public class ab extends JFrame {
	
	public Rectangle[] room = {new Rectangle(10, 10, 120, 80), new Rectangle(200, 200, 100, 100)};
	
	public JPanel gp = (JPanel) getGlassPane();
	fl;
	public ad tl;
		
	public ac 
	public ab() {
		this.setLayout(new GridLayout(1, 1, 0, 0));
		gp.setLayout(new GridLayout(1, 1, 0, 0));
		gp.setVisible(true);
		
		fl = new ac();
		tl = new ad();
		tl.setOpaque(false);
		
		gp.add(tl);
		this.add(fl);
		
	}
}
