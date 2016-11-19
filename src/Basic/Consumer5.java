package Basic;

import java.util.PriorityQueue;
import java.util.concurrent.*;

public class Consumer5 implements Runnable {
	PriorityQueue q;
	public Consumer5(PriorityQueue q) {
		this.q = q;
	}
	
	public void run() {
		while (true) {
			synchronized (q) {
				if (!q.isEmpty()) {
					long current = (long) q.poll();
					System.out.println("-1 " + " size: " + q.size() + "  current: " + current); //time and size may not be consistent
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}