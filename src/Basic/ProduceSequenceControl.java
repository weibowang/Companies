package Basic;

import java.util.concurrent.atomic.AtomicInteger;

public class ProduceSequenceControl {
	static Integer state = 1;
	public static void main(String[] args) {
		System.out.println("Main start....");
		Thread p1 = new Thread(new ProduceSequence(1, state));
		Thread p2 = new Thread(new ProduceSequence(2, state));
		Thread p3 = new Thread(new ProduceSequence(3, state));
		p1.start();
		p2.start();
		p3.start();
		
//		Thread p1 = new Thread(new ProduceSequence2(1, state));
//		Thread p2 = new Thread(new ProduceSequence2(2, state));
//		Thread p3 = new Thread(new ProduceSequence2(3, state));
//		p1.start();
//		p2.start();
//		p3.start();
	}
}
