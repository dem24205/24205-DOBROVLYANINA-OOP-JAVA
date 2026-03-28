package factory;
public abstract class Service {
    private int delay;

    public Service(int delay) {
        this.delay = delay;
    }

    public void setDelay(int speed) {
        if (speed <= 0) {
            return;
        }
        this.delay = speed;
    }

    public int getDelay() {
        return delay;
    }
}
