package be.kimit.ledcube.player;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import be.kimit.ledcube.data.Scenario;
import be.kimit.ledcube.data.ScenarioStep;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.GpioUtil;

public class LedCubePlayer implements Runnable{
	
	private int[] layer0 = new int[16];
	private int[] layer1 = new int[16];
	private int[] layer2 = new int[16];
	private int[] layer3 = new int[16];
	private static int SLEEP_TIME = 4;
	

	public LedCubePlayer(String file) throws FileNotFoundException, InterruptedException {
		XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(file));
		Scenario scenario = (Scenario) xmlDecoder.readObject();
		xmlDecoder.close();
		play(scenario);
	}

	private void play(Scenario scenario) throws InterruptedException {
	   if (Gpio.wiringPiSetup() == -1) {
	        System.out.println(" ==>> GPIO SETUP FAILED");
	        return;
	    }

	    initializePin(0);
	    initializePin(1);
	    initializePin(2);
	    initializePin(3);
	    initializePin(4);
	    initializePin(5);
	    initializePin(6);
	    initializePin(7);
	    initializePin(8);
	    initializePin(9);
	    initializePin(10);
	    initializePin(11);
	    initializePin(12);
	    initializePin(13);
	    initializePin(14);
	    initializePin(15);

	    initializePin(26);
	    initializePin(27);
	    initializePin(28);
	    initializePin(29);
	    
	    Thread runnerThread = new Thread(this);
	    runnerThread.start();
	    scenario.getScenarioSteps();
	    int index = 0;
	    while(true) {
	    	ScenarioStep step = scenario.getScenarioStepAt(index++);
	    	
	    	layer0 = step.getLayer0().toIntArray();
	    	layer1 = step.getLayer1().toIntArray();
	    	layer2 = step.getLayer2().toIntArray();
	    	layer3 = step.getLayer3().toIntArray();
	    	
	    	Thread.sleep(step.getTime());
	    	if (index == scenario.getScenarioSteps().size()) {
	    		index = 0;
	    	}
	    }
		
	}
	
	private void initializePin(int pin) {
		GpioUtil.export(pin, GpioUtil.DIRECTION_OUT);            
        Gpio.pinMode(pin, Gpio.OUTPUT);
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Usage : java -jar LedCubePlayer.jar <scenario file>");
			System.exit(1);
		}
		try {
			new LedCubePlayer(args[0]);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		while (true) {
	        Gpio.digitalWrite(29, 0);
	        for (int x=0; x < 16 ; x++ ) {
	        	Gpio.digitalWrite(x, layer0[x]);
	        }
	        Gpio.digitalWrite(26, 1);
			Gpio.delay(SLEEP_TIME);

	        Gpio.digitalWrite(26, 0);
	        for (int x=0; x < 16 ; x++ ) {
	        	Gpio.digitalWrite(x, layer1[x]);
	        }    
	        Gpio.digitalWrite(27, 1);	
	        Gpio.delay(SLEEP_TIME);
	        
	        Gpio.digitalWrite(27, 0);
	        for (int x=0; x < 15 ; x++ ) {
	        	Gpio.digitalWrite(x, layer2[x]);
	        }   
	        Gpio.digitalWrite(28, 1);	
	        Gpio.delay(SLEEP_TIME);

	        Gpio.digitalWrite(28, 0);
	        for (int x=0; x < 16 ; x++ ) {
	        	Gpio.digitalWrite(x, layer3[x]);
	        }      
	        Gpio.digitalWrite(29, 1);	
	        Gpio.delay(SLEEP_TIME);
		}

		
	}

}
