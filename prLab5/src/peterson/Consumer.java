package peterson;

import java.util.Random;

public class Consumer extends Thread{
	
	private Random rnd = new Random();
	private Buffer buffer;
	
	public Consumer(Buffer b) {
		this.buffer = b;
	}
	public void run(){
		for(int i = 0; i < 14; i++) try{
			buffer.put(rnd.nextInt(100));
			sleep(rnd.nextInt(100)+100);
		}catch(InterruptedException x) {
			x.printStackTrace();
		}
	}
}
