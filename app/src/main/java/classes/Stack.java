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

    public T pop() throws Exception{
        if (isEmpty()) { throw new MyExceptions.stackUnderFlow("Invalid operation"); }
        return stack.remove(stackTop--);
    }

    public T top() throws Exception{
        if (isEmpty()) { throw new MyExceptions.stackUnderFlow("Stack is empty"); }
        return stack.get(stackTop);
    }

    public boolean isEmpty() { return stackTop == - 1; }
    public boolean isFull() { return false; }

    public int size() { return stackTop + 1; }
}
