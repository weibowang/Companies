package Google;

import java.util.*;

public class ZigzagIterator {
	
	public static void main(String[] args) {
		List<Integer> v1 = new ArrayList<Integer>();
		v1.add(1);
		v1.add(2);
		List<Integer> v2 = new ArrayList<Integer>();
		v2.add(3);
		v2.add(4);
		v2.add(5);
		v2.add(6);
		ZigzagIterator z = new ZigzagIterator(v1, v2);
		while (z.hasNext()) {
			System.out.println(z.next());
			if (z.hasPrevious()) {
				z.previous();
			}
		}
		
	}
    
	Queue<Iterator<Integer>> q;
	Queue<Integer> previous;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        
        // First attempt with follow up
        q = new LinkedList<Iterator<Integer>>();
        previous = new LinkedList<Integer>();
        
        Iterator<Integer> i1 = v1.iterator();
        Iterator<Integer> i2 = v2.iterator();
        q.add(i1);
        q.add(i2);
    }

    public int next() {
        Iterator<Integer> i = q.poll();
        int tmp = i.next();
        previous.add(tmp);
        if (i.hasNext()) {
            q.add(i);
        }
        return tmp;
    }

    public boolean hasNext() {
        while (!q.isEmpty()) {
            Iterator<Integer> i = q.peek();
            if (i.hasNext()) {
                return true;
            }
            q.poll();
        }
       return false;
    }
    
    public boolean hasPrevious() {
    	return !previous.isEmpty();
    }
    
    public int previous() {
    	return previous.poll();
    }
}
