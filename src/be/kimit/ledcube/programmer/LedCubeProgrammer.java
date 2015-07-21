package be.kimit.ledcube.programmer;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
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

	private static final String SAVE = "SAVE";
	private static final String LOAD = "LOAD";
	private static final String NEXT = "NEXT";
	private static final String PREVIOUS = "PREVIOUS";
	/**
	 * 
	 */
	private static final long serialVersionUID = -5248018121813704878L;
	private JTextField stepCounter;
	private JTextField totalStepsCounter;
	private JButton previousButton;
	private JButton nextButton;
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
		stepPanel.add(previousButton);
		stepCounter = new JTextField("0");
		stepCounter.setColumns(3);
		stepPanel.add(stepCounter);
		stepCounter.setEditable(false);
		nextButton = new JButton("Next");
		nextButton.addActionListener(this);
		nextButton.setActionCommand(NEXT);
		stepPanel.add(nextButton);
		
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
		
		JMenuItem loadMenuItem = new JMenuItem("Load");
		loadMenuItem.setActionCommand(LOAD);
		loadMenuItem.addActionListener(this);
		fileMenu.add(loadMenuItem);
		
		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.setActionCommand(SAVE);
		saveMenuItem.addActionListener(this);
		fileMenu.add(saveMenuItem);
		
		this.setJMenuBar(menuBar);
		this.setContentPane(panel);
		this.pack();
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == NEXT) {
			int index = Integer.parseInt(stepCounter.getText());
			stepCounter.setText(String.valueOf( index + 1));
			ScenarioStep scenarioStep = scenario.getScenarioStepAt(index);

			if (scenarioStep == null){
				scenarioStep = new ScenarioStep();
				scenario.getScenarioSteps().add(scenarioStep);
			}
			saveUIToScenarioStep(scenarioStep);
			setPreviousState(index + 1);
			scenarioStep = scenario.getScenarioStepAt(index + 1);
			if (scenarioStep != null) {
				setUIToScenarioStep(scenarioStep);
			}
			
		}
		if (e.getActionCommand() == PREVIOUS) {
			int index = Integer.parseInt(stepCounter.getText());
			stepCounter.setText(String.valueOf( index - 1));
			ScenarioStep scenarioStep = scenario.getScenarioStepAt(index - 1);
			setUIToScenarioStep(scenarioStep);
			setPreviousState(index - 1);
		}	
		if (e.getActionCommand() == SAVE) {
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				OutputFormat format =  new OutputFormat();
				format.setEncoding("UTF-8");
				format.setIndenting(true);

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
		
		setTotalStepsCounter();

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
		uiLedLayer0.getLed0().setState(layer0.isLed0());
		uiLedLayer0.getLed1().setState(layer0.isLed1());
		uiLedLayer0.getLed2().setState(layer0.isLed2());
		uiLedLayer0.getLed3().setState(layer0.isLed3());
		uiLedLayer0.getLed4().setState(layer0.isLed4());
		uiLedLayer0.getLed5().setState(layer0.isLed5());
		uiLedLayer0.getLed6().setState(layer0.isLed6());
		uiLedLayer0.getLed7().setState(layer0.isLed7());
		uiLedLayer0.getLed8().setState(layer0.isLed8());
		uiLedLayer0.getLed9().setState(layer0.isLed9());
		uiLedLayer0.getLed10().setState(layer0.isLed10());
		uiLedLayer0.getLed11().setState(layer0.isLed11());
		uiLedLayer0.getLed12().setState(layer0.isLed12());
		uiLedLayer0.getLed13().setState(layer0.isLed13());
		uiLedLayer0.getLed14().setState(layer0.isLed14());
		uiLedLayer0.getLed15().setState(layer0.isLed15());
		
		LedLayer layer1 = scenarioStep.getLayer1();
		uiLedLayer1.getLed0().setState(layer1.isLed0());
		uiLedLayer1.getLed1().setState(layer1.isLed1());
		uiLedLayer1.getLed2().setState(layer1.isLed2());
		uiLedLayer1.getLed3().setState(layer1.isLed3());
		uiLedLayer1.getLed4().setState(layer1.isLed4());
		uiLedLayer1.getLed5().setState(layer1.isLed5());
		uiLedLayer1.getLed6().setState(layer1.isLed6());
		uiLedLayer1.getLed7().setState(layer1.isLed7());
		uiLedLayer1.getLed8().setState(layer1.isLed8());
		uiLedLayer1.getLed9().setState(layer1.isLed9());
		uiLedLayer1.getLed10().setState(layer1.isLed10());
		uiLedLayer1.getLed11().setState(layer1.isLed11());
		uiLedLayer1.getLed12().setState(layer1.isLed12());
		uiLedLayer1.getLed13().setState(layer1.isLed13());
		uiLedLayer1.getLed14().setState(layer1.isLed14());
		uiLedLayer1.getLed15().setState(layer1.isLed15());		
		
		LedLayer layer2 = scenarioStep.getLayer2();
		uiLedLayer2.getLed0().setState(layer2.isLed0());
		uiLedLayer2.getLed1().setState(layer2.isLed1());
		uiLedLayer2.getLed2().setState(layer2.isLed2());
		uiLedLayer2.getLed3().setState(layer2.isLed3());
		uiLedLayer2.getLed4().setState(layer2.isLed4());
		uiLedLayer2.getLed5().setState(layer2.isLed5());
		uiLedLayer2.getLed6().setState(layer2.isLed6());
		uiLedLayer2.getLed7().setState(layer2.isLed7());
		uiLedLayer2.getLed8().setState(layer2.isLed8());
		uiLedLayer2.getLed9().setState(layer2.isLed9());
		uiLedLayer2.getLed10().setState(layer2.isLed10());
		uiLedLayer2.getLed11().setState(layer2.isLed11());
		uiLedLayer2.getLed12().setState(layer2.isLed12());
		uiLedLayer2.getLed13().setState(layer2.isLed13());
		uiLedLayer2.getLed14().setState(layer2.isLed14());
		uiLedLayer2.getLed15().setState(layer2.isLed15());		
	
		LedLayer layer3 = scenarioStep.getLayer3();
		uiLedLayer3.getLed0().setState(layer3.isLed0());
		uiLedLayer3.getLed1().setState(layer3.isLed1());
		uiLedLayer3.getLed2().setState(layer3.isLed2());
		uiLedLayer3.getLed3().setState(layer3.isLed3());
		uiLedLayer3.getLed4().setState(layer3.isLed4());
		uiLedLayer3.getLed5().setState(layer3.isLed5());
		uiLedLayer3.getLed6().setState(layer3.isLed6());
		uiLedLayer3.getLed7().setState(layer3.isLed7());
		uiLedLayer3.getLed8().setState(layer3.isLed8());
		uiLedLayer3.getLed9().setState(layer3.isLed9());
		uiLedLayer3.getLed10().setState(layer3.isLed10());
		uiLedLayer3.getLed11().setState(layer3.isLed11());
		uiLedLayer3.getLed12().setState(layer3.isLed12());
		uiLedLayer3.getLed13().setState(layer3.isLed13());
		uiLedLayer3.getLed14().setState(layer3.isLed14());
		uiLedLayer3.getLed15().setState(layer3.isLed15());		
		
		JFrame frame = this;
		time.setText(String.valueOf(scenarioStep.getTime()));
	      SwingUtilities.invokeLater(new Runnable(){
	          @Override public void run() {
	        	 frame.invalidate();
	          }
	      });
	      
	}

	private void saveUIToScenarioStep(ScenarioStep scenarioStep) {
		LedLayer layer0 = scenarioStep.getLayer0();
		layer0.setLed0(uiLedLayer0.getLed0().getState());
		layer0.setLed1(uiLedLayer0.getLed1().getState());
		layer0.setLed2(uiLedLayer0.getLed2().getState());		
		layer0.setLed3(uiLedLayer0.getLed3().getState());		
		layer0.setLed4(uiLedLayer0.getLed4().getState());		
		layer0.setLed5(uiLedLayer0.getLed5().getState());		
		layer0.setLed6(uiLedLayer0.getLed6().getState());		
		layer0.setLed7(uiLedLayer0.getLed7().getState());		
		layer0.setLed8(uiLedLayer0.getLed8().getState());		
		layer0.setLed9(uiLedLayer0.getLed9().getState());		
		layer0.setLed10(uiLedLayer0.getLed10().getState());		
		layer0.setLed11(uiLedLayer0.getLed11().getState());		
		layer0.setLed12(uiLedLayer0.getLed12().getState());		
		layer0.setLed13(uiLedLayer0.getLed13().getState());		
		layer0.setLed14(uiLedLayer0.getLed14().getState());		
		layer0.setLed15(uiLedLayer0.getLed15().getState());		
		
		LedLayer layer1 = scenarioStep.getLayer1();
		layer1.setLed0(uiLedLayer1.getLed0().getState());
		layer1.setLed1(uiLedLayer1.getLed1().getState());
		layer1.setLed2(uiLedLayer1.getLed2().getState());		
		layer1.setLed3(uiLedLayer1.getLed3().getState());		
		layer1.setLed4(uiLedLayer1.getLed4().getState());		
		layer1.setLed5(uiLedLayer1.getLed5().getState());		
		layer1.setLed6(uiLedLayer1.getLed6().getState());		
		layer1.setLed7(uiLedLayer1.getLed7().getState());		
		layer1.setLed8(uiLedLayer1.getLed8().getState());		
		layer1.setLed9(uiLedLayer1.getLed9().getState());		
		layer1.setLed10(uiLedLayer1.getLed10().getState());		
		layer1.setLed11(uiLedLayer1.getLed11().getState());		
		layer1.setLed12(uiLedLayer1.getLed12().getState());		
		layer1.setLed13(uiLedLayer1.getLed13().getState());		
		layer1.setLed14(uiLedLayer1.getLed14().getState());		
		layer1.setLed15(uiLedLayer1.getLed15().getState());	
		
		LedLayer layer2 = scenarioStep.getLayer2();
		layer2.setLed0(uiLedLayer2.getLed0().getState());
		layer2.setLed1(uiLedLayer2.getLed1().getState());
		layer2.setLed2(uiLedLayer2.getLed2().getState());		
		layer2.setLed3(uiLedLayer2.getLed3().getState());		
		layer2.setLed4(uiLedLayer2.getLed4().getState());		
		layer2.setLed5(uiLedLayer2.getLed5().getState());		
		layer2.setLed6(uiLedLayer2.getLed6().getState());		
		layer2.setLed7(uiLedLayer2.getLed7().getState());		
		layer2.setLed8(uiLedLayer2.getLed8().getState());		
		layer2.setLed9(uiLedLayer2.getLed9().getState());		
		layer2.setLed10(uiLedLayer2.getLed10().getState());		
		layer2.setLed11(uiLedLayer2.getLed11().getState());		
		layer2.setLed12(uiLedLayer2.getLed12().getState());		
		layer2.setLed13(uiLedLayer2.getLed13().getState());		
		layer2.setLed14(uiLedLayer2.getLed14().getState());		
		layer2.setLed15(uiLedLayer2.getLed15().getState());	

		LedLayer layer3 = scenarioStep.getLayer3();
		layer3.setLed0(uiLedLayer3.getLed0().getState());
		layer3.setLed1(uiLedLayer3.getLed1().getState());
		layer3.setLed2(uiLedLayer3.getLed2().getState());		
		layer3.setLed3(uiLedLayer3.getLed3().getState());		
		layer3.setLed4(uiLedLayer3.getLed4().getState());		
		layer3.setLed5(uiLedLayer3.getLed5().getState());		
		layer3.setLed6(uiLedLayer3.getLed6().getState());		
		layer3.setLed7(uiLedLayer3.getLed7().getState());		
		layer3.setLed8(uiLedLayer3.getLed8().getState());		
		layer3.setLed9(uiLedLayer3.getLed9().getState());		
		layer3.setLed10(uiLedLayer3.getLed10().getState());		
		layer3.setLed11(uiLedLayer3.getLed11().getState());		
		layer3.setLed12(uiLedLayer3.getLed12().getState());		
		layer3.setLed13(uiLedLayer3.getLed13().getState());		
		layer3.setLed14(uiLedLayer3.getLed14().getState());		
		layer3.setLed15(uiLedLayer3.getLed15().getState());	
		
		scenarioStep.setTime(Integer.parseInt(time.getText()));
	}

}
