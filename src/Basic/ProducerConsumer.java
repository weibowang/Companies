package Basic;

import java.util.*;

public class ProducerConsumer {
	public static ArrayList<Integer> array = new ArrayList<Integer>();
	public static void main(String[] args) {
		Thread p = new Thread(new Producer1());
		Thread c = new Thread(new Consumer1());
		p.start();
		c.start();
	}
}
