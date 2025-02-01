package classes;

import java.util.ArrayList;

public class Queue<T> {
        private ArrayList<T> list;

        public Queue() {
            list = new ArrayList<>();
        }

        public void enqueue(T element) {
            list.add(element);
        }

        public T dequeue() throws Exception {
            if (isEmpty()) {
                throw new MyExceptions.emptyQueueException("Queue is empty");
            }
            return list.remove(0);
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }
        public boolean isFull() { return false; }

        public int size() {
            return list.size();
    }
}


