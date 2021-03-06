package be.kimit.ledcube.programmer;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import be.kimit.ledcube.data.LedLayer;
import be.kimit.ledcube.data.Scenario;
import be.kimit.ledcube.data.ScenarioStep;
import be.kimit.ledcube.programmer.ui.UILedLayer;

public class LedCubeProgrammer extends JFrame implements ActionListener{

	private static final String NEW_MENU = "NEW_MENU";
	private static final String SAVE_MENU = "SAVE_MENU";
	private static final String LOAD_MENU = "LOAD_MENU";
	private static final String NEXT = "NEXT";
	private static final String PREVIOUS = "PREVIOUS";
	private static final String SAVE = "SAVE";
	/**
	 * 
	 */
	private static final long serialVersionUID = -5248018121813704878L;
	private JTextField stepCounter;
	private JTextField totalStepsCounter;
	private JButton previousButton;
	private JButton nextButton;
	private JButton saveButton;
	private Scenario scenario = new Scenario();
	private UILedLayer uiLedLayer0;
	private UILedLayer uiLedLayer1;
	private UILedLayer uiLedLayer2;
	private UILedLayer uiLedLayer3;
	private JTextField time;

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
		uiLedLayer0 = new UILedLayer(1);
		uiLedLayer1 = new UILedLayer(2);
		uiLedLayer2 = new UILedLayer(3);
		uiLedLayer3 = new UILedLayer(4);
		layerPanel.add(uiLedLayer0);
		layerPanel.add(uiLedLayer1);
		layerPanel.add(uiLedLayer2);
		layerPanel.add(uiLedLayer3);
		panel.add(layerPanel);
		JPanel stepPanel = new JPanel();
		previousButton = new JButton("Previous");
		previousButton.setActionCommand(PREVIOUS);
		previousButton.addActionListener(this);
		previousButton.setEnabled(false);
		previousButton.setFocusable(false);
		stepPanel.add(previousButton);
		stepCounter = new JTextField("0");
		stepCounter.setColumns(3);
		stepPanel.add(stepCounter);
		stepCounter.setEditable(false);
		nextButton = new JButton("Next");
		nextButton.addActionListener(this);
		nextButton.setEnabled(false);
		nextButton.setActionCommand(NEXT);
		nextButton.setFocusable(false);
		stepPanel.add(nextButton);
		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		saveButton.setActionCommand(SAVE);
		saveButton.setFocusable(false);
		stepPanel.add(saveButton);
		
		stepPanel.add(new JLabel("time (ms) :"));
		time = new JTextField("1");
		time.setColumns(3);		
		stepPanel.add(time);
		
		stepPanel.add(new JLabel("Total steps :"));
		totalStepsCounter = new JTextField("0");
		totalStepsCounter.setColumns(3);
		stepPanel.add(totalStepsCounter);
		
		panel.add(stepPanel);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenuItem newMenuItem = new JMenuItem("New");
		newMenuItem.setActionCommand(NEW_MENU);
		newMenuItem.addActionListener(this);
		fileMenu.add(newMenuItem);
		
		JMenuItem loadMenuItem = new JMenuItem("Load");
		loadMenuItem.setActionCommand(LOAD_MENU);
		loadMenuItem.addActionListener(this);
		fileMenu.add(loadMenuItem);
		
		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.setActionCommand(SAVE_MENU);
		saveMenuItem.addActionListener(this);
		fileMenu.add(saveMenuItem);
		
