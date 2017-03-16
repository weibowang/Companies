package Basic;

import java.util.concurrent.atomic.AtomicBoolean;

public class PrintNumberInOrder3 {
	static volatile AtomicBoolean flag;
	public static void main(String[] args) {
		System.out.println("Main start....");
		flag = new AtomicBoolean(true);
		Thread t1 = new Thread(new Runnable() {           
            public void run() {
            	int count = 0;
            	while (true) {
            		synchronized (flag) {
            			while (!flag.get()) {
                			try {
                				flag.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
//                			try {
//                				Thread.sleep(50);
//                			} catch (InterruptedException e) {
//                				// TODO Auto-generated catch block
//                				e.printStackTrace();
//                			}
                		}
            			System.out.println(count);
            			flag.set(false);
            			count = count + 2;
            			flag.notifyAll();
            		}
            		
            	}
            } 
        });
		
		Thread t2 = new Thread(new Runnable() {           
            public void run() {
            	int count = 1;
            	while (true) {
            		synchronized (flag) {
            			while (flag.get()) {
                			try {
                				flag.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
//                			try {
//                				Thread.sleep(50);
//                			} catch (InterruptedException e) {
//                				// TODO Auto-generated catch block
//                				e.printStackTrace();
//                			}
                		}
            			System.out.println(count);
            			flag.set(true);
            			count = count + 2;
            			flag.notifyAll();
            		}
            	}
            } 
        });
        t1.start();
        t2.start();
	}
}

