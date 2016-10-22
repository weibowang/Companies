package Basic;

import java.util.*;

public class Consumer1 implements Runnable {
	//ArrayList<Integer> array;
	@Override
	public void run() {
		while (true) {
			
				//ArrayList<Integer> array = ProducerConsumer.array;
			System.out.println("call-");
			synchronized (ProducerConsumer.array) {
				if (ProducerConsumer.array.isEmpty()) {
					System.out.println("switch-");
				} else {
					ProducerConsumer.array.remove(0);
					System.out.println("-1 " + " current size " + ProducerConsumer.array.size());
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
	
//	public Consumer1(ArrayList<Integer> array) {
//		this.array = array;
//	}

}
