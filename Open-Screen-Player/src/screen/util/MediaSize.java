package screen.util;

public class MediaSize {
	
	long width; // in pixels
	long height; // in pixels
	
	double correct_ratio;
	double actual_ratio;
	
	public MediaSize(long width, long height, double correct_ratio, double actual_ratio) {
		this.width = width;
		this.height = height;
		this.correct_ratio = correct_ratio;
		this.actual_ratio = actual_ratio;
	}

	public long getWidth() {
		return width;
	}

	public void setWidth(long width) {
		this.width = width;
	}

	public long getHeight() {
		return height;
	}

	public void setHeight(long height) {
		this.height = height;
	}

	public double getCorrect_ratio() {
		return correct_ratio;
	}

	public void setCorrect_ratio(double correct_ratio) {
		this.correct_ratio = correct_ratio;
	}

	public double getActual_ratio() {
		return actual_ratio;
	}

	public void setActual_ratio(double actual_ratio) {
		this.actual_ratio = actual_ratio;
	}
	
	
}
