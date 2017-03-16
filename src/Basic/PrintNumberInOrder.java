package Basic;

/*
 * https://www.careercup.com/question?id=5652784707796992
 */
public class PrintNumberInOrder {
	static Boolean flag = true;
	public static void main(String[] args) {
		System.out.println("Main start....");
		//boolean flag = true;
		Thread t1 = new Thread(new Runnable() {           
            public void run() {
            	int count = 0;
            	while (true) {
            		synchronized (flag) {
            			while (flag) {
                			System.out.println(count);
                			flag = false;
                			count = count + 2;
//                			try {
//                				Thread.sleep(50);
//                			} catch (InterruptedException e) {
//                				// TODO Auto-generated catch block
//                				e.printStackTrace();
//                			}
                		}
            		}
            		
            	}
            } 
        });
		
		Thread t2 = new Thread(new Runnable() {           
            public void run() {
            	int count = 1;
            	while (true) {
            		synchronized (flag) {
            			while (!flag) {
                			System.out.println(count);
                			flag = true;
                			count = count + 2;
//                			try {
//                				Thread.sleep(50);
//                			} catch (InterruptedException e) {
//                				// TODO Auto-generated catch block
//                				e.printStackTrace();
//                			}
                		}
            		}
            	}
            } 
        });
        t1.start();
        t2.start();
	}
}
