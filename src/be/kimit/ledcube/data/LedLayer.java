package be.kimit.ledcube.data;

public class LedLayer {
	private boolean led0;
	private boolean led1;
	private boolean led2;	
	private boolean led3;	
	private boolean led4;	
	private boolean led5;	
	private boolean led6;	
	private boolean led7;	
	private boolean led8;	
	private boolean led9;	
	private boolean led10;	
	private boolean led11;	
	private boolean led12;	
	private boolean led13;	
	private boolean led14;	
	private boolean led15;
	
	public boolean isLed0() {
		return led0;
	}
	public void setLed0(boolean led0) {
		this.led0 = led0;
	}
	public boolean isLed1() {
		return led1;
	}
	public void setLed1(boolean led1) {
		this.led1 = led1;
	}
	public boolean isLed2() {
		return led2;
	}
	public void setLed2(boolean led2) {
		this.led2 = led2;
	}
	public boolean isLed3() {
		return led3;
	}
	public void setLed3(boolean led3) {
		this.led3 = led3;
	}
	public boolean isLed4() {
		return led4;
	}
	public void setLed4(boolean led4) {
		this.led4 = led4;
	}
	public boolean isLed5() {
		return led5;
	}
	public void setLed5(boolean led5) {
		this.led5 = led5;
	}
	public boolean isLed6() {
		return led6;
	}
	public void setLed6(boolean led6) {
		this.led6 = led6;
	}
	public boolean isLed7() {
		return led7;
	}
	public void setLed7(boolean led7) {
		this.led7 = led7;
	}
	public boolean isLed8() {
		return led8;
	}
	public void setLed8(boolean led8) {
		this.led8 = led8;
	}
	public boolean isLed9() {
		return led9;
	}
	public void setLed9(boolean led9) {
		this.led9 = led9;
	}
	public boolean isLed10() {
		return led10;
	}
	public void setLed10(boolean led10) {
		this.led10 = led10;
	}
	public boolean isLed11() {
		return led11;
	}
	public void setLed11(boolean led11) {
		this.led11 = led11;
	}
	public boolean isLed12() {
		return led12;
	}
	public void setLed12(boolean led12) {
		this.led12 = led12;
	}
	public boolean isLed13() {
		return led13;
	}
	public void setLed13(boolean led13) {
		this.led13 = led13;
	}
	public boolean isLed14() {
		return led14;
	}
	public void setLed14(boolean led14) {
		this.led14 = led14;
	}
	public boolean isLed15() {
		return led15;
	}
	public void setLed15(boolean led15) {
		this.led15 = led15;
	}	
	
	public int[] toIntArray() {
		int[] intArray = new int[16];
		intArray[0] = isLed0()?1:0;
		intArray[1] = isLed1()?1:0;
		intArray[2] = isLed2()?1:0;
		intArray[3] = isLed3()?1:0;
		intArray[4] = isLed4()?1:0;
		intArray[5] = isLed5()?1:0;
		intArray[6] = isLed6()?1:0;
		intArray[7] = isLed7()?1:0;
		intArray[8] = isLed8()?1:0;
		intArray[9] = isLed9()?1:0;
		intArray[10] = isLed10()?1:0;
		intArray[11] = isLed11()?1:0;
		intArray[12] = isLed12()?1:0;
		intArray[13] = isLed13()?1:0;
		intArray[14] = isLed14()?1:0;
		intArray[15] = isLed15()?1:0;
		return intArray;
		
	}
}
