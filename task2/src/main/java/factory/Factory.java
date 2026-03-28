package factory;

import factory.details.Accessory;
import factory.details.Body;
import factory.details.Car;
import factory.details.Motor;
import gui.FactoryGUIListener;
import utilities.Config;

import java.util.ArrayList;
import java.util.List;

public class Factory implements FactoryGUIListener {
    private final Config config;
    private Storage<Body> bodyStorage;
    private Storage<Motor> motorStorage;
    private Storage<Accessory> accessoryStorage;
    private Storage<Car> carStorage;

    private Supplier<Body> bodySupplier;
    private Supplier<Motor> motorSupplier;
    private List<Supplier<Accessory>> accessorySuppliers;
    private List<Dealer> dealers;
    private AssemblyPoint assemblyPoint;
    private ArrayList<Thread> allThreads;

    private void initStorages() {
        bodyStorage = new Storage<>(config.getInt("StorageBodySize"), null);
        accessoryStorage = new Storage<>(config.getInt("StorageAccessorySize"), null);
        motorStorage = new Storage<>(config.getInt("StorageMotorSize"), null);
        carStorage = new Storage<>(config.getInt("StorageCarSize"), null);
    }

    private void initAssemblePoint() {
        assemblyPoint = new AssemblyPoint(config.getInt("Workers"), bodyStorage,
                motorStorage, accessoryStorage, carStorage);

    }

    private void initController() {
        Controller controller = new Controller(assemblyPoint);
        carStorage.setObserver(controller);
    }

    private void initSuppliers() {
        int bodyDelay = config.getInt("BodySupplierSpeed");
        bodySupplier = new Supplier<>(Body.class, bodyStorage, bodyDelay);

        int motorDelay = config.getInt("MotorSupplierSpeed");
        motorSupplier = new Supplier<>(Motor.class, motorStorage, motorDelay);

        accessorySuppliers = new ArrayList<>();
        int accessorySuppliersNum = config.getInt("AccessorySuppliers");
        int accessoryDelay = config.getInt("AccessorySupplierSpeed");
        for (int i = 0; i < accessorySuppliersNum; ++i) {
            accessorySuppliers.add(new Supplier<>(Accessory.class, accessoryStorage, accessoryDelay));
        }
    }

    private void initDealers() {
        int dealersNum = config.getInt("Dealers");
        int dealerDelay = config.getInt("DealerSpeed");
        boolean logSale = config.getBoolean("LogSale");
        dealers = new ArrayList<>();
        for (int i = 0; i < dealersNum; ++i) {
            dealers.add(new Dealer(carStorage, dealerDelay, i, logSale));
        }
    }

    public Factory(Config config) {
        this.config = config;
        initStorages();
        initSuppliers();
        initDealers();
    }

    public void start() {
        allThreads = new ArrayList<>();
        Thread bodySupplierThread = new Thread(bodySupplier);
        allThreads.add(bodySupplierThread);
        bodySupplierThread.start();

        Thread motorSupplierThread = new Thread(motorSupplier);
        allThreads.add(motorSupplierThread);
        motorSupplierThread.start();

        for (Supplier<Accessory> accessorySupplier : accessorySuppliers) {
            Thread thread = new Thread(accessorySupplier);
            allThreads.add(thread);
            thread.start();
        }

        initAssemblePoint();
        initController();

        for (Dealer dealer : dealers) {
            Thread thread = new Thread(dealer);
            allThreads.add(thread);
            thread.start();
        }
    }

    public FactoryStat getFactoryStat() {
        return new FactoryStat(assemblyPoint.getQueueSize(),
                bodyStorage.getItemsNum(), bodyStorage.getTotalProduced(),
                motorStorage.getItemsNum(), motorStorage.getTotalProduced(),
                accessoryStorage.getItemsNum(), accessoryStorage.getTotalProduced(),
                carStorage.getItemsNum(), carStorage.getTotalProduced());
    }

    public void setDealerSpeed(int delay) {
        for (Dealer dealer : dealers) {
            dealer.setDelay(delay);
        }
    }

    public void setAccessorySupplierSpeed(int delay) {
        for (Supplier<Accessory> accessorySupplier : accessorySuppliers) {
            accessorySupplier.setDelay(delay);
        }
    }

    public void setBodySupplierSpeed(int delay) {
        bodySupplier.setDelay(delay);
    }

    public void setMotorSupplierSpeed(int delay) {
        motorSupplier.setDelay(delay);
    }

    public void GUIWindowExit() {
        for (Thread thread : allThreads) {
            thread.interrupt();
        }
        assemblyPoint.stop();
    }
}
