package Basic;

import java.util.*;
import java.util.concurrent.*;

public class ProducerConsumer {
	public static ArrayList<Integer> array = new ArrayList<Integer>();
	public static BlockingQueue q = new LinkedBlockingQueue();         //BlockingQueue can be set size
	private static BlockingQueue pbq = new PriorityBlockingQueue();
	private static PriorityQueue pq = new PriorityQueue();
	public static void main(String[] args) {
		System.out.println("Main start....");
//		Thread p = new Thread(new Producer1());
//		Thread p2 = new Thread(new Producer1());
//		Thread p3 = new Thread(new Producer1());
//		Thread c = new Thread(new Consumer1());
//		Thread c2 = new Thread(new Consumer1());
//		Thread c3 = new Thread(new Consumer1());
//		p.start();
//		p2.start();
//		p3.start();
//		c.start();
//		c2.start();
//		c3.start();
		
//		Thread p = new Thread(new Producer2());
//		Thread p2 = new Thread(new Producer2());
//		Thread p3 = new Thread(new Producer2());
//		Thread c = new Thread(new Consumer2());
//		Thread c2 = new Thread(new Consumer2());
//		Thread c3 = new Thread(new Consumer2());
//		p.start();
//		p2.start();
//		p3.start();
//		c.start();
//		c2.start();
//		c3.start();
		
//		Thread p = new Thread(new Producer3(q));
//		Thread p2 = new Thread(new Producer3(q));
//		Thread p3 = new Thread(new Producer3(q));
//		Thread c = new Thread(new Consumer3(q));
//		Thread c2 = new Thread(new Consumer3(q));
//		Thread c3 = new Thread(new Consumer3(q));
//		p.start();
//		p2.start();
//		p3.start();
//		c.start();
//		c2.start();
//		c3.start();
		
		
//		Thread p = new Thread(new Producer4(pbq));
//		Thread p2 = new Thread(new Producer4(pbq));
//		Thread p3 = new Thread(new Producer4(pbq));
//		Thread c = new Thread(new Consumer4(pbq));
//		Thread c2 = new Thread(new Consumer4(pbq));
//		Thread c3 = new Thread(new Consumer4(pbq));
//		p.start();
//		p2.start();
//		p3.start();
//		c.start();
//		c2.start();
//		c3.start();		
		
		Thread p = new Thread(new Producer5(pq));
		Thread p2 = new Thread(new Producer5(pq));
		Thread p3 = new Thread(new Producer5(pq));
		Thread c = new Thread(new Consumer5(pq));
		Thread c2 = new Thread(new Consumer5(pq));
		Thread c3 = new Thread(new Consumer5(pq));
		p.start();
		p2.start();
		p3.start();
		c.start();
		c2.start();
		c3.start();
	}
}
