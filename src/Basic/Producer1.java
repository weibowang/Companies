package Basic;

import java.util.*;

public class Producer1 implements Runnable {
	//ArrayList<Integer> array;
	@Override
	public void run() {
		
		while (true) {
			//ArrayList<Integer> array = ProducerConsumer.array;
			//synchronized (array) {
			System.out.println("call+");
			synchronized (ProducerConsumer.array) {
				if (ProducerConsumer.array.size() >= 1) {
					System.out.println("switch+");
				} else {
					ProducerConsumer.array.add(1);
					System.out.println("+1 " + " current size " + ProducerConsumer.array.size());
				}
			}
			try {
				System.out.println("sleep......");
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
//	public Producer1(ArrayList<Integer> array) {
//		this.array = array;
//	}

}
