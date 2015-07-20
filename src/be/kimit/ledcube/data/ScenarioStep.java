package be.kimit.ledcube.data;

public class ScenarioStep {
	private LedLayer layer0 = new LedLayer();
	private LedLayer layer1 = new LedLayer();
	private LedLayer layer2 = new LedLayer();
	private LedLayer layer3 = new LedLayer();
	private int time;
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public LedLayer getLayer0() {
		return layer0;
	}
	public void setLayer0(LedLayer layer0) {
		this.layer0 = layer0;
	}
	public LedLayer getLayer1() {
		return layer1;
	}
	public void setLayer1(LedLayer layer1) {
		this.layer1 = layer1;
	}
	public LedLayer getLayer2() {
		return layer2;
	}
	public void setLayer2(LedLayer layer2) {
		this.layer2 = layer2;
	}
	public LedLayer getLayer3() {
		return layer3;
	}
	public void setLayer3(LedLayer layer3) {
		this.layer3 = layer3;
	}
}
