package be.kimit.ledcube.programmer;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import be.kimit.ledcube.programmer.ui.LedLayer;

public class LedCubeProgrammer extends JFrame implements ActionListener{

	private static final String NEXT = "NEXT";
	private static final String PREVIOUS = "PREVIOUS";
	/**
	 * 
	 */
	private static final long serialVersionUID = -5248018121813704878L;
	private JTextField stepCounter;
	private JButton previousButton;
	private JButton nextButton;

	public static void main(String[] args) {
		new LedCubeProgrammer().setVisible(true);

	}
	
	private LedCubeProgrammer() {
		this.setTitle("LED Cube Programmer");
		this.setPreferredSize(new Dimension(1000,500));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FlowLayout layout = new FlowLayout();
		JPanel panel = new JPanel(layout);
		JPanel layerPanel = new JPanel();
		layerPanel.add(new LedLayer(1));
		layerPanel.add(new LedLayer(2));
		layerPanel.add(new LedLayer(3));
		layerPanel.add(new LedLayer(4));
		panel.add(layerPanel);
		JPanel stepPanel = new JPanel();
		previousButton = new JButton("Previous");
		previousButton.setActionCommand(PREVIOUS);
		previousButton.addActionListener(this);
		previousButton.setEnabled(false);
		stepPanel.add(previousButton);
		stepCounter = new JTextField("1");
		stepCounter.setColumns(3);
		stepPanel.add(stepCounter);
		stepCounter.setEditable(false);
		nextButton = new JButton("Next");
		nextButton.addActionListener(this);
		nextButton.setActionCommand(NEXT);
		stepPanel.add(nextButton);
		
		stepPanel.add(new JLabel("time (ms) :"));
		JTextField time = new JTextField("1");
		time.setColumns(3);		
		stepPanel.add(time);	
		
		panel.add(stepPanel);
		this.setContentPane(panel);
		this.pack();
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == NEXT) {
			stepCounter.setText(String.valueOf( Integer.parseInt(stepCounter.getText()) + 1));
		}
		if (e.getActionCommand() == PREVIOUS) {
			stepCounter.setText(String.valueOf( Integer.parseInt(stepCounter.getText()) - 1));
		}	
		if (Integer.parseInt(stepCounter.getText()) > 1) {
			previousButton.setEnabled(true);
		} else {
			previousButton.setEnabled(false);
		}
	}

}
