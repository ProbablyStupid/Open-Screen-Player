package grabber;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Grabber {
	
	private static ArrayList<Integer> keyCodes = new ArrayList<Integer>();
	
	private static Robot myRobot;
	
	public static void initialize() {
		try {
			myRobot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public static void setKeyGrab(int keyCode) {
		keyCodes.add(keyCode);
	}
	
	public static void processEvent(KeyEvent e) {
		try {
			for (Integer i : keyCodes) {
				if (e.getKeyCode() == i)
				{
					myRobot.keyPress(i);
					// TODO: maybe add a mechanic to dynamically change this?
					Thread.sleep(100);
					myRobot.keyRelease(i);
				}
			}
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
	}
}
