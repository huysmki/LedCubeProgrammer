package be.kimit.ledcube.programmer.ui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class UILed extends JToggleButton {
	
	 Icon iconOff = new ImageIcon(this.getClass().getResource("/off.png"));  
     Icon iconOn = new ImageIcon(this.getClass().getResource("/on.png"));

	public UILed() {
		this.setPreferredSize(new Dimension(50,50));
        this.setIcon(iconOff);  
        this.setSelectedIcon(iconOn);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false); 
        this.setBorderPainted(false);
    }
	
	public boolean getState() {
		return this.isSelected();
	}
	
	public void setState(boolean state) {
		this.setSelected(state);
	}
	
}
