package peterson;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Buffer b = new Buffer(5);
		Consumer cons = new Consumer(b);
		Producer prod = new Producer(b);
		cons.start();
		prod.start();
	}

}
