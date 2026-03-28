package threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPool {
    private final BlockingQueue<Runnable> taskQueue;
    private final ArrayList<PooledThread> threads;
    private static final Logger log = LoggerFactory.getLogger(ThreadPool.class);

    private void createPooledThreads(int threadCount) {
        for (int i = 0; i < threadCount; ++i) {
            PooledThread thread = new PooledThread(taskQueue);
            threads.add(thread);
            thread.start();
        }
    }

    public ThreadPool(int threadCount, int taskQueueSize) {
        taskQueue = new ArrayBlockingQueue<>(taskQueueSize);
        threads = new ArrayList<>(threadCount);
        createPooledThreads(threadCount);
    }

    public void addTask(Runnable task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("Thread was interrupted");
        }
    }

    public void stop() {
        for (PooledThread thread : threads) {
            thread.stopThread();
        }
    }

    public synchronized  int getTaskQueueSize() {
        return taskQueue.size();
    }
}
