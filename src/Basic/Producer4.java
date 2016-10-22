package Basic;

import java.util.concurrent.*;

public class Producer4 implements Runnable{
	BlockingQueue q;
	public Producer4(BlockingQueue q) {
		this.q = q;
	}
	
	public void run(){
		while (true) {
			try {
				long current = System.currentTimeMillis();
				q.put(current);
				System.out.println("+1 " + " size: " + q.size() + "  current: " + current); //time and size may not be consistent
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
