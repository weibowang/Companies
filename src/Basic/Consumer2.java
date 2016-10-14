package Basic;

public class Consumer2 implements Runnable {
	//ArrayList<Integer> array;
		@Override
		public synchronized void run() {
			while (true) {
				
					//ArrayList<Integer> array = ProducerConsumer.array;
				System.out.println("call-");
				synchronized (ProducerConsumer.array) {
					while (ProducerConsumer.array.isEmpty()) {
						System.out.println("switch-");
						try {
							ProducerConsumer.array.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
						
					ProducerConsumer.array.remove(0);
					System.out.println("-1 " + " current size " + ProducerConsumer.array.size());
					ProducerConsumer.array.notifyAll();
				}
			}
		}
		
//		public Consumer1(ArrayList<Integer> array) {
//			this.array = array;
//		}

}
