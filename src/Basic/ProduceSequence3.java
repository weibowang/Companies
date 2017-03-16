package Basic;

import java.util.concurrent.atomic.AtomicInteger;

public class ProduceSequence3 implements Runnable {
	int val;
	AtomicInteger state;
	public ProduceSequence3(int val, AtomicInteger state) {
		this.val = val;
		this.state = state;
	}
	public void run() {
		while (true) {
			synchronized (state) {
				//System.out.println("val " +  val + " state " + state);
				if (val == 1) {
					while (state.get() != 3) {
						try {
							state.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println(val);
					state.set(val);
					state.notifyAll();
				} else if (val == 2) {
					while (state.get() != 1) {
						try {
							state.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println(val);
					state.set(val);
					state.notifyAll();
				} else if (val == 3){
					while (state.get() != 2) {
						try {
							state.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println(val);
					state.set(val);
					state.notifyAll();
				}
			}
//			try {
//				Thread.sleep(50);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
}