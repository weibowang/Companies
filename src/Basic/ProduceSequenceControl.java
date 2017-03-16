package Basic;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * https://www.careercup.com/question?id=4783236498587648
 * 
 * https://www.careercup.com/question?id=5652784707796992
 */

public class ProduceSequenceControl {
	static Integer state = 1;
	static IntWrapper wrapper = new IntWrapper(1);
	static AtomicInteger aInteger = new AtomicInteger(1);
	
	public static void main(String[] args) {
		System.out.println("Main start....");
//		Thread p1 = new Thread(new ProduceSequence(1, state));
//		Thread p2 = new Thread(new ProduceSequence(2, state));
//		Thread p3 = new Thread(new ProduceSequence(3, state));
//		p1.start();
//		p2.start();
//		p3.start();

//		Thread p1 = new Thread(new ProduceSequence2(1, wrapper));
//		Thread p2 = new Thread(new ProduceSequence2(2, wrapper));
//		Thread p3 = new Thread(new ProduceSequence2(3, wrapper));
//		p1.start();
//		p2.start();
//		p3.start();
		
		Thread p1 = new Thread(new ProduceSequence3(1, aInteger));
		Thread p2 = new Thread(new ProduceSequence3(2, aInteger));
		Thread p3 = new Thread(new ProduceSequence3(3, aInteger));
		p1.start();
		p2.start();
		p3.start();
		
		
		
		
	}
}
