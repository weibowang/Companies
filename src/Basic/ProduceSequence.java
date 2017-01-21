package Basic;


public class ProduceSequence implements Runnable {
	int val;
	static Integer state;
	public ProduceSequence(int val, int state) {
		this.val = val;
		this.state = state;
	}
	public void run() {
		while (true) {
			synchronized (state) {
				//System.out.println("val " +  val + " state " + state);
				if (val == 1) {
					if (state == 3) {
						System.out.println(val);
						state = val;
					}
				} else if (val == 2) {
					if (state == 1) {
						System.out.println(val);
						state = val;
					}
				} else if (val == 3){
					if (state == 2) {
						System.out.println(val);
						state = val;
					}
				}
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
