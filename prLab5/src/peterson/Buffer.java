package peterson;

public class Buffer {
	private int[] items;
	private int pIndex = 0;
	private int cIndex = 0;
	private volatile int nItems = 0;
	
	public Buffer(int size) {
		items = new int[size];
	}
	public int get(){
		while(items.length == 0) Thread.yield();
	

		int data = items[pIndex];
		System.out.println("Prod: " + data + " form pos: " + pIndex);
		nItems --;
		pIndex = (pIndex + 1) % items.length;
		return data;
	}
	public void put(int data) {
		while(items.length == nItems) Thread.yield();
		System.out.println("Prod: " + data + " form pos: " + pIndex);
		items[pIndex] = data;
		nItems ++;
		pIndex = (pIndex + 1) % items.length;
	}
}
