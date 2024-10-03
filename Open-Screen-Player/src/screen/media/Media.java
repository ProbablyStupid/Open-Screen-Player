package screen.media;

import javax.swing.JComponent;

public class Media {
	
	/**
	 * The location of the media file on device.
	 */
	protected String hardLocation;
	
	/**
	 * The duration that this media will remain on the screen for.
	 * - Positive numbers in milliseconds.
	 * - 0 means skipping (if enabled)
	 * - negative numbers mean infinite time
	 */
	protected long duration = MediaConstants.DEFAULT_MEDIA_DURATION;
	
	protected boolean loaded = false;
	
	protected JComponent swingComponent;

	public String getHardLocation() {
		return hardLocation;
	}

	public void setHardLocation(String hardLocation) {
		this.hardLocation = hardLocation;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public boolean isLoaded() {
		return loaded;
	}
	
	@Deprecated
	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	public JComponent getSwingComponent() {
		return swingComponent;
	}
	
}
