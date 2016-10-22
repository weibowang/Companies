package Basic;

import java.util.concurrent.*;

public class Consumer3 implements Runnable {
	private BlockingQueue q;
	public Consumer3(BlockingQueue q) {
		this.q = q;
	}
	public void run() {
		while(true) {
			try {
				q.take();
				System.out.println("-1  size:" + q.size());
				Thread.sleep(100);
//				int num = (int) (Math.random() * ( 300 - 100 ));
//				Thread.sleep(num);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
