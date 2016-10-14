package Basic;

public class Producer2 implements Runnable {
	//ArrayList<Integer> array;
		@Override
		public void run() {
			
			while (true) {
				//ArrayList<Integer> array = ProducerConsumer.array;
				//synchronized (array) {
				System.out.println("call+");
				synchronized (ProducerConsumer.array) {
					while (ProducerConsumer.array.size() == 5) {
						System.out.println("switch+");
						try {
							ProducerConsumer.array.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					ProducerConsumer.array.add(1);
					System.out.println("+1 " + " current size " + ProducerConsumer.array.size());
					ProducerConsumer.array.notifyAll();
				}
				
			}
		}
//		public Producer1(ArrayList<Integer> array) {
//			this.array = array;
//		}

}
