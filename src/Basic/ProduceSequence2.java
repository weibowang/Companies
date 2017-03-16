package Basic;

public class ProduceSequence2 implements Runnable {
	int val;
	IntWrapper state;
	public ProduceSequence2(int val, IntWrapper state) {
		this.val = val;
		this.state = state;
	}
	public void run() {
		while (true) {
			synchronized (state) {
				//System.out.println("val " +  val + " state " + state);
				if (val == 1) {
					while (state.value != 3) {
						try {
							state.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println(val);
					state.value = val;
					state.notifyAll();
				} else if (val == 2) {
					while (state.value != 1) {
						try {
							state.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println(val);
					state.value = val;
					state.notifyAll();
				} else if (val == 3){
					while (state.value != 2) {
						try {
							state.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println(val);
					state.value = val;
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
