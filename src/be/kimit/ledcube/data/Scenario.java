package be.kimit.ledcube.data;

import java.util.ArrayList;
import java.util.List;

public class Scenario {
	
	private List<ScenarioStep> scenarioSteps = new ArrayList<ScenarioStep>();

	public List<ScenarioStep> getScenarioSteps() {
		return scenarioSteps;
	}

	public void setScenarioSteps(List<ScenarioStep> scenarioSteps) {
		this.scenarioSteps = scenarioSteps;
	}
	
	public ScenarioStep getScenarioStepAt(int index) {
		try {
			return scenarioSteps.get(index);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

}
