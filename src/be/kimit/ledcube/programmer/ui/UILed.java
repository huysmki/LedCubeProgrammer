package be.kimit.ledcube.programmer.ui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JToggleButton;

public class UILed extends JToggleButton {
	
	private Graphics graphics;

	public UILed() {
		this.setPreferredSize(new Dimension(50,50));
//		this.setUI(new MetalToggleButtonUI() {
//		    @Override
//		    protected Color getSelectColor() {
//		        return Color.RED;
//		    }
//		});
		//super.repaint();
	}
	
	public boolean getState() {
		return this.isSelected();
	}
	
	public void setState(boolean state) {
		this.setSelected(state);
	}
	
}
