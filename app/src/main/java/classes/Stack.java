package classes;

import java.util.ArrayList;

public class Stack<T> {
    private final ArrayList<T> stack;
    private int stackTop = -1;
    public Stack() { stack = new ArrayList<T>(); }

    public void push(T data) {
        stack.add(data);
        stackTop ++;
    }

    public T pop() {
        if (isEmpty()) { throw new IllegalStateException("Stack is empty"); }
        return stack.remove(stackTop--);
    }

    public T peek() {
        if (isEmpty()) { throw new IllegalStateException("Stack is empty"); }
        return stack.get(stackTop);
    }

    public boolean isEmpty() { return stackTop == - 1; }

    public int size() { return stackTop + 1; }
}
