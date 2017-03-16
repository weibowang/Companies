package Facebook;

import java.util.*;
/*
 * https://www.jiuzhang.com/qa/865/
 */

public class MinQueue {
	MinStack input;
	MinStack output;
	public MinQueue() {
        input = new MinStack();
        output = new MinStack();
    }
    
    public void add(int x) {
        input.push(x);
    }
    
    public int poll() {
        top();
        return output.pop();
    }
    
    public int top() {
        if (output.isEmpty()) {
        	while (!input.isEmpty()) {
        		output.push(input.pop());
        	}
        }
        if (!output.isEmpty()) {
        	return output.top();
        }
        return -1;
    }
    
    public int getMin() {
    	if (input.getMin() == Integer.MAX_VALUE) {
    		return output.getMin();
    	}
    	if (output.getMin() == Integer.MAX_VALUE) {
    		return input.getMin();
    	}
        return Math.min(input.getMin(), output.getMin());
    }
    
    public static void main(String[] args) {
    	MinQueue q = new MinQueue();
    	q.add(1);
    	q.add(2);
    	q.add(3);
    	q.add(1);
    	System.out.println(q.getMin());
    	q.poll();
    	System.out.println(q.getMin());
    	q.poll();
    	System.out.println(q.getMin());
    	q.add(4);
    	System.out.println(q.getMin());
    }
    
}
