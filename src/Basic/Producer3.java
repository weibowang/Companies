package Basic;

import java.util.*;
import java.util.concurrent.*;


public class Producer3 implements Runnable {
	private BlockingQueue q;
	
	public void run() {
		while (true) {
			try {
				q.put(1);
				System.out.println("+1  size:" + q.size());
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Producer3(BlockingQueue q) {
		this.q = q;
	}
}
