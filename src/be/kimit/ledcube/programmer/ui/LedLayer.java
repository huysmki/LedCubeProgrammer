package be.kimit.ledcube.programmer.ui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LedLayer extends JPanel {
	
	private Led led0 = new Led();
	private Led led1 = new Led();
	private Led led2 = new Led();
	private Led led3 = new Led();
	private Led led4 = new Led();
	private Led led5 = new Led();
	private Led led6 = new Led();
	private Led led7 = new Led();
	private Led led8 = new Led();
	private Led led9 = new Led();
	private Led led10 = new Led();
	private Led led11 = new Led();
	private Led led12 = new Led();
	private Led led13 = new Led();
	private Led led14 = new Led();
	private Led led15 = new Led();

	public LedLayer(int layer) {
	//	this.setBackground(Color.RED);
		this.setLayout(new GridLayout(5,4));
		this.add(new JLabel("layer " + layer));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(led0);
		this.add(led1);		
		this.add(led2);		
		this.add(led3);		
		this.add(led4);		
		this.add(led5);		
		this.add(led6);		
		this.add(led7);		
		this.add(led8);		
		this.add(led9);		
		this.add(led10);		
		this.add(led11);		
		this.add(led12);		
		this.add(led13);		
		this.add(led14);		
		this.add(led15);		
		
	}
}
