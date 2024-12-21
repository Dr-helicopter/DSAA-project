package classes;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Queue<T> {
        private ArrayList<T> list;

        public Queue() {
            list = new ArrayList<>();
        }

        public void add(T element) {
            list.add(element);
        }

        public T remove() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            return list.remove(0);
        }

        public T peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            return list.get(0);
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }

        public int size() {
            return list.size();
    }
}


