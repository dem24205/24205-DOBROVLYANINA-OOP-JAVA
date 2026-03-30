package factory;

import factory.details.*;
import java.util.LinkedList;
import java.util.Queue;

public class Storage<T extends Product> {
    private final int capacity;
    private final Queue<T> items = new LinkedList<>();
    private StorageObserver observer;
    private int totalProduced = 0;

    public Storage(int capacity, StorageObserver observer) {
        this.capacity = capacity;
        this.observer = observer;
    }

    public void setObserver(StorageObserver observer) {
        this.observer = observer;
    }

    public synchronized void put(T item) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        items.add(item);
        totalProduced++;
        notifyAll();
    }

    public synchronized T get() throws InterruptedException {
        if (observer != null) {
            observer.onCarRemoved();
        }
        while (items.isEmpty()) {
            wait();
        }
        T item = items.poll();
        notifyAll();
        return item;
    }

    private boolean isFull() {
        return items.size() >= capacity;
    }

    public synchronized int getTotalProduced() {
        return totalProduced;
    }

    public synchronized int getItemsNum() {
        return items.size();
    }
}