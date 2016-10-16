package Basic;

import java.util.*;
import java.util.concurrent.*;

public class ProducerConsumer {
	public static ArrayList<Integer> array = new ArrayList<Integer>();
	public static BlockingQueue q = new LinkedBlockingQueue();
	public static void main(String[] args) {
//		Thread p = new Thread(new Producer1());
//		Thread c = new Thread(new Consumer1());
//		p.start();
//		c.start();
		
		Thread p = new Thread(new Producer3(q));
		Thread c = new Thread(new Consumer3(q));
		p.start();
		c.start();
	}
}
