package screen.media;

import java.util.ArrayList;
import java.util.Iterator;

public class MediaQueue implements Iterable<Media>{
	
	protected ArrayList<Media> orderedList = new ArrayList<Media>();
	
	protected int index = 0;
	
	public MediaQueue() {
	}
	
	public Media demand()
	{
		progress();
		
		System.out.println("returning index " + index);
		return orderedList.get(index);
	}
	
	public Media getCurrent() {
		return orderedList.get(index);
	}
	
	public void progress() {
		if (index >= (orderedList.size()-1))
			index = 0;
		else
			index++;
	}
	
	public void jump(int index) {
		this.index = index;
	}
	
	public void setQueue(ArrayList<Media> list) {
		this.orderedList = list;
	}
	
	public void add(Media m)
	{
		this.orderedList.add(m);
	}
	
	public void insert(Media m, int index) {
		this.orderedList.add(index, m);
	}
	
	public void remove(int index) {
		this.orderedList.remove(index);
	}

	@Override
	public Iterator<Media> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public class MediaQueueIterator implements Iterator<Media> {

		@Override
		public boolean hasNext() {
			return !(index >= orderedList.size());
		}

		@Override
		public Media next() {
			return demand();
		}
	}
	
}
