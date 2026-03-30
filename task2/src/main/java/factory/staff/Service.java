package factory.staff;

public abstract class Service {
    private int delay;

    public Service(int delay) {
        this.delay = delay;
    }

    public void setDelay(int delay) {
        if (delay <= 0) {
            return;
        }
        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }
}
