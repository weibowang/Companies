package Basic;

import java.util.concurrent.*;

public class Consumer4 implements Runnable{
	BlockingQueue q;
	public Consumer4(BlockingQueue q) {
		this.q = q;
	}
	
	public void run() {
		while (true) {
			try {
				long current = (long) q.take();
				System.out.println("-1 " + " size: " + q.size() + "  current: " + current);
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