		this.setJMenuBar(menuBar);
		this.setContentPane(panel);
		this.pack();
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == SAVE) {
			int index = Integer.parseInt(stepCounter.getText());
			ScenarioStep scenarioStep = scenario.getScenarioStepAt(index);

			if (scenarioStep == null){
				scenarioStep = new ScenarioStep();
				scenario.getScenarioSteps().add(scenarioStep);
				setTotalStepsCounter();
			}
			saveUIToScenarioStep(scenarioStep);
			setPreviousState(index);
			int totalSteps = Integer.parseInt(totalStepsCounter.getText());
			setNextState(index, totalSteps);
			
		}
		if (e.getActionCommand() == NEXT) {
			int index = Integer.parseInt(stepCounter.getText());
			stepCounter.setText(String.valueOf( index + 1));

			ScenarioStep scenarioStep = scenario.getScenarioStepAt(index + 1);
			if (scenarioStep != null) {
				setUIToScenarioStep(scenarioStep);
			}
			setPreviousState(index + 1);
			int totalSteps = Integer.parseInt(totalStepsCounter.getText());
			setNextState(index + 1, totalSteps);			
		}
		if (e.getActionCommand() == PREVIOUS) {
			int index = Integer.parseInt(stepCounter.getText());
			stepCounter.setText(String.valueOf( index - 1));
			ScenarioStep scenarioStep = scenario.getScenarioStepAt(index - 1);
			setUIToScenarioStep(scenarioStep);
			setPreviousState(index - 1);
			int totalSteps = Integer.parseInt(totalStepsCounter.getText());
			setNextState(index - 1, totalSteps);		}	
		if (e.getActionCommand() == SAVE_MENU) {
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();

				try {
					XMLEncoder xmlSerializer = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));
					xmlSerializer.writeObject(scenario);
					xmlSerializer.flush();
					xmlSerializer.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if (e.getActionCommand() == LOAD_MENU) {
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();

				try {
					XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(file));
					scenario = (Scenario) xmlDecoder.readObject();
					xmlDecoder.close();
					setTotalStepsCounter();
					stepCounter.setText(new String("0"));
					setPreviousState(0);
					int totalSteps = Integer.parseInt(totalStepsCounter.getText());
					setNextState(0, totalSteps);	
					
					ScenarioStep scenarioStep = scenario.getScenarioStepAt(0);
					setUIToScenarioStep(scenarioStep);
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if (e.getActionCommand() == NEW_MENU) {

			scenario = new Scenario();
			setTotalStepsCounter();
			stepCounter.setText(new String("0"));
			setPreviousState(0);
			setNextState(0, 0);	
			ScenarioStep step = new ScenarioStep();
			step.setTime(1);
			setUIToScenarioStep(step);

		}
	}

	private void setNextState(int index, int totalSteps) {
		if (index < totalSteps) {
			nextButton.setEnabled(true);
		} else {
			nextButton.setEnabled(false);
		}
		
	}

	private void setTotalStepsCounter() {
	    totalStepsCounter.setText(String.valueOf(scenario.getScenarioSteps().size()));
	}

	private void setPreviousState(int index) {
		if (index > 0) {
			previousButton.setEnabled(true);
		} else {
			previousButton.setEnabled(false);
		}
	}

	private void setUIToScenarioStep(ScenarioStep scenarioStep) {
		
		LedLayer layer0 = scenarioStep.getLayer0();
		retrieveLedStates(layer0, uiLedLayer0);
		
		LedLayer layer1 = scenarioStep.getLayer1();
		retrieveLedStates(layer1, uiLedLayer1);
		
		LedLayer layer2 = scenarioStep.getLayer2();
		retrieveLedStates(layer2, uiLedLayer2);
	
	
		LedLayer layer3 = scenarioStep.getLayer3();
		retrieveLedStates(layer3, uiLedLayer3);
		
		time.setText(String.valueOf(scenarioStep.getTime()));
	    SwingUtilities.invokeLater(new Runnable(){
	        @Override public void run() {
	        	uiLedLayer0.invalidate();
	        	uiLedLayer1.invalidate();
	        	uiLedLayer2.invalidate();
	        	uiLedLayer3.invalidate();
	        }
	    });
	      
	}

	private void retrieveLedStates(LedLayer layer, UILedLayer uiLedLayer) {
		uiLedLayer.getLed0().setState(layer.isLed0());
		uiLedLayer.getLed1().setState(layer.isLed1());
		uiLedLayer.getLed2().setState(layer.isLed2());
		uiLedLayer.getLed3().setState(layer.isLed3());
		uiLedLayer.getLed4().setState(layer.isLed4());
		uiLedLayer.getLed5().setState(layer.isLed5());
		uiLedLayer.getLed6().setState(layer.isLed6());
		uiLedLayer.getLed7().setState(layer.isLed7());
		uiLedLayer.getLed8().setState(layer.isLed8());
		uiLedLayer.getLed9().setState(layer.isLed9());
		uiLedLayer.getLed10().setState(layer.isLed10());
		uiLedLayer.getLed11().setState(layer.isLed11());
		uiLedLayer.getLed12().setState(layer.isLed12());
		uiLedLayer.getLed13().setState(layer.isLed13());
		uiLedLayer.getLed14().setState(layer.isLed14());
		uiLedLayer.getLed15().setState(layer.isLed15());
	}

	private void saveUIToScenarioStep(ScenarioStep scenarioStep) {
		LedLayer layer0 = scenarioStep.getLayer0();
		saveLedStates(layer0, uiLedLayer0);		
		
		LedLayer layer1 = scenarioStep.getLayer1();
		saveLedStates(layer1, uiLedLayer1);		
		
		LedLayer layer2 = scenarioStep.getLayer2();
		saveLedStates(layer2, uiLedLayer2);		

		LedLayer layer3 = scenarioStep.getLayer3();
		saveLedStates(layer3, uiLedLayer3);		
		
		scenarioStep.setTime(Integer.parseInt(time.getText()));
	}

	private void saveLedStates(LedLayer layer, UILedLayer uiLedLayer) {
		layer.setLed0(uiLedLayer.getLed0().getState());
		layer.setLed1(uiLedLayer.getLed1().getState());
		layer.setLed2(uiLedLayer.getLed2().getState());		
		layer.setLed3(uiLedLayer.getLed3().getState());		
		layer.setLed4(uiLedLayer.getLed4().getState());		
		layer.setLed5(uiLedLayer.getLed5().getState());		
		layer.setLed6(uiLedLayer.getLed6().getState());		
		layer.setLed7(uiLedLayer.getLed7().getState());		
		layer.setLed8(uiLedLayer.getLed8().getState());		
		layer.setLed9(uiLedLayer.getLed9().getState());		
		layer.setLed10(uiLedLayer.getLed10().getState());		
		layer.setLed11(uiLedLayer.getLed11().getState());		
		layer.setLed12(uiLedLayer.getLed12().getState());		
		layer.setLed13(uiLedLayer.getLed13().getState());		
		layer.setLed14(uiLedLayer.getLed14().getState());		
		layer.setLed15(uiLedLayer.getLed15().getState());
	}

}
