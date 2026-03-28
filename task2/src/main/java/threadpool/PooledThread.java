package threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.BlockingQueue;

public class PooledThread extends Thread {
    private final BlockingQueue<Runnable> tasks;
    private static final Logger log = LoggerFactory.getLogger(PooledThread.class);

    public PooledThread(BlockingQueue<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Runnable task = tasks.take();
                task.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.warn("Thread {} was interrupted", getName());
                break;
            }
        }
    }

    public void stopThread() {
        this.interrupt();
    }
}