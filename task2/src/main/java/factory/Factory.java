package factory;

import factory.details.Accessory;
import factory.details.Body;
import factory.details.Motor;
import factory.staff.AssemblyPoint;
import factory.staff.Dealer;
import factory.staff.Supplier;
import gui.FactoryListener;
import utilities.Config;
import java.util.ArrayList;
import java.util.List;

public class Factory implements FactoryListener {
    private final Config config;

    private Storage<Body> bodyStorage;
    private Storage<Motor> motorStorage;
    private Storage<Accessory> accessoryStorage;
    private ObservableStorage carStorage;

    private List<Supplier<Body>> bodySuppliers;
    private List<Supplier<Motor>> motorSuppliers;
    private List<Supplier<Accessory>> accessorySuppliers;
    private List<Dealer> dealers;

    private AssemblyPoint assemblyPoint;

    private ArrayList<Thread> allThreads;

    private void initStorages() {
        bodyStorage = new Storage<>(config.getInt("StorageBodySize"));
        accessoryStorage = new Storage<>(config.getInt("StorageAccessorySize"));
        motorStorage = new Storage<>(config.getInt("StorageMotorSize"));
        carStorage = new ObservableStorage(config.getInt("StorageCarSize"));
    }

    private void initAssemblePoint() {
        assemblyPoint = new AssemblyPoint(config.getInt("Workers"), bodyStorage,
                motorStorage, accessoryStorage, carStorage);
    }

    private void initController() {
        Controller controller = new Controller(assemblyPoint, carStorage);
        carStorage.register(controller);
    }

    private void initSuppliers() {
        bodySuppliers = new ArrayList<>();
        int bodySuppliersNum = config.getInt("BodySuppliers");
        int bodyDelay = config.getInt("BodySupplierDelay");
        for (int i = 0; i < bodySuppliersNum; ++i) {
            bodySuppliers.add(new Supplier<>(Body.class, bodyStorage, bodyDelay));
        }
        motorSuppliers = new ArrayList<>();
        int motorSuppliersNum = config.getInt("MotorSuppliers");
        int motorDelay = config.getInt("MotorSupplierDelay");
        for (int i = 0; i < motorSuppliersNum; ++i) {
            motorSuppliers.add(new Supplier<>(Motor.class, motorStorage, motorDelay));
        }
        accessorySuppliers = new ArrayList<>();
        int accessorySuppliersNum = config.getInt("AccessorySuppliers");
        int accessoryDelay = config.getInt("AccessorySupplierDelay");
        for (int i = 0; i < accessorySuppliersNum; ++i) {
            accessorySuppliers.add(new Supplier<>(Accessory.class, accessoryStorage, accessoryDelay));
        }
    }

    private void initDealers() {
        int dealersNum = config.getInt("Dealers");
        int dealerDelay = config.getInt("DealerDelay");
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

    @Override
    public void start() {
        allThreads = new ArrayList<>();
        for (Supplier<Body> bodySupplier : bodySuppliers) {
            Thread thread = new Thread(bodySupplier);
            allThreads.add(thread);
            thread.start();
        }
        for (Supplier<Motor> motorSupplier : motorSuppliers) {
            Thread thread = new Thread(motorSupplier);
            allThreads.add(thread);
            thread.start();
        }
        for (Supplier<Accessory> accessorySupplier : accessorySuppliers) {
            Thread thread = new Thread(accessorySupplier);
            allThreads.add(thread);
            thread.start();
        }
        initAssemblePoint();
        assemblyPoint.start();
        initController();
        for (Dealer dealer : dealers) {
            Thread thread = new Thread(dealer);
            allThreads.add(thread);
            thread.start();
        }
    }

    @Override
    public FactoryStat getFactoryStat() {
        return new FactoryStat(assemblyPoint.getPendingOrders(),
                bodyStorage.getItemsNum(), bodyStorage.getTotalProduced(),
                motorStorage.getItemsNum(), motorStorage.getTotalProduced(),
                accessoryStorage.getItemsNum(), accessoryStorage.getTotalProduced(),
                carStorage.getItemsNum(), carStorage.getTotalProduced());
    }

    @Override
    public void setDealerDelay(int delay) {
        for (Dealer dealer : dealers) {
            dealer.setDelay(delay);
        }
    }

    @Override
    public void setAccessorySupplierDelay(int delay) {
        for (Supplier<Accessory> accessorySupplier : accessorySuppliers) {
            accessorySupplier.setDelay(delay);
        }
    }

    @Override
    public void setBodySupplierDelay(int delay) {
        for (Supplier<Body> bodySupplier : bodySuppliers) {
            bodySupplier.setDelay(delay);
        }
    }

    @Override
    public void setMotorSupplierDelay(int delay) {
        for (Supplier<Motor> motorSupplier : motorSuppliers) {
            motorSupplier.setDelay(delay);
        }
    }

    @Override
    public void stop() {
        for (Thread thread : allThreads) {
            thread.interrupt();
        }
        assemblyPoint.stop();
    }
}