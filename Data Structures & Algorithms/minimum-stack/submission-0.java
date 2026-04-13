class MinStack {
    private Stack<Integer> inStack;
    private Stack<Integer> minStack;

    public MinStack() {
        inStack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int val) {
        inStack.push(val);
        
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }
    
    public void pop() {
        if (inStack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        inStack.pop();
    }
    
    public int top() {
        return inStack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}