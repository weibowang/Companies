package Basic;

public class PrintNumberInOrder2 {
	static BooleanWrapper wrapper;
	public static void main(String[] args) {
		System.out.println("Main start....");
		wrapper = new BooleanWrapper(true);
		Thread t1 = new Thread(new Runnable() {           
            public void run() {
            	int count = 0;
            	while (true) {
            		synchronized (wrapper) {
            			while (!wrapper.flag) {
                			try {
                				wrapper.wait();
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
            			wrapper.flag = false;
            			count = count + 2;
            			wrapper.notifyAll();
            		}
            		
            	}
            } 
        });
		
		Thread t2 = new Thread(new Runnable() {           
            public void run() {
            	int count = 1;
            	while (true) {
            		synchronized (wrapper) {
            			while (wrapper.flag) {
                			try {
                				wrapper.wait();
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
            			wrapper.flag = true;
            			count = count + 2;
            			wrapper.notifyAll();
            		}
            	}
            } 
        });
        t1.start();
        t2.start();
	}
}
