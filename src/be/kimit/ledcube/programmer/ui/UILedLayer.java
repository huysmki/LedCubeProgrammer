package be.kimit.ledcube.programmer.ui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class UILedLayer extends JPanel {
	
	public UILed getLed0() {
		return led0;
	}

	public UILed getLed1() {
		return led1;
	}

	public UILed getLed2() {
		return led2;
	}

	public UILed getLed3() {
		return led3;
	}

	public UILed getLed4() {
		return led4;
	}

	public UILed getLed5() {
		return led5;
	}

	public UILed getLed6() {
		return led6;
	}

	public UILed getLed7() {
		return led7;
	}

	public UILed getLed8() {
		return led8;
	}

	public UILed getLed9() {
		return led9;
	}

	public UILed getLed10() {
		return led10;
	}

	public UILed getLed11() {
		return led11;
	}

	public UILed getLed12() {
		return led12;
	}

	public UILed getLed13() {
		return led13;
	}

	public UILed getLed14() {
		return led14;
	}

	public UILed getLed15() {
		return led15;
	}

	private UILed led0 = new UILed();
	private UILed led1 = new UILed();
	private UILed led2 = new UILed();
	private UILed led3 = new UILed();
	private UILed led4 = new UILed();
	private UILed led5 = new UILed();
	private UILed led6 = new UILed();
	private UILed led7 = new UILed();
	private UILed led8 = new UILed();
	private UILed led9 = new UILed();
	private UILed led10 = new UILed();
	private UILed led11 = new UILed();
	private UILed led12 = new UILed();
	private UILed led13 = new UILed();
	private UILed led14 = new UILed();
	private UILed led15 = new UILed();

	public UILedLayer(int layer) {
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
