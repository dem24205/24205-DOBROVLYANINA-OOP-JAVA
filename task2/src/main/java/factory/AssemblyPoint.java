package factory;

import factory.details.Accessory;
import factory.details.Body;
import factory.details.Car;
import factory.details.Motor;
import threadpool.ThreadPool;

public class AssemblyPoint {
    private final ThreadPool threadPool;
    private final Storage<Body> bodyStorage;
    private final Storage<Motor> motorStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Car> carStorage;

    public AssemblyPoint(int workersNum, Storage<Body> bodyStorage, Storage<Motor> motorStorage,
                         Storage<Accessory> accessoryStorage, Storage<Car> carStorage) {
        threadPool = new ThreadPool(workersNum, 1000);
        this.bodyStorage = bodyStorage;
        this.motorStorage = motorStorage;
        this.accessoryStorage = accessoryStorage;
        this.carStorage = carStorage;
    }

    public void assembleCar() {
        threadPool.addTask(new Worker(bodyStorage, motorStorage, accessoryStorage, carStorage));
    }

    public int getQueueSize() {
        return threadPool.getTaskQueueSize();
    }

    public void stop() {
        threadPool.stop();
    }
}
