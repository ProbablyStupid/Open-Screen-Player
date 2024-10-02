package screen.media;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Image extends Media {
	
	private ImageFormat format;
	private ImageIcon swingIcon;
	
	public Image(String location) {
		super.hardLocation = location;
		this.load();
	}

	public ImageFormat getFormat() {
		return format;
	}

	public void setFormat(ImageFormat format) {
		this.format = format;
	}
	
	public void load() {
		swingIcon = new ImageIcon(this.hardLocation);
		JLabel label = new JLabel(swingIcon);
		this.swingComponent = label;
		System.out.println("Loaded image: " + this.hardLocation);
	}
}
