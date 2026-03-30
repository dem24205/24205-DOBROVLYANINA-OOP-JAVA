package threadpool;

import java.util.concurrent.BlockingQueue;

public class PooledThread extends Thread {
    private final BlockingQueue<Runnable> tasks;

    public PooledThread(BlockingQueue<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                Runnable task = tasks.take();
                task.run();
            } catch (InterruptedException e) {
                this.interrupt();
                break;
            }
        }
    }

    public void stopThread() { this.interrupt(); }
}