package Basic;

import java.util.*;

public class Producer5 implements Runnable {
	public PriorityQueue q;
	public Producer5(PriorityQueue q) {
		this.q = q;
	}
	
	public void run() {
		while (true) {
			synchronized (q) {
				if (q.size() == 1) {
					//do nothing
				} else {
					long current = System.currentTimeMillis();
					q.add(current);
					//System.out.println("+1 " + " size: " + q.size() + "  current: " + current); //time and size may not be consistent
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
