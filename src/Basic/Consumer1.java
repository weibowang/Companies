package Basic;

import java.util.*;

public class Consumer1 implements Runnable {
	//ArrayList<Integer> array;
	@Override
	public void run() {
		while (true) {
			
				//ArrayList<Integer> array = ProducerConsumer.array;
			//System.out.println("call-");
			while (ProducerConsumer.array.isEmpty()) {
				System.out.println("switch-");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//array.wait(300);
			}
			synchronized (ProducerConsumer.array) {
				ProducerConsumer.array.remove(0);
				System.out.println("-1 " + " current size " + ProducerConsumer.array.size());
				//array.notifyAll();
			}
		}
	}
	
//	public Consumer1(ArrayList<Integer> array) {
//		this.array = array;
//	}

}
