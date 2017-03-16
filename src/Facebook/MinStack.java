package Facebook;

import java.util.*;

public class MinStack {
	/** initialize your data structure here. */
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> min = new Stack<Integer>();
    public MinStack() {
        
    }
    
    public void push(int x) {
        stack.push(x);
        if (min.isEmpty() || x < min.peek()) {
            min.push(x);
        } else {
            min.push(min.peek());
        }
    }
    
    public int pop() {
        int val = stack.pop();
        min.pop();
        return val;
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
    	if (min.isEmpty()) {
    		return Integer.MAX_VALUE;
    	}
        return min.peek();
    }
    
    public boolean isEmpty() {
    	return stack.isEmpty();
    }
}
