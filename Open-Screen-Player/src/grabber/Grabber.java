package grabber;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Grabber {

	private static boolean running = true;
	private static int mouseX = 100, mouseY = 100;
	private static int button1 = InputEvent.getMaskForButton(1);
	private static int interval = 100;

	public static int getMouseX() {
		return mouseX;
	}

	public static void setMouseX(int mouseX) {
		Grabber.mouseX = mouseX;
	}

	public static void startGrab() {
		Thread myThread = new Thread(() -> {
			try {
				loop();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (AWTException e) {
				e.printStackTrace();
			}
		});
		myThread.run();
	}

	private static void loop() throws InterruptedException, AWTException {
		Robot myRobot = new Robot();
		while (running) {
			myRobot.keyRelease(KeyEvent.VK_ALT);
			myRobot.keyRelease(KeyEvent.VK_WINDOWS);

			myRobot.mouseMove(mouseX, mouseY);
			myRobot.mousePress(button1);

			Thread.sleep(interval);

			myRobot.keyPress(KeyEvent.VK_ALT);
			myRobot.keyPress(KeyEvent.VK_WINDOWS);

			myRobot.mouseMove(mouseX, mouseY);
			myRobot.mouseRelease(button1);

			Thread.sleep(interval);
		}
	}
	
	/**
	 * Warning, this will make the operating thread sleep!
	 * Must call initialize() before using this!
	 * All errors are handled on the application end; application end has to provide the Robot!
	 * @throws AWTException 
	 * @throws InterruptedException 
	 */
	public static void loop_iteration(@SuppressWarnings("exports") Robot manualRobot) throws AWTException, InterruptedException {

		manualRobot.keyRelease(KeyEvent.VK_ALT);
		manualRobot.keyRelease(KeyEvent.VK_WINDOWS);

		manualRobot.mouseMove(mouseX, mouseY);
		manualRobot.mousePress(button1);

		Thread.sleep(interval);

		manualRobot.keyPress(KeyEvent.VK_ALT);
		manualRobot.keyPress(KeyEvent.VK_WINDOWS);

		manualRobot.mouseMove(mouseX, mouseY);
		manualRobot.mouseRelease(button1);

		Thread.sleep(interval);
	}
}
