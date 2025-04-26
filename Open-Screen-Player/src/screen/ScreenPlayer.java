package screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import grabber.Grabber;
import screen.media.Image;
import screen.media.MediaQueue;
import screen.util.MediaMode;
import screen.util.Persistance;

public class ScreenPlayer {
	
	public static final int FAILSAFE_KEY = KeyEvent.VK_A;
	
	private JFrame mainFrame;
	
	private Dimension screenSize;
	private Color defaultBackgroundColor;
	private String windowTitle = "Open-Screen-Player";
	private boolean showCursor = true;
	private Persistance persistanceLevel = Persistance.NO_PERSISTANCE;
	private boolean fullScreen = true;
	private boolean init = false;
	
	// media display
	
	private MediaMode mediaMode;
	private Image singleImage;
//	private Clip singleClip;
	private MediaQueue mediaQueue;
	
	private boolean running = true;
	
	// Persistence preferences
	
	private boolean safeKey = true;
	private int safeKeyCode = KeyEvent.VK_A;
	
//	private ArrayList<JComponent> componentList = new ArrayList<JComponent>();
	
	public ScreenPlayer() {
		// TODO: add a reasonable `init();` mechanic.
	}
	
	public void run() {
		if (!init)
			init();
	}
	
	public void _define_defaults() {
		this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	}
	
	public void init() {
		_define_defaults();
		
		mainFrame = new JFrame();
		mainFrame.setTitle(windowTitle);
		mainFrame.setSize(screenSize);
		mainFrame.setUndecorated(true);
		// Fullscreen
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		mainFrame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyCode() == safeKeyCode && safeKey)
				{
					System.exit(0);
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == safeKeyCode && safeKey)
				{
					System.exit(0);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == safeKeyCode && safeKey)
				{
					System.exit(0);
				}
			}
		});
		
		mainFrame.setVisible(true);
		mainFrame.requestFocus();
		
		switch (persistanceLevel) {
		case NO_EXIT:
			mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			Grabber.startGrab();
		case ALWAYS_ON_TOP:
			mainFrame.setAutoRequestFocus(true);
			mainFrame.setAlwaysOnTop(true);
		case NO_PERSISTANCE:
			mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			break;
		default:
			break;
		}
		
		System.out.println("Window initialization complete.");
		initializeMedia();
	}
	
	public void loadDefaultMedia() {
		// TODO implement this feature
	}
	
	public void initializeMedia() {
		System.out.println("Initializing media");
		
		if (this.mediaMode == null) {
			System.out.println("No Media Mode Set!");	
			loadDefaultMedia();
			return;
		}
		
		switch (this.mediaMode)
		{
		case SINGLE_IMAGE:
			mainFrame.getContentPane().add(this.singleImage.getSwingComponent());
			break;
		case IMAGE_QUEUE:
			mainFrame.getContentPane().add(this.mediaQueue.demand().getSwingComponent());
			break;
		default:
			System.out.println("[ERROR] NO VALID MEDIA MODE!");
			break;
		};
		
		// TODO: implement external swing component mechanism.
		
		startMedia();
	}
	
	public void startMedia() {
		System.out.println("Starting media");
		
		//!! Learning: Don't repaint the content pane !!
//		mainFrame.getContentPane().repaint();
		
		mainFrame.repaint();
		
		// handoff to main graphics loop
		if (this.mediaMode == MediaMode.SINGLE_CLIP || this.mediaMode == MediaMode.SINGLE_IMAGE)
		{
			
		}
		else
		{
			loopQueue();
		}
	}
	
	protected void loopQueue() {
		// TODO: time management system for media queue
		
		while (running) {
			try {
				Thread.sleep(mediaQueue.getCurrent().getDuration());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			mainFrame.getContentPane().removeAll();
			mainFrame.add(mediaQueue.demand().getSwingComponent());
			// TODO: analyze how much performance this draws over using an array with all the elements at the beginning
			//		 and then hiding and showing them individually.
			mainFrame.revalidate();
			mainFrame.repaint();
		}
		
	}
	
	
	// getters and setters
	

	public Dimension getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(Dimension screenSize) {
		this.screenSize = screenSize;
	}

	public Color getDefaultBackgroundColor() {
		return defaultBackgroundColor;
	}

	public void setDefaultBackgroundColor(Color defaultBackgroundColor) {
		this.defaultBackgroundColor = defaultBackgroundColor;
	}

	public String getWindowTitle() {
		return windowTitle;
	}

	public void setWindowTitle(String windowTitle) {
		this.windowTitle = windowTitle;
	}

	public boolean isShowCursor() {
		return showCursor;
	}

	public void setShowCursor(boolean showCursor) {
		this.showCursor = showCursor;
	}

	public Persistance getPersistanceLevel() {
		return persistanceLevel;
	}

	public void setPersistanceLevel(Persistance persistanceLevel) {
		this.persistanceLevel = persistanceLevel;
	}

	public MediaMode getMediaMode() {
		return mediaMode;
	}

	public void setMediaMode(MediaMode mediaMode) {
		this.mediaMode = mediaMode;
	}

	public Image getSingleImage() {
		return singleImage;
	}

	public void setSingleImage(Image singleImage) {
		this.singleImage = singleImage;
	}

	public boolean isFullScreen() {
		return fullScreen;
	}

	public void setFullScreen(boolean fullScreen) {
		this.fullScreen = fullScreen;
	}

	public MediaQueue getMediaQueue() {
		return mediaQueue;
	}

	public void setMediaQueue(MediaQueue mediaQueue) {
		this.mediaQueue = mediaQueue;
	}

	public boolean isSafeKey() {
		return safeKey;
	}

	public void setSafeKey(boolean safeKey) {
		this.safeKey = safeKey;
	}

	public int getSafeKeyCode() {
		return safeKeyCode;
	}

	public void setSafeKeyCode(int safeKeyCode) {
		this.safeKeyCode = safeKeyCode;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}
